#!/bin/bash

# Create directory and navigate into it
mkdir directory001
cd directory001

# Create a file
touch file123

# Show the manual for the `cat` command
man cat

# Change file permissions
chmod o=--- file123
chmod g=rw file123
chmod 660 file123

# Display logged-in users
who

# Copy the file to another directory with a new name
cd
mkdir directory001
cp directory001/file123 directory002/file234

# Go back and remove the created directory and its content
rm -r directory001
