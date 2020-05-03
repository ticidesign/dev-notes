# Event Loop

The Event Loop is one of the most important aspects to understand about JavaScript.

This post aims to explain the inner details of how JavaScript works with a single thread, and how it handles asynchronous functions.

Your JavaScript code runs single threaded. There is just one thing happening at a time.

This is a limitation that’s actually very helpful, as it simplifies a lot how you program without worrying about concurrency issues.

You just need to pay attention to how you write your code and avoid anything that could block the thread, like synchronous network calls or infinite loops.

In general, in most browsers there is an event loop for every browser tab, to make every process isolated and avoid a web page with infinite loops or heavy processing to block your entire browser.

The environment manages multiple concurrent event loops, to handle API calls for example. Web Workers run in their own event loop as well.

You mainly need to be concerned that your code will run on a single event loop, and write code with this thing in mind to avoid blocking it.

An event loop has one or more task queues.

### Tool

[Loupe]((http://latentflip.com/loupe/)) is a little visualisation to help you understand how JavaScript's call stack/event loop/callback queue interact with each other.

### Videos
[What the heck is the event loop anyway? | Philip Roberts | JSConf EU 2014](https://www.youtube.com/watch?v=8aGhZQkoFbQ)
[Further Adventures of the Event Loop - Erin Zimmer - JSConf EU 2018](https://www.youtube.com/watch?v=u1kqx6AenYw)
[Jake Archibald: In The Loop - JSConf.Asia 2018](https://www.youtube.com/watch?v=cCOL7MC4Pl0)

### Articles
[The JavaScript Event Loop](https://flaviocopes.com/javascript-event-loop/)
[JavaScript Visualized: Event Loop](https://dev.to/lydiahallie/javascript-visualized-event-loop-3dif)