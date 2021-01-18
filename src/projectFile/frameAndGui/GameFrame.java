package projectFile.frameAndGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 *
 * @author Piotr Siwek
 */

public class GameFrame extends JFrame {

    private GameFrame gameFrame;
    private final NewGameMenu newGameMenu;
    private StartMenu startMenu;
    private JPanel panelButtonow = new JPanel();
    private boolean startgame = false;
    private GamePanel gamePanel;
    private String pathImg;
    private int howManyPuzzle;
    private int sizePuzzle;

    private boolean isTutorial;
    private JButton restartButtom;
    private JButton newGameButtom;
    private JButton mainMenuButtom;

    public GameFrame (StartMenu startMenu, NewGameMenu newGameMenu){
        super();
        setTitle("Square Puzzle");
        this.gameFrame=this;
        this.startMenu=startMenu;
        this.newGameMenu=newGameMenu;
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        initComponents();
    }

    public void newOpen (int howManyPuzzle,int sizePuzzle,String path){
        this.howManyPuzzle=howManyPuzzle;
        this.sizePuzzle=sizePuzzle;
        this.pathImg=path;
        setWhichButtonsSee();
        startGame();
        setFrameSizeAndLocation();
        setVisible(true);
    }
    private void setWhichButtonsSee(){
        if (isTutorial) {
            restartButtom.setVisible(false);
            newGameButtom.setVisible(false);
            mainMenuButtom.setVisible(true);
        }
        else {
            restartButtom.setVisible(true);
            newGameButtom.setVisible(true);
            mainMenuButtom.setVisible(true);
        }

    }
    private void initComponents (){
        addButtons();
        panelButtonow.setBackground(Color.GRAY);
        this.getContentPane().add(panelButtonow, BorderLayout.SOUTH);
    }
    private void addButtons(){
        restartButtom = (JButton)panelButtonow.add(new JButton ("Restart") {
            @Override
            public void paintComponent (Graphics g){
                super.paintComponent(g);
            }
        });
        restartButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        newGameButtom = (JButton)panelButtonow.add(new JButton ("New Game"));
        newGameButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                removeGamePanel();
                newGameMenu.setVisible(true);
            }
        });
        mainMenuButtom = (JButton)panelButtonow.add(new JButton ("Main Menu"));
        mainMenuButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                removeGamePanel();
                startMenu.setVisible(true);
            }
        });
        newGameButtom.setBackground(Color.CYAN);
        mainMenuButtom.setBackground(Color.CYAN);
    }
    private void setFrameSizeAndLocation (){
        int systemWindth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int systemHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        this.setBounds((systemWindth-this.getWidth())/2, (systemHeight-this.getHeight())/2,this.getWidth(),this.getHeight());
        this.setLocation((systemWindth-this.getWidth())/2, (systemHeight-this.getHeight())/2);
    }
    private void buildGUI (){
        gamePanel = new GamePanel(howManyPuzzle,sizePuzzle,pathImg);
    }
    private void addGamePanel (){
        this.add(gamePanel);
        pack();
    }
    private void removeGamePanel (){
        this.remove(gamePanel);
        System.out.println("huraa");
    }

    private void startGame (){
        if (startgame) removeGamePanel();

        startgame=true;
        buildGUI();
        addGamePanel();
        setVisible(true);
    }
    public void setIsTutorial(boolean torF){
        isTutorial=torF;
    }
    public void loadTutorial(){
        newOpen(4,100,
                "C:\\Users\\Hores di En'Claws\\Desktop\\Java\\ProjektyRozne\\Puzzle_lv2\\src\\projectFile" +
                        "\\images\\puzzelmain.jpg");
        try {
            showMassages();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void showMassages() throws InterruptedException {
        showMassage1();
        showMassage2();
        showMassage3();
    }

    private void showMassage1(){
        JOptionPane.showMessageDialog(
                this,"1."+"\n"+"To move the puzzle:"+"\n"+"press and hold the left mouse button.");
    }
    private void showMassage2(){
        JOptionPane.showMessageDialog(
                this,"2."+"\n"+"To rotate a puzzle:"+"\n"+"double click on it."
        );
    }
    private void showMassage3(){
        JOptionPane.showMessageDialog(this,"3."+"\n"+"To arrange the puzzle:"+"\n"+
                "place in the middle of the rectangle");
    }
}
