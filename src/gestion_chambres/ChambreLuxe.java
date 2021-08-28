
package gestion_chambres;

/*
 * @author : Sabah HM
 */
public class ChambreLuxe extends ChambreEquipee implements TypeChambre{
    
    // constante pour les frais Luxe
    public static final int FRAIS_LUXE = 75;

    public ChambreLuxe(String numero) {
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
        return super.get_frais()+FRAIS_LUXE;
    }

    // définition des méthodes d'interface : récupérer la description
    @Override
    public String get_description() {
        return super.get_description() + ", Luxe";
    }
}
