package MenuController;

import Editor.AttributeElement;
import GUI.ElementArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by HyeRyeongSong on 2017. 6. 6..
 */
class CreateJSonFile
{
    CreateJSonFile() {}
    String MakeJSonFile()
    {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (int i=0; i<ElementArray.getSize(); ++i)
        {
            JSONObject element = new JSONObject();
            AttributeElement attributeElement = ElementArray.getElement(i);
            element.put("xValue", attributeElement.getX());
            element.put("yValue", attributeElement.getY());
            element.put("width", attributeElement.getW());
            element.put("height", attributeElement.getH());
            element.put("text", attributeElement.getText());
            element.put("type", attributeElement.getType());
            element.put("var", attributeElement.getVar());

            jsonArray.add(element);
        }

        jsonObject.put("Elements", jsonArray);
        jsonObject.put("num", ElementArray.num);
        return (jsonObject.toJSONString());
    }

    void parseJSonFile(String address) throws ParseException, IOException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(address));
        JSONArray jsonArray = (JSONArray) jsonObject.get("Elements");
        Long lNum = (Long) jsonObject.get("num");
        ElementArray.num = lNum.intValue();


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

        ElementArray.num -= ElementArray.getSize();
    }
}