import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.ActionEvent;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;


public class MyWindow extends JFrame {

    private static final long serialVersionUID = -4939544011287453046L;

    // private Icon caseIcon;
    private JLabel[][] labelCase;
    private JLabel energy, distance, energyWin, energyLose, rollback, timePlayed;
    private Player p;
    private ImageIcon iconUp, iconDown, iconLeft, iconRight, iconHomePlayer, iconJoud, iconRemy, iconCase, iconRock, iconTree, iconFruit, iconMeat, iconHome, iconPlayer, iconEnergy, iconDistance, iconEnergyLose, iconEnergyWin, iconRollback;

    private Timer timer, timerReplay;

    private int iTimer = 0;
    private int iTimerReplay = 0;

    private boolean boucleIsFinish = true;

    private JPanel contentPane;

    public MyWindow(Player p) {
        super( "GAME4J" );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setSize( 800, 800 );
        this.setLocationRelativeTo( null );
        this.setJMenuBar(this.menuBar());

        this.p = p;
        labelCase = new JLabel [Board.DIM_X][Board.DIM_Y];

        this.loadIcons();

        contentPane = (JPanel) this.getContentPane();
        
        contentPane.add(this.displayBoard());
        contentPane.add(this.buttonMove(), BorderLayout.SOUTH);
        contentPane.add(this.energyPanel(), BorderLayout.EAST);

    }	

    private void loadIcons(){
        // String urlCase = "C:\\Users\\joudy\\Documents\\EISTI\\S2\\Java\\Projet\\ING1-GAME4J\\src\\caseVert.png";
        // String urlCase = "src/caseVert.png";
        // ImageIcon iconCase = new ImageIcon(urlCase);
        // iconCase = new ImageIcon(new ImageIcon(urlCase).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));


        String urlUp = "src/top.png";
        // ImageIcon iconJoud = new ImageIcon(urlJoud);
        iconUp = new ImageIcon(new ImageIcon(urlUp).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));

        String urlDown = "src/down.png";
        // ImageIcon iconJoud = new ImageIcon(urlJoud);
        iconDown = new ImageIcon(new ImageIcon(urlDown).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        
        String urlLeft = "src/left.png";
        // ImageIcon iconJoud = new ImageIcon(urlJoud);
        iconLeft = new ImageIcon(new ImageIcon(urlLeft).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        
        String urlRight = "src/right.png";
        // ImageIcon iconJoud = new ImageIcon(urlJoud);
        iconRight = new ImageIcon(new ImageIcon(urlRight).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));

        String urlHomePlayer = "src/caseHomePlayer.png";
        // ImageIcon iconJoud = new ImageIcon(urlJoud);
        iconHomePlayer = new ImageIcon(new ImageIcon(urlHomePlayer).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));

        String urlJoud = "src/joud.png";
        // ImageIcon iconJoud = new ImageIcon(urlJoud);
        iconJoud = new ImageIcon(new ImageIcon(urlJoud).getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));

        String urlRemy = "src/remy.png";
        // ImageIcon iconRemy = new ImageIcon(urlRemy);
        iconRemy = new ImageIcon(new ImageIcon(urlRemy).getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT));

        String urlRock = "src/caseRock.png";
        // ImageIcon iconRock = new ImageIcon(urlRock);
        iconRock = new ImageIcon(new ImageIcon(urlRock).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));

        String urlTree = "src/caseTree.png";
        // ImageIcon iconTree = new ImageIcon(urlTree);
        iconTree = new ImageIcon(new ImageIcon(urlTree).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        
        String urlFruit = "src/caseFruit.png";
        iconFruit = new ImageIcon(new ImageIcon(urlFruit).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        
        String urlMeat = "src/caseMeat.png";
        iconMeat = new ImageIcon(new ImageIcon(urlMeat).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
    
        String urlHome = "src/caseHome.png";
        iconHome = new ImageIcon(new ImageIcon(urlHome).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        
        String urlPlayer = "src/casePlayer.png";
        iconPlayer = new ImageIcon(new ImageIcon(urlPlayer).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        
        String urlEnergy = "src/energy.png";
        iconEnergy = new ImageIcon(new ImageIcon(urlEnergy).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        
        String urlDistance = "src/distance.png";
        iconDistance = new ImageIcon(new ImageIcon(urlDistance).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));

        String urlEnergyLose = "src/energyLose.png";
        iconEnergyLose = new ImageIcon(new ImageIcon(urlEnergyLose).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));

        String urlEnergyWin = "src/energyWin.png";
        iconEnergyWin = new ImageIcon(new ImageIcon(urlEnergyWin).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        
        String urlRollback = "src/rollback.png";
        iconRollback = new ImageIcon(new ImageIcon(urlRollback).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        
    }

    private JPanel displayBoard(){

        //----------------GAME BOARD---------------------//
        // Build the "gameBoard".
        // JPanel gameBoard = (JPanel) this.getContentPane();

        JPanel gameBoard = new JPanel();
        gameBoard.setLayout(new GridLayout(Board.DIM_X,Board.DIM_Y));

        // int[] playerPosition = this.p.board.getPlayer();

        for(int i = 0; i<Board.DIM_X; i++){
            for(int j = 0; j<Board.DIM_Y; j++){
                if(i == Board.DIM_X-1 && j == Board.DIM_Y-1){
                    labelCase[i][j] = new JLabel(iconHome, JLabel.CENTER);
                }
                else if(p.getBoard().isPlayer(i, j)){
                    labelCase[i][j] = new JLabel(iconPlayer, JLabel.CENTER);
                }
                else if(p.getBoard().isRock(i, j)){
                    labelCase[i][j] = new JLabel(iconRock, JLabel.CENTER);
                }
                else if(p.getBoard().isTree(i, j)){
                    labelCase[i][j] = new JLabel(iconTree, JLabel.CENTER);
                }
                else if(p.getBoard().isMeat(i, j)){
                    labelCase[i][j] = new JLabel(iconMeat, JLabel.CENTER);
                }
                else if(p.getBoard().isFruit(i, j)){
                    labelCase[i][j] = new JLabel(iconFruit, JLabel.CENTER);
                }
                else{
                    labelCase[i][j] = new JLabel("", JLabel.CENTER);
                }
                labelCase[i][j].setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
                gameBoard.setBackground(new Color(45,137,33));
                gameBoard.add(labelCase[i][j]);
                  
            }
        } 
        return gameBoard;
    }

    private JMenuBar menuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu mnuGame = new JMenu( "Game" );

        mnuGame.add(mnuNewGame);
        mnuGame.add(mnuSaveGame);
        mnuGame.add(mnuLoadGame);
        mnuGame.add(mnuHistoryGame);
        mnuGame.add(mnuLeaveGame);
        mnuGame.addSeparator();
        mnuGame.add(mnuAboutUs);

        menuBar.add(mnuGame);
        
        return menuBar;
    }

    private AbstractAction mnuNewGame = new AbstractAction() {  
        {
            putValue( Action.NAME, "New Game..." );
            putValue( Action.SMALL_ICON, new ImageIcon( "src/new.png" ) );
            putValue( Action.SHORT_DESCRIPTION, "Start a new game" );
            putValue( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK ) );

        }
        
        @Override public void actionPerformed( ActionEvent e ) {
            restartGame();
        }
    };

    private AbstractAction mnuSaveGame = new AbstractAction() {  
        {
            putValue( Action.NAME, "Save the game" );
            putValue( Action.SMALL_ICON, new ImageIcon( "src/save.png" ) );
            putValue( Action.SHORT_DESCRIPTION, "Save your game to continue later" );
            putValue( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK ) );

        }
        
        @Override public void actionPerformed( ActionEvent e ) {
            int work = p.saveGame();
            if(work == 1){
                saveDialog("Game Saved Successfully !");
            }
            else{
                displayMessageDialog("The game can not be saved");
            }
        }
    };
    private AbstractAction mnuLoadGame = new AbstractAction() {  
        {
            putValue( Action.NAME, "Open your saved game..." );
            putValue( Action.SMALL_ICON, new ImageIcon( "src/open.png" ) );
            putValue( Action.SHORT_DESCRIPTION, "Open your previous game" );
            putValue( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK ) );
        }
        
        @Override public void actionPerformed( ActionEvent e ) {
            try {
                recoverGame(new Player(new Board()), 0);
            } catch (Exception e1) {
                displayMessageDialog("The game can not be open");
            }
        }
    };
    private AbstractAction mnuHistoryGame = new AbstractAction() {  
        {
            putValue( Action.NAME, "See games history" );
            putValue( Action.SMALL_ICON, new ImageIcon( "src/history.png" ) );
            putValue( Action.SHORT_DESCRIPTION, "Load a previous game and see the replay" );
            putValue( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK ) );

        }
        
        @Override public void actionPerformed( ActionEvent e ) {
            showGameHistory();
        }
    };
    private AbstractAction mnuAboutUs = new AbstractAction() {  
        {
            putValue( Action.NAME, "About us" );
            putValue( Action.SMALL_ICON, new ImageIcon( "src/about.png" ) );
            putValue( Action.SHORT_DESCRIPTION, "About the two guys (INFORMATICIEN)");
        }
        
        @Override public void actionPerformed( ActionEvent e ) {
            displayInformaticien();
        }
    };
    private AbstractAction mnuLeaveGame = new AbstractAction() {  
        {
            putValue( Action.NAME, "Leave" );
            putValue( Action.SMALL_ICON, new ImageIcon( "src/exit.png" ) );
            putValue( Action.SHORT_DESCRIPTION, "Leave the game");
            putValue( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK ) );

        }
        
        @Override public void actionPerformed( ActionEvent e ) {
            leaveGame();
        }
    };
    private void leaveGame(){
        this.dispose();
    }


    private JPanel buttonMove(){

        JPanel buttonMove = new JPanel(new FlowLayout());
        JPanel buttonPosition = new JPanel(new BorderLayout());
        JPanel buttonTopPosition = new JPanel(new BorderLayout());

        buttonMove.add(buttonPosition, BorderLayout.CENTER);
        buttonPosition.add(buttonTopPosition, BorderLayout.NORTH);

        JButton undo = new JButton("UNDO");
        undo.setPreferredSize(new Dimension(70,40));
        undo.setFont(new Font("Calibri", Font.BOLD, 15));
        buttonTopPosition.add(undo, BorderLayout.EAST);
     
        undo.addActionListener(e ->
        {
            undoListener();
        });

        JButton save = new JButton("SAVE");
        save.setPreferredSize(new Dimension(70,40));
        save.setFont(new Font("Calibri", Font.BOLD, 15));
        buttonTopPosition.add(save, BorderLayout.WEST);
     
        save.addActionListener(e ->
        {
            saveListener();
        });

        JButton up = new JButton(iconUp);
        up.setPreferredSize(new Dimension(70,40));
        buttonTopPosition.add(up, BorderLayout.CENTER);

        up.addActionListener(e ->
        {
           upListener();
        });

        JButton down = new JButton(iconDown);
        down.setPreferredSize(new Dimension(70,40));
        buttonPosition.add(down, BorderLayout.CENTER);

        down.addActionListener(e ->
        {
            downListener();
        });

        JButton left = new JButton(iconLeft);
        left.setPreferredSize(new Dimension(70,40));
        buttonPosition.add(left, BorderLayout.WEST);

        left.addActionListener(e ->
        {
            leftListener();
        });

        JButton right = new JButton(iconRight);
        right.setPreferredSize(new Dimension(70,40));
        buttonPosition.add(right, BorderLayout.EAST);
        
        right.addActionListener(e ->
        {
            rightListener();
        });
       
        return buttonMove;
    }

    private void saveListener(){
        int work = p.saveGame();
            if(work == 1){
                saveDialog("Game Saved Successfully !");
            }
            else{
                displayMessageDialog("The game can not be saved");
            }
    }

    private void undoListener(){
        int intUndo;
            int x = p.getBoard().getPlayer()[0];
            int y = p.getBoard().getPlayer()[1];
            intUndo = p.undoMovement();
            if(intUndo == 1){
                displayMessageDialog("You can't undo your deplacement anymore ");
            }
            else if(intUndo == 2){
                displayMessageDialog("This is the initial position, you can't undo your deplacement ");
            }
            refreshBoard(x, y);
    }

    private void upListener(){
        if(boucleIsFinish == true){
            int[] currentBox ;
            int x = p.getBoard().getPlayer()[0];
            int y = p.getBoard().getPlayer()[1];
            currentBox = p.moveUp(x, y);
            caseBoucle(p.boucle(currentBox));
            refreshBoard(x, y);
        }
    }

    private void downListener(){
        if(boucleIsFinish == true){
            int[] currentBox ;
            int x = p.getBoard().getPlayer()[0];
            int y = p.getBoard().getPlayer()[1];
            currentBox = p.moveDown(x, y);
            caseBoucle(p.boucle(currentBox));
            refreshBoard(x, y);
        }
    }

    private void rightListener(){
        if(boucleIsFinish == true){
            int[] currentBox ;
            int x = p.getBoard().getPlayer()[0];
            int y = p.getBoard().getPlayer()[1];
            currentBox = p.moveRight(x, y);
            caseBoucle(p.boucle(currentBox));
            refreshBoard(x, y);
        }
    }

    private void leftListener(){
        if(boucleIsFinish == true){
            int[] currentBox ;
            int x = p.getBoard().getPlayer()[0];
            int y = p.getBoard().getPlayer()[1];
            currentBox = p.moveLeft(x, y);
            caseBoucle(p.boucle(currentBox));
            refreshBoard(x, y);
        }
    }
    private JPanel energyPanel(){
        JPanel energyPanel = new JPanel(new GridLayout(5,2));
        energyPanel.setPreferredSize(new Dimension(100,100));

        JLabel energyIcon = new JLabel(iconEnergy, JLabel.CENTER);
        energy = new JLabel("", JLabel.CENTER);
        energy.setFont(new Font("Calibri", Font.BOLD, 32));
        energy.setText(String.valueOf(p.getEnergy()));
        energyPanel.add(energyIcon);
        energyPanel.add(energy);

        JLabel energyWinIcon = new JLabel(iconEnergyWin, JLabel.CENTER);
        energyWin = new JLabel("", JLabel.CENTER);
        energyWin.setFont(new Font("Calibri", Font.BOLD, 32));
        energyWin.setText(String.valueOf(p.getEnergyWin()));
        energyPanel.add(energyWinIcon);
        energyPanel.add(energyWin);

        JLabel energyLoseIcon = new JLabel(iconEnergyLose, JLabel.CENTER);
        energyLose = new JLabel("", JLabel.CENTER);
        energyLose.setFont(new Font("Calibri", Font.BOLD, 32));
        energyLose.setText(String.valueOf(p.getEnergyLose()));
        energyPanel.add(energyLoseIcon);
        energyPanel.add(energyLose);
        
        JLabel distanceIcon = new JLabel(iconDistance, JLabel.CENTER);
        distance = new JLabel("", JLabel.CENTER);
        distance.setFont(new Font("Calibri", Font.BOLD, 32));
        distance.setText(String.valueOf(p.getDistanceParcourure()));
        energyPanel.add(distanceIcon);
        energyPanel.add(distance);

        JLabel rollbackIcon = new JLabel(iconRollback, JLabel.CENTER);
        rollback = new JLabel("", JLabel.CENTER);
        rollback.setFont(new Font("Calibri", Font.BOLD, 32));
        rollback.setText(String.valueOf(p.getUndo()));
        energyPanel.add(rollbackIcon);
        energyPanel.add(rollback);

        return energyPanel;
    }

    
    private void refreshBoard(int x, int y){
        int[] playerPosition = this.p.getBoard().getPlayer();
        if(p.getEnergy() <= 0){
            this.gameOver("You lose !");
        }
        else if(this.p.getBoard().gameOver()){
            // labelCase[p.board.DIM_X-2][p.board.DIM_Y-1].setIcon(null);
            // labelCase[p.board.DIM_X-1][p.board.DIM_Y-2].setIcon(null);
            
            // labelCase[playerPosition[0]][playerPosition[1]].setIcon(null);
            // labelCase[p.board.DIM_X-1][p.board.DIM_Y-1].setIcon(iconHomePlayer);
            p.addToHistory();
            this.gameOver("You win !");
            
        }
        else{
            if(!this.p.getBoard().isRock(x, y) || !this.p.getBoard().isTree(x, y)){
                labelCase[x][y].setIcon(null);
            }
            labelCase[playerPosition[0]][playerPosition[1]].setIcon(iconPlayer);
            
            
            energy.setText(String.valueOf(p.getEnergy()));
            distance.setText(String.valueOf(p.getDistanceParcourure()));
            energyLose.setText(String.valueOf(p.getEnergyLose()));
            energyWin.setText(String.valueOf(p.getEnergyWin()));
            rollback.setText(String.valueOf(p.getUndo()));
            
        }
        
    }

    private void caseBoucle(ArrayList<int[]> boucle){

        timer = new Timer(150, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    try {
                        // if(iTimer>1){
                        //     labelCase[boucle.get(iTimer-2)[0]][boucle.get(iTimer-2)[1]].setOpaque(true);
                        //     labelCase[boucle.get(iTimer-2)[0]][boucle.get(iTimer-2)[1]].setBackground(new Color(45 ,137,33));
                        // }
                        boucleIsFinish = false;
                        labelCase[boucle.get(iTimer)[0]][boucle.get(iTimer)[1]].setOpaque(true);
                        labelCase[boucle.get(iTimer)[0]][boucle.get(iTimer)[1]].setBackground(new Color(41 ,242,15));
                        iTimer++;
                    } 
                    catch (Exception m) {
                        boucleIsFinish = true;
                        iTimer = 0;
                        timer.stop();
                        for(int i=0; i< boucle.size(); i++){
                            labelCase[boucle.get(i)[0]][boucle.get(i)[1]].setBackground(new Color(45 ,137,33));
                        }  
                    }
                    
                }
            });

            if(boucle.size()>1){
                
                timer.start();

            }
     
    }

    private void saveDialog(String title){
        Object[] options = {"Continue", "Restart" , "See games history", "Leave"};
        int n = JOptionPane.showOptionDialog(this, showResultDialog(), title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
        if(n == 0){
            try {
                recoverGame(new Player(new Board()), 1);
            } catch (Exception e) {
                displayMessageDialog("The game can not be open");
            }
        }
        else if(n == 1){
            restartGame();
        }
        else if(n == 2){
            showGameHistory();
        }
        else{
            this.dispose();
        }
    }

    private void displayMessageDialog(String content){
        JOptionPane.showMessageDialog(this, content);
    }

    private void gameOver(String title){

        Object[] options = {"Restart", "Show the best way" , "See games history", "Leave"};
        int n = JOptionPane.showOptionDialog(this, showResultDialog(), title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
        if(n == 0){
            restartGame();
        }
        else if(n == 1){
              
        }
        else if(n == 2){
            showGameHistory();
        }
        else{
            this.dispose();
        }

    }

    private JPanel showResultDialog(){
        JPanel resultPanel = new JPanel(new GridLayout(5,2));

        JLabel energyIcon = new JLabel("Energy left : ", JLabel.CENTER);
        energy = new JLabel("", JLabel.CENTER);
        energy.setText(String.valueOf(p.getEnergy()));
        energy.setFont(new Font("Calibri", Font.BOLD, 20));
        resultPanel.add(energyIcon);
        resultPanel.add(energy);

        JLabel energyWinIcon = new JLabel("\n\nEnergy win : ", JLabel.CENTER);
        energyWin = new JLabel("", JLabel.CENTER);
        energyWin.setText(String.valueOf(p.getEnergyWin()));
        energyWin.setFont(new Font("Calibri", Font.BOLD, 20));
        resultPanel.add(energyWinIcon);
        resultPanel.add(energyWin);

        JLabel energyLoseIcon = new JLabel("\n\nEnergy lose : ", JLabel.CENTER);
        energyLose = new JLabel("", JLabel.CENTER);
        energyLose.setText(String.valueOf(p.getEnergyLose()));
        energyLose.setFont(new Font("Calibri", Font.BOLD, 20));
        resultPanel.add(energyLoseIcon);
        resultPanel.add(energyLose);

        JLabel distanceIcon = new JLabel("\n\nBoxes traveled : ", JLabel.CENTER);
        distance = new JLabel("", JLabel.CENTER);
        distance.setText(String.valueOf(p.getDistanceParcourure()));
        distance.setFont(new Font("Calibri", Font.BOLD, 20));
        resultPanel.add(distanceIcon);
        resultPanel.add(distance);

        JLabel timePlayedLabel = new JLabel("Time played : " , JLabel.CENTER);
        timePlayed = new JLabel(p.showResult(), JLabel.CENTER);
        timePlayed.setFont(new Font("Calibri", Font.BOLD, 20));
        resultPanel.add(timePlayedLabel);
        resultPanel.add(timePlayed);

        return resultPanel;
    }

    public void recoverGame(Player player, int i) throws Exception{
        this.p = player;
        Data data = (Data) Ressources.Load("data.save");
        if(i == 0){
            displayMessageDialog("\n Game from the : " + data.date );
        }

        player.setEnergy(data.energy);
        player.setEnergyLose(data.energyLose);
        player.setEnergyWin(data.energyWin);
        player.setDistanceParcourure(data.distance);
        player.setUndo(data.undo);
        player.getBoard().setPlayer(data.posPlayerX, data.posPlayerY);
        for (int[] obstacle : data.trees) {
            player.getBoard().setTree(obstacle);
        }
        for (int[] obstacle : data.rocks) {
            player.getBoard().setRock(obstacle);
        }
        for (int[] obstacle : data.fruits) {
            player.getBoard().setFruit(obstacle);
        }
        for (int[] obstacle : data.meats) {
            player.getBoard().setMeat(obstacle);
        }

        player.getBoard().getAllFruits();
        player.getBoard().getAllMeats();
        player.getBoard().getAllTrees();
        player.getBoard().getAllRocks();

        contentPane.add(this.displayBoard());
        contentPane.add(this.buttonMove(), BorderLayout.SOUTH);
        contentPane.add(this.energyPanel(), BorderLayout.EAST);

        refreshBoard(data.posPlayerX, data.posPlayerY);
    }

    public static boolean hasGames(){
        File file = new File("./games/");
        switch(file.list().length){
            case 0:
                return false;
        }
        return true;
    }

    private void showGameHistory(){
        
        if(hasGames()){
            File file = new File("./games/");

            JPanel allGameData = new JPanel(new GridLayout( file.list().length+ 2, 1 ));
            JLabel whichGame = new JLabel("Choose which game : ");
            whichGame.setFont(new Font("Calibri", Font.BOLD, 20));
            allGameData.add(whichGame);

            for(int i=0; i<file.list().length; i++){
                try {
                    Data data = (Data) Ressources.Load("./games/game"+i+".save");
                    JLabel gameData = new JLabel(i + " - Game from " + data.date , JLabel.CENTER);
                    allGameData.add(gameData);
                    
                } catch (Exception e) {
                    displayMessageDialog("Couldn't load game");
                }
            }

            String gameNb = displayHistoryDialog(allGameData);
            int speedNb = displaySpeed();
            
            startReplay(new Player(new Board()), gameNb, speedNb);
            
        }
        else{
            displayMessageDialog("There is no game to watch yet !");
        }
    }

    
    public void startReplay(Player player, String gameID, int speed){
        try {
            this.p = player;
            
            Data data = (Data) Ressources.Load("./games/game"+gameID+".save");
            // p.board.resetAll();
            
            player.getBoard().setPlayer(data.posPlayerX, data.posPlayerY);
            for (int[] obstacle : data.trees) {
                player.getBoard().setTree(obstacle);
            }
            for (int[] obstacle : data.rocks) {
                player.getBoard().setRock(obstacle);
            }
            for (int[] obstacle : data.fruits) {
                player.getBoard().setFruit(obstacle);
            }
            for (int[] obstacle : data.meats) {
                player.getBoard().setMeat(obstacle);
            }
            System.out.println("size data deplacement " + data.playerDeplacements.size());

            contentPane.add(this.displayBoard());
            contentPane.add(this.buttonMove(), BorderLayout.SOUTH);
            contentPane.add(this.energyPanel(), BorderLayout.EAST);
            // refreshBoard(data.playerDeplacements.get(0)[0], data.playerDeplacements.get(0)[1]);
            refreshBoard(data.posPlayerX, data.posPlayerY);

            timerReplay = new Timer(speed, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    try {
                        boucleIsFinish = false;
                        if(!player.getBoard().isTree(data.playerDeplacements.get(iTimerReplay)[0], data.playerDeplacements.get(iTimerReplay)[1])
                         && !player.getBoard().isRock(data.playerDeplacements.get(iTimerReplay)[0], data.playerDeplacements.get(iTimerReplay)[1])
                         && !player.getBoard().isHouse(data.playerDeplacements.get(iTimerReplay)[0], data.playerDeplacements.get(iTimerReplay)[1])){
                            labelCase[data.playerDeplacements.get(iTimerReplay)[0]][data.playerDeplacements.get(iTimerReplay)[1]].setIcon(iconPlayer);
                            labelCase[data.playerDeplacements.get(iTimerReplay)[0]][data.playerDeplacements.get(iTimerReplay)[1]].setOpaque(true);
                            labelCase[data.playerDeplacements.get(iTimerReplay)[0]][data.playerDeplacements.get(iTimerReplay)[1]].setBackground(new Color(202,202,202));
                         }
                         else{  
                            labelCase[data.playerDeplacements.get(iTimerReplay)[0]][data.playerDeplacements.get(iTimerReplay)[1]].setOpaque(true);
                            labelCase[data.playerDeplacements.get(iTimerReplay)[0]][data.playerDeplacements.get(iTimerReplay)[1]].setBackground(new Color(36,36,36));
                         }
                        iTimerReplay++;
                    } 
                    catch (Exception m) {

                        boucleIsFinish = true;
                        iTimerReplay = 0;
                        timerReplay.stop();
                        endReplay();
                        
                    }
                    
                }
            });
            if(data.playerDeplacements.size()>1){
                timerReplay.start();
            }     
            
        } catch (Exception e) {
            displayMessageDialog("Can't load the replay");
        }
    }

    private void endReplay(){
        Object[] options = {"Start a new game" , "See games history", "Leave"};
        JLabel endOfReplay = new JLabel("What do you want to do now ?", JLabel.CENTER);
        endOfReplay.setFont(new Font("Calibri", Font.BOLD, 20));
        int n = JOptionPane.showOptionDialog(this, endOfReplay , "", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
        if(n == 0){
            restartGame();
        }
        else if(n == 1){
            showGameHistory();
        }
        else{
            this.dispose();
        }
    }

    private int displaySpeed(){
        int nbSpeed;
        JPanel buttonSpeed = new JPanel();
        JLabel speed = new JLabel("Choose the speed replay : ", JLabel.CENTER);
        speed.setFont(new Font("Calibri", Font.BOLD, 20));
        buttonSpeed.add(speed);

        Object[] options = {"Slow", "Normal" , "Fast"};
        int n = JOptionPane.showOptionDialog(this, buttonSpeed, "Speed replay", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
        if(n == 0){
            nbSpeed = 1000;
        }
        else if(n == 1){
              nbSpeed = 700;
        }
        else{
            nbSpeed = 400;
        }
        return nbSpeed;
    }

    private String displayHistoryDialog(JPanel allGameData){
        String n = (String) JOptionPane.showInputDialog(this, allGameData, "Replay a game", JOptionPane.QUESTION_MESSAGE, null, null, null);
        return n;
    }

    private void displayInformaticien(){
        JPanel panInf = new JPanel(new FlowLayout());
        JPanel panJoud = new JPanel(new BorderLayout());
        JPanel panRemy = new JPanel(new BorderLayout());

        panInf.add(panJoud, BorderLayout.WEST);
        panInf.add(panRemy, BorderLayout.EAST);

        JLabel photoJoud = new JLabel(iconJoud, JLabel.CENTER);
        JLabel nomJoud = new JLabel("Joud Cazeaux", JLabel.CENTER);
        nomJoud.setFont(new Font("Calibri", Font.BOLD, 32));
        JLabel siteJoud = new JLabel("https://joudcazeaux.fr", JLabel.CENTER);
        siteJoud.setFont(new Font("Calibri", Font.ITALIC, 25));
        panJoud.add(photoJoud, BorderLayout.NORTH);
        panJoud.add(nomJoud, BorderLayout.CENTER);
        panJoud.add(siteJoud, BorderLayout.SOUTH);

        JLabel photoRemy = new JLabel(iconRemy, JLabel.CENTER);
        JLabel nomRemy = new JLabel("Rémy Dionisio", JLabel.CENTER);
        nomRemy.setFont(new Font("Calibri", Font.BOLD, 32));
        JLabel siteRemy = new JLabel("https://remydionisio.fr", JLabel.CENTER);
        siteRemy.setFont(new Font("Calibri", Font.ITALIC, 25));
        panRemy.add(photoRemy, BorderLayout.NORTH);
        panRemy.add(nomRemy, BorderLayout.CENTER);
        panRemy.add(siteRemy, BorderLayout.SOUTH);

        // JOptionPane.showMessageDialog(this, paninf, "Les informaticiens", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showOptionDialog(this, panInf, "Les informaticiens", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

    }

    private void restartGame(){

        this.p = new Player(new Board());
        
        // labelCase = new JLabel [Board.DIM_X][Board.DIM_Y];
        // player.board.resetAll();
        this.p.getBoard().Init();
        
        contentPane.add(this.displayBoard());
        contentPane.add(this.buttonMove(), BorderLayout.SOUTH);
        contentPane.add(this.energyPanel(), BorderLayout.EAST);

        refreshBoard(0, 0);

    }
    public static void main(String[] args) throws Exception {
        // Apply a look'n feel
        UIManager.setLookAndFeel( new NimbusLookAndFeel() );

        Board board = new Board();
        board.Init();
        Player player = new Player(board);

        // Start my window
        MyWindow myWindow = new MyWindow(player);
        // myWindow.displayBoard();
        myWindow.setVisible( true );

        myWindow.contentPane.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}
    
            @Override
            public void keyReleased(KeyEvent e) {}
    
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP ||e.getKeyCode() == KeyEvent.VK_Z){
			        myWindow.upListener();
                }
		        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
			        myWindow.downListener();
		        }
		        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
                    myWindow.rightListener();
		        }
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_Q){
                    myWindow.leftListener();
		        }
                if (e.getKeyCode() == KeyEvent.VK_B){
                    myWindow.undoListener();
		        }
                if (e.getKeyCode() == KeyEvent.VK_L){
                    myWindow.saveListener();
		        }       
            }
        });
    
        myWindow.contentPane.setFocusable(true);
        myWindow.contentPane.requestFocusInWindow();
        
    }

}