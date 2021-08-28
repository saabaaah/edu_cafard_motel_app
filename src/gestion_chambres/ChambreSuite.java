
package gestion_chambres;

/*
 * @author : Sabah HM
 */
public class ChambreSuite extends ChambreEquipee implements TypeChambre{
    
    // constante pour les frais Suite
    public static final int FRAIS_SUITE = 100;
    
    
    public ChambreSuite(String numero) {
        super(numero);
    }
    // retourner la chambre actuelle 
    @Override
    public Chambre recuperer_chambre() {
        return this;
    }
    // définition des méthodes d'interface : récupérer les frais
    @Override
    public double get_frais() {
        return super.get_frais()+FRAIS_SUITE;
    }

    // définition des méthodes d'interface : récupérer la description
    @Override
    public String get_description() {
        return super.get_description() + ", Suite";
    }
}
