
package gestion_chambres;

/*
 * @author : Sabah HM
 */
public class ChambreEquipee implements Chambre, TypeChambre{
    
    // attributs privés
    private String numero;
    private double frais = 0;
    private boolean est_occupee = false; // par défaut disponible

    // constructeur avec numéro //
    public ChambreEquipee(String numero) {
        this.numero = numero;
    }

    // définition des méthodes d'interface : récupérer l'etat est occupée
    @Override
    public boolean est_occupee() {
        return est_occupee;
    }

    // définition des méthodes d'interface : récupérer les frais
    @Override
    public double get_frais() {
        return 0;
    }

    // définition des méthodes d'interface : récupérer la description
    @Override
    public String get_description() {
        return "Chambre : N° "+ numero + ", " + ((est_occupee)? "Occupée": "Disponible");
    }
    
    // modificateurs  pour rendre la chambre occupee ou disponible
    @Override
    public void rendre_occupee(boolean est_occupee) {
        this.est_occupee = est_occupee;
    }
    
    @Override
    public Chambre recuperer_chambre() {
        return this;
    }
    
    @Override
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public void init(String num_chambre) {
        est_occupee =false;
    }
    
    @Override
    public double get_taux_population() {
        return 0.50;
    }

}
