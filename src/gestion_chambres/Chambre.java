
package gestion_chambres;

/*
 * @author : Sabah HM
 */
public interface Chambre {
    public boolean est_occupee();
    public void rendre_occupee(boolean occupee);
    public double get_frais();
    public String get_description();
    public void init(String num_chambre);
    public String getNumero();
    public double get_taux_population();
}
