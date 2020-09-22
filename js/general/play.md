
Temporal Dead Zone
JS does 2 passes when executing code; the "Hoisting" / scope phase, then the execution phase. The hoisting / scope phase is where it learns what `let` / `var` / `const` / `function` exist within a scope (what's a scope you ask!? That's a whole other discussion), then during the execution phase it'll hit the `console.log(a)` and know there is a `let a` later so therefore Reference Error.

https://gist.github.com/jesstelford/9a35d20a2aa044df8bf241e00d7bc2d0

https://gist.github.com/jesstelford/bbb30b983bddaa6e5fef2eb867d37678