package projectFile.frameAndGui;

import projectFile.OwnObject.OnePuzzle;
import projectFile.OwnObject.ArrangingField;
import projectFile.OwnObject.Sector;
import projectFile.fileChanger.ImageSpliter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author Piotr Siwek
 */

class GamePanel extends JPanel {

    private final int howManyPuzzle;
    private final int puzzlesSize;
    private final int brinkSize;
    private int space;
    private int myTimerDelay;
    private Timer myTimer;
    private Point lastPoint;
    private OnePuzzle chooseOne;
    private int panelWindth;
    private int panelHeight;
    private ArrayList <OnePuzzle> puzzleInGameList = new ArrayList<>();
    private ArrangingField arrangingField;
    private String pathImage;
    private BufferedImage tableImage;
    private boolean showWinner;
    private int time;

    public GamePanel(int howManyPuzzle,int puzzlesSize,String pathImage) {
        super();
        restart();
        this.howManyPuzzle=howManyPuzzle;
        this.puzzlesSize=puzzlesSize;
        this.pathImage=pathImage;
        System.out.println(pathImage);
        createSpace();
        this.brinkSize=puzzlesSize+space;
        setVisible(true);
        this.setBackground(Color.GRAY);
        setPanelWindthAndHeight();
        this.setSize(panelWindth,panelHeight);
        getTableImage();
        addArrangingField();
        create_And_Add_ThePuzzle();
        setPuzzlesLocation();
        addListeners();
        addTimer();
        time=0;
        setFrameSizeAndLocation();

        showWinner=true;
    }
    private void createSpace(){
        if(puzzlesSize<100)this.space=10;
        else if (puzzlesSize>=100 && puzzlesSize<200)this.space=20;
        else this.space=30;
    }
    private void setPanelWindthAndHeight (){
        panelWindth= (int) (brinkSize *2 + (Math.sqrt((double)(howManyPuzzle))*puzzlesSize));
        panelHeight = panelWindth;
        System.out.println("Panel size "+panelHeight);
        setPreferredSize(new Dimension(panelWindth,panelHeight));
    }
    private void setFrameSizeAndLocation (){
        int systemWindth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int systemHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        this.setBounds((systemWindth-this.getWidth())/2, (systemHeight-this.getHeight())/2,this.getWidth(),this.getHeight());
        this.setLocation((systemWindth-this.getWidth())/2, (systemHeight-this.getHeight())/2);
    }
    private void create_And_Add_ThePuzzle(){
        ImageSpliter imageSpliter =new ImageSpliter(pathImage,arrangingField.getHowManyAreasOnSide(),
                arrangingField.getHowManyAreasOnSide(),puzzlesSize);
        imageSpliter.split();
        for (int i =0; i<howManyPuzzle;i++){
            puzzleInGameList.add(new OnePuzzle(imageSpliter.getImagesPiecesList().get(i),puzzlesSize,i+1));
        }
    }
    private void addArrangingField (){
        arrangingField=new ArrangingField(
                new Point(brinkSize, brinkSize),
                new Point(this.getWidth() - brinkSize, brinkSize),
                new Point(brinkSize,this.getHeight() - brinkSize),
                new Point(this.getWidth() - brinkSize,this.getHeight() - brinkSize),
                puzzlesSize);
    }

    private void addListeners (){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastPoint = new Point(e.getX(),e.getY());
                System.out.println("Move "+getChoosenPuzzel());
                chooseOne=getChoosenPuzzel();
                removePuzzelfromSector();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Graphics g = getGraphics();
                g.setColor(Color.BLACK);
                try {
                    chooseOne.setMyX(getRimX(e));
                    chooseOne.setMyY(getRimY(e));
                    refillAllPuzzle();
                }
                catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
                g.dispose();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    lastPoint = new Point(e.getX(), e.getY());
                    System.out.println("Turn "+getChoosenPuzzel());
                    chooseOne = getChoosenPuzzel();
                    chooseOne.turn();
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                pastePuzzle(e);
            }
        });
    }
    private void addTimer (){
        this.myTimerDelay=100;
        myTimer = new Timer(myTimerDelay,gameTimer);
        myTimer.start();
    }

    ActionListener gameTimer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            redraw();
            checkVictory();
            time+=myTimer.getDelay();
            System.out.println(time);
        }
    };
    /*
    getRimX & getRimY is method to get:
    maximum and minimum coordinates of the image,puzzel, or object, so that it does not come out of the panel.
    */
    private int getRimX(MouseEvent e){
        int rightX = e.getX()-puzzlesSize/2;
        if (rightX<0) rightX=0;
        else if (rightX>(panelWindth-puzzlesSize)) rightX=panelWindth-puzzlesSize;
        return rightX;
    }
    private int getRimY(MouseEvent e){
        int rightY = e.getY()-puzzlesSize/2;
        if (rightY<0)rightY=0;
        else if (rightY>panelHeight-puzzlesSize)rightY=panelHeight-puzzlesSize;
        return rightY;
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.drawImage(tableImage,0,0,this);
        g.fillRect(arrangingField.getEdgeA().x,arrangingField.getEdgeA().y, arrangingField.getUpSize(), arrangingField.getSideSize());
        drawPuzzelOnField(g);
    }
    private void drawPuzzelOnField (Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        for (OnePuzzle p: puzzleInGameList){
            double theta = (Math.PI * 2) / 360 * p.getRotationAngle();
            g2d.rotate(theta,p.getMyX()+puzzlesSize/2,p.getMyY()+puzzlesSize/2);
            g2d.drawImage(p.getImage(),p.getMyX(),p.getMyY(),p.getMySize(),p.getMySize(),this);
            g2d.rotate(-theta,p.getMyX()+puzzlesSize/2,p.getMyY()+puzzlesSize/2);

        }
    }
    private void redraw (){
        this.repaint();
    }

    private void setPuzzlesLocation(){
        for (OnePuzzle p: puzzleInGameList){
            Point o = randomPoint();
            p.setMyX(o.x);
            p.setMyY(o.y);
            p.refillPoints();
        }
    }
    /*
    randomPoint - draws a puzzle's place on the board when game starts
    */
    private Point randomPoint(){
        int x = new Random().nextInt(panelWindth-puzzlesSize-(space*2))+space;
        int y = new Random().nextInt(panelHeight-puzzlesSize-(space*2))+space;
        return new Point(x,y);
    }
    private BufferedImage getTableImage (){
        try {
           tableImage = ImageIO.read(new File("C:\\Users\\Hores di En'Claws\\Desktop\\Java\\ProjektyRozne\\Puzzle_lv2\\src\\projectFile\\images\\bamboo.jpg"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return tableImage;
    }
    private ArrayList<OnePuzzle> getPuzzleInGameList() {
        return puzzleInGameList;
    }
    /*
    refillAllPuzzle - restores the occupied space taken by the puzzle
     */
    private void refillAllPuzzle (){
        for (OnePuzzle p: puzzleInGameList){
            p.refillPoints();
        }
    }
    private OnePuzzle getChoosenPuzzel (){
        int x = lastPoint.x;
        int y = lastPoint.y;
        for (OnePuzzle p: puzzleInGameList){
            if (x>=p.getMyReservedPoints()[0] && x<=p.getMyReservedPoints()[1]
            && y>=p.getMyReservedPoints()[2] && y<=p.getMyReservedPoints()[3]){
                return p;}
        }
        return null;
    }
    private void pastePuzzle (MouseEvent e){
        for (Sector s: arrangingField.getSectorsList()){
            if (!s.hasPuzzle &&
                e.getX()>=s.getEdgeA().x &&
                e.getX()<=s.getEdgeB().x &&
                e.getY()>=s.getEdgeA().y &&
                e.getY()<=s.getEdgeC().y) {
                chooseOne.setMyX(s.getEdgeA().x);
                chooseOne.setMyY(s.getEdgeA().y);
                s.setHasPuzzle();
                s.setNumberPuzzle(chooseOne.getNumber());
            }
        }
    }
    private void removePuzzelfromSector (){
        for (Sector s: arrangingField.getSectorsList()){
            if (s.getNumberPuzzle()==chooseOne.getNumber()){
                s.setNumberPuzzle(0);
                s.setHasPuzzle();
            }
        }
    }
    public void restart(){
        arrangingField=null;
        puzzleInGameList.clear();
    }

    private void checkVictory (){
        if (youWin() && showWinner) {
            showWinner=false;
            JOptionPane.showMessageDialog(this, "Congratulations!" + "\n " + "The puzzles have been arranged");
        }
    }
    private boolean youWin (){
        int i=1;
        int all=0;
        for (Sector s: arrangingField.getSectorsList()){
            if (s.getNumberPuzzle()==i)
                for (OnePuzzle p: puzzleInGameList){
                    if (p.getNumber()==i && p.getRotationAngle()==0)
                        ++all;
                }
            i++;
        }
        if(all == howManyPuzzle)return true;
        return false;
    }
    public Timer getMyTimer(){
        return myTimer;
    }
    public int getTime(){
        return time;
    }
}