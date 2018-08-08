# De Morgan's Laws

DeMorgan's Law refers to the fact that there are two identical ways to write any combination of two conditions - specifically, the `AND` combination (both conditions must be true), and the `OR` combination (either one can be true). Examples are:

### Part 1 of DeMorgan's Law
- Statement: Alice has a sibling.
- Conditions: Alice has a brother `OR` Alice has a sister.
- Opposite: Alice is an only child (does `NOT` have a sibling).
- Conditions: Alice does `NOT` have a brother, `AND` she does `NOT` have a sister.

In other words: `NOT [A OR B] = [NOT A] AND [NOT B]`

### Part 2 of DeMorgan's Law
- Statement: Bob is a car driver.
- Conditions: Bob has a car `AND` Bob has a license.
- Opposite: Bob is `NOT` a car driver.
- Conditions: Bob does `NOT` have a car, `OR` Bob does `NOT` have a license.

In other words: `NOT [A AND B] = [NOT A] OR [NOT B]`.

It's certainly less confusing than all this nonsense about [truth tables](https://en.wikipedia.org/wiki/Truth_table) (even I'm getting confused looking at all of those).

## Inverting Logical Expressions with De Morgan's Laws

A Javascript expression will sometimes be more intuitive (and easier to understand) when written a certain way - but we may actually need the inverse of the expression. For example the code below only needs to react if (x && y) is false. We need to invert the expression.

```
if (x && y) {
  // we don't have anything to do here
} 
else {
  // we want to do something here
}
```

De Morgan's laws describe how to invert a logical expression. In the code below you can see De Morgan's Laws in Javascript syntax. The rule is to invert each logical term and invert each logical operator.

```
!(x || y) == (!x && !y)

!(x && y) == (!x || !y)
```

So, if you have the following:

```
if (!x || y || !z) { /* do something */ }
```
It is logically equivalent to:
```
if (!x && !y && !z)
```
It also works like so:
```
if !(x && !y && z)
```
turns into
```
if !(x || y || z)
```
And you can, of course, go in reverse.

The equivalence of these statements is easy to see using something called a truth table. In a truth table, you simply lay out your variables (x, y, z) and list all the combinations of inputs for these variables. You then have columns for each predicate, or logical expression, and you determine for the given inputs, the value of the expression. Any university curriculum for computer science, computer engineering, or electrical engineering will likely drive you bonkers with the number and size of truth tables you must construct.