# parseUrl

Implement parseUrl() manually without any native URL parsing APIs or libraries.

```js
/**
 * CHALLENGE!
 * Implement parseUrl() manually without any native URL parsing APIs or libraries
 * GO!
 */

function parseUrl() {}

console.log(parseUrl("http://example.com?a=1&b=2&a=2")); // should log http://example.com?a=1&b=2
console.log(parseUrl("http://example.com?a=1&b=2", ["b"])); // should log http://example.com?a=1
console.log(parseUrl("http://example.com?a=1&b=2", ["a", "b"])); // should log http://example.com
```
