package ImageExtraction;
import java.io.IOException;
import java.util.ArrayList;

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
		ArrayList<TestObject> digits = extract.imageExtract(ImageExtraction.Type.DIGIT_TRAIN);

		//instantiate naive bayes object with training sample
		NaiveBayes nb = new NaiveBayes(extract.getfrequency(),
				getPrior(extract.getfrequency(), extract.getTotal()), 1, digits, NaiveBayes.Type.DIGIT);

		//extract digits for testing
		ArrayList<TestObject> digits_test = extract.imageExtract(ImageExtraction.Type.DIGIT_TEST);

		//checking accuracy
		int matched = 0;
		for(int i = 0;  i < digits_test.size(); i++){
			boolean match = nb.test(digits.get(i), 1);
			if(match){
				matched++;
			}
		}

		System.out.println(matched);
		System.out.println(digits_test.size());
		System.out.println("accuracy: " + (matched*1.0)/digits_test.size());


	}

	private static double[] getPrior(double[] frequency, double total) {
		double[] prior = new double[10];
		for(int i = 0; i < 10 ; i++){
			prior[i] = frequency[i]/total;
		}

		return prior;
	}

}
