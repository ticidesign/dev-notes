# Functions

## Callable types

Both type aliases and and interfaces offer the capability to describe [call signatures](https://www.typescriptlang.org/docs/handbook/2/functions.html#call-signatures):

```ts
interface TwoNumberCalculation {
  (x: number, y: number): number
}

type TwoNumberCalc = (x: number, y: number) => number

const add: TwoNumberCalculation = (a, b) => a + b // (parameter) a: number

const subtract: TwoNumberCalc = (x, y) => x - y // (parameter) x: number

```

## Construct signatures

[Construct signatures](https://www.typescriptlang.org/docs/handbook/2/functions.html#construct-signatures) are similar to call signatures, except they describe what should happen with the new keyword.

```ts
interface DateConstructor {
  new (value: number): Date
}

let MyDateConstructor: DateConstructor = Date
const d = new MyDateConstructor() // const d: Date
```

## Function overloads

we define multiple function heads that serve as entry points to a single implementation:

```ts
type FormSubmitHandler = (data: FormData) => void
type MessageHandler = (evt: MessageEvent) => void

function handleMainEvent(
  elem: HTMLFormElement,
  handler: FormSubmitHandler
)
function handleMainEvent(
  elem: HTMLIFrameElement,
  handler: MessageHandler
)
function handleMainEvent(
  elem: HTMLFormElement | HTMLIFrameElement,
  handler: FormSubmitHandler | MessageHandler
) {}

handleMainEvent // function handleMainEvent(elem: HTMLFormElement, handler: FormSubmitHandler): any (+1 overload)
```

## `this` types

Sometimes we have a free-standing function that has a strong opinion around what this will end up being, at the time it is invoked.

```ts
function myClickHandler(
  this: HTMLButtonElement,
  event: Event
) {
  this.disabled = true //(property) HTMLButtonElement.disabled: boolean
}
myClickHandler // function myClickHandler(this: HTMLButtonElement, event: Event): void

const myButton = document.getElementsByTagName("button")[0]
const boundHandler = myClickHandler.bind(myButton) //const boundHandler: (event: Event) => void

boundHandler(new Event("click")) // bound version: ok
myClickHandler.call(myButton, new Event("click")) // also ok
```

## Classes Fields

## Access modifier keywords

TypeScript provides three access modifier keywords, which can be used with class fields and methods, to describe who should be able to see and use them.

| Keyword       | Who can access                                                                             |
| ---------- | ----------------------------------------------------------------------------------- |
| public |	everyone (this is the default) |
| protected | the instance itself, and subclasses    |
| private |	only the instance itself |

