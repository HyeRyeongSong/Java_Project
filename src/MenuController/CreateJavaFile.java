package MenuController;

import GUI.ElementArray;

class CreateJavaFile
{
    String MakeJavaFile(String name) {
        StringBuilder fileContent = new StringBuilder(""
            + "import javax.swing.*;\n\n"
            + "public class " + name + " {\n"
            + "    public static void main(String[] args) \n"
            + "    {\n"
            + "        JFrame frame = new JFrame();\n"
            + "        frame.setLayout(null);\n"
            + "        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);\n"
            + "        frame.setSize(800, 700);\n");


        for(int i=0; i< ElementArray.num; ++i) {
            fileContent.append("        ").append("AttributeElement     ")
                    .append(ElementArray.getElement(i).getVar())
                    .append(" = new AttributeElement();\n");
            String strRec = ElementArray.getElement(i).getX() + ", "
                    + ElementArray.getElement(i).getY() + ", "
                    + ElementArray.getElement(i).getW() + ", "
                    + ElementArray.getElement(i).getH();
            fileContent.append("        ").append(ElementArray.getElement(i).getVar())
                    .append(".setBounds(").append(strRec).append(");\n");

            fileContent.append("        ").append(ElementArray.getElement(i).getVar())
                    .append(".setText(\"").append(ElementArray.getElement(i).getText())
                    .append("\");\n");

            fileContent.append("        frame.add(").append(ElementArray.getElement(i)
                    .getVar()).append(");\n");

            ElementArray.setElementLocation(i, ElementArray.getElement(i).getX(),
                    ElementArray.getElement(i).getY());


            fileContent.append("         ElementArray.setElementLocation(i, ElementArray.getElement(i).getX()"
                                + "ElementArray.getElement(i).getY());\n");

            fileContent.append("         ElementArray.setElementLocation(i, ElementArray.getElement(i).getW()"
                    + "ElementArray.getElement(i).getH());\n");

        }
        fileContent.append("        frame.setVisible(true);\n");
        fileContent.append("    }\n");
        fileContent.append("}\n");
        return fileContent.toString();
    }
}
