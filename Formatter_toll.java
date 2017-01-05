package kore.burp.plugin;


public class Formatter_tool {

	
	private static final String empty="    ";

	public static String format(String json){
		try {
			int empty=0;
			char[]chs=json.toCharArray();
			StringBuilder stringBuilder=new StringBuilder();
			for (int i = 0; i < chs.length;) {
				if (chs[i]=='\"') {
					
					stringBuilder.append(chs[i]);
					i++;
					for ( ; i < chs.length;) {
						if ( chs[i]=='\"'&&isDoubleSerialBackslash(chs,i-1)) {
							stringBuilder.append(chs[i]);
							i++;
							break;
						} else{
							stringBuilder.append(chs[i]);
							i++;
						}
						
					}
				}else if (chs[i]==',') {
					stringBuilder.append(',').append('\n').append(getEmpty(empty));
					
					i++;
				}else if (chs[i]=='{'||chs[i]=='[') {
					empty++;
					stringBuilder.append(chs[i]).append('\n').append(getEmpty(empty));
					
					i++;
				}else if (chs[i]=='}'||chs[i]==']') {
					empty--;
					stringBuilder.append('\n').append(getEmpty(empty)).append(chs[i]);
					
					i++;
				}else {
					stringBuilder.append(chs[i]);
					i++;
				}
				
				
			}
			

			
			return stringBuilder.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return json;
		}
		
	}
	private static boolean isDoubleSerialBackslash(char[] chs, int i) {
		int count=0;
		for (int j = i; j >-1; j--) {
			if (chs[j]=='\\') {
				count++;
			}else{
				return count%2==0;
			}
		}
		
		return count%2==0;
	}

	private static String getEmpty(int count){
		StringBuilder stringBuilder=new StringBuilder();
		for (int i = 0; i < count; i++) {
			stringBuilder.append(empty) ;
		}
		
		return stringBuilder.toString();
	}



}
  
