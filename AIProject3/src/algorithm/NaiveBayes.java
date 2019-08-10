package algorithm;

import java.util.ArrayList;

import objects.Cell;
import objects.Digit;

public class NaiveBayes {
	private double[][][] likelihood;

	private double[] frequency, priors;

	public NaiveBayes(double[] quantity, double[] priors, int k, ArrayList<Digit> digits){
		//instantiate
		likelihood = new double[10][28][28];

		this.frequency = quantity;
		this.priors = priors;

		//get total likelihoods
		for(int i = 0 ; i < digits.size(); i++){
			Digit d = digits.get(i);
			Cell[][] cells = d.getCellArray();
			for(int j = 0 ; j < cells.length; j++){
				for(k = 0 ; k < cells.length; k++){
					if(cells[j][k].getData() != 0){
						likelihood[d.data][j][k]++;
					}
				}
			}
		}

		//laplace smoothing
		for(int digit = 0; digit < 10; digit++ ){
			for(int i = 0; i < 28; i++){
				for(int j = 0; j < 28; j++){
					likelihood[digit][i][j] = (likelihood[digit][i][j] + k)/(frequency[digit]*k*2);
				}
			}
		}
	}

	public boolean testDigit(Digit d, int k){
		Cell[][] img = d.getCellArray();
		double[] probability = new double[10];
		
		//transfer priors to new array
		for(int i = 0 ; i < 10 ; i++){
			probability[i] = priors[i];
		}

		for(double p : probability){
			p = Math.log(p);
		}

		//need help here
		for(int digit = 0; digit < 10; digit++ ){
			for(int i = 0; i < 28; i++){
				for(int j = 0; j < 28; j++){
					if(img[i][j].getData()!=0){
						probability[digit] += Math.log((likelihood[digit][i][j] + k /frequency[digit])) ;
					}
				}
			}
		}

		//choose the best p
		int best_p = 0;
		for(int i = 1; i < 10; i++){
			if(probability[best_p]<probability[i]){
				best_p = i;
			}
		}
		d.assign_value(best_p);
		return d.checkData();
	}
}
