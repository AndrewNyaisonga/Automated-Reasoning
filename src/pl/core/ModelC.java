package pl.core;

import java.util.*;

public class ModelC implements Model{
	protected HashMap<Symbol,Boolean> map;

	public ModelC() {
		map = new HashMap<>();
	}
	
	@Override
	public void set(Symbol sym, boolean value) {
		if(map.containsKey(sym)) {
			map.remove(sym);
			map.put(sym, value);
		}
		else
			map.put(sym, value);
	}

	@Override
	public boolean get(Symbol sym) {
		if(map.containsKey(sym))
			return map.get(sym);
		else
			return false;
	}

	@Override
	public boolean satisfies(KB kb) {
		boolean temp = true;
		for(Sentence sentence : kb.sentences()) {
			temp = temp && sentence.isSatisfiedBy(this);
		}
		return temp;
	}

	@Override
	public boolean satisfies(Sentence sentence) {
		return sentence.isSatisfiedBy(this);
	}

	@Override
	public void dump() {
		Set<Symbol> set = map.keySet();
		for(Symbol sym : set) {
			System.out.println(sym + ": " + map.get(sym));
		}
	}
	
	public ModelC copyModel() {
		ModelC m = new ModelC();
		HashMap<Symbol, Boolean> map = new HashMap<Symbol, Boolean>();
		for(Symbol sym : this.getModel().keySet()) {
			map.put(sym, this.getModel().get(sym));
		}
		m.setModel(map);
		return m;
	}

	public HashMap<Symbol, Boolean> getModel() {
		return map;
	}

	public void setModel(HashMap<Symbol, Boolean> assignment) {
		this.map = assignment;
	}

}
