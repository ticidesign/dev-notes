# Modern JS Language Features

## Optional Chaining (?.)

Optional chaining lets us write code where TypeScript can immediately stop running some expressions if we run into a `null` or `undefined`.


Stops running expression if a null or undefined is encountered

- 🚧 **Warning:** adds complexity. Beware of multiple optional links in a single expression
- 🚧 **Warning:** not appropriate to just “power through” nullish values
- 🚧 **Warning:** only short-circuits the expression in question, not any other compound expressions joined by operators

```ts
// Before
let x = foo === null || foo === undefined ? undefined : foo.bar.baz();
// After
let x = foo?.bar.baz();
```

## Nullish Coalescing (??)

You can think of this feature - the `??` operator - as a way to “fall back” to a default value when dealing with `null` or `undefined`. When we write code like

Useful for fallback defaults, where a user-supplied 0 or ‘’ would have been treated improperly

- 🚧 **Warning:** not the same as ||, due to falsy values that aren’t nullish

```ts
// Before
let x = foo !== null && foo !== undefined ? foo : bar();
// After
let x = foo ?? bar();
```

## Namespace exports

```ts
export * as utils from './utils.mjs';
```

## ECMAScript Private Fields 

```ts
class Foo {
  #bar;
}
```

- “hard private” (undetectable from the outside)
- Cannot be overridden by subclasses
- Use WeakMap under the hood, so might perform differently compared to public class fields (not a problem unless you’re on a really hot path)

## Tuple Types

### Variadic Turple Types(...T)

```ts
type StrStrNumNumBool = [...Strings, ...Numbers, boolean];
```

Consider a function in JavaScript called `concat` that takes two array or tuple types and concatenates them together to make a new array.

```ts
function concat(arr1, arr2) {
  return [...arr1, ...arr2];
}

function tail<T extends any[]>(arr: readonly [any, ...T]) {
  const [_ignored, ...rest] = arr;
  return rest;
}
```

## Labeled Tuple Elements

```ts
function foo(x: [first: string, second: number]) {}
```


```ts
function foo(...args: [string, number]): void {
  // ...
}

function foo(arg0: string, arg1: number): void {
  // ...
}
```

## More powerful type aliases and interfaces

### Recursive type aliases

JSON can now be typed in a single type alias!

### Recursive conditional types

```ts
type ElementType<T> = T extends ReadonlyArray<infer U>
  ? ElementType<U>
  : T;
```

### Template type literals

```ts
type Corner = `${'top'|'bottom'}-${'left'|'right'}`;
```

Allows capitalize, uncapitalize, uppercase, lowercase

```ts
type Corner = `${capitalize 'top'|'bottom'}-${'left'|'right'}`;
```

## Editor Experience

### `/** @deprecated \*/` 

Strikes out symbols in vscode
Support for "assert that this is not deprecated" in tests (we'll see this later)

### `/** @see \*/` 

- Reference other documentation in JSDoc comments
- You can "jump to definition" just like it's code

## Error and Assertion Handling

### `// @ts-expect-error`

- A huge win for negative test cases
- I prefer it nearly always over `// @ts-ignore`

### Unknown on catch clause

- A big improvement over `any` error types
- Forces you to deal with `instanceof Error` properly

### Assertion functions

Type guards, but based on return/throw instead of returning true/false

```ts
function assertIsArray(val: any): asserts val is any[] {
  if (!Array.isArray(val)) throw new Error(`${val} is not an array`);
}
```

This makes things like tests _much_ easier. The code you want to write for your tests
and the code you need to write to make type-checking happy are now the same thing.

## Typed JS Support

### Declaration files can be generated from `.js` 

This is a big deal for projects that may not be viable for converting to TS -- they can still offer first-class TS support entirely based on their JSDoc comments

## Modules

### Type-only imports

```ts
import type { SomeThing } from './some-module.js';
```

Big win for lazy loading, in situations where you _only_ need to refer to a package for type information

