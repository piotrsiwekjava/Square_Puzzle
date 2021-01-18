package projectFile.fileChanger;

import javax.swing.*;
import java.io.File;

/**
 *
 * @author Piotr Siwek
 */

public class MyFilechooser extends JFrame {

    private JFileChooser fileChooser;

    public MyFilechooser(){
        this.setTitle("Choose Your Image");
        this.setBounds(250,300,300,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setAcceptAllFileFilterUsed(true);

        this.getContentPane().add(fileChooser);

    }
    public String getImagePatch(){
        String path="";
        int tmp = fileChooser.showOpenDialog(rootPane);
        if (tmp==0)
            path = ((File)(fileChooser.getSelectedFile())).getPath();
        System.out.println("Image path" + path);
        return path;
    }
}
