# Conditional Types

## Type Queries

Type queries allow us to obtain type information from values, which is an incredibly important capability — particularly when working with libraries that may not expose type information in a way that’s most useful for you

The `keyof` type query allows us to obtain type representing all property keys on a given interface

```ts
type DatePropertyNames = keyof Date //type DatePropertyNames = keyof Date
```

The `typeof` type query allows you to extract a type from a value. An example is shown below

```ts
async function main() {
  const apiResponse = await Promise.all([
    fetch("https://example.com"),
    Promise.resolve("Titanium White"),
  ])

  type ApiResponseType = typeof apiResponse //type ApiResponseType = [Response, string]
}
```

## Ternary operator with values

`condition ? exprIfTrue : exprIfFalse`

## Extract and Exclude

`Extract` is useful for obtaining some sub-part of a type that is assignable to some other type.

```ts
// a set of four specific things
type FavoriteColors =
  | "dark sienna"
  | "van dyke brown"
  | "yellow ochre"
  | "sap green"
  | "titanium white"
  | "phthalo green"
  | "prussian blue"
  | "cadium yellow"
  | [number, number, number]
  | { red: number; green: number; blue: number }

type StringColors = Extract<FavoriteColors, string> 
// type StringColors = "dark sienna" | "van dyke brown" | "yellow ochre" | "sap green" | "titanium white" | "phthalo green" | "prussian blue" | "cadium yellow"

type ObjectColors = Extract<FavoriteColors, { red: number }>
/**
  type ObjectColors = {
    red: number;
    green: number;
    blue: number;
  }
*/
          
type TupleColors = Extract<FavoriteColors, [number, number, number]>
// type TupleColors = [number, number, number]
```

`Exclude` is the opposite of `Extract`, in that it’s useful for obtaining the part of a type that’s not assignable to some other type

```ts
// a set of four specific things
type FavoriteColors =
  | "dark sienna"
  | "van dyke brown"
  | "yellow ochre"
  | "sap green"
  | "titanium white"
  | "phthalo green"
  | "prussian blue"
  | "cadium yellow"
  | [number, number, number]
  | { red: number; green: number; blue: number }

type NonStringColors = Exclude<FavoriteColors, string>        
// type NonStringColors = [number, number, number] | {
//     red: number;
//     green: number;
//     blue: number;
// }
```

How do these work? Here’s the complete source code for these types:

```ts
/**
 * Exclude from T those types that are assignable to U
 */
type Exclude<T, U> = T extends U ? never : T;
/**
 * Extract from T those types that are assignable to U
 */
type Extract<T, U> = T extends U ? T : never;
```

## Inference with Conditional Type

In the [same release where conditional types were added to TypeScript](https://www.typescriptlang.org/docs/handbook/release-notes/typescript-2-8.html) a new `infer` keyword was added as well. This keyword, which can only be used in the context of a condition expression (within a conditional type declaration) is an important tool for being able to extract out pieces of type information from other types.

Example [ConstructorArgs](https://github.com/mike-north/types/blob/master/src/classes.ts)