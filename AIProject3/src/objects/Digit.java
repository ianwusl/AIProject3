package objects;

import java.util.ArrayList;

import ImageExtraction.LoopDetection;

public class Digit extends TestObject {
	protected ArrayList<String> object_edges;
	public static double[] digit_arr = new double[10];
	public static double total;

	public Digit(int data) {
		super(data);
		object_edges = new ArrayList<>();
	}

	public char[][] edges() {
		char[][] object_2D = new char[object.size()][length];
		for(int i = 0; i < object.size(); i ++) {
			char[] s_arr = object.get(i).toCharArray();
			object_2D[i] = s_arr;
		}
		char[][] object_ret = new char[object.size()][length];
		for(int i = 0; i < object.size(); i ++) {
			for(int j = 0 ; j < length ; j++) {
				if(object_2D[i][j] == '+') {
					if(this.inbounds(i, j-1) && this.inbounds(i, j+1)) {
						if(object_2D[i][j-1] == '+' && object_2D[i][j+1] == ' ') {
							object_ret[i][j] = '#';
						}else if(object_2D[i][j-1] == ' ' && object_2D[i][j+1] == '+') {
							object_ret[i][j] = '#';
						}
					}
				}
				if(object_2D[i][j] == ' ' || object_2D[i][j] == '+') {
					object_ret[i][j] = ' ';
				}else {
					object_ret[i][j] = '1';
				}
			}
		}

		for(int i= 0; i<object.size(); i++) {
			char[] arr = object_ret[i];
			String s = new String(arr);
			if(s != null) {
				object_edges.add(s);
			}

		}

		return object_ret;
	}

	public boolean hasLoops() {

		char[][] obj = this.object2D();
		char[][] edges = this.edges();
		LoopDetection ld1 = new LoopDetection(edges);
		LoopDetection ld2 = new LoopDetection(obj);
		int i = ld1.loop_count();
		int j = ld2.loop_count();
		int k = i > j? i : j;
		if(k == 0){
			this.printObject();
		}
		return k > 0? true : false;

	}

	public static double[] getDigitArr(){
		double[] digits = new double[10];
		for(int i = 0 ; i < 10; i++){
			digits[i] = digit_arr[i];
			System.out.println(i + ": " + digits[i]);
		}
		System.out.println();
		return digits;
	}

	public static double[] getPrior(){
		double[] priors = new double[10];
		for(int i = 0 ; i < 10; i++){
			priors[i] = digit_arr[i]/total;
			System.out.println(i + ": " + priors[i]);
		}
		System.out.println();
		return priors;
	}
}
