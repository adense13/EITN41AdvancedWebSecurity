package HA03;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm;

public class KN {

	
	public static void main(String[] args){
		int k = 0;
		int n = 0;
		int privPol;
		ArrayList<Integer> shares = new ArrayList();
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter k:");
		do{
			k = scan.nextInt();
		}while(k<3);
		System.out.println();
		
		System.out.print("Enter n:");
		do{
			n = scan.nextInt();
		}while(n <= k || n > 8);
		System.out.println();
		
		System.out.print("Private polynomial: ");
		privPol = scan.nextInt(); //TODO: REDO!
		System.out.println();
		
		System.out.println("Enter shares. -1 = done and move on");
		int scanVal = 0;
		while(true){
			scanVal = scan.nextInt();
			if(scanVal == -1){
				break;
			}
			else{
				shares.add(scanVal);
			}
		}
		
		double[] myPolVals = {20,20, 11, 6};
		PolynomialFunction myPol = new PolynomialFunction(myPolVals);
		
		double[] myPoints = {197, 1608, 4296, 11255, 3349};
		double[] shared = {34,48,45,39,24};
		
		double f2 = 1908;
		double f3 = 7677;
		double f4 = 0;
		double f5 = 50751;
		double f6 = 101700;
		double myMasterPoint = myPoints[0]+shared[0]+shared[1]+shared[2]+shared[3]+shared[4];
		//abovev is f1
		
		double[] allMasterPoints = {myMasterPoint, f2, f3, f5, f6};
		double[] interPoints = {1,2,3,5,6};
		PolynomialFunctionLagrangeForm masterPol = new PolynomialFunctionLagrangeForm(interPoints, allMasterPoints);
		double[] masterCoefficients = masterPol.getCoefficients();
		
		System.out.println("Master least degree coeff: "+masterCoefficients[0]);
	}
}
