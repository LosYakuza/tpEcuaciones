package sel;

import java.io.File;
import java.util.Scanner;

import algebra.MatrizMath;
import algebra.VectorMath;

public class Sel {
	MatrizMath a;
	VectorMath b;
	MatrizMath s;

	public Sel(MatrizMath a, VectorMath b) {
		super();
		this.a = a;
		this.b = b;
	}

	/**
	 * Resuelve sistema
	 * @return
	 */
	public MatrizMath solve() {
		this.s = this.a.inversa().multiplicar(this.b);
		return s;
	}
	
	/**
	 * Devuelve matriz solucion
	 * @return
	 */
	public MatrizMath getSolve(){
		return this.s;
	}

	
	/**
	 * Calcula el error cometido
	 * @return
	 */
	public double errorCheck() {
		try{
			return this.a.multiplicar(this.s).restar(MatrizMath.castVec(this.b)).det();
		}catch (RuntimeException e) {
			return 0;
		}
	}
	
}
