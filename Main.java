import java.util.Scanner;
import java.io.File;

public class Main {
    public static int Menu(){
        System.out.println("\n################# WELCOME TO GAME4J #################\n");
        System.out.println("1 - Generate a new game");
        System.out.println("2 - Load a previous game");
        System.out.println("3 - See games history");
        System.out.println("4 - Exit\n");
        Scanner keyboard = new Scanner(System.in);
        int action = keyboard.nextInt();

        switch(action){
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            default:
                break;
        }
       return -1;
    }

    public static boolean hasGames(){
        File file = new File("./games/");
        switch(file.list().length){
            case 0:
                return false;
        }
        return true;
    }

    public static void startReplay(int gameID, int speed){
        try {
            Data data = (Data) Ressources.Load("./games/game"+gameID+".save");
            Board board = new Board();
            board.setBoard();
            Player p = new Player(board);
            for (int[] obstacle : data.trees) {
                board.setTree(obstacle);
            }
            for (int[] obstacle : data.rocks) {
                board.setRock(obstacle);
            }
            for (int[] obstacle : data.fruits) {
                board.setFruit(obstacle);
            }
            for (int[] obstacle : data.meats) {
                board.setMeat(obstacle);
            }
            board.Display();
            for (int[] deplacement : data.playerDeplacements) {
                board.setPlayer(deplacement[0], deplacement[1]);
                board.Display();
                switch(speed){
                    case 1:
                        Thread.sleep(1500);
                        break;
                    case 2:
                        Thread.sleep(1000);
                        break;
                    case 3:
                        Thread.sleep(500);
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Couldn't load : " + e.getMessage());
        }
    }

    public static void main(String args[]){

        int start = Menu();
        switch(start){

            case 1:
                Board board = new Board();
                board.Init();
                Player p = new Player(board);
                board.isRealisable();
                while(p.energy > 0 && !p.isDead){
                    board.Display();    
                    System.out.println("\nEnergy left : " + p.energy);
                    String action = p.action();
                    if(action == "L"){
                        break;
                    }
                    if(board.gameOver()){
                        p.addToHistory();
                        break;
                    }
                }
                p.showResult();
                System.out.println("\nSee you soon !");
                break;

            case 2:
                try {
                    Data data = (Data) Ressources.Load("data.save");
                    System.out.println("\n############# PREVIOUS GAME LOADED #############");
                    System.out.println("\n Game from the : " + data.date);
                    Board boardRecover = new Board();
                    boardRecover.setBoard();
                    Player pRecover = new Player(boardRecover);
                    pRecover.energy = data.energy;
                    boardRecover.setPlayer(data.posPlayerX, data.posPlayerY);
                    for (int[] obstacle : data.trees) {
                        boardRecover.setTree(obstacle);
                    }
                    for (int[] obstacle : data.rocks) {
                        boardRecover.setRock(obstacle);
                    }
                    for (int[] obstacle : data.fruits) {
                        boardRecover.setFruit(obstacle);
                    }
                    for (int[] obstacle : data.meats) {
                        boardRecover.setMeat(obstacle);
                    }
                    while(pRecover.energy > 0 && !pRecover.isDead){
                        boardRecover.Display();    
                        System.out.println("\nEnergy left : " + pRecover.energy);
                        String action = pRecover.action();
                        if(action == "L"){
                            break;
                        }
                        if(boardRecover.gameOver()){
                            pRecover.addToHistory();
                            break;
                        }
                    }
                    pRecover.showResult();
                    System.out.println("\nSee you soon !");
                    break;
                } catch (Exception e) {
                    System.out.println("No game saved yet");
                }
                break;
            
            case 3:
                if(hasGames()){
                    File file = new File("./games/");
                    System.out.println("\n############# GAMES HISTORY #############");
                    System.out.println("\nSelect one :");
                    for(int i=0; i<file.list().length; i++){
                        try {
                            Data data = (Data) Ressources.Load("./games/game"+i+".save");
                            System.out.println(i + " - Game from " + data.date);
                        } catch (Exception e) {
                            System.out.println("Couldn't load : " + e.getMessage());
                        }
                    }
                    Scanner keyboard = new Scanner(System.in);
                    int action = keyboard.nextInt();
                    System.out.println("\nSelect Replay Speed :");
                    System.out.println("\n1 - Slow");
                    System.out.println("2 - Normal");
                    System.out.println("3 - Fast\n");
                    int actionSpeed = keyboard.nextInt();
                    
                    startReplay(action,actionSpeed);
                }
                else{
                    System.out.println("\nThere is no game to watch yet !");
                }
            case 4:
                System.out.println("\nSee you soon !");
                break;
            
            default:
                break;
            }

        }

}
