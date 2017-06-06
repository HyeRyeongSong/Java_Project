package MenuController;

import GUI.ElementArray;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class MenuToolController
{
    private CreateJSonFile createJSonFile;
    private CreateJavaFile createJavaFile;
    private JFileChooser jsonFileChooser;
    private JFileChooser javaFileChooser;
    private ElementArray ea;

    private File currentFile;

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
        currentFile = null;
    }

    public void openFile() throws ParseException
    {
        makeNewFile();
        int returnVal = jsonFileChooser.showOpenDialog(new JFrame());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            currentFile = jsonFileChooser.getSelectedFile();
            try {
                createJSonFile.parseJSonFile(currentFile.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (org.json.simple.parser.ParseException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void saveFile() {
    }

    public void saveasFile() {
    }

    public void makeJavaFile() {
    }

    public void exitProgram()
    {
        System.exit(0);
    }
}
