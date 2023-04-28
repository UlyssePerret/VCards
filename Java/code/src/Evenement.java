/*
Cree : 16 janvier 2020
Dernier mise a jour : 23 janvier 2020
Version : 1.0
Credi : Ulysse Perret; Amadou Ly ;Nickola Makovoric

Changelog
version : 0.1- creation du programme
version : 0.2   Verification et mise a jour
1.0: Versionning et Mise en commun (package)
*/

import java.util.Date;
import java.util.Objects;

public class Evenement  {

    //Propriété
    /*
    * Date de début de l'évenement
    */
    public Date Dtstart;
    /*
     * Date de fin de l'évenement
     */
    public Date Dtend;
    /*
    Titre de l'évenement
     */
    public String Sumarry;
    /*
     *  Adresse du lieu de l'évenement
     */
    public String Location;
    /*
    Categorie de l'évement
     */
    public String Categories;
    /*
     *  Status de l'évenement
     */
    public String Status;
    /*
     *  Description de l'évenement
     */
    public String Description;
    /*
     *  Visibilité et type de l'évenement (public, privée)
     */
    public String Transp;
    //Méthodes
    /*
    Creer un évenement
     */
    public void CreerEvent(){};

    /*
   Exporter et envoyer un evenement
     */
    public void ExportEvent(){}

    /*
    Modifier un evenement
     */
    public void ModifEvent(){};
    /*
    Supprimer un Event
     */
    public void SupEvent(){};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evenement evenement = (Evenement) o;
        return Dtstart.equals(evenement.Dtstart) &&
                Dtend.equals(evenement.Dtend) &&
                Objects.equals(Sumarry, evenement.Sumarry) &&
                Objects.equals(Location, evenement.Location) &&
                Objects.equals(Status, evenement.Status) &&
                Objects.equals(Description, evenement.Description);
    }

    @Override
    public String toString() {
        return "Evenement{" +
                "Dtstart=" + Dtstart +
                ", Dtend=" + Dtend +
                ", Sumarry='" + Sumarry + '\'' +
                ", LocationN='" + Location+ '\'' +
                ", Status='" + Status + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }

    //GETTEURS

    public Date getDtstart() {
        return Dtstart;
    }

    public Date getDtend() {
        return Dtend;
    }

    public String getSumarry() {
        return Sumarry;
    }

    public String getLocation() {
        return Location;
    }
    public String getCategories(){
        return Categories;
   }
    public String getStatus(){
        return Status;
    }
    public String getDescription(){
        return Description;
    }
    public String getTransp(){
        return Transp;
    }
           //SETEURS


    protected void setDtstart(Date dtstart) {
        Dtstart = dtstart;
    }

    protected  void setDtend(Date dtend) {
        Dtend = dtend;
    }

    protected  void setSumarry(String sumarry) {
        Sumarry = sumarry;
    }

    protected  void setLocation(String location) {
        Location = location;
    }

    protected  void setCategories(String categories) {
        Categories = categories;
    }

    protected  void setStatus(String status) {
        Status = status;
    }

    protected  void setDescription(String description) {
        Description = description;
    }

    protected  void setTransp(String transp) {
        Transp = transp;
    }
}
