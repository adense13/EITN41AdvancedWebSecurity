package HA01.C01;

import java.util.List;


public class Runner {
	
	public static void main(String[] args)
	{
		Bank bank = new Bank();
		Consumer alice = new Consumer(231231);
		bank.setCurrentConsumer(alice);
		
		List<Double> allAliceBVals = alice.sendB();
		System.out.println("All Alice BValues: "+allAliceBVals);
		
		bank.receiveB(allAliceBVals);
		
		List<Quadruple> quadsToSendBack = alice.receiveHalfIndicesAndSendBackQs(bank.sendBackBIndices());
		
		boolean consumerQuadsAreOk = bank.receiveQuadsAndCompare(quadsToSendBack);
		
		if(consumerQuadsAreOk){
			System.out.println("Bank B vals OK, so ID must also be ok");
			double bankBlindSign = bank.computeBlindSignature();
			System.out.println("BankLindSignature: " + bankBlindSign);
		}
	}
}
