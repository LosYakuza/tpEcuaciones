package pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import algebra.MatrizMath;
import algebra.VectorMath;
import sel.Sel;

import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Calendar;

public class Sels {

	/**
	 * Resuelve midiendo tiempo de ejecucion
	 * @param a
	 * @param b
	 * @return
	 */
	private double solveTime(MatrizMath a, VectorMath b){
		Calendar tIni = new GregorianCalendar();
		Sel sel = new Sel(a, b);
		sel.solve();
		Calendar tFin = new GregorianCalendar();
		return tFin.getTimeInMillis() - tIni.getTimeInMillis();
	}
	
	/**
	 * Genera matrices aleatorias 
	 * incrementando dimension para generar metricas
	 */
	@Test
	public void performance() {
		Random r = new Random();
		MatrizMath a;
		VectorMath b;
		for (int size = 10; size <= 2000; size += 100) {
			a = new MatrizMath(size, size);
			b = new VectorMath(size);
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					a.cargarElemento(i, j, r.nextDouble() * 100);
				}
				b.agregarValor(i, r.nextDouble() * 100);
			}
			System.out.println(a.getFil() + "\t" + solveTime(a, b));
		}
	}

	/**
	 * Verifica error de solucion
	 */
	@Test
	public void errorCalculo() {
		Random r = new Random();
		MatrizMath a;
		VectorMath b;
		int size = 500;
		a = new MatrizMath(size, size);
		b = new VectorMath(size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				a.cargarElemento(i, j, r.nextDouble() * 100);
			}
			b.agregarValor(i, r.nextDouble() * 100);
		}
		Sel sel = new Sel(a, b);
		sel.solve();
		assertEquals(0, sel.errorCheck(), 1e-8);
	}

}
