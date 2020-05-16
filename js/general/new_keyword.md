# New keyword operator

The new operator lets developers create an instance of a user-defined object type or of one of the built-in object types that has a constructor function.

The implicit code is still added in:

1. It binds `this` to a new object and sets its constructor and prototype.
2. It adds logic that will return `this` instead of a non-object.
3. It adds an implicit `return this` statement at the end.
