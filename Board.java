public class Board {
    private static int DIM_X;
    private static int DIM_Y;
    private char[][] board;

    public Board(){
        DIM_X = 20;
        DIM_Y = 40;
        this.board = new char[DIM_X][DIM_Y];
    }

    public void setBoard(){
        for(int i=0; i<DIM_X; i++){
            for(int j=0; j<DIM_Y; j++){
                if (i == 0 || i == DIM_X-1){  //Mise en place des murs horizontaux
                    this.board[i][j] = '#';
                }
                else if(j == 0 || j == DIM_Y-1){ //Mise en place des murs verticaux
                    this.board[i][j] = '#';
                }
                else if( i == 1 && j == 2){ //Positionnement du Joueur en debut de partie
                    this.board[i][j] = 'P';
                }
                else if( i == DIM_X-2 && j == DIM_Y-3){ //Positionnement de la maison a atteindre
                    this.board[i][j] = 'M';
                }
                else{
                    this.board[i][j] = ' ';  // Mise en place case vide
                }
            }
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
            System.out.println("You hit an obstacle, you return to the previous square and lose 10 life points");
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
        if(getPlayer()[0] == DIM_X-2 && getPlayer()[1] == DIM_Y-3){
            System.out.println("Vous avez atteint votre maison !");
            return true;
        }
        return false;
    }

    public void realisable(){
        
    }

    public void Init(){
        setBoard();
        setRandomObstacle();
        setRandomBonus();
    }
}
