package MenuController;

import Editor.AttributeElement;
import GUI.ElementArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

class CreateJSonFile
{
    CreateJSonFile() {}
    String MakeJSonFile()
    {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (int i=0; i<ElementArray.num; ++i)
        {
            JSONObject element = new JSONObject();
            AttributeElement attributeElement = ElementArray.getElement(i);
            element.put("xValue", attributeElement.getX());
            element.put("yValue", attributeElement.getY());
            element.put("width", attributeElement.getW());
            element.put("height", attributeElement.getH());
            element.put("type", attributeElement.getType());
            element.put("var", attributeElement.getVar());

            String type = attributeElement.getClass().getName();
            switch (type) {
                case "javax.swing.JButton":
                    element.put("text", attributeElement.getText());
                    break;
                case "javax.swing.JLabel":
                    element.put("text", attributeElement.getText());
                    break;
                default:
                    element.put("text", attributeElement.getText());
                    break;
            }
            jsonArray.add(element);
        }

        jsonObject.put("Elements", jsonArray);
        return (jsonObject.toJSONString());
    }

    void parseJSonFile(String target) throws ParseException, IOException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(target));
        JSONArray jsonArray = (JSONArray) jsonObject.get("Elements");

        for (Object element : jsonArray) {
            JSONObject object = (JSONObject) element;
            Long x = (Long) object.get("xValue");
            Long y = (Long) object.get("yValue");
            Long w = (Long) object.get("width");
            Long h = (Long) object.get("height");
            ElementArray.addElement(x.intValue(), y.intValue(), w.intValue(), h.intValue(),
                    (String)object.get("text"), (String)object.get("type"),
                    (String)object.get("var"));
        }
    }
}