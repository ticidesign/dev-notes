# Building a Design System

Notes from [Emma Bostian course](https://fem-design-systems.netlify.app/building-a-design-system) 

1. Define your design principles

Design principles are the grounding values which drive the creation of your products. What do you want your users to feel using your product? 

"Bold, optimistic and practical" - Atlassian Design System Principles

2. Conduct a UI audit

Compile all components, in every variation and state, in one place. Group these components by functionality. 

Prioritize the components which have the hights impact on unification of your product and are easly achievable.

Some questions you may want to ask are:

- Does this request embody our design principles?
- Does this requires require a lot of design/development effort?
- Does this request come with a high risk to the success of our product?
- Does this request coincide with our product roadmap?
- Does this request require a lot of maintenance?
- Does this request improve the user experience of our products?
- Are we confident in this request or will it need to be revisited again in the near future?
- Is this request technically feasible?

3. Create your checklist

    **Design Requirements:**

    1. Accessibly

    All users, regardless of circumstance, must be able to accomplish the same tasks within our product. Our designs must account for users with visual impairments and must ensure all facets are consumable.

    2. Interaction

    How should a component respond when a user interacts with it? Is there validation feedback that must occur? The interaction definition must be defined.

    3. Context

    How and where should this component be used? When should I use a link versus a tertiary button, for example?

    4. Completion

    Are all states, including neutral, hover, focus, and disabled, defined?

    5. Content

    What type of content does this component rely upon? Does it accurately embody the brand identity?

    6. Customization

    Are aspects of this component customizable? If so, how? For example, if my design system serves multiple products, the primary button might have a different background color for product A versus product B. We must define these customizable parameters.

    7. Resolution

    How does this component look on varying screen resolutions? How does the layout change?

    **Development Checklist:**

    1. Accessibility

    In addition to an accessible color palette, we must develop our components with semantic HTML elements in order to ensure compliance with assistive technologies. We also must account for keyboard navigation.

    2. Responsiveness

    Our components must respond to browser window resizing and varying screen resolutions.

    3. Completion

    Does this component account for all aspects of the design? Have we implemented a near pixel-perfect component?

    4. Customization

    Have we implemented all of the customizable aspects of this component?

    5. Error Handling / Data Validation

    How do our components respond when something breaks? Have we incorporated type checking with React Prop Types or TypeScript to ensure our parameters comply with expected data types?

    6. Browser Compatibility

    Do the technologies we use work across all supported browsers or must we include polyfills?

4. Define your workflows



