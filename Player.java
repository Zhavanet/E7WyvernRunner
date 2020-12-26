public class Player {

    public double speed;
    public String name;
    //public String team;
    public double CRinPercentage;
    public double numberOftick;

    public Player(String name, double speed) {
        this.speed = speed;
        this.name = name;
        //this.team = team;
    }

    public void playerPlayed() {
        System.out.println(name + " played !"); // affichage console preuve d'avoir joué
    }
}
