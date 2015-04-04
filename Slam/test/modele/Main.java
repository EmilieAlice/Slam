package modele;

import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.DDIV;

public class Main {

	public static void main(String[] args) {
		ArrayList<Double> test = new ArrayList<Double>();
		test.add(12.0);
		test.add(13.0);
		test.add(14.0);
		Note note = new Note();
		
		Double moyenne = new Double(0);
		
		moyenne = note.calculMoyenne(test);
		System.out.println(moyenne);
	}

}
