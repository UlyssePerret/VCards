/*
Crée le 15 janvier 2020
Mise a jour : 23janvier
version actuel : 1.0
Auteur : Amadou Ly

0.3 : creation
0.4 : structure du code + ajout de commentaire pour return/param
1.0: Versionning et Mise en commun (package)
V1.4.1.U -4 mars 2020
Commentaire + correction javadoc
mise en héritage (lien Vcards et Calendrier)
Travaille pour le menu marche
Architecture
Commentaire
V.1.4.1.A Mercredi 4 mars - Amadou
Menu Agenda
fonction LireCalend
V.1.4.2
Remise a jour des méthode V.card (copie)
V.1.4.3 - Mise en commun avec Amadou
V.1.4.4
Commentaires
Correction ModifCard -> pays modifier (enlever doublons ville)

V.2
Version final pour rendu
Generation du Javadoc final (check verif)

V.2.1
Integration des méthodes Creations Calendrier et Gestion de fichier
Regeneration du Javadocer
 */
import java.io.*;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Calendrier {
//-----------------------------//
   //---Variables Vcard - Parametres----//
   //-----------------------------//
   /**
    * ADR
    * Adresse de la personne
    */
   public String ADR = "";
   /**
    * ADRDOM
    * Adresse du domicile la personne
    */
   public String ADRDOM = "";
   /**
    * ADRTRA
    * Adresse du travaille la personne
    */
   public String ADRTRA = "";
   /**
    * EMAIL
    * email de la personne
    */
   public String Email;
   /**
    * FN
    * nom de famille la personne
    */
   public String FN;
   /**
    * Label
    * L'adresse de livraison de la personne
    */
   public String Label;
   /**
    * N
    * prenom de la personne
    */
   public String N;
   /**
    * Nickname
    * surnome de la personne
    */
   public String Nickname;
   /**
    * PHOTO
    * la photo de profile
    */
   public String Photo;
   /**
    * REV
    * date de mise à jour de la carte
    */
   public Date Rev;
   /**
    * Role
    * la fonction dans l'organisation ou la profesion de la personne
    */
   public String Role;
   /**
    * TEL
    * téléphone de la personne
    */
   public String Tel;
   /**
    * Title
    * titre du poste
    */
   public String Title;
   /**
    * URL
    * lien de mise a jour de la carte
    */
   public String URL;
   /**
    * Version
    * la version de la carte
    */
   protected String Version;
   /**
    * Note
    * commentaire lié à la carte
    */
   protected String Note;
   /**
    * SortString
    * Permet de trier la carte par nom
    */
   protected String SortString;
   /**
    * Key
    * clé de chiffrement de la clé
    */
   protected String Key;
   /**
    * UID
    * ID de la carte
    */
   protected int UID;
   //Parametre crée pour les méthode
   /**
    * NomCarte
    * le nom de la carte
    */
   public String NomCarte;
   //-----------------------------//
   //---Variables Calendrier- Parametres----//
   //-----------------------------//
   /** Action
    * Faire l’action voulue (ex : Audio: lit la carte) */
   private String Action;
   /** Dtstart
    * Date de début */
   public Date Dtstart;
   /**  Categories
    * Catégories de l’événement (réunion de travail, meeting...) */
   public String Categories;
   /** Description
    * Description de l’événement */
   public String Description;
   /** Dtstamp
    * Clé de chiffrement calendrier  */
   private int Dtstamp;
   /**  Location
    * Lieu de l’événement  */
   public String Location;
   /** Prodid
    * ID du calendrier  */
   private int Prodid = 0;
   /**  Sequence
    * Mise à jour du calendrier  */
   private String Sequence;
   /** Status
    * Statut de l’événement (en cours, fini ...)  */
   public String Status;
   /** Summary
    * Titre événement  */
   public String Summary;
   /**  Transp
    * Vérifié la disponibilité de la personne */
   public String Transp;
   /** Dtend;
    * Fin de l’événement  */
   public String Dtend;
   //--Constructeur---//
   //Rappel : un constructeur est le programme qui se lance en premier quand on l'appelle (Dans une interface ou dans le main)
   // ici le constructeur Montre le lancement du programme et lance un menu d'interface
   /** Calendrier
    * Constructeur pour le main, lance un menu
    * @throws IOException utilisation dans le main :
    *                     Calendrier cal = new Calendrier();  */
   public Calendrier() throws IOException {
      System.out.println("Projet Agenda et Vcard");
      Menu();
   }
   //Menu
   //INTERFACE UTILISATEUR -> Menu
   /** Menu
    * description: Affiche un menu, avec le choix des fonctions déja faite
    *
    * @throws IOException Utilisation: Cette fonction est la base du programme, il est donc dans le constructeur Vcard
    *                     System.out.println("Lancement du programme");
    *                     Menu() */
   public void Menu() throws IOException {
      boolean recommencer = true; //permet de recommencer
      boolean retourmenu = true; //Retour au menu
      boolean quitter = false; //pour quitter
      boolean premier = true; //Savoir si c'est la premier fois
      boolean fonction = false; //savoir si c'est une fonction
      do {
         if (retourmenu && premier) {
            retourmenu = false;
            premier = false;
            fonction = false;
            System.out.println("Bienvenu sur notre programme de gestion de carte de l'agenda");
            //Affichage de démarage du menu
            System.out.println("Que voulez vous faire?");
            System.out.println("1.Travaille sur les Vcards");
            System.out.println("2.Travaille sur l'Agenda");
            System.out.println("3.Quitter L'appli");
            //Demande que faire
            Scanner scmenup = new Scanner(System.in);
            System.out.println("Quel est votre Choix? 1.Vcard 2.Agenda 3.Quitter "); //Message recap pour les choix possible
            char menup = scmenup.nextLine().charAt(0);//pour recuperer le caractére saisi par l'utilisateur.
            //On fait un switch étant donné le nombre de choix possible
            //remarque : le menu actuel est que pour les carte !
            switch (menup) {
               case '1': //Vcard
                  //Menu carte
                  char menuc = MenuCarte();
                  switch (menuc) {
                     case '1': //Lire une carte
                        LireCard();
                        fonction = true;
                        break;//on sort du switch
                     case '2': //Créer une carte
                        CreerCard();
                        fonction = true;
                        break;//on sort du switch
                     case '3': //Modification d'une carte
                        ModifCard();
                        fonction = true;
                        break;//on sort du switch
                     case '4': //Gestion Fichier
                        GestionFichier();
                        fonction = true;
                        break;//on sort du switch
                     case '5': //Retour
                        retourmenu = true;
                        break;//on sort du switch
                     case '6': //Quitter
                        quitter = true;
                        break;//on sort du switch
                     default: //Cas ou on tape un mauvais caractére
                        System.out.println("Erreur! Fonction non trouvé!");
                        break;//on sort du switch
                  }
                  break;//on sort du switch
               case '2': //Agenda
                  System.out.println("1.Lire calendrier");
                  LireCalend();
                  System.out.println("2.Créer créer calendrier");
                  System.out.println("3.Modifier calendrier");
                  System.out.println("4.Gestion calendrier");
                  System.out.println("5.Retour");
                  System.out.println("6.Quitter L'appli");
                  //Demande que faire
                  Scanner scmenua = new Scanner(System.in);
                  System.out.println("Quel est votre Choix? 1.Lecture 2.Creation 3.Modifier 4.Gestion 5.Retour 6.Quitter "); //Message recap pour les choix possible
                  char menua = scmenua.nextLine().charAt(0);//pour recuperer le caractére saisi par l'utilisateur.
                  switch (menua) {
                     case '1': //Lire un calendrier
                        LireCalend();
                        fonction = true;
                        break;//on sort du switch
                     case '2': //Créer une carte
                        CreerCalend();
                        fonction = true;
                        break;//on sort du switch
                     case '3': //Modification d'une carte
                        ModifCalend();
                        fonction = true;
                        break;//on sort du switch
                     case '4': //Gestion Fichier
                        GestionFichier();
                        fonction = true;
                        break;//on sort du switch
                     case '5': //Retour
                        retourmenu = true;
                        break;//on sort du switch
                     case '6': //Quitter
                        quitter = true;
                        break;//on sort du switch
                     default: //Cas ou on tape un mauvais caractére
                        System.out.println("Erreur! Fonction non trouvé!");
                        break;//on sort du switch
                  }
                  break;//on sort du switch
               case '3': //Quitter l'application proprement
                  System.out.println("Fin de l'appli");
                  break;//on sort du switch
               default: //Cas ou on tape un mauvais caractére
                  System.out.println("Erreur! Fonction non trouvé!");
                  break;//on sort du switch
            }
         } else if (retourmenu) {
            retourmenu = false;
            System.out.println("Retour au menu");
            //Affichage de démarage du menu
            System.out.println("Que voulez vous faire?");
            System.out.println("1.Travaille sur les Vcards");
            System.out.println("2.Travaille sur l'Agenda");
            System.out.println("3.Retour Menu Principal");
            Scanner scmenup = new Scanner(System.in);
            System.out.println("Quel est votre Choix? 1.Vcard 2.Agenda 3.Quitter "); //Message recap pour les choix possible
            char menup = scmenup.nextLine().charAt(0);//pour recuperer le caractére saisi par l'utilisateur.
            //On fait un switch étant donné le nombre de choix possible
            //remarque : le menu actuel est que pour les carte !
            switch (menup) {
               case '1': //Vcard
                  //Menu carte
                  char menuc = MenuCarte();
                  switch (menuc) {
                     case '1': //Lire une carte
                        LireCard();
                        break;//on sort du switch
                     case '2': //Créer une carte
                        CreerCard();
                        break;//on sort du switch
                     case '3': //Modification d'une carte
                        ModifCard();
                        break;//on sort du switch
                     case '4': //Gestion Fichier - Vcard
                        GestionFichier();
                        break;//on sort du switch
                     case '5': //Retour
                        retourmenu = true;
                        recommencer = true;
                        break;//on sort du switch
                     case '6': //Quitter
                        retourmenu = false;
                        recommencer = false;
                        break;//on sort du switch
                     default: //Cas ou on tape un mauvais caractére
                        System.out.println("Erreur! Fonction non trouvé!");
                        break;//on sort du switch
                  }
                  break;//on sort du switch
               case '2': //Agenda
                  System.out.println("1.Lire calendrier");
                  LireCalend();
                  System.out.println("2.Créer créer calendrier");
                  System.out.println("3.Modifier calendrier");
                  System.out.println("4.Gestion calendrier");
                  System.out.println("5.Retour");
                  System.out.println("6.Quitter L'appli");
                  //Demande que faire
                  Scanner scmenua = new Scanner(System.in);
                  System.out.println("Quel est votre Choix? 1.Lecture 2.Creation 3.Modifier 4.Gestion 5.Retour 6.Quitter "); //Message recap pour les choix possible
                  char menua = scmenua.nextLine().charAt(0);//pour recuperer le caractére saisi par l'utilisateur.
                  switch (menua) {
                     case '1': //Lire un calendrier
                        LireCalend();
                        fonction = true;
                        break;//on sort du switch
                     case '2': //Créer une carte
                        CreerCalend();
                        fonction = true;
                        break;//on sort du switch
                     case '3': //Modification d'une carte
                        ModifCalend();
                        fonction = true;
                        break;//on sort du switch
                     case '4': //Gestion Fichier - Calend
                        GestionFichier();
                        fonction = true;
                        break;//on sort du switch
                     case '5': //Retour
                        retourmenu = true;
                        break;//on sort du switch
                     case '6': //Quitter
                        quitter = true;
                        break;//on sort du switch
                     default: //Cas ou on tape un mauvais caractére
                        System.out.println("Erreur! Fonction non trouvé!");
                        break;//on sort du switch
                  }
                  break;//on sort du switch
               case '3': //Quitter l'application proprement
                  System.out.println("Fin de l'appli");
                  break;//on sort du switch

               default: //Cas ou on tape un mauvais caractére
                  System.out.println("Erreur! Fonction non trouvé!");
                  break;//on sort du switch
            }
         } else if (retourmenu == false) //si ou
         {
            //Menu carte
            char menuc = MenuCarte();
            //On fait un switch étant donné le nombre de choix possible
            //remarque : le menu actuel est que pour les carte !
            switch (menuc) {
               case '1': //Lire une carte
                  LireCard();
                  fonction = true;
                  break;//on sort du switch
               case '2': //Créer une carte
                  CreerCard();
                  fonction = true;
                  break;//on sort du switch
               case '3': //Modification d'une carte
                  ModifCard();
                  fonction = true;
                  break;//on sort du switch
               case '4': //Gestion Fichier - Vcard
                  GestionFichier();
                  fonction = true;
                  break;//on sort du switch
               case '5': //Retour
                  retourmenu = true;
                  break;//on sort du switch
               case '6': //Quitter
                  quitter = true;
                  break;//on sort du switch
               default: //Cas ou on tape un mauvais caractére
                  System.out.println("Erreur! Fonction non trouvé!");
                  break;//on sort du switch
            }
         }
         //Fin
         if (fonction) //si on a fait une fonction auparavant
         {
            Scanner scmenur = new Scanner(System.in);
            System.out.println("Voulez-vous recommencer ? O.Oui N.Non"); //Message recap pour les choix possible
            char recommencermenu = scmenur.nextLine().charAt(0);//pour recuperer le caractére saisi par l'utilisateur.
            if (recommencermenu == 'O') //Demande de recommencer le menu
            {
               System.out.println("On recommence et retourne au menu principale");
               recommencer = true;
               retourmenu = true;
            } else if (recommencermenu == 'N') //Demande de ne pas recommencer le menu
            {
               System.out.println("Arret du programme");
               recommencer = false;
            } else { //Cas Erreur saisi
               System.out.println("Erreur System!");
               System.out.println("Le programme va s'arreter !");
               recommencer = false;
            }
         } else if (retourmenu) {
            recommencer = true;
         } else if (quitter) {
            recommencer = false;
         } else {
            System.out.println("Erreur System!");
            System.out.println("Le programme va s'arreter !");
            recommencer = false;
         }
      } while (recommencer);

   }
   //Méthode Agenda

   /**LireCalend
    * lire calendrier ; cela n'affiche rien, juste permet de lire le fichier)
    * @throws   IOException : exception pour le fichier
    */
   public void LireCalend() throws IOException{
      System.out.println("Lecture du calendrier");
      System.out.println("Debut");
      // Ecrit ton code
      String chemin ="./icalendar"; //on selection le chemin qu'on doit travaille
      System.out.println("Affichage du calendrier dans " + chemin);
      ListerFichiers(chemin);
      Scanner Scanfichiers = new Scanner(System.in); // Demnde de lire le fichier
      System.out.println ("Quel Fichier voulez-vous lire?");// Message: demande du fichier qu'on ve lire
      String fichier = Scanfichiers.nextLine();//lecture de la variable
      chemin ="./icalendar/" + fichier;
      LireFichier(chemin); // fonction pour lire le fichier
      System.out.println("Fin");
   }
   /**AffCalend
    * afficher calendrier dans la console*/
   public void AffCalend() {
   }
   /** CreerCalend
    * Creer un fichier Calendrier*/
   private void CreerCalend() {
      // System.out.print("Ici Fonction Creation");

      //Etape 1 :  Creation d'une Calendrier

      // Etape 1.1 : on entre le nom de la nouvelle calendrier
      Scanner sc = new Scanner(System.in); //Pour lire le nom de la carte
      System.out.println("Veuillez saisir un le nom de la nouvelle carte:"); //message pour demander le nom de la carte
      //remarque : par défault on considére que l'utilisteur ne rentre pas .vcf
      String str = sc.nextLine(); //lecture de la variable
      //Vérification qu'on a bien rentrer une valeur pour le nom de la carte
      boolean regle2 =CheckChaineVide(str); //on vérifie qu'on a bien rentrer un nom
      //Voir les Méthodes de Vérifications
      while(regle2)//tant que la régle est vraie (donc qu'on est une chaine vide ) alors on fait la boucle
      {
         sc = new Scanner(System.in); //Pour lire le nom de la carte
         System.out.println("Veuillez saisir un le nom de la nouvelle carte:"); //message pour demander le nom de la carte
         str = sc.nextLine(); //lecture de la variable

      }
      //On transforme le nom de la carte pour quel soit dans le bon format.
      String nomfichier = str+".ics"; // pour mettre l'extension au nom du fichier
      //remarque: on suppose que l'utilisateur oublie le .vcf pour le nom de la carte
      String chemin="icalendar/"+ nomfichier; // pour aller au chemin relatif
      System.out.println("La nouvelle carte se situe en :"+ chemin); //test si etape ok - affiche le chemin relatif

      //Etape 2 : On écrit les information dessus
      try {
         File ff = new File(chemin); // on initialise pour savoir ou on va creer la carte
         ff.createNewFile(); // creer le fichier vierge
         FileWriter ffw = new FileWriter(ff); // Initialise l'écriture sur le fichier
         //Ligne 1  : Begin
         ffw.write("BEGIN:VCALENDAR");  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 2 : Version
         ffw.write("VERSION:2.0");  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 3 : PRODID
         ffw.write("PRODID:-//hacksw/handcal//NONSGML v1.0//EN");  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne4: begin vEVent
         ffw.write("BEGIN:VEVENT");  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne5 : Date entrée
         Scanner sc8  = new Scanner(System.in); //demande de lire
         System.out.println("Merci d'indiquer l'année du début de l'évenement"); //message pour demander l'année de l'évenement
         String anneeStart =  sc8.nextLine(); //lecture de la variable - l'année de l'évenement
         sc8  = new Scanner(System.in); //demande de lire
         System.out.println("Merci d'indiquer le mois en format chiffre du début"); //message pour demander le mois de l'évenement
         String moisStart = sc8.nextLine(); //lecture de la variable - mois de l'évenement
         sc8  = new Scanner(System.in); //demande de lire
         System.out.println("Merci d'indiquer le jour du début "); //message pour demander le jour de l'évenement
         String jourStart = sc8.nextLine(); //lecture de la variable - jour de l'évenement
         System.out.println("Nous allons demander l'heure du début. ");
         System.out.println(" Merci d'écrire d'abord l'heure complet (13) puis les minutes avec deux chiffres (00)");
         Scanner  sc9  = new Scanner(System.in); //demande de lire
         System.out.println("1.Indiquer l'heure"); //message pour demander l'heure
         String heureStart = sc9.nextLine(); //lecture de la variable - heure de l'évenement
         sc9  = new Scanner(System.in); //demande de lire
         System.out.println("2.Indiquer les minutes"); //message pour demander l''année de l'évenement
         String minuteStart = sc9.nextLine(); //lecture de la variable - minute de l'évenement
         ffw.write("DTSTART:"+anneeStart+moisStart+jourStart+"T"+heureStart+minuteStart+"00Z");  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne6 : Date fin
         Scanner sc10  = new Scanner(System.in); //demande de lire
         System.out.println("Merci d'indiquer l'année de fin de l'évenement "); //message pour demander l'année de l'évenement
         String anneeEnd =  sc10.nextLine(); //lecture de la variable - l'année de l'évenement
         sc10  = new Scanner(System.in); //demande de lire
         System.out.println("Merci d'indiquer le mois en format chiffre  de fin"); //message pour demander le mois de l'évenement
         String moisEnd = sc10.nextLine(); //lecture de la variable - mois de l'évenement
         sc10  = new Scanner(System.in); //demande de lire
         System.out.println("Merci d'indiquer le jour  de fin"); //message pour demander le jour de l'évenement
         String jourEnd= sc10.nextLine(); //lecture de la variable - jour de l'évenement

         System.out.println("Nous allons demander l'heure. Merci d'écrire d'abord l'heure complet (13) puis les minutes avec deux chiffres (00)");
         Scanner  sc11  = new Scanner(System.in); //demande de lire
         System.out.println("1.Indiquer l'heure"); //message pour demander l'heure
         String heureEnd = sc11.nextLine(); //lecture de la variable - heure de l'évenement
         sc11  = new Scanner(System.in); //demande de lire
         System.out.println("2.Indiquer les minutes"); //message pour demander l''année de l'évenement
         String minuteEnd = sc11.nextLine(); //lecture de la variable - minute de l'évenement
         ffw.write("DTEND:"+anneeEnd+moisEnd+jourEnd+"T"+heureEnd+minuteEnd+"00Z");  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 7 : Summary
         Scanner sc7  = new Scanner(System.in); //demande de lire
         System.out.println("Donner un titre à l'évenement"); //message pour demander le titre de l'événement
         String summary =  sc7.nextLine(); //lecture de la variable - email
         //Vérification Summary
         regle2 =CheckChaineVide(summary); //on vérifie qu'on une valeur pour le codepostale -liv
         while(regle2 ){
            sc7  = new Scanner(System.in); //demande de lire
            System.out.println("Donner un titre à l'évenement"); //message pour demander l'email
            summary =  sc7.nextLine(); //lecture de la variable - email
            regle2 =CheckChaineVide(summary);
         }
         ffw.write("SUMMARY:"+summary);  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne8: begin vEVent
         ffw.write("END:VEVENT");  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 9: END
         ffw.write("END:VCALENDAR");  // écrire une ligne dans le fichier resultat.txt
         //FIN
         ffw.close(); // fermer le fichier à la fin des traitements
      }catch (IOException e) {
         e.printStackTrace();
      }


      System.out.print("Fin Creation");
   }
   /** ModifCalend
    * Modifier un fichier Calendrier*/
   private void ModifCalend() {
      System.out.print("Ici Fonction Modif");
   }

   // Les méthodes propre
   /** CreerEvent
    * créer un événement et le fichier associer*/
   public void CreerEvent() {
   }
   /**DelPer
    * supprimer une personne
    necessaire: information de la personne
    supprime un fichier Vcard de la personne concerné*/
   public void DelPer() {
   }
   /** ExportEvent
    Exporter un événement, envoie par mail l'evenement aux personnes
    parametre future : l'email de la personne qu'on va envoyer l'evenement (demande dans la fonction)
    retourne : return  export par mail l'evenement aux personnes mis en parametre */
   public void ExportEvent() {
      // String email;
   }
   /** Invit()
    Inviter personne
     param : nom/ email de la personne
     return : la personne inviter*/
   public void Invit() {
   }
   /** ModifEvent()
    Modifier événement
     return : la modification faite */
   public void ModifEvent() {
   }

   /**MenuCarte
    * Menu pour la carte
    * @return menuc- pour savoir le choix pour les cartes
    @throws   IOException : exception pour le fichier
    */
   private char MenuCarte() throws IOException {
      System.out.println("Menu des cartes");
      //Affichage du Menu
      System.out.println("1.Lire une carte");
      System.out.println("2.Créer une carte");
      System.out.println("3.Modifier une carte");
      System.out.println("4.Export d'une Carte en html");
      System.out.println("5.Retour");
      System.out.println("6.Quitter");
      //Ecoute
      Scanner scmenuc = new Scanner(System.in);
      System.out.println("Quel est votre Choix? 1.Lecture 2.Creation 3.Modification. 4.Export 5.Retour 6.Quitter"); //Message recap pour les choix possible
      char menuc = scmenuc.nextLine().charAt(0);//pour recuperer le caractére saisi par l'utilisateur.
      return menuc;
   }
   //-----------------------------//
   //Getteurs Calendrier
   //-----------------------------//
   /** getAction
    * retourne Action
    * @return Action  */
   public String getAction() {
      return Action;
   }
   /**  getDtstart
    * retour la date de début
    * @return Dtstart */
   public Date getDtstart() {
      return Dtstart;
   }
   /** getDtstamp()
    * retourne la clé chiffrement calendrier
    * @return Dtstamp */
   public int getDtstamp() {
      return Dtstamp;
   }
   /** getCategories
    * retourne la catégorie
    * @return Categories */
   public String getCategories() {
      return Categories;
   }
   /**  getProdid
    * retourne le id du calendrier
    * @return Prodid  */
   public int getProdid() {
      return Prodid;
   }
   /** getDescription(
    * retourne la Description
    * @return Description */
   public String getDescription() {
      return Description;
   }
   /** getDtend
    * Retourne la date de fin
    * @return Dtend */
   public String getDtend() {
      return Dtend;
   }
   /** getLocation()
    * retourne la location
    * @return Location  */
   public String getLocation() {
      return Location;
   }
   /** Sequence
    * retourne la Sequnece de chiffrement
    * @return Sequence */
   public String getSequence() {
      return Sequence;
   }
   /** Status
    * retourle le statue du calendrier
    * @return Status */
   public String getStatus() {
      return Status;
   }
   /** Summary
    * retourne le résumé du calendrier
    * @return Summary */
   public String getSummary() {
      return Summary;
   }
   //-----------------------------//
   //Setteur Calendrier
   //-----------------------------//
   /** setCategories
    * Modifie la  categorie
    * @param categories; */
   public void setCategories(String categories) {
      Categories = categories;
   }
   /**  setDescription
    * Modifie la description
    * @param description;  */
   public void setDescription(String description) {
      Description = description;
   }
   /**  setDtstamp
    * Modifie le chiffrement de la date
    * @param dtstamp;  */
   public void setDtstamp(int dtstamp) {
      Dtstamp = dtstamp;
   }
   /**  setDtstart
    * Modifie la date de début
    * @param dtstart;  */
   public void setDtstart(Date dtstart) {
      Dtstart = dtstart;
   }
   /** setDtend
    * Modifie la date de fin
    * @param dtend :date de fin ; */
   public void setDtend(String dtend) {
      Dtend = dtend;
   }
   /**  setLocation
    * Modifie le lieu de l'évenement
    * @param location : lieu */
   public void setLocation(String location) {
      Location = location;
   }
   /** setProdid
    * modifie l'id du calendrier
    * @param prodid: id calend */
   public void setProdid(int prodid) {
      Prodid = prodid;
   }
   /** setSequence
    * modifie la clé de séquence (liée à la clé de chiffrement);
    * @param sequence : la clé de séquance */
   public void setSequence(String sequence) {
      Sequence = sequence;
   }
   /** setStatus
    * modifier le  status (public/privée)
    * @param status : status */
   public void setStatus(String status) {
      Status = status;
   }
   /** setSummary
    * modifier le résumé
    * @param summary : résumé */
   public void setSummary(String summary) {
      Summary = summary;
   }
   /** setTransp
    * modifier la transparence
    * @param transp: transparence */
   public void setTransp(String transp) {
      Transp = transp;
   }
   //-----------------------------//
   //VCRAD
   //-----------------------------//
   //-----------------------------//
   //1.Lire une carte
   //-----------------------------//
   /** ListerFichiers
    * Cette méthode affichera tous les fichiers contenu dans le repertoire Vcards
    * @param chemin:  nom du chemin du repertoire choisi
    *  Utilisation dans la méthode Menu() - lire carte:
   //Etape 1 : on regardes tous les cartes qu'on a dans le repertoir
   //1.1. on donne la valeur du chemin ou sont les Vcards.
   String chemin= "./Vcards"; //on selection le chemin qu'on doit travaille - ici le repertoir des cartes - /Vcards
   //1.2. information à l'utilisateur
   System.out.println("Affichage des cartes présentes dans "+ chemin); //on informe ce qu'on veux faire
   //1.3. On exécute la fonction ListerFichiers - details dans le code
   ListerFichiers( chemin); // equivaut à   ListerFichiers("./Vcards");
   //1.4. Resultat : affiche les fichiers contenus dans le chemin préciser ci dessous**/
   static void ListerFichiers(String chemin) {
      //pour rappel on a chemin = ./Vcards" qu'on a rentrér de base
      File repertoire = new File(chemin); //on cherche le repertoir dans le chemin précisement => ici Vcards
      String[] liste = repertoire.list(); //on créer un tableau pour stocker la liste des fichiers
      if (liste != null) { //si la liste est non null => le repertoire existe
         //boucle for qui parcour la liste des fichiers
         for (String s : liste) {
            System.out.println(s);//pour affiche le nom des fichier
         }
      }
      else //sinon, la liste est nulle => le repertoire n'existe pas => Erreur
      {
         System.err.println("Nom de repertoire invalide"); //affichage de l'erreur posé.
      }
   }
   /**LireFichier
    * fonction qui permet de lier un fichier
    * @param chemin : le chemin source du fichier qu'on veut lire
    * @throws IOException : erreur possible liée si le fichier est manquant
    *  Utilisation dans la méthode Menu() - lire carte:
   //Etape 1 : on regardes tous les cartes qu'on a dans le repertoir
   //1.1. on donne la valeur du chemin ou sont les Vcards.
   String chemin= "./Vcards"; //on selection le chemin qu'on doit travaille - ici le repertoir des cartes - /Vcards
   //1.2. information à l'utilisateur
   System.out.println("Affichage des cartes présentes dans "+ chemin); //on informe ce qu'on veux faire
   //1.3. On exécute la fonction ListerFichiers - details dans le code
   ListerFichiers( chemin); // equivaut à   ListerFichiers("./Vcards");
   //1.4. Resultat : affiche les fichiers contenus dans le chemin préciser ci dessous
   //Etape 2: On sélectionne le fichier qu'on veux lire
   Scanner scfichier= new Scanner(System.in); //Demande de lire
   System.out.println("Quel Fichier voulez vous lire?"); //message pour demander le fichier qu'on veux lire
   String fichier = scfichier.nextLine(); //lecture de la variable
   String chemin="./Vcards/"+fichier+".vcf"; //le chemin du fichier
   //Remarque : on considére que l'utilisateur oublie le .vcf
   LireFichier(chemin); //Fonction pour lire le fichier,*/
   public static void LireFichier(String chemin) throws IOException {
      //Variables
      InputStream flux=new FileInputStream(chemin); //lire le flux du chemin
      InputStreamReader lecture=new InputStreamReader(flux); //lire precisement le Fichier
      BufferedReader buff=new BufferedReader(lecture); //pour permettre de lire rapidement le fichier
      String laLigne; //variable
      //boucle -> tant qu'il a des lignes on l'affiche
      while((laLigne = buff.readLine()) !=null)
      {
         System.out.println(laLigne); //on affiche la ligne précisement
      }
      buff.close();//fermerture du buffer.
   }
   //A Venir-> Nikolla

   /**LireCard - A Creer (vide)
    *  Permet de lire la carte
    *@throws   IOException : exception pour le fichier
   Utilisation : dans le menu */
   public void LireCard() throws IOException {
      //Etape 1 : on regardes tous les cartes qu'on a dans le repertoir
      //1.1. on donne la valeur du chemin ou sont les Vcards.
      String chemin = "./Vcards"; //on selection le chemin qu'on doit travaille - ici le repertoir des cartes - /Vcards
      //1.2. information à l'utilisateur
      System.out.println("Affichage des cartes présentes dans " + chemin); //on informe ce qu'on veux faire
      //1.3. On exécute la fonction ListerFichiers - details dans le code
      ListerFichiers(chemin); // equivaut à   ListerFichiers("./Vcards");
      //1.4. Resultat : affiche les fichiers contenus dans le chemin préciser ci dessous
      //Etape 2: On sélectionne le fichier qu'on veux lire
      Scanner scfichier = new Scanner(System.in); //Demande de lire
      System.out.println("Quel Fichier voulez vous lire?"); //message pour demander le fichier qu'on veux lire
      String fichier = scfichier.nextLine(); //lecture de la variable
      chemin = "./Vcards/" + fichier; //le chemin du fichier
      // on supp
      //System.out.println( chemin);
      //chemin="./Vcards/"+fichier+".vcf"; //le chemin du fichier
      //Remarque : on considére que l'utilisateur oublie le .vcf
      LireFichier(chemin); //Fonction pour lire le fichier,
   }
   /**AffCard - A creer (vide)
    * permet d'afficher une card
    * @param NomCarte:de la carte qu'on veux afficher
    *  Utilisation :Vcard.AffCard(NomCarte);*/
   public void AffCard(String NomCarte){
      //String nom carte //demande quel carte on doit afficher
   }
   //-----------------------------//
   //2.Créer une carte
   //-----------------------------//
   /* A retravailler - travaille chemin :
    * System.out.println("Debut Test Chemin");
    *         //Anciennement =  String nvchemin = NouveauChemin(nomfichier);
    *         String[] nvchemin = NouveauCheminTab(nomfichier); // test nouveauchemin avec un retour d'un string tableau
    *         //Test affichage tableau
    *         for(int i = 0; i inférieur nvchemin.length; i++)
    *         {
    *             System.out.println("À l'emplacement " + i +" du tableau nous avons = " + nvchemin[i]);
    *         }
    *         //En console =  /home/perret/Bureau/PERRET_LY_MARINKOVIC/Java/code/src/Test2.vcf
    *         //En IDE =  /home/perret/Bureau/PERRET_LY_MARINKOVIC/Java/code/Test2.vcf
    *         System.out.println("Le nouveau chemin est "+nvchemin ); //chemin absolu
    *         System.out.println("Fin Test Chemin " );*/
   /** CreerCard
    * Description : Méthode qui permet de Créer une Carte de Visite.
    *  Utilisation dans  le menu
    CreerCard();*/
   public static void CreerCard() {
      //Etape 1 :  Creation d'une Vcard
      // Etape 1.1 : on entre le nom de la nouvelle carte
      Scanner sc = new Scanner(System.in); //Pour lire le nom de la carte
      System.out.println("Veuillez saisir un le nom de la nouvelle carte:"); //message pour demander le nom de la carte
      //remarque : par défault on considére que l'utilisteur ne rentre pas .vcf
      String str = sc.nextLine(); //lecture de la variable
      //Vérification qu'on a bien rentrer une valeur pour le nom de la carte
      boolean regle2 =CheckChaineVide(str); //on vérifie qu'on a bien rentrer un nom
      //Voir les Méthodes de Vérifications
      while(regle2)//tant que la régle est vraie (donc qu'on est une chaine vide ) alors on fait la boucle
      {
         sc = new Scanner(System.in); //Pour lire le nom de la carte
         System.out.println("Veuillez saisir un le nom de la nouvelle carte:"); //message pour demander le nom de la carte
         str = sc.nextLine(); //lecture de la variable
         regle2 =CheckChaineVide(str);//on reverifie qu'on a bien rentrer un nom
      }
      //On transforme le nom de la carte pour quel soit dans le bon format.
      String nomfichier = str+".vcf"; // pour mettre l'extension au nom du fichier
      //remarque: on suppose que l'utilisateur oublie le .vcf pour le nom de la carte
      String chemin="Vcards/"+ nomfichier; // pour aller au chemin relatif
      System.out.println("La nouvelle carte se situe en :"+ chemin); //test si etape ok - affiche le chemin relatif
      //Etape 2 : On écrit les information dessus
      //le try est important, car c'est le cas ou le fichier peux se créer! (pas de doublons, pas de souci de chemin à vérifier)
      try{
         File ff=new File(chemin); // on initialise pour savoir ou on va creer la carte
         ff.createNewFile(); // creer le fichier vierge
         FileWriter ffw=new FileWriter(ff); // Initialise l'écriture sur le fichier
         //Ligne 1  : Begin
         ffw.write("BEGIN:VCARD");  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 2  : Version
         ffw.write("Version:2.1");  // écrire une ligne dans le fichier resultat.txt
         //Pour l'instant on travaille en création version 2.1
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 3  : FN- First Name
         //Pour cette ligne nous avont besoin du nom et du prenom de la personne.
         //Demande Nom de famille
         Scanner sc3 = new Scanner(System.in); //demande de lire- Scanner
         System.out.println("Veuillez saisir le Nom de famille la personne"); //message pour demander le nom de la personne
         String nom  = sc3.nextLine(); //lecture de la variable- nom
         boolean regle = CheckChaineNomsCompose(nom);
         while(!regle)//tant que la régle est fausse alors :
         {
            CheckChaineVide(nom); //envoie un message d'erreur si on a rien saisie
            // System.out.println("Erreur sur le nom"); //Chek
            sc3 = new Scanner(System.in); //demande de lire- Scanner
            System.out.println("Erreur ! Veuillez resaisir le Nom de la personne"); //message pour demander le nom de la personne
            nom  = sc3.nextLine(); //lecture de la variable- nom
            regle = CheckChaineNomsCompose(nom);//on vérfie que la régle est vérifier (sinon la boulce se répéte)
         }
         //Demande Prenom/ First name
         sc3 = new Scanner(System.in);//demande de lire- Scanner
         System.out.println("Veuillez saisir le Prenom de la personne"); //message pour demander le prenom de la personne
         String prenom  = sc3.nextLine(); //lecture de la variable - prenom
         //Vérificaiton si c'est bien un nom
         regle = CheckChaineNomsCompose(prenom);//Régle pour vérifier qu'on a un prénom
         while(!regle){
            CheckChaineVide(prenom); //envoie un message d'erreur si on a rien saisie
            // System.out.println("Erreur sur le nom"); //Chek
            sc3 = new Scanner(System.in); //demande de lire- Scanner
            System.out.println("Erreur ! Veuillez resaisir le Nom de la personne"); //message pour demander le nom de la personne
            prenom  = sc3.nextLine(); //lecture de la variable- nom
            regle = CheckChaineNomsCompose(prenom);
         }
         //Ligne 3 :  FN  = nom de famille la personne*/
         ffw.write("FN:"+nom+" "+prenom);  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 4  : N = prenom de la personne
         ffw.write("N:"+prenom+";"+nom);  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 5: ADR: les Adresses
         // 5.1 Adresse Domicile
         // On commence par l'adresse de domicile,où   la personne ou elle habite
         //Variable utilisé
         String nruedom ;//Pour le numéro et nom de la rue de domicile
         String villedom ;//Pour la ville de domicile
         String cpdom ; //Pour le code postale  de domicile
         String paysdom ;// pour le pays de domicile
         //Code de demande l'adresse- Domicile
         //Information pour l'utilisateur
         System.out.println("Nous allons vous demandez votre adresse de domicile merci de renseignez les information dans cet ordre:"); //message pour introduire les differents champs de l'adresse
         System.out.println("1. Le nom et le numéro de la rue - 2. La ville - 3. le code postale - 4- le pays"); //message pour introduire les differents champs de l'adresse
         //Pour le Numero et nom rue - nruedom- Domicile
         Scanner sc51 = new Scanner(System.in); //Scanner
         System.out.println("1. Indiquez le numero et le nom de la rue "); //message pour demander le numero de la rue
         nruedom  = sc51.nextLine(); //lecture de la variable -numéro de la rue
         //Vérification si on a bien rentrer une valeur pour la numero
         regle2 =CheckChaineVide(nruedom); //on vérifie qu'on une caleur pour la rue
         while(regle2 ){
            sc = new Scanner(System.in); //Pour lire le nom de la carte
            System.out.println("1. Indiquez le numero et le nom de la rue "); //message pour demander le nom de la carte
            nruedom  = sc.nextLine(); //lecture de la variable
            regle2 =CheckChaineVide(nruedom );
         }
         //Pour la Ville - villedom - Domicile
         sc51  = new Scanner(System.in); //demande de lire- Scanner
         System.out.println("2. Indiquez la Ville où la personne habitue"); //message pour demander le nom de la ville
         villedom  = sc51.nextLine(); //lecture de la variable - ville domicile
         //Vérification si on a bien rentrer une ville
         regle2 =CheckChaineVide(villedom); //on vérifie qu'on une valeur pour la rue
         while(regle2 ){
            sc = new Scanner(System.in); //Pour lire le nom de la carte
            System.out.println("2. Indiquez la Ville où la personne habitue"); //message pour demander la ville
            villedom = sc.nextLine(); //lecture de la variable - ville domicile
            regle2 =CheckChaineVide(villedom);
         }
         //CodePostale - cpdom - Domicile
         sc51  = new Scanner(System.in); //demande de lire- Scanner
         System.out.println("3. Indiquez le CodePoste de la Ville où la personne habitue"); //message pour demander le Code Postale
         cpdom  =  sc51.nextLine(); //lecture de la variable - codePostale de la ville -domicile
         //Vérificaiton si on a bien rentrer une  codepostal
         regle2 =CheckChaineVide(cpdom); //on vérifie qu'on une valeur pour le code postale
         while(regle2 ){
            sc = new Scanner(System.in); //Pour lire
            System.out.println("3. Indiquez le CodePoste de la Ville où la personne habitue"); //message pour demander le code Postale
            cpdom = sc.nextLine(); //lecture de la variable - code postale dom
            regle2 =CheckChaineVide(cpdom);
         }
         //Pays- paysdom - Domicile
         sc51  = new Scanner(System.in); //demande de lire
         System.out.println("4. Indiquez la Pays où la personne habitue"); //message pour demander le pays
         paysdom =  sc51.nextLine(); //lecture de la variable -Pays
         //Vérificaiton si on a bien rentrer un pays
         regle2 =CheckChaineVide(paysdom); //on vérifie qu'on une valeur pour de pays - domicile
         while(regle2 ){
            sc = new Scanner(System.in); //Pour lire  - pays
            System.out.println("4. Indiquez la Pays où la personne habitue"); //message pour demander le pays
            paysdom = sc.nextLine(); //lecture de la variable -pays dom
            regle2 =CheckChaineVide(paysdom);
         }
         //Ecriture Sur le fichier de l'adresse
         ffw.write("ADR;HOME;PREF;QUOTED-PRINTABLE:;"+villedom+" "+cpdom+"="+paysdom+";"+nruedom);  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //5.2 Adresse Travaille
         // On demande si la personne a une adresse de travaille (falculative)
         //Variable pour demande inf
         char reponsetra='0'  ; //check si la personne  à une adresse de travaille -
         //Variable de demande générale
         String villetra = villedom;
         String cptra= cpdom;
         String paystra=paysdom;
         String nruetra= nruedom;
         //Ici on fait une boucle while, pour forcer l'utilisateur de rentrer soit 1 ou 2 comme choix.
         do{
            Scanner sca1 = new Scanner(System.in);
            System.out.println("La personne a t-il une adresse de travaille ? 1.Oui 2.Non"); //message pour demander le prenom de la personne
            reponsetra = sca1.nextLine().charAt(0) ; // lit le charactere attendu
            //On considére que la personne rentre 1 ou 2 uniquement
         }while(reponsetra!= '1' && reponsetra!= '2'   );
         if (reponsetra == '1'){
            //La personne a une adresse de travaille
            // System.out.println("Il a une adresse de travaille "); Message de check
            System.out.println("Nous allons vous demandez votre adresse de travaille merci de renseignez les information dans cet ordre:"); //message pour introduire les differents champs de l'adresse
            System.out.println("1. Le nom et le numéro de la rue - 2. La ville - 3. le code postale - 4- le pays"); //message pour introduire les differents champs de l'adresse
            //Pour le Numero et nom rue - nruetra - Travaille
            Scanner sc52 = new Scanner(System.in); //Scanner
            System.out.println("1. Indiquez le numero et le nom de la rue "); //message pour demander le numero de la rue - travaille
            nruetra  = sc52.nextLine(); //lecture de la variable -numéro de la rue - travaille
            //Vérification si on a bien rentrer une valeur pour la numero
            regle2 =CheckChaineVide(nruetra); //on vérifie qu'on une valeur pour la rue de travaille
            while(regle2 ){
               sc = new Scanner(System.in); //Pour lire le nom de la carte
               System.out.println("1. Indiquez le numero et le nom de la rue "); //message pour demander la rue - travaille
               nruetra = sc.nextLine(); //lecture de la variable - adresse rue de travaille
               regle2 =CheckChaineVide(nruetra);
            }
            //Pour la Ville - villetra- Travaille
            sc52  = new Scanner(System.in);
            System.out.println("2. Indiquez la Ville où la personne travaille"); //message pour demander le nom de la ville -travaille
            villetra  = sc52 .nextLine(); //lecture de la variable - ville
            //Vérification si on a bien rentrer une valeur pour la ville
            regle2 =CheckChaineVide(villetra); //on vérifie qu'on une valeur pour la ville de travaille
            while(regle2 ){
               sc = new Scanner(System.in); //Pour lire le nom de la carte
               System.out.println(" 2. Indiquez la Ville où la personne travaille "); //message pour demander la ville - travaille
               villetra = sc.nextLine(); //lecture de la variable-
               regle2 =CheckChaineVide(villetra);
            }
            //CodePostale - cptra- Travaille
            sc52  = new Scanner(System.in);
            System.out.println("3. Indiquez le CodePoste de la Ville où la personne travaille"); //message pour demander le Code Postale
            cptra  =  sc52 .nextLine(); //lecture de la variable - codePostale de la ville
            //Vérification si on a bien rentrer une valeur pour le codepostale de travaille
            regle2 =CheckChaineVide(cptra ); //on vérifie qu'on une valeur pour le code postale - travaille
            while(regle2 ){
               sc = new Scanner(System.in); //Pour lire le nom de la carte
               System.out.println("3. Indiquez le CodePoste de la Ville où la personne travaille"); //message pour demander le code postale
               cptra = sc.nextLine(); //lecture de la variable
               regle2 =CheckChaineVide(cptra);
            }
            //Pays - paystra- Travaille
            sc52  = new Scanner(System.in); //demande de lire
            System.out.println("4. Indiquez la Pays où la personne travaille"); //message pour demander le pays
            paystra =  sc52.nextLine(); //lecture de la variable - codePostale de la ville -travialle
            //Vérification si on a bien rentrer une valeur pour le codepostale de travaille
            regle2 =CheckChaineVide(paystra); //on vérifie qu'on une valeur pour le pays
            while(regle2 ){
               sc = new Scanner(System.in); //Pour lire le nom de la carte
               System.out.println(" 4. Indiquez la Pays où la personne travaille"); //message pour demander le pays - travaille
               paystra = sc.nextLine(); //lecture de la variable
               regle2 =CheckChaineVide(paystra);
            }
            //Ecriture Sur le fichier de l'adresse
            ffw.write("ADR;WORK;PREF;QUOTED-PRINTABLE:;"+villetra+" "+cptra+"="+paystra+";"+nruetra);  // écrire une ligne dans le fichier resultat.txt
            ffw.write("\n"); // forcer le passage à la ligne
         }
         //5.3 Adresse Livraison - LABEL (falcultative !)
         //Variable pour demande info
         char reponseliv; //check si la personne  à une adresse de livraison - par défault on considére non
         //Demande si la personne a une adresse de livraison
         do{
            Scanner sc53 = new Scanner(System.in);
            System.out.println("La personne a t-il une adresse de livraison? 1.Oui 2.Non"); //message pour demander si il a bien une adresse de livraison
            reponseliv = sc53.nextLine().charAt(0);
         }while(reponseliv != '1' && reponseliv != '2');
         if (reponseliv == '1'){
            //La personne a une adresse de livraison
            do{
               Scanner sc531 = new Scanner(System.in);
               System.out.println("Ou voulez vous faire livrer ? 1.Domicile 2.Travaille 3.Autre"); //message pour demander ou livrer
               reponseliv = sc531.nextLine().charAt(0);
            }while(reponseliv != '1' && reponseliv != '2' && reponseliv != '3' );
            if(reponseliv == '3'){
               // System.out.println("Il a une adresse de livraison "); Check
               // Variable de demande générale
               String villeliv  ;
               String cpliv ;
               String paysliv;
               String nrueliv ;
               //Affiche
               System.out.println("Nous allons vous demandez votre adresse de Livraison merci de renseignez les information dans cet ordre:"); //message pour introduire les differents champs de l'adresse
               System.out.println("1. Le nom et le numéro de la rue - 2. La ville - 3. le code postale - 4- le pays"); //message pour introduire les differents champs de l'adresse
               //Pour le Numero et nom rue - nrueliv - Livraison
               Scanner sc52 = new Scanner(System.in); //Scanner
               System.out.println("1. Indiquez le numero et le nom de la rue  de livraison"); //message pour demander le numero de la rue
               nrueliv  = sc52.nextLine(); //lecture de la variable -numéro de la rue - livraison
               //Vérification si on a bien rentrer une valeur pour la rue et numéro de livraison
               regle2 =CheckChaineVide(nrueliv); //on vérifie qu'on une valeur pour le code postale
               while(regle2 ){
                  sc = new Scanner(System.in); //Pour lire le nom de la carte
                  System.out.println(" 1. Indiquez le numero et le nom de la rue de livraison "); //message pour demander la rue - livraison
                  nrueliv = sc.nextLine(); //lecture de la variable - rue liv
                  regle2 =CheckChaineVide(nrueliv);
               }
               //Pour la Ville - villeliv- Livraison
               sc52  = new Scanner(System.in);
               System.out.println("2. Indiquez la Ville où la personne veut être livrer"); //message pour demander le nom de la ville - livrasion
               villeliv = sc52 .nextLine(); //lecture de la variable - ville - liv
               //Vérification si on a bien rentrer une valeur pour le ville - liv
               regle2 =CheckChaineVide(villeliv ); //on vérifie qu'on une valeur pour la ville - travaille
               while(regle2 ){
                  sc = new Scanner(System.in); //Pour lire
                  System.out.println("2. Indiquez la Ville où la personne veut être livrer");//message pour demander le nom de la ville - livrasion
                  villeliv = sc.nextLine(); //lecture de la variable
                  regle2 =CheckChaineVide(villeliv );
               }
               //CodePostale - cpliv - Livraison
               sc52  = new Scanner(System.in);
               System.out.println("3. Indiquez le CodePoste de la Ville où la personne veut être livrer"); //message pour demander le Code Postale
               cpliv  =  sc52 .nextLine(); //lecture de la variable - codePostale de la ville
               //Vérification si on a bien rentrer une valeur pour le codepostale - liv
               regle2 =CheckChaineVide(cpliv  ); //on vérifie qu'on une valeur pour le code ville - livraison
               while(regle2 ){
                  sc = new Scanner(System.in); //Pour lire le nom de la carte
                  System.out.println("3. Indiquez le CodePoste de la Ville où la personne veut être livrer");//message pour demander le nom de la ville - livrasion
                  cpliv  = sc.nextLine(); //lecture de la variable - codepostale
                  regle2 =CheckChaineVide(cpliv );
               }
               //Pays - paysliv- Livraison
               sc52  = new Scanner(System.in); //demande de lire
               System.out.println("4. Indiquez la Pays où la personne veut être livrer"); //message pour demander e pays -livraison
               paysliv =  sc52.nextLine(); //lecture de la variable -ville de livraions
               //Vérification si on a bien rentrer une valeur pour le codepostale -liv
               regle2 =CheckChaineVide(paysliv ); //on vérifie qu'on une valeur pour le codepostale -liv
               while(regle2 ){
                  sc = new Scanner(System.in); //Pour lire le nom de la carte
                  System.out.println("4. Indiquez la Pays où la personne veut être livrer");//message pour demander le codepostale -liv
                  paysliv = sc.nextLine(); //lecture de la variable
                  regle2 =CheckChaineVide(paysliv);
               }
               //Ecriture sur le fichier de l'adresse
               ffw.write("LABEL;QUOTED-PRINTABLE;WORK;PREF:"+nrueliv+"="+paysliv+" "+cpliv+"="+villeliv);  // écrire une ligne dans le fichier resultat.txt
               ffw.write("\n"); // forcer le passage à la ligne
            }
            else if(reponseliv =='1'){
               //Cas si l'adresse de domicile est identique à celle de livraison
               //Ecriture Sur le fichier de l'adresse
               ffw.write("LABEL;QUOTED-PRINTABLE;HOME;PREF:"+nruedom+"="+paysdom+" "+cpdom+"="+villedom);  // écrire une ligne dans le fichier resultat.txt
               ffw.write("\n"); // forcer le passage à la ligne
            }
            else { // reponseliv =='2'
               //Cas si l'adresse de domicile est identique à celle de livraison
               //Ecriture Sur le fichier de l'adresse
               ffw.write("LABEL;QUOTED-PRINTABLE;WORK;PREF:"+nruetra+"="+paystra+" "+cptra+"="+villetra);  // écrire une ligne dans le fichier resultat.txt
               ffw.write("\n"); // forcer le passage à la ligne
            }
         }
         //Ligne 6:  TEL : Telephone
         //Etape 6.1 Demande le téléphone
         Scanner sc6  = new Scanner(System.in); //demande de lire
         System.out.println("6. Quel est votre teléphone?"); //message pour demander le teléphone
         String tel =  sc6.nextLine(); //lecture de la variable - téléphone
         //Vérification tel
         regle2 =CheckChaineTel(tel ); //on vérifie qu'on une valeur pour le codepostale -liv
         while(!regle2 ){
            CheckChaineVide(tel );
            sc6  = new Scanner(System.in); //demande de lire
            System.out.println("6. Quel est votre teléphone?"); //message pour demander le teléphone
            tel =  sc6.nextLine(); //lecture de la variable - téléphone
            regle2 =CheckChaineTel(tel);
         }
         //Etape 6.2 Ecriture sur le fichier
         ffw.write("TEL;CELL:"+tel);  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 7:  EMAIL: email de la personne
         Scanner sc7  = new Scanner(System.in); //demande de lire
         System.out.println("7. Quel est votre email?"); //message pour demander l'email
         String email =  sc7.nextLine(); //lecture de la variable - email
         //Vérification email
         regle2 =CheckChaineVide(email ); //on vérifie qu'on une valeur pour le codepostale -liv
         while(regle2 ){
            sc7  = new Scanner(System.in); //demande de lire
            System.out.println("7. Quel est votre email?"); //message pour demander l'email
            email =  sc7.nextLine(); //lecture de la variable - email
            regle2 =CheckChaineVide(email );
         }
         ffw.write("EMAIL;INTERNET:"+email);  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //LIGNE 8: UID : id de la carte
         ffw.write("UID:");  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 9: END
         ffw.write("END:VCARD");  // écrire une ligne dans le fichier resultat.txt
         //FIN
         ffw.close(); // fermer le fichier à la fin des traitements
      } catch (Exception ignored) {
         System.out.println("Erreur! le fichier n'a pas été trouvé");
      }
      System.out.print("Fin de la creation de la carte");
   }
   /**NouveauChemin
    * permet de retrouner le Chemin de la nouvelle carte même si l'architecture est différente
    * parcourgt initial
    * @param nomfichier : le fichier qu'on doit trouver le nouveau chemin
    * @return chemin : le chemin du fichier final.*/
   private static String NouveauChemin(String nomfichier) {
      System.out.println("Debut méthode NouveauChemin  "); //debut
      //Ligne pour relatif
      String chemin="Vcards/"+ nomfichier; // pour aller au chemin relatif
      System.out.println("Le nouvelle carte se situe en :"+ chemin); //test si etape ok - affiche le chemin relatif
      File f = new File(chemin); // on stocke le chemin fait
      String cheminabsolu = f.getAbsolutePath();// ici normalement on doit récupérer le chemin absolu du fichier
      System.out.println("Chemin absolu du fichier "+nomfichier+" est : " + cheminabsolu ); //chemin absolu
      //on récupére le chemin du dosisier racine => cheminracine
      final String SEPARATEUR = "/"; //on va séparer le chemin absolu avec les espace
      StringBuilder cheminracine = new StringBuilder(); //de base on ne connait pas le chemin racine
      String[] mots = cheminabsolu .split(SEPARATEUR); // on sépare le chemin absolu avec le séparateur
      //boucle for=> on parcour le chemin absolu
      for (String mot : mots) {
         System.out.println(mot); //on affiche les élement du chemin
         boolean compteur = false; // boolean qui permet de s'arreter/démarrer
         //Dés qu'on a trouvé le bureau=> on active le Compteur
         if (mot.equals("Bureau") || mot.equals("Desktop")) {
            compteur = true;
         }
         //si le compteur est activé alors on stocke les valeurs dans le chemin racine
         if (compteur) {
            cheminracine.append('/').append(mot);
         }
         //Dés qu'on a trouvé PERRET_LY_MARINKOVIC, on arréte le compteur
         if (mot.equals("PERRET_LY_MARINKOVIC")) {
            cheminracine.append('/').append(mot);
            compteur = false;
         }
      }
      System.out.println(cheminracine);
      // Attendu pour bonne architecture sur bureau: cheminracine="/Bureau/PERRET_LY_MARINKOVIC" ou /Desktop/PERRET_LY_MARINKOVIC" ;
      //Cas Espace de travaille sur bureau
      if(cheminracine.toString().equals("/Bureau/PERRET_LY_MARINKOVIC") || cheminracine.toString().equals("/Desktop/PERRET_LY_MARINKOVIC" )) {
         System.out.println("Espace de travaille : Bureau");
         if (cheminabsolu.equals("/home/perret/Bureau/PERRET_LY_MARINKOVIC/Java/code/Vcards/" + nomfichier)) //Respect du readme- bureau
         {
            System.out.println("Nous travaillons sur l'espace bureau  avec bonne architecture "); //check si vraiment tou est ok
            //  nous somme dans le cas ideal, il n'a pas de modificaiton sur le chemin à faire

         } else {
            System.out.println("Espace de travaille bureau mais l'architecture n'est pas bonne "); //check
            //Travaille a faire => probleme avec le chemin absolu
            // nous savons que cheminabsolu = cheminrelatif+cheminfichier
            //on va séparer le chemin absolu avec les espace
            cheminracine = new StringBuilder(); //de base on ne connait pas le chemin racine
            String[] mots2 = cheminabsolu.split(SEPARATEUR); // on sépare le chemin absolu avec le séparateur

            //boucle for=> on parcour le chemin absolu jusqu'a Perret
            for (int i = 0; i < mots2.length; i++) {
               if (!mots[i].equals("PERRET_LY_MARINKOVIC")) {
                  cheminracine.append('/').append(mots2[i]);
               }
            }
         }
      }
      //Cas Espace de travaille non sur le bureau=> Nous demendons à l'utilisateur de mettre le bureau sur celui-ci
      else{
         System.out.println("Nous ne travaillons pas sur l'espace du bureau !"); //check
         System.out.println("Merci de mettre le projet PERRET_LY_MARINKOVIC sur le bureau!"); //check

         //remarque, en ide et si le projet n'est pas sur le bureau, le chemin absolu est adapté .
         cheminabsolu = f.getAbsolutePath();
      }
      //En console => /home/perret/Bureau/PERRET_LY_MARINKOVIC/Java/code/src/Test2.vcf
      //En IDE => /home/perret/Bureau/PERRET_LY_MARINKOVIC/Java/code/Test2.vcf
      System.out.println("Fin méthode NouveauChemin"); //fin
      return cheminabsolu;
   }
   /**NouveauCheminTab
    NouveauChemin mais avec un retour en tableau de donnée
    * @param nomfichier nomfichier : le fichier qu'on doit trouver le nouveau chemin
    * @return chemin : le chemin du fichier final.*/
   private static String[] NouveauCheminTab(String nomfichier){
      System.out.println("Debut méthode NouveauChemin Tab  "); //debut
      // Chemin relatif
      StringBuilder cheminrelatif = new StringBuilder("./");
      String[] cheminrelatiftab = {"Vcard" , nomfichier};
      //affichage standard
      for (int i = 0; i < cheminrelatiftab.length; i++) {
         //System.out.println(cheminrelatiftab[i]);
         if(i>0){
            cheminrelatif.append("/");
         }
         cheminrelatif.append(cheminrelatiftab[i]);
      }
      System.out.println("Debut Test Absolu  "); //debut
      // Affiche
      System.out.println("Le nouvelle carte se situe en chemin relatif:" + cheminrelatif); //test si etape ok - affiche le chemin relatif
      // On creer le fichier avec ce chemin
      File f = new File(cheminrelatif.toString()); // on stocke le chemin fait
      //Chemin Absolu
      final String SEPARATEUR = "/"; //on va séparer le chemin absolu avec les espace
      String cheminabsolu = f.getAbsolutePath();// récupération du chemin absolu par méthode
      System.out.println("Le chemin absolu est :" +cheminabsolu );
      String[ ] cheminabsolutab = cheminabsolu .split(SEPARATEUR);  //on fixe a 20 pour permettre une flexibilité du tableau
      /*  test affichage */
      for(int i = 0; i < cheminabsolutab.length; i++)
      {
         System.out.println("À l'emplacement " + i +" du tableau nous avons = " + cheminabsolutab[i]);
      }
      System.out.println("Fin Test Absolu  "); //debut
      System.out.println("Fin méthode NouveauChemin"); //fin
      return cheminabsolutab ; //retourne un tableau des parametre du chemin
   }
   //-----------------------------//
   //3.Modifier une carte
   //-----------------------------//
   /**ModifCard
    * permet de modifier une carte
    * @throws IOException
    * Utilisation : dans le menu
   ModifCard( );*/
   public void ModifCard() throws IOException{
      // Grand Objectif 3 : modifier un fichier Vcard
      //Etape 1, on doit demander à l'utilisateur quel fichier il veut modifie
      //Etape 1.1 - lister tous les fichiers
      System.out.println("Affichage des cartes présentes"); //on informe ce qu'on veux faire
      String chemin= "./Vcards"; //on selection le chemin qu'on doit travaille
      ListerFichiers(chemin); //permet de lister le fichier
      // A voir ou remplacer par une méthode de Recherche?
      //Etape 1.2 - demande du choix du fichier a modifier
      Scanner sc = new Scanner(System.in); //demande de lire- Scanner
      System.out.println("Quel est le fichier voulez vous modifier?"); //message pour demander le nom de la personne
      String nomcarte = sc.nextLine(); //lecture de la variable- nomcarte
      //ici on suppose que l'utilisateur écrira l'extension, le nom complet de la carte.
      //Etape 1.3 = on regarde si le nom du fichier, pour savoir si il existe bien
      boolean regle = CheckNomCarte(nomcarte,chemin); //varible pour stoker la regle
      //System.out.println(regle); //Vérification
      if(regle) // Si  le check, le fichier existe alors on peux modifier:
      {
         //Etape 2: Modification
         //Etape 2.1 : On copie dans un fichier tampon pour des souci d'écriture !
         chemin = chemin+"/"+nomcarte; //le chemin sera alors :  "./Vcards/nomcarte"
         //System.out.println("Le fichier est :"+chemin); //Vérification qu'on a pris en compte le chemin complet
         String chemintampon = "./Vcards/Temp.vcf"; // On appelle le fichier tempon , Temp.vcf .
         CopieFichier(chemin, chemintampon); //fonction pour la copie de fichier
         //Cas validation, on récria - créara sur un nouveau fichier
         //Cas Erreur : on supprime simplement le fichier tampon
         //Etape 2.2 : On regarde et lit le fichier actuel- permet de vérifier qu'on peux lire les valleur
         // (dans le future ne doit pas afficher !)
         LireFichier(chemintampon); // permet de lire le fichier sans modification.
         // rappel : le fichier tampon n'est pas modifier et identique au fichier standard
         //Etape 2.3 : On recupére et regarde les valeurs qu'on a déja.
         //Etape 2.3.1 test  FN et N
         // System.out.println("Debut Test Nom et prénom" );
         String nom = ChercheNom(chemintampon); //on cherche le nom et le stocke dans la variable nom
         //Pour les fonctions de Recherche de donné voir :  Méthode de Recherche de Donnée
         String prenom = CherchePrenom(chemintampon); //on cherche le prenom et le stocke dans la variable prenom
         //System.out.println("Nom : "+nom+" Prenom :"+prenom); //check pour voir si on a bien récuperer les variables ci-dessous
         //On les stocke dans les valeur de classe - N et FN
         setFN(nom);//On stocke/met la valeur FN qui est le nom avec l'acesseur
         setN(prenom);//On stocke/met la valeur N qui est le prenom avec l'acesseur
         //System.out.println("Nom : "+getFN()+" Prenom :"+getN());
         // System.out.println("Fin Test Nom et Prénom" );
         //Etape 2.3.2 test ADR
         //System.out.println("Debut Test Adr");
         //On doit regarder combien d'adresse on a
         int nombreadresse= NbrAdress(chemintampon); //Cheche le nombre d'adresse
         // System.out.println("il a "+nombreadresse+" adresse");//affiche le nombre d'adresse
         if(nombreadresse ==1) //Cas 1 adresse
         {
            //Cas si une seul adresse
            String nrue = ChecheNomRue(chemintampon); // Cherche le nom de la rue dans la carte et le stocke dans la variable nrue
            String ville = ChecheNomVille(chemintampon); // Cherche le nom de la ville dans la carte et le stocke dans la variable ville
            String pays = ChecheNomPays(chemintampon);// Cherche le nom dy pays dans la carte et le stocke dans la variable pays
            String cp = ChecheCodePostale(chemintampon);// Cherche le code postale dans la carte et le stocke dans la variable code postale
            //System.out.println("L'adresse unique est :"+nrue+"; Ville : "+ville+"; Pays :"+pays+" Cp :"+cp);
            //Stockage dans ADR
            setADR("ADR;HOME;PREF;QUOTED-PRINTABLE:;"+ville+" "+cp+"="+pays+";"+nrue); //Modifie la variable ADR avec l'acesseur
            //reference jean_luc: ADR;WORK;PREF;QUOTED-PRINTABLE:;Bruxelles 1200=Belgique;6A Rue Th. Decuyper
            //System.out.println(getADR()); //verif
         }
         else if (nombreadresse ==2) // cas si on a 2 adresse
         {
            //System.out.println("Attention! il a plus qu'une Adresse!");// Message d'avertissement- information.
            //Recupération de l'adresse de Domicile
            String nruedom = ChecheNomRueDom(chemintampon); // Cherche le nom de la rue de domicle dans la carte et le stocke dans la variable nruedom
            String villedom = ChecheNomVilleDom(chemintampon); // Cherche le nom de la ville de domicle dans la carte et le stocke dans la variable villedom
            String paysdom = ChecheNomPaysDom(chemintampon); // Cherche le nom du pays de domicle dans la carte et le stocke dans la variable paysdom
            String cpdom = ChecheCodePostaleDom(chemintampon); // Cherche le codepostale de domicle dans la carte et le stocke dans la variable cpdom
            //System.out.println("L'adresse de domicile est :"+nruedom+"; Ville : "+villedom+"; Pays :"+paysdom+" Cp :"+cpdom);
            //Stockage dans ADRDROM
            setADRDOM("ADR;HOME;PREF;QUOTED-PRINTABLE:;"+paysdom+" "+cpdom+"="+villedom+";"+nruedom ); //Modifie la variable ADRDOM avec l'acesseur
            //reference ADR;HOME;PREF;QUOTED-PRINTABLE:;France 92800=Puteaux;7 Rue De La Paix
            // System.out.println(getADRDOM()); //set modifie
            //Recupération de l'adresse de Travaille
            String nruetra = ChecheNomRueTra(chemintampon); // Cherche le nom de la rue de travaille dans la carte et le stocke dans la variable nruetra
            String villetra = ChecheNomVilleTra(chemintampon); // Cherche le nom de la ville de travaille dans la carte et le stocke dans la variable villetra
            String paystra = ChecheNomPaysTra(chemintampon); // Cherche le nom du pays de travaille dans la carte et le stocke dans la variable paystra
            String cptra= ChecheCodePostaleTra(chemintampon);// Cherche le codepostale de travaille dans la carte et le stocke dans la variable cptra
            // System.out.println("L'adresse de travaille est :"+nruetra+"; Ville : "+villetra +"; Pays :"+paystra +" Cp :"+cptra );
            //Stockage dans ADRTRA
            setADRTRA("ADR;WORK;PREF;QUOTED-PRINTABLE:;"+paystra+" "+cptra+"="+villetra+";"+nruetra ); //Modifie la variable ADRTRA avec l'acesseur
            //reference ADR;WORK;PREF;QUOTED-PRINTABLE:;Bruxelles 12000=Belgique;6A Rue Th. Decuyper
            // System.out.println(getADRTRA());
         }
         //System.out.println("Fin Test Adr");
         //Etape 2.3.3 test LABEl
         //System.out.println("Debut test LABEL" );
         //Cas si une seul adresse
         String nrueliv = ChecheNomRueLiv(chemintampon); // Cherche le nom de la rue de livraison dans la carte et le stocke dans la variable nrueliv
         String villeliv = ChecheNomVilleLiv(chemintampon); // Cherche le nom de la ville de livraison dans la carte et le stocke dans la variable villeliv
         String paysliv = ChecheNomPaysLiv(chemintampon); // Cherche le nom du pays de livraison dans la carte et le stocke dans la variable paysliv
         String cpliv  = ChecheCodePostaleLiv(chemintampon); // Cherche le codepostale de livraison dans la carte et le stocke dans la variable cpliv
         String labellieu = ChecheLieuLiv(chemintampon); //Cherche si le lieu de livraions est au un domicile ou de travaille (ou autre?)
         //System.out.println("L'adresse de livraison de "+labellieu+" est :"+nrueliv+"; Ville : "+villeliv+"; Pays :"+paysliv+" Cp :"+cpliv);
         //Stockage label
         setLabel("LABEL;QUOTED-PRINTABLE;"+labellieu+";PREF:"+nrueliv+"="+villeliv+" "+cpliv+"="+paysliv); //Modifie la variable LABEL avec l'acesseur
         //reference :LABEL;QUOTED-PRINTABLE;WORK;PREF:Rue Th. Decuyper 6A=Bruxelles 1200=Belgique
         //System.out.println(getLabel());
         //System.out.println("Fin test LABEL" );
         //Etape 2.3.3 test TEL
         //System.out.println("Debut test Tel" );
         String tel = ChercheTel(chemintampon); //cherche le telephone dans la carte et le stocke dans TEL
         //System.out.println("Le téléphone est :"+tel);
         //Stockage Tel
         setTel(tel);//Modifie la variable TEL avec l'acesseur
         //System.out.println(getTel());
         //System.out.println("Fin test TEL" );
         //System.out.println("Debut test EMAIL" );
         String email= ChercheEmail(chemintampon); //cherche l'email  dans la carte et le stocke dans EMAIL
         //System.out.println("L'email est :"+email);
         //Stockage Email
         setEmail(email); //Modifie la variable EMAIL avec l'acesseur
         //System.out.println(getEmail());
         //System.out.println("Fin test EMAIL");
         //Debut boucle verif
         boolean verif = false; // boolean pour vérifier si on fait la modifciation
         do{
            System.out.println("Programme de Modification du Fichier"); //affichage de démarrage du programme de modification
            //Etape 2.4 : On demande la valeurs qu'on veux modifie
            Scanner scmodif = new Scanner(System.in); //demande de lire- Scanner
            System.out.println("Quel Valeur voulez-vous modifiez?"); //menu de modification
            System.out.println("1. Le Nom - FN");
            System.out.println("2. Le Prenom - N");
            System.out.println("3. L'adresse- domicile ou travaile- ADR ");
            System.out.println("4. L'adresse de livraison - LABEL");
            System.out.println("5. Le téléphone -TEL");
            System.out.println("6. L'email - EMAIL");
            System.out.println("7. Quitter la modification");
            int valmodif = scmodif.nextInt() ; //lecture de la variable- verifm
            String nvLabel;
            switch (valmodif){
               case 1://Le Nom - FN
                  //System.out.println("Debut test Modif Nom");
                  //1.Affiche Valeur
                  System.out.println("Le nom de famille est :"+getFN());
                  //2.Demande valeur
                  Scanner scnom = new Scanner(System.in); //demande de lire- Scanner
                  System.out.println("Saisir le nouveau nom famille est :"); //Demande de saision
                  String nvnom  = scnom.nextLine();
                  //3.Verif Valeur
                  regle = CheckChaineNomsCompose(nvnom);
                  //System.out.println(regle);
                  while(!regle) //Boucle de Vérification du nom
                  {
                     scnom = new Scanner(System.in); //demande de lire- Scanner
                     System.out.println("Resaisir le nouveau nom :"); //Demande de saision
                     nvnom  =  scnom.nextLine();
                     regle = CheckChaineNomsCompose(nvnom);
                  }
                  //4.Modification de la valeur
                  setFN(nvnom); //Modification de la valeur du nom
                  // System.out.println("Le nouveau nom est:"+getFN()); //Vérification
                  //5.Modification du fichier tempon
                  ModifTempon();
                  //6.Lire le Fichier Tampon Modifier
                  LireFichier("./Vcards/Temp.vcf"); //lecture du fichier
                  //System.out.println("Fin test Modif Nom");
                  break;
               case 2://Le Prenom - N
                  //1.Affiche Valeur
                  //System.out.println("Debut test Modif Prenom");
                  System.out.println("Le prenom est :"+getN());
                  //2.Demande valeur
                  Scanner scprenom = new Scanner(System.in); //demande de lire- Scanner
                  System.out.println("Saisir le nouveau prenom :"); //Demande de saision
                  String nvprenom  = scprenom.nextLine();
                  //3.Verif Valeur
                  regle = CheckChaineNomsCompose(nvprenom);
                  //System.out.println(regle);
                  while(!regle){
                     scprenom = new Scanner(System.in); //demande de lire- Scanner
                     System.out.println("Resaisir le nouveau nom :"); //Demande de saision
                     nvprenom  = scprenom.nextLine();
                     regle = CheckChaineNomsCompose(nvprenom );
                  }
                  //4.Modification de la valeur
                  setN(nvprenom);
                  System.out.println("Le nouveau prenom est:"+getN());
                  //5.Modification du fichier tempon
                  ModifTempon(); //Modification du fichier tempon
                  //6.Lire le Fichier Tampon Modifier
                  LireFichier("./Vcards/Temp.vcf"); //lecture du fichier
                  //System.out.println("Fin test Modif Prenom");
                  break;
               case 3://L'adresse - ADR
                  //1.Affiche Valeur
                  //System.out.println("Debut test Modif ADR");
                  int nbadresses = 0;
                  //variable
                  String nrue     = null, nruedom     = null, nruetra = null;
                  String cp       = null, cpdom       = null, cptra = null;
                  String ville    = null, villedom    = null, villetra = null;
                  String pays     = null, paysdom     = null, paystra = null;
                  if(!getADR().equals("") && !getADR().isEmpty()) //Cas adresse unique - isEmpty very que la chaine est vide.
                  {
                     System.out.println("Il a une une adresse unique sur la carte!");
                     nbadresses = 1;
                     //on doit retranscrire l'adresse d'abord
                     nrue = ChecheNomRue("./Vcards/Temp.vcf");
                     cp = ChecheCodePostale("./Vcards/Temp.vcf");
                     ville = ChecheNomVille("./Vcards/Temp.vcf");
                     pays = ChecheNomPays("./Vcards/Temp.vcf");
                     //Affichage adresse
                     System.out.println("L'adresse actuelle est :");
                     System.out.println(nrue);
                     System.out.println(ville+"     "+cp);
                     System.out.println(pays);
                  }
                  else if( (!getADRDOM().equals("")  && !getADRDOM().isEmpty() ) &&
                          (!getADRTRA().equals("")  && !getADRTRA().isEmpty() )
                  )//Cas adresse multiple - Si on a DOM on a un travaille.
                  {
                     System.out.println("Il a deux type d'adresses sur la carte!");
                     nbadresses = 2;
                     //on doit retranscrire les adresse  d'abord
                     //Domicile
                     nruedom = ChecheNomRueDom("./Vcards/Temp.vcf");
                     cpdom = ChecheCodePostaleDom("./Vcards/Temp.vcf");
                     villedom = ChecheNomVilleDom("./Vcards/Temp.vcf");
                     paysdom = ChecheNomPaysDom("./Vcards/Temp.vcf");
                     //Affichage adresse dom
                     System.out.println("L'adresse de domicile est :");
                     System.out.println(nruedom);
                     System.out.println(villedom+"     "+cpdom);
                     System.out.println(paysdom);
                     //recherche
                     nruetra = ChecheNomRueTra("./Vcards/Temp.vcf");
                     cptra = ChecheCodePostaleTra("./Vcards/Temp.vcf");
                     villetra = ChecheNomVilleTra("./Vcards/Temp.vcf");
                     paystra = ChecheNomPaysTra("./Vcards/Temp.vcf");
                     //Affichage
                     System.out.println("L'adresse de travaille est :");
                     System.out.println(nruetra);
                     System.out.println(villetra+"     "+cptra);
                     System.out.println(paystra);
                  }
                  else{
                     System.out.println("ERREUR ! Adresse introuvable ! ");
                  }
                  //2.Demande valeur
                  if (nbadresses == 1) {
                     //Code de demande l'adresse
                     //Information pour l'utilisateur
                     System.out.println("Nous allons vous demandez votre adresse de domicile merci de renseignez les information dans cet ordre:"); //message pour introduire les differents champs de l'adresse
                     System.out.println("1. Le nom et le numéro de la rue - 2. La ville - 3. le code postale - 4- le pays"); //message pour introduire les differents champs de l'adresse
                     //Pour le Numero et nom rue - nrue
                     Scanner sc51 = new Scanner(System.in); //Scanner
                     System.out.println("1. Indiquez le numero et le nom de la rue "); //message pour demander le numero de la rue
                     nrue = sc51.nextLine(); //lecture de la variable -numéro de la rue
                     //Vérification si on a bien rentrer une valeur pour la numero
                     boolean regle2 = CheckChaineVide(nrue); //on vérifie qu'on une caleur pour la rue
                     while(regle2 ){
                        sc = new Scanner(System.in); //Pour lire le nom de la carte
                        System.out.println("1. Indiquez le numero et le nom de la rue "); //message pour demander le numero de la rue
                        nrue  = sc.nextLine(); //lecture de la variable nrue
                        regle2 =CheckChaineVide(nrue);
                     }
                     //Pour la Ville
                     sc51  = new Scanner(System.in); //demande de lire- Scanner
                     System.out.println("2. Indiquez la Ville où la personne habitue"); //message pour demander le nom de la ville
                     ville = sc51.nextLine(); //lecture de la variable - ville
                     //Vérification si on a bien rentrer une ville
                     regle2 =CheckChaineVide(ville); //on vérifie qu'on une valeur pour la rue
                     while(regle2 ){
                        sc = new Scanner(System.in); //Pour lire le nom de la carte
                        System.out.println("2. Indiquez la Ville où la personne habitue"); //message pour demander la ville
                        ville = sc.nextLine(); //lecture de la variable
                        regle2 =CheckChaineVide(ville);
                     }
                     //CodePostale - cpdom - Domicile
                     sc51  = new Scanner(System.in); //demande de lire- Scanner
                     System.out.println("3. Indiquez le CodePoste de la Ville où la personne habitue"); //message pour demander le Code Postale
                     cp = sc51.nextLine(); //lecture de la variable - codePostale de la ville
                     //Vérificaiton si on a bien rentrer une  codepostal
                     regle2 =CheckChaineVide(cp ); //on vérifie qu'on une valeur pour le code postale
                     while(regle2 ){
                        sc = new Scanner(System.in); //Pour lire
                        System.out.println("3. Indiquez le CodePoste de la Ville où la personne habitue"); //message pour demander le code Postale
                        cp = sc.nextLine(); //lecture de la variable - code postale
                        regle2 =CheckChaineVide(cp);
                     }
                     //Pays- paysdom - Domicile
                     sc51  = new Scanner(System.in); //demande de lire
                     System.out.println("4. Indiquez la Pays où la personne habitue"); //message pour demander le pays
                     pays = sc51.nextLine(); //lecture de la variable -Pays
                     //Vérificaiton si on a bien rentrer un pays
                     regle2 =CheckChaineVide(pays); //on vérifie qu'on une valeur pour de pays
                     while(regle2 ){
                        sc = new Scanner(System.in); //Pour lire  - pays
                        System.out.println("4. Indiquez la Pays où la personne habitue"); //message pour demander le pays
                        pays = sc.nextLine(); //lecture de la variable -pays
                        regle2 =CheckChaineVide(pays);
                     }
                  }
                  else if(nbadresses == 2) {
                     Scanner scadr = new Scanner(System.in); //demande de lire
                     System.out.println("Quel adresse voulez-vous modifiez?");
                     System.out.println("1.Domicile 2.Travaille");
                     char adr = scadr.nextLine().charAt(0); //lecture de la variable adr
                     if(adr == '1')//Domicile
                     {
                        //Demande Info
                        //Information pour l'utilisateur
                        System.out.println("Nous allons vous demandez votre adresse de domicile merci de renseignez les information dans cet ordre:"); //message pour introduire les differents champs de l'adresse
                        System.out.println("1. Le nom et le numéro de la rue - 2. La ville - 3. le code postale - 4- le pays"); //message pour introduire les differents champs de l'adresse
                        //Pour le Numero et nom rue - nruedom- Domicile
                        Scanner sc51 = new Scanner(System.in); //Scanner
                        System.out.println("1. Indiquez le numero et le nom de la rue "); //message pour demander le numero de la rue
                        nruedom  = sc51.nextLine(); //lecture de la variable -numéro de la rue
                        //Vérification si on a bien rentrer une valeur pour la numero
                        boolean regle2 = CheckChaineVide(nruedom); //on vérifie qu'on une caleur pour la rue
                        while(regle2 ){
                           sc = new Scanner(System.in); //Pour lire le nom de la carte
                           System.out.println("1. Indiquez le numero et le nom de la rue "); //message pour demander le nom de la carte
                           nruedom  = sc.nextLine(); //lecture de la variable
                           regle2 =CheckChaineVide(nruedom );
                        }
                        //Pour la Ville - villedom - Domicile
                        sc51  = new Scanner(System.in); //demande de lire- Scanner
                        System.out.println("2. Indiquez la Ville où la personne habitue"); //message pour demander le nom de la ville
                        villedom  = sc51.nextLine(); //lecture de la variable - ville domicile
                        //Vérification si on a bien rentrer une ville
                        regle2 =CheckChaineVide(villedom); //on vérifie qu'on une valeur pour la rue
                        while(regle2 ){
                           sc = new Scanner(System.in); //Pour lire le nom de la carte
                           System.out.println("2. Indiquez la Ville où la personne habitue"); //message pour demander la ville
                           villedom = sc.nextLine(); //lecture de la variable - ville domicile
                           regle2 =CheckChaineVide(villedom);
                        }
                        //CodePostale - cpdom - Domicile
                        sc51  = new Scanner(System.in); //demande de lire- Scanner
                        System.out.println("3. Indiquez le CodePoste de la Ville où la personne habitue"); //message pour demander le Code Postale
                        cpdom  =  sc51.nextLine(); //lecture de la variable - codePostale de la ville -domicile
                        //Vérificaiton si on a bien rentrer une  codepostal
                        regle2 =CheckChaineVide(cpdom); //on vérifie qu'on une valeur pour le code postale
                        while(regle2 ){
                           sc = new Scanner(System.in); //Pour lire
                           System.out.println("3. Indiquez le CodePoste de la Ville où la personne habitue"); //message pour demander le code Postale
                           cpdom = sc.nextLine(); //lecture de la variable - code postale dom
                           regle2 =CheckChaineVide(cpdom);
                        }
                        //Pays- paysdom - Domicile
                        sc51  = new Scanner(System.in); //demande de lire
                        System.out.println("4. Indiquez la Pays où la personne habitue"); //message pour demander le pays
                        paysdom =  sc51.nextLine(); //lecture de la variable -Pays
                        //Vérificaiton si on a bien rentrer un pays
                        regle2 =CheckChaineVide(paysdom); //on vérifie qu'on une valeur pour de pays - domicile
                        while(regle2 ){
                           sc = new Scanner(System.in); //Pour lire  - pays
                           System.out.println("4. Indiquez la Pays où la personne habitue"); //message pour demander le pays
                           paysdom = sc.nextLine(); //lecture de la variable -pays dom
                           regle2 =CheckChaineVide(paysdom);
                        }
                     }
                     else if(adr =='2')//Travaille
                     {
                        //Demande Info
                        //Information pour l'utilisateur
                        System.out.println("Nous allons vous demandez votre adresse de travaille merci de renseignez les information dans cet ordre:"); //message pour introduire les differents champs de l'adresse
                        System.out.println("1. Le nom et le numéro de la rue - 2. La ville - 3. le code postale - 4- le pays"); //message pour introduire les differents champs de l'adresse
                        //Pour le Numero et nom rue - nruedom- travaille
                        Scanner sc51 = new Scanner(System.in); //Scanner
                        System.out.println("1. Indiquez le numero et le nom de la rue "); //message pour demander le numero de la rue
                        nruetra = sc51.nextLine(); //lecture de la variable -numéro de la rue
                        //Vérification si on a bien rentrer une valeur pour la numero
                        boolean regle2 = CheckChaineVide(nruetra); //on vérifie qu'on une caleur pour la rue
                        while(regle2 ){
                           sc = new Scanner(System.in); //Pour lire le nom de la carte
                           System.out.println("1. Indiquez le numero et le nom de la rue du lieu de travaille"); //message pour demander le nom de la carte
                           nruetra  = sc.nextLine(); //lecture de la variable
                           regle2 =CheckChaineVide(nruetra );
                        }
                        //Pour la Ville - villedom - Travaille
                        sc51  = new Scanner(System.in); //demande de lire- Scanner
                        System.out.println("2. Indiquez la Ville où la personne travaille"); //message pour demander le nom de la ville
                        villetra  = sc51.nextLine(); //lecture de la variable - ville domicile
                        //Vérification si on a bien rentrer une ville
                        regle2 =CheckChaineVide(villetra); //on vérifie qu'on une valeur pour la rue
                        while(regle2 ){
                           sc = new Scanner(System.in); //Pour lire le nom de la carte
                           System.out.println("2. Indiquez la Ville où la personne habitue"); //message pour demander la ville
                           villetra = sc.nextLine(); //lecture de la variable - ville domicile
                           regle2 =CheckChaineVide(villetra);
                        }
                        //CodePostale - cpdom - Domicile
                        sc51  = new Scanner(System.in); //demande de lire- Scanner
                        System.out.println("3. Indiquez le CodePoste de la Ville où la personne habitue"); //message pour demander le Code Postale
                        cptra  =  sc51.nextLine(); //lecture de la variable - codePostale de la ville -domicile
                        //Vérificaiton si on a bien rentrer une  codepostal
                        regle2 =CheckChaineVide(cptra); //on vérifie qu'on une valeur pour le code postale
                        while(regle2 ){
                           sc = new Scanner(System.in); //Pour lire
                           System.out.println("3. Indiquez le CodePoste de la Ville où la personne habitue"); //message pour demander le code Postale
                           cptra = sc.nextLine(); //lecture de la variable - code postale dom
                           regle2 =CheckChaineVide(cptra);
                        }
                        //Pays- paysdom - Domicile
                        sc51  = new Scanner(System.in); //demande de lire
                        System.out.println("4. Indiquez la Pays où la personne habitue"); //message pour demander le pays
                        paystra =  sc51.nextLine(); //lecture de la variable -Pays
                        //Vérificaiton si on a bien rentrer un pays
                        regle2 =CheckChaineVide(paystra); //on vérifie qu'on une valeur pour de pays - domicile
                        while(regle2 ){
                           sc = new Scanner(System.in); //Pour lire  - pays
                           System.out.println("4. Indiquez la Pays où la personne habitue"); //message pour demander le pays
                           paystra = sc.nextLine(); //lecture de la variable -pays dom
                           regle2 =CheckChaineVide(paystra);
                        }
                     }
                  }
                  else{
                     System.out.println("Erreur ! le nombre d'adresse est non trouvé");
                  }
                  //3.Verif Valeur-> si besoin
                  if (nbadresses == 1) {
                     System.out.println("La nouvelle adresse est :");
                     System.out.println(nrue);
                     System.out.println(ville+"     "+cp);
                     System.out.println(pays);
                  }
                  else if(nbadresses == 2){
                     System.out.println("La nouvelle adresse de domicile est :");
                     System.out.println(nruedom);
                     System.out.println(villedom+"     "+cpdom);
                     System.out.println(paysdom);
                     System.out.println("La nouvelle adresse de travaille est :");
                     System.out.println(nruetra);
                     System.out.println(villetra+"     "+cptra);
                     System.out.println(paystra);
                  }
                  else{
                     System.out.print("Erreur Systéme");
                  }
                  //Check Adresse
                  //4.Modification de la valeur
                  if (nbadresses == 1) {
                     setADR("ADR;HOME;PREF;QUOTED-PRINTABLE:;"+ville+" "+cp+"="+pays+";"+nrue);
                     //reference : ADR;HOME;PREF;QUOTED-PRINTABLE:;Puteaux 92800=France;5 Rue de la République
                     // System.out.println( getADR());//vérification
                  }
                  else if(nbadresses==2){
                     setADRDOM("ADR;HOME;PREF;QUOTED-PRINTABLE:;"+villedom+" "+cpdom+"="+paysdom+";"+nruedom);
                     setADRTRA("ADR;WORK;PREF;QUOTED-PRINTABLE:;"+villetra+" "+cptra+"="+paystra+";"+nruetra);
                  }
                  else{
                     System.out.println("Erreur System");
                  }
                  //5.Modification du fichier tempon
                  ModifTempon(); //Modification du fichier tempon
                  //6.Lire le Fichier Tampon Modifier
                  LireFichier("./Vcards/Temp.vcf"); //lecture du fichier
                  //System.out.println("Fin test Modif ADR");
                  break;
               case 4://L'adresse de livraison - LABEL
                  //System.out.println("Debut Test Label");
                  //1.Affiche Valeur
                  //variable
                  nrueliv     = null ;
                  cpliv       = null ;
                  villeliv   = null ;
                  paysliv    = null ;
                  //on doit retranscrire l'adresse d'abord
                  nrueliv = ChecheNomRueLiv("./Vcards/Temp.vcf");
                  cpliv = ChecheCodePostaleLiv("./Vcards/Temp.vcf");
                  villeliv = ChecheNomVilleLiv("./Vcards/Temp.vcf");
                  paysliv = ChecheNomPaysLiv("./Vcards/Temp.vcf");
                  String lieu = ChecheLieuLiv("./Vcards/Temp.vcf");
                  //Affichage livraison
                  System.out.println("L'adresse de livraison actuelle est :");
                  System.out.println(nrueliv);
                  System.out.println(villeliv+"     "+cpliv);
                  System.out.println(paysliv);
                  //2.Demande valeur
                  //Demande Info
                  //Information pour l'utilisateur
                  Scanner sclieu = new Scanner(System.in);
                  System.out.println("Votre adresse de livraison est à votre: 1.Domicile 2.Travaille 3.Autre");
                  char nvlieu = sclieu.nextLine().charAt(0); //lecture de la variable adr
                  // System.out.println("Debut Check switch");
                  switch(nvlieu) {
                     case '1': //Domicile
                        lieu="HOME";
                        break;
                     case '2': //Travaille
                        lieu="WORK";
                        break;
                     case '3'://Autre
                        lieu="HOME";
                        break;
                     default:
                        System.out.println("Erreur de Saisi! la livraison se fera à votre Domicile!");
                        lieu="HOME";
                        break;
                  }
                  // System.out.println("fin Check switch");
                  System.out.println("Nous allons vous demandez votre adresse de Livraison merci de renseignez les information dans cet ordre:"); //message pour introduire les differents champs de l'adresse
                  System.out.println("1. Le nom et le numéro de la rue - 2. La ville - 3. le code postale - 4- le pays"); //message pour introduire les differents champs de l'adresse
                  //Pour le Numero et nom rue - nrueliv  - Livraison
                  Scanner sc51 = new Scanner(System.in); //Scanner
                  System.out.println("1. Indiquez le numero et le nom de la rue de livraison"); //message pour demander le numero de la rue
                  nrueliv  = sc51.nextLine(); //lecture de la variable -numéro de la rue - livraison
                  //Vérification si on a bien rentrer une valeur pour la numero
                  boolean regle2 = CheckChaineVide(nrueliv); //on vérifie qu'on une caleur pour la rue
                  while(regle2 ){
                     sc = new Scanner(System.in); //Pour lire le nom de la carte
                     System.out.println("1. Indiquez le numero et le nom de la rue "); //message pour demander le nom de la carte
                     nrueliv  = sc.nextLine(); //lecture de la variable
                     regle2 =CheckChaineVide(nrueliv);
                  }
                  //Pour la Ville - villedom - Domicile
                  sc51  = new Scanner(System.in); //demande de lire- Scanner
                  System.out.println("2. Indiquez la Ville où la personne veut être livrer"); //message pour demander le nom de la ville
                  villeliv = sc51.nextLine(); //lecture de la variable - ville livraison
                  //Vérification si on a bien rentrer une ville
                  regle2 =CheckChaineVide(villeliv); //on vérifie qu'on une valeur pour la rue
                  while(regle2 ){
                     sc = new Scanner(System.in); //Pour lire le nom de la carte
                     System.out.println("2. Indiquez la Ville où la personne veut être livrer"); //message pour demander la ville
                     villeliv = sc.nextLine(); //lecture de la variable - ville livraison
                     regle2 =CheckChaineVide(villeliv);
                  }
                  //CodePostale - cpdom - Domicile
                  sc51  = new Scanner(System.in); //demande de lire- Scanner
                  System.out.println("3. Indiquez le CodePoste de la Ville où la personne veut être livrer"); //message pour demander le Code Postale
                  cpliv  =  sc51.nextLine(); //lecture de la variable - codePostale de la ville - livraions
                  //Vérificaiton si on a bien rentrer une  codepostal
                  regle2 =CheckChaineVide(cpliv); //on vérifie qu'on une valeur pour le code postale
                  while(regle2 ){
                     sc = new Scanner(System.in); //Pour lire
                     System.out.println("3. Indiquez le CodePoste de la Ville où la personne veut être livrer"); //message pour demander le code Postale
                     cpliv = sc.nextLine(); //lecture de la variable - code postale livraison
                     regle2 =CheckChaineVide(cpliv);
                  }
                  //Pays- paysdom - Domicile
                  sc51  = new Scanner(System.in); //demande de lire
                  System.out.println("4. Indiquez la Pays où la personne habitue"); //message pour demander le pays
                  paysliv =  sc51.nextLine(); //lecture de la variable -Pays
                  //Vérificaiton si on a bien rentrer un pays
                  regle2 =CheckChaineVide(paysliv); //on vérifie qu'on une valeur pour de pays - livraison
                  while(regle2 ){
                     sc = new Scanner(System.in); //Pour lire  - pays
                     System.out.println("4. Indiquez la Pays où la personne habitue"); //message pour demander le pays
                     paysliv = sc.nextLine(); //lecture de la variable -pays dom
                     regle2 =CheckChaineVide(paysliv);
                  }
                  //3.Verif Valeur
                  System.out.println("L'adresse de livraison actuelle est :");
                  System.out.println(nrueliv);
                  System.out.println(villeliv+"     "+cpliv);
                  System.out.println(paysliv);
                  System.out.println("LABEL;QUOTED-PRINTABLE;"+lieu+";PREF:"+nrueliv+"="+villeliv+" "+cpliv+"="+paysliv);
                  nvLabel="LABEL;QUOTED-PRINTABLE;"+lieu+";PREF:"+nrueliv+"="+villeliv+" "+cpliv+"="+paysliv;
                  //4.Modifciation de la valeur
                  setLabel(nvLabel); //Modification de la valeur du nom
                  // System.out.println("Le nouveau label est:"+getLabel()); //Vérification
                  //5.Modification du fichier tempon
                  ModifTempon();
                  //6.Lire le Fichier Tampon Modifier
                  LireFichier("./Vcards/Temp.vcf"); //lecture du fichier Tempon
                  //System.out.println("Debut Test Label");
                  break;
               case 5://Le tel - TEL
                  //System.out.println("Debut test Modif Tel");
                  //1.Affiche Valeur
                  System.out.println("Le Téléphone est :"+getTel());
                  //2.Demande valeur
                  Scanner sctel = new Scanner(System.in); //demande de lire- Scanner
                  System.out.println("Saisir le nouveau Tel :"); //Demande de saision
                  String nvtel = sctel.nextLine();
                  //3.Verif Valeur
                  boolean test = CheckChaineTel(nvtel);
                  //System.out.println("le téléphone "+nvtel+"est :"+test);
                  while(!test) {
                     CheckChaineVide(tel);
                     sctel = new Scanner(System.in); //demande de lire- Scanner
                     System.out.println("Erreur! Veuillez Resaisir le nouveau Tel :"); //Demande de saision
                     nvtel = sctel.nextLine();
                     test = CheckChaineTel(nvtel);
                  }
                  //4.Modifciation de la valeur
                  setTel(nvtel); //Modification de la valeur du nom
                  //System.out.println("Le nouveau tel est:"+getTel()); //Vérification
                  //5.Modification du fichier tempon
                  ModifTempon();
                  //6.Lire le Fichier Tampon Modifier
                  LireFichier("./Vcards/Temp.vcf"); //lecture du fichier
                  //System.out.println("Fin test Modif Tel");
                  break;
               case 6://L'email - EMAIL
                  //System.out.println("Debuttest Modif Email");
                  //1.Affiche Valeur
                  System.out.println("L'adresse email actuelle est  :"+getEmail());
                  //2.Demande valeur
                  Scanner scEmail= new Scanner(System.in); //demande de lire- Scanner
                  System.out.println("Saisir le nouveau Email :"); //Demande de saision
                  String nvEmail= scEmail.nextLine();
                  //3.Verif Valeur
                  test = CheckChaineVide(nvEmail);
                  //System.out.println("le téléphone "+nvtel+"est :"+test);
                  while(test) {
                     scEmail= new Scanner(System.in); //demande de lire- Scanner
                     System.out.println("Erreur! ReSaisir le nouveau Email :"); //Demande de saisie
                     nvEmail= scEmail.nextLine();
                     test = CheckChaineVide(nvEmail);
                  }
                  //4.Modification de la valeur
                  setEmail(nvEmail); //Modification de la valeur du nom
                  //System.out.println("Le nouveau email est:"+getEmail()); //Vérification
                  //5.Modification du fichier tempon
                  ModifTempon();
                  //6.Lire le Fichier Tampon Modifier
                  LireFichier("./Vcards/Temp.vcf"); //lecture du fichier
                  //System.out.println("Fin test Modif Email");
                  break;
               case 7://Quitter la modife
                  System.out.println("Fin Programme de modification de valeur");
                  break;
               default:
                  System.out.println("Erreur! saisie non valide"); //cas si on a saisie un mauvais caractére
                  break;
            }
            //Etape 2.4.1 : Demande la nouvelle valeur
            //Etape 2.6 : Vérification - si on veut modifier une autre valeur
            Scanner scv = new Scanner(System.in); //demande de lire- Scanner
            System.out.println("Voulez-vous arretez les modifications? O.Oui N.Non"); //question si on arrete les modif
            String verifm = scv.nextLine(); //lecture de la variable- verifm
            if(verifm.equals("O")) //oui -  on arrete les modife
            {
               verif=true;
            }
            else if(verifm.equals("N"))//non - on continue
            {
               verif=false;
            }
            else{
               System.out.println("Erreur de saisie!"); //Erreur lors de la Saisie de l'utilsiateur
            }
         }while(!verif); //fin boucle verif
         //Etape 3: Vérification final- on affiche la carte temp,pour voir les modifier
         //sinon on efface le temp
         //si oui  Récriture => Copie
         boolean verif2 = false; // boolean pour vérifier
         do{
            System.out.println("Lecture du Fichier modifier"); // affichage à l'utilisateur
            LireFichier("./Vcards/Temp.vcf");//on lit le fichier tempon
            Scanner scv = new Scanner(System.in); //demande de lire- Scanner
            System.out.println("Validez vous cette fiche? O.Oui N.Non"); //question si on arrete les modif
            String verifm2 = scv.nextLine(); //lecture de la variable- verifm
            if(verifm2.equals("O")){  //si oui  Récriture => Copie
               verif2=true; //
               //on peux copier le tempon sur la carte faite
               CopieFichier( chemintampon,chemin); //fonction pour la copie de fichier
               //suprimer le fichier
               SupprimerFichier(chemintampon);
               //Affichage message de fin => modification faite
               System.out.println("Modification Faite!");//Affichage de l'action faite
            }
            else if(verifm2.equals("N")){   //sinon on efface le temp
               verif2=true;
               //suprimer le fichier
               SupprimerFichier(chemintampon);
               //Affichage message de fin => modification faite
               System.out.println("Retour du fichier initial");//Affichage de l'action faite
            }
            else{
               System.out.println("Erreur de saisie!");//Erreur de saisie
            }
         }while(!verif2);
      }
      else{
         System.out.println("Erreur! le fichier n'est pas trouvé !");
         //on arrete ici le test de modification, car on ne trouve pas le fichier
      }
   }
   /**ModifTempon
    * Permet de modifer directement le fichier Tempon
    * Utilisation =  Sur ModifCard, permet de retranscrire les donnée directement (Utilisation courante)*/
   private void ModifTempon() {
      try{
         File ff=new File("./Vcards/Temp.vcf"); // on récrie la carte tempon
         FileWriter ffw=new FileWriter(ff); // Initialise l'écriture sur le fichier
         //Ligne 1  : Begin
         ffw.write("BEGIN:VCARD");  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 2  : Version
         ffw.write("Version:3.0");  // écrire une ligne dans le fichier resultat.txt
         //Pour l'instant on travaille en création version 2.1
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 3  : FN: First Name
         ffw.write("FN:"+getFN()+" "+getN());  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 4  : N: Nom de famille
         ffw.write("N:"+getN()+";"+getFN());  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //System.out.println(getADR());
         //Ligne 5: ADR: les Adresses
         if(getADR().equals("") && getADR().isEmpty())//si l'adress standard est vide
         {
            ffw.write(getADRDOM());  // écrire une ligne dans le fichier resultat.txt
            ffw.write("\n"); // forcer le passage à la ligne

            ffw.write(getADRTRA());  // écrire une ligne dans le fichier resultat.txt
            ffw.write("\n"); // forcer le passage à la ligne
         }
         else{
            ffw.write(getADR());  // écrire une ligne dans le fichier resultat.txt
            ffw.write("\n"); // forcer le passage à la ligne
         }
         ffw.write(getLabel());  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 6:  TEL : Telephone
         ffw.write("TEL;CELL:"+getTel());  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 7:  EMAIL: email de la personne
         ffw.write("EMAIL;INTERNET:"+getEmail());  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //LIGNE 8: UID : id de la carte
         ffw.write("UID:");  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 9: END
         ffw.write("END:VCARD");  // écrire une ligne dans le fichier resultat.txt
         //FIN
         ffw.close(); // fermer le fichier à la fin des traitements
      }
      catch (Exception ignored) // cas si on a une exception
      {
         System.out.print("ERREUR SYSTEME! impossible de modifier !");
      }
   }
   /**CopieFichier
    *  fonction qui permet de copierun fichier
    * @param chemin1 : le chemin source, qu'on veut copier
    * @param chemin2 : le chemin destination, qu'on aura aprés la copie
    * @return boolean : retourne un boolean pour verifier que la copie à marcher
    *  Utilisation pur : CopieFichier("chemin1","chemin2");
    *  cette fonction est utiliser dans ModifCard() - pour modifier un fichier tempon dans un premier temps puis recopiera sur le fichier lui même*/
   public static boolean CopieFichier(String chemin1, String chemin2 ){
      //chemin 1= chemin source==> mettra dans Input , le fichier qu'on copie
      //chemin 2 = chemin destination=> Mettra dans Output, le fichier copiée
      //  tester si les source , chemin et fichier sont ok
      try (InputStream sourceFile = new FileInputStream(chemin1); // verifciation du chemin du fichier source
           OutputStream destinationFile = new FileOutputStream(chemin2))// verifciation du chemin du fichier destination
      {
         // Lecture par segment de 0.5Mo
         byte[] buffer = new byte[512 * 1024]; //on buff pour garder en mémoire le fichier exitant
         int nbLecture; //pour savoir nombre de Lecture necessaire = permet de savoir la place necessaire
         while ((nbLecture = sourceFile.read(buffer)) != -1) //tant qu'on a pas lu le fichier entier/ sauvegarde
         {
            destinationFile.write(buffer, 0, nbLecture); // on ecrite sur le fichier de destination avec l'aide du buffer
         }
      }
      //Cas si exception, erreur (probleme si un chemin, fichier n'existe pas
      catch (IOException e) //si erreur
      {
         e.printStackTrace();//affiche l'erreur
         return false; // Erreur
      }
      return true; // Résultat OK
   }
   //-----------------------------//
   //4.Fonction Gestion fichier
   /**GestionFichier
    *  permet la gestion d'une fichier
    *  Utilisation : dans le menu     **/
   public void GestionFichier(){
      System.out.print("Gestion Fichier");
      //Initialement Rien
      //Etape 1 : On affiche les carte qu'on a actullement
      System.out.println("Affichage des cartes présentes"); //on informe ce qu'on veux faire
      String chemin= "./Vcards"; //on selection le chemin qu'on doit travaille
      ListerFichiers(chemin); //permet de lister le fichier
      // A voir ou remplacer par une méthode de Recherche?
      //Etape 1.2 - demande du choix du fichier a modifier
      Scanner sc = new Scanner(System.in); //demande de lire- Scanner
      System.out.println("Quel est le fichier voulez vous gerer?"); //message pour demander le nom de la personne
      String nomcarte = sc.nextLine(); //lecture de la variable- nomcarte
      //ici on suppose que l'utilisateur écrira l'extension, le nom complet de la carte.
      //Etape 1.3 = on regarde si le nom du fichier, pour savoir si il existe bien
      boolean regle = CheckNomCarte(nomcarte,chemin); //varible pour stoker la regle
      //System.out.println(regle); //Vérification

      Scanner scg = new Scanner(System.in); //demande de lire- Scanner
      System.out.println("1.Transformer le fichier html 2.Supprimer 3.Retour"); //message pour demander le nom de la personne
      char menug =scg.nextLine().charAt(0);//pour recuperer le caractére saisi par l'utilisateur.
      //On fait un switch étant donné le nombre de choix possible
      //remarque : le menu actuel est que pour les carte !
      switch (menug) {
         case '1':
            TransformHtml(nomcarte);
            break;
         case '2':
            String chemincarte="./Vcards/"+ nomcarte;
            Scanner scv = new Scanner(System.in); //demande de lire- Scanner
            System.out.println("1.Supprimer 2.Annuler"); //message pour demander le nom de la personne
            char vald =scv.nextLine().charAt(0);//pour recuperer le caractére saisi par l'utilisateur.
            if (vald=='1'){
               SupprimerFichier(chemincarte);
               System.out.println("Suppresion du fichier : "+nomcarte);
            }
            else{
               System.out.println("Annulation ");
            }
            break;
         default:
            System.out.println("Erreur! fonction non trouvé!");
            break;
      }


   }

   /**TransformHtml(
    * Permet de transformer une carte de visite .vcf en .html
    * @param nomcarte : nom de la carte */
   public void TransformHtml(String nomcarte){
      //Transformer la carte
      //On transforme le nom de la carte pour quel soit dans le bon format.
      System.out.println("Avant:"+nomcarte);
      //System.out.println(Str.replace(".vcf", ".html"));
      String nomcartehtml = nomcarte.replace(".vcf", ".html");// on change .vcf en .html
      //System.out.println(nomcartehtml);
      //nomcarte.replace(".html", ".vcf" );
      System.out.println("Aprés:"+nomcartehtml);
      ///System.out.println("FIN TEST");
      //remarque: on suppose que l'utilisateur oublie le .vcf pour le nom de la carte
      String cheminhtml="Vcards/"+ nomcartehtml; // pour aller au chemin relatif
      String chemincarte="Vcards/"+ nomcarte;;
      System.out.println("La nouveau fichier html situe en :"+ cheminhtml); //test si etape ok - affiche le chemin relatif
      System.out.println("Le chemin de la carte es en :"+ chemincarte);
      ///Creer le fichier html
      //écritur sur ton fichier
      try{
         File ff=new File(cheminhtml); // on initialise pour savoir ou on va creer la carte
         ff.createNewFile(); // creer le fichier vierge
         FileWriter ffw=new FileWriter(ff); // Initialise l'écriture sur le fichier
         //Ligne 1 Doctype
         ffw.write("<!DOCTYPE html>");  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 2: html
         ffw.write("<html>");  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 3 : Saut de ligne
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 4 : head
         ffw.write("<head>");  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne5
         String fn = ChercheNom(chemincarte);
         String n = CherchePrenom(chemincarte);
         ffw.write("<title>Carte de visite de "+fn+" "+n+"</title>");  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 6 // écrire une ligne dans le fichier resultat.txt
         ffw.write("</head>");  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 7 : Saut de ligne
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 8  écrire une ligne dans le fichier resultat.txt
         ffw.write("<body>");  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 9 // écrire une ligne dans le fichier resultat.txt
         // on doit récuper le nom de la personne dans la Vcard

         String ecriture = "<div id=\"vcard-NOM-PRENOM\" class=\"vcard\">";
         ffw.write(ecriture);  // écrire une ligne dans le fichier resultat.txt
         //Reference : <div id="vcard-NOM-PRENOM" class="vcard">
         ffw.write("\n"); // forcer le passage à la ligne
         //Ligne 10
         ffw.write(" <a class=\"fn\" >"+fn+" "+n+"</a>");  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n<br>"); // forcer le passage à la ligne
         //Ligne 11 écrire une ligne dans le fichier resultat.txt
         String email = ChercheEmail(chemincarte);
         ffw.write("Email:<a class=\"email\"  href=\" "+email+"\" >"+email+"</a>");  // écrire une ligne dans le fichier resultat.txt
         // ref :  <a class="email" href="bob.eponge@exemple.com">bob.eponge@exemple.com</a>
         ffw.write("\n<br>"); // forcer le passage à la ligne
         //CODAGE POUR ADRESSAGE
         //Ligne 12 adresse
         ffw.write("<div class=\"adr adrdom\">Addresse Domicile:");  //12
         // ref :<div class="adr adrdom"> // 12
         ffw.write("\n<br>"); //
         //Ligne 13
         String ruedom = ChecheNomRueDom(chemincarte);
         ffw.write("<div class=\"rueadresse\">"+ruedom+"</div>");  //12
         // ref : <div class="rueadresse">10 Place du Temple Neuf</div> // 13
         ffw.write("\n"); //
         //ligne 14
         String cpdom = ChecheCodePostaleDom(chemincarte);
         ffw.write(" <span class=\"code-postal\">"+cpdom+"</span>");  //12
         // ref :  <span class="code-postal">67000</span> //14
         ffw.write("\n");
         //Ligne 15
         String villedom = ChecheNomVilleDom(chemincarte);
         ffw.write(" <span class=\"ville\">"+villedom+"</span>");
         // ref :  <span class="code-postal">67000</span> //15
         ffw.write("\n");
         //Ligne 16 - pays
         String paysdom = ChecheNomPaysDom(chemincarte);
         ffw.write(" <span class=\"ville\">"+paysdom+"</span>");
         // ref :  <span class="pays">France</span> // 16
         ffw.write("\n<br>");
         //Ligne 17
         ffw.write("</div>\n<br>");
         //Adresses Travaille
         //Ligne 18 adresse
         ffw.write("<div class=\"adr adrtra\"> Addresse de Travaille:");  //18
         // ref : <div class="adr adrtra">// 18
         ffw.write("\n<br>"); //
         //Ligne 19
         String ruetra = ChecheNomRueTra(chemincarte);
         ffw.write("<div class=\"rueadresse\">"+ruetra+"</div>");  //19
         // ref :  <div class="rueadresse">10 Place du Temple Neuf</div> //19
         ffw.write("\n"); //
         //ligne 20
         String cptra = ChecheCodePostaleTra(chemincarte);
         ffw.write(" <span class=\"code-postal\">"+cptra+"</span>");  //20
         // ref :  <span class="code-postal">67000</span> //20
         ffw.write("\n");
         //Ligne 21
         String villetra= ChecheNomVilleTra(chemincarte);
         ffw.write(" <span class=\"ville\">"+villetra+"</span>"); //21
         // ref :   <span class="ville">Strasbourg</span> //21
         ffw.write("\n");
         //Ligne 22 - pays
         String paystra = ChecheNomPaysTra(chemincarte); //22
         ffw.write(" <span class=\"ville\">"+paystra+"</span>"); //22
         // ref :   <span class="pays">France</span> //22
         ffw.write("\n");
         //Ligne 23
         ffw.write("</div>\n<br>"); //23
         //Ref : </div> // 23
         //LIvraison
         //Ligne 24 adresse
         ffw.write("<div class=\"adr adrliv\"> Addresse de Livraison:");  //24
         // ref :   <div class="adr adrliv"> //24
         ffw.write("\n"); //24
         //Ligne 25
         String rueliv = ChecheNomRueLiv(chemincarte); //25
         ffw.write("<div class=\"rueadresse\">"+rueliv+"</div>");  //25
         // ref : <div class="rueadresse">10 Place du Temple Neuf</div> // 25
         ffw.write("\n"); //25
         //ligne 26
         String cpliv = ChecheCodePostaleLiv(chemincarte);
         ffw.write(" <span class=\"code-postal\">"+cpliv+"</span>");  //<26
         // ref :  <span class="code-postal">67000</span> // 26
         ffw.write("\n");
         //Ligne 27
         String villeliv= ChecheNomVilleLiv(chemincarte);
         ffw.write(" <span class=\"ville\">"+villeliv+"</span>"); //27
         // ref :  <span class="ville">Strasbourg</span> //27
         ffw.write("\n");
         //Ligne 28 - pays
         String paysliv = ChecheNomPaysLiv(chemincarte); //28
         ffw.write(" <span class=\"ville\">"+paysliv+"</span>"); //28
         // ref :   <span class="pays">France</span> // 16
         ffw.write("\n");
         //Ligne 29:
         ffw.write("</div>");  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n<br>"); // forcer le passage à la ligne
         //Ligne 30:
         String tel = ChercheTel(chemincarte);
         ffw.write("<div class=\"tel\">Téléphone : "+tel+"</div>");  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //ref :<div class="tel">09 54 96 50 50</div>
         // ligne 31
         ffw.write("</div>");  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //ligne 32
         ffw.write("</body>");  // écrire une ligne dans le fichier resultat.txt
         ffw.write("\n"); // forcer le passage à la ligne
         //ligne 33
         ffw.write("\n"); // forcer le passage à la ligne
         //lgine 34
         ffw.write("</html>");  // écrire une ligne dans le fichier resultat.txt
         ffw.close(); // fermer le fichier à la fin des traitements
      }
      catch (Exception ignored) {
      }
   }

   /**SupprimerFichier
    * Permet de supprimer un fichier
    * @param chemin : chemin du fichier
    */
   public void SupprimerFichier(String chemin){
      File fichier = new File(chemin);
      fichier.delete();
   };
   //-----------------------------//
   //5.Fonction test - HelloWorld
   //-----------------------------//
   /**HelloWord
    *   méthode qui affiche un helloworld
    *   utilisation : pour tester et afficher Hello Wolrd -  utile pour voir ou on est - affichage*/
   public static void HelloWord() {
      System.out.println("Hello World");        //Affiche Hello World
   }
   //-----------------------------//
   //Méthodes non faites- A venir
   //-----------------------------//
   /**ExportCard() - a creer( vide)
    * permet d'exproter et d'envoyer la carte à une personne
    *  Utilisation :    Vcard.ExportCard();**/
   public void ExportCard(){
   }
   /**SupCard
    * Supprime une carte
    *  Utilisation :    Vcard.SupCard();**/
   public void SupCar(){
      //information necessaire : nomcarte - pour savoir ce la carte qu'on modifie
   }
   /**MajCarte
    *   Mettre a jour la carte pour les valeurs privée tel que la version/clé de chiffrement.
    *  Utilisation :    Vcard.MajCard();**/
   public void MajCard(){
   }
   /**AjoutCarte
    *  permet d'ajouter une carte?
    *  Utilisation :    Vcard.AjoutCarte();**/
   public void AjoutCarte(){
   }
   //-----------------------------//
   //--Méthode de Vérification ---//
   //-----------------------------//
   /**CheckChaineVide
    * vérifie que la chaine est vide est dans ce cas retourne true
    * @param chaine, la chaine qui vérifie
    * @return regle, bolean qui retourne true si on a une chaine vide
    * utilisation :
   Cette fonction est utiliser dans CreerCard et ModifCard, lorsque qu'on demande à l'utilisateur de saisir une chaine de caractére
   et forcer à rentrer une valeur
    *   dans main standard
   Scanner sc = new Scanner(System.in); //Pour lire le nom de la carte
   System.out.println("Veuillez saisir un le nom de la nouvelle carte:"); //message pour demander le nom de la carte
   String chaine = sc.nextLine(); //lecture de la variable
   boolean regle2 =CheckChaineVide(chaine); //on entre la chaine qu'on veux
   while(regle2 ){
   sc = new Scanner(System.in); //Pour lire le nom de la carte
   System.out.println("Veuillez saisir une chaine que vous voulez tester:"); //message pour demander le nom de la carte
   chaine = chaine.nextLine(); //lecture de la variable
   regle2 =CheckChaineVide(chaine);
   }//tant qu'on a rien rentrer on continue la boucle*/
   private static boolean CheckChaineVide(  String chaine) {
      // System.out.println("Debut Chaine"); //check si on est bien entrer dans la méthode
      boolean regle;
      if(chaine.equals(""))//si la chaine est vide
      {
         System.out.println("Erreur! vous n'avez rien saisie!"); //affichage de l'erreur
         regle=true;
      }
      else //si la chaine n'est pas vide
      {
         //System.out.println("Vous avez bien entrez une valeur");
         regle=false;
      }
      // System.out.println("Fin Chaine");//check si on est bien sortie
      return regle;//return le boolean
   }
   /**CheckChemin
    *  Fonction qui permet de voir le chemin absolu, si c'est un fichier etc...
    * @param chemin : le chemin relatif du fichier ou se trouve le fichier
    *  Utilisation : aucun actuellement
   dans main : CheckChemin(chemin);*/
   public static void CheckChemin(String chemin){
      //Création de l'objet File
      File f = new File(chemin); // on stocke le chemin fait
      //Affichage valeurs
      System.out.println("Chemin absolu du fichier : " + f.getAbsolutePath()); //chemin absolu
      System.out.println("Nom du fichier : " + f.getName()); // le nom du fichier
      System.out.println("Est-ce qu'il existe ? " + f.exists()); // le fichier existe?
      System.out.println("Est-ce un répertoire ? " + f.isDirectory()); //Savoir si le document est un repertoire
      System.out.println("Est-ce un fichier ? " + f.isFile()); // Savoir si le document est un fichier
   }
   /** CheckChaineSansAccents
    * Vérifie que la chaine n'a pas d'accents
    * @param texte : le texte qu'on veut vérifier
    * @return boolean : informe si la chaine est valide
    *  Utilisation :
   String texte = "blabla"; // La chaine qu'on veux teste
   boolean a = CheckChaineSansAccents( texte) ;// la méthode, renvoie un boolean
   System.out.println(a); //renvoie a, qui peut être true ou false*/
   public static boolean CheckChaineSansAccents(String texte){
      //b correspon a voir si le match entre le paterne et le texte a marcher
      boolean b = Pattern.matches("[A-Za-z]*", texte) ;
      //Explication - on vérifie juste si on a chaine standard, sans accent ici
      return b;
   }
   /** CheckChaineNomCompose
    * Pour vérifier une chaine de nom composée
    * @param  texte : le texte qu'on vérifie
    * @return boolean: b , pour vérifie que la chaine est vérfier ou non
    *  Utilisation :
   String texte = "Là-Jè"; // La chaine qu'on veux teste
   boolean a = CheckChaineNomsCompose( texte ) ;// la méthode, renvoie un boolean
   System.out.println(a); //renvoie a, qui peut être true ou false*/
   public static boolean CheckChaineNomsCompose(String texte){
      //b correspon a voir si le match entre le paterne et le texte a marcher
      boolean b = Pattern.matches("[A-Za-zÀ-ÖØ-öø-ÿœŒ'-]+",texte) ;
      // on vérifie ici pour le cas des Nom compossés, compsée d'accent.
      if (b){
         //System.out.println("C'est bien une chaine de lettre "); //check
      }
      else{
         System.out.println("ERREUR! il n'a pas que des lettres "); //check
      }
      return b;
   }
   /** CheckNomCarte
    * Check si le nom de la carte est bon , existe.
    * @param nomcarte: le nom de la carte, qu'on veux vérifie
    * @param chemin : le chemin de la carte, ou elle est situé
    * @return : b: boolean pour montre que le nom du ficheir est bon
    *  Utilisation :
   String texte = "Jean_Dupont.vcf"; // La chaine qu'on veux teste
   String chemin ="./Vcards"; //le chemin ou se situe le fichier
   boolean a = CheckNomCarte( texte, chemin) ;// la méthode, renvoie un boolean
   System.out.println(a); //renvoie a, qui peut être true ou false*/
   private static boolean CheckNomCarte(String nomcarte,String chemin ) {
      boolean b=false; // par défault, on suppose que la carte n'exite pas
      File fichier;
      //System.out.println("Question, le fichier "+ nomcarte +" existe?"); // Demande
      fichier = new File(chemin+"/"+ nomcarte);
      if (fichier.exists()) {
         //System.out.println("Le fichier existe"); // Réponse True
         b=true;
      } else {
         //System.out.println("Le fichier n'existe pas");//Réponse False
      }
      return b;
   }
   /** CheckChaineTel
    * Vérifie que la chaine est bien un téléphone, donc que la chaine est composée de 10 chiffres
    * @param Tel: , la chaine contenant le numéro de téléphone qu'on doit vérifié
    * @return boolean : b, boolean de vérification qui indique si la chaine est un télphone ou non
    *  Utilisation :
   String texte="0123456789"; //Le texte qu'on veux savoir si c'est un numéro de téléphone
   boolean a = CheckChaineTel( texte ) ;// la méthode, renvoie un boolean
   System.out.println(a); //renvoie a, qui peut être true ou false**/
   private static boolean CheckChaineTel(String Tel) {
      //b correspond a voir si le match entre le paterne et le texte a marcher
      boolean b = Pattern.matches("[0-9]{10}",Tel) ; //un téléphone est composé de 10 chiffre.
      // on vérifie ici pour le cas du tel
      if (b){
         System.out.println("C'est bien une chaine de chiffre et composée de 10 chiffre"); //check
      }
      else{
         System.out.println("ERREUR! il n'a pas que des chiffre ou manque des chiffre, recommencez! "); //check
      }
      return b;
   }
   //-----------------------------//
   //--Méthode de Recherche de Donnée ---//
   //-----------------------------------//
   /** ChercheNom
    * Cherche le nom dans une VCards
    * @param cheminfichier : le fichier, Vacard ou on cherche le nom.
    * @return nom : retourne le nom de la personne présente dans la Carte
    *  Utilisation :
   String nom = ChercheNom(chemintampon);
   System.out.println("Nom :"+nom);//affiche le nom*/
   private static String ChercheNom(String cheminfichier) {
      //  System.out.println("Test affiche nom");
      String nom = "";
      try{ // Cas si le fichier marche
         InputStream flux=new FileInputStream(  cheminfichier ); //On demande de prendre le fichier Tempon pour plus de sécurité
         InputStreamReader lecture=new InputStreamReader(flux); // on lit le fichier tempon
         BufferedReader buff=new BufferedReader(lecture); // Sauvegarde de lecture
         String ligne; // pour stocker la ligne qu'on lit*
         while ((ligne=buff.readLine())!=null){ //On ne s'arrete pas tant qu'on n'as pas de ligne vide
            if(ligne.startsWith("FN:")){ //on cherche que la ligne commence par FN
               //System.out.println(ligne); //On fait un test d'affichage
               //On doit séparer les mots
               final String SEPARATEUR = "[ ':]";
               String[] mots = ligne.split(SEPARATEUR);
               for (int i = 0; i < mots.length; i++) {
                  //System.out.println(mots[i]+i); //test
               }
               nom =    mots[1]   ; //on stocke dans la variable
            }
         }
         buff.close();
      }catch (Exception e) {
         System.out.println(e.toString()); //on affiche l'erreur !
      }
      //System.out.println("Nom :"+nom); //check final
      return nom;
   }
   /** CherchePrenom
    * Cherche le prenom dans une VCards
    * @param cheminfichier : le fichier, Vacard ou on cherche le nom.
    * @return prenom : retourne le prenom de la personne présente dans la Carte
    *  Utilisation :
   String prenom = CherchePrenom(chemintampon);
   System.out.println("Prenom:"+prenom);*/
   private static String CherchePrenom(String cheminfichier) {
      String  prenom = "";
      try{ // Cas si le fichier marche
         InputStream flux=new FileInputStream( cheminfichier ); //On demande de prendre le fichier Tempon pour plus de sécurité
         InputStreamReader lecture=new InputStreamReader(flux); // on lit le fichier tempon
         BufferedReader buff=new BufferedReader(lecture); // Sauvegarde de lecture
         String ligne; // pour stocker la ligne qu'on lit*
         while ((ligne=buff.readLine())!=null){ //On ne s'arrete pas tant qu'on n'as pas de ligne vide
            if(ligne.startsWith("FN:")){ //on cherche que la ligne commence par FN
               //System.out.println(ligne); //On fait un test d'affichage
               //On doit séparer les mots
               final String SEPARATEUR = "[ ':]";
               String[] mots = ligne.split(SEPARATEUR);
               for (int i = 0; i < mots.length; i++) {
                  //System.out.println(mots[i]+i);//test
               }
               prenom = mots[2];// Pour le prenom
            }
         }
         buff.close();
      }catch (Exception e) {
         System.out.println(e.toString());
      }
      // System.out.println( "Prenom:"+prenom);//check final
      return prenom;
   }
   /**ChercheTel
    * Cherche le telephone dans un fichier
    * @param cheminfichier : le fichier sur le quel on cherche
    * @return tel : le telphone*/
   private String ChercheTel(String  cheminfichier ) {
      String tel = null;
      try{ // Cas si le fichier marche
         InputStream flux=new FileInputStream( cheminfichier ); //On demande de prendre le fichier Tempon pour plus de sécurité
         InputStreamReader lecture=new InputStreamReader(flux); // on lit le fichier tempon
         BufferedReader buff=new BufferedReader(lecture); // Sauvegarde de lecture
         String ligne; // pour stocker la ligne qu'on lit*
         while ((ligne=buff.readLine())!=null){ //On ne s'arrete pas tant qu'on n'as pas de ligne vide
            if(ligne.startsWith("TEL;CELL")){ //on cherche que la ligne commence par FN
               //System.out.println(ligne); //On fait un test d'affichage
               //On doit séparer les mots
               final String SEPARATEUR = "[:]";
               String[] mots = ligne.split(SEPARATEUR);
               for (int i = 0; i < mots.length; i++) {
                  //System.out.println(mots[i]+i);//test
               }
               tel= mots[1];// Pour le tel
            }
         }
         buff.close();
      }catch (Exception e) {
         System.out.println(e.toString());
      }
      //System.out.println( "Tel"+tel);//check final
      return tel;
   }
   /**ChercheEmail
    * cherche l'email dans un fichier
    * @param cheminfichier : le chemin du fichier
    * @return email : l'email*/
   private String ChercheEmail(String cheminfichier) {
      String email= null;
      try{ // Cas si le fichier marche
         InputStream flux=new FileInputStream( cheminfichier ); //On demande de prendre le fichier Tempon pour plus de sécurité
         InputStreamReader lecture=new InputStreamReader(flux); // on lit le fichier tempon
         BufferedReader buff=new BufferedReader(lecture); // Sauvegarde de lecture
         String ligne; // pour stocker la ligne qu'on lit*
         while ((ligne=buff.readLine())!=null){ //On ne s'arrete pas tant qu'on n'as pas de ligne vide
            if(ligne.startsWith("EMAIL;INTERNET:")){ //on cherche que la ligne commence par FN
               //System.out.println(ligne); //On fait un test d'affichage
               //On doit séparer les mots
               final String SEPARATEUR = "[:]";
               String[] mots = ligne.split(SEPARATEUR);
               for (int i = 0; i < mots.length; i++) {
                  //System.out.println(mots[i]+i);//test
               }
               email= mots[1];// Pour le tel
            }
         }
         buff.close();
      }catch (Exception e) {
         System.out.println(e.toString());
      }
      //System.out.println( "email"+email);//check final
      return email;
   }
   /* --------ADRESSES----------*/
   /** NbrAdress
    * Indique le nombre d'adresse, commençant par ADR dans la carte
    * @param cheminfichier : le fichier ou on cherche l'information
    * @return compteur : retourne le nombre de ligne présente (attention ! ne prend pas en compte l'adresse de livraions !)
    *  Utilisation :
   int nombreadresse= NbrAdress(chemintampon);
   System.out.println("il a "+nombreadresse+" adresse"); //affiche le nombre d'adresse */
   private static int NbrAdress(String cheminfichier ) {
      int compteur = 0;
      try{ // Cas si le fichier marche
         InputStream flux=new FileInputStream( cheminfichier ); //On demande de prendre le fichier Tempon pour plus de sécurité
         InputStreamReader lecture=new InputStreamReader(flux); // on lit le fichier tempon
         BufferedReader buff=new BufferedReader(lecture); // Sauvegarde de lecture
         String ligne; // pour stocker la ligne qu'on lit*
         while ((ligne=buff.readLine())!=null    ){ //On ne s'arrete pas tant qu'on n'as pas de ligne vide
            if(ligne.startsWith("ADR;")){ //on cherche que la ligne commence par ADR
               //System.out.println(ligne); //On fait un test d'affichage
               compteur=compteur+1;
            }
         }
         buff.close();
      }catch (Exception e) {
         System.out.println(e.toString()); //Affichage de l'erreur
      }
      return compteur;
   }
   /*  Adresse Unique*/
   /** ChecheCodePostale
    * Cherche le code postale dans une VCards - marche que si il n'a qu'une adresse unique !
    * @param cheminfichier : le fichier ou on cherche l'information
    * @return cp:  le code poste
    *  Utilisation :
   String cp = ChecheCodePostale(chemintampon);
   System.out.println( " Cp :"+cp)*/
   private static String ChecheCodePostale(String cheminfichier ) {
      String cp = "";
      try{ // Cas si le fichier marche
         InputStream flux=new FileInputStream( cheminfichier ); //On demande de prendre le fichier Tempon pour plus de sécurité
         InputStreamReader lecture=new InputStreamReader(flux); // on lit le fichier tempon
         BufferedReader buff=new BufferedReader(lecture); // Sauvegarde de lecture
         String ligne; // pour stocker la ligne qu'on lit*
         while ((ligne=buff.readLine())!=null    ){ //On ne s'arrete pas tant qu'on n'as pas de ligne vide
            if(ligne.startsWith("ADR;")){ //on cherche que la ligne commence par ADR
               //System.out.println(ligne); //On fait un test d'affichage
               //On doit séparer les mots
               final String SEPARATEUR = "[ '=;]";
               String[] mots = ligne.split(SEPARATEUR);
               for (int i = 0; i < mots.length; i++) {
                  //System.out.println(mots[i]+i);
               }
               cp =    mots[5]   ; //Pour le Code post
            }
         }
         buff.close();
      }catch (Exception e) {
         System.out.println(e.toString()); //Affichage de l'erreur
      }
      //System.out.println("Code Postal :"+cp); //Check
      return cp;
   }
   /** ChecheNomPays
    *  Cherche le pays  dans une VCards - marche que si il n'a qu'une adresse unique !
    * @param cheminfichier : le fichier ou on cherche l'information
    * @return pays: retourne le pays
    *  Utilisation :
   String pays= ChecheNomPays(chemintampon);
   System.out.println( "Pays :"+pays)*/
   private static String ChecheNomPays(String cheminfichier ) {
      String pays = "";
      try{ // Cas si le fichier marche
         InputStream flux=new FileInputStream( cheminfichier ); //On demande de prendre le fichier Tempon pour plus de sécurité
         InputStreamReader lecture=new InputStreamReader(flux); // on lit le fichier tempon
         BufferedReader buff=new BufferedReader(lecture); // Sauvegarde de lecture
         String ligne; // pour stocker la ligne qu'on lit*
         while ((ligne=buff.readLine())!=null    ){ //On ne s'arrete pas tant qu'on n'as pas de ligne vide
            if(ligne.startsWith("ADR;")){ //on cherche que la ligne commence par ADR
               //System.out.println(ligne); //On fait un test d'affichage
               //On doit séparer les mots
               final String SEPARATEUR = "[ '=;]";
               String[] mots = ligne.split(SEPARATEUR);
               for (int i = 0; i < mots.length; i++) {
                  //System.out.println(mots[i]+i);
               }
               pays=    mots[6]   ; //Pour le pays
            }
         }
         buff.close();
      }catch (Exception e) {
         System.out.println(e.toString()); //Affichage de l'erreur
      }
      // System.out.println("Pays :"+pays);//check
      return pays;
   }
   /** ChecheNomVille
    *  Cherche la ville  dans une VCards - marche que si il n'a qu'une adresse unique !
    * @param cheminfichier : le fichier ou on cherche l'information
    * @return ville : retourne la ville
    *  Utilisation :
   String ville= ChecheNomVille(chemintampon);
   System.out.println( "Ville :"+ville)*/
   private static String ChecheNomVille(String cheminfichier ) {
      String ville = "";
      try{ // Cas si le fichier marche
         InputStream flux=new FileInputStream( cheminfichier ); //On demande de prendre le fichier Tempon pour plus de sécurité
         InputStreamReader lecture=new InputStreamReader(flux); // on lit le fichier tempon
         BufferedReader buff=new BufferedReader(lecture); // Sauvegarde de lecture
         String ligne; // pour stocker la ligne qu'on lit*
         while ((ligne=buff.readLine())!=null    ){ //On ne s'arrete pas tant qu'on n'as pas de ligne vide
            if(ligne.startsWith("ADR;")){ //on cherche que la ligne commence par ADR
               //System.out.println(ligne); //On fait un test d'affichage
               //On doit séparer les mots
               final String SEPARATEUR = "[ ';]";
               String[] mots = ligne.split(SEPARATEUR);
               for (int i = 0; i < mots.length; i++) {
                  //System.out.println(mots[i]+i);
               }
               ville=    mots[4]   ; //Pour la ville
            }
         }
         buff.close();
      }catch (Exception e) {
         System.out.println(e.toString()); //Affichage de l'erreur
      }
      // System.out.println("Ville :"+ville);//ville
      return ville;
   }
   /**ChecheNomRue
    *  Cherche le numéro et la rue  dans une VCards - marche que si il n'a qu'une adresse unique !
    * @param cheminfichier : le fichier ou on cherche l'information
    * @return adr :retourne len nom et le numéro de la rue
    *  Utilisation :
   String nrue= ChecheNomRue(chemintampon);
   System.out.println( "Adresse :"+nrue)*/
   private static String ChecheNomRue(String cheminfichier  ) {
      String adr = "";
      try{ // Cas si le fichier marche
         InputStream flux=new FileInputStream( cheminfichier ); //On demande de prendre le fichier Tempon pour plus de sécurité
         InputStreamReader lecture=new InputStreamReader(flux); // on lit le fichier tempon
         BufferedReader buff=new BufferedReader(lecture); // Sauvegarde de lecture
         String ligne; // pour stocker la ligne qu'on lit*

         while ((ligne=buff.readLine())!=null    ){ //On ne s'arrete pas tant qu'on n'as pas de ligne vide
            if(ligne.startsWith("ADR;")){ //on cherche que la ligne commence par ADR
               //System.out.println(ligne); //On fait un test d'affichage
               //On doit séparer les mots
               final String SEPARATEUR = "[;]";
               String[] mots = ligne.split(SEPARATEUR);
               for (int i = 0; i < mots.length; i++) {
                  // System.out.println(mots[i]+i);
               }
               adr =    mots[5]   ; //Pour le nom de la rue
            }
         }
         buff.close();
      }catch (Exception e) {
         System.out.println(e.toString()); //Affichage de l'erreur
      }
      //System.out.println("Adresse :"+adr);
      return adr;
   }
   /*  Plusieur Adresse*/
   /*  on suppose qu'on a 1 adresse de travaille et 1 adresse de Domicile
    * On ne gére pas les résidence secondaire */
   //ADRESSE DOMICIlE
   /** ChecheCodePostaleDom
    * Cherche le code postale de domicile dans une VCards
    * @param cheminfichier : le fichier ou on cherche l'information
    * @return cp:  le code postale de domicile
    *  Utilisation :
   String cpdom = ChecheCodePostaleDom(chemintampon);
   System.out.println( " Cpdom :"+cpdom)*/
   private static String ChecheCodePostaleDom(String cheminfichier ) {
      String cp = "";
      try{ // Cas si le fichier marche
         InputStream flux=new FileInputStream( cheminfichier ); //On demande de prendre le fichier Tempon pour plus de sécurité
         InputStreamReader lecture=new InputStreamReader(flux); // on lit le fichier tempon
         BufferedReader buff=new BufferedReader(lecture); // Sauvegarde de lecture
         String ligne; // pour stocker la ligne qu'on lit*

         while ((ligne=buff.readLine())!=null    ){ //On ne s'arrete pas tant qu'on n'as pas de ligne vide
            if(ligne.startsWith("ADR;HOME;")){ //on cherche que la ligne commence par ADR
               //System.out.println(ligne); //On fait un test d'affichage
               //On doit séparer les mots
               final String SEPARATEUR = "[ '=;]";
               String[] mots = ligne.split(SEPARATEUR);
               for (int i = 0; i < mots.length; i++) {
                  //System.out.println(mots[i]+i);
               }
               cp =    mots[5]   ; //Pour le Code post
            }
         }
         buff.close();
      }catch (Exception e) {
         System.out.println(e.toString()); //Affichage de l'erreur
      }
      //System.out.println("Code Postal :"+cp); //Check
      return cp;
   }
   /** ChecheNomPaysDom
    *  Cherche le pays  dans une VCards  de domicile
    * @param cheminfichier : le fichier ou on cherche l'information
    * @return pays: retourne le pays de domicile
    *  Utilisation :
   String paysdom= ChecheNomPaysDom(chemintampon);
   System.out.println( "Pays :"+paysdom)*/
   private static String ChecheNomPaysDom(String cheminfichier ) {
      String pays = "";
      try{ // Cas si le fichier marche
         InputStream flux=new FileInputStream( cheminfichier ); //On demande de prendre le fichier Tempon pour plus de sécurité
         InputStreamReader lecture=new InputStreamReader(flux); // on lit le fichier tempon
         BufferedReader buff=new BufferedReader(lecture); // Sauvegarde de lecture
         String ligne; // pour stocker la ligne qu'on lit*
         while ((ligne=buff.readLine())!=null    ){ //On ne s'arrete pas tant qu'on n'as pas de ligne vide
            if(ligne.startsWith("ADR;HOME;")){ //on cherche que la ligne commence par ADR
               //System.out.println(ligne); //On fait un test d'affichage
               //On doit séparer les mots
               final String SEPARATEUR = "[ '=;]";
               String[] mots = ligne.split(SEPARATEUR);
               for (int i = 0; i < mots.length; i++) {
                  //System.out.println(mots[i]+i);
               }
               pays=    mots[6]   ; //Pour le pays
            }
         }
         buff.close();
      }catch (Exception e) {
         System.out.println(e.toString()); //Affichage de l'erreur
      }
      // System.out.println("Pays :"+pays);//check
      return pays;
   }
   /** ChecheNomVilleDom
    *  Cherche la ville de domicile
    * @param cheminfichier : le fichier ou on cherche l'information
    * @return ville : retourne la ville de domicile
    *  Utilisation :
   String villedom= ChecheNomVilleDom(chemintampon);
   System.out.println( "Ville :"+villedom)*/
   private static String ChecheNomVilleDom(String cheminfichier ) {
      String ville = "";
      try{ // Cas si le fichier marche
         InputStream flux=new FileInputStream( cheminfichier ); //On demande de prendre le fichier Tempon pour plus de sécurité
         InputStreamReader lecture=new InputStreamReader(flux); // on lit le fichier tempon
         BufferedReader buff=new BufferedReader(lecture); // Sauvegarde de lecture
         String ligne; // pour stocker la ligne qu'on lit*
         while ((ligne=buff.readLine())!=null    ){ //On ne s'arrete pas tant qu'on n'as pas de ligne vide
            if(ligne.startsWith("ADR;HOME;")){ //on cherche que la ligne commence par ADR
               //System.out.println(ligne); //On fait un test d'affichage
               //On doit séparer les mots
               final String SEPARATEUR = "[ ';]";
               String[] mots = ligne.split(SEPARATEUR);
               for (int i = 0; i < mots.length; i++) {
                  //System.out.println(mots[i]+i);
               }
               ville=    mots[4]   ; //Pour la ville
            }
         }
         buff.close();
      }catch (Exception e) {
         System.out.println(e.toString()); //Affichage de l'erreur
      }
      // System.out.println("Ville :"+ville);//ville
      return ville;
   }
   /**ChecheNomRueDom
    * Récupére uniquement l'adresse de Domicile
    * @param cheminfichier  : le fichier ou on cherche l'information
    * @return adr : l'adresse de domicile
    *  Utilisation :
   String nruedom= hecheNomRueDom(chemintampon);
   System.out.println( "Ville :"+nruedom)*/
   private static String ChecheNomRueDom(String cheminfichier) {
      String adr = "";
      try{ // Cas si le fichier marche
         InputStream flux=new FileInputStream( cheminfichier ); //On demande de prendre le fichier Tempon pour plus de sécurité
         InputStreamReader lecture=new InputStreamReader(flux); // on lit le fichier tempon
         BufferedReader buff=new BufferedReader(lecture); // Sauvegarde de lecture
         String ligne; // pour stocker la ligne qu'on lit*
         while ((ligne=buff.readLine())!=null    ){ //On ne s'arrete pas tant qu'on n'as pas de ligne vide
            if(ligne.startsWith("ADR;HOME;")){ //on cherche que la ligne commence par ADR
               //System.out.println(ligne); //On fait un test d'affichage
               //On doit séparer les mots
               final String SEPARATEUR = "[;]";
               String[] mots = ligne.split(SEPARATEUR);
               for (int i = 0; i < mots.length; i++) {
                  // System.out.println(mots[i]+i);
               }
               adr =    mots[5]   ; //Pour le nom de la rue
            }
         }
         buff.close();
      }catch (Exception e) {
         System.out.println(e.toString()); //Affichage de l'erreur
      }
      // System.out.println("L'adresse de domicille est :"+adr);
      return adr;
   }
   //ADRESSE Travaille
   /** ChecheCodePostaleTra
    * Cherche le code postale de travaille dans une VCards
    * @param cheminfichier : le fichier ou on cherche l'information
    * @return cp:  le code postale de travaille
    *  Utilisation :
   String cptra = ChecheCodePostaleTra(chemintampon);
   System.out.println( " Cpdom :"+cptra)*/
   private static String ChecheCodePostaleTra(String cheminfichier ) {
      String cp = "";
      try{ // Cas si le fichier marche
         InputStream flux=new FileInputStream( cheminfichier ); //On demande de prendre le fichier Tempon pour plus de sécurité
         InputStreamReader lecture=new InputStreamReader(flux); // on lit le fichier tempon
         BufferedReader buff=new BufferedReader(lecture); // Sauvegarde de lecture
         String ligne; // pour stocker la ligne qu'on lit*
         while ((ligne=buff.readLine())!=null    ){ //On ne s'arrete pas tant qu'on n'as pas de ligne vide
            if(ligne.startsWith("ADR;WORK;")){ //on cherche que la ligne commence par ADR
               //System.out.println(ligne); //On fait un test d'affichage
               //On doit séparer les mots
               final String SEPARATEUR = "[ '=;]";
               String[] mots = ligne.split(SEPARATEUR);
               for (int i = 0; i < mots.length; i++) {
                  //System.out.println(mots[i]+i);
               }
               cp =    mots[5]   ; //Pour le Code post
            }
         }
         buff.close();
      }catch (Exception e) {
         System.out.println(e.toString()); //Affichage de l'erreur
      }
      //System.out.println("Code Postal :"+cp); //Check
      return cp;
   }
   /** ChecheNomPaysTra
    *  Cherche le pays  dans une VCards  de travaille
    * @param cheminfichier : le fichier ou on cherche l'information
    * @return pays: retourne le pays de travaille
    *  Utilisation :
   String paystra= ChecheNomPaysTra(chemintampon);
   System.out.println( "Pays :"+paystra)
    */
   private static String ChecheNomPaysTra(String cheminfichier ) {
      String pays = "";
      try{ // Cas si le fichier marche
         InputStream flux=new FileInputStream( cheminfichier ); //On demande de prendre le fichier Tempon pour plus de sécurité
         InputStreamReader lecture=new InputStreamReader(flux); // on lit le fichier tempon
         BufferedReader buff=new BufferedReader(lecture); // Sauvegarde de lecture
         String ligne; // pour stocker la ligne qu'on lit*
         while ((ligne=buff.readLine())!=null    ){ //On ne s'arrete pas tant qu'on n'as pas de ligne vide
            if(ligne.startsWith("ADR;WORK;")){ //on cherche que la ligne commence par ADR
               //System.out.println(ligne); //On fait un test d'affichage
               //On doit séparer les mots
               final String SEPARATEUR = "[ '=;]";
               String[] mots = ligne.split(SEPARATEUR);
               for (int i = 0; i < mots.length; i++) {
                  //System.out.println(mots[i]+i);
               }
               pays=    mots[6]   ; //Pour le pays
            }
         }
         buff.close();
      }catch (Exception e) {
         System.out.println(e.toString()); //Affichage de l'erreur
      }
      System.out.println("Pays :"+pays);//check
      return pays;
   }
   /** ChecheNomVilleTra
    *  Cherche la ville de travaille
    * @param cheminfichier : le fichier ou on cherche l'information
    * @return ville : retourne la ville de travaille
    *  Utilisation :
   String villetra= ChecheNomVilleTra(chemintampon);
   System.out.println( "Ville :"+villetra)
    */
   private static String ChecheNomVilleTra(String cheminfichier ) {
      String ville = "";
      try{ // Cas si le fichier marche
         InputStream flux=new FileInputStream( cheminfichier ); //On demande de prendre le fichier Tempon pour plus de sécurité
         InputStreamReader lecture=new InputStreamReader(flux); // on lit le fichier tempon
         BufferedReader buff=new BufferedReader(lecture); // Sauvegarde de lecture
         String ligne; // pour stocker la ligne qu'on lit*
         while ((ligne=buff.readLine())!=null    ){ //On ne s'arrete pas tant qu'on n'as pas de ligne vide
            if(ligne.startsWith("ADR;WORK;")){ //on cherche que la ligne commence par ADR
               //System.out.println(ligne); //On fait un test d'affichage
               //On doit séparer les mots
               final String SEPARATEUR = "[ ';]";
               String[] mots = ligne.split(SEPARATEUR);
               for (int i = 0; i < mots.length; i++) {
                  //System.out.println(mots[i]+i);
               }
               ville=    mots[4]   ; //Pour la ville
            }
         }
         buff.close();
      }catch (Exception e) {
         System.out.println(e.toString()); //Affichage de l'erreur
      }
      System.out.println("Ville :"+ville);//ville
      return ville;
   }
   /**ChecheNomRueTra
    * récupére uniquement l'adresse et le numéro de la rue de Travaille
    * @param cheminfichier : chemin du fichier
    * @return adr: retourne l'adresse- notamment l'adresse et le numéro de la rue
    *                  *  Utilisation :
    * 			     		String nruetra= ChecheNomRueDom(chemintampon);
    * 			     		System.out.println( "Adresse:"+nruetra=)*/
   private static String ChecheNomRueTra(String cheminfichier) {
      String adr = "";
      try{ // Cas si le fichier marche
         InputStream flux=new FileInputStream( cheminfichier ); //On demande de prendre le fichier Tempon pour plus de sécurité
         InputStreamReader lecture=new InputStreamReader(flux); // on lit le fichier tempon
         BufferedReader buff=new BufferedReader(lecture); // Sauvegarde de lecture
         String ligne; // pour stocker la ligne qu'on lit*

         while ((ligne=buff.readLine())!=null    ){ //On ne s'arrete pas tant qu'on n'as pas de ligne vide
            if(ligne.startsWith("ADR;WORK;")){ //on cherche que la ligne commence par ADR
               //System.out.println(ligne); //On fait un test d'affichage
               //On doit séparer les mots
               final String SEPARATEUR = "[;]";
               String[] mots = ligne.split(SEPARATEUR);
               for (int i = 0; i < mots.length; i++) {
                  // System.out.println(mots[i]+i);
               }
               adr =    mots[5]   ; //Pour le nom de la rue
            }
         }
         buff.close();
      }catch (Exception e) {
         System.out.println(e.toString()); //Affichage de l'erreur
      }
      //System.out.println("L'adresse de travaille est :"+adr); //check
      return adr;
   }
   //Adresse Label - livraison
   /**ChecheLieuLiv
    * cherche la catégorie/type de lieu de la livraison   (Home/work)
    * @param cheminfichier : fichier ou on cherche
    * @return adr : retourne le type de lieu de la livraison*/
   private String ChecheLieuLiv(String cheminfichier){
      String adr = "";
      try{ // Cas si le fichier marche
         InputStream flux=new FileInputStream( cheminfichier ); //On demande de prendre le fichier Tempon pour plus de sécurité
         InputStreamReader lecture=new InputStreamReader(flux); // on lit le fichier tempon
         BufferedReader buff=new BufferedReader(lecture); // Sauvegarde de lecture
         String ligne; // pour stocker la ligne qu'on lit*
         while ((ligne=buff.readLine())!=null    ){ //On ne s'arrete pas tant qu'on n'as pas de ligne vide
            if(ligne.startsWith("LABEL;QUOTED-PRINTABLE;")){ //on cherche que la ligne commence par LABEL
               //System.out.println(ligne); //On fait un test d'affichage
               //On doit séparer les mots
               final String SEPARATEUR = "[';]";
               String[] mots = ligne.split(SEPARATEUR);
               for (int i = 0; i < mots.length; i++) {
                  //System.out.println(mots[i]+i);
               }
               adr =    mots[2]   ; //Pour le nom de la rue
            }
         }
         buff.close();
      }catch (Exception e) {
         System.out.println(e.toString()); //Affichage de l'erreur
      }
      //System.out.println("L'adresse de livraison est :"+adr); //check
      return adr;
   }
   /**ChecheNomRueLiv
    * cherche le nom de la rue de livraison dans le fichier en quetion
    * @param cheminfichier : le fichier ou on cherche
    * @return adr : la chaine avec le nom de la rue
    */
   private String ChecheNomRueLiv(String cheminfichier){
      String adr = "";
      try{ // Cas si le fichier marche
         InputStream flux=new FileInputStream( cheminfichier ); //On demande de prendre le fichier Tempon pour plus de sécurité
         InputStreamReader lecture=new InputStreamReader(flux); // on lit le fichier tempon
         BufferedReader buff=new BufferedReader(lecture); // Sauvegarde de lecture
         String ligne; // pour stocker la ligne qu'on lit*
         while ((ligne=buff.readLine())!=null    ){ //On ne s'arrete pas tant qu'on n'as pas de ligne vide
            if(ligne.startsWith("LABEL;QUOTED-PRINTABLE;")){ //on cherche que la ligne commence par LABEL
               //System.out.println(ligne); //On fait un test d'affichage
               //On doit séparer les mots
               final String SEPARATEUR = "[';'=':]";
               String[] mots = ligne.split(SEPARATEUR);
               for (int i = 0; i < mots.length; i++) {
                  //System.out.println(mots[i]+i);
               }
               adr =    mots[4]   ; //Pour le nom de la rue
            }
         }
         buff.close();
      }catch (Exception e) {
         System.out.println(e.toString()); //Affichage de l'erreur
      }
      // System.out.println("L'adresse de livraison est :"+adr); //check
      return adr;
   }
   /**ChecheNomVilleLiv
    * cherche le nom de la ville de livraison dans le fichier en question
    * @param cheminfichier : le fichier ou on cherche
    * @return adr : la chaine avec le nom de la ville*/
   private String ChecheNomVilleLiv(String cheminfichier){
      String adr = "";
      try{ // Cas si le fichier marche
         InputStream flux=new FileInputStream( cheminfichier ); //On demande de prendre le fichier Tempon pour plus de sécurité
         InputStreamReader lecture=new InputStreamReader(flux); // on lit le fichier tempon
         BufferedReader buff=new BufferedReader(lecture); // Sauvegarde de lecture
         String ligne; // pour stocker la ligne qu'on lit*
         while ((ligne=buff.readLine())!=null    ){ //On ne s'arrete pas tant qu'on n'as pas de ligne vide
            if(ligne.startsWith("LABEL;QUOTED-PRINTABLE;")){ //on cherche que la ligne commence par LABEL
               //System.out.println(ligne); //On fait un test d'affichage
               //On doit séparer les mots
               final String SEPARATEUR = "[';'=':]";
               String[] mots = ligne.split(SEPARATEUR);
               for (int i = 0; i < mots.length; i++) {
                  //System.out.println(mots[i]+i);
               }
               adr =    mots[5]   ; //Pour le nom de la rue
               final String SEPARATEUR2 = "[ ]";
               mots = adr.split(SEPARATEUR2);
               for (int i = 0; i < mots.length; i++) {
                  //System.out.println(mots[i]+i);
               }
               adr=    mots[0];
            }
         }
         buff.close();
      }catch (Exception e) {
         System.out.println(e.toString()); //Affichage de l'erreur
      }
      // System.out.println("Le nom de la ville de livraison est :"+adr); //check
      return adr;
   }
   /**ChecheNomPaysLiv
    * cherche le nom du pays de livraison dans le fichier en question
    * @param cheminfichier : le fichier ou on cherche
    * @return adr : la chaine avec le nom du pays*/
   private String ChecheNomPaysLiv(String cheminfichier){
      String adr = "";
      try{ // Cas si le fichier marche
         InputStream flux=new FileInputStream( cheminfichier ); //On demande de prendre le fichier Tempon pour plus de sécurité
         InputStreamReader lecture=new InputStreamReader(flux); // on lit le fichier tempon
         BufferedReader buff=new BufferedReader(lecture); // Sauvegarde de lecture
         String ligne; // pour stocker la ligne qu'on lit*
         while ((ligne=buff.readLine())!=null    ){ //On ne s'arrete pas tant qu'on n'as pas de ligne vide
            if(ligne.startsWith("LABEL;QUOTED-PRINTABLE;")){ //on cherche que la ligne commence par LABEL
               // System.out.println(ligne); //On fait un test d'affichage
               //On doit séparer les mots
               final String SEPARATEUR = "[';'=':]";
               String[] mots = ligne.split(SEPARATEUR);
               for (int i = 0; i < mots.length; i++) {
                  // System.out.println(mots[i]+i);
               }
               adr =    mots[6]   ; //Pour le nom de la rue
            }
         }
         buff.close();
      }
      catch (Exception e) {
         System.out.println(e.toString()); //Affichage de l'erreur
      }
      // System.out.println("Le pays de livraison est  :"+adr); //check
      return adr;
   }
   /**ChecheCodePostaleLiv
    * cherche le code postale de livraison dans le fichier en question
    * @param cheminfichier : le fichier ou on cherche
    * @return adr : la chaine avec le code postale*/
   private String ChecheCodePostaleLiv(String cheminfichier){
      String adr = "";
      try{ // Cas si le fichier marche
         InputStream flux=new FileInputStream( cheminfichier ); //On demande de prendre le fichier Tempon pour plus de sécurité
         InputStreamReader lecture=new InputStreamReader(flux); // on lit le fichier tempon
         BufferedReader buff=new BufferedReader(lecture); // Sauvegarde de lecture
         String ligne; // pour stocker la ligne qu'on lit*
         while ((ligne=buff.readLine())!=null    ){ //On ne s'arrete pas tant qu'on n'as pas de ligne vide
            if(ligne.startsWith("LABEL;QUOTED-PRINTABLE;")){ //on cherche que la ligne commence par LABEL
               //System.out.println(ligne); //On fait un test d'affichage
               //On doit séparer les mots
               final String SEPARATEUR = "[';'=':]";
               String[] mots = ligne.split(SEPARATEUR);
               for (int i = 0; i < mots.length; i++) {
                  //System.out.println(mots[i]+i);
               }
               adr =    mots[5]   ; //Pour le nom de la rue
               final String SEPARATEUR2 = "[ ]";
               mots = adr.split(SEPARATEUR2);
               for (int i = 0; i < mots.length; i++) {
                  //System.out.println(mots[i]+i);
               }
               adr=    mots[1];
            }
         }
         buff.close();
      }catch (Exception e) {
         System.out.println(e.toString()); //Affichage de l'erreur
      }
      // System.out.println("Le code Postale de livraison est  :"+adr); //check
      return adr;
   }
   //-----------------------------//
   //---Méthode Générale - Générer par IDE---//
   //-----------------------------//
   /**Equals
    * Equals = pour valider que la carte est identique
    * @param o : objet
    * @return tous les parametre*/
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Vcard vcards = (Vcard) o;
      return UID == vcards.UID &&
              ADR.equals(vcards.ADR) &&
              Objects.equals(Email, vcards.Email) &&
              FN.equals(vcards.FN) &&
              Objects.equals(Label, vcards.Label) &&
              N.equals(vcards.N) &&
              Objects.equals(Nickname, vcards.Nickname) &&
              Objects.equals(Rev, vcards.Rev) &&
              Objects.equals(Role, vcards.Role) &&
              Tel.equals(vcards.Tel) &&
              Objects.equals(Title, vcards.Title) &&
              Objects.equals(URL, vcards.URL) &&
              Version.equals(vcards.Version) &&
              Objects.equals(Note, vcards.Note) &&
              Objects.equals(SortString, vcards.SortString) &&
              Objects.equals(Key, vcards.Key);
   }
   //toString = pour afficahge des valeurs
   public String toString() {
      return "Vcards{" +
              "ADR='" + ADR + '\'' +
              ", Email='" + Email + '\'' +
              ", FN='" + FN + '\'' +
              ", Label='" + Label + '\'' +
              ", N='" + N + '\'' +
              ", Tel='" + Tel + '\'' +
              ", URL='" + URL + '\'' +
              ", Version='" + Version + '\'' +
              ", UID=" + UID +
              '}';
   }
   //-----------------------------//
   //---Getteurs---//
   //-----------------------------//
   /**getADR
    * pour avoir l'adresse
    * @return ADR : adresses*/
   public String getADR() {
      return ADR;
   }
   /**getEmail
    * pour avoir l'email
    * @return Email : adress*/
   public String getEmail() {
      return Email;
   }
   /**getFN
    * pour avoir le FN, prenom
    * @return FN : prenom*/
   public String getFN() {
      return FN;
   }
   /**getLabel
    * pour avoir le Labe, l'adresse de livraison
    * @return label : l'adresse de livraison*/
   public String getLabel() {
      return Label;
   }
   /**getN
    * pour avoir N, le nom
    * @return N: nom*/
   public String getN() {
      return N;
   }
   /**getNickname
    * pour avoir Nickname : le surnom
    * @return Nickname;*/
   public String getNickname() {
      return Nickname;
   }
   /**getPhoto
    * pour avoir la photo
    * @return Photo*/
   public String getPhoto() {
      return Photo;
   }
   /**getRev
    * pour avoir le Rev, mise a jour de la carte
    * @return Rev : mise a jour de la carte*/
   public Date getRev() {
      return Rev;
   }
   /**getRole
    * pour avoir le role
    * @return Role*/
   public String getRole() {
      return Role;
   }
   /**getTel
    * pour avoir le telephone
    * @return Tel : telphone*/
   public String getTel() {
      return Tel;
   }
   /**getTitle
    *Pour le titre
    * @return Title : titre*/
   public String getTitle() {
      return Title;
   }
   /**getUID
    *retourne le id
    * @return UID : ID de la carte*/
   public int getUID() {
      return UID;
   }
   /**getURL
    * pour le url
    * @return URL : lien de mise a jour*/
   public String getURL() {
      return URL;
   }
   /**getKey
    * pour la clé
    * @return Key : clé de chiffrement*/
   public String getKey() {
      return Key;
   }
   /**getNote
    * pour la description
    * @return Note: note/description de la vcard
    */
   public String getNote() {
      return Note;
   }
   /** getNomCarte
    * pour le nom de la carte
    * @return NomCarte : nom de la carte*/
   public String getNomCarte() {
      return NomCarte;
   }
   /**getSortString
    * pour le trie
    * @return SortString : trie*/
   public String getSortString() {
      return SortString;
   }
   /**getADRDOM
    * pour l'adresse dom
    * @return ADRDOM : adresse de domicile*/
   public String getADRDOM() {
      return ADRDOM;
   }
   /**getADRTRA
    * pour l'adresse de travaille
    * @return ADRTRA : adresse de travaille*/
   public String getADRTRA() {
      return ADRTRA;
   }
   //-----------------------------//
   //---Setteurs---//
   //-----------------------------//
   /** setADR
    * modifie l'adresse
    * @param ADR : adresse*/
   protected void setADR(String ADR) {
      this.ADR = ADR;
   }
   /** setEmail
    * modifie l'email
    *  @param email: email
    */
   protected void setEmail(String email) {
      Email = email;
   }
   /**setFN
    * modifie le prenom
    * @param FN : prenom*/
   protected void setFN(String FN) {
      this.FN = FN;
   }
   /**setLabel
    * modifie l'adresse de livraison
    * @param label : l'adresse de livraison*/
   protected void setLabel(String label) {
      Label = label;
   }
   /**setN
    * modifie le nom
    * @param n : nom*/
   public void setN(String n) {
      N = n;
   }
   /**setNickname
    * modifie le surnom
    * @param nickname: surnom*/
   protected void setNickname(String nickname) {
      Nickname = nickname;
   }
   /**setNomCarte
    * modifie le nom de la carte
    * @param nomCarte: nom de la carte*/
   protected void setNomCarte(String nomCarte) {
      NomCarte = nomCarte;
   }
   /**setNote
    * modifie la description de la carte
    * @param note: description de la carte*/
   protected void setNote(String note) {
      Note = note;
   }
   /**setTel
    * modifie le telephone
    * @param tel : telephone*/
   protected void setTel(String tel) {
      Tel = tel;
   }
   /**setTitle
    * modifie le titre
    * @param  title : titre*/
   protected void setTitle(String title) {
      Title = title;
   }
   /** setPhoto
    * modifie la photo
    * @param photo : photo*/
   protected void setPhoto(String photo) {
      Photo = photo;
   }
   /** setKey
    * modifie la clé de chiffrement
    * @param key : la clé de chiffrement*/
   protected void setKey(String key) {
      Key = key;
   }
   /** setRev
    * modifie la date de mise à jour
    * @param  rev : la clé de chiffrement*/
   protected void setRev(Date rev) {
      Rev = rev;
   }
   /** setRole
    * modifie le role
    * @param  role :  role*/
   protected void setRole(String role) {
      Role = role;
   }
   /** setSortString
    * modifie le trie
    * @param  sortString :  trie*/
   public void setSortString(String sortString) {
      SortString = sortString;
   }
   /** setUID
    * modifie l'id'
    * @param   UID:  ID*/
   protected void setUID(int UID) {
      this.UID = UID;
   }
   /**setURL
    * modifie l'URL del a carte
    * @param  URL:  URL)*/
   protected void setURL(String URL) {
      this.URL = URL;
   }
   /**setVersion
    * modifie la version de la carte
    * @param  version:  version de la carte*/
   public void setVersion(String version) {
      Version = version;
   }
   /**setADRDOM
    * modifie l'adresse de domicile
    * @param ADRDOM : l'adresse de domicile*/
   public void setADRDOM(String ADRDOM) {
      this.ADRDOM = ADRDOM;
   }
   /**setADRTRA
    * modifie l'adresse de travaille
    * @param ADRTRA : l'adresse de travaille*/
   public void setADRTRA(String ADRTRA) {
      this.ADRTRA = ADRTRA;
   }
}