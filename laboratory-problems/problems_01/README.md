# Laboratory problems in UNIX - Redicrecting, filtering, regular expressions and command procedures/scripts basics
## Problem 1
1. Create a directory with name directory001 and add a file named file123.
2. Write a command to read the manual for the command cat.
3. Change the file permissions for the file named file123 so that it can be read and written into by the user and the group.
4. Do the previous request in another way.
5. Write a command to get the info about the users which are currently logged in on the system.
6. Create a new directory named directory002 and copy file123 into the new directory and change the name of the file to file234
7. Delete directory001 created in this exercise together with its content.

## Problem 2
1. Create a folder with the name results and in it csv files named OS1.txt and OS2.txt.
2. Fill in the files you created with data for the students that took the exam for the course using the format: index date time(minutes:seconds) group status. (get the content of the files from the links below)
3. Show the content for these files on screen, separately and together.
4. Create a file named total.txt that will have the data for all students from both files.
5. Move the file total.txt in a folder that is a child of results and is named results_2023.
6. Count the number of rows, words and signs in the file results.txt.
7. Show on the screen the data for the students that have enrolled the Faculty in 2023.
8. Show on the screen the names of the students, date and time that have enrolled the Faculty in 2021 and have status submitted.
9. Count how many students have submitted the results in less than 30 minutes.

Contents of file OS1.txt - https://courses.finki.ukim.mk/pluginfile.php/269875/question/questiontext/714849/2/982581/OS1_B.txt

Contents of file OS2.txt - https://courses.finki.ukim.mk/pluginfile.php/269875/question/questiontext/714849/2/982581/OS2_B.txt

## Problem 3
Write a command procedure that will receive one input argument representing the name of a file. The procedure should populate the specified file with the contents of all .txt files in the same directory for which the user has read permissions (no write or execute permissions). So that the content between two files will be separated by a new line.

You need to check if an input argument is provided. If not, print Insert name of file!

If more than one input argument is provided, print Too many input arguments!

In all other situations, save the contents of the filtered files in the specified file.

example bash script.sh total.txt
