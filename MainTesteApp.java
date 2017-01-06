import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.json.JSONException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainTesteApp {

        public static List<String> value = new ArrayList<String>();

        static Set getAllKeys (String jsonDataString){

                ObjectMapper mapper = new ObjectMapper();

                try {
                        HashMap<String, Object> jsonMapKeys = mapper.readValue(jsonDataString, HashMap.class);

                        Set allKeys = new HashSet<>();
                        getLevelKeys(jsonMapKeys);
                        allKeys.addAll(value);
                        value.clear();
                        value.addAll(allKeys);
                        System.out.println("function getAllKeys: "+ allKeys);

                        return allKeys;

                } catch (JsonGenerationException e) {
                        e.printStackTrace();
                } catch (JsonMappingException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return null;
        }

        static Object getLevelKeys (HashMap map) throws JsonParseException, JsonMappingException, IOException {
                Iterator it = map.entrySet().iterator();
            List<String> keys = new ArrayList<>();

                while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
            
                        value.add(pair.getKey().toString());
                
                keys.add(pair.getKey().toString());
            }

                for (int i = 0; i < keys.size(); i++) {
                        if (map.get(keys.get(i)).getClass().toString().equals("class java.util.LinkedHashMap")) {
                                getLevelKeys((HashMap<String, Object>) map.get(keys.get(i)));
                }
                        if (map.get(keys.get(i)).getClass().toString().equals("class java.util.ArrayList")) {
                                List<Object> array = (ArrayList<Object>) map.get(keys.get(i));

                                for(Object value: array) {
                                        if (!(value instanceof String)) {
                                                getLevelKeys((HashMap<String, Object>) value);
                                        }
                                }
                        }

                }
                return null;
        }

        static Object getLevel(HashMap map, Set chosenKeys) throws JsonParseException, JsonMappingException, IOException {
                Iterator it = map.entrySet().iterator();
            List<String> keys = new ArrayList<>();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                for (Object key : chosenKeys){
                        //System.out.println("keyAtual:"+pair.getKey() + " == "+ key.toString()+"(escolhida)");
                        if (pair.getKey().toString().toUpperCase().equals(key.toString().toUpperCase())) {
                                value.add(pair.getKey()+":"+pair.getValue().toString());
                                //return pair.getValue();
                        
                        }
                }
                
                
                keys.add(pair.getKey().toString());
            }

                for (int i = 0; i < keys.size(); i++) {
                        if (map.get(keys.get(i)).getClass().toString().equals("class java.util.LinkedHashMap")) {
                                getLevel((HashMap<String, Object>) map.get(keys.get(i)), chosenKeys);
                }
                        if (map.get(keys.get(i)).getClass().toString().equals("class java.util.ArrayList")) {
                                List<Object> array = (ArrayList<Object>) map.get(keys.get(i));

                                for(Object value: array) {
                                        if (!(value instanceof String)) {
                                                getLevel((HashMap<String, Object>) value, chosenKeys);
                                        }
                                }
                        }

                }
                return null;
        }

        static String getValues (Set chosenKeys, String jsonData) throws JSONException {

                value = new ArrayList<String>();

                ObjectMapper mapper = new ObjectMapper();

                try {
                        HashMap<String, Object> jsonMap = mapper.readValue(jsonData, HashMap.class);

                        getLevel(jsonMap, chosenKeys);

                        for(String val: value) {
                                //Result var static
                                System.out.println(val); 
                        }


                } catch (JsonGenerationException e) {
                        e.printStackTrace();
                } catch (JsonMappingException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }

                return null;
        }

        public static void main(String[] args) throws Exception {
                String json1 = "{\n    \"widget\": {\n        \"debug\": \"on\",\n        \"window\": {\n            \"title\": \"Sample Konfabulator Widget\",\n            \"name\": \"main_window\",\n            \"width\": 500,\n            \"height\": 500\n        },\n        \"image\": {\n            \"src\": \"Images/Sun.png\",\n            \"name\": \"sun1\",\n            \"hOffset\": 250,\n            \"vOffset\": 250,\n            \"alignment\": \"center\"\n        },\n        \"text\": {\n            \"data\": \"Click Here\",\n            \"size\": 36,\n            \"style\": \"bold\",\n            \"name\": \"text1\",\n            \"hOffset\": 250,\n            \"vOffset\": 100,\n            \"alignment\": \"center\",\n            \"onMouseUp\": \"sun1.opacity = (sun1.opacity / 100) * 90;\"\n        }\n    }\n}";
                String json2 = 
                                "  {" +
                                "  \"data\": "+
                                "  [{" +
                    "   \"name\": \"Jack\", " +
                    "   \"age\" : 13, " +
                    "   \"isMarried\" : false, " +
                    "   \"address\": { " +
                    "     \"street\": \"#1234, Main Street\", " +
                    "     \"zipCode\": \"123456\" " +
                    "   }, " +
                    "   \"phoneNumbers\": [\"011-111-1111\", \"11-111-1111\"] " +
                    " }," +
                    "{" +
                    "   \"name\": \"maria\", " +
                    "   \"age\" : 13, " +
                    "   \"isMarried\" : false, " +
                    "   \"address\": { " +
                    "     \"street\": \"#1234, Main Street\", " +
                    "     \"zipCode\": \"123456\" " +
                    "   }, " +
                    "   \"phoneNumbers\": [\"011-111-1111\", \"11-111-1111\"] " +
                    " }], "+
                    " \"count\": 2"+
                    " }"
                    ;
                System.out.println("JsonData: ");
                Scanner scanner = new Scanner(System.in);
                String json3 = scanner.nextLine();

                //Set escolhidas = getAllKeys(json3);
                Set<String> escolhidas = new HashSet<String>();
                escolhidas.add("username");
                escolhidas.add("facebookID");
                System.out.println(escolhidas.toString());


                getValues(escolhidas, json3);
                //getValues("street", json2);

                //System.out.println(value);
        }

}
