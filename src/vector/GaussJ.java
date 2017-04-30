package vector;

public class GaussJ {
	private static final double EPSILON = 1e-8;

	private final int N;
	private double[][] a;

	public GaussJ(double[][] A, int len) {
		N = len;

		a = new double[N][N + N];
		//cargo a
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				a[i][j] = A[i][j];
		
		//cargo identidad
		for (int i = 0; i < N; i++)
			a[i][N + i] = 1.0;

		solve();

	}

	/**
	 * Devolver inversa
	 * @return
	 */
	public double[][] getInvert() {

		double[][] m = new double[N][N];
		
		for (int i = 0; i < N; i++) {
			for(int j = 0;j<N;j++){
				m[i][j]=a[i][j+N];
			}
		}
		return m;
	}

	/**
	 * Invertir
	 */
	private void solve() {
		for (int p = 0; p < N; p++) {

			int max = p;
			for (int i = p + 1; i < N; i++) {
				if (Math.abs(a[i][p]) > Math.abs(a[max][p])) {
					max = i;
				}
			}

			swap(p, max);

			if (Math.abs(a[p][p]) <= EPSILON) {
				throw new RuntimeException("Matrix is singular or nearly singular");
			}

			pivot(p, p);
		}
	}

	/**
	 * Intercambia filas de la matriz
	 * @param row1
	 * @param row2
	 */
	private void swap(int row1, int row2) {
		double[] temp = a[row1];
		a[row1] = a[row2];
		a[row2] = temp;
	}

	/**
	 * Pivotea en pq
	 * @param p
	 * @param q
	 */
	private void pivot(int p, int q) {

		//hace fx = fx - coef*fp
		for (int i = 0; i < N; i++) {
			double alpha = a[i][q] / a[p][q]; //coeficiente para la fila
			for (int j = 0; j < N + N; j++) {
				if (i != p && j != q)
					a[i][j] -= alpha * a[p][j];
			}
		}
		
		// pone en cero toda la columna (salvo pivot)
		for (int i = 0; i < N; i++)
			if (i != p)
				a[i][q] = 0.0;

		//pone en 1 el pivot (escala fila)
		for (int j = 0; j < N + N; j++)
			if (j != q)
				a[p][j] /= a[p][q];
		a[p][q] = 1.0;
	}

}
