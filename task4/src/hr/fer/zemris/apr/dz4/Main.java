package hr.fer.zemris.apr.dz4;

import static hr.fer.zemris.apr.dz4.utils.Constants.initialiseConstants;
import static hr.fer.zemris.apr.dz4.utils.Constants.iterations;
import static hr.fer.zemris.apr.dz4.utils.Constants.type;

import java.util.Scanner;

import hr.fer.zemris.apr.dz4.chromosome.ChromosomeType;
import hr.fer.zemris.apr.dz4.ga.EliminationBinary;
import hr.fer.zemris.apr.dz4.ga.EliminationDouble;
import hr.fer.zemris.apr.dz4.ga.EliminationGA;

public class Main {
	
	private static final boolean useInput = false;

	public static void main(String[] args) {
		
		EliminationGA ga = null;
		
		if(useInput) {
			Scanner sc = new Scanner(System.in);
			
			int popSize = 0;
			ChromosomeType type = null;
			double mutationProb = 0;
			int iterations = 0;
			
			while(true) {
				System.out.print("Upiši veličinu populacije");
				try {
					popSize = Integer.parseInt(sc.nextLine());
				} catch(NumberFormatException e) {
					System.out.println("Neispravan unos, kreni ispočetka.");
					continue;
				}
				
				System.out.println("Upiši vrstu prikaza rješenja (b/d)");
				String lineType = sc.nextLine();
				if(lineType.equals("b")) {
					type = ChromosomeType.BINARY;
				} else if(lineType.equals("d")) {
					type = ChromosomeType.DECIMAL;
				} else {
					System.out.println("Neispravan unos, kreni ispočetka.");
					continue;
				}
				
				try {
					mutationProb = Double.parseDouble(sc.nextLine());
				} catch(NumberFormatException e) {
					System.out.println("Neispravan unos, kreni ispočetka.");
					continue;
				}
				
				try {
					iterations = Integer.parseInt(sc.nextLine());
				} catch(NumberFormatException e) {
					System.out.println("Neispravan unos, kreni ispočetka.");
					continue;
				}
				initialiseConstants(popSize, type, mutationProb, iterations);
				break;
			}
			sc.close();
		}
		initialiseConstants();
		
		if(type == ChromosomeType.DECIMAL) ga = new EliminationDouble();
		if(type == ChromosomeType.BINARY) ga = new EliminationBinary();
		
		ga.createPopulation();
		for(int i = 0; i < iterations; i++) {
			ga.iteration();
		}
		ga.print();
	}

}
