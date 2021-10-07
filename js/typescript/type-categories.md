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
} else {
  // In this branch of your code, second is the user info
  second
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

## Top Types

A [top type](https://en.wikipedia.org/wiki/Top_type) (symbol: `⊤`) is a type that describes any possible value allowed by the system. To use our set theory mental model, we could describe this as `{x| x could be anything }`

TypeScript provides two of these types: `any` and `unknown`.

`any`

You can think of values with an `any` type as “playing by the usual JavaScript rules”. Here’s an illustrative example:

```ts
let flexible: any = 4
flexible = "Download some more ram"
flexible = window.document
flexible = setTimeout
```

`unkwon`

Like any, unknown can accept any value:

```ts
let flexible: unknown = 4
flexible = "Download some more ram"
flexible = window.document
flexible = setTimeout
```
However, `unknown` is different from `any` in a very important way:

    Values with an unknown type cannot be used without first applying a type guard

## Bottom Types

A [bottom type](https://en.wikipedia.org/wiki/Bottom_type) (symbol: `⊥`) is a type that describes no possible value allowed by the system. To use our set theory mental model, we could describe this as “any value from the following set: `{ }`(intentionally empty)”

TypeScript provides one bottom type: `never`.

```ts
function obtainRandomVehicle(): any {
  return {} as any
}
class Car {
  drive() {
    console.log("vroom")
  }
}
class Truck {
  tow() {
    console.log("dragging something")
  }
}
class Boat {
  isFloating() {
    return true
  }
}
type Vehicle = Truck | Car | Boat

let myVehicle: Vehicle = obtainRandomVehicle()

class UnreachableError extends Error {
  constructor(_nvr: never, message: string) {
    super(message)
  }
}

// The exhaustive conditional
if (myVehicle instanceof Truck) {
  myVehicle.tow() // Truck
} else if (myVehicle instanceof Car) {
  myVehicle.drive() // Car
} else {
  // NEITHER!
  throw new UnreachableError(
    myVehicle,
    `Unexpected vehicle type: ${myVehicle}`
  )
}
```

## Type guards and narrowing

- Built-in type guards: `typeof` and `instanceof`.

```ts
let value:
  | Date
  | null
  | undefined
  | "pineapple"
  | [number]
  | { dateRange: [Date, Date] }

// instanceof
if (value instanceof Date) {
  value //let value: Date
}
// typeof
else if (typeof value === "string") {
  value //let value: "pineapple"
}
// Specific value check
else if (value === null) {
  value //let value: null
}
// Truthy/falsy check
else if (!value) {
  value //let value: undefined
}
// Some built-in functions
else if (Array.isArray(value)) {
  value //let value: [number]
}
// Property presence check
else if ("dateRange" in value) {
  value //let value: { dateRange: [Date, Date]; }
} else {
  value //let value: never
}
```

- User-defined type guards: `value is Foo` and `asserts value is Foo`

## Nullish values

`null`

null means: there is a value, and that value is nothing. While some people believe that null is not an important part of the JS language, I find that it’s useful to express the concept of a “nothing” result (kind of like an empty array, but not an array).

`undefined`

undefined means the value isn’t available (yet?)

`void`

void should exclusively be used to describe that a function’s return value should be ignored

`Non-null assertion operator`

The non-null assertion operator (`!.`) is used to cast away the possibility that a value might be null or undefined.

`Definite assignment operator`

The definite assignment `!:` operator is used to suppress TypeScript’s objections about a class field being used, when it can’t be proven1 that it was initialized.