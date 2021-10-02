# Redis Cache with Spring Data

[Spring Boot cache with Redis](https://medium.com/@MatthewFTech/spring-boot-cache-with-redis-56026f7da83a)


RedisConfiguration.kt
```kotlin
@Configuration
class RedisConfiguration {
    @Bean
    fun reactiveRedisTemplate(
        connectionFactory: ReactiveRedisConnectionFactory,
        objectMapper: ObjectMapper
    ): ReactiveRedisTemplate<String, BoardScopeIssues> {

        val valueSerializer = Jackson2JsonRedisSerializer(BoardScopeIssues::class.java).apply {
            setObjectMapper(objectMapper)
        }

        return ReactiveRedisTemplate(
            connectionFactory,
            newSerializationContext<String, BoardScopeIssues>(StringRedisSerializer())
                .value(valueSerializer)
                .build()
        )
    }
}
```

CacheCLient.kt
```kotlin
// Abstraction layer over our actual cache implementation (redis client, memcache etc)
interface CacheClient {
    suspend fun get(key: String): BoardScopeIssues?

    suspend fun put(key: String, newValue: BoardScopeIssues, ttl: Duration): Boolean

    suspend fun invalidate(key: String): Boolean

    suspend fun invalidateAll(): String
}
```

RedisClient.kt 
```kotlin
@Component
class RedisCacheClient(
    private val reactiveRedisTemplate: ReactiveRedisTemplate<String, BoardScopeIssues>
) : CacheClient {

    override suspend fun get(key: String): BoardScopeIssues? = reactiveRedisTemplate.opsForValue()
        .get(key)
        .awaitSingleOrNull()

    override suspend fun put(key: String, newValue: BoardScopeIssues, ttl: Duration): Boolean = reactiveRedisTemplate.opsForValue()
        .set(key, newValue, ttl)
        .awaitSingle()

    override suspend fun invalidate(key: String): Boolean = reactiveRedisTemplate.opsForValue()
        .delete(key)
        .awaitSingle()

    override suspend fun invalidateAll(): String = reactiveRedisTemplate.connectionFactory.reactiveConnection
        .serverCommands()
        .flushDb()
        .awaitSingle()
}
```