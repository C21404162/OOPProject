Name: Joe Mulholland
Student Number: C21404162
Date: 07/04/2023

List of Classes;

gui
---
This class contains all the framework for the gui itself. Includes frames, panels, buttons and actionlisteners

searchFileForTerm
---
This class contains all the necessary functionality for the search algorithm. This function is called upon when buttons
are pressed. It contains components to loop through the file and look for any matches. Matches are tracked character by
character in order to display a match percentage (calculated within searchFileForTerm) when results are displayed.
Results are displayed detailing what term was found in which file with what match percentage.

actionPerformed
---
This class is what is responsible for any action after a button is pressed. When search is pressed, user is prompted with
option to search within 3 files specified or in any file on device. Other buttons include the option to cancel search and
clear results from gui.

Control
---
This class creates the 3 files to be searched (if chosen) and writes to each of the 3 files with terms. This class is also
responsible for actually running the interface



Core functions list;

-Functioning search that finds terms in files
-Functioning 'search bar'
-Simple, functional GUI
-Error checking
-Minor spell checking with error message "Term might be mispelt"
-Inform user of which file contains which term

Optional functions list;

-Multiple word search support
-Wildcard search support
-Option to search between files specified or any file on device
-Match percentage calculated
-'Clear results' option
-File Chooser only allows txt files

To be added/Undelivered funtions list;

-Hoped to figure out 'Return strongest match first'
-Make GUI more visually appealing
-Include an 'Easter Egg' or secret in GUI