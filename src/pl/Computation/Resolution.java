/**
 *
 *Taken from AIMA Figure 7.12 as adviced
 */

package pl.Computation;

import java.util.*;
import pl.core.*;
import pl.cnf.*;

public class Resolution {







	public static boolean Resolution(KB kb, Sentence alpha) {
		KB kbCopy = kb.copyKB();
		kbCopy.add(new Negation(alpha));
		return ResolutionHelper(kbCopy);
	}


	public static Set<Clause> Resolve(Clause ci, Clause cj) {  //As the book explains this return the set of possible clauses
		Clause clause_i = ci.copyClause();
		Clause clause_j = cj.copyClause();
		Set<Clause> resolve = new HashSet<>();
		for(Literal literal_i : clause_i) {
			for(Literal literal_j : clause_j) {
				if(literal_i.getContent().equals(literal_j.getContent()) && literal_i.getPolarity() != literal_j.getPolarity()) {
					clause_i.remove(literal_i);
					clause_j.remove(literal_j);

					clause_i.addAll(clause_j);
					resolve.add(clause_i);
					return resolve;
				}
			}
		}
		resolve.add(clause_i);
		resolve.add(clause_j);
		return resolve;
	}

	public static boolean ResolutionHelper(KB kbCopy){
		Set<Clause> clauses = CNFConverter.convert(kbCopy);
		Set<Clause> newclauses;
		Set<Clause> newClauses = new HashSet<>();
		while(true) {
			for(Clause clause_i : clauses) {
				for(Clause clause_j : clauses) {
					if(!clause_i.equals(clause_j)) {
						newclauses = Resolve(clause_i, clause_j);
						for(Clause currentClause : newclauses) {
							if(currentClause.isEmpty())
								return true;
						}
						newClauses.addAll(newclauses);
					}
				}
			}
			if(clauses.containsAll(newClauses))
				return false;
			clauses.addAll(newClauses);
		}
	}
}
