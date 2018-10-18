# Projet EPF Spring-Boot ROUGIER ROUSSIN DANTY
=====

Description:

L'application web a pour but la création, l'hébergement et la lecture d'histoires interactives dont vous etes le héros.

La lecture simple:

Moyen d'entrée: Depuis la section accueil de l'application, l'utilisateur séléctionne une histoire parmis celles disponibles. Ne nécessite pas que l'utilisateur soit connecté.

La lecture simple permet aux utilisateurs d'accéder aux histoires et d'interagir avec celles-ci. A mesure de leur lecture, ils sont amenés à faire des choix qui définissent la suite de l'histoire.

La création:

Moyen d'entrée: L'utilisateur séléctionne simplement l'option 'Créer une histoire' depuis la barre de navigation. Ce mode nécessite que l'utilisateur soit connecté.

Un formulaire permet la saisie d'informations comme le titre de l'histoire à créer, le résumé ou encore le contenu du premier chapitre.

L' édition:

Moyen d'entrée: L'utilisateur séléctionne l'option 'Tableau de bord' depuis la barre de navigation. Il peut alors éditer ses histoires. Ce mode nécessite que l'utilisateur soit connecté.

L'édition permet au créateur d'une histoire d'ajouter des chapitres, de les modifier ou de les supprimer. Il peut également décider de supprimer l'histoire. Ces modifications sont sauvegardées dans la base de données. Ce mode permet également au créateur,via la création de chapitres, l'ajout de nouveaux choix.

Ce projet utilise les frameworks Spring-Boot et Hibernate ainsi que le moteur de template Thymeleaf et l'outil de gestion de dépendences Maven.

Processus d'installation:

1. Télécharger le projet depuis github sur votre machine.
2. Importer le projet dans IntelliJ IDEA.
3. Dans XAMPP ou WANPP, démarrer le serveur Web ainsi que le module de gestion de BDD.
4. Créer une base de données SQL.
5. Dans application.properties, indiquer l'identifiant et le mot de passe de la BDD créée. Remplacer dans l'url de la BDD "madbamoi" par    le nom de la BDD créée.
6. Exécuter le projet.
Ouvrir un navigateur internet et tapper l'URL suivante : http://localhost:8080/home

