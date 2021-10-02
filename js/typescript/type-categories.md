# Types Categories


## Index signatures

Sometimes we need to represent a type for dictionaries, where values of a consistent type are retrievable by keys.

```ts
const phones: {
  [k: string]: {
    country: string
    area: string
    number: string
  }
} = {}
```

## Tuples

Sometimes we may want to work with a multi-element, ordered data structure, where position of each item has some special meaning or convention. This kind of structure is often called a tuple.

```ts
let myCar: [number, string, string] = [
  2002,
  "Toyota",
  "Corolla",
]
```

## Union Types

Union types in TypeScript can be described using the `|` (pipe) operator.

```ts
function flipCoin(): "heads" | "tails" {
  if (Math.random() > 0.5) return "heads"
  return "tails"
}

const outcome = flipCoin() // const outcome: "heads" | "tails"
```

## Narrowing with type guards

Ultimately, we need to “separate” the two potential possibilities for our value, or we won’t be able to get very far. We can do this with [type guards](https://www.typescriptlang.org/docs/handbook/2/narrowing.html).

    Type guards are expressions, which when used with control flow statement, allow us to have a more specific type for a particular value.

```ts
function maybeGetUserInfo():
  | ["error", Error]
  | ["success", { name: string; email: string }] {
  if (Math.random() > 0.5) {
    return [
      "success",
      { name: "Mike North", email: "mike@example.com" },
    ]
  } else {
    return [
      "error",
      new Error("The coin landed on TAILS :("),
    ]
  }
}
/// ---cut---
const outcome = maybeGetUserInfo()
const [first, second] = outcome
//            ^?
if (second instanceof Error) {
  // In this branch of your code, second is an Error
  second
  // ^?
} else {
  // In this branch of your code, second is the user info
  second
  // ^?
}
```

## Intersection Types

Intersection types in TypeScript can be described using the `&` (ampersand) operator.

```ts
const ONE_WEEK = 1000 * 60 * 60 * 24 * 7 // 1w in ms
function makeWeek(): Date & { end: Date } {

  const start = new Date()
  const end = new Date(start.valueOf() + ONE_WEEK)

  return { ...start, end } 
}

const thisWeek = makeWeek()
thisWeek.toISOString() // const thisWeek: Date & { end: Date; }
thisWeek.end.toISOString() //(property) end: Date

```

## Type aliases

TypeScript provides two mechanisms for centrally defining types and giving them useful and meaningful names: interfaces and type aliases.

```ts
export type UserContactInfo = {
  name: string
  email: string
}
import { UserContactInfo } from "./types"


type UserInfoOutcomeError = ["error", Error]
type UserInfoOutcomeSuccess = [
  "success",
  { name: string; email: string }
]
type UserInfoOutcome =
  | UserInfoOutcomeError
  | UserInfoOutcomeSuccess

export function maybeGetUserInfo(): UserInfoOutcome

```

## Interfaces

An [interface](https://www.typescriptlang.org/docs/handbook/2/everyday-types.html#interfaces) is a way of defining an object type. An “object type” can be thought of as, “an instance of a class could conceivably look like this”.

```ts
interface UserInfo {
  name: string
  email: string
}
function printUserInfo(info: UserInfo) {
  info.name // (property) UserInfo.name: string
}
```

## Recursion

[Recursive types](https://www.typescriptlang.org/docs/handbook/release-notes/typescript-4-1.html#recursive-conditional-types), are self-referential, and are often used to describe infinitely nestable types. For example, consider infinitely nestable arrays of numbers.

```ts
type NestedNumbers = number | NestedNumbers[]
const val: NestedNumbers = [3, 4, [5, 6, [7], 59], 221]
```

#### Example

```ts
type JSONPrimitive = string | number | boolean | null
type JSONObject = { [k: string]: JSONValue }
type JSONArray = JSONValue[]
type JSONValue = JSONArray | JSONObject | JSONPrimitive

////// DO NOT EDIT ANY CODE BELOW THIS LINE //////
function isJSON(arg: JSONValue) {}

// POSITIVE test cases (must pass)
isJSON("hello")
isJSON([4, 8, 15, 16, 23, 42])
isJSON({ greeting: "hello" })
isJSON(false)
isJSON(true)
isJSON(null)
isJSON({ a: { b: [2, 3, "foo"] } })

// NEGATIVE test cases (must fail)
// @ts-expect-error
isJSON(() => "")
// @ts-expect-error
isJSON(class {})
// @ts-expect-error
isJSON(undefined)
// @ts-expect-error
isJSON(new BigInt(143))
// @ts-expect-error
isJSON(isJSON)


```

```ts
interface TwoNumberCalculation {
  (x: number, y: number): number
}

type TwoNumberCalc = (x: number, y: number) => number

const add: TwoNumberCalculation = (a, b) => a + b
//                                 ^?
const subtract: TwoNumberCalc = (x, y) => x - y
//                               ^?
```