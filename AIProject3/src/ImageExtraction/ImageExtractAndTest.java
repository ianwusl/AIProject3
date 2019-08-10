package ImageExtraction;
import java.io.IOException;
import java.util.ArrayList;

import objects.Cell;
import objects.Digit;

/*
 * Author : Charmian Goh
 */
public class ImageExtractAndTest {

	public static void main(String[] args) throws IOException{
		ImageExtraction extract = new ImageExtraction();
		ArrayList<Digit> digits = extract.digitExtract(ImageExtraction.Type.DIGIT_TRAIN);
		double[] quantity = Digit.getDigitArr();
		double[] priors = Digit.getPrior();

		//test cells;
		Cell[][] cells = digits.get(0).getCellArray();
		digits.get(0).printCellArray();

	}

}
