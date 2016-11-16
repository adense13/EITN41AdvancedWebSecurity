package HA01.C01;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

public class Quadruple {
	
	private int a,c,d,r;
	private int index;
	
	public Quadruple(int index){
		this.index = index;
		init();
	}
	
	private void init(){
		a = generateNbr();
		c = generateNbr();
		d = generateNbr();
		r = generateNbr();
	}
	
	public int generateNbr(){
		Random rand = new Random();
		return rand.nextInt(); //maybe restrict int size in the future
	}
	
	public int hFunc(int input1, int input2){
		String text = ""+input1+input2;
		byte[] hash;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			hash = md.digest(text.getBytes(StandardCharsets.UTF_8));
			return Integer.parseInt( new String(hash) );
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int fFunc(int input1, int input2){
		return input1*input2; //fix later
	}
	
	public double generateB(int id, int n){
		int x = hFunc(a, c);
		int y = hFunc(a ^ id, d);
		double B = ( Math.pow(r, 3)*fFunc(x, y) ) % n;
		return B;
	}

}
