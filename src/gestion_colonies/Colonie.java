
package gestion_colonies;

import java.util.Observable;
import java.util.Observer;

/*
 * @author : Sabah HM
 */
public class Colonie implements Observer {
    
    // attributs privés
    private String nom;
    private double taux_croissance;
    private int population_initial;
    private int population_acteulle = 0;

    // constructeur avec les trois parametres 
    public Colonie(String nom, int population_initial, double taux_croissance) {
        this.nom = nom;
        this.taux_croissance = taux_croissance;
        this.population_initial = population_initial;
        this.population_acteulle = population_initial; // la population reelle est bien la population initiale au depart
    }
    
    // accesseur et modificateurs des attributs
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getTaux_croissance() {
        return taux_croissance;
    }

    public void setTaux_croissance(double taux_croissance) {
        this.taux_croissance = taux_croissance;
    }

    public int getPopulation_initial() {
        return population_initial;
    }

    public void setPopulation_initial(int population_initial) {
        this.population_initial = population_initial;
    }

    public int getPopulation_actuelle() {
        return population_acteulle;
    }

    public void setPopulation_actuelle(int population_acteulle) {
        this.population_acteulle = population_acteulle;
    }

    public void organiser_fete(double taux_alteration) {
        population_acteulle += (int)(population_acteulle*taux_croissance/100*taux_alteration) ;
    }

    @Override
    public void update(Observable o, Object arg) {
        // cette colonie est en attente pour un enregistrement
        // TODO : gérer la notification
        System.out.println("\n- msg pour la colonie '"+nom+"' : Une chambre est disponbile!");
    }
    
    
}
