package algorithm;

import java.util.ArrayList;

import objects.Cell;
import objects.Digit;

public class NaiveBayes {
	private double[][][] likelihood_empty;
	private double[][][] likelihood_edge;
	private double[][][] likelihood_inner;
	private double[] frequency, priors;
	private ArrayList<Digit> digits;

	public NaiveBayes(double[] quantity, double[] priors, int k, ArrayList<Digit> digits){
		//instantiate
		likelihood_empty = new double[10][28][28];
		likelihood_edge = new double[10][28][28];
		likelihood_inner = new double[10][28][28];
		this.frequency = quantity;
		this.priors = priors;
		this.digits = digits;

		//get total likelihoods
		for(int i = 0 ; i < digits.size(); i++){
			Digit d = digits.get(i);
			Cell[][] cells = d.getCellArray();
			for(int j = 0 ; j < cells.length; j++){
				for(k = 0 ; k < cells.length; k++){
					switch(cells[j][k].getData()){
						case 0:
							likelihood_empty[d.data][i][j]++;
							break;
						case 1:
							likelihood_edge[d.data][i][j]++;
							break;
						case 2:
							likelihood_inner[d.data][i][j]++;
							break;
					}
				}
			}
		}

		//laplace smoothing
		for(int digit = 0; digit < 10; digit++ ){
			for(int i = 0; i < 28; i++){
				for(int j = 0; j < 28; j++){
					likelihood_empty[digit][i][j] = (likelihood_empty[digit][i][j] + k)/(frequency[digit]*k*3);
					likelihood_edge[digit][i][j] = (likelihood_edge[digit][i][j] + k)/(frequency[digit]*k*3);
					likelihood_inner[digit][i][j] = (likelihood_inner[digit][i][j] + k)/(frequency[digit]*k*3);
				}
			}
		}
	}

}
