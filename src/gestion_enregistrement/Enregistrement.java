
package gestion_enregistrement;

import gestion_chambres.*;
import gestion_commodites.*;
import gestion_colonies.*;
import gestion_paiement.*;
import java.util.Date;

/*
 * @author : Sabah HM
 */
public class Enregistrement {
    
    // attributs privés
    private String numero_chambre;
    private int nombre_jours;
    private Date date;
    private Chambre chambre;
    private Colonie colonie;
    private Paiement paiement;
    private Etat etat;

    // constructeur avec la chambre et la colonie
    public Enregistrement(Chambre chambre, Colonie colonie, String numero_chambre) {
        this.chambre = chambre;
        this.colonie = colonie;
        this.numero_chambre = numero_chambre;
        
        // par défaut, un enregistrement est en cours 
        this.etat = Etat.EN_COURS;
    }
    
    // fonctions de finalisation d'enregistrement 
    public String finaliser(Paiement p){
        
        // TODO : lancer le paiement 
        this.paiement = p;
        
        // rendre l'enregistrement comme terminé
        this.etat = Etat.TERMINE;
        // liberer chambre
        chambre.rendre_occupee(false);
        
        // return le numéro de la chambre afin de la finaliser
        return numero_chambre;
        
    }
    
    // fonction de calcul des frais total de cet enregistrement
    public double calculer_frais(int nombre_jours){
        double frais_total=0;
        //  calculer le frais total
        frais_total = chambre.get_frais() * nombre_jours;
        return frais_total;
    } 

    // affichage d'une transaction //
    public void afficher_transaction(){
        if(this.paiement == null){
            System.out.println("\n Aucune transaction pour cet enregistrement!");
        }else{
            System.out.println("\n Transaction : " + paiement);

        }
    }
    
    // organiser une fete pour la colonie de cet enregistrement
    public void organiser_fete() {
        colonie.organiser_fete(chambre.get_taux_population());
    }
        
    // setters & getters 
    public String getNumero_chambre() {
        return numero_chambre;
    }

    public void setNumero_chambre(String numero_chambre) {
        this.numero_chambre = numero_chambre;
    }

    public Paiement getPaiement() {
        return paiement;
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }
    
    // les etat d'un enregistrement --> en cours ou terminé
    public enum Etat{
        EN_COURS,
        TERMINE
    }
    
}

