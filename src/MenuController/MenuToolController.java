package MenuController;

import GUI.ElementArray;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class MenuToolController
{
    private CreateJSonFile createJSonFile;
    private CreateJavaFile createJavaFile;
    private JFileChooser jsonFileChooser;
    private JFileChooser javaFileChooser;
    private ElementArray ea;

    private File selectedFile;

    public MenuToolController(ElementArray ea) throws Exception {
        createJSonFile = new CreateJSonFile();
        createJavaFile = new CreateJavaFile();
        jsonFileChooser = new JFileChooser();
        javaFileChooser = new JFileChooser();
        this.ea = ea;

        jsonFileChooser.setCurrentDirectory(
            new File(System.getProperty("user.home") + "//" + "Desktop"));
        FileNameExtensionFilter jsonFileExtensionFilter =
            new FileNameExtensionFilter("JSON 파일", "json");

        javaFileChooser.setCurrentDirectory(
            new File(System.getProperty("user.home") + "//" + "Desktop"));
        FileNameExtensionFilter javaFileExtensionFilter =
            new FileNameExtensionFilter("JAVA 파일", "java");

        jsonFileChooser.setFileFilter(jsonFileExtensionFilter);
        javaFileChooser.setFileFilter(javaFileExtensionFilter);

    }

    public void makeNewFile()
    {
        ea.clear();
    }

    public void openFile()
    {
    }

    public void saveFile() {
    }

    public void saveAsFile() {
    }

    public void makeJavaFile() {
    }

    public void exitProgram()
    {
        System.exit(0);
    }
}
