import java.util.Scanner;

public class Player{
    public Board board;
    public int energy = 10;
    public boolean isDead = false;
    public int energyWin = 0;
    public int energyLose = 0;
    public int distanceParcourure = 0;

    public Player(Board b){
        this.board = b;
    }

    public Player(char[][] board2) {
    }

    
    public void action(){
        System.out.println("Where do you want to go ?");
        Scanner keyboard = new Scanner(System.in);
        String action = keyboard.nextLine();

        int x = this.board.getPlayer()[0];
        int y = this.board.getPlayer()[1];
        int resultat = 0;
        

        switch(action){ //deplacement avec ZQSD pour le moment
            case "z":
                resultat=this.board.setPlayer(x-1,y);
                break;
            case "q":
                resultat=this.board.setPlayer(x,y-1);
                break;
            case "s":
                resultat=this.board.setPlayer(x+1,y);
                break;
            case "d":
                resultat=this.board.setPlayer(x,y+1);
                break;
            default:
                break;
        }
        this.energy--;
        if(resultat == 1){
            this.energy -= 10;
            energyLose += 10;
        }
        else if(resultat == 2){
            this.energy += 10;
            energyWin += 10;
            distanceParcourure += 1;
        }
        else{
            distanceParcourure += 1;
        }
    }

    public void showResult(){
        System.out.println("You have still " + this.energy + ", you win " + energyWin + " and lose " + energyLose + " energy");
        System.out.println("You have traveled " + distanceParcourure + " boxes");
    }
 
}