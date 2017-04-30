package algebra;

import java.util.Arrays;

public class VectorMath {

	private double vector[];
	private int dim;

	public VectorMath(int dim) {
		this.vector = new double[dim];
		this.dim = dim;
	}

	public int len(){ return this.dim; }
	
	/**
	 * Leer valor
	 * @param i fila
	 */
	public double leerElemento(int i) {
		if(i>=this.dim)
			throw new DistDimException("Fuera de rango");
		return this.vector[i];
	}
	
	public void agregarValor(int pos,double valor) {
		if (pos >= this.dim)
			throw new DistDimException("Se supera la dimension establecida");

		this.vector[pos] = valor;

	}

	/**
	 * Suma vectores
	 * @param obj
	 * @return
	 */
	public VectorMath sumar(VectorMath obj) {
		if (this.dim != obj.dim)
			throw new DistDimException("vectores de distinta dimension");
 
		VectorMath aux = new VectorMath(this.dim);

		for (int i = 0; i < this.dim; i++)
			aux.agregarValor(i,this.vector[i] + obj.vector[i]);

		return aux;

	}
   
	/**
	 * Resta vector
	 * @param obj
	 * @return
	 */
	public VectorMath restar(VectorMath obj) {

		if (this.dim != obj.dim)
			throw new DistDimException("vectores de distinta dimension");

		VectorMath aux = new VectorMath(this.dim);

		for (int i = 0; i < this.dim; i++)
			aux.agregarValor(i, this.vector[i] - obj.vector[i]);

		return aux;

	}
	
	
	/**
	 * Multiplica por k
	 * @param obj
	 * @return
	 */
	public VectorMath multiplicar(double obj) {
		VectorMath aux = new VectorMath(this.dim);

		for (int i = 0; i < this.dim; i++)
			aux.agregarValor(i,this.vector[i] * obj);

		return aux;
	}
	
	/**
	 * Devuelve norma uno
	 * @return
	 */
	public double normaUno() {
		double s =0;

		for (int i = 0; i < this.dim; i++)
			if(this.vector[i]>0)
				s+= this.vector[i];
			else
				s+= this.vector[i]*(-1);

		return s;
	}
	
	/**
	 * Devuelve modulo del vector
	 * @return
	 */
	public double normaDos() {
		double s =0;

		for (int i = 0; i < this.dim; i++)
			s+= Math.pow(this.vector[i], 2);

		return Math.sqrt(s);
	}
	
	/**
	 * Devuelve numero maximo
	 * @return
	 */
	public double normaInf() {
		double m =0;
		double abs=0;
		for (int i = 0; i < this.dim; i++){
			abs =this.vector[i];
			if(abs<0)
				abs = abs*(-1);
			
			if( i==0 || abs>m){
				m = abs;
			}
		}
		return m;
	}
	
	/**
	 * Multiplica por otro vector
	 * @param obj
	 * @return
	 */
	public VectorMath multiplicar(VectorMath obj) {

		if (this.dim != obj.dim)
			throw new DistDimException("vectores de distinta dimension");

		VectorMath aux = new VectorMath(this.dim);

		for (int i = 0; i < this.dim; i++)
			aux.agregarValor(i,this.vector[i] * obj.vector[i]);

		return aux;

	}
	
	@Override
	public VectorMath clone() {
		VectorMath v = new VectorMath(this.dim);

		for (int i = 0; i < dim; i++)
			v.agregarValor(i,this.vector[i]);

		return v;
	}

	/**
	 * Multiplica por matriz
	 * @param m
	 * @return
	 */
	public MatrizMath multiplicar(MatrizMath m){
		return MatrizMath.castVec(this).multiplicar(m);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dim;
		result = prime * result + Arrays.hashCode(vector);
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
		VectorMath other = (VectorMath) obj;
		if (dim != other.dim)
			return false;
		if (!Arrays.equals(vector, other.vector))
			return false;
		return true;
	}
	
	
}
