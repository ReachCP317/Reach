
# Contributing to Reach
:raised_hands::confetti_ball: First off, thanks for taking your time to contribute to ***Reach***! :confetti_ball::raised_hands:

The following is a set of guidelines for contributing to ***Reach*** on GitHub. These are mostly guidelines, not rules. Use your best judgment, and feel free to propose changes to this document in a pull request.

#### Table Of Contents

[Code of Conduct](#code-of-conduct)

[What do I need to do before contributing?](#what-do-i-need-to-do-before-contributing)
   - [Get GitHub Desktop](#get-github-desktop)
   - [Clone The Repository](#clone-the-repository)

[Contributing](#contributing)
   - [Creating a branch](#creating-a-branch)
   - [Making and commiting changes](#making-and-commiting-changes)
   - [Pull Requests](#pull-requests)
   - [Merging](#merging)

[Styleguide](#styleguides)

## Code of Conduct

This project and everyone participating in it is governed by the [Reach Code of Conduct](CODE_OF_CONDUCT.md). By participating, you are expected to uphold this code. Please report unacceptable behavior to [dbrown@wlu.ca](mailto:dbrown@wlu.ca).

## What do I need to do before contributing?
### Get GitHub Desktop
For simplicity, [GitHub Desktop](https://desktop.github.com/) will be used for interacting with Github and the ***Reach*** repository.

**Steps:**
1. Visit the link above and install GitHub Desktop .
2. Launch GitHub Desktop.
3. Browse to *File > options > Accounts* and log into your GitHub account.
4. Congratulations! You now have GitHub Desktop setup.

### Clone The Repository
The next prerequisite is to clone the ***Reach*** repository to your local machine.

**Steps:**
1. In GitHub Desktop browse to *File > Clone repository > GitHub.com* and select the "ReachCP317/Reach" repository.
2. Choose a Local path to clone the repository to and click "Clone".
3. Congratulations! The repository should now be accessible on your computer.

## Contributing
It is crucial to the neatness and efficiency of the ***Reach*** repository that the following instructions are followed to a T.

### Creating a branch
When adding to the repository, a new branch off of the `develop` branch should be created. **Branching** is the way to work on different versions of a repository at one time.

When you create a branch off the `develop` branch, you’re making a copy, or snapshot, of `develop` as it was at that point in time. If someone else made changes to the `develop` branch while you were working on your branch, you could pull in those updates.

When creating a branch, consider what it is you plan on implementing or fixing and name the branch accordingly.
Examples: if you are adding a feature that zooms, a good branch name would be "zoom-feature", if you are fixing a bug that crashes the application, a good branch name would be "crash-bug-fix".

**Steps:**
1. In GitHub Desktop select ***Reach*** as the current repository.
2. Browse to *Branch > New Branch*.
3. Name your new branch as described above.
4. Select `develop` and click "Create branch".
5. In the top right click "Publish branch".
5. Congratulations! You now have a branch you can make changes to!

### Making and commiting changes
Now that you have a local version of the repository you can make changes to it as needed. Once you have made these changes you need to save them to the repository. On GitHub, saving changes is called **Commiting**. Each commit has an associated commit message, which is a description explaining why a particular change was made. Commit messages capture the history of your changes, so other contributors can understand what you’ve done and why. Use the Git Commit [Styleguide](#styleguides) for styling your commits.

**Steps:**
1. After making changes open GitHub Desktop.
2. You should see files appear under the changes tab.
3. Make sure you have the branch you created selected.
4. Add a Summary and an optional Description.
5. Click "Commit to (branch_name_here)".
6. In the top right click "Push origin".
7. Congratulations! Your changes have been pushed to your branch in the repository.

### Pull Requests
Nice edits! Now that you have changes in a branch off of `develop`, you can open a pull request.

**Pull Requests** are the heart of collaboration on GitHub. When you open a pull request, you’re proposing your changes and requesting that someone review and pull in your contribution and merge them into their branch. Pull requests show *diffs*, or differences, of the content from both branches. The changes, additions, and subtractions are shown in green and red. 

As soon as you make a commit, you can open a pull request and start a discussion, even before the code is finished. Use the Pull Request [Styleguide](#styleguides) for styling your Pull Requests.

**Steps:**
1. In GitHub Desktop browse to *Branch > Create pull request*.
2. This will open your browser with a pull request.
3. Make sure You compare your branch with develop.
4. Add a Title and Description.
5. Click "Create pull request".
6. Congratulations! You created a pull request.

### Merging
In this final step, it’s time to bring your changes together – merging your branch into the `develop` branch.
After your code has been reviewed by at least two other members it can then be merged to develop.

**Steps:**
1. Under your pull request click "Merge".
2. Congratulations! You've successfully merged your branch into `develop`.
3. It is now safe to delete your branch, click "Delete".

This concludes the guide on contributing.

## Styleguides
