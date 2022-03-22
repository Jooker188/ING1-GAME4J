

public class Main {
    public static void main(String args[]){
        // Initialisation
        Board board = new Board();
        board.Init();
        Player p = new Player(board);

        //Mecanisme de jeu
        while(p.energy > 0 && !p.isDead){
            board.Display();    
            System.out.println("Energy left : " + p.energy);
            p.action();
            if(board.gameOver()){
                break;
            }
        }
        System.out.println("Game Over");
        p.showResult();
    }

}
