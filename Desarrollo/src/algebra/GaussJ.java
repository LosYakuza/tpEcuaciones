package algebra;

public class GaussJ {

	private final int N;
	private double[][] a;
	private double det;

	public GaussJ(double[][] A, int len) {
		N = len;
		this.det = 0;
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
	
	public double getDet(){
		return this.det;
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

			if (Math.abs(a[p][p]) == 0) {
				this.det = 0;
				throw new RuntimeException("No inversible");
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
		
		if(this.det != 0)
			det *= a[p][q];
		else
			det = a[p][q];
 		
		a[p][q] = 1.0;
	}

}
