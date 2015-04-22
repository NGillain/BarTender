#!/bin/bash

rm commandes.sql

while read line
do
	types=`echo "$line" | cut -d ";" -f 1`
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
		prixAchat="4"
	fi	
	if [[ -z "$prixVente" ]]
	then
		prixVente="4"
	fi
	if [[ -z "$photo" ]]
	then
		photo="null"
	else
		photo="'$photo'"
	fi
	if [[ -z "$stock" ]]
	then
		stock="4"
	fi
	if [[ -z "$max" ]]
	then
		max="4"
	fi
	if [[ -z "$seuil" ]]
	then
		seuil="4"
	fi
	if [[ -z "$description" ]]
	then
		description="null"
	else
		description="'$description'"
	fi
	if [[ -z "$types" ]]
	then
		types="null"
	fi

	prixAchat=`echo "$prixAchat" | sed 's/,/\./g'`
	prixVente=`echo "$prixVente" | sed 's/,/\./g'`
	echo "INSERT INTO Boisson VALUES ('$nom', $prixAchat, $prixVente, $description, $photo, '$types', $stock, $seuil, $max);" >> commandes.sql
done < boissons.cvs
