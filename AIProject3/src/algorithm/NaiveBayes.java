package algorithm;

import objects.Cell;

public class NaiveBayes {
	private int[][][] likelihood;
	private int[] frequency, priors;

	public NaiveBayes(int[] quantity, int[] priors){
		likelihood = new int[10][28][28];
		this.frequency = quantity;
		this.priors = priors;
	}

	public void likelihood(Cell[][] image, double label_quantity, int k){
		double[][] empty_grid = new double[image.length][image.length];
		double[][] nonempty_grid = new double[image.length][image.length];

		//array is already in 0 and 1 form
		for(int digit = 0; digit < 10; digit++ ){
			for(int i = 0; i < 28; i++){
				for(int j = 0; j < 28; j++){
					if(image[i][j].getData() == 0){
						likelihood[digit][i][j]++;
						empty_grid[i][j] = likelihood[digit][i][j];
					}else{
						likelihood[digit][i][j]++;
						nonempty_grid[i][j] = likelihood[digit][i][j];
					}
				}
			}
		}

		for(int digit = 0; digit < 10; digit++ ){
			for(int i = 0; i < 28; i++){
				for(int j = 0; j < 28; j++){
					//check parentheses to see if they are ok
					double belief = (likelihood[digit][i][j] + k)/(frequency[i] + Math.pow(k, 2));
					if(image[i][j].getData() == 0){
						empty_grid[i][j] = belief;
					}else{
						nonempty_grid[i][j] = belief;
					}
				}
			}
		}
	}
}
