package com.JayPi4c;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 * @author JayPi4c
 * @version 1.0.1
 */
public class Matrix implements Serializable {

	private static final long serialVersionUID = -1611903368454112326L;
	int rows, cols;
	double data[][];

	@Deprecated
	public Matrix() {
	}

	/**
	 * Erstellt eine Matrix und initialisiert sie mit 0 als Werten
	 * 
	 * @param rows die Anzahl der Reihen
	 * @param cols die Anzahl der Spalten
	 * @since 1.0.0
	 */
	public Matrix(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.data = new double[this.rows][this.cols];
	}

	/**
	 * Erstelle eine Matrix aus einem zweidimensionalem Array
	 * 
	 * @param data
	 * @since 1.0.0
	 */
	public Matrix(double data[][]) {
		this.rows = data.length;
		this.cols = data[0].length;
		this.data = new double[this.rows][this.cols];
		for (int row = 0; row < this.rows; row++)
			for (int col = 0; col < this.cols; col++)
				this.data[row][col] = data[row][col];
	}

	/**
	 * Erstellt ein zweidimensionales Array aus der Matrix
	 * 
	 * @return
	 * @since 1.0.0
	 */
	public double[][] toArray() {
		double output[][] = new double[this.rows][this.cols];
		for (int row = 0; row < this.rows; row++)
			for (int col = 0; col < this.cols; col++)
				output[row][col] = this.data[row][col];
		return output;
	}

	/**
	 * Erstellt aus einer Matrix ein zweidimensionales Array
	 * 
	 * @param m
	 * @return
	 * @since 1.0.0
	 */
	public static double[][] toArray(Matrix m) {
		double output[][] = new double[m.rows][m.cols];
		for (int row = 0; row < m.rows; row++)
			for (int col = 0; col < m.cols; col++)
				output[row][col] = m.data[row][col];
		return output;
	}

	/**
	 * Kopiert diese Matrix
	 * 
	 * @return unabh&aumlngige Kopie der Matrix
	 * @since 1.0.0
	 */
	public Matrix copy() {
		return new Matrix(this.data);
	}

	/**
	 * F&uumlllt die Matrix mit immer den selben Werten
	 * 
	 * @param d der Wert, mit dem die Matrix gef&uumlllt wird.
	 * @return
	 * @since 1.0.0
	 */
	public Matrix fill(double d) {
		for (int row = 0; row < this.rows; row++) {
			for (int col = 0; col < this.cols; col++) {
				this.data[row][col] = d;
			}
		}
		return this;
	}

	/**
	 * Verwendung:<br>
	 * Matrix m = new Matrix(3, 3);<br>
	 * m.fill(new Matrix(), Matrix.class.getMethod("random"));
	 * 
	 * @param obj
	 * @param m   Muss eine Funktion sein, die einen double zur&uumlck gibt und
	 *            keine Parameter annimmt
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @since 1.0.0
	 */
	public Matrix fill(Object obj, Method m)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (int row = 0; row < this.rows; row++) {
			for (int col = 0; col < this.cols; col++) {
				this.data[row][col] = (double) m.invoke(obj);
			}
		}
		return this;
	}

	/**
	 * 
	 * Verwendung:<br>
	 * Matrix m = new Matrix(3, 3);<br>
	 * m.map(new Matrix(), Matrix.class.getMethod("sigmoid", double.class));
	 * 
	 * @param obj
	 * @param m
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @since 1.0.0
	 */
	public Matrix map(Object obj, Method m)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (int row = 0; row < this.rows; row++) {
			for (int col = 0; col < this.cols; col++) {
				this.data[row][col] = (double) m.invoke(obj, this.data[row][col]);
			}
		}
		return this;
	}

	/**
	 * f&uumlllt die Matrix mit zuf&aumllligen Werten zwischen min und max
	 * 
	 * @param min
	 * @param max
	 * @return
	 * @since 1.0.0
	 */
	public Matrix randomize(double min, double max) {
		for (int row = 0; row < this.rows; row++) {
			for (int col = 0; col < this.cols; col++) {
				this.data[row][col] = ThreadLocalRandom.current().nextDouble(min, max + 0.000001);
			}
		}
		return this;
	}

	/**
	 * f&uumlllt die Matrix mit zuf&aumllligen Werten zwischen 0 und 1
	 * 
	 * @return
	 * @since 1.0.0
	 */
	public Matrix randomize() {
		for (int row = 0; row < this.rows; row++) {
			for (int col = 0; col < this.cols; col++) {
				this.data[row][col] = ThreadLocalRandom.current().nextDouble();
			}
		}
		return this;
	}

	/**
	 * Schreibt den Inhalt der Matrix anschaulich in die Konsole.
	 * 
	 * @since 1.0.0
	 */
	public void print() {
		System.out.println("-------------------------------------------------");
		for (int row = 0; row < this.rows; row++) {
			for (int col = 0; col < this.cols; col++) {
				System.out.print(this.data[row][col] + "\t");
			}
			System.out.println();
		}
		System.out.println("-------------------------------------------------");
	}

	public double random() {
		return Math.random();
	}

	/**
	 * 
	 * @return
	 * @since 1.0.0
	 */
	public Matrix transpose() {
		Matrix m = new Matrix(this.cols, this.rows);
		for (int row = 0; row < this.rows; row++)
			for (int col = 0; col < this.cols; col++)
				m.data[col][row] = this.data[row][col];
		this.data = m.data;
		this.rows = m.rows;
		this.cols = m.cols;

		return this;
	}

	/**
	 * 
	 * @param matrix
	 * @return
	 * @since 1.0.0
	 */
	public static Matrix transpose(Matrix matrix) {
		Matrix newMatrix = new Matrix(matrix.cols, matrix.rows);
		for (int row = 0; row < matrix.rows; row++)
			for (int col = 0; col < matrix.cols; col++)
				newMatrix.data[col][row] = matrix.data[row][col];
		return newMatrix;
	}

	/**
	 * 
	 * @param m
	 * @return
	 * @since 1.0.0
	 */
	public Matrix dot(Matrix m) {
		if (this.cols != m.rows)
			throw new IllegalArgumentException("A's cols and B's rows must match!");

		Matrix newMatrix = new Matrix(this.rows, m.cols);
		for (int row = 0; row < newMatrix.rows; row++) {
			for (int col = 0; col < newMatrix.cols; col++) {
				double sum = 0;
				for (int j = 0; j < this.cols; j++) {
					sum += this.data[row][j] * m.data[j][col];
				}
				newMatrix.data[row][col] = sum;
			}
		}
		this.data = newMatrix.data;
		this.rows = newMatrix.rows;
		this.cols = newMatrix.cols;

		return this;
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 * @since 1.0.0
	 */
	public static Matrix dot(Matrix a, Matrix b) {
		if (a.cols != b.rows)
			throw new IllegalArgumentException("A's cols and B's rows must match!");

		Matrix newMatrix = new Matrix(a.rows, b.cols);
		for (int row = 0; row < newMatrix.rows; row++) {
			for (int col = 0; col < newMatrix.cols; col++) {
				double sum = 0;
				for (int j = 0; j < a.cols; j++) {
					sum += a.data[row][j] * b.data[j][col];
				}
				newMatrix.data[row][col] = sum;
			}
		}
		return newMatrix;
	}

	/**
	 * 
	 * @param m
	 * @return
	 * @since 1.0.0
	 */
	public Matrix sub(Matrix m) {
		if (m.cols != this.cols || m.rows != this.rows)
			throw new IllegalArgumentException("rows and columns must match!");

		for (int row = 0; row < this.rows; row++)
			for (int col = 0; col < this.cols; col++)
				this.data[row][col] -= m.data[row][col];
		return this;
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 * @since 1.0.0
	 */
	public static Matrix sub(Matrix a, Matrix b) {
		if (a.cols != b.cols || a.rows != b.rows) {
			System.out.println("rows and columns must match!");
			return null;
		}
		Matrix newMatrix = new Matrix(a.rows, a.cols);
		for (int row = 0; row < a.rows; row++)
			for (int col = 0; col < a.cols; col++)
				newMatrix.data[row][col] = a.data[row][col] - b.data[row][col];
		return newMatrix;
	}

	/**
	 * 
	 * @param d
	 * @param m
	 * @return
	 * @since 1.0.0
	 */
	public static Matrix sub(double d, Matrix m) {
		Matrix newMatrix = new Matrix(m.rows, m.cols);
		for (int row = 0; row < newMatrix.rows; row++)
			for (int col = 0; col < newMatrix.cols; col++)
				newMatrix.data[row][col] = d - m.data[row][col];
		return newMatrix;
	}

	/**
	 * 
	 * @param m
	 * @return
	 * @since 1.0.0
	 */
	public Matrix add(Matrix m) {
		if (this.cols != m.cols || this.rows != m.rows) {
			System.out.println("rows and columns must match!");
			return null;
		}
		for (int row = 0; row < this.rows; row++)
			for (int col = 0; col < this.cols; col++)
				this.data[row][col] += m.data[row][col];
		return this;
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 * @since 1.0.0
	 */
	public static Matrix add(Matrix a, Matrix b) {
		if (a.cols != b.cols || a.rows != b.rows) {
			System.out.println("rows and columns must match!");
			return null;
		}
		Matrix newMatrix = new Matrix(a.rows, a.cols);
		for (int row = 0; row < a.rows; row++)
			for (int col = 0; col < a.cols; col++)
				newMatrix.data[row][col] = a.data[row][col] + b.data[row][col];
		return newMatrix;
	}

	/**
	 * 
	 * @param scl
	 * @return
	 * @since 1.0.0
	 */
	public Matrix mult(double scl) {
		for (int row = 0; row < this.rows; row++)
			for (int col = 0; col < this.cols; col++)
				this.data[row][col] *= scl;
		return this;
	}

	/**
	 * 
	 * @param m
	 * @return
	 * @since 1.0.0
	 */
	public Matrix mult(Matrix m) {
		if (this.cols != m.cols || this.rows != m.rows) {
			System.out.println("rows and columns must match!");
			return null;
		}
		for (int row = 0; row < this.rows; row++)
			for (int col = 0; col < this.cols; col++)
				this.data[row][col] *= m.data[row][col];
		return this;
	}

	/**
	 * 
	 * @param m
	 * @param scl
	 * @return
	 * @since 1.0.0
	 */
	public static Matrix mult(Matrix m, double scl) {
		Matrix newMatrix = new Matrix(m.rows, m.cols);
		for (int row = 0; row < newMatrix.rows; row++)
			for (int col = 0; col < newMatrix.cols; col++)
				newMatrix.data[row][col] = scl * m.data[row][col];
		return newMatrix;
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 * @since 1.0.0
	 */
	public static Matrix mult(Matrix a, Matrix b) {
		if (a.cols != b.cols || a.rows != b.rows)
			throw new IllegalArgumentException("rows and columns must match!");
		Matrix newMatrix = new Matrix(a.rows, b.cols);
		for (int row = 0; row < newMatrix.rows; row++)
			for (int col = 0; col < newMatrix.cols; col++)
				newMatrix.data[row][col] = a.data[row][col] * b.data[row][col];
		return newMatrix;
	}

	/**
	 * 
	 * @param col
	 * @param data
	 * @return
	 * @since 1.0.1
	 */
	public Matrix setColumn(int col, double data[]) {
		if (this.cols <= col || col < 0)
			throw new IllegalArgumentException("This column does not exist!");
		if (this.data.length != data.length)
			throw new IllegalArgumentException("The data does not fit in the column!");
		for (int i = 0; i < this.data.length; i++)
			this.data[i][col] = data[i];
		return this;
	}

	/**
	 * 
	 * @param row
	 * @param data
	 * @return
	 * @since 1.0.1
	 */
	public Matrix setRow(int row, double data[]) {
		if (this.rows <= row || row < 0)
			throw new IllegalArgumentException("This row does not exist!");
		if (this.data[row].length != data.length)
			throw new IllegalArgumentException("The data does not fit in the row!");
		this.data[row] = data;
		return this;
	}

	/**
	 * 
	 * @return
	 * @since 1.0.1
	 */
	public double det() {
		if (data.length != data[0].length)
			throw new IllegalArgumentException("The Matrix is not quadratic!");
		int n = data.length;

		if (n == 1)
			return data[0][0];

		if (n == 2)
			return (data[0][0] * data[1][1]) - (data[0][1] * data[1][0]);

		double det = 0;
		double[][] tmp;
		for (int i = 0; i < n; i++) {
			tmp = new double[n - 1][n - 1];
			for (int j = 1; j < n; j++)
				for (int k = 0; k < n; k++)
					if (k < i)
						tmp[j - 1][k] = data[j][k];
					else if (k > i)
						tmp[j - 1][k - 1] = data[j][k];
			Matrix m = new Matrix(tmp);
			det += (data[0][i] * Math.pow(-1, i) * m.det());
		}
		return det;
	}

	/**
	 * Setzt den Wert in der angegeben Zeile und Spalte auf den gegebenen Wert.
	 * 
	 * @param row
	 * @param col
	 * @param val
	 * @return
	 * @since 1.0.1
	 */
	public Matrix set(int row, int col, double val) {
		data[row][col] = val;
		return this;
	}

}