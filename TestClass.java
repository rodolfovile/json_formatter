package json_teste_plugin;


	import java.io.BufferedWriter;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.Iterator;
	import java.util.List;

	import org.json.JSONArray;
	import org.json.JSONException;
	import org.json.JSONObject;

	public class TestClass {
	    private static StringBuilder strOut = new StringBuilder();
		private static JSONObject inputJson;

	    public static void main(String[] args) {

	        try {
	            String json = "  {" +
	    				"  \"users\": "+
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
	    	            "   \"name\": \"Jack\", " +
	    	            "   \"age\" : 13, " +
	    	            "   \"isMarried\" : false, " +
	    	            "   \"address\": { " +
	    	            "     \"street\": \"#1234, Main Street\", " +
	    	            "     \"zipCode\": \"123456\" " +
	    	            "   }, " +
	    	            "   \"phoneNumbers\": [\"011-111-1111\", \"11-111-1111\"] " +
	    	            " }]"+
	    	            " }";
	            List<String> lst = new ArrayList<String>();
	            lst = findKeysOfJsonObject(inputJson, lst);

	            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\temp\\temp.txt"))) {
	                writer.write(strOut.toString());
	            }
	        } catch (JSONException | IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }

	    private static List<String> findKeysOfJsonArray(JSONArray jsonIn, List<String> keys) {
	        List<String> keysFromArr = new ArrayList<>();

	        if (jsonIn != null && jsonIn.length() != 0) {
	            for (int i = 0; i < jsonIn.length(); i++) {
	                JSONObject jsonObjIn = jsonIn.getJSONObject(i);
	                keysFromArr = findKeysOfJsonObject(jsonObjIn, keys);
	            }
	        }

	        return keysFromArr;
	    }

	    private static List<String> findKeysOfJsonObject(JSONObject jsonIn, List<String> keys) {

	        Iterator<String> itr = jsonIn.keys();
	        List<String> keysFromObj = makeList(itr);
	        keys.addAll(keysFromObj);

	        itr = jsonIn.keys();
	        while (itr.hasNext()) {
	            String itrStr = itr.next();
	            System.out.println("out " + itrStr);
	            JSONObject jsout = null;
	            JSONArray jsArr = null;
	            if (jsonIn.get(itrStr).getClass() == JSONObject.class) {
	                jsout = jsonIn.getJSONObject(itrStr);
	                findKeysOfJsonObject(jsout, keys);
	            } else if (jsonIn.get(itrStr).getClass() == JSONArray.class) {
	                jsArr = jsonIn.getJSONArray(itrStr);
	                keys.addAll(findKeysOfJsonArray(jsArr, keys));
	            } else if (jsonIn.get(itrStr).getClass() == String.class) {
	                System.out.println(itrStr + "-" + jsonIn.get(itrStr));
	                strOut.append(itrStr + "," + jsonIn.get(itrStr));
	                strOut.append(System.lineSeparator());
	            }
	        }
	        return keys;
	    }

	    public static List<String> makeList(Iterator<String> iter) {
	        List<String> list = new ArrayList<String>();
	        while (iter.hasNext()) {
	            list.add(iter.next());
	        }
	        return list;
	    }
	   
	}

