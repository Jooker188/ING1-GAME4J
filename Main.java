import java.util.Scanner;
import java.io.*;

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
    public static void Load(){
        BufferedReader lect;
        try{
            FileInputStream fichier = new FileInputStream("sauvegarde.txt" );
            ObjectInputStream ois = new ObjectInputStream(fichier);
            int[] load =  (int[]) ois.readObject();
                
            System.out.println(load);
        }
        catch (java.io.IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
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
                    System.out.println("Energy left : " + p.energy);
                    String action = p.action();
                    if(board.gameOver() | action == "L"){
                        break;
                    }
                }
                p.showResult();

            case 2:
                Load();
            
            case 3:
            System.out.println("\nSee you soon !");
                break;
            
            default:
                break;
            }

        }

}
