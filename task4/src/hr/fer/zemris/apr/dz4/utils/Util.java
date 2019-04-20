package hr.fer.zemris.apr.dz4.utils;

import static hr.fer.zemris.apr.dz4.utils.Constants.dg;
import static hr.fer.zemris.apr.dz4.utils.Constants.f;
import static hr.fer.zemris.apr.dz4.utils.Constants.gg;
import static hr.fer.zemris.apr.dz4.utils.Constants.n;
import static hr.fer.zemris.apr.dz4.utils.Constants.params;
import static java.lang.Math.pow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hr.fer.zemris.apr.dz4.chromosome.Unit;

public class Util {
	
	private static double fmax_cache;
	private static double fmin_cache;
	

	public static List<Unit> randomSelect(List<Unit> pop, int n) {
		Random rand = new Random();
		List<Unit> nPop = new ArrayList<>();
		
		if(pop.size() == n) {
			for(Unit unit: pop) nPop.add(unit);
			return nPop;
		}
		
		int counter = 0;
		while(counter != n) {
			Unit unit = pop.get(rand.nextInt(pop.size()));
			if(nPop.contains(unit)) continue;
			nPop.add(unit);
			counter++;
		}
		return nPop;
	}
	
	public static void updatePopulationFitness(List<Unit> population) {
		double fmax = findFmax(population);
		double fmin = findFmin(population);
		
		fmax_cache = fmax;
		fmin_cache = fmin;
		
		for(int i = 0; i < population.size(); i++) {
			double fval = f.evaluate(population.get(i).getArray());
			population.get(i).setFintess((fval-fmax) / (fmin-fmax));
		}
	}
	
	public static void calculateFitness(List<Unit> population, Unit child) {
		double fval = f.evaluate(child.getArray());

		if(fval < fmin_cache || fval > fmax_cache) updatePopulationFitness(population);
		else child.setFintess((fval-fmax_cache) / (fmin_cache-fmax_cache));
	}

	public static double findFmax(List<Unit> population) {
		double fmax = 0;
		
		for(Unit unit : population) {
			double fval = f.evaluate(unit.getArray());
			if(fval > fmax) fmax = fval;
		}
		return fmax;
	}
	
	public static double findFmin(List<Unit> population) {
		double fmin = Double.MAX_VALUE;
		
		for(Unit unit : population) {
			double fval = f.evaluate(unit.getArray());
			if(fval < fmin) fmin = fval;
		}
		return fmin;
	}

	
	public static Unit findWorstUnit(List<Unit> pop) {
		double minFitness = Double.MAX_VALUE;
		Unit worstUnit = null;
		
		for(Unit unit : pop) {
			double fitness = unit.getFitness();
			if(fitness < minFitness) {
				minFitness = fitness;
				worstUnit = unit;
			}
		}
		return worstUnit;
	}
	
	public static Unit findBestUnit(List<Unit> pop) {
		double maxFitness = 0;
		Unit bestUnit = null;
		
		for(Unit unit : pop) {
			double fitness = unit.getFitness();
			if(fitness > maxFitness) {
				maxFitness = fitness;
				bestUnit = unit;
			}
		}
		return bestUnit;
	}
	
	public static double[] decode(int[] b) {
		double[] arr = new double[params];
		int counter = 0;
		
		for(int i = 0; i < params; i++) {
			for(int j = 0; j < n; j++) {
				arr[i] += b[counter] * pow(2, n - j - 1);
				counter++;
			}
			arr[i] = dg + arr[i] * (gg - dg) / (pow(2, n) - 1);
		}
		return arr;
	}
	
}
