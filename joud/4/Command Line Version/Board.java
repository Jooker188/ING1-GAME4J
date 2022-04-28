import java.util.ArrayList; 
import java.io.*;

public class Board{
    public static int DIM_X;
    public static int DIM_Y;
    public char[][] board;
    // private Player p;
    private ArrayList<int[]> allMeats;
    private ArrayList<int[]> allFruits;
    private ArrayList<int[]> allRocks;
    private ArrayList<int[]> allTrees;
    private ArrayList<int[]> shortestPathDistance;
    private ArrayList<int[]> shortestPathEnergy;
    private float taux_obstacle = 0.2f;
    private float taux_bonus = 0.2f;

    public Board(){
        DIM_X = 10;
        DIM_Y = 10;
        this.board = new char[DIM_X][DIM_Y];
        // this.p = new Player(this);
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
                else if( i == DIM_X-1 && j == DIM_Y-1){ //Positionnement de la maison a atteindre
                    this.board[i][j] = 'M';
                }
                else{
                    this.board[i][j] = ' ';  // Mise en place case vide
                }
            }
        }
    }

    public ArrayList<int[]> getShortestPathDistance() {
        return shortestPathDistance;
    }

    public void setShortestPathDistance(ArrayList<int[]> shortestPathDistance) {
        this.shortestPathDistance = shortestPathDistance;
    }

    public ArrayList<int[]> getShortestPathEnergy() {
        return shortestPathEnergy;
    }

    public void setShortestPathEnergy(ArrayList<int[]> shortestPathEnergy) {
        this.shortestPathEnergy = shortestPathEnergy;
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
        int nbrArbre = Math.round(DIM_X*DIM_Y*taux_bonus)/2;
        while(nbrArbre > 0){
            int caseX = (int) (DIM_X * Math.random());
            int caseY = (int) (DIM_Y * Math.random());

            if(this.board[caseX][caseY] == ' '){
                this.board[caseX][caseY] = 'A'; // Mise en place des arbres
                nbrArbre--;
            }
        }

        int nbrRocher = Math.round(DIM_X*DIM_Y*taux_bonus)/2;
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
        int nbrViande = Math.round(DIM_X*DIM_Y*taux_obstacle)/2;
        while(nbrViande > 0){
            int caseX = (int) (DIM_X * Math.random());
            int caseY = (int) (DIM_Y * Math.random());

            if(this.board[caseX][caseY] == ' '){
                this.board[caseX][caseY] = 'V'; // Mise en place des viandes
                nbrViande--;
            }
        }

        int nbrFruit = Math.round(DIM_X*DIM_Y*taux_obstacle)/2;
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
    public boolean isHouse(int x, int y){
        if(this.board[x][y] == 'M'){
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
        else if(this.board[x][y] == 'M'){
            for(int i=0; i<DIM_X; i++){
                for(int j=0; j<DIM_Y; j++){
                    if(this.board[i][j] == 'P'){  //on efface l'ancienne position du joueur
                        this.board[i][j] = ' ';
                    }
                }
            }
            this.board[x][y] = 'P'; //on place la nouvelle position du joueur
            return 3; // la case est la maison
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

    public void resetAll(){
        for(int i=0; i<DIM_X; i++){
            for(int j=0; j<DIM_Y; j++){
                
                    this.board[i][j] = ' ';  // Mise en place case vide
            }
        }
    }

    public int[][] genererDistances(int n){
        int sommets = n*n;
        int[][] distances = new int[sommets][sommets];

        //on genere un tableau de distances a chaque nouvelle partie
        for(int i=0; i<sommets; i++){
            for(int j=0; j<sommets; j++){
                if(i==0 && j==0){
                    distances[i][j] = 0;
                }
                else{
                    int randomDistance = (int) (100 * Math.random()) + 50; //entre 50 et 150
                    distances[i][j] = randomDistance;
                }
            }
        }
        return distances;
    }

    public int[][] generateMatriceAdjacence(int n) throws IOException{
        int sommets = n*n;
        int[][] matrice = new int[sommets][sommets];

        try{
            FileReader fr = new FileReader("./matrices/10x10");
            BufferedReader input = new BufferedReader(fr);
            int y = 0;
            while (input.ready()) {
                String s = input.readLine();
                for (int x = 0; x < sommets; x++){
                    matrice[x][y] = Integer.parseInt(s.substring(x, x+1));
                }
                y++;
            }
            input.close();
        }
        catch(Exception e){
            
        }

        
        ArrayList<Integer> numObstacle = whereAreItem("obstacles");

        for(int i=0;i<sommets;i++){
            for(int j=0; j<sommets; j++){
                for(int k=0; k<numObstacle.size();k++){
                    if(j==numObstacle.get(k)){
                        matrice[i][j]=0;
                    }
                    if(i==sommets-1){
                        matrice[i][j] = 1; // Juste pour regler un bug, aucune repercution sur le gameplay puisque quand i=sommet-1 on est arrivé a la maison
                    }
                }
            }
        }

        for(int i=0;i<sommets;i++){
            for(int j=0; j<sommets; j++){
                if(matrice[i][j] == 1){
                    matrice[i][j] = 10;
                }
            }
        }

        ArrayList<Integer> numBonus = whereAreItem("bonuses");
        
        for(int i=0;i<sommets;i++){
            for(int j=0; j<sommets; j++){
                for(int k=0; k<numBonus.size();k++){
                    if(j==numBonus.get(k)){
                        if(matrice[i][j]==10){
                            matrice[i][j]= 1;
                        }
                    }
                }
            }
        }
        
        for(int i=0;i<sommets;i++){
            for(int j=0; j<sommets; j++){
                int adjacence = 0;
                if(matrice[i][j] == 10 || matrice[i][j] == 1){
                    adjacence++;
                    if(adjacence>1){
                        matrice[i][j] = 0;
                    }
                }
            }
        }

         //System.out.println();
         //for(int i=0;i<sommets;i++){
         //    for(int j=0; j<sommets; j++){
         //        System.out.print(matrice[i][j] + "\t");
         //    }
         //    System.out.println();
         //}
        return matrice;

    }


    // Ex : pour matrice en 3x3 n=3 ---> sommets = 9
    // méthode qui va lire une matrice d'adjacence de dimension n 
    // et qui va générer la version pondéré de celle-ci
    public int[][] generateMatriceAdjacencePondere(int n) throws IOException{
        int sommets = n*n;
        int[][] matrice = new int[sommets][sommets];

        try{
            FileReader fr = new FileReader("./matrices/10x10");
            BufferedReader input = new BufferedReader(fr);
            int y = 0;
            while (input.ready()) {
                String s = input.readLine();
                for (int x = 0; x < sommets; x++){
                    matrice[x][y] = Integer.parseInt(s.substring(x, x+1));
                }
                y++;
            }
            input.close();
        }
        catch(Exception e){
            
        }

        ArrayList<Integer> numObstacle = whereAreItem("obstacles");

        for(int i=0;i<sommets;i++){
            for(int j=0; j<sommets; j++){
                for(int k=0; k<numObstacle.size();k++){
                    if(j==numObstacle.get(k)){
                        matrice[i][j]=0;
                    }
                    if(i==sommets-1){
                        matrice[i][j] = 1; // Juste pour regler un bug, aucune repercution sur le gameplay puisque quand i=sommet-1 on est arrivé a la maison
                    }
                }
            }
        }

        //verification par affichage
        //System.out.println();
        //for(int i=0;i<sommets;i++){
        //    for(int j=0; j<sommets; j++){
        //        System.out.print(matrice[i][j] + "\t");
        //    }
        //    System.out.println();
        //}
    

        int[][]distances = genererDistances(10);
        //on pondere la matrice avec les distances
        for(int i=0;i<sommets;i++){
            for(int j=0; j<sommets; j++){
                if(matrice[i][j] == 1){
                    matrice[i][j] = distances[i][j];
                }
            }
        }


        return matrice;
    }



	public ArrayList<Integer> dijkstra(int[][] adjacencyMatrix,int startVertex){
		int nVertices = adjacencyMatrix[0].length;

		int[] shortestDistances = new int[nVertices];

		boolean[] added = new boolean[nVertices];

		for (int vertexIndex = 0; vertexIndex < nVertices;vertexIndex++){
			shortestDistances[vertexIndex] = Integer.MAX_VALUE;
			added[vertexIndex] = false;
		}
		
		shortestDistances[startVertex] = 0;
		int[] parents = new int[nVertices];
		parents[startVertex] = -1;

		for (int i = 1; i < nVertices; i++){
			int nearestVertex = -1;
			int shortestDistance = Integer.MAX_VALUE;
			for (int vertexIndex = 0;vertexIndex < nVertices;vertexIndex++){
				if (!added[vertexIndex] &&shortestDistances[vertexIndex] <shortestDistance){
					nearestVertex = vertexIndex;
					shortestDistance = shortestDistances[vertexIndex];
				}
			}

            try{
                added[nearestVertex] = true;
            }
            catch(ArrayIndexOutOfBoundsException e){
                
            }

			for (int vertexIndex = 0;vertexIndex < nVertices;vertexIndex++){
				int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];
				
				if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])){
					parents[vertexIndex] = nearestVertex;
					shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
				}
			}
		}
        
		return data(startVertex, shortestDistances, parents);
	}

	private ArrayList<Integer> data(int startVertex, int[] distances, int[] parents){
		int nVertices = distances.length;
        ArrayList<Integer> dataDijkstra = new ArrayList<Integer>();
		for (int vertexIndex = 99;vertexIndex < nVertices;vertexIndex++){
			if (vertexIndex != startVertex){
				printPath(vertexIndex, parents,dataDijkstra);
			}
		}

        dataDijkstra.add(distances[distances.length-1]);
        return dataDijkstra;
	}

	private void printPath(int currentVertex, int[] parents, ArrayList<Integer> dataDijkstra){
		if (currentVertex == -1){
			return;
		}
		printPath(parents[currentVertex], parents,dataDijkstra);
		dataDijkstra.add(currentVertex);
	}

    //méthode qui traduit un numero de case (sommet) en position x,y sur la map
    public ArrayList<int[]> convertionNodeIntoCoordonates(int[][] matrice){
        ArrayList<Integer> data = dijkstra(matrice,0);
        ArrayList<int[]> path = new ArrayList<int[]>();
        
        int n = 10;
        int x = 0;
        int y = 0;

        int tour = 0;
        int k = 0;
        int i=0;

        while(i<data.size()-1){
                if(data.get(i) >= k && data.get(i) < k+n){
                    for(int j=0;j<n;j++){
                        x = tour;
                        if(data.get(i) % n-j == 0){
                            y = j;
                            path.add(new int[]{x,y});
                            i++;
                        }
                    }
                    k = k+n;
                    tour++;
                }
        }

        return path;
    }

    //méthode qui trouve les items (obstacle ou bonus) en x,y sur la map et qui traduit ça en numero de case (sommet)
    public ArrayList<Integer> whereAreItem(String item){

        ArrayList<int[]> e1;
        ArrayList<int[]> e2;
        ArrayList<int[]> fusion = new ArrayList<int[]>();
        
        if(item =="obstacles"){
            e1 = getAllRocks();
            e2 =  getAllTrees(); 
        }
        else{
            e1 = getAllFruits();
            e2 =  getAllMeats(); 
        }

        for (int[] e : e1) {
            fusion.add(e);
        }
        for (int[] e : e2) {
            fusion.add(e);
        }
        ArrayList<Integer> cases = new ArrayList<Integer>();

        for(int i=0; i<fusion.size(); i++){
            
            int x = fusion.get(i)[0];
            int y = fusion.get(i)[1];

            int c = 0 ;
            for(int a=0;a<10;a++){
                for(int b=0;b<10;b++){
                    if(x==a && y==b){

                        cases.add(c);      
                    }
                    c++;
                }
            }  
        }

        return cases;
    }
    private boolean isRealisable(){
        if(this.getShortestPathDistance().size() > 0){
            return true;
        }
        else return false;
    }


    public void Init()  throws IOException{
        setBoard();
        setRandomObstacle();
        setRandomBonus();
        getAllFruits();
        getAllMeats();
        getAllRocks();
        getAllTrees();
       
        int[][] matriceDistance = generateMatriceAdjacencePondere(10);
        
        
        try{
            this.shortestPathDistance = convertionNodeIntoCoordonates(matriceDistance);
        }
        catch(ArrayIndexOutOfBoundsException e){
            this.shortestPathDistance = new ArrayList<int[]>();
        }
        
    
        int[][] matriceEnergy = generateMatriceAdjacence(10);
        try{
            this.shortestPathEnergy = convertionNodeIntoCoordonates(matriceEnergy);
        }
        catch(ArrayIndexOutOfBoundsException e){
            this.shortestPathEnergy = new ArrayList<int[]>();
        }

        while(!this.isRealisable()){
            System.out.println("reset");;
            this.Init();
        }
    }
}
