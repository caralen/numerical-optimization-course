package hr.fer.zemris.apr.dz5;

public class Point {

	private double[] x;

	public Point(double ...x) {
		this.x = x;
	}
	
	public double[] getX() {
		return x;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < x.length; i++) {
			sb.append(x[i]);
			sb.append(" ");
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}
	
}
