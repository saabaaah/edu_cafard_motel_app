
package gestion_commodites;

import gestion_chambres.*;

/*
 * @author : Sabah HM
 */
public abstract class CommoditeChambre implements Chambre {
    // constante pour les différentes commodités possibles 
    public static final int COMMODITE_MINI_BAR = 1;
    public static final int COMMODITE_SPA = 2;
    public static final int COMMODITE_DOUCHE = 3;
    
    // la chambre de cette commodité
    private Chambre chambre;
    
    // constructeur avec TypeChambre
    public CommoditeChambre(ChambreEquipee type_chambre){
        // recuperer la chambre depuis la classe TypeChambre
        this.chambre = type_chambre;
    }    
    // constructeur avec Commodité
    public CommoditeChambre(CommoditeChambre comm_chambre){
        // recuperer la chambre depuis la classe TypeChambre
        this.chambre = comm_chambre;
    }
    
    @Override
    public String get_description() {
        return chambre.get_description() + " ";
    }
    // définition des méthodes d'interface : récupérer les frais
    @Override
    public double get_frais() {
        return chambre.get_frais();
    }
    @Override
    public boolean est_occupee() {
        return chambre.est_occupee();
    }

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(ChambreEquipee chambre) {
        this.chambre = chambre;
    }
    
    public void rendre_occupee(boolean occupee) {
        this.chambre.rendre_occupee(occupee);
    }
    
    @Override
    public void init(String num_chambre) {
        chambre.init(num_chambre);
        chambre = new ChambreEquipee( num_chambre);
    }
    
        
    @Override
    public String getNumero() {
        return chambre.getNumero();
    }
    @Override
    public double get_taux_population() {
        return 0.50;
    }
    
    
    
}
