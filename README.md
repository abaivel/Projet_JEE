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
