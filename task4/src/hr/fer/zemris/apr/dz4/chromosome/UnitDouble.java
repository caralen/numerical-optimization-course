package hr.fer.zemris.apr.dz4.chromosome;

import java.util.Arrays;

public class UnitDouble extends Unit {
	
	private double[] x;
	
	public UnitDouble(double... x) {
		this.x = x;
	}
	
	public int length() {
		return x.length;
	}
	
	public double getX(int index) {
		if(index >= x.length) throw new IndexOutOfBoundsException("Index is " + index + " and length is " + x.length);
		if(index < 0) throw new IndexOutOfBoundsException("Index is must be greater than 0");
		return x[index];
	}
	
	public void setX(double value, int index) {
		if(index >= x.length) throw new IndexOutOfBoundsException("Index is " + index + " and length is " + x.length);
		if(index < 0) throw new IndexOutOfBoundsException("Index is must be greater than 0");
		x[index] = value;
	}
	
	@Override
	public double[] getArray() {
		return x;
	}

	public boolean equals(Unit unit) {
		if(!(unit instanceof UnitDouble)) return false;
		UnitDouble other = (UnitDouble) unit;
		if(this.length() != other.length()) return false;
		if(!Arrays.equals(this.x, other.x)) return false;
		return true;
	}

}
