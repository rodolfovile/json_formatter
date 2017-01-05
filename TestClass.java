package kore.burp.plugin;
import java.util.Scanner;


public class Main_Teste {

			public static void main(String[] args) {
				
				System.out.println("Type your json: ");
				Scanner scanner = new Scanner(System.in);
				String s = scanner.nextLine();

				System.out.println(Formatter_tool.format(s));			
			}
			
			
			
		
	}
