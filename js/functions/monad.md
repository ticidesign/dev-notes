# Monad

Monads is a type of [Functor](./functors.md). As you know, a Functor is an object that implements *map*. So, a monad is a more powerful functor that also implements *flatMap*.

## Example

How to flatten the Either monad using both the chain and map methods.

```js

const Right = x =>
({
 chain: f => f(x),
 map: f => Right(f(x)),
 fold: (f, g) => g(x),
 toString: () => `Right(${x})`
})


const Left = x =>
({
 chain: f => Left(x),
 map: f => Left(x),
 fold: (f, g) => f(x),
 toString: () => `Left(${x})`
})

const fromNullable = x =>
 x != null ? Right(x) : Left(null)

const tryCatch = f => {
 try {
   return Right(f())
 } catch(e) {
   return Left(e)
 }
}

//=====================================

// const getPort = () => {
//   try {
//     const str = fs.readFileSync('config.json')
//     const config = JSON.parse(str)
//     return config.port
//   } catch(e) {
//     return 5000
//   }
// }

const readFIleSync = path => tryCatch(() => fromNullable(fs.readFileSync(path)))

const getPort = () => 
  readFIleSync('config.json')
  .map(contents => JSON.parse(contents))
  .map(config => config.port)
  .fold(() => 8080, x => x)

const result = getPort()

console.log(result)

```

## Articles

[Monads Made Simple](https://medium.com/javascript-scene/javascript-monads-made-simple-7856be57bfe8)
