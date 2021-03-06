package HA01.C01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bank {
	
	private List<Double> bValues;
	private List<Integer> indices;
	private Consumer c;
	
	public void setCurrentConsumer(Consumer c){
		this.c = c;
	}
	
	public void receiveB(List<Double> bList){
		bValues = bList;
		int listSize = bList.size();
		indices = createIndexArray(listSize);
		System.out.println("Bvals: "+bValues);
		System.out.println("Indices: "+indices);
	
		Random rand = new Random();
		
		for(int i = 0; i< listSize/2; i++){
			indices.remove(rand.nextInt(indices.size()));
		}
		
		//Let's print and check all our chosen B values
		List<Double> pickedBVals = new ArrayList();
		for(int i = 0; i<indices.size(); i++){
			pickedBVals.add(bValues.get(indices.get(i)));
		}
		System.out.println("pickedBVals: "+pickedBVals);
	}
	
	/*
	 * These are the indices belonging to the B values that the Bank requests the Quadruples for
	 */
	public List<Integer> sendBackBIndices(){
		return indices;
	}
	
	private ArrayList<Integer> createIndexArray(int size){
		ArrayList<Integer> indices = new ArrayList<Integer>();
		for (int i=0; i< size; i++){
			indices.add(i);
		}
		return indices;
	}
	
	/*
	 * Returns true if all B values are OK compared to consumer id
	 */
	public boolean receiveQuadsAndCompare(List<Quadruple> quads){
		Consumer test = new Consumer (c.getId()); //we create this to verify user id from real user is valid
		int i = 0;
		ArrayList<Double> bankGeneratedBVals = new ArrayList();
		for(Quadruple q : quads){
			bankGeneratedBVals.add(q.generateB(test.getId(), Globals.GLOBAL_N)); //debug
			if(bValues.get(indices.get(i)) != q.generateB(test.getId(), Globals.GLOBAL_N)){
				return false;
			}
			i++;
		}
		System.out.println("Bank Generated B vals: "+bankGeneratedBVals);
		return true;
	}
	
	public double computeBlindSignature(){
		double bMulSum = 1;
		for(int i = 0; i<bValues.size(); i++){
			if(!indices.contains(i)){ //only do this on B values that we did not use to check ID correctness on
				bMulSum = bMulSum*bValues.get(i) / 3; //DO NOT divide by 3 later. Simply do something else :PPP
			}
		}
		return bMulSum % Globals.GLOBAL_N;
	}

}
