

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import javafx.scene.image.Image.*;




public class MyWindow extends JFrame {

    // private static final long serialVersionUID = -4939544011287453046L;

    // private Icon caseIcon;
    private JLabel[][] labelCase;
    private Player p;
    private ImageIcon iconCase, iconRock, iconTree, iconFruit, iconMeat, iconHome, iconPlayer;

    public MyWindow(Player p) {
        super( "GAME4J" );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setSize( 800, 800 );
        this.setLocationRelativeTo( null );
        // this.add(gameBoard);

        this.p = p;
        labelCase = new JLabel [p.board.DIM_X][p.board.DIM_Y];

                
        String urlCase = "C:\\Users\\joudy\\Documents\\EISTI\\S2\\Java\\Projet\\ING1-GAME4J\\src\\caseVert.png";
        // String urlCase = "./src/caseVert.png";
        // ImageIcon iconCase = new ImageIcon(urlCase);
        iconCase = new ImageIcon(new ImageIcon(urlCase).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));

        String urlRock = "C:\\Users\\joudy\\Documents\\EISTI\\S2\\Java\\Projet\\ING1-GAME4J\\src\\caseRock.png";
        // ImageIcon iconRock = new ImageIcon(urlRock);
        iconRock = new ImageIcon(new ImageIcon(urlRock).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));

        String urlTree = "C:\\Users\\joudy\\Documents\\EISTI\\S2\\Java\\Projet\\ING1-GAME4J\\src\\caseTree.png";
        // ImageIcon iconTree = new ImageIcon(urlTree);
        iconTree = new ImageIcon(new ImageIcon(urlTree).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        
        String urlFruit = "C:\\Users\\joudy\\Documents\\EISTI\\S2\\Java\\Projet\\ING1-GAME4J\\src\\caseFruit.png";
        iconFruit = new ImageIcon(new ImageIcon(urlFruit).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        
        String urlMeat = "C:\\Users\\joudy\\Documents\\EISTI\\S2\\Java\\Projet\\ING1-GAME4J\\src\\caseMeat.png";
        iconMeat = new ImageIcon(new ImageIcon(urlMeat).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
    
        String urlHome = "C:\\Users\\joudy\\Documents\\EISTI\\S2\\Java\\Projet\\ING1-GAME4J\\src\\caseHome.png";
        iconHome = new ImageIcon(new ImageIcon(urlHome).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        
        String urlPlayer = "C:\\Users\\joudy\\Documents\\EISTI\\S2\\Java\\Projet\\ING1-GAME4J\\src\\casePlayer.png";
        iconPlayer = new ImageIcon(new ImageIcon(urlPlayer).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        
        

            
        
    }

    public void displayBoard(){

        //----------------GAME BOARD---------------------//
        // Build the "gameBoard".

        JPanel gameBoard = (JPanel) this.getContentPane();
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
                else if(playerPosition[0] == i && playerPosition[1] == j){
                    labelCase[i][j] = new JLabel(iconPlayer, JLabel.CENTER);
                }
                else{
                    labelCase[i][j] = new JLabel(iconCase, JLabel.CENTER);
                }
                
                labelCase[i][j].setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
                gameBoard.add(labelCase[i][j]);
                  
            }
        } 
    }
/*
    //--------------------Related to Icons----------------------------//
    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) 
    {
        Image img = icon.getImage();  
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
        return new ImageIcon(resizedImage);
    }    
    
    public void setIcons()
    {
       //---------------------Set Icons-----------------------------//

        int bOffset = labelCase[0][1].getInsets().left;
        int bWidth = labelCase[0][1].getWidth();
        int bHeight = labelCase[0][1].getHeight();
        
        ImageIcon d;
                 
        d = new ImageIcon(getClass().getResource("./src/image/caseVert.png"));                
        caseIcon =   resizeIcon(d, bWidth - bOffset, bHeight - bOffset);  
        //-------------------------------------------------------//
        
    }

    public Icon getIconCase()
    {
        return caseIcon;       
    }     
    
    */

    public static void main(String[] args) throws Exception {
        // Apply a look'n feel
        UIManager.setLookAndFeel( new NimbusLookAndFeel() );

        Board board = new Board();
        board.Init();
        Player player = new Player(board);

        // Start my window
        MyWindow myWindow = new MyWindow(player);
        myWindow.displayBoard();
        myWindow.setVisible( true );

    

    }

}