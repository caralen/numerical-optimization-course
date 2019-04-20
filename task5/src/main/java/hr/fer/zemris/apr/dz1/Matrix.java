package hr.fer.zemris.apr.dz1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Matrix {

	private int x;
	
	private int y;
	
	private double[][] elements;
	
	private static double epsilon = 1e-6;
	
	public Matrix(int x, int y) {
		elements = new double[x][y];
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Matrix(double[][] elements) {
		this.elements = elements;
		x = elements.length;
		y = elements.length != 0 ? elements[0].length : 0;
	}
	
	public Matrix copyOf() {
		return new Matrix(elements);
	}
	
	public void setElement(double value, int x, int y) {
		elements[x][y] = value;
	}
	
	public double getElement(int x, int y) {
		return elements[x][y];
	}
	
	public double[][] getElements() {
		return elements;
	}
	
	public Matrix add(Matrix other) {
		if(this.x != other.x || this.y != other.y) {
			throw new IllegalArgumentException("Matrices must be of the same dimension.");
		}
		double[][] addedElements = new double[x][y];
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				addedElements[i][j] = this.getElement(i, j) + other.getElement(i, j);
			}
		}
		return new Matrix(addedElements);
	}
	
	public Matrix subtract(Matrix other) {
		if(this.x != other.x || this.y != other.y) {
			throw new IllegalArgumentException("Matrices must be of the same dimension.");
		}
		double[][] subtractedElements = new double[x][y];
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				subtractedElements[i][j] = this.getElement(i, j) - other.getElement(i, j);
			}
		}
		return new Matrix(subtractedElements);
	}
	
	public Matrix multiply(Matrix other) {
		if(this.y != other.x ) {
			throw new IllegalArgumentException("Matrices cannot be multiplied");
		}
		
		double[][] multipliedElements = new double[this.x][other.y];
		for(int i = 0; i < this.x; i++) {
			for(int j = 0; j < other.y; j++) {
				for(int k = 0; k < this.y; k++) {
					multipliedElements[i][j] += this.elements[i][k] * other.elements[k][j];
				}
			}
		}
		return new Matrix(multipliedElements);
	}
	
	public Matrix transpose() {
		double[][] transposedElements = new double[x][y];
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				transposedElements[j][y - 1 - i] = elements[i][j];
			}
		}
		return new Matrix(transposedElements);
	}
	
	public Matrix scaled(double scaler) {
		double[][] scaledElements = new double[x][y];
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				scaledElements[i][j] = scaler * elements[i][j];
			}
		}
		return new Matrix(scaledElements);
	}
	
	public void addToThis(Matrix other) {
		if(this.x != other.x || this.y != other.y) {
			throw new IllegalArgumentException("Matrices must be of the same dimension.");
		}
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				this.elements[i][j] += other.elements[i][j];
			}
		}
	}
	
	public void subtractFromThis(Matrix other) {
		if(this.x != other.x || this.y != other.y) {
			throw new IllegalArgumentException("Matrices must be of the same dimension.");
		}
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				this.elements[i][j] -= other.elements[i][j];
			}
		}
	}
	
	public void switchRows(int m, int n) {
		double[][] changedElements = new double[x][y];
		
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				if(i == m) {
					changedElements[i][j] = elements[n][j];
				} else if(i == n) {
					changedElements[i][j] = elements[m][j];
				} else {
					changedElements[i][j] = elements[i][j];
				}
			}
		}
		elements = changedElements;
	}
	
	public void switchColumns(int m, int n) {
		double[][] changedElements = new double[x][y];
		
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				if(j == m) {
					changedElements[i][j] = elements[i][n];
				} else if(j == n) {
					changedElements[i][j] = elements[i][m];
				} else {
					changedElements[i][j] = elements[i][j];
				}
			}
		}
		elements = changedElements;
	}
	
	public Matrix getColumn(int column) {
		double[][] columnMatrix = new double[x][1];
		for(int i = 0; i < x; i++) {
			columnMatrix[i][0] = elements[i][column];
		}
		return new Matrix(columnMatrix);
	}
	
	public void setColumn(Matrix vector, int column) {
		for(int i = 0; i < x; i++) {
			elements[i][column] = vector.getElement(i, 0);
		}
	}
	
	public Matrix getRow(int row) {
		double[][] rowMatrix = new double[1][y];
		for(int i = 0; i < y; i++) {
			rowMatrix[0][i] = elements[row][i];
		}
		return new Matrix(rowMatrix);
	}
	
	public void setRow(Matrix vector, int row) {
		for(int i = 0; i < x; i++) {
			elements[row][i] = vector.getElement(0, i);
		}
	}
 	
	public Matrix forwardSupstitution(Matrix b) {
		if(this.x != this.y) {
			throw new IllegalArgumentException("This operation is only valid on square matrix.");
		}
		if(b.x != this.x) {
			throw new IllegalArgumentException("Given vector should be of the same length as this matrix.");
		}
		Matrix m = new Matrix(x, 1);
		
		for(int i = 0; i < x; i++) {
			double sum = b.getElement(i, 0);
			for(int j = 0; j < i; j++) {
				sum -= this.getElement(i, j) * m.getElement(j, 0);
			}
			m.setElement(sum, i, 0);
		}
//		System.out.println(m.toString());
//		System.out.println();
		return m;
	}
	
	public Matrix backwardSupstitution(Matrix y) {
		if(this.x != this.y) {
			throw new IllegalArgumentException("This operation is only valid on square matrices.");
		}
		if(y.x != this.x) {
			throw new IllegalArgumentException("Given vector should be of the same length as this matrix.");
		}
		double[][] result = new double[x][1];
		
		for(int i = x-1; i >= 0; i--) {
			double sum = y.getElement(i, 0);
			for(int j = x-1; j > i; j--) {
				sum -= elements[i][j] * result[j][0];
			}
			if(Math.abs(elements[i][i]) < epsilon) {
				throw new RuntimeException("Cannot divide with 0");
			}
			result[i][0] = sum / elements[i][i];
		}
		Matrix m = new Matrix(result);
//		System.out.println(m.toString());
		return m;
	}
	
	public Matrix lu() {
		double[][] newElements = elements;
		for(int i = 0; i < x - 1; i++) {
			if (Math.abs(elements[i][i]) < epsilon) {
                throw new RuntimeException("Pivot is zero.");
            }
			for(int j = i + 1; j < x; j++) {
				newElements[j][i] /= newElements[i][i];
				for(int k = i+1; k < x; k++) {
					newElements[j][k] -= newElements[j][i] * newElements[i][k];
				}
			}
		}
		Matrix m = new Matrix(newElements);
		System.out.println(m.toString());
		return m;
	}
	
	public Matrix lup() {
		Matrix m = this.copyOf();
		Matrix p = new Matrix(x, y);
		
		for(int i = 0; i < x; i++) {
			p.setElement(1, i, i);
			for(int j = i + 1; j < x; j++) {
				p.setElement(0, i, j);
				p.setElement(0, j, i);
			}
		}
		
		for(int i = 0; i < x - 1; i++) {
			int pivot = i;
			for(int j = i + 1; j < x; j++) {
				if(Math.abs(m.getElement(j, i)) > Math.abs(m.getElement(pivot, i))) {
					pivot = j;
				}
			}
			if(pivot != i) {
				m.switchRows(i, pivot);
				p.switchRows(i, pivot);
			}
			if(Math.abs(m.getElement(i, i)) < epsilon) {
				throw new RuntimeException("Matrix is singular.");
			}
			for(int j = i + 1; j < x; j++) {
				m.setElement(m.getElement(j, i) / m.getElement(i, i), j, i);
				for(int k = i + 1; k < x; k++) {
					m.setElement(m.getElement(j, k) - m.getElement(i, k) * m.getElement(j, i), j, k);
				}
			}
		}
		this.elements = m.elements;
		return p;
	}
	
	public static Matrix parseFile(String file) throws IOException {
		Path path = Paths.get(file);
		
		List<List<Double>> lists = new ArrayList<>();
		
		List<String> lines = Files.readAllLines(path);
		for(String line : lines) {
			String[] parts = line.split("\\s+");
			List<Double> row = new ArrayList<>();
			
			for(String part : parts) {
				row.add(Double.parseDouble(part));
			}
			lists.add(row);
		}
		if(lists.isEmpty()) return null;
		
		double[][] elements = new double[lists.size()][lists.get(0).size()];
		
		for(int i = 0; i < lists.size(); i++) {
			for(int j = 0; j < lists.get(i).size(); j++) {
				elements[i][j] = lists.get(i).get(j);
			}
		}
		Matrix m = new Matrix(elements);
//		System.out.println(m.toString());
		return m;
	}
	
	public void writeToFile(String file) throws IOException {
		Path path = Paths.get(file);
		
		for(int i = 0; i < x; i++) {
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j < y; j++) {
				sb.append(elements[i][j]);
				sb.append(" ");
			}
			String line = sb.toString().substring(0, sb.length()-1) + "\r\n";
			Files.write(path, line.getBytes());
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i = 0; i < x; i++) {
			sb.append("[");
			for(int j = 0; j < y; j++) {
				sb.append(elements[i][j]);
				sb.append(", ");
			}
			sb.setLength(sb.length()-2);
			sb.append("],\n");
		}
		sb.setLength(sb.length()-2);
		sb.append("]\n");
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matrix other = (Matrix) obj;
		if(this.x != other.x || this.y != other.y)
			return false;
		
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				if(Math.abs(this.getElement(i, j) - other.getElement(i, j)) > epsilon)
					return false;
			}
		}
		return true;
	}
	
	public double[] toArray() {
		double[] arr = new double[elements.length];
		
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				arr[i * y + j] = elements[i][j];
			}
		}
		return arr;
	}
	
	public static Matrix eye(int rank) {
		Matrix mat = new Matrix(rank, rank);
		
		for(int i = 0; i < rank; i++) {
			mat.elements[i][i] = 1;
		}
		return mat;
	}
	
	
	public Matrix inverse() {
		
		if(x != y) throw new IllegalArgumentException("Can only calculate inverse for square matrices.");
		
		int dim = x;
		Matrix p = this.lup();
		
		double[][] inverseElements = new double[dim][dim];

		for(int i = 0; i < dim; i++) {
			Matrix b = p.getColumn(i);

			Matrix y = this.forwardSupstitution(b);
			Matrix a = this.backwardSupstitution(y);
	
			for(int j = 0; j < dim; j++) {
				inverseElements[j][i] = a.getElement(j, 0);
			}
		}
		
		elements = inverseElements;
		return this;
	}
}
