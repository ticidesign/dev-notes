# Tips

**git branch ~(END) on terminal?**

Whenever I type in git branch on my Oh-My-Zsh terminal, instead of showing me the branches of my git, it shows something like this:

```js
~
~
~
~
~
(END)
```

Git pipes long output into what's called a `pager` by default, which can make it easier to view the output if it doesn't fit on a screen. The `~` characters indicate that those lines were not in the original output, but it's showing them so that you can scroll down past the bottom (i.e. the last line of output can go up to the top of the screen).

You typically can use the arrow keys to scroll up or down, and can exit by pressing `q`.

You can turn paged output for `git branch` back off by default with the [pager.branch](https://git-scm.com/docs/git-config#git-config-pagerltcmdgt) [config setting](https://git-scm.com/docs/git-config#git-config-pagerltcmdgt):

`git config --global pager.branch false`

For those that want to update their `~/.gitconfig` to fix this, it would look like this:

    [pager]
        branch = false

You can replace all `pager` with `less` so it doesn't "scroll" outputs less than the height of the terminal.

`git config --global --replace-all core.pager "less -F -X"`

[More information here](https://stackoverflow.com/a/14118014/4881742)

------

**How to use Visual Studio Code as Default Editor for Git**

Another useful option is to set EDITOR environment variable. This environment variable is used by many utilities to know what editor to use. Git also uses it if no core.editor is set.

You can set it for current session using:

`export EDITOR=code --wait`

This way not only git, but many other applications will use VS Code as editor.

To make this change permanent, add this to `~/.profile` for example. See [this question](https://unix.stackexchange.com/questions/117467/how-to-permanently-set-environmental-variables) for more options.