
package gestion_paiement;

/*
 * @author : Sabah HM
 */

public class Paiement {
    
    // attributs privé
    private double total;
    private MoyenPaiement moyen_paiement;

    public Paiement(double total, MoyenPaiement moyen_paiement) {
        this.total = total;
        this.moyen_paiement = moyen_paiement;
    }

    
    // affichage d'un paiement 
    @Override
    public String toString() {
        return "Paiement -> " + "total :" + total + ", \nmoyen_paiement : " + moyen_paiement;
    }
    
    
}
