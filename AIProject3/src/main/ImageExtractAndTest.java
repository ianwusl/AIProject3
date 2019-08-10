package main;
import java.io.IOException;
import java.util.ArrayList;

import ImageExtraction.ImageExtraction;
import ImageExtraction.ImageExtraction.Type;
import algorithm.NaiveBayes;
import objects.TestObject;

/*
 * Author : Charmian Goh
 */
public class ImageExtractAndTest {

	public static void main(String[] args) throws IOException{
		//object to extract images from file
		ImageExtraction extract = new ImageExtraction();
		//extract training digits
		ArrayList<TestObject> sample_space = extract.imageExtract(ImageExtraction.Type.DIGIT_TRAIN);

		//instantiate naive bayes object with training sample
		double[] prior_p = getPrior(extract.getfrequency(), extract.getTotal());
		double[] frequency = extract.getfrequency();

		//params are: #occurence of each digit, prior probability of each digit, k, training data,
		//			  type of object testing, number of times training data is tested to increased
		//			  likelihood and accuracy.

		//get a subset of the sample space
		ArrayList<TestObject> subset1 = getSampleSet(0.5, sample_space);
		//ArrayList<TestObject> subset2 = getSampleSet(0.5, sample_space);
		NaiveBayes nb = new NaiveBayes(frequency,prior_p, 1, subset1, NaiveBayes.Type.DIGIT, 1000);

		//print out likelihood array
		//nb.printLikelihood(0);


		//extract image for testing
		ArrayList<TestObject> test = extract.imageExtract(ImageExtraction.Type.DIGIT_TEST);

		//checking accuracy
		int matched = 0;
		for(int i = 0;  i < test.size(); i++){
			boolean match = nb.test(test.get(i), 1);
			if(match){
				matched++;
			}
		}

		System.out.println(matched);
		System.out.println(test.size());
		System.out.println("accuracy: " + (matched*1.0)/test.size());


	}

	private static double[] getPrior(double[] frequency, double total) {
		double[] prior = new double[10];
		for(int i = 0; i < 10 ; i++){
			prior[i] = frequency[i]/total;
		}

		return prior;
	}

	private static ArrayList<TestObject> getSampleSet(double percentage, ArrayList<TestObject> sample_space){
		ArrayList<TestObject> sample_set = new ArrayList<>();

		if(percentage >= 0 && percentage <= 1){
			int sample_set_size = (int)(percentage*sample_space.size());
			for(int i = 0; i < sample_set_size ; i++){
				int random_index = (int)Math.random()*(sample_space.size()-1);
				TestObject obj = sample_space.remove(random_index);
				sample_set.add(obj);
			}
			return sample_set;
		}else{
			return null;
		}
	}

}
