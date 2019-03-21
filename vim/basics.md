# VIM Basics

`vim` start vim\
`i` Enter Insert mode\
`esc` Exit Insert mode\
`u` Undo

`A` Enter Insert mode at end of the line\
`o` Open new line down the current line in insert mode\
`O` Open new line upper the current line in insert mode\

`$` go to end of the line\
`0` go to beggining to the line

### Motions / Navigating
`h` left\
`j` down\
`k` up\
`l` right\
`w` move forwards to the beginning of next word\
`b` move backwards to beginning of word\
`e` move forwards to the end of next word

`3w` move forwards 3 words\
`f<character>` move forwards to the next occurance of <character>\
`2fo` move forwards to the second occurance 'o' character in the line\
`13j` move cursor 13 lines down\
`13k` move cursor 13 lines up

`16j2w` move cursor 16 lines down and to the beginning of the second word\

`H` o to the first line of the visible screen\
`L` o to the last line of the visible screen\
`M` o to the middle line of the visible screen\
`crtl+f` jump the screen down a visible screen\
`crtl+b` jump the screen up a visible screen\
`gg` go to the top of the buffer (file)

> There is no multicursor in Vim

### Deleting
`x` delete character (function delete key)\
`dw` delete word\
`db` delete backwards to the start of the nearest word\
`ce` delete everything to the end of the word and enter insert mode (ie; "change")\
`D` delete content of the line\
`dd` delete content of the line + newline character\
`dG` delete from the current cursor position to the end of the   buffer (file)\
`dH` delete from the current cursor position to the top of the   visible screen (file)\
`di"` delete everything inside quotes\
`di{` delete everything inside curly brackets\
`da"` delete everything inside quotes, plus delete the quotes themselves\
`da{` delete everything inside curly brackets, plus delete the brackets themselves\
`ci"` delete what is inside the quotes and enter insert mode\
`c2w` delete the next 2 words and enter insert mode\
`ci[` delete what is inside a array and enter insert mode\
`dap` delete around a paragraph\
`d8j` delete 8 lines down\
`:1,$d` delete everything in a file (colon is a range, `1` is the  start of the range, `$` is the end of the range, `d` is the command to operate on that range - "delete")

### Copy and Paste
`yw` yank (copy) word\
`yl` yank one character right\
`p` paste to the right of the cursor\
`P` paste at the cursor position

### Registers
Registers are temporary stores of text. Like a clipboard. There are 26 registers: a-z.\
`"ayw` yank (copy) word into register 'a'\
`"ap` paste the contents of register 'a' at current cursor position

### Marks
`ma` set a _mark_ to the register 'a'\
`'a` jump to line containing mark stored in register 'a'\
`\`a` (backtick + 'a') jump to the character containing mark stored in register 'a'

### Macros
You can record a series of normal mode commands into a register. These are called _macros_.\
`qa` Begins recording to register 'a'\
`q` Stops recording\
`qayepq` start recording to register 'a' (`qa`), yank the current word (`ye`), paste the current word (`p`), stop recording (`q`). Now, the command `yep` is stored in register 'a'.\
`"ap` will paste the contents of register 'a'.\
`@a` will _execute_ the commands stored in register 'a'.\
`@@` will execute the most recently executed register.

### Help
`:help` open the help documentation\
`:help <command>` go to the <command> documentation

### Buffers, Windows, Tabs
By default, vim has a single open tab, containing a single open window, containing a single open buffer (file).\
`:q` close the currently focused window. If it is the last window in the last tab, it will also exit vim.\
`:help` will open a new window in the current tab. (Use `:q` to close that window and move to your previous window)\
`:e <file>` will open a new buffer (file) in the current window\
`ctrl + 6` toggle between the latest 2 opened buffers\
`:buffers` list all the open buffers\
`:b1` open buffer number '1'\
`:sp <file>` will open a new buffer (file) in a new window, in the current tab.\
`:wq` save and close window\
`:q!` close windown without save changes\
`:w <file.txt>` Save changes to the file name\
`vim file.txt` start vim with `file.txt` as the only open buffer

`nvim -u NONE` open vim without any custom settings

### Folds
All folding commands start with a `z`\
`zc` folding close\
`zo` open one fold under the cursor

### Terminal Commands inside Vim
`:` run a vim command\
`:pwd` show the file path

`:!` run a terminal command\
`:!echo "hello world"`\
`:!echo %` `%` is expanded to the current buffer's filename, relative to the current working directory of vim

### Plugins
[fzf](https://github.com/junegunn/fzf.vim)\
[nerdtree](https://github.com/scrooloose/nerdtree)

### Tutorial
`vimtutor` start a tutorial built into Vim (30 min of basics)
