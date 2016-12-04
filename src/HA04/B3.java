package HA04;

import java.math.BigInteger;
import java.util.Scanner;

public class B3 {
	
	BigInteger mgfSeed, maskLen;
	
	public B3(BigInteger mgfSeed, BigInteger maskLen){
		this.maskLen = maskLen;
		this.mgfSeed = mgfSeed;
	}
	
	public String MGF1(BigInteger mgfSeed, BigInteger maskLen, BigInteger hLen){
		if( maskLen.longValue() > hLen.longValue() * ( (long) Math.pow(2, 32) ) ){
			return "Mask Too Long";
		}
		String T = "";
		for(int i = 0; i < (maskLen.divide(hLen).longValue()); i++){
			Byte C = null;
		}
			return "";
	}
	
	public void I2OSP(BigInteger x, BigInteger xLen){
		if(x.longValue() >= Math.pow(256, xLen.longValue())){
			return; //integer too large
		}
		
	}
	
	public void run(){
		
		//THE END
		System.out.println("ANSWER (maskLen bytes string): "+mgfSeed+maskLen);
	}

	//https://tools.ietf.org/html/rfc8017#appendix-B.2.1
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("mgfSeed: ");
		BigInteger mgfSeed = BigInteger.valueOf( Long.decode( "0x" + scan.nextLine() ) );
		
		System.out.println("maskLen: ");
		BigInteger maskLen = BigInteger.valueOf( Long.decode( "0x" + scan.nextLine() ) );
		
		B3 b3 = new B3(mgfSeed, maskLen);
		b3.run();

	}

}
