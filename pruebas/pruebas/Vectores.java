package pruebas;

import org.junit.*;
import algebra.VectorMath;

public class Vectores {

	@Test
	public void general() {
		VectorMath v = new VectorMath(3);
		VectorMath v1 = new VectorMath(3);

		v.agregarValor(0,1);
		v.agregarValor(1,2);
		v.agregarValor(2,3);

		v1.agregarValor(0,2);
		v1.agregarValor(1,2);
		v1.agregarValor(2,2);

		VectorMath v2 = v.multiplicar(v1);
		VectorMath v3 = new VectorMath(3);

		v3.agregarValor(0,2);
		v3.agregarValor(1,4);
		v3.agregarValor(2,6);

		v1 = v2.clone();

		Assert.assertEquals(v3.equals(v2), true);
		Assert.assertEquals(6.0, v3.normaInf(), 0);
		Assert.assertEquals(12.0, v3.normaUno(), 0);
		Assert.assertEquals(7.5, v3.normaDos(), 0.1);


	}

}
