
Useful git commands:

#To check the remote repo
git remote -v

#To add the remote repo
git remote add origin https://github.com/shri-110011/Java-Projects.git

#To check the files in the git staging area
git status

#To add the files to the staging area
git add .

#To commit the changes to the local repo
git commit -m "Some message about the code changes"

#To push the code to the remote branch
git push origin <branch_name>

#Note by default "git init" will name the branch in our local repo as "master"
#To change that branch name in our local repo use:
git branch -m <new_branch_name>
or
git branch -m <old_branch_name> <new_branch_name>

-------------------------------------------------------------------

Steps to create a single folder having multiple java projects in Eclipse:

1. Create a folder in your workspace say "Test".
2. Create a java project say "TestProject" in eclipse by choosing the default location which will be the workspace.
3. Move the "TestProject" into the folder "Test".
4. Refresh the Project Explorer.
5. Now go to File >  Import... and select "Projects from Folder or Archive" option.
6. Click on Directory and choose the folder "Test" which is the one that we want to hold multiple java projects.
7. Click on Finish.
And you should now see the folder "Test" containing java projects in Project Explorer.