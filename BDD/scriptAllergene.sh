#!/bin/bash

rm allergenes.sql

while read line
do
	nom=$(echo "$line" | cut -d ";" -f 2 | cut -d ";" -f 1)
	allergenes=$(echo "$line" | cut -d ";" -f 10 | cut -d ";" -f 1)
	arr=$(echo "$allergenes" | tr ";" "\n")

	for var in $arr
	do
		echo "INSERT INTO Allergene VALUES (\"$nom\", \"$var\")" >> allergenes.sql
	done

done < boissons.cvs

echo "$(tail -n +2 allergenes.sql)" > allergenes.sql
