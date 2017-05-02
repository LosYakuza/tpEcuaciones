package sel;

import algebra.MatrizMath;
import algebra.VectorMath;

public class Sel {
	private MatrizMath a;
	private VectorMath b;
	private MatrizMath s;

	public Sel(MatrizMath a, VectorMath b) {
		super();
		this.a = a;
		this.b = b;
	}

	/**
	 * Resuelve sistema
	 * 
	 * @return
	 */
	public MatrizMath resolver() {
		try{
			this.s = this.a.inversa().multiplicar(this.b);
		}catch (Exception e) {
			return null;
		}
		return s;
	}

	/**
	 * Devuelve matriz solucion
	 * 
	 * @return
	 */
	public MatrizMath getSolucion() {
		return this.s;
	}

	/**
	 * Calcula el error cometido
	 * 
	 * @return
	 */
	public double getError() {
		return this.a.multiplicar(this.s).restar(MatrizMath.castVec(this.b)).normaDos();
	}

}
