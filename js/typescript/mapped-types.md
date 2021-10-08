# Mapped Types

Mapped allow types to be defined in other types through a much more flexible version of an index signature.

```ts
type Fruit = {
  name: string
  color: string
  mass: number
}

// mapped type
type MyRecord = { [FruitKey in "apple" | "cherry"]: Fruit }

function printFruitCatalog(fruitCatalog: MyRecord) {
  fruitCatalog.cherry //(property) apple: Fruit
  fruitCatalog.apple //(property) apple: Fruit
                
  fruitCatalog.pineapple //ERROR: Property 'pineapple' does not exist on type 'MyRecord'.

```

## Record

If make our type a bit more generalized with some type params instead of hardcoding `Fruit` and `"apple" | "cherry"` as shown below, we’ll arrive at a built-in utility type that comes with TypeScript.

```ts
type MyRecord = { [FruitKey in "apple" | "cherry"]: Fruit }

type MyRecord<KeyType, ValueType> = { [Key in KeyType]: ValueType }

type MyRecord<KeyType extends string, ValueType> = {
  [Key in KeyType]: ValueType
}
```

The built-in TypeScript type, which matches this pretty much exactly:

```ts
/**
 * Construct a type with a set of properties K of type T
 */
type Record<K extends keyof any, T> = {
  [P in K]: T
}

// keyof any (string | number | symbol)
```

## Pick

```ts

type PickProperties<
  ValueType,
  Keys extends keyof ValueType
> = {
  [Key in Keys]: ValueType[Key]
}
```

```ts
/**
 * From T, pick a set of properties whose keys are in the union K
 */
type Pick<T, K extends keyof T> = {
  [P in K]: T[P]
}
```

## Mapping modifiers

`readonly` and/or `optional`

```ts
/**
 * Make all properties in T optional
 */
type Partial<T> = {
  [P in keyof T]?: T[P]
}
/**
 * Make all properties in T required
 */
type Required<T> = {
  [P in keyof T]-?: T[P]
}
/**
 * Make all properties in T readonly
 */
type Readonly<T> = {
  readonly [P in keyof T]: T[P]
}
```

## Template Literal Types

TypeScript provides a few special types you can use within these template literal types

- UpperCase
- LowerCase
- Capitalize
- Uncapitalize

```ts
type ArtFeatures = "cabin" | "tree" | "sunset"
type Colors =
  | "darkSienna"
  | "sapGreen"
  | "titaniumWhite"
  | "prussianBlue"

type ArtMethodNames =
  `paint${Capitalize<Colors>}${Capitalize<ArtFeatures>}`
  // type ArtMethodNames = "paintDarkSiennaCabin" | "paintDarkSiennaTree" | "paintDarkSiennaSunset" | "paintSapGreenCabin" | "paintSapGreenTree" | "paintSapGreenSunset" | "paintTitaniumWhiteCabin" | ... 4 more ... | "paintPrussianBlueSunset"
```