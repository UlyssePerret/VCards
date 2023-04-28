/*
Cree : 16 janvier 2020
Dernier mise a jour : 17 janvier 2020
Version : 0.2
Credi : Ulysse Perret; Amadou Ly ;Nickola Makovoric

Changelog
version : 0.1- creation du programme
version : 0.2
    modification de visibilité- SortString : public=> protected
    uid -> id
1.0: Versionning et Mise en commun (package)
 */

public class FlipVcards {

    //Propriétés
    /*
    @about Permet de trier les cartes pars nom
     */
     protected String SortString="";
     /*
     @about Le nom de la personne
      */
     public String N="";
     /*
     @about ID de la carte
      */
     private  int id=0;
    /*
   @about ici c'est le groupe de carte, la composition des card en fait
    */
    private Object group;



     //Méthodes
    /*
    Permet de recherche une carte par N, donc par Nom
     */
    public void RechercheCardN(String N){

    }
    /*
    Permet de recherche une carte par UID, donc par ID de la carte
    */
    private void RechercheCardID(int uid){

    }
    /*
    Permet d'afficher la carte de visite
    */
    public void AffCard(){

    }

    /*
    Permet de créer une carte de visite
    */
    public void CreerCard(){

    }

    /*
    Permet d'exporter la carte
    */
    public void ExportCard(){

    }

    /*
    Permet de lire la Carte
    */
    public void LireCard(){

    }

    /*
    Permet de modif la Carte
    */
    public void ModifCard(){

    }

    /*
    Permet de supprimer la Carte
    on met en private vu qu'on n'en sert rarement
    */
    private void SupCard(){

    }
    /*
    Permet de  mettre a jour la carte
    */
    public void MajCad(){

    }

    /*
    Permet de recherche une carte par N, donc par Nom
    */
    public void RecherchePersonneN(String N){

    }
    /*
   Permet d'ajouter une Personne dans le groupe
    */
    public void AjoutPersonne(){

    }
    /*
   Permet de Supprimer une Personne dans le groupe
    */
    public void SupPersonne(){

    }

    /*
    Permet d'afficher le groupe
    */
    public void affGroup(){

    }

    /*
    Permet de supprimer un groupe
    */
    public void SupGroup(){

    }

    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlipVcards that = (FlipVcards) o;
        return id == that.id &&
                SortString == that.SortString &&
                N == that.N;
    }

    
    public String toString() {
        return "Le FlipVcards  est composés des cartes suivante:" +
                "ID :" + id +
                "Nom ; " + N +"/n"+
                '}';
    }

    // GETTEURS

    public String getSortString() {
        return SortString;
    }


    public String getN() {
        return N;
    }


    public int getId() {
        return id;
    }

    public Object getGroup() {
        return group;
    }

    //SETTEUR

    public void setSortString(String sortString) {
        SortString = sortString;
    }

    public void setN(String n) {
        N = n;
    }

    public void setId(int udi){
        id = udi;
    }

    public void setGroup(Object group) {
        this.group = group;
    }
}
