# Performance Workshop at Atlassian

## Goals and Plans

Learn, Identity and Mitigate

Achieve performance and scale to change perception of Jira Cloud from being a repellant to a propellant

## Foundation of Performance

Agile for Teams - Susana

~~Speed~~ Perpection

Faster apps are better apps (references everywhere)

Measures:

- [RAIL model](https://web.dev/rail/)
- Load Goals (TPC and TTI)
- Interaction Goals (inline response and feedback)

Analyse the JSW next-gen FY20 performance:

- [TTI board and Backlog (eg with SPA and SSR)](https://redash.data.internal.atlassian.com/queries/75202)

The increase number for TTI in SPA transitions might also be due to more features being shipped in next-gen, which result in more items on the nav (e.g. Releases, Issues shipped, Code, Deployment which are both coming soon). In future, when we ship multiple boards per project, probably will have a spike in SPA transitions as well.

From talking to Jira Server last Friday, understanding impact on different kind of cohorts will be important. E.g. the impact might be less for boards for less cards and swimlanes, but it may be huge for boards with >1000 cards and swimlanes.

[Performance AGILE FY21 Dashboard](https://redash.data.internal.atlassian.com/dashboard/performance-agile-fy21)

## Speed vs. Efficiency

HaTS for JSW Cloud (go/hats) - Dimitry KAzakov

Speed and Efficiency is one of the 3 main theme from user feedback, with navigation and findability and representing your work in Jira.

The main complains are:

- "Slow"
- Page loads and lagging responsiveness (e.g. commenting)
- IA and functionality exacerbate clicking required to complete tasks (e.g. bulk change, no auto-updates for linked elements, copying project, auto-refresh, external links, ticket creation, shortcuts)
- Better automation

Page loads vs. excessive clickiness

Speed CSAT ~50% past 2 years:

- Around 50% of Jira clients are happy with 'speed' (page load and lagging responsiveness) in the past 2 year. It drops to 17% when compared to 'speed' (clickiness / efficiency).

Speed is just the 5th item of the list of importance for overwall user satisfaction. After easy to use, features and capabilities,

TLDR: Once we hit the expected TTI of 4 secs/1 secs based on RAIL, it’d be better to optimize for efficiency (too many clicks) as improving further won't see as much impact based on customers’ perceived perf.

## Problem Areas

What our clients are saying? Next-gen performance feedback

- Slow
- Page load
- Reponsiveness

---

## Performance Problem is Large instances

### What is slow beyond TTI & TTR? Beyond 'Jira is slow'

_JANKDEX Metric_

- Excessive/Incorrect clicks + Information layout & Hierarchy => Unclear UI
- Google - Cumulative Layout Shift (CLS)

_Bulk Actions & Settings_

- Lack of bulk operability => Excessive Clicks

_Keyboard Shortcuts_

- Excessive / Incorrect clicks => Learning and relearning UI

_Background Task Pattern_

- Foreground Processing => Lack of Background Processing

_Layout & IA_

- Information layout & Hierarchy

_Loading Killing Collaboration_

- Loading Killling (RT) collaboration (user working concurrently and there is a lot of refreshing)

What was the team doing magnitude / data shape analysis?
Jira Scale and Performance: https://hello.atlassian.net/wiki/spaces/JEM/pages/646775373/What+data+shape+is+representative+of+a+10k+JSW+tenant

https://hello.atlassian.net/wiki/spaces/JCES/pages/578588258/Server+customers+data+shapes

## Jira Performance - Lessons learnt & where they're leading us

Jira Performance - Luke Durkan, Delorean manager and Atlassian Performance Defense

### Lessons learnt

- **Lesson #1**

Performance planning is tough, don't expect it to go as plannned.

It’s very complex for FE as we don’t have control. There are a range of browser, OS, network. Feature delivery impacts performance.
More details modelling / estimates didn’t change our approach.
Validade hypothesis early, don’t start with long term solutions.

- **Lesson #2**

Improvements aren't always additive.
You have to understand how experiences changes interact with each other.

- **Lesson #3**

Where possible, confirm your impact with low-level metrics.
Mesure with metric you know and rearely change.

- **Lesson #4**

Performance never belongs to one team, don't become sole custodians.
Work to make sure performance is part of every team's culture.

## Future

Offence - work to help us improve our performanc
Defence - work that will stop us from regressing our performance

**Focus Areas for Defence**

- Standardised metrics: everyone is speaking the same language, easy to rollout standard tools. (Browser Metrics V3 / BM V3 only uses Gas V3 pipelines)
- Performance portal: a tailored experience to make it eaier to understand performance.
- Pre-production check: stop regression from reaching production.

FMP = TTR (TPC)

**Focus Areas for Offence**

What about the Frontend?

- **Improve Platform Perf:** frontend services (SSR), SPA shell, building improvements
- **New platform Capabilities:** loading phases, SPA prefetching and caching.
- **Dependency Managment:** getting on the release train, reducing duplicate components in FE
- **Improving TTFB** ([time to frist byte](https://web.dev/time-to-first-byte/)): can we start downloading content sooner?

What about the Backend?

- **Decomposition:** new services that optimise for read views of experiences at scale.
- **Improve DB usage:** optimise DB usage in code, caching of common queries.
- **Consist GraphQL Schema:** optimised schema for performance when acessing issue and field data.

Slack channels: #jira-cloud-perf | #jira-spa | #jira-frontend-perf

---

# Overview of the Jira web app load

"Correctly prioritizing resource download and execution and reducing browser downtime during the page load is one of the main levers for improving web application performance." - Glenn Conner

[Making instagram.com Faster](https://instagram-engineering.com/making-instagram-com-faster-part-1-62cc0c327538)

[Resource Prioritization – Getting the Browser to Help You](https://developers.google.com/web/fundamentals/performance/resource-prioritization)

[Chrome DevTools workshop learnings](https://hello.atlassian.net/wiki/spaces/JST/pages/536684202/Chrome+DevTools+workshop+learnings)

[Chrome Dev Tools Workshop Aug 19 2019, Sam Richard](https://www.youtube.com/watch?v=OcEgziTeKfA)

Biggest 1% board 512 issues, backlog 902
[More backlog](https://redash.data.internal.atlassian.com/queries/77769/source#144916)

# Supported by measurement and data

# Totoro strategy to solving a performance problem.

[Recommended approach for Improving TTI in the Backlog](https://hello.atlassian.net/wiki/spaces/agile/pages/745689674/Recommended+approach+for+Improving+TTI+in+the+Backlog)

[Proposed Solutions](https://hello.atlassian.net/wiki/spaces/agile/pages/745736266/Proposed+Solutions)

Other competitors:

- Github Board (integrated with DevOps)
- ServiceNow
- Azure DevOps
- Trello (internal competitor)
- Gitlab

Findings:

- They are using virtualisation
- Ranking using a popup with a form
- Board uses pagination with a ‘show more items’
-
- D&D isn't a very good solution for ranking a project with lots of items.

# Performance tools

[Performance Tools](https://hello.atlassian.net/wiki/spaces/~782353553/pages/798834898/Performance+tools)

- AWS CodeGuru
- Splunk

# Recap

[Understanding how work/issues are distributed - Board & Backlog views](https://hello.atlassian.net/wiki/spaces/agile/pages/808009605/Understanding%2Bhow%2Bwork%2Bissues%2Bare%2Bdistributed%2B-%2BBoard%2BBacklog%2Bviews)

Interesting case of communication about performance wins in Twitter web app (they recently released modern bundles) https://twitter.com/CharlieCroom/status/1291478104016289799
There are follow up tweets how they make it possible - babel config, browser recognition etc - wondering if sharing it that way is "better" than tech blogpost or if would be even better when both

Recap board for end of day: https://templates.atlassian.net/jira/software/projects/TPWR/boards/59
