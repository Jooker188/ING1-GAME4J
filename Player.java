import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.*;
import java.io.File;

public class Player{
    public Board board;
    public int energy;
    public boolean isDead = false;
    public int energyWin = 0;
    public int energyLose = 0;
    public int distanceParcourure = 0;
    public long startTime;
    public long endTime;
    public ArrayList<int[]> pathUsed1;
    public ArrayList<int[]> pathUsed2;
    public int undo;

    public Player(Board b){
        this.board = b;
        this.startTime = System.currentTimeMillis();
        this.pathUsed1 = new ArrayList<int[]>();
        this.pathUsed2 = new ArrayList<int[]>();
        int[] startPos = {1,2};
        this.pathUsed1.add(startPos);
        this.pathUsed2.add(startPos);
        this.undo = 6;
        this.energy = (int) ((Math.random() * (15 - 10)) + 10);  // int) ((Math.random() * (max - min)) + min);
    }

    public Player(char[][] board2) {
    }

    
    public String action(){
        System.out.println("You can move forwards (Z,Q,S,D), backwards (B) or save the game and quit (L)");
        Scanner keyboard = new Scanner(System.in);
        String action = keyboard.nextLine();

        int x = this.board.getPlayer()[0];
        int y = this.board.getPlayer()[1];
        int resultat = 0;
        

        switch(action){ //deplacement avec ZQSD pour le moment
            case "z":
                if(!this.board.isBorder(x-1,y)){
                    resultat=this.board.setPlayer(x-1,y);
                    int[] currentBox = {x-1,y};
                    boucle();
                    this.pathUsed1.add(currentBox);
                    this.pathUsed2.add(currentBox);
                   
                
                }
                else{
                    System.out.println("\nThis is a Wall");
                }
                break;
            case "q":
                if(!this.board.isBorder(x,y-1)){
                    int[] currentBox = {x,y-1};
                    resultat=this.board.setPlayer(x,y-1);
                    boucle();
                    this.pathUsed1.add(currentBox);
                    this.pathUsed2.add(currentBox);
                    
                }
                else{
                    System.out.println("\nThis is a Wall");
                }
                break;
            case "s":
                if(!this.board.isBorder(x+1,y)){
                    int[] currentBox = {x+1,y};
                    resultat=this.board.setPlayer(x+1,y);
                    boucle();
                    this.pathUsed1.add(currentBox);
                    this.pathUsed2.add(currentBox);
                    
                }
                else{
                    System.out.println("\nThis is a Wall");
                }
                break;
            case "d":
                if(!this.board.isBorder(x,y+1)){
                    int[] currentBox = {x,y+1};
                    resultat=this.board.setPlayer(x,y+1);
                    boucle();
                    this.pathUsed1.add(currentBox);
                    this.pathUsed2.add(currentBox);
                    
                }
                else{
                    System.out.println("\nThis is a Wall");
                }
                break;
            case "l":
                Data data = new Data();
                data.posPlayerX = this.board.getPlayer()[0];
                data.posPlayerY = this.board.getPlayer()[1];
                data.meats = this.board.getAllMeats();
                data.fruits = this.board.getAllFruits();
                data.rocks = this.board.getAllRocks();
                data.trees = this.board.getAllTrees();
                data.energy = this.energy;
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                data.date = dtf.format(LocalDateTime.now());
                try{
                    Ressources.Save(data, "data.save");
                    System.out.println("\nGame Saved Successfully !");
                }
                catch(Exception e){
                    System.out.println("Couldn't save : " + e.getMessage());
                }
                return "L";

            case "b":
                if(this.undo > 0){
                    if(this.pathUsed1.size()>1){
                        this.pathUsed1.remove(this.pathUsed1.size()-1);
                        this.board.setPlayer(this.pathUsed1.get(this.pathUsed1.size()-1)[0],this.pathUsed1.get(this.pathUsed1.size()-1)[1]);
                        this.undo--;
                        int[] currentBox = {this.pathUsed1.get(this.pathUsed1.size()-1)[0],this.pathUsed1.get(this.pathUsed1.size()-1)[1]};
                        boucle();
                        this.pathUsed2.add(currentBox);
                        
                    }
                    else{
                        System.out.println("This is the initial position");
                    }
                }
                else{
                    System.out.println("You can't undo your deplacement anymore");
                }
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
        return "a";
    }

    public void boucle(){
        ArrayList<int[]> tmp = new ArrayList<>();
        for(int i = 0; i<this.pathUsed2.size();i++){
            if(this.pathUsed2.get(i)[0] == this.board.getPlayer()[0] &&
               this.pathUsed2.get(i)[1] == this.board.getPlayer()[1]){
                    System.out.println("Already traveled, here is the boucle since then :");
                    for(int j=tmp.size(); j<this.pathUsed2.size(); j++){
                        System.out.println("---->" + Arrays.toString(this.pathUsed2.get(j)));
                    }
            }
            else{
                tmp.add(this.pathUsed2.get(i));
            }
           
        }
    }

    public void addToHistory(){
        Data data = new Data();
        data.playerDeplacements = this.pathUsed2;
        data.meats = this.board.allMeats;
        data.fruits = this.board.allFruits;
        data.rocks = this.board.allRocks;
        data.trees = this.board.allTrees;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        data.date = dtf.format(LocalDateTime.now());
        File file = new File("./games/");
        try{
            Ressources.Save(data, "./games/game"+file.list().length+".save");
            System.out.println("\nGame Saved Successfully !");
        }
        catch(Exception e){
            System.out.println("Couldn't save : " + e.getMessage());
        }   
    }

    public void showResult(){
        System.out.println("\n############# PLAYER ENERGY INFORMATIONS #############");
        System.out.println("Energy left : " + this.energy);
        System.out.println("Energy won  : " + energyWin);
        System.out.println("Energy used : " + energyLose);
        System.out.println("\n############# PLAYER DISTANCE INFORMATIONS #############");
        System.out.println("You have traveled " + this.pathUsed2.size() + " boxes");
        for (int[] boxe : this.pathUsed2) {
            System.out.println("----> "+Arrays.toString(boxe));
        }
        System.out.println("\n############# GAME INFORMATIONS #############");
        this.endTime = System.currentTimeMillis();
        long second = (this.endTime-this.startTime)/1000;
        long minute = second / 60;
        if(second >= 60){
            second = second % 60;
        }
        System.out.println("Time played : " + minute + "m "+ second +"s");
    }
 
}