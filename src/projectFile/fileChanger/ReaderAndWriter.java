package projectFile.fileChanger;

import projectFile.frameAndGui.NewGameMenu;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Piotr Siwek
 */

public class ReaderAndWriter {

    private BufferedWriter writer;
    private NewGameMenu newGameMenu;
    private BufferedReader reader;
    private int positionInFile;
    private String newText;
    private ArrayList<String> recordList;
    private String pathText = "C:\\Users\\Hores di En'Claws\\Desktop\\Java\\ProjektyRozne\\Puzzle_lv2\\src\\projectFile\\images\\imagesinfo";

    public ReaderAndWriter(NewGameMenu newGameMenu){
        super();
        this.newGameMenu=newGameMenu;
        this.recordList=new ArrayList<>();
        reloadRecordList();
    }
    public void reloadRecordList(){
        recordList.clear();
        try {
            reader = new BufferedReader(new FileReader(pathText));
            String str;
            while ((str = reader.readLine()) != null){
                recordList.add(str);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void changeInfo(int positionInFile){
        this.positionInFile=positionInFile;

        this.newText=((JTextArea)(newGameMenu.get_Components_from_chooseImage_section_and_info().get(9))).getText();
        correctNewText();
        ((JTextArea)(newGameMenu.get_Components_from_chooseImage_section_and_info().get(9))).setText(newText);
        clean_write_load();
        JOptionPane.showMessageDialog(newGameMenu,"Description has been changed");
    }
    private void correctNewText(){
        String newString = newText;
        newString.trim();
        String newString2="";
        for (int i=0;i<newString.length();i++){
            if (newString.charAt(i)!='\\' && newString.charAt(i)!='\n')
                newString2+=newString.charAt(i);
        }
        String [] tab1 = newString2.split("  ");
        this.newText="";
        for (int j=0;j<tab1.length;j++){
            this.newText+=tab1[j];
        }
    }
    private void clean_write_load(){
        cleanFile();
        rewrite();
        reloadRecordList();
    }
    private void rewrite() {
        try {
            
            writer = new BufferedWriter(new FileWriter(pathText,true));
            String reStr="";
           for (int i = 0; i< recordList.size(); i++){
                if (i==positionInFile) {
                    String[] tab = recordList.get(i).split("&&&");
                    reStr+=tab[0]+"&&&"+newText;
                    writer.append(reStr+"\n");
                }
                else writer.append(recordList.get(i)+"\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeNewRekord(String imagepathAndInfo ){
        try {
            writer = new BufferedWriter(new FileWriter(pathText,true));
            writer.append(imagepathAndInfo+"\n");
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        reloadRecordList();
    }
    public void cleanFile(){
        try {
            writer = new BufferedWriter(new FileWriter(pathText));
            writer.write("");
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public ArrayList<String> getrekordList () {
        return this.recordList;
    }
}
