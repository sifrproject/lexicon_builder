# lexicon_builder
* Dictionnary/lexicon creation from a BioPortal ontology repository

il y a deux classes  client-fr pour  recuperer les ontologies du groupe SIFR du bioportal Lirrm  et client-en pour les ontologies du bioportal NCBO  ,les  URL et API_Key sont differentes .




il faut créer des comptes sur les portails pour obtenir des apikey  .


*iterateur utilisé* : 

“classes?page=1&pagesize=100&include_context=false&include_links=false"


 Pour plus  d’informations  : 
[ Voir documentation ](http://data.bioontology.org/documentation)


**les api utilisé :**

- jackson : pour parser les fichier json de L’api REST .
- opencsv : pour lecture/écriture des fichier .csv
- String.Utils pour les traitement  sur les chaînes de caractères .
- log4j : pour la journalisation 
- regex : libraire d’expression régulière pour définir les règle de nettoyage 

**les etapes  :**

1. lancer le programme client  pour recuperer les ontologies chaque ontologie dans un fichier .csv .
2. lancer le programme statistique  .
3. lancer merge   pour fusionner tous les fichiers csv dans un seul Bdico.

le fichier statistiques  contient :
 
le nombre de présence de chaque caractère spécial dans chaque ontologie .


**journalisation** (avec log4j  ) :

permet d'avoir :

le nombre de pages , de termes 

le temps pour récupérer l’ontologie et la stocker dans le fichier csv ansi que le temps total   .

les ontologies vides .
