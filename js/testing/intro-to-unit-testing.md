# Cascade Unit Test Introduction

Hi there! Thanks for stopping by, so you're pretty new to the world of unit testing yeah? That's great! It's a great thing to add to your belt and in a great skill to have.

So in this document we want to give you an overview of how to `think about testing` which is a huge part of understanding the `what | why | how` of unit testing. Getting an idea of the way to think about what tests will need to be written is the first step in writing good tests.

Further down we will go through an example of what we want to be looking at when it comes to working out what we need from our unit test but a great plate to look for some insights and further reading is [Kent. C. Dodds Blog](https://kentcdodds.com/blog?q=testing) specifically some of his writing around testing and use of [Testing Library](https://testing-library.com/) which is one of the main tools we use when writing tests on the project.

## Contemplation

OK, so we have the project set up and running. We have been doing some work in it and are looking at adding / improving the unit tests. In the example we will be looking at `Tooltip` on our design system component.

So as a first part of knowing what we want to test for we need to be thinking about what the user sees, and how interactions change what is presented to the user. We are wanting to make sure that when we interact with our component/page we are getting the desired result from that interaction.

So what happens when we spin up `Tooltip` inside of Storybook for example? We can see there are multiple stories that cover different experiences, this in itself is a perfect case for tests we would like to write.

Now one thing to think about before we start, when looking at storybook stories or thinking about tests is that we are looking to test **interaction** rather than **implementation**. So what does that mean? Great question, lets use the stories from `Tooltip` as an example:

### Interaction Vs Implementation

#### Interaction

- **Default Hover** - This is the standard interaction a user would have to experience a tooltip.
- **Disable Event Listener** - If the user interacts with a disabled event listener the tooltip should not show.
- **Custom Event Handler** - Tooltip should be triggered by something custom like a button press, etc.

#### Implementation

- **Light** - Tooltip is still rendering, the styling is what changes.
- **Without Arrow** - Tooltip is still rendering, the styling is what changes.
- **Child Composition / Long Message** - Tooltip is still rendering, the styling is what changes.
- **Delay** - Tooltip is still rendering just after a delay.

So looking at the above list our `Implementations` are essentially variations of the base component where as the `Interactions` are what happens when a user does something on the page. Another way to think about it is what happens if it fails?

*e.g.* Implementation of `light` fails === tooltip still renders just not with the light styling. A problem yes but the component is still 'working' at a base level. However, if the `default / disable` functionality does not work then we have a fatal flaw for that component. If it does not render, or does not respect the `disable` prob then that is a much deeper problem in the component itself.

## Implementation

Ok so now we are looking at writing 3 unit tests to cover the interactions available on `Tooltip`, let's get into that. A good place to start with having a clean layout of our test is to see if there are any changes to the component between tests, like in this case the passing of the disabled prop or the custom event handler.

So in this example with `tooltip` we will make 2 'base' components as there is a little bit of a difference between `default / disabled`, compared to the custom event handler. This is also moved to the top of the file so the test itself is nice and readable.

This will be our base component where we have the implementation option of passing the `disabled` prop.

```typescript
const ToolTipComponent = ({disableHoverListener = false}: {disableHoverListener?: boolean}) => {
  return (
    <Tooltip appearance="dark" content="This is a tooltip" disableHoverListener={disableHoverListener}>
      <button type="button">
        <p>Hover here</p>
      </button>
    </Tooltip>
  );
};
```

Our next component is the custom event handler where again we are building this with that implementation

```typescript
const ToolTipCustomEventComponent = ({disableHoverListener = false}: {disableHoverListener?: boolean}) => {
  const [isOpen, setIsOpen] = React.useState(false);
  return (
    <>
      <Tooltip appearance="dark" content="This is a tooltip" open={isOpen} disableHoverListener={disableHoverListener}>
        <button type="button">
          <p>Hover here</p>
        </button>
      </Tooltip>
      <button onClick={() => setIsOpen(!isOpen)} type="button">
        Click to open tooltip
      </button>
    </>
  );
};
```

***Note:*** There may be a question going back to [[Cascade Unit Test Introduction#Interaction Vs Implementation | Interaction Vs Implementation]] as we discussed above, we said we do not want to test implementation details we want to test interactions, but then our components above are implementations of that. Well below we will go through how that will work.

## Execution

Ok so now we have our notes on what we want to test, we have our implementations we can go about writing our tests, from the note above the components we have built are the `implementation`. However as you will see in the tests bellow we are actually only worried about the result of the `interaction` with that implementation.

```typescript
it('Tooltip should render on hover', async () => {
    render(<ToolTipComponent />);
    fireEvent.mouseOver(screen.getByRole('button'));
    expect(await screen.findByText('This is a tooltip')).toBeInTheDocument();
  });
  
it('Tooltip should not render on hover if disabled', async () => {
    render(<ToolTipComponent disableHoverListener />);
    fireEvent.mouseOver(screen.getByRole('button'));
    expect(screen.queryByText('This is a tooltip')).not.toBeInTheDocument();
  });
```

The above test should make it a little clearer when it comes to the `Interaction vs Implementation` discussion. While our tests are calling those implementation based components, we are only testing that the result remains the same, for example if you added the `light` or `withoutArrow` prop to the `ToolTipComponent` implementation the tests would still pass. If someone came along and refactored to change `light` to be called `lighter` or do something else when that prop was passed, or someone removed `withoutArrow` all together the test would still pass.

```typescript
it('Tooltip should render ONLY on custom button event', async () => {
    render(<ToolTipCustomEventComponent />);
    fireEvent.mouseOver(screen.getByText('Hover here'));
    expect(screen.queryByText('This is a tooltip')).not.toBeInTheDocument();
    fireEvent.click(screen.getByText('Click to open tooltip'));
    expect(await screen.findByText('This is a tooltip')).toBeInTheDocument();
  });
```

This final test around working with a custom event handler adds a little check at the start where we try to hover, this makes sure the `tooltip` is not behaving in the default manner and will only be triggered by the custom event. In this test were covering both parts that

1. By using a custom handler we don't have the default behavior
2. The custom event works in triggering the display of the `tooltip`.

## Reflection

Ok, now that we have gone on that whirlwind adventure of testing our tooltip component lets look back on our adventure, what did we learn?

- Our focus of testing should be around `interaction` with the component **rather** than `implementation` of calling it.
- Splitting out our `implementations` when writing tests can help make the test more readable.
- Tests should cover both the should OR shouldn't change when an interaction happens.

Testing is a huge part of improving your thought process as a developer, and the mental process is one of the biggest parts of starting to make headway in effective unit testing.
