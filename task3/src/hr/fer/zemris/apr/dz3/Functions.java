package hr.fer.zemris.apr.dz3;

import static java.lang.Math.pow;

import hr.fer.zemris.apr.dz2.Function;

public class Functions {

	public static final Function f1 = (x) -> 100 * pow(x[1] - pow(x[0], 2), 2) + 1 * pow(1 - x[0], 2);
	
	public static final Function grad_f1_x1 = (x) -> 2 * (200 * pow(x[0], 3) - 200 * x[0] * x[1] + x[0] - 1);
	
	public static final Function grad_f1_x2 = (x) -> 200 * (x[1] - pow(x[0], 2));
	
	public static final Function grad_f1_x11 = (x) -> 2 * (600 * pow(x[0], 2) - 200 + 1);
	
	public static final Function grad_f1_x12 = (x) -> -400 * x[0];
	
	public static final Function grad_f1_x21 = (x) -> -400 * x[0];
	
	public static final Function grad_f1_x22 = (x) -> 200;
	
	
	
	public static final Function f2 = (x) -> pow(x[0] - 4, 2) + 4 * pow(x[1] - 2, 2);
	
	public static final Function grad_f2_x1 = (x) -> 2 * (x[0] - 4);
	
	public static final Function grad_f2_x2 = (x) -> 8 * (x[1] - 2);
	
	public static final Function grad_f2_x11 = (x) -> 2;
	
	public static final Function grad_f2_x12 = (x) -> 0;
	
	public static final Function grad_f2_x21 = (x) -> 0;
	
	public static final Function grad_f2_x22 = (x) -> 8;
	
	
	
	public static final Function f3 = (x) -> pow(x[0] - 2, 2) + pow(x[1] + 3, 2);
	
	public static final Function grad_f3_x1 = (x) -> 2 * (x[0] - 2);
	
	public static final Function grad_f3_x2 = (x) -> 2 * (x[1] + 3);
	
	public static final Function grad_f3_x11 = (x) -> 2;
	
	public static final Function grad_f3_x12 = (x) -> 0;
	
	public static final Function grad_f3_x21 = (x) -> 0;
	
	public static final Function grad_f3_x22 = (x) -> 2;
	
	
	
	public static final Function f4 = (x) -> pow(x[0] - 3, 2) + pow(x[1], 2);
	
	public static final Function grad_f4_x1 = (x) -> 2 * (x[0] - 3);
	
	public static final Function grad_f4_x2 = (x) -> 2 * x[1];
	
	public static final Function grad_f4_x11 = (x) -> 2;
	
	public static final Function grad_f4_x12 = (x) -> 0;
	
	public static final Function grad_f4_x21 = (x) -> 0;
	
	public static final Function grad_f4_x22 = (x) -> 2;
}
