package hr.fer.zemris.apr.dz1;

import java.io.IOException;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		try {
			// zadatak 2.
			System.out.println("*********ZADATAK 2**********");
			double[][] matrix_2 = {{3, 9, 6}, {4, 12, 12}, {1, -1, 1}};
			double[][] vector_2 = {{12}, {12}, {1}};
			Matrix matrix2 = new Matrix(matrix_2);
			Matrix vector2 = new Matrix(vector_2);
//			Matrix lu2 = matrix2.lu();
			Matrix lu2 = matrix2.lup();
			Matrix y2 = lu2.forwardSupstitution(vector2);
			Matrix x2 = lu2.backwardSupstitution(y2);
			
			
			// zadatak 3.
			System.out.println("*********ZADATAK 3**********");
			double[][] matrix_3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
			double[][] vector_3 = {{12}, {12}, {1}};
			Matrix matrix3 = new Matrix(matrix_3);
			Matrix vector3 = new Matrix(vector_3);
//			Matrix lu3 = matrix3.lu();
			Matrix lu3 = matrix3.lup();
			Matrix y3 = lu3.forwardSupstitution(vector2);
			Matrix x3 = lu3.backwardSupstitution(y3);
			
			
			// zadatak 4.
			System.out.println("*********ZADATAK 4**********");
			double[][] matrix_4 = {{0.000001, 3000000, 2000000}, {1000000, 2000000, 3000000}, {2000000, 1000000, 2000000}};
			double[][] vector_4 = {{12000000.000001}, {14000000}, {10000000}};
			Matrix matrix4 = new Matrix(matrix_4);
			Matrix vector4 = new Matrix(vector_4);
			Matrix lu4 = matrix4.lu();
//			Matrix lu4 = matrix4.lup();
			Matrix y4 = lu4.forwardSupstitution(vector4);
			Matrix x4 = lu4.backwardSupstitution(y4);
			
			
			// zadatak 5.
			System.out.println("*********ZADATAK 5**********");
			double[][] matrix_5 = {{0, 1, 2}, {2, 0, 3}, {3, 5, 1}};
			double[][] vector_5 = {{6}, {9}, {3}};
			Matrix matrix5 = new Matrix(matrix_5);
			Matrix vector5 = new Matrix(vector_5);
			Matrix lu5 = matrix5.lup();
			Matrix y5 = lu5.forwardSupstitution(vector5);
			Matrix x5 = lu5.backwardSupstitution(y5);
			
			
			// zadatak 6.
			System.out.println("*********ZADATAK 6**********");
			Matrix matrix6 = Matrix.parseFile("./resources/matrica.txt");
			Matrix vector6 = Matrix.parseFile("./resources/vektor.txt");
			Matrix lu6 = matrix6.lup();
			Matrix y6 = lu6.forwardSupstitution(vector6);
			Matrix x6 = lu6.backwardSupstitution(y6);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			System.err.println(e.getMessage());
		}
	}
}
