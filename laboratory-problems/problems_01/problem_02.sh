#!/bin/bash

# 1. Create a directory and touch files
mkdir results
touch results/OS1.txt results/OS2.txt

# 2. Edit files (requires user interaction)
nano results/OS1.txt
nano results/OS2.txt

# 3. Display contents
cat results/OS1.txt
cat results/OS2.txt
cat results/OS1.txt results/OS2.txt

# 4. Combine contents into total.txt
cat results/OS1.txt results/OS2.txt > total.txt

# 5. Move total.txt to a new directory
mkdir results_2023
mv total.txt results_2023

# 6. Count rows, words, and characters in total.txt
wc results_2023/total.txt

# 7. Display students enrolled in 2023
grep '^23' results_2023/total.txt

# 8. Display index, date, and time for students enrolled in 2021 with status 'submitted'
awk '$1 ~ /^21/ && $5 == "submitted" {print $1, $2, $3}' results_2023/total.txt

# 9. Count students in groupA who submitted results in less than 30 minutes
awk '$4 == "groupA" && $5 == "submitted" && $3 < "30:00" {count++} END {print count}' results/results.txt
