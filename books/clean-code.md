# Clean Code

"Clean code always reads like well-written prose." - Grady Booch's description of clean code

1. Clean Code

- We will always develop on the code level because all the details matter.
  Good, clean code matters: Bad code eventually brings a product down, because during further development, productivity gradually approaches zero.
- Programmers must stand up for clean code just like managers stand up for requirements and schedules. But managers rely on programmers, not vice versa. And in order to go fast, we must have clean code.
- Definitions of clean code by Bjarne Stroustrup (C++), Grady Booch (UML), Dave Thomas, Michael Feathers, Ron Jeffries (XP), Ward Cunningham (XP, Wiki, Design Patterns): Clean code is elegant, simple, efficient, straightforward, crisp, clear, literate, readable by others, unsurprising, has minimal and explicit dependencies, has automated tests, minimizes the number of classes and methods, expresses its design ideas, handles errors, has nothing obvious that one could do to make it better, looks like the author has cared.
- Code gets read a lot (at least whenever someone is writing more code), so any school of clean code should emphasize readability. Cleaning up a little wherever you go is required to keep code clean.

2. Names

- Names are not just for your convenience. They are a tool, a powerful tool, that you can use to communicate with others. So choose your names well and thoughtfully.
- When you're choosing your names, to communicate your intent. Remember that if you have to put a comment that describes your name, your name is a bad name. You've chosen it badly.
- Always avoid disinformation. Don't let your names rot. Don't let their meanings drift. Don't let the names degrade. Make sure that a name says what it means, and means what it says. Remember that if you have to read the code to understand what a name means, that's a bad name.
- Create names that people can pronounce. Other people are going to have to talk about your code. They'll probably have to talk to you about your code. So make it easy for them. Pick names that people can say.
- Avoid encodings like Hungarian Notation. This is not the 1990s. We don't need to keep track of our types the way we used to. We're not coding in C. We have powerful IDEs and powerful language, and we have unit test. So we don't need to keep track of all our types that way. Don't put the I's in front of your interfaces. Don't put the M underscores in front of your member names. God help you, don't put PSZ in front of anything. Encodings are distracting. They obfuscate the code. They make it hard to read.
- Clean code should always read like well-written prose. So choose your parts of speech well. Give classes, noun names. Give variables nouns. Give methods verb names. If they're Booleans, make them predicates. Make sure that when you write a line of code, it looks like and reads like a sentence.
- Remember the scope rule. Variable names should be short, even one letter, if they're in a tiny little scope. But variable names should be long if they're in a big long scope. Global variable names, if you have them, should be very long and deep. Functions follow the opposite rule. Function names should be short if they've got a long scope. Function names should be long and descriptive, if they have a short scope. And the same rule goes for classes. Nice, short names for public classes. Longer names for private classes in tiny scopes.
- Finally, remember Martin Fowler's reprimand: "Any fool can write code a computer can understand. But it takes a good programmer to write code a human can understand."

3. Functions

- First rule of functions is that they are small. And the second rule, they're smaller than that.
- Lots of little, well-named functions will save you and everybody lots of time because they're gonna act like signposts along the way, helping everybody navigate through your code.
- Most of us don't have to worry about the efficiency of function-call overhead. In almost every case, worrying about how long it takes to call a function is probably misplaced.
- Making functions small will save you and everybody time
- Classes hide in long functions and that if you want to properly partition your program into classes, keep your functions small.
- Functions do one thing, and the only way to really be sure that a function does one thing is to **Extract Till You Drop**. If you can extract one function from another, you should, because that original function was, by definition, doing more than one thing.

Video [https://vimeo.com/channels/1111213/12643301](https://vimeo.com/channels/1111213/12643301)
