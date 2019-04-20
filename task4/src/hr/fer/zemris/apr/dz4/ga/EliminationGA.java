package hr.fer.zemris.apr.dz4.ga;

import static hr.fer.zemris.apr.dz4.utils.Constants.k;
import static hr.fer.zemris.apr.dz4.utils.Util.calculateFitness;
import static hr.fer.zemris.apr.dz4.utils.Util.findWorstUnit;
import static hr.fer.zemris.apr.dz4.utils.Util.randomSelect;
import static hr.fer.zemris.apr.dz4.utils.Util.updatePopulationFitness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hr.fer.zemris.apr.dz4.chromosome.ChromosomeType;
import hr.fer.zemris.apr.dz4.chromosome.Unit;
import hr.fer.zemris.apr.dz4.utils.Constants;
import hr.fer.zemris.apr.dz4.utils.Util;

public abstract class EliminationGA implements GeneticAlgorithm {
	
	protected List<Unit> population;
	protected ChromosomeType type;
	private int counter = 0;
	
	
	abstract void generatePopulation();
	abstract Unit crossover(Unit parent1, Unit parent2);
	abstract void mutate(Unit unit);

	@Override
	public void createPopulation() {
		population = new ArrayList<>();
		generatePopulation();
		updatePopulationFitness(population);
	}
	

	@Override
	public void iteration() {
		List<Unit> kPop = randomSelect(population, k);
		
		Unit worst = findWorstUnit(kPop);
		kPop.remove(worst);
		population.remove(worst);
		
		List<Unit> parents = randomSelect(kPop, 2);
		Unit child = crossover(parents.get(0), parents.get(1));
		mutate(child);

		population.add(child);
		calculateFitness(population, child);
		
		if(counter % 10000 == 0) print();
		counter++;
	}
	

	@Override
	public void print() {
		Unit best = Util.findBestUnit(population);
		
		System.out.println("Najbolja jedinka je " + Arrays.toString(best.getArray())
			+ ", vrijednost fje je: " + Constants.f.evaluate(best.getArray())
		);
	}

}
