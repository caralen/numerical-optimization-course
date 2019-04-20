package hr.fer.zemris.apr.dz4.ga;

import static hr.fer.zemris.apr.dz4.utils.Constants.mutationProb;
import static hr.fer.zemris.apr.dz4.utils.Constants.n;
import static hr.fer.zemris.apr.dz4.utils.Constants.params;
import static hr.fer.zemris.apr.dz4.utils.Constants.popSize;

import java.util.Random;

import hr.fer.zemris.apr.dz4.chromosome.Unit;
import hr.fer.zemris.apr.dz4.chromosome.UnitBinary;

public class EliminationBinary extends EliminationGA {
	
	@Override
	void generatePopulation() {
		Random rand = new Random();
		for(int i = 0; i < popSize; i++) {
			int[] arr = new int[n * params];
			for(int j = 0; j < n * params; j++) {
				arr[j] = rand.nextInt(2);
			}
			population.add(new UnitBinary(arr));
		}
	}
	
	@Override
	Unit crossover(Unit parent1, Unit parent2) {
		UnitBinary p1 = (UnitBinary) parent1;
		UnitBinary p2 = (UnitBinary) parent2;
		return uniformCrossover(p1, p2);
	}
	
	@SuppressWarnings("unused")
	private Unit singlePointCrossover(UnitBinary p1, UnitBinary p2) {
		Random rand = new Random();
		int point = rand.nextInt(p1.length());
		
		int[] bits = new int[p1.length()];
		for(int i = 0; i < p1.length(); i++) {
			bits [i] = i < point ? p1.getBit(i) : p2.getBit(i);
		}
		return new UnitBinary(bits);
	}
	
	private Unit uniformCrossover(UnitBinary p1, UnitBinary p2) {
		Random rand = new Random();
		
		int[] bits = new int[p1.length()];
		for(int i = 0; i < p1.length(); i++) {
			bits[i] = rand.nextFloat() > 0.5 ? p1.getBit(i) : p2.getBit(i);
		}
		return new UnitBinary(bits);
	}

	@Override
	void mutate(Unit u) {
		UnitBinary unit = (UnitBinary) u;
		Random rand = new Random();
		
		for(int i = 0; i < unit.length(); i++) {
			if(mutationProb > rand.nextDouble()) {
				unit.setBit(1 - unit.getBit(i), i);
			}
		}
	}

}
