package pl.examples;

import pl.Computation.*;
import pl.core.*;


public class HornClausesKB extends KB{


    public HornClausesKB() {
        super();
        Symbol m = intern("Mythical");
        Symbol i = intern("Immortal");
        Symbol h = intern("Horned");
        Symbol g = intern("Magical");
        Symbol ma = intern("Mammal");
        add(new Implication(m, i));
        add(new Implication(new Negation(m), new Conjunction(new Negation(i), ma)));
        add(new Implication(new Disjunction(i, ma), h));
        add(new Implication(h, g));
    }



    public static void main(String[] args) {
        HornClausesKB hornClausesKB =  new HornClausesKB();
        hornClausesKB.dump();
        System.out.println("\nMODEL CHECKING");
        System.out.println("a. Can we prove that the unicorn is mythical?");
        System.out.println("Mythical ----- " + ModelChecking.Entails(hornClausesKB, new Symbol("Mythical")) + "\n");

        System.out.println("b. Can we prove that the unicorn is magical?");
        System.out.println("Magical ----- " + ModelChecking.Entails(hornClausesKB, new Symbol("Magical")) + "\n");

        System.out.println("c. Can we prove that the unicorn is horned?");
        System.out.println("Horned ----- " + ModelChecking.Entails(hornClausesKB, new Symbol("Horned")) + "\n");


        hornClausesKB = new HornClausesKB();

        System.out.println("RESOLUTION");
        System.out.println("a.Can we prove that the unicorn is mythical?");
        System.out.println("Mythical ----- " + Resolution.Resolution(hornClausesKB, new Symbol("Mythical")) + "\n");

        System.out.println("b. Can we prove that the unicorn is magical?");
        System.out.println("Magical -----  " + Resolution.Resolution(hornClausesKB, new Symbol("Magical")) + "\n");

        System.out.println("c. Can we prove that the unicorn is horned?");
        System.out.println("Horned -----  " + Resolution.Resolution(hornClausesKB, new Symbol("Horned")) + "\n");
    }
}
