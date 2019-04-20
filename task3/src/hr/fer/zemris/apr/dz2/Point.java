package hr.fer.zemris.apr.dz2;

import java.util.Arrays;

public class Point {

	private double[] x;
	
	public Point(double... x) {
		this.x = x;
	}

	public double getValue(int index) {
		if(index < 0 || index > x.length) throw new IllegalArgumentException();
		return x[index];
	}
	
	public void setValue(int index, double value) {
		if(index < 0 || index > x.length) throw new IllegalArgumentException();
		x[index] = value;
	}
	
	public int dimension() {
		return x.length;
	}
	
	public void normalize() {
		double norm = norm();
		
		for(int i = 0; i < x.length; i++) {
			x[i] = x[i] / norm;
		}
	}
	
	public Point normalized() {
		double norm = norm();
		double[] e = new double[x.length];
		
		for(int i = 0; i < x.length; i++) {
			e[i] = x[i] / norm;
		}
		return new Point(e);
	}
	
	public double norm() {
		double sum = 0;
		
		for(int i = 0; i < x.length; i++) {
			sum += Math.pow(x[i], 2);
		}
		return Math.sqrt(sum);
	}
	
	public Point add(Point other) {
		double[] arr = new double[x.length];
		for(int i = 0; i < x.length; i++) {
			arr[i] = x[i] + other.x[i];
		}
		return new Point(arr);
	}
	
	public Point subtract(Point other) {
		double[] arr = new double[x.length];
		for(int i = 0; i < x.length; i++) {
			arr[i] = x[i] - other.x[i];
		}
		return new Point(arr);
	}
	
	public Point scale(double scaler) {
		for(int i = 0; i < x.length; i++) {
			x[i] *= scaler;
		}
		return this;
	}
	
	public Point scaled(double scaler) {
		double[] arr = new double[x.length];
		for(int i = 0; i < x.length; i++) {
			arr[i] = x[i] * scaler;
		}
		return new Point(arr);
	}
	
	public double distance(Point other) {
		double sum = 0;
		
		for(int i = 0; i < x.length; i++) {
			sum += Math.pow(x[i] - other.x[i], 2);
		}
		return Math.sqrt(sum);
	}
	
	public Point copy() {
		return new Point(toArray());
	}
	
	public double[] toArray() {
		return Arrays.copyOf(x, x.length);
	}

	@Override
	public String toString() {
		return "Point [x=" + Arrays.toString(x) + "]";
	}
	
}
