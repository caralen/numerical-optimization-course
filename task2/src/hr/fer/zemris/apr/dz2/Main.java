package hr.fer.zemris.apr.dz2;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import hr.fer.zemris.apr.dz2.optimizers.CoordinateSearch;
import hr.fer.zemris.apr.dz2.optimizers.HookeJeeves;
import hr.fer.zemris.apr.dz2.optimizers.Optimizer;
import hr.fer.zemris.apr.dz2.optimizers.SimplexNelderMeade;

@SuppressWarnings("unused")
public class Main {
	
	private static final Function f1 = (x) -> 100 * pow(x[1] - pow(x[0], 2), 2) + 1 * pow(1 - x[0], 2);
	
	private static final Function f2 = (x) -> pow(x[0] - 4, 2) + 4 * pow(x[1] - 2, 2);
	
	private static final Function f3 = (x) -> {
		double sum = 0;
		for (int i = 0; i < x.length; i++)
			sum += pow(x[i] - (i + 1), 2);
		
		return sum;
	};
	
	private static final Function f4 = (x) -> abs((x[0] - x[1]) * (x[0] + x[1]) + sqrt(pow(x[0], 2) + pow(x[1], 2)));
	
	private static final Function f6 = (x) -> {
		double sum = 0;
		for(int i = 0; i < x.length; i++) 
			sum += pow(x[i], 2);
		
		return 0.5 + (pow(sin(sqrt(sum)), 2) - 0.5) / pow(1 + 0.001 * sum, 2);
	};
	
	private static final Function f7 = (x) -> pow(x[0] - 2, 2) + pow(x[1] + 1, 2);
	
	private static final Function f8 = (x) -> pow(x[0], 2) + 4 * pow(x[1], 2);

	private static final boolean addInput = false;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		//zadatak0
		runAllOptimizers(sc, f8, new Point(7, 3), addInput);

		//zadatak2
		runAllOptimizers(sc, new FunctionCounter(f1), new Point(-1.9, 2), addInput);
		runAllOptimizers(sc, new FunctionCounter(f2), new Point(0.1, 0.3), addInput);
		runAllOptimizers(sc, new FunctionCounter(f3), new Point(0, 0, 0, 0, 0), addInput);
		runAllOptimizers(sc, new FunctionCounter(f4), new Point(5.1, 1.1), addInput);
		
		//zadatak3
		runAllOptimizers(sc, new FunctionCounter(f4), new Point(5, 5), addInput);
		
		//zadatak4
		runAllOptimizers(sc, new FunctionCounter(f1), new Point(0.5, 0.5), addInput);
		
		//zadatak5
		Random rand = new Random();
		double first = rand.nextFloat() * 100 - 50;
		double second = rand.nextFloat() * 100 - 50;
		System.out.println("Tocka: (" + first + ", " + second + ")");
		runAllOptimizers(sc, new FunctionCounter(f6), new Point(first, second), addInput);
		
		
		sc.close();
	}
	
	private static void runAllOptimizers(Scanner sc, Function f, Point p, boolean inputFlag) throws IOException {
		Optimizer o1;
		Optimizer o2;
		Optimizer o3;
		
		if(inputFlag) {
			o1 = runHookeJeeves(sc, new FunctionCounter(f), p.copy());
			o2 = runCoordinate(sc, new FunctionCounter(f), p.copy());
			o3 = runSimplex(sc, new FunctionCounter(f), p.copy());
		} else {
			o1 = new HookeJeeves(new FunctionCounter(f), p.copy()).optimize();
			o2 = new CoordinateSearch(new FunctionCounter(f), p.copy()).optimize();
			o3 = new SimplexNelderMeade(new FunctionCounter(f), p.copy()).optimize();
		}
		
		System.out.println(o1);
		System.out.println(o2);
		System.out.println(o3);
		System.out.println("---------------------------------------");
	}
	
	private static Optimizer runHookeJeeves(Scanner sc, Function f, Point p) {
		HookeJeeves o = new HookeJeeves(new FunctionCounter(f), p.copy());
		
		System.out.println("Podatci za pretragu po Hooke Jeeves:");
		
		String line;
		String[] parts;
		
		System.out.format("Pocetna tocka: ");
		line = sc.nextLine();
		if(!line.isEmpty()) {
			parts = line.split("\\s+");
			
			double[] arr = new double[parts.length];
			for(int i = 0; i < parts.length; i++) {
				try {
					arr[i] = Double.parseDouble(parts[i]);
				} catch(NumberFormatException ex) {
					arr[i] = 0;
				}
			}
			o.setX0(new Point(arr));
		}
		
		System.out.format("Upisi pomak: ");
		line = sc.nextLine();
		try {
			o.setDx(Double.parseDouble(line));
		} catch(NumberFormatException ex) {
			o.setDx(0.5);
		}
		
		System.out.format("Upisi preciznost: ");
		line = sc.nextLine();
		try {
			o.setEpsilon(Double.parseDouble(line));
		} catch(NumberFormatException ex) {
			o.setEpsilon(10E-6);
		}
		o.optimize();
		return o;
	}
	
	
	private static Optimizer runCoordinate(Scanner sc, Function f, Point p) {
		CoordinateSearch o = new CoordinateSearch(new FunctionCounter(f), p.copy());
		
		System.out.println("Podatci za pretragu po koordinarnim osima:");
		
		String line;
		String[] parts;
		
		System.out.format("Pocetna tocka: ");
		line = sc.nextLine();
		if(!line.isEmpty()) {
			parts = line.split("\\s+");
			
			double[] arr = new double[parts.length];
			for(int i = 0; i < parts.length; i++) {
				try {
					arr[i] = Double.parseDouble(parts[i]);
				} catch(NumberFormatException ex) {
					arr[i] = 0;
				}
			}
			o.setX0(new Point(arr));
		}
		
		
		System.out.format("Upisi preciznost: ");
		line = sc.nextLine();
		
		try {
			o.setEpsilon(Double.parseDouble(line));
		} catch(NumberFormatException ex) {
			o.setEpsilon(10E-6);
		}
		o.optimize();
		return o;
	}
	
	private static Optimizer runSimplex(Scanner sc, Function f, Point p) {
		SimplexNelderMeade o = new SimplexNelderMeade(new FunctionCounter(f), p.copy());
		
		System.out.println("Podatci za pretragu po koordinarnim osima:");
		
		String line;
		String[] parts;
		
		System.out.format("Pocetna tocka: ");
		line = sc.nextLine();
		if(!line.isEmpty()) {
			parts = line.split("\\s+");
			
			double[] arr = new double[parts.length];
			for(int i = 0; i < parts.length; i++) {
				try {
					arr[i] = Double.parseDouble(parts[i]);
				} catch(NumberFormatException ex) {
					arr[i] = 0;
				}
			}
			o.setX0(new Point(arr));
		}
		
		System.out.format("Upisi preciznost e: ");
		line = sc.nextLine();
		try {
			o.setE(Double.parseDouble(line));
		} catch(NumberFormatException ex) {
			o.setE(10E-6);
		}
		
		System.out.format("Upisi alfu: ");
		line = sc.nextLine();
		try {
			o.setAlfa(Double.parseDouble(line));
		} catch(NumberFormatException ex) {
			o.setAlfa(1);
		}
		
		System.out.format("Upisi betu: ");
		line = sc.nextLine();
		try {
			o.setBeta(Double.parseDouble(line));
		} catch(NumberFormatException ex) {
			o.setBeta(0.5);
		}
		
		System.out.format("Upisi gammu: ");
		line = sc.nextLine();
		try {
			o.setGamma(Double.parseDouble(line));
		} catch(NumberFormatException ex) {
			o.setGamma(2);
		}
		
		System.out.format("Upisi sigmu: ");
		line = sc.nextLine();
		try {
			o.setSigma(Double.parseDouble(line));;
		} catch(NumberFormatException ex) {
			o.setSigma(0.5);
		}
		
		System.out.format("Upisi pomak: ");
		line = sc.nextLine();
		try {
			o.setStep(Double.parseDouble(line));
		} catch(NumberFormatException ex) {
			o.setStep(1);
		}
		o.optimize();
		return o;
	}
}
