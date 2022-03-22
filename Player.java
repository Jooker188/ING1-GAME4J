import java.util.Scanner;

public class Player{
    public Board board;
    public int energy = 10;
    public boolean isDead = false;

    public Player(Board b){
        this.board = b;
    }

    public void action(){
        System.out.println("Where do you want to go ?");
        Scanner keyboard = new Scanner(System.in);
        String action = keyboard.nextLine();

        int x = this.board.getPlayer()[0];
        int y = this.board.getPlayer()[1];

        switch(action){ //deplacement avec ZQSD pour le moment
            case "z":
                this.board.setPlayer(x-1,y);
                break;
            case "q":
                this.board.setPlayer(x,y-1);
                break;
            case "s":
                this.board.setPlayer(x+1,y);
                break;
            case "d":
                this.board.setPlayer(x,y+1);
                break;
            default:
                break;
        }
        
        this.energy--;
    }
}