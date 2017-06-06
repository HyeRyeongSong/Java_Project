package MenuController;

import GUI.ElementArray;

class CreateJavaFile
{
    String MakeJavaFile(String name) {
        StringBuilder fileContent = new StringBuilder(""
            + "import javax.swing.*;\n"
            + "import java.awt.*;\n\n"
            + "public class " + name + " extend JFrame" + "{\n"
            + "    public static void main(String[] args) \n"
            + "    {\n"
            + "        Container contentPane = getContentPane();\n"
            + "        contentPane.setLayout(null);\n"
            + "        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);\n"
            + "        setSize(800, 700);\n");


        for(int i=0; i< ElementArray.num; ++i) {
            fileContent.append("        ").append(ElementArray.getElement(i).getVar())
                    .append(" ").append(ElementArray.getElement(i).getVar())
                    .append(" = new ").append(ElementArray.getElement(i).getVar())
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
        fileContent.append("        contentPane.setVisible(true);\n");
        fileContent.append("    }\n");
        fileContent.append("}\n");
        return fileContent.toString();
    }
}
