package pruebas;

import org.junit.*;
import algebra.VectorMath;

public class Vectores {

	@Test
	public void general() {
		VectorMath v = new VectorMath(3);
		VectorMath v1 = new VectorMath(3);

		v.cargarElemento(0,1);
		v.cargarElemento(1,2);
		v.cargarElemento(2,3);

		v1.cargarElemento(0,2);
		v1.cargarElemento(1,2);
		v1.cargarElemento(2,2);

		VectorMath v2 = v.multiplicar(v1);
		VectorMath v3 = new VectorMath(3);

		v3.cargarElemento(0,2);
		v3.cargarElemento(1,4);
		v3.cargarElemento(2,6);

		v1 = v2.clone();

		Assert.assertEquals(v3.equals(v2), true);
		Assert.assertEquals(6.0, v3.normaInfinito(), 0);
		Assert.assertEquals(12.0, v3.normaUno(), 0);
		Assert.assertEquals(7.5, v3.normaDos(), 0.1);


	}

}
