/**
 *Idea taken directly from AIMA Figure 7.10
 * I just added Arraylist as suggested by Ferguson
 *
 * Andrew Nyaisonga
 */

package pl.Computation;

import java.util.*;
import pl.core.*;

public class ModelChecking {
	

	
	public static boolean Entails(KB kb, Sentence alpha) {
		ArrayList<Symbol> fromKB = new ArrayList<>(kb.symbols());
		ArrayList<Symbol> fromAlpha = alpha.getSymbols();
		for(Symbol s : fromAlpha) {
			if(!fromKB.contains(s)) {
				fromKB.add(s);
			}
		}
		ModelC model = new ModelC();
		return CheckAllPossiblities(kb, alpha, fromKB, model);
	}

	public static boolean CheckAllPossiblities(KB kb, Sentence alpha, ArrayList<Symbol> symbols, ModelC model) {
		if(symbols.isEmpty()) {
			if(model.satisfies(kb))
				return model.satisfies(alpha);  //If it's true then check for alpha
			else
				return true;
		}
		else {
			ArrayList<Symbol> symbols2 = new ArrayList<>(symbols);
			Symbol p = symbols.remove(0);
			ModelC m1 = model.copyModel();
			m1.set(p, true);
			ModelC m2 = model.copyModel();
			p = symbols2.remove(0);
			m2.set(p, false);
			return CheckAllPossiblities(kb, alpha, symbols, m1) && CheckAllPossiblities(kb, alpha, symbols2, m2);
		}
	}
}
