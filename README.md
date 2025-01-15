# Projet_JEE

## Auteurs
 - Alexandra Baivel
 - David Mitsakis
 - Camille Bezet
 - Fiona Pavot

## Description

Ce projet consiste à réaliser un jeu 4X, c'est-à-dire dans lequel les actions des joueurs sont résumées par les "4X" (eXploration, eXpansion, eXploitation et eXtermination). Ce projet utilise JEE tant en respectant l'architecture MVC.

## Prérequis
 - Java Development Kit (JDK) : Version 21
 - Apache Tomcat : Version 11
 - Base de données : MySQL
 - Maven : Outil de gestion de build

## Technologies utilisées :
 - Langages : Java, HTML, CSS, Javascript
 - Framework : Jakarta EE
 - Base de données : MySQL

## Installation :
1. Cloner le projet:
git clone https://github.com/abaivel/Projet_JEE.git
cd Projet_JEE

2. Configurer la base de données :  
*Conseil : utiliser MySQL Workbench*  
Modifier le fichier src/main/resources/META-INF/persistance.xml :
 - l'url de la base de données :
```
<property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/bdd_4XGame?useSSL=false&amp;serverTimezone=UTC&amp;createDatabaseIfNotExist=true&amp;allowPublicKeyRetrieval=true"/>
```
Remplacer "localhost:3306" par l'url et le port de votre base de données

 - le nom d'utilisateur :
```
 <property name="jakarta.persistence.jdbc.user" value="root"/>
```
Remplacer "root" par le nom d'utilisateur à utiliser pour se connecter à votre base de données

 - le mot de passe :
```
 <property name="jakarta.persistence.jdbc.password" value="cytech0001"/>
```
Remplacer "cytech0001" par le mot de passe à utiliser pour se connecter à votre base de données

## Fonctionnement

Une fois le jeu lancé, une page de connexion se lance. Une fois connecté, vous avez le choix entre rejoindre la partie en cours ou voir un récapitulatif de vos scores précédents.  
Une fois la partie rejoit, une grille 10x10 s'affiche. Initialement, vous avez un seul soldat. Le jeu se joue au tour à tour. Lors d'un tour, vos soldats peuvent faire une action maximum chacun.Vous devez sélectionner un de vos soldats (entourés de vert) pour pouvoir lui faire faire une action.

Les actions possibles pour un soldat sont :
- se déplacer vers le nord, le sud, l'est ou l'ouest
- Se soigner, pour réinitialiser les points de défense du soldat
- Fourrager une forêt, si le soldat se trouve sur une case contenant une forêt

Combat :
Lorsque votre soldat se dirige vers une ville ou un soldat qui ne vous appartient pas, un combat a lieu. Le combat se déroule avec des attaques provoquant une quantité de dégats aléatoire. Le premier des deux combatants dont les points de défense sont négatifs a perdu. Si votre soldat perd, il meurt et disparait de la grille. En revanche, si votre soldat gagne, ça dépend du type de votre adversaire. Si votre adversaire était une ville, la ville vous appartient et vous rapportera des points de production à chaque tour. Si le combat était contre un soldat, ce soldat meurt et disparait de la grille.

Les actions possibles pour un joueur sont :
- finir le tour
- finir la partie
- recruter un soldat pour 15 points de production

Lorsque vous finissez la partie, un tableau des scores des joueurs s'affiche.

## Demo

https://1drv.ms/v/s!AohqmbHDLtLEmrBxMa4_k9WwWgDO_Q?e=aD3RHE


