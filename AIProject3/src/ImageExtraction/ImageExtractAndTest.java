package ImageExtraction;
import java.io.IOException;
import java.util.ArrayList;

import algorithm.NaiveBayes;
import objects.Cell;
import objects.Digit;

/*
 * Author : Charmian Goh
 */
public class ImageExtractAndTest {

	public static void main(String[] args) throws IOException{
		ImageExtraction extract = new ImageExtraction();
		ArrayList<Digit> digits = extract.digitExtract(ImageExtraction.Type.DIGIT_TRAIN, new double[10], 0);
		NaiveBayes nb = new NaiveBayes(extract.getDigit_frequency(),
				Digit.getPrior(extract.getDigit_frequency(), extract.getTotal()),1, digits);

		ArrayList<Digit> digits_test = extract.digitExtract(ImageExtraction.Type.DIGIT_TEST, new double[10], 0);
		int matched = 0;
		for(int i = 0;  i < digits_test.size(); i++){
			boolean match = nb.testDigit(digits.get(i), 1);
			if(match){
				matched++;
			}
		}
		System.out.println(matched);
		System.out.println(digits_test.size());
		System.out.println("accuracy: " + (matched*1.0)/digits_test.size());
	}

}
