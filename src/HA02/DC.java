package HA02;

import java.util.BitSet;
import java.util.Random;

public class DC {
	
	public BitSet mySecret, leftNeighborSecret;
	
	public BitSet create16BitSecret(){
		BitSet bits = new BitSet();
		Random rand = new Random();
		for(int i = 0; i<16; i++){
			if(rand.nextInt(2) == 1){
				bits.set(i, true);
			} else{
				bits.set(i, false);
			}
		}
		System.out.print("Created: ");
		for(int i = 0; i<16; i++){
			System.out.print(bits.get(i) + ", ");
		}
		mySecret = bits;
		return bits;
	}
	
	public void receiveSecretFromLeft(BitSet leftNeighborSecret){
		this.leftNeighborSecret = leftNeighborSecret;
	}
	
	public BitSet answerTrue(){ //XOR
		BitSet xorSet = mySecret;
		xorSet.xor(leftNeighborSecret);
		return xorSet;
	}
	public BitSet answerFalse(){ //Opposite of XOR
		BitSet xorSet = answerTrue();
		for(int i = 0; i<xorSet.length(); i++){
			xorSet.flip(i);
		}
		return xorSet;
	}
	
	
	
	public static void main(String[] args){
		DC dc = new DC();
		dc.create16BitSecret();
		
		DC you = new DC();
		DC alice = new DC();
		DC bob = new DC();
		
		//STAGE ONE -- Each creates a secret and shares with the neighbor to the right
		BitSet yourSecret = you.create16BitSecret();
		alice.receiveSecretFromLeft(yourSecret);
		
		BitSet aliceSecret = alice.create16BitSecret();
		bob.receiveSecretFromLeft(aliceSecret);
		
		BitSet bobSecret = bob.create16BitSecret();
		you.receiveSecretFromLeft(bobSecret);
		
		//STAGE TWO --
		
		
	}
}
