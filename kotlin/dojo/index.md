# Kotlin Dojo

- Kotlin best practices
- Spring best practices
- Ensemble Programming
- Barbel stack brief overview
- Barbel system structure
- Kotlin practical examples (practice)
- Microservices in Atlassian
- App structure (controller, services, repository/dao) ???
- Caching Redis/memcached?
- Frameworks/libraries that will be used (Spring, anything else?)
- Intro to Coroutines in Kotlin

- Integrating with SWAG
- Servlet vs Reactive
- Synchronous vs Asynchronous
- SQS
- StreamHub

Barbel

[A primer on async programming - Part 1: Unblocking your code](https://hello.atlassian.net/wiki/spaces/~921076133/blog/2019/12/23/610923439/A%2Bprimer%2Bon%2Basync%2Bprogramming%2B-%2BPart%2B1%2BUnblocking%2Byour%2Bcode)

I don't know if any of you have access to O'Reilly's Safari platform, but if you do Hadi Hariri (Jetbrains developer advocate) has a playlist for learning Coroutines. https://learning.oreilly.com/playlists/dd42ec64-a539-4bab-ad55-3473db4c7177/

[restTemplate](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html)

Synchronous client to perform HTTP requests, exposing a simple, template method API over underlying HTTP client libraries such as the JDK HttpURLConnection, Apache HttpComponents, and others. https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/reactive/function/client/WebClient.html

Non-blocking, reactive client to perform HTTP requests, exposing a fluent, reactive API over underlying HTTP client libraries such as Reactor Netty.

`cache[key]?.let{…}`

## Docker Commands

Start : `docker run --name <some-redis> -d redis`

Stop: `docker stop <some-redis>`

Remove: `docker rm <some-redis>`

Run: `docker run --name <some-redis> -d -p 6379:6379 redis`

`docker ps -a`

`docker exec -it <some-redis> redis-cli`

`docker run -it --network <some-network> --rm redis redis-cli -h <some-redis>`

`docker logs --follow <some-redis>`

28/04/2021

- Deployed to ddev (and provisioned redis)
- Hit the endpoint in the app running in ddev, using an asap key from SWAG
- Created integration tests running redis
- Implemented a mechanism to cleanup redis between test runs
- Introduced the monitor pattern, with a cache hit metric.

## Kotlin Spring

- Never use [`@Autowired`](https://www.baeldung.com/spring-autowire), anti-pattern, it's terrible (probably just ok for tests)
- Never have more than 5 dependencies in the constructor. If there is more, consider split the compoment!

## Deploy Barbel

1. Build the docker image

`docker login docker.atl-pass.net`

`docker push <docker-image-name>:<tag>`

`atlas micros service`

`atlas micros service show -s barbel`

`atlas packages permission grant`
write permission granted successfully

`atlas micros service deploy --env <env> -service barbel --file <file-name>`

## MockK

[spy](https://mockk.io/#spy)

29/04/21

- Add more metrics (at least a cache miss)
- Change gradle to make the integration tests task depend on the runRedis task, so ITs work in bitbucket pipelines.
- Complete the jira part (and remove the mock)
- Start implementing the SQS sync/invalidation flow

[Streamhub](https://developer.atlassian.com/platform/streamhub/getting-started/)

30/04-21

[@EnableScheduling / @Scheduled](https://spring.io/guides/gs/scheduling-tasks/)
[Atlassian Micros - Lifecycle Hooks](https://hello.atlassian.net/wiki/spaces/MICROS/pages/169248561/Lifecycle+hooks)
