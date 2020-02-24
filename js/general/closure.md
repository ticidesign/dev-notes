# Closure

- Closure is the most esoteric of JavaScript concepts
- Enables powerful pro-level functions like ‘once’ and ‘memoize’
- Many JavaScript design patterns including the module pattern use closure
- Build iterators, handle partial application and maintain state in an asynchronous world

When our functions get called, we create a live store of data (local memory/ variable environment/state) for that function’s execution context.

When the function finishes executing, its local memory is deleted (except the returned value). 

But what if our functions could hold on to live data/state between executions?
This would let our function definitions have an associated cache/persistentmemory. But it starts with returning us returning a function from another function

We just saw that functions can be returned from other functions in JavaScript.

```js
    function instructionGenerator() {
        function multiplyBy2 (num){
            return num*2;
        }
        return multiplyBy2;
    }
    let generatedFunc = instructionGenerator()
```
How can we run/call multiplyBy2 now?

```js
    let result = generatedFunc(3) //6
```

[Exercices](http://csbin.io/closures) 

Calling a function in the same scope as it was defined

```js
function outer (){
    let counter = 0;
    function incrementCounter (){
        counter ++;
    }
    incrementCounter();
}
outer();
```
Where you define your functions determines what variables your function have access to when you call the function