package modele;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.org.apache.bcel.internal.generic.DDIV;

public class Main {

	public static void main(String[] args) {
		String valeur = "a";
		boolean testLettres = Pattern.matches("[0-9]{1,}", valeur);
		System.out.println(testLettres);
		
		String valeurDeux = "2,3";
		String valeurTrois = "2.3";
		boolean testLettresDeux = Pattern.matches("[0-9]{1,2},[0-9]{1}", valeurDeux);
		boolean testLettresPoint = Pattern.matches("[0-9]{1,2}.[0-9]", valeurTrois);
		System.out.println("virgule : "+ testLettresDeux);
		System.out.println("point : " + testLettresPoint);
		
		Pattern pattern = Pattern.compile("([0-9]{1,2})(,)([0-9]{1})");
		Matcher matcher = pattern.matcher(valeurDeux);
		while (matcher.find()) {
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
			System.out.println(matcher.group(3));
		}

	}

}
