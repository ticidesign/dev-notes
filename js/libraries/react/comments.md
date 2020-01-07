## Add coment in React JSX

You can’t just use HTML comments inside of JSX because it thinks they are real DOM Nodes:

```js
render() {
  return (
    <div>
      <!-- This doesn't work! -->
    </div>
  )
}
```
You can use regular `/* Block Comments */`, but they need to be wrapped in curly braces:

```js
{/* A JSX comment */}
```