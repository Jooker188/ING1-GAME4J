import java.util.ArrayList; 

public class Board{
    public static int DIM_X;
    public static int DIM_Y;
    private char[][] board;
    private Player p;
    public ArrayList<int[]> allMeats;
    public ArrayList<int[]> allFruits;
    public ArrayList<int[]> allRocks;
    public ArrayList<int[]> allTrees;


    public Board(){
        DIM_X = 13;
        DIM_Y = 13;
        this.board = new char[DIM_X][DIM_Y];
        this.p = new Player(this);
    }

    public void setBoard(){
        for(int i=0; i<DIM_X; i++){
            for(int j=0; j<DIM_Y; j++){
                // if (i == 0 || i == DIM_X-1){  //Mise en place des murs horizontaux
                //     this.board[i][j] = '#';
                // }
                // else if(j == 0 || j == DIM_Y-1){ //Mise en place des murs verticaux
                //     this.board[i][j] = '#';
                // }
                // else 
                if( i == 0 && j == 0){ //Positionnement du Joueur en debut de partie
                    this.board[i][j] = 'P';
                }
                // else if( i == DIM_X-2 && j == DIM_Y-3){ //Positionnement de la maison a atteindre
                //     this.board[i][j] = 'M';
                // }
                else{
                    this.board[i][j] = ' ';  // Mise en place case vide
                }
            }
        }
    }

    public boolean isBorder(int x, int y){
        if(this.board[x][y] == '#'){
            return true;
        }
        else{
            return false;
        }
    }

    public void setRandomObstacle(){
        int nbrArbre = 10;
        while(nbrArbre > 0){
            int caseX = (int) (DIM_X * Math.random());
            int caseY = (int) (DIM_Y * Math.random());

            if(this.board[caseX][caseY] == ' '){
                this.board[caseX][caseY] = 'A'; // Mise en place des arbres
                nbrArbre--;
            }
        }

        int nbrRocher = 10;
        while(nbrRocher > 0){
            int caseX = (int) (DIM_X * Math.random());
            int caseY = (int) (DIM_Y * Math.random());

            if(this.board[caseX][caseY] == ' '){
                this.board[caseX][caseY] = 'R'; // Mise en place des rochers
                nbrRocher--;
            }
        }
    }

    public void setRandomBonus(){
        int nbrViande = 10;
        while(nbrViande > 0){
            int caseX = (int) (DIM_X * Math.random());
            int caseY = (int) (DIM_Y * Math.random());

            if(this.board[caseX][caseY] == ' '){
                this.board[caseX][caseY] = 'V'; // Mise en place des viandes
                nbrViande--;
            }
        }

        int nbrFruit = 10;
        while(nbrFruit > 0){
            int caseX = (int) (DIM_X * Math.random());
            int caseY = (int) (DIM_Y * Math.random());

            if(this.board[caseX][caseY] == ' '){
                this.board[caseX][caseY] = 'F'; // Mise en place des fruits
                nbrFruit--;
            }
        }
    }

    public void setTree(int[] tree){
        this.board[tree[0]][tree[1]] = 'A';
    }
    public void setRock(int[] rock){
        this.board[rock[0]][rock[1]] = 'R';
    }
    public void setFruit(int[] fruit){
        this.board[fruit[0]][fruit[1]] = 'F';
    }
    public void setMeat(int[] meat){
        this.board[meat[0]][meat[1]] = 'V';
    }

    public void Display(){
        System.out.println();

        for(int i=0; i<DIM_X; i++){
            for(int j=0; j<DIM_Y; j++){
                System.out.print(this.board[i][j]);
            }
            System.out.println();
        }

    }

    public int[] getPlayer() {
		int positionX = 0;
		int positionY = 0;
		
		boolean exit = false;
		
		for (int i=0; i<DIM_X && exit == false; i++) {
			for (int j=0; j<DIM_Y && exit == false; j++) {
				if (this.board[i][j] == 'P'){
					exit = true;
				}
				positionY = j;
			}
			positionX = i;
		}
		return new int[] {positionX,positionY};
	}

    

    public boolean isTree(int x, int y){
        if(this.board[x][y] == 'A'){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isRock(int x, int y){
        if(this.board[x][y] == 'R'){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isMeat(int x, int y){
        if(this.board[x][y] == 'V'){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isFruit(int x, int y){
        if(this.board[x][y] == 'F'){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isPlayer(int x, int y){
        if(this.board[x][y] == 'P'){
            return true;
        }
        else{
            return false;
        }
    }
    public ArrayList<int[]> getAllTrees(){
        ArrayList<int[]> obstacles = new ArrayList<int[]>();

        for(int i=0; i<DIM_X; i++){
            for(int j=0; j<DIM_Y; j++){
                if(isTree(i,j)){
                    int[] currentObstacle = {i,j};
                    obstacles.add(currentObstacle);
                }
            }
        }
        this.allTrees = obstacles;
        return obstacles;
    }

    public ArrayList<int[]> getAllRocks(){
        ArrayList<int[]> obstacles = new ArrayList<int[]>();

        for(int i=0; i<DIM_X; i++){
            for(int j=0; j<DIM_Y; j++){
                if(isRock(i,j)){
                    int[] currentObstacle = {i,j};
                    obstacles.add(currentObstacle);
                }
            }
        }
        this.allRocks = obstacles;
        return obstacles;
    }

    public ArrayList<int[]> getAllMeats(){
        ArrayList<int[]> obstacles = new ArrayList<int[]>();

        for(int i=0; i<DIM_X; i++){
            for(int j=0; j<DIM_Y; j++){
                if(isMeat(i,j)){
                    int[] currentObstacle = {i,j};
                    obstacles.add(currentObstacle);
                }
            }
        }
        this.allMeats = obstacles;
        return obstacles;
    }

    public ArrayList<int[]> getAllFruits(){
        ArrayList<int[]> obstacles = new ArrayList<int[]>();

        for(int i=0; i<DIM_X; i++){
            for(int j=0; j<DIM_Y; j++){
                if(isFruit(i,j)){
                    int[] currentObstacle = {i,j};
                    obstacles.add(currentObstacle);
                }
            }
        }
        this.allFruits = obstacles;
        return obstacles;
    }
    public int setPlayer(int x, int y){
        
        if(this.board[x][y] == ' '){
            for(int i=0; i<DIM_X; i++){
                for(int j=0; j<DIM_Y; j++){
                    if(this.board[i][j] == 'P'){  //on efface l'ancienne position du joueur
                        this.board[i][j] = ' ';
                    }
                }
            }
            this.board[x][y] = 'P'; //on place la nouvelle position du joueur
            return 0; //la case est vide
        }
        else if(this.board[x][y] == 'A' || this.board[x][y] == 'R'){
            System.out.println("You have hit an obstacle, you return to the previous position and lose 10 life points");
            return 1; //la case est un obstacle
        }
        else{
            for(int i=0; i<DIM_X; i++){
                for(int j=0; j<DIM_Y; j++){
                    if(this.board[i][j] == 'P'){  //on efface l'ancienne position du joueur
                        this.board[i][j] = ' ';
                    }
                }
            }
            this.board[x][y] = 'P'; //on place la nouvelle position du joueur
            return 2; //la case est a manger
        }
        
    }

    public boolean gameOver(){
        if(getPlayer()[0] == DIM_X-1 && getPlayer()[1] == DIM_Y-1){
            System.out.println("You have reached your House !");

            return true;
        }
        return false;
    } 

    public boolean isRealisable(){
        int range = this.p.energy;
        while(range > 0){
            for(int i = 0; i<range; i++){
                int newX1 = getPlayer()[0] + i;
                int newX2 = getPlayer()[0] - i;
                int newY1 = getPlayer()[1] + i;
                int newY2 = getPlayer()[1] - i;

                if(this.board[newX1][getPlayer()[1]] != ('V' |'F') && 
                   this.board[newX2][getPlayer()[1]] != ('V' |'F') &&
                   this.board[getPlayer()[0]][newY1] != ('V' |'F') &&
                   this.board[getPlayer()[0]][newY2] != ('V' |'F')){
                        System.out.println("false");
                        return false; 
                }
            }
        }
        System.out.println("true");
        return true;
    }

    public void Init(){
        setBoard();
        setRandomObstacle();
        setRandomBonus();
        getAllFruits();
        getAllMeats();
        getAllRocks();
        getAllTrees();
    }
}
