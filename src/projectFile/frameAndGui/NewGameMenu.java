/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectFile.frameAndGui;

import projectFile.fileChanger.ImagesCopier;
import projectFile.fileChanger.MyFilechooser;
import projectFile.fileChanger.ReaderAndWriter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Piotr Siwek
 */
public class NewGameMenu extends javax.swing.JFrame {

    private NewGameMenu newGameMenu;
    private ChooseImagePanel chooseImagePanel;
    private GameFrame gameFrame;
    private StartMenu startMenu;
    private MyFilechooser filechooser;
    private ReaderAndWriter MyRAW;
    private javax.swing.JButton addImageButton;
    private javax.swing.JButton changeInfoButton;
    private javax.swing.JButton mainMenuButton;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel imageViewPanel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane scroll_of_info;
    private javax.swing.JTextArea infoImage_TextArea;
    private javax.swing.JLabel numberLabel;
    private javax.swing.JLabel quantifyLabel;
    private javax.swing.JSlider quantifySlide;
    private javax.swing.JLabel sizeLabel;
    private javax.swing.JSlider sizeSlide;
    private javax.swing.JLabel sizenumberLabel;
    private javax.swing.JLabel startGameLabel;
    private javax.swing.JButton startNewGameButton;
    private int [] howManyPuzzle = {4,9,16,25,36,49,64,81,100};

    private javax.swing.JButton nextButton;
    private javax.swing.JLabel pageNumberLabel;
    private javax.swing.JButton pos1Button;
    private javax.swing.JButton pos2Button;
    private javax.swing.JButton pos3Button;
    private javax.swing.JButton pos4Button;
    private javax.swing.JButton pos5Button;
    private javax.swing.JButton previousButton;
    private boolean path_is_correct;
    private String path_ofChosenImage;
    private String path_folderForImages="C:\\Users\\Hores di En'Claws\\Desktop\\Java\\ProjektyRozne\\Puzzle_lv2\\src\\projectFile\\images";

    public NewGameMenu(StartMenu startMenu) {
        setTitle("Square Puzzle");
        this.newGameMenu=this;
        this.startMenu=startMenu;
        setResizable(false);
        initComponents();
        initLayout();
        setFrameBoundsAndLocation();


        chooseImagePanel.refresh();
    }
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        MyRAW = new ReaderAndWriter(newGameMenu);

        add_All_Sections();

        this.filechooser= new MyFilechooser();
        filechooser.setVisible(false);
        gameFrame = new GameFrame(startMenu,newGameMenu);
    }
    private void add_All_Sections(){
        add_ImageChoose_Section();
        add_Info_Section();
        add_Quantify_and_Size_Section();
        add_Back_Add_Start_Section();
    }
    private void add_ImageChoose_Section (){
        imageViewPanel = new ImagePanel();
        chooseImagePanel= new ChooseImagePanel(newGameMenu);

        ActionListener chooseButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((ChooseImagePanel)chooseImagePanel).chooseImage(
                        ((JButton)e.getSource()).getText());

            }
        };
        ActionListener changePageListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseImagePanel.change_page(
                        ((JButton)e.getSource()).getText()
                );
            }
        };
        pos1Button = new javax.swing.JButton();
        pos2Button = new javax.swing.JButton();
        pos3Button = new javax.swing.JButton();
        pos4Button = new javax.swing.JButton();
        pos5Button = new javax.swing.JButton();
        previousButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        pageNumberLabel = new javax.swing.JLabel();

        pos1Button.setText("-empty-");
        pos1Button.addActionListener(chooseButtonListener);

        pos2Button.setText("-empty-");
        pos2Button.addActionListener(chooseButtonListener);

        pos3Button.setText("-empty-");
        pos3Button.addActionListener(chooseButtonListener);

        pos4Button.setText("-empty-");
        pos4Button.addActionListener(chooseButtonListener);

        pos5Button.setText("-empty-");
        pos5Button.addActionListener(chooseButtonListener);

        previousButton.setText("previous");
        previousButton.addActionListener(changePageListener);

        nextButton.setText("next");
        nextButton.addActionListener(changePageListener);

        pageNumberLabel.setText("page?");
    }
    private void add_Info_Section(){
        scroll_of_info = new javax.swing.JScrollPane();
        infoImage_TextArea = new javax.swing.JTextArea();
        changeInfoButton = new javax.swing.JButton();
        infoLabel = new javax.swing.JLabel();

        infoLabel.setText("Information about image:");
        infoImage_TextArea.setColumns(20);
        infoImage_TextArea.setRows(5);
        infoImage_TextArea.setEditable(false);
        scroll_of_info.setViewportView(infoImage_TextArea);

        changeInfoButton.setText("change info");
        changeInfoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (!infoImage_TextArea.isEditable()){
                    infoImage_TextArea.setEditable(true);
                    changeInfoButton.setText("accept");
                }
                else {
                    MyRAW.changeInfo(
                            chooseImagePanel.getPosition_of_chosen_image()
                    );
                    chooseImagePanel.refill_Patches_and_Infos_list();
                    infoImage_TextArea.setEditable(false);
                    changeInfoButton.setText("change info");
                }
            }
        });
    }
    private void add_Quantify_and_Size_Section(){
        quantifySlide = new javax.swing.JSlider(JSlider.HORIZONTAL,2,10,4);
        quantifyLabel = new javax.swing.JLabel();
        sizeLabel = new javax.swing.JLabel();
        sizenumberLabel = new javax.swing.JLabel();
        numberLabel = new javax.swing.JLabel();
        sizeSlide = new javax.swing.JSlider(JSlider.HORIZONTAL,40,160,60);

        quantifySlide.setMajorTickSpacing(1);
        quantifySlide.setPaintTicks(true);
        quantifySlide.setSnapToTicks(true);
        quantifySlide.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                numberLabel.setText(""+howManyPuzzle[((JSlider)e.getSource()).getValue()-2]);
                setMaxValueSlides();
            }
        });

        sizeSlide.setMajorTickSpacing(20);
        sizeSlide.setMinorTickSpacing(10);
        sizeSlide.setPaintTicks(true);
        sizeSlide.setPaintLabels(true);
        sizeSlide.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                sizenumberLabel.setText(String.valueOf(((JSlider)e.getSource()).getValue()));
                setMaxValueSlides();
            }
        });

        quantifyLabel.setText("Puzzle quantity:");
        numberLabel.setText(String.valueOf(quantifySlide.getValue()*quantifySlide.getValue()));

        sizeLabel.setText("The size of the puzzle:");
        sizenumberLabel.setText(String.valueOf(sizeSlide.getValue()));
    }
    private void add_Back_Add_Start_Section(){
        mainMenuButton = new javax.swing.JButton();
        addImageButton = new javax.swing.JButton();
        startGameLabel = new javax.swing.JLabel();
        startNewGameButton = new javax.swing.JButton();

        startGameLabel.setText("Accept option and start a new game");
        startNewGameButton.setText("Let's start");
        startNewGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setVisible(false);
                if(path_is_correct) {
                    try {
                        gameFrame.setIsTutorial(false);
                        gameFrame.newOpen(Integer.parseInt(numberLabel.getText()), (int) sizeSlide.getValue(), path_ofChosenImage);
                    } catch (Exception e) {
                        setVisible(true);
                         JOptionPane.showMessageDialog(newGameMenu, "Error loading data!");
                    }
                }
                else {
                    setVisible(true);
                    JOptionPane.showMessageDialog(newGameMenu, "You have not selected an image");
                    try {
                        chooseImagePanel.chooseFirst();
                    } catch (Exception x) {
                        JOptionPane.showMessageDialog(newGameMenu, "You have not added any pictures!");
                    }
                }
            }
        });

        addImageButton.setText("Add new image");
        addImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    add_ImageToYourOwnList(filechooser.getImagePatch());
                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(null,"files cannot be loaded");
                }
            }
        });

        mainMenuButton.setText("Back to Main");
        mainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToMain();
            }
        });
    }
    private void initLayout (){
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(changeInfoButton))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(infoLabel)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(quantifyLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(numberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(sizeLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(sizenumberLabel)))
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(sizeSlide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(quantifySlide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(startNewGameButton)
                                                .addContainerGap())
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(startGameLabel)
                                                .addGap(26, 26, 26))))
                        .addComponent(scroll_of_info, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(infoLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scroll_of_info, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(changeInfoButton)
                                .addGap(23, 23, 23)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(numberLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(quantifyLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(quantifySlide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(sizeLabel)
                                        .addComponent(sizenumberLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sizeSlide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(startGameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(startNewGameButton)
                                .addContainerGap())
        );

        javax.swing.GroupLayout imageViewPanelLayout = new javax.swing.GroupLayout(imageViewPanel);
        imageViewPanel.setLayout(imageViewPanelLayout);
        imageViewPanelLayout.setHorizontalGroup(
                imageViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        imageViewPanelLayout.setVerticalGroup(
                imageViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );


        javax.swing.GroupLayout panelInScrollLayout = new javax.swing.GroupLayout(chooseImagePanel);
        chooseImagePanel.setLayout(panelInScrollLayout);
        panelInScrollLayout.setHorizontalGroup(
                panelInScrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelInScrollLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelInScrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(pos1Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pos2Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pos3Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pos4Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pos5Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelInScrollLayout.createSequentialGroup()
                                                .addComponent(previousButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(pageNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        panelInScrollLayout.setVerticalGroup(
                panelInScrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelInScrollLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pos1Button)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pos2Button)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pos3Button)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pos4Button)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pos5Button)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelInScrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelInScrollLayout.createSequentialGroup()
                                                .addGroup(panelInScrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(previousButton)
                                                        .addComponent(nextButton))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(panelInScrollLayout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(pageNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(imageViewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(chooseImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(mainMenuButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(addImageButton)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addContainerGap())
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(imageViewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(chooseImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(mainMenuButton)
                                                        .addComponent(addImageButton))
                                                .addGap(21, 21, 21))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
    private void setFrameBoundsAndLocation(){
        int systemWindth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int systemHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        this.setBounds((systemWindth-this.getWidth())/2, (systemHeight-this.getHeight())/2,this.getWidth(),this.getHeight());
        this.setLocation((systemWindth-this.getWidth())/2, (systemHeight-this.getHeight())/2);
    }
    private void setMaxValueSlides(){
        int systemWindth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int systemHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        double space = 100;
        if (getSizeFutureFrame()>(int)(systemHeight - space))
            sizeSlide.setValue(((int)(systemHeight - space)) / (quantifySlide.getValue()+3));
    }

    private int getSizeFutureFrame (){
        return ((quantifySlide.getValue()+3)*sizeSlide.getValue());
    }
    public void backToMain (){
        setVisible(false);
        startMenu.setVisible(true);
    }
    public ArrayList <?> get_Components_from_chooseImage_section_and_info(){
        ArrayList<Object> components_list= new ArrayList<>();
        components_list.add(imageViewPanel);
        components_list.add(pos1Button);
        components_list.add(pos2Button);
        components_list.add(pos3Button);
        components_list.add(pos4Button);
        components_list.add(pos5Button);
        components_list.add(previousButton);
        components_list.add(pageNumberLabel);
        components_list.add(nextButton);
        components_list.add(infoImage_TextArea);
        return components_list;
    }
    private void add_ImageToYourOwnList (String imgPath) {
        ImagesCopier imagec = new ImagesCopier(path_folderForImages);
        String newPath = imagec.copyImageAndGiveNewPath(imgPath);
        if (!newPath.equals("error")) {
            MyRAW.writeNewRekord(newPath);
            chooseImagePanel.refresh();
            JOptionPane.showMessageDialog(this, "You added a picture succesfully");
        }
    }
    public void setPath_ofChosenImage(String path){
        this.path_ofChosenImage=path;
    }
    public void setPath_is_correct(boolean b){
        path_is_correct=b;

    }
    public ReaderAndWriter getMyRAW(){
        return MyRAW;
    }
    public GameFrame getGameFrame (){
        return gameFrame;
    }
}
