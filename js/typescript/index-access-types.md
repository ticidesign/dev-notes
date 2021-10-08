# Index Access Types

These kinds of types are all about accessing some part of another type, via an index

```ts
interface Car {
  make: string;
  model: string;
  year: number;
  color: {
    red: string;
    green: string;
    blue: string;
  }
}

let carColor: Car['color'];
// let carColor: {
//     red: string;
//     green: string;
//     blue: string;
// }
```
And you can pass or “project” a union type (`|`) through `Car` as an index, as long as all parts of the union type are each a valid index

```ts
let carProperty: Car['color' | 'year'];     
// let carProperty: number | {
//     red: string;
//     green: string;
//     blue: string;
// }
```

