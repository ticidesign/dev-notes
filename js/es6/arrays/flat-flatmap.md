# flat and flatMap

The `flat()` method creates a new array with all sub-array elements concatenated into it recursively up to the specified depth.

Syntax:

```js
const newArray = arr.flat([depth]);
```

Example:

```js
const nestedValues = [1, [2, 3], [ [] ], [4, [5]], 6 ]];
nestedValues.flat(0); //[1, [2, 3], [ [] ], [4, [5]], 6 ]]
nestedValues.flat(/* default: 1 */); //[1, 2, 3, [] , 4, [5], 6 ]
nestedValues.flat(2); //[1, 2, 3 , 4, 5, 6 ]
```

The `flatMap()` method first maps each element using a mapping function, then flattens the result into a new array. It is identical to a map followed by a flat of depth 1, but flatMap is often quite useful, as merging both into one method is slightly more efficient.

Syntax:

```js
var new_array = arr.flatMap(function callback(currentValue[, index[, array]]) {
    // return element for new_array
}[, thisArg])
```

Example:

```js
[1,2,3].map(v => [v * 2, String(v * 2)]); 				//[[2,"2"], [4,"4"], [6,"6"]]

[1,2,3].map(v => [v * 2, String(v * 2)]).flat();	//[2,"2",4,"4",6,"6"]

[1,2,3].flatMap(v => [v * 2, String(v * 2)]);			//[2,"2",4,"4",6,"6"]
```
