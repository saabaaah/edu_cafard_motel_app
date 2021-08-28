
package gestion_commodites;

import gestion_chambres.*;

/*
 * @author : Sabah HM
 */
public class MiniBar extends CommoditeChambre{
    
    // constante pour les frais MiniBar
    public static final int FRAIS_MINI_BAR = 10;

    public MiniBar(ChambreEquipee type_chambre) {
        super(type_chambre);
    }

    public MiniBar(CommoditeChambre comm_chambre) {
        super(comm_chambre);
    }
    
    
    // définition des méthodes d'interface : récupérer les frais
    @Override
    public double get_frais() {
        return super.get_frais()+FRAIS_MINI_BAR ;
    }

    // définition des méthodes d'interface : récupérer la description
    @Override
    public String get_description() {
        return super.get_description() + " MiniBar,";
    }

    @Override
    public boolean est_occupee() {
        return super.est_occupee();
    }
    
    @Override
    public double get_taux_population() {
        return 0.5;
    }
}
