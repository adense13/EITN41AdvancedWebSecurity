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
	
		Random rand = new Random();
		
		for(int i = 0; i< listSize/2; i++){
			indices.remove(rand.nextInt(indices.size()));
		}
		
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
	
	public boolean receiveQuadsAndCompare(List<Quadruple> quads){
		Consumer test = new Consumer (c.getId()); //we create this to verify user id from real user is valid
		int i = 0;
		for(Quadruple q : quads){
			if(bValues.get(indices.get(i)) != q.generateB(test.getId(), Globals.GLOBAL_N)){
				return false;
			}
			i++;
		}
		return true;
	}

}
