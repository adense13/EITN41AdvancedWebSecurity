package HA03;

import java.util.ArrayList;
import java.util.Scanner;

public class ThresholdScheme {
	
	double k,n, myIndex;
	double[] myPoints, shares, collabIndexes, collabShares;

	public ThresholdScheme(double k, double n, double[] myPoints, double[] shares, double[] collaboratorIndexes, double[] collaboratorShares){
		this.k = k;
		this.n = n;
		this.myIndex = 1;
		this.myPoints = myPoints;
		this.shares = shares;
		this.collabIndexes = collaboratorIndexes;
		this.collabShares = collaboratorShares;
		run();
	}
	
	public double getMyMasterPoint(){
		double temp = 0;
		for(int i = 0; i < shares.length; i++){
			temp += shares[i];
		}
		temp += myPoints[0];
		return temp;
	}
	
	public double[] getAllMasterPoints(double myMasterPoint){
		double[] da = new double[1+collabShares.length];
		for(int i = 0; i < da.length; i++){
			if(i == 0){
				da[i] = myMasterPoint;
			}
			else{
				da[i] = collabShares[i-1];
			}
		}
		return da;
	}
	
	public double[] getAllIndexes(){
		double[] da = new double[1+collabIndexes.length];
		for(int i = 0; i < da.length; i++){
			if(i == 0){
				da[i] = myIndex;
			}
			else{
				da[i] = collabIndexes[i-1];
			}
		}
		return da;
	}
	
	public void printArray(double[] arr, String name){
		System.out.print(name+": ");
		System.out.print("[");
		for(int i = 0; i<arr.length; i++){
			System.out.print(arr[i]);
			if(i != (arr.length-1)){
				System.out.print(", ");
			}
		}
		System.out.print("]");
		System.out.println();
	}
	
	public void run(){
		double code = 0;
		double[] allMasterPoints = getAllMasterPoints(getMyMasterPoint());
		double[] indexes = getAllIndexes();
		
		printArray(allMasterPoints, "allMasterPoints");
		printArray(indexes, "indexes");
		
		for(int j = 0; j < allMasterPoints.length; j++){
			double interpolation = 1;
			for(int m = 0; m < indexes.length; m++){
				if (j != m){
					interpolation = interpolation * ((indexes[m])/(indexes[m]-indexes[j]));
				}
			}
			code = code + ( interpolation*allMasterPoints[j] );
		}
		System.out.println("Answer: "+code);
	}
	
	public static void main(String[] args){
		double k = 5;
		double n = 8;
		//---------------------------------------
		//Manually do the inputs here:
		double[] myF1Points = {3+15+17+16+11,231,1133,4911,9467,23074,34681,46170};
		double[] shares = {32, 51, 51, 35, 54, 43, 47};
		//---------------------------------------
		double[] collaboratorIndexes = {2, 3, 4, 8};
		double[] collaboratorShares = {2622, 10731, 30900,435936};
		
		ThresholdScheme scheme = new ThresholdScheme(k,n,myF1Points,shares,collaboratorIndexes,collaboratorShares);
	}
}
