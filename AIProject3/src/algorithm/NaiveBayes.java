package algorithm;

import java.text.DecimalFormat;
import java.util.ArrayList;

import objects.Cell;
import objects.Digit;

public class NaiveBayes {
	private double[][][] likelihood;

	private double[] frequency, priors;
	private ArrayList<Digit> digits;

	public NaiveBayes(double[] quantity, double[] priors, int k, ArrayList<Digit> digits){
		//instantiate
		likelihood = new double[10][28][28];

		this.frequency = quantity;
		this.priors = priors;
		this.digits = digits;

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

		//testing
		/*
		DecimalFormat df = new DecimalFormat("###.##");
		for(int i = 0; i < 28; i++){
			for(int j = 0; j < 28; j++){
				System.out.print(df.format(likelihood[0][i][j]) + " ");
			}
			System.out.println();
		}*/
	}

}
