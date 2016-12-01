package HA03;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Paillier {

	public int n, g, lambda, my, p, q;
	public ArrayList<Integer> publicKey, privateKey;
	Random rand;

	public Paillier() {
		rand = new Random();
	}

	public int pickPrimeNbr() {
		return 13;
	}
	
	public static BigInteger L(BigInteger x, BigInteger n){
		return (x.subtract(BigInteger.valueOf(1))).divide(n);
	}

//	public void keyGeneration(){
//		do{
//			p = pickPrimeNbr();
//			q = pickPrimeNbr();
//			//TODO: Add some sort of check about gdc??
//		}while(gcd(p*q, (p-1)*(q-1)) != 1);
//		n = p*q;
//		my = lcm(p-1, q-1);
//		do{
//			g = rand.nextInt((int) Math.pow(n, 2)); //WHAT ABOUT THE BOUNDS??
//		}while(my == ( Math.pow(L(Math.pow(g, lambda)%Math.pow(n, 2)),-1)%n )); //Ensuring n divides the order of g
//	}

	// GCD method source @
	// http://stackoverflow.com/questions/4009198/java-get-greatest-common-divisor/4009247#4009247
	//public long gcd(long a, long b) {
//		if (b == 0) {
//			return a;
//		}
//		return gcd(b, a % b);
//		while(b>0){
//			long tmp = b;
//			b = a % y;
//			a = tmp;
//		}
//	}

	//LCM source: http://stackoverflow.com/questions/4201860/how-to-find-gcd-lcm-on-a-set-of-numbers
//	public long lcm(long a, long b) {
//		return a * (b / gcd(a, b));
//	}

	public double encrypt(int m) {
		int r = rand.nextInt(n); // WHAT ARE THE BOUNDS??
		double c = (Math.pow(g, m) * Math.pow(r, n)) % (Math.pow(n, 2));
		System.out.println("Encrypting as c = " + c);
		return c;
	}

	public static BigInteger decrypt(BigInteger c, BigInteger n, BigInteger my, BigInteger lambda) {
		BigInteger m = L( c.modPow(lambda, (n.multiply(n))), n).multiply(my.mod(n));
		System.out.println("Decrypting as m = " + m);
		return m;
	}
	
	

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		//int n = 0; //?????
		
		System.out.print("Prime 1: ");
		int p = scan.nextInt();
		System.out.print("Prime 1: ");
		int q = scan.nextInt();
		BigInteger n = BigInteger.valueOf(p*q);
		BigInteger g = new BigInteger("652534095028");
//		do{
//			System.out.print("G: ");
//			scan.nextInt();
//		}while(g >= Math.pow(n,2));
		ArrayList<BigInteger> votes = new ArrayList();
		try {
			List<String> file = Files.readAllLines(Paths.get("votes.txt"));
			for(String line : file){
				votes.add(new BigInteger(line));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//ALLA LINES % n^2 = sum of votes
		BigInteger voteSum = BigInteger.valueOf(1);
		for(BigInteger vote : votes){
			voteSum = voteSum.multiply(vote);
		}
		//
		Paillier pail = new Paillier();
		BigInteger p1 = BigInteger.valueOf(p-1);
		BigInteger q1 = BigInteger.valueOf(q-1);
		BigInteger lambda = p1.multiply(q1).divide(p1.gcd(q1));
		//BigInteger my = ( BigInteger.valueOf( (pail.L( (long) (Math.pow(g.doubleValue(), lambda.doubleValue()) % n.multiply(n)), n) % n) ).modInverse(n) ); //TODO: mod inverse on L()
		BigInteger my = g.modPow(lambda, n.multiply(n));
		my = L(my, n);
		my = my.modInverse(n);
		
		//ANSWER = m eller m-n <--om m är större än antalet lines
		BigInteger m = decrypt(voteSum, n, my, lambda);
		m = m.mod(n);
		System.out.println("M after modulo: " + m);
		System.out.println("My:"+my);
		System.out.println("Lambda: "+lambda);
		System.out.println("N: "+n);
		if(m.longValue() > votes.size()){
			System.out.println("ANSWER: "+(m.subtract(n)));
		}
		else{
			System.out.println("ANSWER: "+m);
		}
		//TODO: DO THE VOTE COUNTING HERE
		
	}

}
