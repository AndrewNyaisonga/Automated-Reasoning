package pl.examples;

import pl.Computation.*;
import pl.core.Implication;
import pl.core.KB;
import pl.core.Sentence;
import pl.core.Symbol;


public class ModusPonensKB extends KB {
	
	public ModusPonensKB() {
		super();
		Symbol p = intern("P");
		Symbol q = intern("Q");
		add(p);
		add(new Implication(p, q));

	}



	
	public static void main(String[] argv) {

		ModusPonensKB modus = new ModusPonensKB();
        modus.dump();

        Symbol q = new Symbol("Q");
        Sentence s = q;
        System.out.println("\nModel Checking --------- Q is " + ModelChecking.Entails(modus, s) + "\n");

        /*Reset everything for reuse*/
        modus = new ModusPonensKB();
        q = new Symbol("Q");
        s = q;
        System.out.println("\nResolution ------------- Q is " + Resolution.Resolution(modus, s) + "\n");

	}

}
