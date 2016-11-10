package HA01;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class B01 {
	
	static class Luhn{
		
		public Luhn(){
			
		}
		
		public static List<Integer> getDigitsArray(String s){
			return s.chars().mapToObj(i -> (char) i)
                   .map(Character::getNumericValue)
                    .collect(Collectors.toList());
		}
		
		public static List<Integer> getDigitsArray(int nbr){
			return getDigitsArray(Integer.toString(nbr));
		}
		
		public static int sumDigits(List<Integer> list){
			int temp = 0;
			for(int i = 0; i < list.size(); i++){
				temp = temp + list.get(i);
			}
			return temp;
		}
		
		public static void run(String[] s){
			for(int i = 0; i<s.length; i++){
				run(s[i]);
			}
		}
		
		
		public static String run(String str){
			List<Integer> l = getDigitsArray(str);
			//System.out.println(l);
			List<Integer> odd = new ArrayList<Integer>();
			int sum = 0;
			boolean isTimeToDouble = false;
			boolean xWasDoubled = false;
			for(int i = l.size()-1; i >= 0; i--){
				int tempRes = 0;
				//System.out.println("Found '"+l.get(i)+"' at index '"+i+"', and double bool is: "+isTimeToDouble);
				if(l.get(i) == 33){
					l.set(i, 0); //unneeded?
					if(isTimeToDouble){
						xWasDoubled = true;
					}
				}
				else if (isTimeToDouble){
					int temp = l.get(i)*2;
					if(temp > 9){
						List<Integer> subl = getDigitsArray(temp);
						temp = 0;
						for(int j = 0; j<subl.size(); j++){
							sum = sum+subl.get(j);
							temp = temp+subl.get(j);
						}
					}
					else{
						sum = sum + temp;
					}
					tempRes = temp;
				}
				else{
					sum = sum + l.get(i);
					tempRes = l.get(i);
				}
				isTimeToDouble = !isTimeToDouble;
				//System.out.println("Turned into '"+tempRes+"'");
				//System.out.println("----------------------------");
			}
			int x = 0;
			for(int i = 0; i < 10; i++){
				x = i;
				if(xWasDoubled){
					i=i*2;
					if(i>9){
						i = i - 9;
					}
				}
				if( (sum+i)%10 == 0 ){
					break;
				}
			}
			//System.out.println("Sum without X: "+sum);
			System.out.println(x);
			return Integer.toString(x);
			//System.out.println("------------------");
			
		}
	}
	
	public static void main(String args[]){
		
		//System.out.println(Luhn.getDigitsArray("12774212857X4109"));
		//Luhn.run("12774212857X4109");
		Scanner scan = new Scanner(System.in);
		String output = "";
		try {
			for (String line : Files.readAllLines(Paths.get("list.txt"))) {
			    output = output + Luhn.run(line);
			}
			System.out.println("Output: "+output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//while(true){
		//	String s = scan.nextLine();
		//	output = output + Luhn.run(s);
		//	System.out.println("Output: "+output);
		//}
		
	}

}
