# Pure Functions


Pure functions are part of the functional programming approach. Functional programming is a paradigm. A mean of structuring and thinking about how we write our code at scale.

Some of the core categories:
- Function as a first class citizen.


[00:00:45]
So when we're writing code for other developers to pick up, when we're writing code for the ability to add many features in the coming months. We need a predictable way of being able to add new features. We need a way that ensures when someone else sees our code they can go, I can reason through this.

[00:01:04]
Often we think this scale is about efficiency and performance. Actually scale is often about, can other developers see my code and quickly add features to it because it was written in a clean, standardized manner, or one of those paradigms? Well what was the most popular paradigm for writing code?

[00:01:24]
What was the most popular sort of prominent paradigm for writing code in the past 25 years? It wasn't Functional Programming, it was something else. What was it, Griffin? What was it in the last 20 years? Most popular paradigm for writing code? Not Functional Programming, but something else.
>> Amine: Object oriented.

[00:01:41]

>> Will Sentance: Object oriented. That has been the most popular way of thinking. We're gonna see that tomorrow in JavaScript, what it looks like, or JavaScript sort of version of object oriented programming. It's very much a version, but burgeoning interest has formed around this paradigm known as functional programming. And it's a alternative way of thinking about structuring our code, such that it's easy to think about, reason about and understand and to add features to other developers to pick up etc, etc.

[00:02:18]
There are a number of core principles to it. Our friend Amine just shouted one of them out. Functions are first class citizens. We're gonna see what that means. I'm not gonna go into that yet, we're gonna see exactly what that means ahead. But what else? What's another classic characteristic of functional programming?

[00:02:37]
Clara, do you have an idea?
>> Clara: Immutability?
>> Will Sentance: What's immutability mean?
>> Clara: It's when you create a variable with some data in it, you can't change it.
>> Will Sentance: That's absolutely right. Let's even add along to that pure functions. What does pure functions mean, Clara?
>> Clara: So no side effects.

[00:02:58]
So if you have a call of function you don't then change other parts of the program.
>> Will Sentance: That's very nicely put. You do have one consequence. It's no side effects but you do have one consequence from calling your functions. Pure functions and high order functions, which is what Amine was shouting out, we’re going to come to those in a moment.

[00:03:18]
But pure functions no side effects, means when I run a function its only consequence, because it will have a consequence, its only consequence is determined by what, Claire?
>> Clara: From the data you give the parameters of that function.
>> Will Sentance: Is there any consequence for the little bit at the bottom, return?

[00:03:39]
It's only consequence is the return value. That's passing out to an execution context above some change. But it's not mutating anything in global memory. It's not mutating, mutating is a posh word for changing. I don't know why we need a fancy word when we can just use the word change.

[00:03:58]
I think people adopt words to compensate for not knowing the stuff inside their heart. So you say fancy words to mask it. So when we run a function, in our functional programming land we do not want the function to have any consequences, besides what is determined in the return statement.

[00:04:19]
We do not want, inside here to write something like num plus plus. And try to speak to num in global memory. Cause that makes writing tests to evaluate, is our code working, very hard. If my only consequence in running my function is whatever gets returned, then that means that this function evaluates to the return value.

[00:04:44]
Cuz this little block of code will literally become the code multiplied by two, will literally become a value of eight. This block of code here, evaluating or testing that is all we will need to do. Whereas if we have little side effects, it's very hard to test. Wow do I possibly write test to check what my function is doing if inside the function I'm having effects on my global memory from within the function?

[00:05:09]
That's very hard to test. Very hard to evaluate, so we don't wanna do that. Another aspect of pure functions, and we'll see this in a moment, is that if I pass in an input like an array or an object to my function that's being passed by reference. It's referencing the globally defined object or array and passing it in.

[00:05:29]
If I mutate that object or array directly inside of my function body, I'll actually be mutating, or altering, the globally defined version of that object or array as well. Again, super problematic. Makes it very hard for me to know what my function's gonna do, if it's gonna perform in the way I expect.

[00:05:49]
Whereas if I can just know that its only consequences are bundled up in this return statement, I'm very happy.

