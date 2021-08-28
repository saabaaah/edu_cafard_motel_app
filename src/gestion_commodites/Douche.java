
package gestion_commodites;

import gestion_chambres.*;

/*
 * @author : Sabah HM
 */
public class Douche extends CommoditeChambre{
    
    // constante pour les frais Douche
    public static final int FRAIS_DOUCHE = 25;

    public Douche(ChambreEquipee type_chambre) {
        super(type_chambre);
    }

    public Douche(CommoditeChambre comm_chambre) {
        super(comm_chambre);
    }
    
    // définition des méthodes d'interface : récupérer les frais
    @Override
    public double get_frais() {
        return super.get_frais()+FRAIS_DOUCHE ;
    }

    // définition des méthodes d'interface : récupérer la description
    @Override
    public String get_description() {
        return super.get_description() + " Douche résistante à l’insecticide,";
    }

    @Override
    public boolean est_occupee() {
        return super.est_occupee();
    }
    
    @Override
    public double get_taux_population() {
        return 0.25;
    }
}
