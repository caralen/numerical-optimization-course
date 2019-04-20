package hr.fer.zemris.apr.dz5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;

import hr.fer.zemris.apr.dz1.Matrix;

public class Main {
	
	private static final String file = "./src/main/resources/data";
	private static double t = 3;
	private static double T = 0.1;
	private static int printStep = 2;
	private static NumericalMethod integration = new RungeKutta(printStep);

	public static void main(String[] args) {
		
//		first();
//		second();
//		third();
		fourth();
		
	}
	
	private static void first() {
		double[][] arr1 = new double[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		Matrix mat1 = new Matrix(arr1);
		printMatrix(mat1);
	}

	private static void second() {
		double[][] arr2 = new double[][] {{4, -5, -2}, {5, -6, -2}, {-8, 9, 3}};
		Matrix mat2 = new Matrix(arr2);
		printMatrix(mat2);
	}

	private static void third() {
		Matrix matA = new Matrix(new double[][] {{0, 1}, {-1, 0}});
		Matrix matX = new Matrix(new double[][] {{1}, {-2}});
		Matrix matB = new Matrix(new double[][] {{0}, {0}});
		
		Map<Double, Point> map = integration.iteration(matA, matX, matB, T, t);
		
		writeMapToFile(map);
	}

	private static void fourth() {
		Matrix matA = new Matrix(new double[][] {{0, 1}, {-200, -102}});
		Matrix matX = new Matrix(new double[][] {{1}, {-2}});
		Matrix matB = new Matrix(new double[][] {{0}, {0}});
		
		Map<Double, Point> map = integration.iteration(matA, matX, matB, T, t);
		
		writeMapToFile(map);
	}
	
	private static void printMatrix(Matrix mat) {
		try {
			System.out.println(mat.toString());
			System.out.println(mat.inverse().toString());
		} catch(RuntimeException e) {
			System.err.println(e.getMessage());
			System.err.println("Exiting program.");
			System.exit(1);
		}
	}
	
	private static void writeMapToFile(Map<Double, Point> map) {
		try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
			for(Entry<Double, Point> e : map.entrySet()) {
				writer.write(e.getKey() + "\t" + e.getValue().toString() + "\r\n");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}
	}
	
	@SuppressWarnings("unused")
	private static void printMap(Map<Double, Point> map) {
		for(Entry<Double, Point> e : map.entrySet()) {
			System.out.println(e.getKey() + " " + e.getValue().toString());
		}
	}

}
