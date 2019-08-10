package algorithm;

import java.text.DecimalFormat;
import java.util.ArrayList;

import objects.Cell;
import objects.TestObject;

public class NaiveBayes {
	private double[][][] likelihood;

	private double[] frequency, priors;
	private int dim, data_size;

	public enum Type{
		FACE, DIGIT
	}

	public NaiveBayes(double[] quantity, double[] priors, int k, ArrayList<TestObject> digits, Type type, int repeat){
		dim = 0;
		data_size = 0;

		switch(type){
			case DIGIT:
				dim = 28;
				data_size = 10;
				break;
			case FACE:
				dim = 70;
				data_size = 2;

		}
		//instantiate
		likelihood = new double[10][dim][dim];

		this.frequency = quantity;
		this.priors = priors;

		//run number of time to increase accuracy
		for(int a = 0; a < repeat; a++){
			//get total likelihoods
			for(int i = 0 ; i < digits.size(); i++){
				TestObject d = digits.get(i);
				Cell[][] cells = d.getCellArray();
				for(int j = 0 ; j < cells.length; j++){
					for(k = 0 ; k < cells.length; k++){
						if(cells[j][k].getData() != 0){
							likelihood[d.data][j][k]++;
						}
					}
				}
			}

		}

		//laplace smoothing
		for(int digit = 0; digit < data_size; digit++ ){
			for(int i = 0; i < dim; i++){
				for(int j = 0; j < dim; j++){
					likelihood[digit][i][j] = (likelihood[digit][i][j] + k)/(frequency[digit]*k*2);

				}
			}
		}
	}

	public void printLikelihood(int data){
		DecimalFormat df = new DecimalFormat("#.##");
		if(data <= data_size){
			for(int i = 0; i < dim ; i++){
				for(int j = 0; j < dim ; j++){
					String s = df.format(likelihood[data][i][j]) + "";
					if(s.equals("0")){
						s = "0.00";
					}
					System.out.print(s + " ");
				}
				System.out.println();
			}
		}
	}


	public boolean test(TestObject d, int k){
		Cell[][] img = d.getCellArray();
		double[] probability = new double[10];

		//transfer priors to new array
		for(int i = 0 ; i < data_size ; i++){
			probability[i] = priors[i];
		}

		for(double p : probability){
			p = Math.log(p);
		}

		//need help here
		for(int digit = 0; digit < data_size; digit++ ){
			for(int i = 0; i < img.length; i++){
				for(int j = 0; j < img.length; j++){
					if(img[i][j].getData()!=0){
						probability[digit] += Math.log((likelihood[digit][i][j] + k /frequency[digit]*k*2)) ;
					}
				}
			}
		}

		//choose the best p
		int best_p = 0;
		for(int i = 1; i < data_size; i++){
			if(probability[best_p]<probability[i]){
				best_p = i;
			}
		}

		d.assign_value(best_p);
		return d.checkData();
	}
}
