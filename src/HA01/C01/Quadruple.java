package HA01.C01;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quadruple {
	
	private int a,c,d,r;
	private int index;
	
	public Quadruple(int index){
		this.index = index;
		init();
	}
	
	public String toString(){
		ArrayList<Integer> nbrs = new ArrayList();
		nbrs.add(a);
		nbrs.add(c);
		nbrs.add(d);
		nbrs.add(r);
		return "Quadruple"+index+": "+nbrs;
	}
	
	private void init(){
		a = generateNbr();
		c = generateNbr();
		d = generateNbr();
		r = generateNbr();
	}
	
	public int generateNbr(){
		Random rand = new Random();
		int nbr = 0;
		do{
			nbr = rand.nextInt();
		}while(nbr <= 0);
		return nbr; //maybe restrict int size in the future
	}
	
	public double hFunc(double input1, double input2){
		String text = ""+input1+input2;
		byte[] hash;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			hash = md.digest(text.getBytes(StandardCharsets.UTF_8));
			System.out.println("hFunc Hash value: "+new String(hash, StandardCharsets.UTF_8));
			return input1+input2;//return Integer.parseInt( new String(hash) );
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public double fFunc(double input1, double input2){
		return input1*input2; //fix later
	}
	
	public double generateB(int id, int n){
		double x = hFunc(a, c);
		//System.out.println("x: "+x);
		double y = hFunc(a ^ id, d);
		//System.out.println("y: "+y);
		double B = ( Math.pow(r, 3)*fFunc(x, y) ) % n;
		//System.out.println("Math.pow(r,3) = "+Math.pow(r,3));
		//System.out.println("fFunc(x,y) = "+fFunc(x,y));
		//System.out.println("Everything except %n = "+( Math.pow(r, 3)*fFunc(x, y) ));
		//System.out.println("Generated B: "+B);
		return B;
	}

}
