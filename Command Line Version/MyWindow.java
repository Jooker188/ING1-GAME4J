

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Console.*;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

// import javafx.event.ActionEvent;
import javafx.scene.image.Image.*;
import javafx.scene.input.KeyEvent.*;
import javafx.scene.paint.Color.*;
import javafx.scene.text.Font.*;
import javafx.scene.paint.Color.*;
import javafx.stage.WindowEvent.*;

public class MyWindow extends JFrame {

    private static final long serialVersionUID = -4939544011287453046L;

    // private Icon caseIcon;
    private JLabel[][] labelCase;
    private JLabel energy, distance, energyWin, energyLose, rollback;
    private Player p;
    private ImageIcon iconCase, iconRock, iconTree, iconFruit, iconMeat, iconHome, iconPlayer, iconEnergy, iconDistance, iconEnergyLose, iconEnergyWin, iconRollback;

    private Timer timer;

    private int iTimer = 0;

    private boolean boucleIsFinish = true;

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
        JPanel buttonTopPosition = new JPanel(new BorderLayout());


        buttonMove.add(buttonPosition, BorderLayout.CENTER);
        buttonPosition.add(buttonTopPosition, BorderLayout.NORTH);

        JButton undo = new JButton("UNDO");
        // undo.setPreferredSize(new Dimension(40,0));
        buttonTopPosition.add(undo, BorderLayout.EAST);
     
        undo.addActionListener(e ->
        {
            int x = p.board.getPlayer()[0];
            int y = p.board.getPlayer()[1];
            p.undoMovement();
            refreshBoard(x, y);
        });

        JButton up = new JButton("UP");
        up.setPreferredSize(new Dimension(0,40));
        buttonTopPosition.add(up, BorderLayout.CENTER);

        up.addActionListener(e ->
        {
            if(boucleIsFinish == true){
                int[] currentBox ;
                int x = p.board.getPlayer()[0];
                int y = p.board.getPlayer()[1];
                currentBox = p.moveUp(x, y);
                caseBoucle(p.boucle(currentBox));
                refreshBoard(x, y);
            }
        });

        JButton down = new JButton("DOWN");
        down.setPreferredSize(new Dimension(70,40));
        buttonPosition.add(down, BorderLayout.CENTER);

        down.addActionListener(e ->
        {
            if(boucleIsFinish == true){
                int[] currentBox ;
                int x = p.board.getPlayer()[0];
                int y = p.board.getPlayer()[1];
                currentBox = p.moveDown(x, y);
                caseBoucle(p.boucle(currentBox));
                refreshBoard(x, y);
            }
        });

        JButton left = new JButton("LEFT");
        left.setPreferredSize(new Dimension(70,40));
        buttonPosition.add(left, BorderLayout.WEST);

        left.addActionListener(e ->
        {
            if(boucleIsFinish == true){
                int[] currentBox ;
                int x = p.board.getPlayer()[0];
                int y = p.board.getPlayer()[1];
                currentBox = p.moveLeft(x, y);
                caseBoucle(p.boucle(currentBox));
                refreshBoard(x, y);
            }
        });

        JButton right = new JButton("RIGHT");
        right.setPreferredSize(new Dimension(70,40));
        buttonPosition.add(right, BorderLayout.EAST);

        
            right.addActionListener(e ->
        {
            if(boucleIsFinish == true){

                int[] currentBox ;
                int x = p.board.getPlayer()[0];
                int y = p.board.getPlayer()[1];
                currentBox = p.moveRight(x, y);
                caseBoucle(p.boucle(currentBox));
                // if(p.boucle().size()>1){
                //     caseBoucle(p.boucle());
                // }
                refreshBoard(x, y);
            }   
        });
       
        

        return buttonMove;
    }

    private JPanel energyPanel(){
        JPanel energyPanel = new JPanel(new GridLayout(5,2));
        energyPanel.setPreferredSize(new Dimension(100,100));

        JLabel energyIcon = new JLabel(iconEnergy, JLabel.CENTER);
        energy = new JLabel("", JLabel.CENTER);
        energy.setFont(new Font("Calibri", Font.BOLD, 32));
        energy.setText(String.valueOf(p.energy));
        energyPanel.add(energyIcon);
        energyPanel.add(energy);

        JLabel energyWinIcon = new JLabel(iconEnergyWin, JLabel.CENTER);
        energyWin = new JLabel("", JLabel.CENTER);
        energyWin.setFont(new Font("Calibri", Font.BOLD, 32));
        energyWin.setText(String.valueOf(p.energyWin));
        energyPanel.add(energyWinIcon);
        energyPanel.add(energyWin);

        JLabel energyLoseIcon = new JLabel(iconEnergyLose, JLabel.CENTER);
        energyLose = new JLabel("", JLabel.CENTER);
        energyLose.setFont(new Font("Calibri", Font.BOLD, 32));
        energyLose.setText(String.valueOf(p.energyLose));
        energyPanel.add(energyLoseIcon);
        energyPanel.add(energyLose);
        
        JLabel distanceIcon = new JLabel(iconDistance, JLabel.CENTER);
        distance = new JLabel("", JLabel.CENTER);
        distance.setFont(new Font("Calibri", Font.BOLD, 32));
        distance.setText(String.valueOf(p.distanceParcourure));
        energyPanel.add(distanceIcon);
        energyPanel.add(distance);

        JLabel rollbackIcon = new JLabel(iconRollback, JLabel.CENTER);
        rollback = new JLabel("", JLabel.CENTER);
        rollback.setFont(new Font("Calibri", Font.BOLD, 32));
        rollback.setText(String.valueOf(p.undo));
        energyPanel.add(rollbackIcon);
        energyPanel.add(rollback);

        

        return energyPanel;
    }

    
    private void refreshBoard(int x, int y){
        int[] playerPosition = this.p.board.getPlayer();
        if(p.energy <= 0){
            this.gameOver("You lose !");
        }
        else if(this.p.board.gameOver()){
            this.gameOver("You win !");
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

    private void caseBoucle(ArrayList<int[]> boucle){

        timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    try {
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

    private void gameOver(String title){

        Object[] options = {"Restart", "Show the best way" , "See games history", "Leave"};
        int n = JOptionPane.showOptionDialog(this, showResultDialog(), title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, null, options, null);
        // JOptionPane.showMessageDialog(this, resultPanel, "You lose !!", JOptionPane.CLOSED_OPTION, null);
        if(n == 0){
            
        }
        else if(n == 1){
              
        }
        else if(n == 2){
            
        }
        else{
            // this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }

    }

    private JPanel showResultDialog(){
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

        return resultPanel;
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