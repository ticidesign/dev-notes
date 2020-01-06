# Next-generation web styling digested (Chrome Dev Summit 2019)

Digested from Next-generation web styling (Chrome Dev Summit 2019) https://www.youtube.com/watch?v=-oyeaIirVC0.

## scroll-snap
Native-like scroll containers that will snap into place. TBH it’s still kind of weird, and it won’t overflow back to the initial element, but it could prove useful for some interactions. Maybe.

https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Scroll_Snap


## :focus-within
It’s been around a while. Basically allows you to style an element if any child element has focus within it. Super useful - and everything except IE11 support it. Use it!

https://developer.mozilla.org/en-US/docs/Web/CSS/:focus-within

```js
<div 
  css={{
    ':focus-within': {
      backgroundColor: 'blue',
    }
  }}
>
  <button>hey</button>
</div>
```

## Logical properties
A flip on how we define margins and padding using properties that natively handle RTL, LTR, and vertical displayed languages. It’s weird but cool.

https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Logical_Properties

## position: sticky
It’s been around for a while. Native sticky-ness that doesn’t have any of the perf problems doing it with Javascript. Watch out for the CSS gotchas though!

https://developer.mozilla.org/en-US/docs/Learn/CSS/CSS_layout/Positioning

## Backdrop filter
Think about the usual filter property except this applies to any elements that are underneath the styled element (instead of the element itself). Super useful.

## :is() css selector
Speaks for itself. Useful for simplifying CSS selectors.

## Flexbox gap
Makes it easier for children to have space between it without affecting the outside of the element! Super useful for container style elements. Can imagine this being on any Group style component we write in Design Systems. Basically brings this from grid into flex layouting.

Shit support atm. https://developer.mozilla.org/en-US/docs/Web/CSS/gap

## CSS Houdini
Low level API for CSS. Mainly made for creating high performant custom CSS properties or something. Shit support ATM so don’t really worry too much just yet. Read https://developer.mozilla.org/en-US/docs/Web/Houdini for more info.

## Typed CSS object model
Think css variables except they can now be objects instead of always a string. Use case they gave - transitioning via css variables instead of css properties. Which is kind of weird to me (not very explicit).


Register CSS property in JS and then assign it a specific syntax


Enables this to work. Previously we would get around this by changing the background property again - but I suppose that isn’t as DRY compared to just changing the CSS variable.


Will eventually have a CSS equivalent of registering the property.

“Great for Design Systems”… well what do you know  . We just need to use CSS variables first. Typed OM has shit support ATM.

Also has coming a new way to get calculated styles.

## Paint API
Neat way of creating custom CSS functions for painting stuff.

Can use it for many things, it’s wild. Like angled buttons! OOF. Of course shit browser support tho. Chrome only basically. https://developer.mozilla.org/en-US/docs/Web/API/PaintWorklet

## Animation worklet
Building on houdini API talked previous (and paint API) - this is another worklet styled api.

Don’t think about using this any time soon.

So cutting edge MDN doesn’t have docs yet. https://developer.mozilla.org/en-US/docs/Web/API/AnimationWorklet


## Speed rounds

### size
Set height/width with one property instead of two… noone supports it? https://developer.mozilla.org/en-US/docs/Web/CSS/@page/size


### aspect-ratio
Official way of keeping an aspect ration instead of relying on padding hacks. Noone supports it https://developer.mozilla.org/en-US/docs/Web/CSS/aspect-ratio


### min() max() clamp()
Set constraints on any CSS property. Chrome and Opera support.

### list-style-type
Instead of doing with with :before psuedo elements now there is an official way to have custom list item icons. https://developer.mozilla.org/en-US/docs/Web/CSS/list-style-type

Has great support - is this actually new?


### display outer inner
Display can take two values now, one for outer one for inner. Outer is what we’re always used to. Inner is.. I suppose to have a flex container as children, grid? Etc.   Inner has bad support atm https://developer.mozilla.org/en-US/docs/Web/CSS/display-inside


### CSS regions
Define specific regions for content to go into. Or something. Not entirely sure about it.


### Native css modules
You can import css inside javascript now. Natively. Wild! “Gets a rich object back”.


### Sub grid
Firefox only. https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Grid_Layout/Subgrid

Enables a grid child to have its children flow in the parent grid.. or something.