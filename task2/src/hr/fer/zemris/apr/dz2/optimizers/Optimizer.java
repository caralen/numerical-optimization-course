package hr.fer.zemris.apr.dz2.optimizers;

import hr.fer.zemris.apr.dz2.Point;

public interface Optimizer {

	public Point getResult();
	public Optimizer optimize();
}
