/*
class JavaFileMaker
{
    String make(String name) {
        StringBuilder ret = new StringBuilder(""
            + "import javax.swing.JFrame;\n"
            + "import javax.swing.WindowConstants;\n"
            + "\n"
            + "public class " + name + " {\n"
            + "    public static void main(String[] args) {\n"
            + "        JFrame frame = new JFrame();\n"
            + "        frame.setLayout(null);\n"
            + "        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);\n"
            + "        frame.setSize(1000, 1000);\n");

        Component[] components =
                //MainFrame.editorPane.getComponents();
        for(Component i : components) {
            ret.append("        ").append(i.getClass().getName()).append(" ").append(i.getName())
                .append(" = new ").append(i.getClass().getName()).append("();\n");
            String rectangle = i.getX() + ", " + i.getY() + ", " + i.getWidth() + ", " + i.getHeight();
            ret.append("        ").append(i.getName()).append(".setBounds(").append(rectangle)
                .append(");\n");
            String type = i.getClass().getName();
            switch (type) {
                case "javax.swing.JButton":
                    ret.append("        ").append(i.getName()).append(".setText(\"")
                        .append(((JButton) i).getText()).append("\");\n");
                break;
                case "javax.swing.JLabel":
                    ret.append("        ").append(i.getName()).append(".setText(\"")
                        .append(((JLabel) i).getText()).append("\");\n");
                    break;
                case "javax.swing.JTextField":
                    ret.append("        ").append(i.getName()).append(".setText(\"")
                        .append(((JTextField) i).getText()).append("\");\n");
                    break;
                default :
                    break;
            }
            ret.append("        frame.add(").append(i.getName()).append(");\n");
        }
        ret.append("        frame.setVisible(true);\n");
        ret.append("    }\n");
        ret.append("}\n");
        return ret.toString();
    }
}
*/
