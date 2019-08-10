package algorithm;

import objects.Cell;

public class NaiveBayes {
	private int[][][] likelihood_empty;
	private int[][][] likelihood_edge;
	private int[][][] likelihood_inner;
	private int[] frequency, priors;

	public NaiveBayes(int[] quantity, int[] priors){
		likelihood_empty = new int[10][28][28];
		likelihood_edge = new int[10][28][28];
		likelihood_inner = new int[10][28][28];
		this.frequency = quantity;
		this.priors = priors;
	}

	public void likelihood(Cell[][] image, double label_quantity){
		int k = 1;
		double[][] empty_grid = new double[image.length][image.length];
		double[][] inner_grid = new double[image.length][image.length];
		double[][] edge_grid = new double[image.length][image.length];

		//array is in form 0 = ' ', 1 = '+', 2 = '#'
		for(int digit = 0; digit < 10; digit++ ){
			for(int i = 0; i < 28; i++){
				for(int j = 0; j < 28; j++){
					if(image[i][j].getData() == 0){
						likelihood_empty[digit][i][j]++;
						empty_grid[i][j] = likelihood_empty[digit][i][j];
					}else if(image[i][j].getData() == 1){
						likelihood_edge[digit][i][j]++;
						edge_grid[i][j] = likelihood_edge[digit][i][j];
					}else{
						likelihood_inner[digit][i][j]++;
						inner_grid[i][j] = likelihood_inner[digit][i][j];
					}
				}
			}
		}

		for(int digit = 0; digit < 10; digit++ ){
			for(int i = 0; i < 28; i++){
				for(int j = 0; j < 28; j++){
					//' '
					if(image[i][j].getData() == 0){
						double belief = (likelihood_empty[digit][i][j] + k)/(frequency[i] + Math.pow(k, 2));
						empty_grid[i][j] = belief;
						//'+'
					}else if(image[i][j].getData() == 1){
						double belief = (likelihood_edge[digit][i][j] + k)/(frequency[i] + Math.pow(k, 2));
						edge_grid[i][j] = belief;
					}else{
						//'#'
						double belief = (likelihood_inner[digit][i][j] + k)/(frequency[i] + Math.pow(k, 2));
						inner_grid[i][j] = belief;
					}
				}
			}
		}
	}
}
