
package motelcafardapp;

import gestion_chambres.*;
import gestion_colonies.*;
import gestion_commodites.*;
import gestion_enregistrement.*;
import java.util.ArrayList;

/*
 * @author : Sabah HM
 */
public class MotelCafard {
    
    // attributs privés
    private static MotelCafard instance; // instance de l'objet MotelCafard
    private ArrayList<Chambre> chambres; // liste des chambres
    private ArrayList<Colonie> colonie_en_attente; // liste des colonies en attente
    private FileAttente file_attente; // file d'attente
    private ArrayList<Enregistrement> enregistrements; // leste des enregistrements
    
    // constructeur par défaut privé 
    private MotelCafard() {
        chambres = new ArrayList();
        colonie_en_attente = new ArrayList();
        file_attente = new FileAttente();
        enregistrements = new ArrayList();
    }
    
    // récupérer instance
    public static MotelCafard getInstance(){
        if(instance == null){
            // initialiser cette instance si nulle 
            instance = new MotelCafard();
        }
        return instance;
    }
    
    // méthode de création des chmabres //
    public void creer_chambres(ArrayList<String> num_chambres){
        
        // vider le chambres initiales 
        chambres.clear();
        for(int i= 0 ; i < num_chambres.size() ; i++){
            chambres.add(new ChambreEquipee(num_chambres.get(i)));
        }
    }
    
    // méthode de création d'un enregistrement //
    public Enregistrement creer_enregistrement(Colonie c, int type_chambre, ArrayList<Integer> list_commodites){
        int indice_chambre = 0;
        // verifier disponibilité
        if(nb_chambres_disponibles() == 0){
            System.out.print("\n"+afficher_complet());
            // ajouter cette colonie à la liste d'attente 
            this.file_attente.addObserver(c);
            System.out.println("\n Colonie '"+c.getNom()+"' est ajouté à la file d'attente!");
            colonie_en_attente.add(c);
            return null;
        }
        // recupérer la position de la 1ere chambre disponible //
        for(int i= 0 ; i < chambres.size() ; i++){
            if(!chambres.get(i).est_occupee()){
                indice_chambre = i;
                break;
            }
        }
        String num_chambre = ((ChambreEquipee)chambres.get(indice_chambre)).getNumero();
        // else, créer l'enregistrement //
        chambres.set(indice_chambre, recuperer_chambre_par_type(type_chambre ,((ChambreEquipee)chambres.get(indice_chambre)).getNumero()));
        
        // ajouter les commodités //
        ajouter_commodite_chambre(list_commodites, indice_chambre);
        // rendre la chambre occupée
        (chambres.get(indice_chambre)).rendre_occupee(true);
        
        // ajouter l'enregistrement 
        Enregistrement e = new Enregistrement(chambres.get(indice_chambre), c,num_chambre );
        enregistrements.add(e);
        
        return e;
    }   

    // méthode d'affichage du journal des transaction de paiement //
    public void afficher_journal(){
        // renseigner les données d'enregistrement terminés
        for(int i= 0 ; i < enregistrements.size() ; i++){
            if(enregistrements.get(i).getEtat() == Enregistrement.Etat.TERMINE){
                // afficher cette transaction //
                enregistrements.get(i).afficher_transaction();
            }
        }
    }   
    // méthodes poir consulter le nombre de chambres disponibles / occupées
    public int nb_chambres_disponibles(){
        int nb_result= 0;
        // sommer le nombre des chambre non occupées
        for(int i= 0 ; i < chambres.size() ; i++){
            if(!chambres.get(i).est_occupee()){
                nb_result++;
            }
        }
        return nb_result;
    }    
    public int nb_chambres_occupees(){
        
        int nb_result= 0;
        // sommer le nombre des chambre occupées
        for(int i= 0 ; i < chambres.size() ; i++){
            if(chambres.get(i).est_occupee()){
                nb_result++;
            }
        }
        return nb_result;
    }    
    public String afficher_complet(){
        
        return ("\n\t_________________________________" +
                "\n\t         PAS DE PLACES!"+
                "\n\t_________________________________\n");
    }   

    // affichage MotelCafard
    @Override
    public String toString() {
        
        // recuperer le nombre des chambres disponible 
        int nb_dispo = nb_chambres_disponibles();
        
        String result =  "MotelCafard : " +
                "\n Nombre de chambres total : " + chambres.size();
        
        // si le motel est vide 
        if(chambres.size() == 0){
            result += (" \n\t_________________________" +
                            "\n\t    PAS DE CHAMBRES!"+
                            "\n\t_________________________\n");
            return result;
        }
        // verifier si le motelcafard est plein ou pas
        if(nb_dispo == 0){
            // motel compli
            result += afficher_complet();
        }else{
            // si ce n'est pas complet, afficher les chambre dispo
            // afficher les chmbres dispo
            result += "\n Chambres Disponibles : ";
            for(int i= 0 ; i < chambres.size() ; i++){
                if(!chambres.get(i).est_occupee()){
                    result += "\n\t - " + chambres.get(i).get_description();
                }
            }
        }
        // verifier si le motelcafard est vide ou pas
        if(nb_dispo == chambres.size()){
            // motel compli
            result += ("\n - Aucune chambre occupée!");
        }else{
            // afficher les chambres occupés
            result += "\n Chambres Occupées : ";
            for(int i= 0 ; i < chambres.size() ; i++){
                if(chambres.get(i).est_occupee()){
                    result += "\n\t - " + chambres.get(i).get_description() ;
                }
            }
        }
        
        // afficher le nombre de colonies en attente //
        result += "\n Colonies en attente : "+colonie_en_attente.size();
        
        return result;
    }
    
    // retourner une chambre selon son type
    public Chambre recuperer_chambre_par_type(int type_chambre, String num_chambre) {
        
        // retourner un objet chambre selon le type donné 
        switch(type_chambre){
            case TypeChambre.CHAMBRE_STANDARD :
                return new ChambreStandard(num_chambre);
            case TypeChambre.CHAMBRE_LUXE :
                return new ChambreLuxe(num_chambre);
            case TypeChambre.CHAMBRE_SUITE :
                return new ChambreSuite(num_chambre);
            default :
                return new ChambreEquipee(num_chambre);
        }
    }
    //  ajouter les commodité pour une chambre donné
    public void ajouter_commodite_chambre(ArrayList<Integer> list_commodites, int indice_chambre) {
        
        for(int i = 0 ; i < list_commodites.size() ; i++){
            // Pour chacune des commodités, reconstruire la chambre
            switch(list_commodites.get(i)){
                case CommoditeChambre.COMMODITE_MINI_BAR :
                    // verifier si chambre a des commodité ou pas //
                    if(chambres.get(indice_chambre) instanceof SPA || chambres.get(indice_chambre) instanceof MiniBar  || chambres.get(indice_chambre) instanceof Douche ){
                        //System.out.print("\n ---> chambre commodité <----- \n");
                        chambres.set(indice_chambre, new MiniBar( (CommoditeChambre)chambres.get(indice_chambre)));
                        break;
                    }
                    chambres.set(indice_chambre, new MiniBar( (ChambreEquipee)chambres.get(indice_chambre)));
                    break;
                case CommoditeChambre.COMMODITE_SPA:
                    // verifier si chambre a des commodité ou pas //
                    if(chambres.get(indice_chambre) instanceof SPA || chambres.get(indice_chambre) instanceof MiniBar  || chambres.get(indice_chambre) instanceof Douche ){
                        //System.out.print("\n ---> chambre commodité <----- \n");
                        chambres.set(indice_chambre, new SPA((CommoditeChambre)chambres.get(indice_chambre)));
                        break;
                    }
                    chambres.set(indice_chambre, new SPA((ChambreEquipee)chambres.get(indice_chambre)));
                    break;
                case CommoditeChambre.COMMODITE_DOUCHE:
                    // verifier si chambre a des commodité ou pas //
                    if(chambres.get(indice_chambre) instanceof SPA || chambres.get(indice_chambre) instanceof MiniBar  || chambres.get(indice_chambre) instanceof Douche ){
                        //System.out.print("\n ---> chambre commodité <----- \n");
                        chambres.set(indice_chambre, new Douche( (CommoditeChambre)chambres.get(indice_chambre)));
                        break;
                    }
                    chambres.set(indice_chambre, new Douche( (ChambreEquipee)chambres.get(indice_chambre)));
                    break;
                default :
                    
                    break;
            }
        }
        
    }
    
    public void nettoyer_chambre(String num_chambre){
        // initialiser l'etat d'une chambre pour les futures utilisations
        for(int i= 0 ; i < chambres.size() ; i++){
            if((chambres.get(i).getNumero().equals(num_chambre))){
                // chambre trouvée, initialiser la
                chambres.set(i, new ChambreEquipee(num_chambre));
                
                // notifier les colonies en attentes
                this.file_attente.notifier_colonies();
            }
        }
    }  
    
    // setters & getters //

    public ArrayList<Chambre> getChambres() {
        return chambres;
    }

    public void setChambres(ArrayList<Chambre> chambres) {
        this.chambres = chambres;
    }

    public ArrayList<Colonie> getColonie_en_attente() {
        return colonie_en_attente;
    }

    public void setColonie_en_attente(ArrayList<Colonie> colonie_en_attente) {
        this.colonie_en_attente = colonie_en_attente;
    }

    public FileAttente getFile_attente() {
        return file_attente;
    }

    public void setFile_attente(FileAttente file_attente) {
        this.file_attente = file_attente;
    }

    public ArrayList<Enregistrement> getEnregistrements() {
        return enregistrements;
    }

    public void setEnregistrements(ArrayList<Enregistrement> enregistrements) {
        this.enregistrements = enregistrements;
    }
    
}
