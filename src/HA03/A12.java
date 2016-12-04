package HA03;

public class A12 {

	public static void main(String[] args){
		double code = 0;
		double[] allMasterPoints = {0,-2,4,10};
		double[] indexes = {1,2,3,4};
		
		for(int j = 0; j < allMasterPoints.length; j++){
			double interpolation = 1;
			for(int m = 0; m < indexes.length; m++){
				if (j != m){
					interpolation *= ((indexes[m])/(indexes[m]-indexes[j]));
				}
			}
			code = code + ( interpolation*allMasterPoints[j] );
		}
		System.out.println("Answer: "+code);
	}
}
