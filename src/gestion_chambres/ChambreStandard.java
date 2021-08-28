
package gestion_chambres;

/*
 * @author : Sabah HM
 */
public class ChambreStandard extends ChambreEquipee implements TypeChambre{
    
    // constante pour les frais standard
    public static final int FRAIS_STANDARD = 50;

    public ChambreStandard(String numero) {
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
        return super.get_frais()+FRAIS_STANDARD;
    }

    // définition des méthodes d'interface : récupérer la description
    @Override
    public String get_description() {
        return super.get_description() + ", Standard";
    }
}
