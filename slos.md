# SLOs

## What is a capability?
A Capability is a customer-facing experience that our customers cares about. We need a list of Capabilities for every Product and Platform in a Capabilities Directory. Each Capability needs an Owner and an Operating Team.

- SLOs and Error Budgets one-pager 

A Roadmap capability is essentially an end-to-end experience, which may include one or more front-end interactions and also back-end requests.

## What are we tracking?
We will define SLOs for each of the core capabilities listed below. We will focus on 3 types of SLOs:

## Availability
In the context of capabilities, availability would measure whether the necessary analytics for a capability was being emitted. This is usually associated with traffic. If a page failed to render, then it would affect the availability of every capability normally available on said page.

## Reliability
Reliability measures whether a capability is behaving as expected, given that it is available. This is associated with success / failure metrics and errors. Capturing logical errors in the front-end that do not produce errors would be impractical, so they will be mostly excluded. The rationale is that these logical errors should be picked up by testing.

## Performance
Performance measures whether a capability completed within an expected time frame. This is associated with the interaction SLO metrics, which is essentially a performance grading. Our performance targets will be influenced by the [RAIL model](https://web.dev/rail/). For page views, we will use TTR, TTI or TTC, [measured from](https://www.w3.org/TR/navigation-timing-2/timestamp-diagram.svg) `unloadEventEnd` of the previous page. For other interactions, we will use start / end timers in the code. This means most performances measurements will incorporate server response time, where applicable.

## Risks
Reliability calculated from interaction success / failure reflects back-end reliability, and does not completely capture the entire experience. It also does not capture all bugs. For example, we might succeed in creating an issue, but in the wrong position / rank.

Performance is impacted by external dependencies like navigation and issue details view. This makes it difficult to isolate Roadmap performance. Our performance targets will be capped.

It’s difficult to thoroughly measure availability in all use cases and conditions, and we may overlook some cases. For example, we may introduce a bug that makes it impossible for users to enable Roadmap if they have a premium license.

It’s difficult to connect multiple interactions to paint a complete picture of a particular user experience.