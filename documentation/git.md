#Git

##Some rules for the workflow

* base your branch on the remote sprint branch

* do not push any changes to the remote master

* share your changes with the team through remote branches

##Pushing a local branch to its own remote

* Run `git push origin branch` to place a copy of your branch on the remote

##Retrieve a remote branch

* First pull down the new branch information from the repository: `git fetch origin`

* You can view all branches plus remote branches with `git branch -a`

* Then create a local branch of the remote branch: `git checkout --track origin/branch`

##Set your local branch to a remote branch

* Make a `git fetch origin` to retrieve latest changes

* Run `git branch -a` and make note of the remote branch you want

* Run a `git log origin/branch` and save the first 5 characters of the hash you want

* While on the branch you want to reset, run `git reset --HARD 01234a` with the hash you saved at the end of the command

##Pulling in Changes

* git checkout branchA

* Prefer changes in other branch over current branch `git merge -X theirs branchB`