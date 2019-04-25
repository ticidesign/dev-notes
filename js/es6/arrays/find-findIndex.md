# find and findIndex

The `find()` method returns the value of the first element in the array that satisfies the provided testing function. Otherwise `undefined` is returned. The `findIndex()` method, will returns the `index` of a found element in the array instead of its value.

```js
const arr = [{ a: 1} , { a: 2 }];
arr.find(v => v && v.a > 1); //{ a: 2 }
arr.find(v => v && v.a > 10); //undefined
arr.findIndex(v => v && v.a > 10); //-1
```
