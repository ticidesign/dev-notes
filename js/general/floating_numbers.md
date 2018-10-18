## Floating Point Numbers

The term floating point is derived from the fact that there is no fixed number of digits before and after the decimal point; that is, the decimal point can float. There are also representations in which the number of digits before and after the decimal point is set, called fixed-pointrepresentations.

As a programmer, at some point, you will realise that the result of the folow calculation
` 0.1 + 0.2 = 0.3` isn't correct in the computer.

Add this to a programming langague like JavaScript and you will get something like this:

``` js
> 0.1 + 0.2
0.30000000000000004
```

```js
0.3 !== 0.30000000000000004
```

### Scientific notation

Scientific notation is the way that scientists easily handle very large numbers or very small numbers. For example, instead of writing 0.0000000015, we write 1.5 x 10<sup>-9</sup>. And 300000000, we write 3 * 10<sup>8</sup>. This is good for precision.


### Significant Digits

All non-zero digits are considered significant. For example, 91 has two significant figures (9 and 1), while 123.45 has five significant figures (1, 2, 3, 4 and 5).
Zeros appearing anywhere between two non-zero digits are significant. ex: (705.001 has 6 significant figures)
Trailing zeros in a number containing a decimal point are significant. ex: (10.0 has 3 significant figures)


### Advantages

Two advantages of floating point number are efficiency and speed. Speed because it's being build over many many years and it's lighten fast for computer can deal with it. And efficiency because it can deal with huge number(size of the universe number) but also really small number (size of an atom number) without need a lot of space.

Base 10 - human numbers

This is a representation of the result of 1/3 in base 10, result 0.33333...

| 100 | 10 | 1 | . | 1/10  | 1/100 | 1/1000 |
| --- | -- | - | - | ----  | ----- | ------ |
|     |    | 0 | . |   3   |   3   |    3   | ...
|     |    |   |   |       |       |        |
|     |    |   |   |       |       |        |
|     |    |   |   |       |       |        |


Base 2 - computer numbers

This is a representation of the result of 1/3 in base 10, result 0.0001100110011001100...

| 4 | 2 | 1 | . | 1/2  | 1/4 | 1/8 | 1/16 |
| - | - | - | - | ---  | --- | --- | ---- |
|   |   | 0 | . |  0   |  0  |  0  |  1   | ...
|   |   |   |   |      |     |     |      |
|   |   |   |   |      |     |     |      |



32 bits computer just store 23 significant digits, they also store where the decimal point is using Scientific notation in base 2. The problem is computers loose precision by not understand recuring numbers (repetition).
