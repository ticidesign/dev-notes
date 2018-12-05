## Bash Script

I've had a few runs at it, but from what I've seen, npm scripts don't support arguments in the middle of the script (e.g. `cd projects/{$1} && bolt start`). This is explained in this Stack Overflow post https://github.com/npm/npm/pull/5518#issuecomment-46915480. The workaround is using something called a command-line parser such as `Minimist, dashdash, nopt, and commander`, but requiring something to be installed to run one little script feels like overkill.

Allow projects to be specified with 'bolt start'
Provide arguments to the bolt start scrip. Example: "cd project/$1 && yarn start"

`bolt w [name] run [script]`
`bolt w @voussoir/cypress-project-basic run start`

Solution:
"start": "/bin/sh -c 'cd projects/${1:-$0} && yarn start' basic",
`${1:-$0}` <- This says “use $1 as the project directory if given, otherwise use $0”. Adding `basic` to the end of the command ensure that `$0` is always `basic` and then `$1` will always be whatever the user types on the command line, e.g. ’bolt start login => `$1 = login`.
We can’t use `${0:-basic}` to get the defaults, because if the user doesn’t supply an argument, rather than `$0` being unset, it gets set to `/bin/sh`, which doesn’t particularly help.
Some light reading: http://wiki.bash-hackers.org/syntax/pe#parameter_expansion
