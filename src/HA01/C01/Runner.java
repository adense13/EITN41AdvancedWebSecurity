package HA01.C01;


public class Runner {
	
	public static void main(String[] args)
	{
		Bank bank = new Bank();
		Consumer alice = new Consumer(231231);
		bank.receiveB(alice.sendB());
		
	}
}
