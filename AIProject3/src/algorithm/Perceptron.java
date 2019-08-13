package algorithm;

import java.util.ArrayList;

import ImageExtraction.ImageExtraction;
import objects.Cell;
import objects.Group;
import objects.TestObject;

public class Perceptron {
	private Type type;
	private int dim, data_size;
	private Group[] class_groups;
	private double[][][] weights;

	public enum Type{
		FACE, DIGIT
	}

	public Perceptron(ArrayList<TestObject> arr, Type type){
		//instantiate
		this.type = type;

		//set the dimensions of the array and the digit/face classes
		switch(this.type){
		case DIGIT:
			dim = 28;
			data_size = 10;
			break;
		case FACE:
			dim = 70;
			data_size = 2;
		}

		//number of subsets for object classes
		class_groups = new Group[data_size];

		//extract subsets
		for(int i = 0; i < data_size; i++){
			ArrayList<TestObject> group_arr = ImageExtraction.getSampleSet(1, arr, i);
			Group group = new Group(i, group_arr);
			class_groups[i] = group;
		}

		//get the weights
		weights = new double[data_size][dim][dim];

		//get total weight
		for(int i = 0 ; i < arr.size(); i++){
			//get the object in the training data
			TestObject d = arr.get(i);
			//make the object into a 2D cell array
			Cell[][] cells = d.getCellArray();
			//traverse through cell array for each cell
			for(int j = 0 ; j < cells.length; j++){
				for(int b = 0 ; b < cells.length; b++){
					if(cells[j][b].getData() != 0){
						//d.data is the type of object, increase count in cell[j][k] for d.data (digit class)
						weights[d.data][j][b]++;
					}
				}
			}
		}

		//get total weight
		for(int i = 0; i < data_size; i++){
			for(int j = 0; j < dim; j++){
				for(int k = 0 ; k < dim; k++){
					//divided by frequency of each digit class
					weights[i][j][k] /= class_groups[i].arr.size();
				}
			}
		}


	}



	public boolean test(TestObject d, int k){
		return false;
	}
}
