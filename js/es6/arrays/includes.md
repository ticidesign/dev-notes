# includes

The `includes()` method determines whether an array includes a certain value among its entries, returning true or false as appropriate.

Syntax:

`arr.includes(valueToFind[, fromIndex])`


```js
const arr = [10, 20, NaN, 30, 40, 50];
arr.includes(20);				//true
arr.includes(60);				//false
arr.includes(20, 3);		//false
arr.includes(10, -2);		//false
arr.includes(40, -2);		//true
arr.includes(NaN);			//true
```
	 If `fromIndex` is negative, the computed index is calculated to be used as a position in the array at which to begin searching for valueToFind. If the computed index is less or equal than `-1 * array.length`, the entire array will be searched.
