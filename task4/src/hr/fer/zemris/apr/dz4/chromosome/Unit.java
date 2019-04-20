package hr.fer.zemris.apr.dz4.chromosome;

public abstract class Unit {

	private double fitness;

	public double getFitness() {
		return fitness;
	}

	public void setFintess(double fitness) {
		this.fitness = fitness;
	}
	
	public abstract int length();
	
	public abstract double[] getArray();

	public abstract boolean equals(Unit obj);

	
}
