
package gestion_chambres;

/*
 * @author : Sabah HM
 */
public interface TypeChambre {
    // constante pour les différents types chambre possibles 
    public static final int CHAMBRE_STANDARD = 1;
    public static final int CHAMBRE_LUXE = 2;
    public static final int CHAMBRE_SUITE = 3;
    public Chambre recuperer_chambre();
    
}
