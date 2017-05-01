package pruebas;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import algebra.MatrizMath;
import algebra.VectorMath;

public class Matrices {

	/**
	 * Genera matriz identidad
	 * @param dim
	 * @return
	 */
	private MatrizMath getI(int dim) {
		MatrizMath i = new MatrizMath(dim, dim);
		int j;
		for (j = 0; j < dim; j++) {
			i.cargarElemento(j, j, 1);

		}
		return i;

	}
	
	@Test
	public void ejemploTp(){
		MatrizMath tpT = new MatrizMath(3, 3);
		tpT.cargarElemento(0, 0, 2);
		tpT.cargarElemento(0, 1, 9);
		tpT.cargarElemento(0, 2, 9);
		tpT.cargarElemento(1, 0, 3);
		tpT.cargarElemento(1, 1, 9);
		tpT.cargarElemento(1, 2, 2);
		tpT.cargarElemento(2, 0, 1);
		tpT.cargarElemento(2, 1, 0);
		tpT.cargarElemento(2, 2, 1);

		VectorMath b = new VectorMath(3);
		b.cargarElemento(0,0);
		b.cargarElemento(1,1);
		b.cargarElemento(2,2);
		tpT = tpT.inversa().multiplicar(b);
		
		MatrizMath res = new MatrizMath(3, 1);
		res.cargarDesdeStrOctave("[1.875;-0.5416666666666667;0.125]");
		assertEquals(true,res.equals(tpT));
	}
	

	@Test
	public void rendimientoInversa(){
		int size = 1000;
		Random r = new Random();
		MatrizMath mi = new MatrizMath(size, size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				mi.cargarElemento(i, j, r.nextDouble() * 100);
			}
		}
		assertEquals(true, getI(size).equals(mi.multiplicar(mi.inversa())));
	}
	
	
	@Test
	public void errorInversa(){
		int size = 500;
		Random r = new Random();
		MatrizMath mi = new MatrizMath(size, size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				mi.cargarElemento(i, j, r.nextDouble() * 100);
			}
		}
		assertEquals(true, 
				mi.multiplicar(mi.inversa()).restar(getI(size)).det() < 1e-8);
	}
	
	@Test
	public void multiplicacion() {

		// -------test Ejemplo ------
		MatrizMath m1 = new MatrizMath(4, 4);
		MatrizMath ans = new MatrizMath(4, 4);

		m1.cargarElemento(0, 0, 3);
		m1.cargarElemento(0, 1, 6.5);
		m1.cargarElemento(0, 2, 3.2);
		m1.cargarElemento(0, 3, 1);
		m1.cargarElemento(1, 1, 15);
		m1.cargarElemento(1, 2, 56);
		m1.cargarElemento(1, 3, 1);
		m1.cargarElemento(1, 1, 15);
		m1.cargarElemento(2, 3, 56);
		m1.cargarElemento(3, 3, 1);

		ans.cargarElemento(0, 0, 9);
		ans.cargarElemento(0, 1, 117);
		ans.cargarElemento(0, 2, 373.6);
		ans.cargarElemento(0, 3, 189.7);
		ans.cargarElemento(1, 1, 225);
		ans.cargarElemento(1, 2, 840);
		ans.cargarElemento(1, 3, 3152);
		ans.cargarElemento(2, 3, 56);
		ans.cargarElemento(3, 3, 1);


		assertEquals(true, m1.equals(m1.multiplicar(getI(4))));
		assertEquals(true, ans.equals(m1.multiplicar(m1)));

		

	}

}
