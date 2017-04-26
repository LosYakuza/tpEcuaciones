package vector;

import static org.junit.Assert.*;

import org.junit.Test;

public class PruebaMatrices {
	
	private MatrizMath getI(int dim){
		MatrizMath i = new MatrizMath(dim, dim);
		int j;
		for(j=0;j<dim;j++){
			i.cargarElemento(j, j, 1);
			
		}
		return i;
		
	}

	@Test
	public void test() {
				
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
		
		System.out.println(ans);
		System.out.println(m1.multiplicar(m1));
		//System.out.println(ans);
		/*por identidad*/
		assertEquals(true, m1.equals(m1.multiplicar(getI(4))));
		assertEquals(true, ans.equals(m1.multiplicar(m1)));

		
	}

}
