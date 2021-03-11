package projectFile.frameAndGui;

import projectFile.fileChanger.ReaderAndWriter;

import javax.swing.*;
import java.util.ArrayList;

/**
 *
 * @author Piotr Siwek
 */

public class ChooseImagePanel extends JPanel {

    private ChooseImagePanel chooseImagePanel;
    private NewGameMenu newGameMenu;
    private ReaderAndWriter MyRAW;
    private ArrayList <String> pathImagesList;
    private ArrayList <String> imagesInfoList;
    private int position_of_chosen_image;
    private int currentPageNumber;
    private int howMuchPages;
    private String choiceButtonText;

    public ChooseImagePanel(NewGameMenu newGameMenu){
        super();
        this.newGameMenu=newGameMenu;
        this.MyRAW=newGameMenu.getMyRAW();

        pathImagesList=new ArrayList<>();
        imagesInfoList = new ArrayList<>();

        setVisible(true);
    }

    public void refresh(){
        refill_Patches_and_Infos_list();
        update_pageNumber();
        change_reference_on_the_buttons();
    }
    public void chooseFirst(){
        position_of_chosen_image=0;
        change_NewGameMenu();
    }
    public void refill_Patches_and_Infos_list(){
        pathImagesList.clear();
        imagesInfoList.clear();
        String[] tab = new String[2];
        String rez = "Brak opisu. \n Dodaj coś od siebie";
        for (String str: MyRAW.getrekordList()) {
            if (str.contains("&&&")) {
                tab = str.split("&&&");
                if (tab[1].equals("")) tab[1] = rez;
            }
            else{
                tab[0]=str;
                tab[1]=rez;
            }
                pathImagesList.add(tab[0]);
                imagesInfoList.add(tab[1]);
        }
    }

    private String getImageName(String str){
        return str.substring(str.lastIndexOf("\\")+1);
    }
    private void change_NewGameMenu (){
        change_ImageViewPanel();
        change_InfoTextArea();
        newGameMenu.setPath_ofChosenImage(pathImagesList.get(position_of_chosen_image));
        newGameMenu.setPath_is_correct(true);
    }

    private void change_ImageViewPanel(){
        ((ImagePanel)(newGameMenu.get_Components_from_chooseImage_section_and_info().get(0)))
                .redraw(pathImagesList.get(position_of_chosen_image),0,0, 100,100);
    }
    private void change_InfoTextArea(){
        ((JTextArea)(newGameMenu.get_Components_from_chooseImage_section_and_info().get(9)))
                .setText(imagesInfoList.get(position_of_chosen_image));
    }
    private void whithImages (){
        int i=0;
        for (String str: pathImagesList){
            if (getImageName(str).equals(choiceButtonText)){
                position_of_chosen_image=i;
            }
            i++;
        }
    }
    public void change_page (String str){
        change_PageNumber(str);
        change_PageNumber_LabelText();
        change_reference_on_the_buttons();
    }
    private void update_pageNumber(){
        setHowMuchPages();
        currentPageNumber=1;
        change_PageNumber_LabelText();
    }

    private void change_PageNumber(String str){
        if (str.equals("previous") && currentPageNumber!=1){
            --currentPageNumber;
        }
        else if (str.equals("next") && currentPageNumber<howMuchPages){
            ++currentPageNumber;
        }

    }
    private void change_PageNumber_LabelText() {
        ((JLabel) newGameMenu.get_Components_from_chooseImage_section_and_info()
                .get(7))
                .setText(currentPageNumber+"/"+howMuchPages);
    }
    private void setHowMuchPages (){
        int i=1;
        while(true) {
            howMuchPages = i;
            if (pathImagesList.size() <= (5*i))
                break;
            i++;
        }
    }

    private void change_reference_on_the_buttons (){
        cleanTheButtons();
        for (int i=((currentPageNumber*5)-5), k=1 ; i<(currentPageNumber*5) && i<pathImagesList.size() ; i++,k++){
            ((JButton) newGameMenu.get_Components_from_chooseImage_section_and_info().get(k))
                    .setText(
                            getImageName(pathImagesList.get(i)));
            ((JButton) newGameMenu.get_Components_from_chooseImage_section_and_info().get(k))
                    .setVisible(true);

        }
    }
    private void cleanTheButtons (){
        for (int i=1;i<6;i++) {
            ((JButton) newGameMenu.get_Components_from_chooseImage_section_and_info().get(i)).setText("-empty-");
            ((JButton) newGameMenu.get_Components_from_chooseImage_section_and_info().get(i)).setVisible(false);
        }
    }
    public void chooseImage(String buttonText){
        this.choiceButtonText=buttonText;
        whithImages();
        change_NewGameMenu();
    }

    public int getPosition_of_chosen_image() {
        return position_of_chosen_image;
    }
}
