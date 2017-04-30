package vector;

import java.util.Arrays;

public class VectorMath {

	private double vector[];
	private int dim;
	private int agregados;

	public VectorMath(int dim) {
		this.vector = new double[dim];
		this.dim = dim;
		this.agregados = 0;
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
	
	public void agregarValor(double valor) {

		if (this.agregados == this.dim)
			throw new DistDimException("Se supera la dimension establecida");

		this.vector[this.agregados] = valor;
		this.agregados++;

	}

	public void mostrarVec() {

		for (int i = 0; i < agregados; i++)
			System.out.println(this.vector[i]);
	}

	public VectorMath sumar(VectorMath obj) {

		if (this.dim != obj.dim)
			throw new DistDimException("vectores de distinta dimension");
 
		VectorMath aux = new VectorMath(this.dim);

		for (int i = 0; i < this.dim; i++)
			aux.agregarValor(this.vector[i] + obj.vector[i]);

		return aux;

	}
   
	public VectorMath restar(VectorMath obj) {

		if (this.dim != obj.dim)
			throw new DistDimException("vectores de distinta dimension");

		VectorMath aux = new VectorMath(this.dim);

		for (int i = 0; i < this.dim; i++)
			aux.agregarValor(this.vector[i] - obj.vector[i]);

		return aux;

	}
	
	
	public VectorMath multiplicar(double obj) {
		VectorMath aux = new VectorMath(this.dim);

		for (int i = 0; i < this.dim; i++)
			aux.agregarValor(this.vector[i] * obj);

		return aux;
	}
	
	public double normaUno() {
		double s =0;

		for (int i = 0; i < this.dim; i++)
			if(this.vector[i]>0)
				s+= this.vector[i];
			else
				s+= this.vector[i]*(-1);

		return s;
	}
	
	public double normaDos() {
		double s =0;

		for (int i = 0; i < this.dim; i++)
			s+= Math.pow(this.vector[i], 2);

		return Math.sqrt(s);
	}
	
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
	
	public VectorMath multiplicar(VectorMath obj) {

		if (this.dim != obj.dim)
			throw new DistDimException("vectores de distinta dimension");

		VectorMath aux = new VectorMath(this.dim);

		for (int i = 0; i < this.dim; i++)
			aux.agregarValor(this.vector[i] * obj.vector[i]);

		return aux;

	}
	public VectorMath clone() {
		VectorMath v = new VectorMath(this.dim);

		for (int i = 0; i < this.agregados; i++)
			v.agregarValor(this.vector[i]);

		return v;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + agregados;
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
		if (agregados != other.agregados)
			return false;
		if (dim != other.dim)
			return false;
		if (!Arrays.equals(vector, other.vector))
			return false;
		return true;
	}

	public MatrizMath multiplicar(MatrizMath m){
		return MatrizMath.castVec(this).multiplicar(m);
	}
}
