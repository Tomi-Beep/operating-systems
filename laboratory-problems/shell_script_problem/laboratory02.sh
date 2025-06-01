#!/bin/bash

if [ $# -ne 1 ]
then
	echo "Usage: $0 <csv_file_path>"
	echo "Example: $0 sample.csv"
	exit 1
fi

if [ ! -f $1 ]
then
	echo "No such file exists."
	exit 1
fi

if [[ ! $1 =~ \.csv$ ]]
then
	echo "Please use a csv file."
	exit 1
fi

file=$1

echo "Exam Scores Analysis"
echo "-------------------"

awk -F, 'END{print "Total Number of Students:", NR-1, "\n"}' "$file"

echo "Subject Averages:"

awk -F, '{sum+=$3} END {printf "  Math:    %.2f\n", sum/(NR-1)}' "$file"
awk -F, '{sum+=$4} END {printf "  Science: %.2f\n", sum/(NR-1)}' "$file"
awk -F, '{sum+=$5} END {printf "  English: %.2f\n", sum/(NR-1)}' "$file"
awk -F, '{sum+=$6} END {printf "  History: %.2f\n", sum/(NR-1)}' "$file"

echo ""
echo "Subject Extreme Performers:"

awk -F, 'NR>1 {if (max < $3 || NR==2) {max=$3; name1=$2} if (min > $3 || NR==2) {min=$3; name2=$2}} END{printf "  Math - Highest: %s (Score: %d), Lowest: %s (Score: %d)\n", name1, max, name2, min}' "$file"

awk -F, 'NR>1 {if (max < $4 || NR==2) {max=$4; name1=$2} if (min > $4 || NR==2) {min=$4; name2=$2}} END{printf "  Science - Highest: %s (Score: %d), Lowest: %s (Score: %d)\n", name1, max, name2, min}' "$file"

awk -F, 'NR>1 {if (max < $5 || NR==2) {max=$5; name1=$2} if (min > $5 || NR==2) {min=$5; name2=$2}} END{printf "  English - Highest: %s (Score: %d), Lowest: %s (Score: %d)\n", name1, max, name2, min}' "$file"

awk -F, 'NR>1 {if (max < $6 || NR==2) {max=$6; name1=$2} if (min > $6 || NR==2) {min=$6; name2=$2}} END{printf "  History - Highest: %s (Score: %d), Lowest: %s (Score: %d)\n", name1, max, name2, min}' "$file"

exit 0