package HA01.C01;

import java.util.ArrayList;
import java.util.List;

public class Consumer {
	
	List<Quadruple> quadList;
	private int id;
	
	public Consumer(int id){
		this.id = id;
		quadList = new ArrayList();
		generateQuadruples(Globals.GLOBAL_K);
	}
	
	public int getId(){
		return id;
	}
	
	public void generateQuadruples(int k){
		for(int i = 0; i < 2*k; i++){
			quadList.add(new Quadruple(i));
		}
	}
	
	public List<Double> sendB(){
		List<Double> listToSend = new ArrayList();
		
		for(Quadruple quad :  quadList){
			listToSend.add(quad.generateB(this.id, Globals.GLOBAL_N));
		}
		
		return listToSend;
	}
	
	/*
	 *	Pick quadruples corresponding to indices from bank and return them to bank 
	 */
	public List<Quadruple> receiveHalfIndicesAndSendBackQs(List<Integer> indices){
		List<Quadruple> quadsToSendBack = new ArrayList();
		for(int index : indices){
			quadsToSendBack.add(quadList.get(index));
		}
		return quadsToSendBack;
	}
	
	public static void main(String[] args){
		
	}

}
