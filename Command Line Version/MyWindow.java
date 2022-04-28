import java.nio.file.*;
import java.io.*;
import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import javafx.scene.image.Image.*;
import javafx.scene.input.KeyEvent.*;
import javafx.scene.paint.Color.*;
import javafx.stage.WindowEvent;

public class MyWindow extends JFrame {

    private static final long serialVersionUID = -4939544011287453046L;

    // private Icon caseIcon;
    private JLabel[][] labelCase;
    private JLabel energy, distance, energyWin, energyLose, rollback;
    private Player p;
    private ImageIcon iconCase, iconRock, iconTree, iconFruit, iconMeat, iconHome, iconPlayer, iconEnergy, iconDistance, iconEnergyLose, iconEnergyWin, iconRollback;

    public MyWindow(Player p) {
        super( "GAME4J" );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setSize( 800, 800 );
        this.setLocationRelativeTo( null );
        this.setJMenuBar(this.menuBar());

        this.p = p;
        labelCase = new JLabel [p.board.DIM_X][p.board.DIM_Y];

        this.loadIcons();

        JPanel contentPane = (JPanel) this.getContentPane();
        
        contentPane.add(this.displayBoard());
        contentPane.add(this.buttonMove(), BorderLayout.SOUTH);
        contentPane.add(this.energyPanel(), BorderLayout.EAST);
        
    }

    private void loadIcons(){
        // String urlCase = "C:\\Users\\joudy\\Documents\\EISTI\\S2\\Java\\Projet\\ING1-GAME4J\\src\\caseVert.png";
        // String urlCase = "src/caseVert.png";
        // ImageIcon iconCase = new ImageIcon(urlCase);
        // iconCase = new ImageIcon(new ImageIcon(urlCase).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));

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
        gameBoard.setLayout(new GridLayout(p.board.DIM_X,p.board.DIM_Y));



        int[] playerPosition = this.p.board.getPlayer();

        for(int i = 0; i<p.board.DIM_X; i++){
            for(int j = 0; j<p.board.DIM_Y; j++){
                if(p.board.isRock(i, j)){
                    labelCase[i][j] = new JLabel(iconRock, JLabel.CENTER);
                }
                else if(p.board.isTree(i, j)){
                    labelCase[i][j] = new JLabel(iconTree, JLabel.CENTER);
                }
                else if(p.board.isMeat(i, j)){
                    labelCase[i][j] = new JLabel(iconMeat, JLabel.CENTER);
                }
                else if(p.board.isFruit(i, j)){
                    labelCase[i][j] = new JLabel(iconFruit, JLabel.CENTER);
                }
                else if(i == p.board.DIM_X-1 && j == p.board.DIM_Y-1){
                    this.p.board.setHouse(i, j);
                    labelCase[i][j] = new JLabel(iconHome, JLabel.CENTER);
                }
                // else if(playerPosition[0] == i && playerPosition[1] == j){
                //     labelCase[i][j] = new JLabel(iconPlayer, JLabel.CENTER);
                // }
                else if(p.board.isPlayer(i, j)){
                    labelCase[i][j] = new JLabel(iconPlayer, JLabel.CENTER);
                }
                else{
                    labelCase[i][j] = new JLabel("", JLabel.CENTER);
                }
                labelCase[i][j].setBorder(BorderFactory.createLineBorder(Color.black, 1, true));

                gameBoard.setBackground(new Color(45,137,33));
                gameBoard.add(labelCase[i][j]);
                  
            }
        }
        
        if(p.board.shortestPathDistance.size() > 0){
            for (int[] node : p.board.shortestPathDistance) {
                int x = node[0];
                int y = node[1];
                labelCase[x][y].setOpaque(true);
                labelCase[x][y].setBackground(new Color(120 ,0,0));
            }
           
            for (int[] node : p.board.shortestPathEnergy) {
                int x = node[0];
                int y = node[1];
                labelCase[x][y].setOpaque(true);
                labelCase[x][y].setBackground(new Color(0 ,0,255));
            }
        }
        else{
            for(int i=0;i<p.board.DIM_X;i++){
                for(int j=0;j<p.board.DIM_Y;j++){
                    labelCase[i][j].setOpaque(true);
                    labelCase[i][j].setBackground(new Color(255 ,0,0));
                }
            }
        }
        
        return gameBoard;
    }

    private JMenuBar menuBar(){
        JMenuBar menuBar = new JMenuBar();

        // Définition du menu déroulant "File" et de son contenu
        JMenu mnuGame = new JMenu( "Game" );
        // mnuGame.setMnemonic( 'F' );

        JMenuItem mnuNewGame = new JMenuItem( "New Game" );
        // mnuNewGame.setIcon( new ImageIcon( "icons/new.png" ) );
        mnuGame.add(mnuNewGame);

        JMenuItem mnuSavedGame = new JMenuItem( "Load a game" );
        // mnuNewGame.setIcon( new ImageIcon( "icons/new.png" ) );
        mnuGame.add(mnuSavedGame);

        mnuGame.addSeparator();

        menuBar.add(mnuGame);
        
        return menuBar;
    }

    private JPanel buttonMove(){

        JPanel buttonMove = new JPanel(new FlowLayout());
        JPanel buttonPosition = new JPanel(new BorderLayout());

        buttonMove.add(buttonPosition, BorderLayout.CENTER);

        JButton up = new JButton("UP");
        up.setPreferredSize(new Dimension(40,40));
        buttonPosition.add(up, BorderLayout.NORTH);

        up.addActionListener(e ->
        {
            int x = p.board.getPlayer()[0];
            int y = p.board.getPlayer()[1];
            p.moveUp(x, y);
            refreshBoard(x, y);
        });

        JButton down = new JButton("DOWN");
        down.setPreferredSize(new Dimension(70,40));
        buttonPosition.add(down, BorderLayout.CENTER);

        down.addActionListener(e ->
        {
            int x = p.board.getPlayer()[0];
            int y = p.board.getPlayer()[1];
            p.moveDown(x, y);
            refreshBoard(x, y);
        });

        JButton left = new JButton("LEFT");
        left.setPreferredSize(new Dimension(70,40));
        buttonPosition.add(left, BorderLayout.WEST);

        left.addActionListener(e ->
        {
            int x = p.board.getPlayer()[0];
            int y = p.board.getPlayer()[1];
            p.moveLeft(x, y);
            refreshBoard(x, y);
        });

        JButton right = new JButton("RIGHT");
        right.setPreferredSize(new Dimension(70,40));
        buttonPosition.add(right, BorderLayout.EAST);

        right.addActionListener(e ->
        {
            int x = p.board.getPlayer()[0];
            int y = p.board.getPlayer()[1];
            p.moveRight(x, y);
            refreshBoard(x, y);
        });

        return buttonMove;
    }

    private JPanel energyPanel(){
        JPanel energyPanel = new JPanel(new GridLayout(5,2));
        energyPanel.setPreferredSize(new Dimension(100,100));

        JLabel energyIcon = new JLabel(iconEnergy, JLabel.CENTER);
        energy = new JLabel("", JLabel.CENTER);
        energy.setText(String.valueOf(p.energy));
        energyPanel.add(energyIcon);
        energyPanel.add(energy);

        JLabel distanceIcon = new JLabel(iconDistance, JLabel.CENTER);
        distance = new JLabel("", JLabel.CENTER);
        distance.setText(String.valueOf(p.distanceParcourure));
        energyPanel.add(distanceIcon);
        energyPanel.add(distance);

        JLabel energyWinIcon = new JLabel(iconEnergyWin, JLabel.CENTER);
        energyWin = new JLabel("", JLabel.CENTER);
        energyWin.setText(String.valueOf(p.energyWin));
        energyPanel.add(energyWinIcon);
        energyPanel.add(energyWin);

        JLabel energyLoseIcon = new JLabel(iconEnergyLose, JLabel.CENTER);
        energyLose = new JLabel("", JLabel.CENTER);
        energyLose.setText(String.valueOf(p.energyLose));
        energyPanel.add(energyLoseIcon);
        energyPanel.add(energyLose);

        JLabel rollbackIcon = new JLabel(iconRollback, JLabel.CENTER);
        rollback = new JLabel("", JLabel.CENTER);
        rollback.setText(String.valueOf(p.undo));
        energyPanel.add(rollbackIcon);
        energyPanel.add(rollback);

        return energyPanel;
    }


    
    private void refreshBoard(int x, int y) {
        int[] playerPosition = this.p.board.getPlayer();
        if(p.energy <= 0 || this.p.board.gameOver()){
            nullEnergy();
        }
        else{
            if(!this.p.board.isRock(x, y) || !this.p.board.isTree(x, y)){
                labelCase[x][y].setIcon(null);
            }
            labelCase[playerPosition[0]][playerPosition[1]].setIcon(iconPlayer);

            
            energy.setText(String.valueOf(p.energy));
            distance.setText(String.valueOf(p.distanceParcourure));
            energyLose.setText(String.valueOf(p.energyLose));
            energyWin.setText(String.valueOf(p.energyWin));
            rollback.setText(String.valueOf(p.undo));
            
        }
        
    }

    private void nullEnergy(){
        JPanel resultPanel = new JPanel(new GridLayout(5,2));

        JLabel energyIcon = new JLabel("Energy left : ", JLabel.CENTER);
        energy = new JLabel("", JLabel.CENTER);
        energy.setText(String.valueOf(p.energy));
        resultPanel.add(energyIcon);
        resultPanel.add(energy);

        JLabel energyWinIcon = new JLabel("\n\nEnergy win : ", JLabel.CENTER);
        energyWin = new JLabel("", JLabel.CENTER);
        energyWin.setText(String.valueOf(p.energyWin));
        resultPanel.add(energyWinIcon);
        resultPanel.add(energyWin);

        JLabel energyLoseIcon = new JLabel("\n\nEnergy lose : ", JLabel.CENTER);
        energyLose = new JLabel("", JLabel.CENTER);
        energyLose.setText(String.valueOf(p.energyLose));
        resultPanel.add(energyLoseIcon);
        resultPanel.add(energyLose);

        JLabel distanceIcon = new JLabel("\n\nBoxes traveled : ", JLabel.CENTER);
        distance = new JLabel("", JLabel.CENTER);
        distance.setText(String.valueOf(p.distanceParcourure));
        resultPanel.add(distanceIcon);
        resultPanel.add(distance);

        
        Object[] options = {"Restart a new game", "Leave the game"};
        int n = JOptionPane.showOptionDialog(this, resultPanel, "You lose !", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[1]);
        // JOptionPane.showMessageDialog(this, resultPanel, "You lose !!", JOptionPane.CLOSED_OPTION, null);
        if(n == JOptionPane.YES_OPTION){
            Board board = new Board();
            try{
                board.Init();
            }
            catch(IOException e){

            }
            Player player = new Player(board);
        }
        else{
            // this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

        }
      

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
        // int x = board.getPlayer()[0];
        // int y = board.getPlayer()[1];
        // Thread.sleep(3000);
        // player.moveDown(x, y);
        // myWindow.refreshBoard(x, y);
    }

}