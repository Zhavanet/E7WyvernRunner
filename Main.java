import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {

    public static double tickValue; // taille de la barre de CR (exemple : si 300 speed et plus rapide alors tickValue = 300 )

    public static void main(String[] args) {
        ArrayList<Player> playerList = new ArrayList<>();
        Player p1 = new Player("Krau",195);
        Player p2 = new Player("Alencia",167);
        Player p3 = new Player("Fallen Cecilia ",209);
        Player p4 = new Player("ML Baal ",240);
        Player p5 = new Player("Kayron ",137);
        Player p6 = new Player("Fighter Maya ",99);
        Player p7 = new Player("Achates ",176);
        Player p8 = new Player("Karin",120);


        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        playerList.add(p5);
        playerList.add(p6);
        playerList.add(p7);
        playerList.add(p8);

        //speedRNG(playerList); // applique la speed RNG
        sortbyCR(playerList); // permet de setup les variables CR/% pour chaque personnage
        int compteur = 0;
        for (Player player : playerList) { // Initialise le CR et détermine le nombre de tick à diminuer pour que le prochain personnage joue
            player.CRinPercentage = player.speed*100/tickValue; // initialise la valeur et facilite l'affichage
            player.numberOftick = 100/(player.speed/tickValue); //
        }
        while(compteur < 10){ // simule le déroulement des tours de jeu
            //displayCR(playerList);
            CRcounter(playerList);
            compteur++;
        }
    }

    public static void speedRNG(ArrayList<Player> playerList) {
        for (Player player : playerList) { // Initialise le CR et détermine le nombre de tick à diminuer pour que le prochain personnage joue
            Random rand = new Random();
            double random = player.speed + Math.random() * (player.speed*1.05 - player.speed);
            player.speed = player.speed*random;
        }
    }


    public static void sortbyCR(ArrayList<Player> playerList) {
        ArrayList<Double> sortedCRList = new ArrayList<>(); // arraylist de speed
        //sortedCRList.add(243); // speed wyvern
        for (Player player : playerList) {
            sortedCRList.add(player.speed);
        }
        Collections.sort(sortedCRList);
        tickValue = sortedCRList.get(playerList.size()-1); // Construction de tickValue en fonction de la speed du plus rapide
    }

    public static void CRcounter(ArrayList<Player> playerList) {
        // Affichage t1
        double jumpCRbar = tickValue; // compteur inverse de tick commençant à tickValue, et diminue vers 0. Saute de joueur de joueur
        for (Player player2 : playerList) { // Affiche le CR de chaque personnage
            /*System.out.println(player2.name + " has a CR of : " + player2.CRinPercentage + "%");
            if (player2.CRinPercentage == 100) {
                player2.CRinPercentage = 0;
            }*/
        }

        // display CR
        ArrayList<Double> sortedCRList = new ArrayList<>();
        for (Player player : playerList) {
            sortedCRList.add(player.CRinPercentage);
        }
        Collections.sort(sortedCRList);
        for (int i = sortedCRList.size(); i-- > 0;) {
            for (Player player : playerList) {
                if (player.CRinPercentage == sortedCRList.get(i)) {
                    if (sortedCRList.get(i) == 100) {
                        System.out.println(player.name + "'s turn !");
                        System.out.println("-------------------------------------");
                    }
                    System.out.println(player.name + " has a CR of : " + String.format("%.2f", sortedCRList.get(i)) +"%");
                    if (player.CRinPercentage == 100) {
                        player.CRinPercentage = 0;
            }
                }
            }
        }

        // on se prépare à update pour le t2
        for (Player player : playerList) { // Initialise le CR et détermine le nombre de tick à diminuer pour que le prochain personnage joue
            player.numberOftick = (100 - player.CRinPercentage) / (player.speed/tickValue); // nombre de ticks à réaliser pour atteindre 100%
                if (player.numberOftick < jumpCRbar){
                    jumpCRbar = player.numberOftick;
                }
        }
        for (Player player : playerList) {
            player.numberOftick -= jumpCRbar; // actualise la CR bar de tout le monde en décalant tout le monde linéairement
            // du nombre de tick du joueur qui atteint la barre 0 en premier
            player.CRinPercentage = 100*(1-player.numberOftick/ (tickValue*100/player.speed)); // current CR = nb_ticks/total_ticks

            if (player.CRinPercentage == 0) {
                player.CRinPercentage = 100;
            }
        }
        System.out.println("------------------------------------- \n" +
                "\n" +
                "-------------------------------------"
        );
    }
}
