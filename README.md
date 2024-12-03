# Programmation Oriente Objet 1 : </br> Rapport du projet
Enseignant : Samuel Peltier

Composition du groupe : Saba Dzigua, Sebastian Lovejoy-Black, Antonin Spychala

# Sommaire 
1. [Description](#description)
2. [Structure des fichiers](#structure-des-fichiers)
3. [Execution](#execution)
4. [UML](#uml)
5. [Classes](#classes)
6. [Divers](#divers)

# Description
## Histoire
Lifeless est un jeu d'aventure textuel se déroulant dans un monde post-apocalyptique. Le jeu commence lorsque le personnage principal se réveille d'un coma dans un hôpital abandonné, découvrant un monde radicalement changé. L'objectif principal est de s'échapper de l'hôpital après avoir appelé à l'aide. Pour réussir, le joueur doit explorer l'hôpital pour trouver un radio, appeler à l'aide, récupérer une arme à feu ainsi que suffisamment de munitions pour survivre, et sauver un chirurgien qui possède un badge de sortie permettant de quitter les lieux. Le gameplay met l'accent sur l'exploration, la prise de décision et la résolution de problèmes, alors que le joueur navigue dans un environnement déserté et inconnu, rassemblant progressivement les éléments nécessaires pour avancer.

## Plan du jeu
<!-- insert map -->

# Structure des fichiers <!-- to be updated -->
```bash 
.
├── game
│   ├── Ammo.java
│   ├── Backpack.java
│   ├── Game.java
│   ├── Hero.java
│   ├── Item.java
│   ├── Key.java
│   ├── Main.java
│   ├── NPC.java
│   └── Room.java
├── README.md
└── utilities
    ├── CommandProcessor.java
    └── Util.java
```
# Execution
<!-- will be known WHEN I GET THE FINISHED PRODUCT-->
# UML
<!-- AGAIN WAITING FOR THE FINISHED PRODUCT -->
# Classes
## Package game
Toutes les classes présentes dans le package "game sont directement liées au jeu lui-même et à ses fonctionnalités.

### Game
La classe Game représente le cœur du jeu. Elle est chargée d'orchestrer les différentes mécaniques principales, comme la navigation entre les salles, l'interaction avec les objets, et l'affichage des informations au joueur. Elle est responsable de l'initialisation du jeu, la gestion de la boucle principale, les interactions avec le joueur, ainsi que la navigation et la positionnement.

#### Attributs
- currentRoom : La salle actuelle où se trouve le joueur.
- hero :  Le personnage du joueur, capable d'interagir avec le monde du jeu.

#### Constructeur 
Le constructeur Game() initialise toutes les salles, les connecte entre elles, et place des objets spécifiques dans certaines salles, et positionne le joueur dans la salle de départ (salle 301).

#### Méthodes
- start() : Lance le jeu
- go() : Déplace le joueur d'une salle à une autre
- help() : Affiche une liste des commandes disponibles
- look() : Affiche les objets présents dans la salle actuelle
- take() : Ramasse un objet dans la salle actuelle et le met dans l'inventaire du joueur
- inventory() : Montre les items que le joueur a récupéré

### Room
La classe Room représente une pièce ou un lieu dans le jeu. Elle gère les connexions avec d'autres pièces (sorties) et les items présents dans la salle.

#### Attributs 
- name : Le nom de la salle 
- description : Une description narrative de la salle 
- exits : Les sorties possibles depuis la salle
- items : Les items disponibles dans la salle

#### Constructeur
Le constructeur Room(String, String) initialise une nouvelle salle avec un nom et une description, et prépare les structures pour les sorties et les objets.

#### Méthodes
- getName() : Retourne le nom de la salle
- getDescription() : Retourne la description de la salle


## Package utilities
# Divers
