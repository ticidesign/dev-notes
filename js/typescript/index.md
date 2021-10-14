## Typescript

Typescript is a programming language that improve readability with typescript numeric separators when working with large numbers.

- Compiles to readable JS
- A syntactic superset of Javascript
- Three parts: Language, Language Server and Compiler
- Kind of like a fancy linter and a fantastic static analysis tool

Example: 

```ts
let car: {
  make: string
  model: string
  year: number
  chargeVoltage?: number // Optional
}
```
## Table of Content

- [Types Categories](./type-categories.md)
- [Functions](./functions.md)
- [Generics](./generics.md)
- [Conditional Types](./conditional-type.md)
- [Index Access Types](./index-access-types.md)
- [Mapped Types](./mapped-types.md)
- [Resources](./resources.md)


How to type `forwardRef` with components that has an `as` props ie, `as='p'`
[Forwarding refs for a polymorphic React component in TypeScript](https://www.benmvp.com/blog/forwarding-refs-polymorphic-react-component-typescript/)

```ts
import { ComponentPropsWithoutRef, ElementType, ReactElement, ReactNode, Ref, forwardRef } from 'react';

type ElementTagNameMap = HTMLElementTagNameMap &
  Pick<SVGElementTagNameMap, Exclude<keyof SVGElementTagNameMap, keyof HTMLElementTagNameMap>>;

type AsProp<Comp extends ElementType, Props> = {
  as?: Comp;
  ref?: Ref<
    Comp extends keyof ElementTagNameMap
      ? ElementTagNameMap[Comp]
      : Comp extends new (...args: any) => any
      ? InstanceType<Comp>
      : undefined
  >;
} & Omit<ComponentPropsWithoutRef<Comp>, 'as' | keyof Props>;

export type CompWithAsProp<Props, DefaultElementType extends ElementType> = <
  Comp extends ElementType = DefaultElementType
>(
  props: AsProp<Comp, Props> & Props
) => ReactElement;

export const forwardRefWithAs = <DefaultElementType extends ElementType, BaseProps>(
  render: (props: BaseProps & { as?: ElementType }, ref: React.Ref<any>) => Exclude<ReactNode, undefined>
): CompWithAsProp<BaseProps, DefaultElementType> => {
  // @ts-ignore
  return forwardRef(render);
};
```

## TypeScript Interfaces vs Types

For use cases such as creating new types through things like primitives, union types, and tuple types, use the `type` keyword. For anything else (objects/arrays), it’s an `interface`.

An `interface` is extremely helpful when dealing with data structures as they’re a very visual representation (albeit so is `type`). It’s completely okay to choose a `type` for your objects and arrays too.

## Classes vs Interfaces in TypeScript

`Classes` and `interfaces` are powerful structures that facilitate not just object-oriented programming but also type-checking in TypeScript. A `class` is a blueprint from which we can create objects that share the same configuration - properties and methods. An `interface` is a group of related properties and methods that describe an object, but neither provides implementation nor initialisation for them.

Unlike `classes`, an `interface` is a virtual structure that only exists within the context of TypeScript. The TypeScript compiler uses interfaces solely for type-checking purposes. Once your code is transpiled to its target language, it will be stripped from its interfaces - JavaScript isn’t typed, there’s no use for them there.

And, while a `class` may define a `factory` or a `singleton` by providing initialisation to its properties and implementation to its methods, an `interface` is simply a structural contract that defines what the properties of an object should have as a name and as a type. How you implement or initialise the properties declared within the `interface` is not relevant to it. Let’s see an example by transforming our Pizza `class` into a Pizza `interface`:

```ts
interface Pizza {
  name: string;
  toppings: string[];
}

class PizzaMaker {
  static create(event: Pizza) {
    return { name: event.name, toppings: event.toppings };
  }
}
```