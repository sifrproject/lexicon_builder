# lexicon_builder
* Dictionnary/lexicon creation from a BioPortal ontology repository

il y a deux classe main client_fr(pour les ontologies du groupe SIFR récupérées sur bioportal Lirrm ) et client_en ( pour les ontologies du bioportal NCBO ) 
Utilisation 

il faut fournir URL et API_Key (déjà présent sur le code  les deux branch)
ainsi que les dossier ou ecrire  pour les fichier 

** iterateur utilisé : 

“classes?page=1&pagesize=100&include_context=false&include_links=false"


 plus d’information sur : 
[ Voir documentation ](http://data.bioontology.org/documentation)


** les api :

jackson : pour parser les fichier json de L’api REST .
opencsv : pour lecture/écriture des fichier .csv
String.Utils pour les traitement  sur les chaînes de caractères .
log4j : pour la journalisation 
regex : libraire d’expression régulière pour définir les règle de nettoyage 

les etapes  : 

1 -lancer le programme client  .
2- lancer le programme statistique mettre le dossier de chaque  ontologie.. .
3- lancer merge .
statistiques :
le nombre de présence de chaque caractère spécial dans chaque ontologie :
la taille de l’ontologie :utilisation d’une boucle sur les  codes ASCII .

journalisation  (avec log4j  ) :

le temps pour récupérer l’ontologie et la stocker dans le fichier csv ansi que le temps total   .
