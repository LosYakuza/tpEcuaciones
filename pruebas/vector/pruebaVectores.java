package vector;

import org.junit.*;


public class pruebaVectores {

	@Test
	public void test() {
		VectorMath v = new VectorMath(3);
		VectorMath v1 = new VectorMath(3);
		

			v.agregarValor(1);
			v.agregarValor(2);
			v.agregarValor(3);

			
			
			

		v1.agregarValor(2);
		v1.agregarValor(2);
		v1.agregarValor(2);
		//v1.agregarValor(2);
		
		v.mostrarVec();
		v1.mostrarVec();
		
		VectorMath v2 = v.multiplicar(v1);
		VectorMath v3 = new VectorMath(3);
		
		v3.agregarValor(2);
		v3.agregarValor(4);
		v3.agregarValor(6);
		
		System.out.println("multiplicacion:");
		v2.mostrarVec();
		System.out.println("clonado");
		//v1 = v2.clone();
		
				
		Assert.assertEquals(v3.equals(v2), true);
		Assert.assertEquals(6.0, v3.normaInf(), 0);
		Assert.assertEquals(12.0, v3.normaUno(), 0);
		Assert.assertEquals(7.5, v3.normaDos(), 0.1);
		
		System.out.println("multiplicado por un escalar");
		v1.multiplicar(3).sumar(v2).mostrarVec();
		
		 MatrizMath m = new MatrizMath(2,2);
		 

	}

}
