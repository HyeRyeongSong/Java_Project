package Controller;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by HyeRyeongSong on 2017. 6. 6..
 */
public class MenuToolController
{
    private JSonFileController JSonFileController;
    private CreateJavaFile createJavaFile;
    private JFileChooser jsonFileChooser;
    private JFileChooser javaFileChooser;
    private EditorController editorController;

    private File currentFile;

    public MenuToolController(EditorController ea) throws Exception {
        JSonFileController = new JSonFileController();
        createJavaFile = new CreateJavaFile();
        jsonFileChooser = new JFileChooser();
        javaFileChooser = new JFileChooser();
        this.editorController = ea;

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
        editorController.clear();
        currentFile = null;
    }

    public void openFile()
    {
        int returnedValue = jsonFileChooser.showOpenDialog(new JFrame());
        if(returnedValue == JFileChooser.APPROVE_OPTION) {
            currentFile = jsonFileChooser.getSelectedFile();
            try {
                editorController.clear();
                JSonFileController.saveJSonFile(currentFile.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (org.json.simple.parser.ParseException e)
            {
                e.printStackTrace();
            }
        }
        //파일 불러와서 ArrayList 저장하는 코드
        editorController.loadComponent();
    }

    //파일의 이름이 지정되어 있지 않을 경우 "다른이름으로 저장 메소드 호출"
    public void saveFile()
    {
        if(currentFile == null) {
            this.saveAsFile();
            return;
        }
        //String으로 변환된 ElementArray의 내용들을 dataElements에 저장
        String dataElements = JSonFileController.MakeJSonFile();
        try {
            String filePath = currentFile.getPath();
            if(!(filePath.endsWith(".json")) && !(filePath.endsWith(".JSON"))) {
                filePath += ".json";
            }
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(dataElements);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAsFile()
    {
        int returnedValue = jsonFileChooser.showSaveDialog(new JFrame());
        if (returnedValue == JFileChooser.APPROVE_OPTION)
        {
            currentFile = jsonFileChooser.getSelectedFile();
            this.saveFile();
        }
    }

    public void createJavaFile() {
        File javaFile;
        int returnedValue = javaFileChooser.showSaveDialog(new JFrame());
        if (returnedValue == JFileChooser.APPROVE_OPTION) {
            javaFile = javaFileChooser.getSelectedFile();
            try {
                String filePath = javaFile.getPath();
                if(!(filePath.endsWith(".java")) && !(filePath.endsWith(".JAVA"))) {
                    filePath += ".java";
                }
                String fileName = javaFile.getName();
                String dataElements = createJavaFile.MakeJavaFile(fileName);
                FileWriter fileWriter = new FileWriter(filePath);
                fileWriter.write(dataElements);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void exitProgram()
    {
        System.exit(0);
    }
}
