
package motelcafardapp;

import gestion_chambres.*;
import gestion_colonies.*;
import gestion_commodites.*;
import gestion_enregistrement.*;
import gestion_paiement.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * @author : Sabah HM
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static MotelCafard m;
    static ArrayList<String> list_chambres =  new ArrayList<String>( Arrays.asList("101","102", "103", "104", "105",
                                                                                    "201","202", "203", "204", "205") );
    public static void main(String[] args) {
        //  lancement des tests Motel Cafard ici
         test_preparation_motel();
         test_enregistrements();
         
         test_organiser_fete();
         
         test_file_attente();
                 
         test_afficher_journal();
    }
    
    
    // Méthode de tests //
    public static void test_preparation_motel() {
        // tester l'initialisation du motelcafar, chambres, ... etc
         m = MotelCafard.getInstance();
         System.out.print("\n -------- Test preparation du motelcafard  -------\n");
         m.creer_chambres(list_chambres);
         System.out.print(m);
         System.out.print("\n -------- Fin Test preparation du motelcafard  -------\n");
         continuer();
    }    
    
    public static void test_enregistrements() {
        // tester de creation des enregistrements
         m = MotelCafard.getInstance();
         System.out.print("\n -------- Test enregistrements  -------\n");
         m.creer_chambres(list_chambres);
         System.out.println(m);
         
         // créer des colonies 
         Colonie c1 = new Colonie("Colonie 1", 100, 200);
         Colonie c2 = new Colonie("Colonie 2", 100, 150);
         
         // choisir des commodités 
         ArrayList commodites_c1 = new ArrayList<>( Arrays.asList(
                                                    CommoditeChambre.COMMODITE_MINI_BAR,
                                                    CommoditeChambre.COMMODITE_SPA));
         ArrayList commodites_c2 = new ArrayList<>( Arrays.asList(CommoditeChambre.COMMODITE_DOUCHE));
         
         // créer les enregistrements //
         Enregistrement e1 = m.creer_enregistrement(c1, TypeChambre.CHAMBRE_SUITE, commodites_c1);
         Enregistrement e2 = m.creer_enregistrement(c2, TypeChambre.CHAMBRE_STANDARD, commodites_c2);
         System.out.print("\n  Ajout enregistrements e1 et e2 : \n");
         System.out.print(m);
         
         // finaliser enregistrement e1 //
         
         // calcul total e1 :
         double total = e1.calculer_frais(5);
         // création du paiement 
         Paiement p1 = new Paiement(total, new MasterCafard( "MASTER CAFARD AZERT", "FR043943CAF233443", "123", "02/2022"));
         String chambre_a_initialiser = e1.finaliser(p1); // finaliser e1, et recuperer la chambre à nettoyer
         
         m.nettoyer_chambre(chambre_a_initialiser);
         
         
         System.out.print("\n  Finalisation enregistrement e1 : \n");
         System.out.print(m);
         System.out.print("\n -------- Fin Test enregistrements -------\n");
         continuer();
    }
 
    public static void test_organiser_fete() {
        // tester l'augmentation de la population au cours d'une fete
         System.out.print("\n -------- Test Organiser Fete  -------\n");
         m.creer_chambres(list_chambres);
         //System.out.println(m);
         
         // créer des colonies 
         Colonie c1 = new Colonie("Colonie 1", 100, 200);
         Colonie c2 = new Colonie("Colonie 2", 100, 150);
         
         // choisir des commodités 
         ArrayList commodites_c1 = new ArrayList<>( Arrays.asList(
                                                    CommoditeChambre.COMMODITE_MINI_BAR,
                                                    CommoditeChambre.COMMODITE_SPA));
         ArrayList commodites_c2 = new ArrayList<>( Arrays.asList(CommoditeChambre.COMMODITE_DOUCHE));
         
         // créer les enregistrements //
         Enregistrement e1 = m.creer_enregistrement(c1, TypeChambre.CHAMBRE_SUITE, commodites_c1);
         Enregistrement e2 = m.creer_enregistrement(c2, TypeChambre.CHAMBRE_STANDARD, commodites_c2);
         System.out.print("\n  populations c1 et c2 avant fetes  : \n");
         System.out.println("c1 : "+ c1.getPopulation_actuelle() + " , taux : "+ c1.getTaux_croissance()+"%");
         System.out.println("c2 : "+ c2.getPopulation_actuelle()+ " , taux : "+ c2.getTaux_croissance()+"%");
         e1.organiser_fete();
         e2.organiser_fete();
         System.out.print("\n  populations c1 et c2 après fetes  : \n");
         System.out.println("c1 : "+ c1.getPopulation_actuelle());
         System.out.println("c2 : "+ c2.getPopulation_actuelle());         
         System.out.print(m);
         
         System.out.print("\n -------- Fin Test Organiser Fete  -------\n");
         continuer();
    }  
    
    public static void test_file_attente() {
        // tester l'initialisation du motelcafar, chambres, ... etc
         System.out.print("\n -------- Test File attente  -------\n");
         Enregistrement e1 = null;
         // ajout d'une dixaine de colonies
         for(int i = 0 ; i < 12 ; i++){
             // créer des colonies 
            Colonie c1 = new Colonie("Colonie TEMP "+i, 100+10*i, 10*i);

            // choisir des commodités 
            ArrayList commodites_c1 = new ArrayList<>( Arrays.asList(
                                                       i%3, (i+1)%3));

            // créer les enregistrements //
            e1 = m.creer_enregistrement(c1, i%3, commodites_c1);
         }
         // libérer une chambre --> deuxième enregistrement 
        e1 = m.getEnregistrements().get(1);
        double total = e1.calculer_frais(5);
        // création du paiement 
        Paiement p1 = new Paiement(total, new CafardPal( "mail@cafard.com", "FR043943CAF233443"));
        String chambre_a_initialiser = e1.finaliser(p1); // finaliser e1, et recuperer la chambre à nettoyer

        m.nettoyer_chambre(chambre_a_initialiser);
         System.out.print("\n"+m);
         System.out.print("\n -------- Fin Test File attente  -------\n");
         continuer();
    }  
   
    public static void test_afficher_journal() {
        // tester l'initialisation du motelcafar, chambres, ... etc
         System.out.print("\n -------- Test afficher journal  -------\n");
         m.afficher_journal();
         System.out.print("\n -------- Fin Test afficher journal  -------\n");
         continuer();
    }  
    
    // fonction pour arreter le console afin d'avancer les tests un par un
    private static void continuer() {
        System.out.println("\nAppuyer sur entrée pour continuer les tests.") ;
        Scanner sc = new Scanner(System.in);
        
        sc.nextLine();
    }
    
    
}
