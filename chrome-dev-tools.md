# Mastering Chrome DevTools

[Official Documentation](https://developers.google.com/web/tools/chrome-devtools/)

[Jon Kuperman](https://github.com/jkup/ama)
[Mastering Chrome Devtools](https://github.com/jkup/mastering-chrome-devtools)
[Slides](https://slides.com/jkup/devtools/#/6)

# History of Debugging

A reviews a brief history of browser developer tools. Years ago developers relied on viewing the page source, alert boxes and plugins like Firebug. Today each browser has its own set of developer tools enabling a much richer debugging experience.

[Live DOM Viewer](http://software.hixie.ch/utilities/js/live-dom-viewer/)
[Firebug 0.3]()

## Use the DevTools as an IDE

You can use Chrome DevTools as a full time IDE. It can do a bunch of amazing things, such as:

- Edit HTML and CSS
- Save and reuse your color palette (Multiple options: by redefining color scheme with OS'color wheel UI, Google's Material Design clolr scheme or DevTools can generate the site's own color scheme)
- Scroll items into view (right click on the HTML element)
- Hide and show elements (click the 'H' key to toggle the visibility of a HTML element)
- Trigger various states (:hover, :focus, etc)
- Display computed styles (CSS tab)
- Find event listeners
- Change color formats from hexadecimal to RGB, etc (click the 'shift' key on the square of the css color)
- Drag and drop DOM nodes
- Visualize CSS specificity ([CSS SpeciFISHity](https://specifishity.com/))
- Pretty print source code (Source tab)
- Break on DOM changes
- Save changes to disk ([Edit Files With Workspaces](https://developers.google.com/web/tools/chrome-devtools/workspaces/))
- Use special symbols in the console (Recent Selection History | $0 or $1)

## Debug complex applications

DevTools provides us with a powerful step through debugger located under the Source panel.

- Step through a JavaScript file (Watch, Call Stack, Scope and Breakpoints)
- Add breakpoints
- Watch variables
- Blackbox scripts (Inside Call Stack, click right on the js file you want to blackbox)
- Add conditional breakpoints (right click on the line number you want to debbug and add conditional breakpoint)
- Add XHR breakpoint ()

## Analyze network traffic

## Audit a website

## Monitor real user performance

## Profile CPU usage

## Analyze Node.js performance

## Find and fix memory leaks

## Tips and tricks!
