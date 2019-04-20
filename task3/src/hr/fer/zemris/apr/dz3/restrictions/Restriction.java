package hr.fer.zemris.apr.dz3.restrictions;

import hr.fer.zemris.apr.dz2.Function;
import hr.fer.zemris.apr.dz2.Point;

public interface Restriction extends Function {
	
	boolean test(Point x);
}
