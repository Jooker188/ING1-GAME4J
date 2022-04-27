import java.util.ArrayList;

public class Data implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

    // LISTE DES INFOS A SAUVEGARDER
    public int posPlayerX;
    public int posPlayerY;
    public ArrayList<int[]> trees;
    public ArrayList<int[]> fruits;
    public ArrayList<int[]> rocks;
    public ArrayList<int[]> meats;
    public ArrayList<int[]> playerDeplacements;
    public int energy, energyLose, energyWin, distance, undo;
    public String date;
}
