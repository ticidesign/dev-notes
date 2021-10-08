## Typescript

Typescript improve readability with typescript numeric separators when working with large numbers.

- Compiles to readable JS
- Three parts: Language, Language Server and Compiler
- Kind of like a fancy linter

```ts
let car: {
  make: string
  model: string
  year: number
  chargeVoltage?: number // Optional
}
```

- [Types Categories](./type-categories.md)
- [Functions](./functions.md)
- [Generics](./generics.md)
- [Conditional Types](./conditional-type.md)
- [Index Access Types](./index-access-types.md)
- [Mapped Types](./mapped-types.md)
- [Resources](./resources.md)


how to type `forwardRef` with components that has an `as` props ie, `as='p'`
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