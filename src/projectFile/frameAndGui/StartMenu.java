/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectFile.frameAndGui;

import java.awt.*;

/**
 *
 * @author Piotr Siwek
 */
public class StartMenu extends javax.swing.JFrame {

    private StartMenu startMenu;
    private javax.swing.JButton exitButtom;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel autorLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton newGameButtom;
    private javax.swing.JButton tutorialButtom;
    private NewGameMenu newGameMenu;
    private GameFrame gameFrame;

    public StartMenu() {
        setTitle("Square Puzzle");
        initComponents();
        setFrameSizeAndLocation();
        startMenu=this;
        this.setResizable(true);
        newGameMenu = new NewGameMenu(startMenu);
        gameFrame=newGameMenu.getGameFrame();
    }
    private void initComponents() {

        mainPanel = new ImagePanel();
        newGameButtom = new javax.swing.JButton();
        exitButtom = new javax.swing.JButton();
        tutorialButtom = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        autorLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


        mainPanel.setToolTipText("");

        newGameButtom.setText("New Game");

        newGameButtom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setVisible(false);
                newGameMenu.setVisible(true);

            }
        });

        exitButtom.setText("Exit");
        exitButtom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });

        tutorialButtom.setText("Tutorial");
        tutorialButtom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    gameFrame.setIsTutorial(true);

                    startMenu.setVisible(false);
                    gameFrame.loadTutorial();
                    gameFrame.setVisible(true);

                }
                catch (Exception r){
                    startMenu.setVisible(true);
                }
            }
        });

        titleLabel.setFont(new java.awt.Font("Trajan Pro", 3, 20)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Square Puzzle");

        autorLabel.setFont(new java.awt.Font("Yu Gothic Light", 1, 12)); // NOI18N
        autorLabel.setText("by Piotr Siwek");



        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(81, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(newGameButtom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tutorialButtom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(exitButtom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(61, 61, 61))
                            .addComponent(autorLabel))
                        .addGap(93, 93, 93))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(autorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newGameButtom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tutorialButtom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exitButtom)
                .addGap(74, 74, 74))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    private void setFrameSizeAndLocation (){
        int systemWindth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int systemHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        this.setBounds((systemWindth-this.getWidth())/2, (systemHeight-this.getHeight())/2,this.getWidth(),this.getHeight());
        this.setLocation((systemWindth-this.getWidth())/2, (systemHeight-this.getHeight())/2);
    }

    private void tutorialButtomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tutorialButtomActionPerformed
        // TODO add your handling code here:
    }
}
