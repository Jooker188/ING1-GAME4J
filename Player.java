public class Player{
    public int age;
    public String nom;

    public Player(int age, String nom){
        this.age = age;
        this.nom = nom;
    }
    public static void main(String args[]){
        Player p = new Player(7, "rollo");
        System.out.println("J'ai " + p.age +" ans\n" + "Et je m'appelle " + p.nom);
    }
}