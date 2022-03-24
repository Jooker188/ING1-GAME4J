import java.util.Scanner;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static int Menu(){
        System.out.println("\n################# WELCOME TO GAME4J #################\n");
        System.out.println("1 - Generate a new game");
        System.out.println("2 - Load a previous game");
        System.out.println("3 - Exit\n");
        Scanner keyboard = new Scanner(System.in);
        int action = keyboard.nextInt();

        switch(action){
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            default:
                break;
        }
       return -1;
    }
    public static void main(String args[]){

        int start = Menu();
        switch(start){

            case 1:
                Board board = new Board();
                board.Init();
                Player p = new Player(board);

                while(p.energy > 0 && !p.isDead){
                    board.Display();    
                    System.out.println("\nEnergy left : " + p.energy);
                    String action = p.action();
                    if(board.gameOver() | action == "L"){
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
                        if(boardRecover.gameOver() | action == "L"){
                            break;
                        }
                    }
                    pRecover.showResult();
                    System.out.println("\nSee you soon !");
                    break;
                } catch (Exception e) {
                    System.out.println("Couldn't save : " + e.getMessage());
                }
                break;

            case 3:
                System.out.println("\nSee you soon !");
                break;
            
            default:
                break;
            }

        }

}
