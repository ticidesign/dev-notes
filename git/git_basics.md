# Basis

Notes from the video: Introduction to Git with Scott Chacon of GitHub

[Video here](https://www.youtube.com/watch?v=ZDR433b0HJY)

## What is Git?

Git is an open source, distributed version control system designed for speed and efficiency

(almost) everything is local wich means everything is fast, every clone is a backup and you can work offline.

No network needed to:
- Performing a diff
- Viewing file history
- Commiting changes
- Merging branches
- Obtaining other revisions of a file
- Switching branches

(almost) never removes data wich means you will never re-write history. Git will move its points to a new commit.

Snapshots, not Patches

## Git Commands

```sh
git clone

git init
git status / git diff
git add .
git add -p (patch commits)
git commit - m 'initial message'
git log

git chekcout master (change branch to master)
git branch
git checkout -b email master (checkout master, create email branch and checkout email branch)
git commit -a 'message' (stage and commit)

git checkout master
git merge email (merge email to master)
git branch -D branch (delete branch)
```

*If you are working in a branch, keep merging master to the featured branch every single day.*

```sh
git merge --ff-only email ?
```
### Global Config
```sh
git config --global user.name "Your Name"
git config --global user.email "youremail@email.com"
git config --global color.ui true
```

### Determine the URL that a local Git repository was originally cloned from
```sh
git config --get remote.origin.url
```

# Remote Branch

```sh
git remote -v
git remote add origin <url>
git remote set-url origin <url>
```

## Branches

Branch is just a pointer in the git DB
HEAD is your current branch, parent of your last commit,

To see all the heads:
```sh
find git/.refs
```

## Merging
Merge modify the branch

Merge conflicts tool:

```sh
git mergetool
```
```sh
git merge --nocommit (merge but it does not commit)
```

### Why branching is cool?
- Try out an idea, a new feature
- Isolate work units
- Long running topic

## Remotes

### Push
```sh
git push origin master
```

### Fetch
fetch gets latest master from origin
```sh
git fetch
```

### Pull
pull == fetch + merge
```sh
git pull
```

### Log
The history of the commits

```sh
git log
```
Formatting the result

```sh
git log --oneline
git log --oneline --graph --all --decorate
```

### Create alias

```sh
git config --global alias.lol "log --oneline --graph --all --decorate"
```

## Squash all commits into one
```sh
git config --global alias.gisquash 'git reset --soft HEAD~$(git rev-list --count HEAD ^master)'
```

More [here](https://www.youtube.com/watch?v=ZDR433b0HJY)
