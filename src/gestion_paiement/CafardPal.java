
package gestion_paiement;

/*
 * @author : Sabah HM
 */
public class CafardPal extends MoyenPaiement{

    // attributs privés : aucun
    
    // constructeur
    public CafardPal(String description, String code_carte) {    
        super(description, code_carte);
    }    

    // Accesseur description modifié
    @Override
    public String getDescription() {
        return "CafardPal paiement : "+super.getDescription();
    }    

    // affichage de moyenPaiement modifié
    @Override
    public String toString() {
        return "CafardPal paiement : "+super.toString(); 
    }
}