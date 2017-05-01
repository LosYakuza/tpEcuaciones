package sel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import algebra.MatrizMath;
import algebra.VectorMath;

public abstract class SelFile {

	public static void guardar(Sel sel,String path){
		FileWriter fstream;
		int i;
		MatrizMath s = sel.getSolve();
		try {
			fstream = new FileWriter(path, true);
	        BufferedWriter out = new BufferedWriter(fstream);
	        out.write(String.valueOf(s.getFil()));
	        out.newLine();
	        for(i=0;i<s.getFil(); i++){
	        	out.write(String.valueOf(s.leerElemento(i, 0)));
	        	out.newLine();
	        }
	        out.newLine();
	        out.write(String.valueOf(sel.errorCheck()));
	        out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static Sel cargar(String path){
		MatrizMath a;
		VectorMath b;
		int size,i;
		String line[];
		File fp = new File(path);
		try {

	        Scanner sc = new Scanner(fp);
	        size = sc.nextInt();
	        sc.nextLine();
	        a = new MatrizMath(size, size);
	        b = new VectorMath(size);
	        i=0;
	        while (sc.hasNextLine() && i < (size*size)) {
	        	line = sc.nextLine().split(" ");
        	
	        	a.cargarElemento(Integer.parseInt(line[0]),
	        			Integer.parseInt(line[1]),
	        			Double.parseDouble(line[2]));
	        	i++;
	        }
	        i=0;
	        while (sc.hasNextLine() && i<size) {
	        	b.agregarValor(i, Double.parseDouble(sc.nextLine()));
	        	i++;
	        }
	        sc.close();
	        return new Sel(a, b);
	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	    }
		return null;
	}
}
