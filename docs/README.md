![Duncan ASCII Art](20250314142043.png)

## Introduction
Duncan is a friendly command-line interface (CLI) chatbot to help you organise your tasks!

## Quick Start
1. Ensure you have Java 17 installed on your computer.
2. Move or copy the `.jar` file from the root of this repository to a desired location on your machine (files will be created there).
3. Run the `.jar` file by running `java -jar duncan.jar` in a terminal at the location you put the `.jar` file in. Note that double-clicking it might produce a Java exception. A greeting message should be printed to the output:
   ![](20250314145046.png)
4. Type a command in the terminal and press enter to execute it. Below is a list of sample commands you may try. Refer to the corresponding feature sections for a more details about each command.
    - `todo read book`: adds a to-do task named "read book"
    - `deadline submit iP /by 14/3/2025 2359`: adds a deadline named "submit iP", for you to complete by 14th March 2025 at 2359 hrs.
    - `list`: lists all tasks in your list.
    - `mark 1`: marks the first task in your list.
    - `bye`: exists the app.
## Features

>  **Notes about the command format**:
> - Uppercased words in angled brackets,  `<UPPER_CASE>` are the parameters to be supplied by the user.
> - e.g. for `todo <TASK NAME>`, `TASK NAME` is a parameter, which can be `read book.`
> - Extraneous parameters for commands that do not take in parameters (such as `list`, `bye`) will be ignored.

### Viewing help: `help`
Shows the URL link to this page.
### Adding a to-do: `todo`
Adds a to-do to the task list. The to-do does not have any time associated with it.
```
todo <TASK NAME>
```
**Examples**
1. `todo read book`
### Adding a deadline: `deadline`
Adds a deadline to the task list. The deadline has **exactly one** time stamp associated with it.
``` 
deadline <TASK NAME> /by <TIME>
```

> **Tip**: Time Formats
> Duncan can understand a few different date formats. Generally, times can be provided as such:
> -   `MM dd yyyy`, `dd MM yyyy`, `dd MM yy`, `MM dd yy`.
>
> A 24 hour time `HHmm` can also be specified after the date if the deadline has a specific time.
> Duncan can also understand relative dates. For example, if the current date is 13/3/2025, and the following command is given to Duncan:
> ```deadline CS2113 quiz /by this friday```, a deadline will be created for 2359 Hrs, for the first Friday that occurs after the current time.

**Examples**
1. `deadline CS2113 quiz /by 14/3/2025 2359`
2. `deadline CS2113 quiz /by this friday`
3. `deadline CS2113 quiz /by tomorrow`
### Adding an Event: `event`
Adds an event to the task list. The event has **exactly two** time stamps associated with it.
```
event <TASK NAME> /from <TIME> /to <TIME>
```
**Examples**
1. `event CS2113 lecture /from 14/3/2025 1600 /to 14/3/2025 1800`
2. `event CS2113 lecture /from this friday 1600 /to this friday 1800`
3. `event CS2113 lecture /from tomorrow 1600 /to tomorrow 1800`

### Deleting Tasks: `delete`
Deletes a specified task from the list by its number.
```
delete <TASK NUMBER>
```
If we had a list as such:\
![](20250314155408.png)\
We could delete the **first** entry by doing `delete 1`:\
![](20250314155737.png)\
**Examples**
1. `delete 1`
### Marking/Unmarking tasks: `mark`/ `unmark`
Mark or unmark a specified task in the list by its number.
```
mark <TASK NUMBER>
unmark <TASK NUMBER>
```
If we had a list as such: \
![](20250314160642.png)\
We could mark the **first** entry by doing `mark 1`:\
![](20250314160828.png)\
We can unmark the task that we just marked:\
![](20250314160838.png)\
**Examples**
1. `mark 1`
2. `unmark 1`

### Listing Tasks: `list`
Lists all tasks and their respective information in your list.
```
list
```

### Finding tasks: `find`
Finds all tasks in your list that match the specific keyword provided.
```
find <KEYWORD>
```
If you had a few tasks:\
![](20250314155408.png)\
And you just wanted all tasks with `cs2113`:\
![](20250314155501.png)\
**Examples**
1. `find cs2113`

### Exiting the program and Saving the list: `bye`
Save and exit the program by saying bye to Duncan!
```
bye
```
Of course, he'll be sad to see you go:\
![](20250314160949.png)

### Editing the data file.
The task data is saved upon exit as a `.txt` file. If you are familiar with the format used to save the tasks, you may edit the file.

>  **Exercise caution when editing the file**
>
> - If your changes to the data file makes it format invalid, Duncan will discard all data and start with an empty file. Hence, it is advisable to backup the data in the files before editing.
> - Certain edits may cause unexpected behaviour from Duncan. 

