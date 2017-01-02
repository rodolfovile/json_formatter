package json_teste_plugin;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class GSonWay {

    public static void main(String[] args) throws Exception {
        String jsonString = "  {" +
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
        List keys1 = getKeysFromJson(jsonString);
        System.out.println(keys1.size());
        System.out.println();
        System.out.println(keys1);
       

    }

    static List getKeysFromJson(String jsoString) throws Exception {
        Object things = new Gson().fromJson(jsoString, Object.class);
        List keys = new ArrayList();
        collectAllTheKeys(keys, things);
        return keys;
    }

    static void collectAllTheKeys(List keys, Object o) {
        Collection values = null;
        if (o instanceof Map) {
            Map map = (Map) o;
            keys.addAll(map.keySet()); // coleta todas as keys em sequencia.
            values = map.values();
            String todasChaves = null;
            Set chaves = map.keySet();
            chaves.retainAll(chaves);
            for (Object chave : chaves) {
            	todasChaves +=  chave + "\n";
            }
            System.out.println(todasChaves);
        } else if (o instanceof Collection) {
            values = (Collection) o;
            
        } else{return;}

        for (Object value : values) {
        	if(!value.equals(values)) {
        		collectAllTheKeys(keys, value);
        	}
        }
    }
}