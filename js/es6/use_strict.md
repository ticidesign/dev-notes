## use strict

Credits for [Lucy's Blog](https://lucybain.com/blog/2014/js-use-strict/)

If you put "use strict"; at the top of your code (or function), then the JS is evaluated in strict mode. Strict mode throws more errors and disables some features in an effort to make your code more robust, readable, and accurate.

### Advantages

Strict mode helps out in a couple ways:

- It catches some common coding bloopers, throwing exceptions.
- It prevents, or throws errors, when relatively “unsafe” actions are taken (such as gaining access to the global object).
- It disables features that are confusing or poorly thought out.

* Taken from [John Resig](https://johnresig.com/blog/ecmascript-5-strict-mode-json-and-more/):

Sounds great! I've been reading JavaScript: the Good Parts, and it seems there are a number of “features that are confusing or poorly thought out.” I'll take anything that helps me avoid them!

### Disadvantages

I had a harder time finding why people don’t like strict mode. The best explanation I found was when code mixed strict and “normal” modes. If a developer used a library that was in strict mode, but the developer was used to working in normal mode, they might call some actions on the library that wouldn’t work as expected. Worse, since the developer is in normal mode, they don’t have the advantages of extra errors being thrown, so the error might fail silently.

Also, as listed above, strict mode stops you from doing certain things. People generally think that you shouldn’t use those things in the first place, but some developers don’t like the constraint and want to use all the features of the language.

### References

- [John Resig](http://ejohn.org/blog/ecmascript-5-strict-mode-json-and-more/) has a fairly in depth discussion about what strict mode does and is used for
- [Dr. Axel Rauschmayer](http://2ality.com/2011/01/javascripts-strict-mode-summary.html) has a summary of the changes strict mode introduces
