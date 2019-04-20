package hr.fer.zemris.apr.dz4.chromosome;

import static hr.fer.zemris.apr.dz4.utils.Util.decode;

import java.util.Arrays;

public class UnitBinary extends Unit {

	private int[] b;
	
	public UnitBinary(int... b) {
		this.b = b;
	}
	
	public int length() {
		return b.length;
	}
	
	public int getBit(int index) {
		return b[index];
	}
	
	public void setBit(int value, int index) {
		b[index] = value;
	}
	
	public int[] bits() {
		return b;
	}
	
	@Override
	public double[] getArray() {
		return decode(b);
	}
	
	public boolean equals(Unit unit) {
		if(!(unit instanceof UnitBinary)) return false;
		UnitBinary other = (UnitBinary) unit;
		if(this.length() != other.length()) return false;
		if(!Arrays.equals(this.b, other.b)) return false;
		return true;
	}


}
