package sel;

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

	public MatrizMath solve() {
		this.s = this.a.inversa().multiplicar(this.b);
		return s;
	}

	public double errorCheck() {
		return this.a.multiplicar(this.s).restar(MatrizMath.castVec(this.b)).det();
	}
	
}
