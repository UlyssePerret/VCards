/*Changelog
Creation le 23 janvier 2020
Mise a jour : Mecredi 5 Février
Auteur : Ulysse, Amadou, Nickola
Changelog
1.0: Versionning et Mise en commun (package) + creation (23 Janvier)
1.0.1 -Mercredi 29 2020  par amadou
Mise a jour CreerCard - mise en commun avec le travaille ulysse
et ajout des méthodes:
CheckChemin
CopieFichier
ChecheNomRue
LireFichier

1.0.2  par ulysse - jeudi 30
Mise en forme - structure du document
Ajout des fonctions suivante :
CreerCard -> Permet de crée une card
Méthode de Vérification : 
CheckChaineTel-CheckChaineSansAccents-
Méthode de Recherche de donné :
ChercheNom-CherchePrenom- CheckChaineNomsCompose-CheckNomCarte
Pour les adresse
Générale : NbrAdress
Unique :(si la carte n'a qu'une addresse)
ChecheCodePostale-ChecheNomPays-ChecheNomVille-ChecheNomRue-CheckChaineTel
Plusieur adresse
Domicile :
ChecheCodePostaleDom-ChecheNomPaysDom- ChecheNomVilleDom-ChecheNomRueDom
Travaille : 
ChecheCodePostaleTra-ChecheNomPaysTra-ChecheNomVilleTra-ChecheNomRueTra
Autres fonction:
ListerFichiers

1.1 Mise commun - sur la clé
1.1.1
CreationCard - téléphone
1.1.2
Mise a jour javadoc
getteur + setteur
Continuer la creation
ajout méthode : CheckChaineVide -> pour vérifier si l'utilisateur n'a rien rentrer
Creation - finition + vérifation
- tel (avec méthode CheckChaineTel)
- email (check si non vide)

V1.1.3 - Samedi 1 Février
Ajout de la fonction Menu

V1.1.4.1 - Mecredi 5 Février
Tentative de correction scanner
Constructeur Vcard + Main corriger
Méthode ModifCard
    - Stocke valeur N,FN
    - stocke ADR, ADRDOM, ADRTRA
Ajout des parametres, get et setter :
ADRDOM (param ) => getADRDOM et setADRDOM
ADRTRA (param) => getADRTRA et setADRTRA
V1.1.4.2
Travail sur le label
- recuperation des valeur avec ChecheCodePostaleLiv-ChecheNomPaysLiv-ChecheNomVilleLiv-ChecheNomRueLiv
ChercheLieuLiv => savoir si le label est pour work/home
Verification des stockage du label
Mise a jour Javadoc + exception

V1.1.4.3- jeudi 6 Février
Avancement ModifCard
Stockage du  Tel et Email
Dubut Modification du nom

Méthode crée
    ChercheTel-ChecheEmail
V1.1.5 jeudi 6 Février
FInition et nom modifier sur fichier Temp

V.1.1.5.1 - Samedi 7 février
Rangement pour retrouver les méthodes en ordre d'exécution + vérification

V1.1.6.0 - Mecredi 12 Fevrier
Relecture
presentation à mes collégues
Début travaille sur modif prenom

V1.1.6.1 (matin)
Travaille modif prenom +adresse

V1.1.6.2 (aprem)
travaille adresse

V1.1.6.3 Jeudi 13 Fevrier
travaille adresse
Méthode  ModifTempon => fonction premettant de récrire sur le fichier tampon
Finition et Vérfication qu'on peux modifier les adress (domicile ou travaille)


V1.1.6.4
AJOUT Version
+ Explication changelot -> Amadou prend Gestion Fichier

V1.1.7
Dernier Modif du jour

===========
V1.2 - Mise en commun ?

A FAIRE avant V2
CreerCard (Arret -> 2 bug vu (Saisi caractere + chemin (console ) (necessite LireCard/AffCard) )
ModifCard (Ulysse) (en cours)
LireCard (Nickola)
AffichCard(Nickola)
GestionFichier(Amadou)

V2 - Toutes les fonction fini Vcard Fini
 */

//---Import----//
//-----------------------------//

import java.io.*;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

/**Vcard
 * La classe permetant la gestion d'une carte
 * @author ulysse, amadou ly
 * @version 1.1.3
 */
public class Vcard  {

	//---Variables - Parametres----//
  	//-----------------------------//
	/**ADR
     * Adresse de la personne
     */
    public String ADR="";

    /**ADRDOM
     * Adresse du domicile la personne
     */
    public String ADRDOM="";

    /**ADRTRA
     * Adresse du travaille la personne
     */
    public String ADRTRA="";

    /**EMAIL
     * email de la personne*/
    public String Email;

    /**FN
     * nom de famille la personne*/
    public String FN;

    /**Label
     * L'adresse de livraison de la personne
     */
    public String Label;

    /**N
     * prenom de la personne
     */
    public String N;

    /**Nickname
     * surnome de la personne
     */
    public String Nickname;

    /** PHOTO
     * la photo de profile
     */
    public String Photo;

    /**REV
     *  date de mise à jour de la carte
     */
    public Date Rev;

    /**Role
     * la fonction dans l'organisation ou la profesion de la personne
     */
    public String Role;

    /**TEL
     * téléphone de la personne
     */
    public String Tel;

    /**Title
     * titre du poste
     */
    public String Title;

    /** URL
     * lien de mise a jour de la carte
     */
    public String URL;

    /**Version
     * la version de la carte
     */
    protected String Version;

    /**Note
     * commentaire lié à la carte
     */
    protected String Note;

    /**SortString
     * Permet de trier la carte par nom
     */
    protected String SortString;

    /**Key
     * clé de chiffrement de la clé
     */
    private String Key;

    /**UID
     * ID de la carte
     */
    private int UID;

    //Parametre crée pour les méthode
    /**NomCarte
     * le nom de la carte
     */
    public String NomCarte;

    //--Constructeur---//
    //Rappel : un constructeur est le programme qui se lance en premier quand on l'appelle (Dans une interface ou dans le main)
    // ici le constructeur Montre le lancement du programme et lance un menu d'interface

    /**Vcard
     * Constructeur pour le main, lance un menu
     * @throws IOException
     * utilisation dans le main :
            public static void main(String[] args) throws IOException {
                Vcard vcard = new Vcard(); //permet de lancer le constructeur Vcard et donc le menu contenu
            }
     */
    public  Vcard() throws IOException {
        System.out.println("Lancement du programme");//Information qu'on a lancé le programme
        Menu(); //la méthode Menu affichera un menu à choix = voir Menu()
    }

    //--Méthode  ---//
  	//-----------------------------//
    //INTERFACE UTILISATEUR -> Menu
    /**Menu
     * description: Affiche un menu, avec le choix des fonctions déja faite
     * @throws IOException
     * Utilisation: Cette fonction est la base du programme, il est donc dans le constructeur Vcard
        System.out.println("Lancement du programme");
        Menu()
     */
	public void Menu() throws IOException {
	    //Affichage de démarage du menu
		System.out.println("Projet Vcard et Evenement");
		System.out.println("Que voulez vous faire?");
		//Affichage du Menu
		System.out.println("1.Lire une carte");
		System.out.println("2.Créer une carte");
		System.out.println("3.Modifier une carte");
		System.out.println("4.Fonction Test - helloWorld");
		System.out.println("5.Quitter L'appli");
		
		Scanner scmenu= new Scanner(System.in);
        System.out.println("Quel est votre Choix? 1.Lecture 2.Creation 3.Modification. 4.Test 5.Quitter"); //Message recap pour les choix possible
        char menu = scmenu.nextLine().charAt(0);//pour recuperer le caractére saisi par l'utilisateur.
		//On fait un switch étant donné le nombre de choix possible
        //remarque : le menu actuel est que pour les carte !
        switch (menu) {
		  case '1': //Lire une carte
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
		         chemin="./Vcards/"+fichier+".vcf"; //le chemin du fichier
              //Remarque : on considére que l'utilisateur oublie le .vcf
		        LireFichier(chemin); //Fonction pour lire le fichier,

		    break;//on sort du switch
		  case '2': //Créer une carte
			  CreerCard();
			  break;//on sort du switch
		  case '3': //Modification d'une carte
              ModifCard( );
		      break;//on sort du switch
		  case '4': //Test - Hello world
			  HelloWord();
		      break;//on sort du switch
		  case '5': //Quitter l'application proprement
			  System.out.println("Fin de l'appli");
		      break;//on sort du switch
		  default: //Cas ou on tape un mauvais caractére
			  System.out.println("Erreur! Fonction non trouvé!");
		    break;//on sort du switch
		}
		
	}

	//-----------------------------//
    //1.Lire une carte
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
        //1.4. Resultat : affiche les fichiers contenus dans le chemin préciser ci dessous
     **/
    private static void ListerFichiers(String chemin) {
        //pour rappel on a chemin = ./Vcards" qu'on a rentrér de base
        File repertoire = new File(chemin); //on cherche le repertoir dans le chemin précisement => ici Vcards
        String liste[] = repertoire.list(); //on créer un tableau pour stocker la liste des fichiers

        if (liste != null) { //si la liste est non null => le repertoire existe
            for (int i = 0; i < liste.length; i++) //boucle for qui parcour la liste des fichiers
            {
                System.out.println(liste[i]);//pour affiche le nom des fichier
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
        LireFichier(chemin); //Fonction pour lire le fichier,
     */
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
     *  @param NomCarte, le nom de la carte q'on veux lire
     *  Utilisation : Vcard.LireCard(NomCarte);
     */
    public static void LireCard(String NomCarte){
	          //information necessaire : nomcarte
	}

    /**AffCard - A creer (vide)
     * permet d'afficher une card
     * @param NomCarte:de la carte qu'on veux afficher
     *                Utilisation :Vcard.AffCard(NomCarte);
     */
    public void AffCard(String NomCarte){
      //String nom carte //demande quel carte on doit afficher
    	}
    
    //-----------------------------//
    //2.Créer une carte
    /** CreerCard
     * Description : Méthode qui permet de Créer une Carte de Visite.
     *  Utilisation dans le constructeur
        CreerCard();
     */
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
    	System.out.println("Le nouvelle carte se situe en :"+ chemin); //test si etape ok - affiche le chemin relatif

    	//Etape 2 : On écrit les information dessus
    	//le try est important, car c'est le cas ou le fichier peux se créer! (pas de doublons, pas de souci de chemin vérifier)
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
                char reponsetra ; //check si la personne  à une adresse de travaille -

                //Variable de demande générale
                String villetra = villedom;
                String cptra= cpdom;
                String paystra=paysdom;
                String nruetra= nruedom;

                //Ici on fait une boucle while, pour forcer l'utilisateur de rentrer soit 1 ou 2 comme choix.
                do{

                    Scanner sca1 = new Scanner(System.in);
                    System.out.println("La personne a t-il une adresse de travaille ? 1.Oui 2.Non"); //message pour demander le prenom de la personne
                    reponsetra = sca1.nextLine().charAt(0); // lit le charactere attendu
                    //Vérification si on a bien rentrer un caractere
                    System.out.println("reponse :"+reponsetra);
                    /*
                     * if(reponsetra.equals("") ){
                        System.out.println("Erreur! vous n'avez pas entrer de caractere!");
                    }
                     */


                }while(reponsetra != '1' && reponsetra != '2'   );

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
        	}
        }

    //-----------------------------//
    //3.Modifier une carte
    /**ModifCard
     * permet de modifier une carte
     * @throws IOException
     * Utilisation : dans le constructeur
       ModifCard( );
     */
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
                    String email= ChecheEmail(chemintampon); //cherche l'email  dans la carte et le stocke dans EMAIL
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
                        System.out.println("4. L'adresse de livraison ou travaile- LABEL");
                        System.out.println("5. Le téléphone -TEL");
                        System.out.println("6. L'email - EMAIL");
                        System.out.println("7. Quitter la modification");
                        int valmodif = scmodif.nextInt() ; //lecture de la variable- verifm

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
                                System.out.println("Debut test Modif ADR");
                                int nbadresses = 0;

                                //variable
                                String nrue     = null, nruedom     = null, nruetra = null;
                                String cp       = null, cpdom       = null, cptra = null;
                                String ville    = null, villedom    = null, villetra = null;
                                String pays     = null, paysdom     = null, paystra = null;

                                 if(!getADR().equals("") && !getADR().isEmpty()) //Cas adresse unique - isEmpty very que la chaine est vide.
                                 {
                                     System.out.println("Il a une une adresse unique!");
                                     nbadresses = 1;
                                     //on doit retranscrire l'adresse d'abord
                                     nrue = ChecheNomRue("./Vcards/Temp.vcf");
                                     cp = ChecheCodePostale("./Vcards/Temp.vcf");
                                     ville = ChecheNomVille("./Vcards/Temp.vcf");
                                     pays = ChecheNomPays("./Vcards/Temp.vcf");

                                     System.out.println("L'adresse actuelle est :");
                                     System.out.println(nrue);
                                     System.out.println(ville+"     "+cp);
                                     System.out.println(pays);
                                 }
                                 else if( (!getADRDOM().equals("")  && !getADRDOM().isEmpty() ) &&
                                        (!getADRTRA().equals("")  && !getADRTRA().isEmpty() )
                                    )//Cas adresse multiple - Si on a DOM on a un travaille.
                                 {
                                     System.out.println("Il a deux type d'adresses!");
                                     nbadresses = 2;

                                     //on doit retranscrire les adresse  d'abord

                                     //Domicile
                                      nruedom = ChecheNomRueDom("./Vcards/Temp.vcf");
                                      cpdom = ChecheCodePostaleDom("./Vcards/Temp.vcf");
                                      villedom = ChecheNomVilleDom("./Vcards/Temp.vcf");
                                      paysdom = ChecheNomPaysDom("./Vcards/Temp.vcf");

                                     System.out.println("L'adresse de domicile est :");
                                     System.out.println(nruedom);
                                     System.out.println(villedom+"     "+cpdom);
                                     System.out.println(paysdom);

                                      nruetra = ChecheNomRueTra("./Vcards/Temp.vcf");
                                      cptra = ChecheCodePostaleTra("./Vcards/Temp.vcf");
                                      villetra = ChecheNomVilleTra("./Vcards/Temp.vcf");
                                      paystra = ChecheNomPaysTra("./Vcards/Temp.vcf");

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
                                    setADR("ADR;HOME;PREF;QUOTED-PRINTABLE:;"+ville+" "+cp+"="+ville+";"+nrue);
                                    //reference : ADR;HOME;PREF;QUOTED-PRINTABLE:;Puteaux 92800=France;5 Rue de la République
                                    // System.out.println( getADR());//vérification
                                }
                                else if(nbadresses==2){
                                    setADRDOM("ADR;HOME;PREF;QUOTED-PRINTABLE:;"+villedom+" "+cpdom+"="+villedom+";"+nruedom);
                                    setADRTRA("ADR;WORK;PREF;QUOTED-PRINTABLE:;"+villetra+" "+cptra+"="+villetra+";"+nruetra);
                                }
                                else{
                                    System.out.println("Erreur System");
                                }

                                //5.Modification du fichier tempon
                                ModifTempon(); //Modification du fichier tempon

                                //6.Lire le Fichier Tampon Modifier
                                LireFichier("./Vcards/Temp.vcf"); //lecture du fichier
                                 System.out.println("Fin test Modif ADR");
                                break;
                            case 4://L'adresse de livraison - LABEL
                                //1.Affiche Valeur
                                System.out.println("L'adresse de livraison actuelle est  :"+getLabel());
                                //2.Demande valeur
                                Scanner scLabel = new Scanner(System.in); //demande de lire- Scanner
                                System.out.println("Saisir le nouveau Label :"); //Demande de saision
                                String nvLabel = scLabel.nextLine();
                                //3.Verif Valeur

                                //4.Modifciation de la valeur
                                setLabel(nvLabel); //Modification de la valeur du nom
                                // System.out.println("Le nouveau nom est:"+getFN()); //Vérification
                                //5.Modification du fichier tempon
                                ModifTempon();
                                //6.Lire le Fichier Tampon Modifier
                                LireFichier("./Vcards/Temp.vcf"); //lecture du fichier
                                //System.out.println("Fin test Modif Nom");
                                break;
                            case 5://Le tel - TEL
                                //1.Affiche Valeur
                                System.out.println("Le Téléphone est :"+getTel());
                                //2.Demande valeur
                                Scanner sctel = new Scanner(System.in); //demande de lire- Scanner
                                System.out.println("Saisir le nouveau Tel :"); //Demande de saision
                                String nvtel = sctel.nextLine();
                                //3.Verif Valeur

                                //4.Modifciation de la valeur
                                setTel(nvtel); //Modification de la valeur du nom
                                System.out.println("Le nouveau tel est:"+getTel()); //Vérification
                                //5.Modification du fichier tempon
                                ModifTempon();
                                //6.Lire le Fichier Tampon Modifier
                                LireFichier("./Vcards/Temp.vcf"); //lecture du fichier
                                //System.out.println("Fin test Modif Nom");
                                break;
                            case 6://L'email - EMAIL
                                //1.Affiche Valeur
                                System.out.println("L'adresse de livraison actuelle est  :"+getLabel());
                                //2.Demande valeur
                                Scanner scEmail= new Scanner(System.in); //demande de lire- Scanner
                                System.out.println("Saisir le nouveau Email :"); //Demande de saision
                                String nvEmail= scEmail.nextLine();
                                //3.Verif Valeur

                                //4.Modification de la valeur
                                setLabel(nvEmail); //Modification de la valeur du nom
                                System.out.println("Le nouveau email est:"+getEmail()); //Vérification
                                //5.Modification du fichier tempon
                                ModifTempon();
                                //6.Lire le Fichier Tampon Modifier
                                LireFichier("./Vcards/Temp.vcf"); //lecture du fichier
                                //System.out.println("Fin test Modif Nom");
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
                            System.out.println("Modification Faite!");//Affichage de l'action faite
                        }
                        else if(verifm2.equals("N")){   //sinon on efface le temp
                            verif2=true;
                            //on supprime le fichier tempon
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
     * Utilisation => Sur ModifCard, permet de retranscrire les donnée directement (Utilisation courante)
     *
     */
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
     *  cette fonction est utiliser dans ModifCard() - pour modifier un fichier tempon dans un premier temps puis recopiera sur le fichier lui même
     *
     */
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
    //4.Fonction test - HelloWorld
    
    /**HelloWord
     *   méthode qui affiche un helloworld
     *   utilisation : pour tester et afficher Hello Wolrd -> utile pour voir ou on est
     */
	public static void HelloWord() {
    	System.out.println("Hello World");//Affiche Hello World
    }

    //-----------------------------//
    //Méthodes non faites- A venir

	/**ExportCard() - a creer( vide)
     * permet d'exproter et d'envoyer la carte à une personne
     *  Utilisation :    Vcard.ExportCard();
     **/
    public void ExportCard(){

    }

    /**SupCard
	 * Supprime une carte
     *  Utilisation :    Vcard.SupCard();
	 **/
	public void SupCar(){
	    //information necessaire : nomcarte - pour savoir ce la carte qu'on modifie
	}
	
    /**MajCarte
	 *   Mettre a jour la carte pour les valeurs privée tel que la version/clé de chiffrement.
	 *  
     *  Utilisation :    Vcard.MajCard();
	 **/
	public void MajCard(){
	   
	}
	
    /**AjoutCarte
	 *  permet d'ajouter une carte?
	 *  
     *  Utilisation :    Vcard.AjoutCarte();
	 **/
	public void AjoutCarte(){
	  
	}
	
    /**GestionCarte
	 *  permet la gestion d'une carte
	 *  
     *  Utilisation :    Vcard.GestionCarte();
	 **/
	public void GestionCarte(){
	  
	}

    //--Méthode de Vérification ---//
  	//-----------------------------//
    /**CheckChaineVide
     * vérifie que la chaine est vide est dans ce cas retourne true
     * @param chaine, la chaine qui vérifie
     * @return regle, bolean qui retourne true si on a une chaine vide
     * utilisation :
     Cette fonction est utiliser dans CreerCard et ModifCard, lorsque qu'on demande à l'utilisateur de saisir une chaine de caractére
     et forcer à rentrer une valeur
     *

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
    }//tant qu'on a rien rentrer on continue la boucle
     */
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
       dans main : CheckChemin(chemin);
     */
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
            System.out.println(a); //renvoie a, qui peut être true ou false
     */
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
         System.out.println(a); //renvoie a, qui peut être true ou false
     */
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
        System.out.println(a); //renvoie a, qui peut être true ou false
     */
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
         System.out.println(a); //renvoie a, qui peut être true ou false
     **/
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

    //--Méthode de Recherche de Donnée ---//
  	//-----------------------------------//
    
   	/** ChercheNom
        * Cherche le nom dans une VCards
        * @param cheminfichier : le fichier, Vacard ou on cherche le nom.
        * @return nom : retourne le nom de la personne présente dans la Carte
     *  Utilisation :
        	String nom = ChercheNom(chemintampon);           
          	System.out.println("Nom :"+nom);//affiche le nom
        */
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
   			System.out.println("Prenom:"+prenom);
        */
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
     * @return tel : le telphone
     */
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

    /**ChecheEmail
     * cherche l'email dans un fichier
     * @param cheminfichier : le chemin du fichier
     * @return email : l'email
     */
    private String ChecheEmail(String cheminfichier) {

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
			 		System.out.println("il a "+nombreadresse+" adresse"); //affiche le nombre d'adresse
		     */
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
			     		System.out.println( " Cp :"+cp)
			     */
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
			     		System.out.println( "Pays :"+pays)
				 */
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
			     		System.out.println( "Ville :"+ville)
				 */
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
			     		System.out.println( "Adresse :"+nrue)
				 */
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
			     		System.out.println( " Cpdom :"+cpdom)
			     */
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
			     		System.out.println( "Pays :"+paysdom)
				 */
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
			     		System.out.println( "Ville :"+villedom)
				 */
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
			     		System.out.println( "Ville :"+nruedom)
				 */
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
			     		System.out.println( " Cpdom :"+cptra)
			     */
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
		
			   // System.out.println("Pays :"+pays);//check
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
		
			   // System.out.println("Ville :"+ville);//ville
			   return ville;
		
				}

				/**ChecheNomRueTra
                 * récupére uniquement l'adresse et le numéro de la rue de Travaille
                 * @param cheminfichier : chemin du fichier
                 * @return adr: retourne l'adresse- notamment l'adresse et le numéro de la rue
                 *                  *  Utilisation :
                 * 			     		String nruetra= ChecheNomRueDom(chemintampon);
                 * 			     		System.out.println( "Adresse:"+nruetra=)
                 */
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
             * @return adr : retourne le type de lieu de la livraison
             */
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
                 * @return adr : la chaine avec le nom de la ville
                 */
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
                 * @return adr : la chaine avec le nom du pays
                 */
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
                 * @return adr : la chaine avec le code postale
                 */
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

	//---Méthode Générale - Générer par IDE---//
  	//-----------------------------//
    //Equals = pour valider que la carte est identique
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

  //---Getteurs---//
  	//-----------------------------//

    /**getADR
     * pour avoir l'adresse
     * @return ADR : adress
     */
    public String getADR() {
        return ADR;
    }

    /**getEmail
     * pour avoir l'email
     * @return Email : adress
     */
    public String getEmail() {
        return Email;
    }

    /**getFN
     * pour avoir le FN, prenom
     * @return FN : prenom
     */
    public String getFN() {
        return FN;
    }

    /**getLabel
     * pour avoir le Labe, l'adresse de livraison
     * @return label : l'adresse de livraison
     */
    public String getLabel() {
        return Label;
    }

    /**getN
     * pour avoir N, le nom
     * @return N: nom
     */
    public String getN() {
        return N;
    }

    /**getNickname
     * pour avoir Nickname : le surnom
     * @return Nickname;
     */
    public String getNickname() {
        return Nickname;
    }

    /**getPhoto
     * pour avoir la photo
     * @return Photo
     */
    public String getPhoto() {
        return Photo;
    }

    /**getRev
     * pour avoir le Rev, mise a jour de la carte
     * @return Rev : mise a jour de la carte
     */
    public Date getRev() {
        return Rev;
    }

    /**getRole
     * pour avoir le role
     * @return Role
     */
    public String getRole() {
        return Role;
    }

    /**getTel
     * pour avoir le telephone
     * @return Tel : telphone
     */
    public String getTel() {
        return Tel;
    }

    /**getTitle
     *
     * @return Title : titre
     */
    public String getTitle() {
        return Title;
    }

    /**getUID
     *
     * @return UID : ID de la carte
     */
    public int getUID() {
        return UID;
    }

    /**getURL
     *
     * @return URL : lien de mise a jour
     */
    public String getURL() {
        return URL;
    }

    /**getKey
     *
     * @return Key : clé de chiffrement
     */
    public String getKey() {
        return Key;
    }

    /**getNote
     *
     * @return Note: note/description de la vcard
     */
    public String getNote() {
        return Note;
    }

    /** getNomCarte
     *
     * @return NomCarte : nom de la carte
     */
    public String getNomCarte() {
        return NomCarte;
    }

    /**getSortString
     *
     * @return SortString : trie
     */
    public String getSortString() {
        return SortString;
    }

    /**getVersion
     *
     * @return Version : version de la carte
     */
    public String getVersion() {
        return Version;
    }

    /**getADRDOM
     *
     * @return ADRDOM : adresse de domicile
     */
    public String getADRDOM() {
        return ADRDOM;
    }

    /**getADRTRA
     *
     * @return ADRTRA : adresse de travaille
     */
    public String getADRTRA() {
        return ADRTRA;
    }

    //---Setteurs---//
  	//-----------------------------//

    /** setADR
     * modifie l'adresse
     * @param ADR : adresse
     */
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
     * @param FN : prenom
     */
    protected void setFN(String FN) {
        this.FN = FN;
    }

    /**setLabel
     * modifie l'adresse de livraison
     * @param label : l'adresse de livraison
     */
    protected void setLabel(String label) {
        Label = label;
    }

    /**setN
     * modifie le nom
     * @param n : nom
     */
    protected void setN(String n) {
        N = n;
    }

    /**setNickname
     * modifie le surnom
     * @param nickname: surnom
     */
    protected void setNickname(String nickname) {
        Nickname = nickname;
    }

    /**setNomCarte
     * modifie le nom de la carte
     * @param nomCarte: nom de la carte
     */
    protected void setNomCarte(String nomCarte) {
        NomCarte = nomCarte;
    }

    /**setNote
     * modifie la description de la carte
     * @param note: description de la carte
     */
    protected void setNote(String note) {
        Note = note;
    }

    /**setTel
     * modifie le telephone
     * @param tel : telephone
     */
    protected void setTel(String tel) {
        Tel = tel;
    }

    /**setTitle
     * modifie le titre
     * @param  title : titre
     */
    protected void setTitle(String title) {
        Title = title;
    }

    /** setPhoto
     * modifie la photo
     * @param photo : photo
     */
    protected void setPhoto(String photo) {
        Photo = photo;
    }

    /** setKey
     * modifie la clé de chiffrement
     * @param key : la clé de chiffrement
     */
    protected void setKey(String key) {
        Key = key;
    }

    /** setRev
     * modifie la date de mise à jour
     * @param  rev : la clé de chiffrement
     */
    protected void setRev(Date rev) {
        Rev = rev;
    }

    /** setRole
     * modifie le role
     * @param  role :  role
     */
    protected void setRole(String role) {
        Role = role;
    }

    /** setSortString
     * modifie le trie
     * @param  sortString :  trie
     */
    protected void setSortString(String sortString) {
        SortString = sortString;
    }

    /** setUID
     * modifie l'id'
     * @param   UID:  ID
     */
    protected void setUID(int UID) {
        this.UID = UID;
    }

    /**setURL
     * modifie l'URL del a carte
     * @param  URL:  URL)
     */
    protected void setURL(String URL) {
        this.URL = URL;
    }

    /**setVersion
     * modifie la version de la carte
     * @param  version:  version de la carte
     */
    protected void setVersion(String version) {
        Version = version;
    }

    /**setADRDOM
     * modifie l'adresse de domicile
     * @param ADRDOM : l'adresse de domicile
     */
    public void setADRDOM(String ADRDOM) {
        this.ADRDOM = ADRDOM;
    }

    /**setADRTRA
     * modifie l'adresse de travaille
     * @param ADRTRA : l'adresse de travaille
     */
    public void setADRTRA(String ADRTRA) {
        this.ADRTRA = ADRTRA;
    }
}







