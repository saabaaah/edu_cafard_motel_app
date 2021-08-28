
package gestion_paiement;

/*
 * @author : Sabah HM
 */
public abstract class MoyenPaiement {
    
    // attributs privés
    private String description;
    private String code_carte;

    // constructeur sans arguments (sans description ni code carte )
    public MoyenPaiement() {
        this.description = "Non reconnu";
        this.code_carte = "Aucun code donné";
    }
    
    // constructeur avec argument (description et code carte )
    public MoyenPaiement(String description, String code_carte) {
        this.description = description;
        this.code_carte = code_carte;
    }
    
    // accesseurs et modificateurs 
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode_carte() {
        return code_carte;
    }

    public void setCode_carte(String code_carte) {
        this.code_carte = code_carte;
    }
    
    // affichage d'un moyen de paiement
    @Override
    public String toString() {
        return "MoyenPaiement :" + "description=" + description + ", code_carte=" + code_carte;
    }
    
    
}
