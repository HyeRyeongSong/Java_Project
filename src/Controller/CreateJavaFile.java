package Controller;

import Model.ElementArray;

/**
 * Created by HyeRyeongSong on 2017. 6. 6..
 */
class CreateJavaFile
{
    /**
     * 자바파일에 저장할 문자열을 만드는 메소드
     * @param name 파일 이름
     * @return 자바파일에 저장될 문자열 리턴
     */
    String MakeJavaFile(String name) {
        if((name.endsWith(".java")) || (name.endsWith(".JAVA"))) {
            name = name.substring(0, name.length()-5);
        }
        StringBuilder fileContent = new StringBuilder(""
            + "import javax.swing.*;\n"
            + "import java.awt.*;\n\n"
            + "public class " + name + " extends JFrame" + "{\n"
            + "    " + name + "()\n"
            + "    {\n"
            + "        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n"
            + "        Container contentPane = getContentPane();\n"
            + "        contentPane.setLayout(null);\n");


        for(int i=0; i< ElementArray.getSize(); ++i) {
            fileContent.append("        ").append(ElementArray.getElement(i).getType())
                    .append(" ").append(ElementArray.getElement(i).getVar())
                    .append(" = new ").append(ElementArray.getElement(i).getType())
                    .append("();\n");
            String strRec = ElementArray.getElement(i).getX() + ", "
                    + ElementArray.getElement(i).getY() + ", "
                    + ElementArray.getElement(i).getW() + ", "
                    + ElementArray.getElement(i).getH();
            fileContent.append("        ").append(ElementArray.getElement(i).getVar())
                    .append(".setBounds(").append(strRec).append(");\n");

            fileContent.append("        ").append(ElementArray.getElement(i).getVar())
                    .append(".setText(\"").append(ElementArray.getElement(i).getText())
                    .append("\");\n");

            fileContent.append("        contentPane.add(").append(ElementArray.getElement(i)
                    .getVar()).append(");\n");
        }
        fileContent.append("        setSize(800, 700);\n");
        fileContent.append("        setVisible(true);\n");
        fileContent.append("    }\n");
        fileContent.append("    public static void main(String[] args)\n")
                .append("    {\n").append("        ").append(name)
                .append(" newObject = new ").append(name).append("();\n")
                .append("    }\n").append("}");
        return fileContent.toString();
    }
}
