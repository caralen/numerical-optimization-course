package hr.fer.zemris.apr.dz4.ga;

import static hr.fer.zemris.apr.dz4.utils.Constants.mutationProb;
import static hr.fer.zemris.apr.dz4.utils.Constants.params;
import static hr.fer.zemris.apr.dz4.utils.Constants.popSize;
import static hr.fer.zemris.apr.dz4.utils.Constants.dg;
import static hr.fer.zemris.apr.dz4.utils.Constants.gg;

import java.util.Random;

import hr.fer.zemris.apr.dz4.chromosome.Unit;
import hr.fer.zemris.apr.dz4.chromosome.UnitDouble;
import hr.fer.zemris.apr.dz4.utils.Constants;

public class EliminationDouble extends EliminationGA {

	@Override
	void generatePopulation() {
		Random rand = new Random();
		
		for(int i = 0; i < popSize; i++) {
			double[] arr = new double[params];
			for(int j = 0; j < params; j++) {
				arr[j] = dg + (gg-dg) * rand.nextDouble();
			}
			population.add(new UnitDouble(arr));
		}
	}

	@Override
	Unit crossover(Unit parent1, Unit parent2) {
		UnitDouble p1 = (UnitDouble) parent1;
		UnitDouble p2 = (UnitDouble) parent2;
		return heuristicCrossover(p1, p2);
	}
	
	@SuppressWarnings("unused")
	private Unit arithmeticCrossover(UnitDouble p1, UnitDouble p2) {
		double rand = new Random().nextDouble();
		double[] x = new double[p1.length()];
		for(int i = 0; i < p1.length(); i++) {
			x[i] = rand * p1.getX(i) + (1 - rand) * p2.getX(i);
		}
		return new UnitDouble(x);
	}
	
	private Unit heuristicCrossover(UnitDouble p1, UnitDouble p2) {
		if(p1.getFitness() >= p2.getFitness()) {
			UnitDouble temp = p2;
			p2 = p1;
			p1 = temp;
		}
		Random rand = new Random();
		double[] x = new double[p1.length()];
		for(int i = 0; i < p1.length(); i++) {
			x[i] = rand.nextDouble() * (p2.getX(i) - p1.getX(i)) + p2.getX(i);
		}
		return new UnitDouble(x);
	}

	@Override
	void mutate(Unit u) {
		double mutationFP = (1 - Math.pow((1 - mutationProb), Constants.n)) / params;
		Random rand = new Random();
		UnitDouble unit = (UnitDouble) u;
		
		for(int i = 0; i < unit.length(); i++) {
			if(mutationFP > rand.nextDouble()) {
				unit.setX(dg + (gg-dg) * rand.nextDouble(), i);
			}
		}
	}

}
