package HA01;

import java.util.ArrayList;
import java.util.List;
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
		
		public static void run(String str){
			List<Integer> l = getDigitsArray(str);
			List<Integer> odd = new ArrayList<Integer>();
			int sum = 0;
			boolean alt = true;
			for(int i = l.size()-1; i >= 0; i--){
				if(l.get(i) == 33){
					l.set(i, 0);
				}
				else if (alt){
					int temp = l.get(i)*2;
					if(temp > 9){
						List<Integer> subl = getDigitsArray(temp);
						for(int j = 0; j<subl.size(); j++){
							sum = sum+subl.get(j);
						}
					}
					else{
						sum = sum + temp;
					}
				}
				else{
					sum = sum + l.get(i);
				}
				alt = !alt;
			}
			int x = 0;
			for(int i = 0; i < 10; i++){
				if( (sum+i)%10 == 0 ){
					x = i;
					break;
				}
			}
			if(x%2 == 0){ //even
				x = x/2;
			}
			else{
				x = 10+(x-1)/2;
			}
			System.out.println("Sum: "+sum);
			System.out.println("Real x: "+x);
			System.out.println("------------------");
			
		}
	}
	
	public static void main(String args[]){
		
		//System.out.println(Luhn.getDigitsArray("12774212857X4109"));
		Luhn.run("12774212857X4109");
		Luhn.run("586604X108627571");
		Luhn.run("7473X86953606632");
		Luhn.run("4026467X45830632");
		Luhn.run("20X3092648604969");
		
	}

}
