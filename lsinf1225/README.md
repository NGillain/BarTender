# README #

Vous pouvez signaler tout bug via https://bitbucket.org/dmercier/lsinf1225/issues

### Ce qui me (Damien) reste à faire ###
0. **FAIT** Adapter le schéma de la base de donnée.
1. **FAIT** Rajouter la possibilité de lire un fichier .sql plutôt que d'hardcoder les requêtes de création des tables. 
2. **FAIT** documenter le code Collector (et ** A FAIRE ** MusicPlayer)
3. Préparer le TP : extraire les fichiers nécessaires + Noter les étapes de conception du TP MusicPlayer
4. Créer un document/manuel d'explication du code de Collectionneur (prendra à mon avis pas mal de temps mais est nécessaire pour que
   les étudiants puissent prendre facilement en main le code et l'adapter à leur application)

* vérifier les noms de variables et les conventions spécifiées au cours
* Re-parcourir et corriger les warnings donnés par l'inspection de code (Android Lint et autres outils)
* (s'il reste du temps) Faire des screens et expliquer la configuration de git dans Android Studio, la gestion des conflits, etc.
    => Pour cette étape, je penses que le plus pertinent est de travailler à 2 dans la même pièce afin de générer des conflits, voir la réaction, etc.


### Nombres d'heures ###

* 02:45 - Mar 15/07 - Réunion + réflexion sur la structure de l'app
* 05:15 - Mer 16/07 - SQLiteHelper, relecture CM, Android studio
* 03:30 - Jeu 17/07 - Réunion + prise en main d'Android Studio et git
* 06:00 - Ven 18/07 - Git, sqlite, show / listview
* 08:00 - Mar 22/07 - Model, ShowView, Login,…
* 09:30 - Mer 23/07 - Main menu, model, …
* 07:00 - Jeu 24/07 - Réunion, design/layout, details,…
* 02:00 - Ven 25/07 - AddActivity, design details,…
* 04:00 - Sam 26/07 - Fonction de tri, documentation,…
* 04:15 - Lun 28/07 - Documentation, renommage, corrections…
* 02:00 - Mar 29/07 - Documentation, correction,...
* 07:15 - Mer 30/07 - Correction d'issues, documentation, notify,…
* 04:00 - Jeu 31/07 - Réunion, documentation, renommage
* 07:15 - Ven 01/08 - Changement du schéma de la base de donnée, adaptation collectedItem, import sql, layout, documentation,…
* 07:15 - Sam 02/08 - Layout, Ré-arrangement du code et documentation complète, … .

** TOTAL :  80 -- QUOTA ATTEINT :/ Issue #13 **

### Options pour la génération de la java doc ###
Pour générer une Javadoc correcte, j'utilise les paramètres suivants : 
`-encoding "UTF-8" -docencoding "iso-8859-1" -tag pre:a:"Pré-condition:" -tag post:a:"Post-condition:" -tag note:a:"Note:"`