# Generics

[Generics](https://www.typescriptlang.org/docs/handbook/2/generics.html) allow us to parameterize types, which unlocks great opportunity to reuse types broadly across a TypeScript project.

    Generics may change their type, depending on the type parameters you use with them.


```ts
function listToDict<T>(
  list: T[],
  idGen: (arg: T) => string
): { [k: string]: T } {
  const dict: { [k: string]: T } = {}
  return dict
}
```

Example

```ts
function wrapInArray<T>(arg: T): [T] { //function wrapInArray<T>(arg: T): [T]
  return [arg]
}

wrapInArray(3) // function wrapInArray<number>(arg: number): [number]
wrapInArray(new Date()) // function wrapInArray<Date>(arg: Date): [Date]
wrapInArray(new RegExp("/s/")) // function wrapInArray<RegExp>(arg: RegExp): [RegExp]
```

## Scopes and Constraints