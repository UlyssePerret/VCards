/*
Crée le 15 janvier 2020
Mise a jour : 23janvier
version actuel : 1.0
Auteur : Amadou Ly

0.3 : creation
0.4 : structure du code + ajout de commentaire pour return/param
1.0: Versionning et Mise en commun (package)
 */
import java.util.Date;

public class Calendrier {  // creation d'un calendrier

   // Les Variables

    /*
    Faire l’action voulue (ex : Audio: lit la carte)
     */
    private String Action;

   /*
   Date de début
    */
   public Date Dtstart;

   /*
   Catégories de l’événement (réunion de travail, meeting...)
    */
    public String Categories;

   /*
    Description de l’événement
    */
   public String Description;

   /*
   Clé de chiffrement calendrier
    */
   private int Dtstamp;

   /*
    Lieu de l’événement
    */
   public String Location;

   /*
   ID du calendrier
    */
   private int Prodid =0;

   /*
   Mise à jour du calendrier
    */
   private String Sequence;

   /*
   Statut de l’événement (en cours, fini ...)
    */
   public  String Status;

   /*
   Titre événement
    */
   public  String Summary;

   /*
   Vérifié la disponibilité de la personne
    */
   public String Transp;

   /*
   Version du calendrier
    */
   private String Version;

   /*
   Fin de l’événement
    */
   public String Dtend;

   // Les méthodes

   /*
   afficher calendrier dans la console
   @return  affiche  le calendrier
    */
   public void AffCalend(){
   }

   /*
   lire calendrier ; cela n'affiche rien, juste permet de lire le fichier)
   @return rien
    */
   public void LireCalend(){
   }

   /*
   créer un événement et le fichier associer
   @return :creer fichier evenement dans un dossier?
    */
   public void CreerEvent(){
   }

   /*
   supprimer une personne
   @para necessaire: information de la personne
   @return supprime un fichier Vcard de la personne concerné
    */
   public void DelPer(){
   }

   /*
   Exporter un événement, envoie par mail l'evenement aux personnes
   @param email : l'email de la personne qu'on va envoyer l'evenement (demande dans la fonction)
   @return  export par mail l'evenement aux personnes mis en parametre
    */
   public void ExportEvent(){
      // String email;
   }

   /*
   Inviter personne
   @param : nom/ email de la personne
   @return : la personne inviter
    */
   public void Invit(){
   }

   /*
   Modifier événement
   @return : la modification faite
    */
   public void ModifEvent(){
   }

   public String getAction() {
      return Action;
   }

   public Date getDtstart() {
      return Dtstart;
   }

   public int getDtstamp() {
      return Dtstamp;
   }

   public String getCategories() {
      return Categories;
   }

   public int getProdid() {
      return Prodid;
   }

   public String getDescription() {
      return Description;
   }

   public String getDtend() {
      return Dtend;
   }

   public String getLocation() {
      return Location;
   }

   public String getSequence() {
      return Sequence;
   }

   public String getStatus() {
      return Status;
   }

   public String getSummary() {
      return Summary;
   }

   public String getTransp() {
      return Transp;
   }

   public String getVersion() {
      return Version;
   }

   // Les setteurs

   public void setAction(String action) {
      Action = action;
   }

   public void setCategories(String categories) {
      Categories = categories;
   }

   public void setDescription(String description) {
      Description = description;
   }

   public void setDtstamp(int dtstamp) {
      Dtstamp = dtstamp;
   }

   public void setDtstart(Date dtstart) {
      Dtstart = dtstart;
   }

   public void setDtend(String dtend) {
      Dtend = dtend;
   }

   public void setLocation(String location) {
      Location = location;
   }

   public void setProdid(int prodid) {
      Prodid = prodid;
   }

   public void setSequence(String sequence) {
      Sequence = sequence;
   }

   public void setStatus(String status) {
      Status = status;
   }

   public void setSummary(String summary) {
      Summary = summary;
   }

   public void setTransp(String transp) {
      Transp = transp;
   }

   public void setVersion(String version) {
       Version = version;
   }
}

