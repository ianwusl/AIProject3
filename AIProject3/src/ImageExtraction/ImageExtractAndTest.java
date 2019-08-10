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
				Digit.getPrior(extract.getDigit_frequency(), extract.getTotal()), 1, digits);

	}

}
