#!/bin/bash

rm commandes.sql

while read line
do
	nom=`echo "$line" | cut -d ";" -f 2 | sed "s/'/\\\\\'/g"`
	prixAchat=`echo "$line" | cut -d ";" -f 3`
	prixVente=`echo "$line" | cut -d ";" -f 4`
	photo=`echo "$line" | cut -d ";" -f 6`
	stock=`echo "$line" | cut -d ";" -f 7`
	max=`echo "$line" | cut -d ";" -f 8`
	seuil=`echo "$line" | cut -d ";" -f 9`
	allergies=`echo "$line" | cut -d ";" -f 10`
	description=`echo "$line" | cut -d ";" -f 11 | sed "s/'/\\\\\'/g"`
	#echo "$description"


	if [[ -z "$nom" ]]
	then
		nom="null"
	fi
	if [[ -z "$prixAchat" ]]
	then
		prixAchat="null"
	fi	
	if [[ -z "$prixVente" ]]
	then
		prixVente="null"
	fi
	if [[ -z "$photo" ]]
	then
		photo="null"
	fi
	if [[ -z "$stock" ]]
	then
		stock="null"
	fi
	if [[ -z "$max" ]]
	then
		max="null"
	fi
	if [[ -z "$seuil" ]]
	then
		seuil="null"
	fi
	if [[ -z "$description" ]]
	then
		description="null"
	fi

	types='null'
	prixAchat=`echo "$prixAchat" | sed 's/,/\./g'`
	prixVente=`echo "$prixVente" | sed 's/,/\./g'`
	echo "INSERT INTO Boissons VALUES ('$nom', $prixAchat, $prixVente, '$description', '$photo', $types, $stock, $seuil, $max);" >> commandes.sql
done < boissons.cvs
