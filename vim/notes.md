# VIM

`nvim` New Window
`i` Enter Insert mode
`esc` Exit Insert mode
`u` Undo

`A` Insert mode and enter in the end of the line
`o` Open new line down the current line in insert mode
`O` Open new line upper the current line in insert mode

`$` go to end of the line
`0` go to beggining to the line

### Navigate with letters
`h` left
`j` down
`k` up
`l` right

### Navigate word by word
`w` move forwards in the beginning of next word
`b` move backwards
`e` move forwards to the end of a next word

`3w` move forwards 3 words
`f<character>` move forwards to the word that starts with <character>
`2fo` move forwards to the second 'o' character in the line

> There is no multicursor in Vim

### Deleting

`x` delete character (function delete key)
`dw` delete word
`db` delete backwards
`D` delete content of the line
`dd` delete line and space

### Copy and Paste
`yw` yank / copy word
`yl` copy character
`p` paste to the right of the cursor
`P` paste at the cursor position

### Set markers
`ma` set a marker with a register named 'a'
`"a"` register just created

### Macros
`yep` copy to the end of the line and paste to the right of the cursor
`qayepq` start registering a new marker called 'a', add action 'yep' described on the line above and stop registering.
`@a` use the marker registered before 'a'

### Find more
`:help` open the help documentation
`:help <motion>` go to the <motion> documentation

### Open and Close
`:q` close window
`:wq` save and close window
`:q!` close windown without save changes
`:w <file.txt>` Save changes to the file name
`cat file.txt` see content of the file
`vim file.txt` open file into vim

`nvim -u NONE` open vim without any custom settings
`:e file.txt`

### Moving
`13j` move cursor 13 lines down
`13k` move cursor 13 lines up

`16j2w` move cursor 16 lines down and to the beginning of the second word
`ce` delete everything to the end of the word

### Motion
`z` folding lines
`zc` folding close
`zo` open one fold under the cursor
`H` get to the first line to the visible screen
`L` get to the last line to the visible screen
`M` get to the middle line to the visible screen
`crtl + f` jump the screen down a visible screen
`crtl + b` jump the screen up a visible screen
`:1,$d` delete everything in a file (collon is a range)
`gg` go to the top of the file
`dG` delete from the current cursor position to the end of the file
`dH` delete from the current cursor position to the top of the file
`di"` delete everything inside quotes
`di{` delete everything inside curli bracktes
`da"` delete everything around quotes
`da{` delete everything inside curli bracktes
`ci"` delete what is inside the quotes and enter insert mode
`c2w` delete the next 2 words and enter insert mode
`ci[` delete what is inside a array and enter insert mode
`dap` delete around a paragraph
`d8j` delete 8 lines down

### Terminal Commands inside Vim
`:! yarn format:file %` keystone command to format file
`:pwd` show the file path
`:!echo %`

### Buffers
`ctrl + 6` toggle between the latest 2 openned tabs
`:buffers` list all the open buffers
`:b1` open buffer listed in line 1

### Plugins
[fzf](https://github.com/junegunn/fzf.vim)
[nerdtree](https://github.com/scrooloose/nerdtree)

### Tutorial
`vimtutor` start a tutorial built into Vim (30 min of basics)
