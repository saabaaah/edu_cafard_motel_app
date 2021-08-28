
package gestion_paiement;

/*
 * @author : Sabah HM
 */
public class MasterCafard extends MoyenPaiement{
    // attributs privés 
    private String code_securite;
    private String date_exp;
    
    // constructeur avec ce code de sécuriyé

    public MasterCafard(String code_securite, String date_exp) {
        this.code_securite = code_securite;
        this.date_exp = date_exp;
    }

    public MasterCafard(String description, String code_carte, String code_securite, String date_exp) {
        super(description, code_carte); // appel constructeur moyenPaiement
        this.code_securite = code_securite;
        this.date_exp = date_exp;
    }
    
    // Accesseur description modifié
    @Override
    public String getDescription() {
        return "MasterCafard paiement : "+super.getDescription() ; //To change body of generated methods, choose Tools | Templates.
    }
        
    // affichage de moyenPaiement modifié
    @Override
    public String toString() {
        return "MasterCafard : "+super.toString() + ", code sec="+ this.code_securite+ ", EXP="+ this.date_exp; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
