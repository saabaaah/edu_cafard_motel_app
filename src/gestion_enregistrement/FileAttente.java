
package gestion_enregistrement;

import java.util.Observable;
import java.util.Observer;

/*
 * @author : Sabah HM
 */
public class FileAttente extends Observable{
    
    // methode pour notifier les colonies en attente
    public void notifier_colonies(){
        // TODO, parcourir et notifier les colonies
        setChanged();
        this.notifyObservers(this);
    }
   
    
}
