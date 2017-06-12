package Controller;

import Model.AttributeElement;
import Model.ElementArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by HyeRyeongSong on 2017. 6. 6..
 */
class JSonFileController
{
    JSonFileController() {}

    /**
     * JSON 형태로 저장을 위해 속성 정보가 모두 저장된 JSON 문자열 출력 메소드
     * @return 속성정보가 모두 포함된 JSON 문자열
     */
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

    /**
     * 저장된 JSON파일의 속성 정보를 불러와 ElementArray에 저장하기 위한 메소드
     * @param address 파일 주소
     * @throws ParseException
     * @throws IOException
     */
    void saveJSonFile(String address) throws ParseException, IOException {
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