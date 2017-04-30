package algebra;

import java.util.Arrays;

public class MatrizMath {
	private double mat[][];
	private int fil;
	private int col;

	/**
	 * Constructor
	 * @param i filas
	 * @param j columnas
	 */
	public MatrizMath(int i, int j) {
		this.mat = new double[i][j];
		this.fil = i;
		this.col = j;
	}

	/**
	 * Llena matriz desde array
	 * @param m
	 */
	public void cargarDesdevector(double [][] m){
		this.mat =m;
	}
	
	/**
	 * Llena matriz con formato octave
	 * @param s
	 */
	public void cargarDesdeStrOctave(String s){
		s = s.substring(1, s.length()-1);
		String [] fs = s.split(";");
		for(int i=0;i<fs.length;i++){
			String [] cs = fs[i].split(" ");
			for(int j = 0; j<cs.length; j++){
				this.cargarElemento(i, j, Double.parseDouble(cs[j]));
			}
		}
	}
	
	@Override
	protected MatrizMath clone() {
		MatrizMath m = new MatrizMath(this.fil, this.col);
		m.cargarDesdevector(this.mat);
		return m;
	}

	/**
	 * Convierte vector a matriz
	 * @param v
	 * @return
	 */
	public static MatrizMath castVec(VectorMath v){
		MatrizMath s = new MatrizMath(v.len(), 1);
		int i;
		for(i=0;i<v.len();i++){
			s.cargarElemento(i, 0, v.leerElemento(i)); 
		}
		return s;
	}
	
	/**
	 * Matriz a nula
	 */
	public void limpiar(){
		int i,j;
		for(i=0;i<=this.fil;i++){
			for(j=0;j<=this.col;j++){
				this.mat[i][j]=0;
			}
		}
	}
	
	/**
	 * Carga valor en matriz
	 * @param i fila
	 * @param j columna
	 * @param val valor
	 */
	public void cargarElemento(int i, int j, double val) {
		if(i>=this.fil || j>=this.col)
			throw new DistDimException("Fuera de rango");
		this.mat[i][j]=val;
	}
	
	/**
	 * Leer valor en matriz
	 * @param i fila
	 * @param j columna
	 */
	public double leerElemento(int i, int j) {
		if(i>=this.fil || j>=this.col)
			throw new DistDimException("Fuera de rango");
		return this.mat[i][j];
	}
	
	/**
	 * Suma matrices
	 * @param m
	 * @return
	 */
	public MatrizMath sumar(MatrizMath m){
		if(m.getFil()!= this.getFil() || m.getCol()!=this.getCol())
			throw new DistDimException("Fuera de rango");
		MatrizMath s = new MatrizMath(this.getFil(), this.getCol());
		int i,j;
		for(i=0;i<=this.fil;i++){
			for(j=0;j<=this.col;j++){
				s.cargarElemento(i, j,
						this.leerElemento(i, j) +
						m.leerElemento(i, j));
			}
		}
		return s;
	}

	/**
	 * Resta matrices
	 * @param m
	 * @return
	 */
	public MatrizMath restar(MatrizMath m){
		if(m.getFil()!= this.getFil() || m.getCol()!=this.getCol())
			throw new DistDimException("Fuera de rango");
		MatrizMath s = new MatrizMath(this.getFil(), this.getCol());
		int i,j;
		for(i=0;i<this.fil;i++){
			for(j=0;j<this.col;j++){
				s.cargarElemento(i, j,
						this.leerElemento(i, j) -
						m.leerElemento(i, j));
			}
		}
		return s;
	}
	
	
	/**
	 * Multiplicar con vector
	 * @param m
	 * @return
	 */
	public MatrizMath multiplicar(VectorMath v){
		return this.multiplicar(MatrizMath.castVec(v));
	}
	
	/**
	 * Multiplicar con k
	 * @param m
	 * @return
	 */
	public MatrizMath multiplicar(double k){
		MatrizMath s = new MatrizMath(this.getFil(), this.getCol());
		int i,j;
		for(i=0;i<=this.fil;i++){
			for(j=0;j<=this.col;j++){
				s.cargarElemento(i, j, this.leerElemento(i, j)*k);
			}
		}
		return s;
	}
	
	/**
	 * Multiplicar matrices
	 * @param m
	 * @return
	 */
	public MatrizMath multiplicar(MatrizMath m){
		if(m.getFil()!= this.getCol())
			throw new DistDimException("Fuera de rango");
		MatrizMath s = new MatrizMath(this.getFil(), m.getCol());
		int i,j;
		for(i=0;i<this.getFil();i++){
			for(j=0;j<m.getCol();j++){
				for (int z=0; z< this.getCol(); z++) {
					s.cargarElemento(i, j,
							s.leerElemento(i, j) +
							this.leerElemento(i, z) *
							m.leerElemento(z, j));
				    }
				
			}
		}
		return s;
	}
	
	public int getFil() {
		return fil;
	}

	public int getCol() {
		return col;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + fil;
		result = prime * result + Arrays.deepHashCode(mat);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatrizMath other = (MatrizMath) obj;
		if (col != other.col)
			return false;
		if (fil != other.fil)
			return false;
		int i,j;
		for(i=0;i<this.getFil();i++){
			for(j=0;j<this.getCol();j++){
				if(Math.abs(this.leerElemento(i, j)-other.leerElemento(i, j))>0.005){
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public String toString() {
		String s = "[";
		int i,j;
		for(i=0;i<this.getFil();i++){
			for(j=0;j<this.getCol();j++){
				s+=this.leerElemento(i, j) + " ";
			}
			s=s.substring(0, s.length()-1);
			s+=";";
		}
		s=s.substring(0, s.length()-1);
		return s+"]";
	}
	
	/**
	 * Retorna inversa de la matriz
	 * @return
	 */
	public MatrizMath inversa(){
		if(this.col!=this.fil)
			throw new DistDimException("Matriz debe ser cuadrada");
		GaussJ inv = new GaussJ(this.mat,this.col);
		MatrizMath m = new MatrizMath(this.fil, this.col);
		m.cargarDesdevector(inv.getInvert());
		return m;
	}
	
	
	public double det(){
		GaussJ inv = new GaussJ(this.mat,this.col);
		return inv.getDet();
	}
	
	public double normaUno() {
		double max = 0, acum = 0;
		for (int j = 0; j < this.col; j++) {
			for (int i = 0; i < this.fil; i++) {
				if (this.mat[i][j] < 0)
					acum += this.mat[i][j] * (-1);
				else
					acum += this.mat[i][j];
			}
			if (acum > max)
				max = acum;
			acum = 0;
		}
		return max;
	}

	public double normaInfinito() {
		double max = 0, acum = 0;
		for (int i = 0; i < this.col; i++) {
			for (int j = 0; j < this.fil; j++) {
				if (this.mat[i][j] < 0)
					acum += this.mat[i][j] * (-1);
				else
					acum += this.mat[i][j];
			}
			if (acum > max)
				max = acum;
			acum = 0;
		}
		return max;
	}
	
	
}
