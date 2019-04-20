package hr.fer.zemris.apr.dz4.utils;

import hr.fer.zemris.apr.dz4.Function;
import hr.fer.zemris.apr.dz4.chromosome.ChromosomeType;

public class Constants {
	
	public static int popSize = 100;

	public static ChromosomeType type = ChromosomeType.DECIMAL;
	
	public static double mutationProb = 0.02;
	
	public static int iterations = 1_000_000;

	public static final Function f = Functions.f6;
	
	public static final int params = 3;
	
	public static final int k = 3;
 
	public static final int dg = -50;
	
	public static final int gg = 150;

	public static final int precision = 3;
	
	public static int n;

	public static void initialiseConstants(int popSize, ChromosomeType type, double mutationProb, int iterations) {
		Constants.popSize = popSize;
		Constants.type = type;
		Constants.mutationProb = mutationProb;
		Constants.iterations = iterations;
		calculateN();
	}
	
	public static void initialiseConstants() {
		calculateN();
	}
	
	public static void calculateN() {
		double brojnik = Math.log(1 + (gg-dg) * Math.pow(10, precision));
		double nazivnik = Math.log(2);
		n = (int)Math.ceil(brojnik / nazivnik);
	}

}
