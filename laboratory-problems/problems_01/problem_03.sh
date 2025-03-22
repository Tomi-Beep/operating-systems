#!/bin/bash

# Check if exactly one argument is provided
if [ "$#" -eq 0 ]; then
    echo "Insert name of file!"
    exit 1
elif [ "$#" -gt 1 ]; then
    echo "Too many input arguments!"
    exit 1
fi

# Get the output file name from the argument
output_file=$1

# Gather contents of all .txt files the user has read permission for
# Ensure content from each file is separated by a new line
for file in *.txt; do
    [ -r "$file" ] && [ ! -w "$file" ] && [ ! -x "$file" ] && cat "$file" >> "$output_file" && echo >> "$output_file"
done
