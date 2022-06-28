# Full accessibility tree in Chrome DevTools

Chrome recently launched an experimental DevTools feature which allows developers and designers to get a full overview of the DOM tree from an accessibility focused viewpoint - the Full Accessibility Tree.

### What is web accessibility?

Web Accessibility can be defined as the inclusive practice of ensuring there are no barriers that prevent access and interaction with websites by people with disabilities, whether they be mental or physical. As digital organizations, making our products and the wider web accessibile benefits individuals, businesses and society as a whole.

[Intro to accessibility](https://www.w3.org/WAI/fundamentals/accessibility-intro/)

### What is the tree?

When a Chromium browser detects the use of assistive technology on a site, such as a screen reader, [Chromium's accessibility API](https://www.chromium.org/developers/design-documents/accessibility/) is utilised to interact with the web content. The model of the API is the accessibility tree - a tree that assistive technology can query for attributes and perform actions on.

### Why is this important?

Often when working on a web-application, it is easy for developers to miss obvious yet critical accessibility issues present in their work, primarily due to the lack of clear accessibility information in the Chrome DevTools elements pane. 

The dichotomy of the DOM tree and the accessibility tree can be clearly seen with an example of [Google](https://www.google.com/)

For non-visually impaired developers, the benefits of the accessibility tree should be quite explicit - we can easily see the site as a disabled user would, viewing at a glance the overall flow of the site and reading the site in a non-visual manner.

An example where we can clearly see accessibility issues on display is Atlassians Jira board view:

In this tree, we can see multiple issues clearly - repitition of words, buttons and comboboxes with no legible words and a generally confusing structure to parse through.

---

## Accessibility

Over the last few months, we've made some good first steps towards a more accessible app via basic accessibility unit testing. Unfortunately, automated testing via packages such as [axe-core](https://github.com/dequelabs/axe-core) can only reliably [catch approximately 50% of issues](https://github.com/dequelabs/axe-core#the-accessibility-rules).

The general recommendation in order to catch the vast majority of accessibility issues is a **manual accessibility audit**.

With the addition of the Chrome accessibility tree, we can now shave a lot of time off of a potential accessibility audit and catch the most glaring issues with ease - such as unnamed buttons and missing help text.

Further reading:

[Chrome Developer docs](https://developer.chrome.com/blog/full-accessibility-tree/)
