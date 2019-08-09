package ImageExtraction;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import objects.Digit;
import objects.Face;
import objects.TestObject;

/*
 * Author : Charmian Goh
 */
public class ImageExtractAndTest {

	//number image class paths
	private static final String img1_test_dir = "src/data/digitdata/testimages";
	private static final String img2_test_dir = "src/data/digitdata/testlabels";
	private static final String img1_train_dir = "src/data/digitdata/trainingimages";
	private static final String img2_train_dir = "src/data/digitdata/traininglabels";
	private static final String img1_valid_dir = "src/data/digitdata/validationimages";
	private static final String img2_valid_dir = "src/data/digitdata/validationlabels";

	//face image class paths
	private static final String face1_test_dir = "src/data/facedata/facedatatest";
	private static final String face2_test_dir = "src/data/facedata/facedatatestlabels";
	private static final String face1_train_dir = "src/data/facedata/facedatatrain";
	private static final String face2_train_dir = "src/data/facedata/facedatatrainlabels";
	private static final String face1_valid_dir = "src/data/facedata/facedatavalidation";
	private static final String face2_valid_dir = "src/data/facedata/facedatavalidationlabels";




	public static void main(String[] args) throws IOException {

		/*
		 * Get data files
		 */

		File image_file = new File(img1_train_dir);
		File label_file = new File(img2_train_dir);

		/*
		 * Create array to store objects
		 */
		ArrayList<TestObject> TO_arr = new ArrayList<>();


		/*
		 * Check that file exists
		 * Get files from file reader
		 */
		if(!image_file.exists() || !label_file.exists()) {
			System.out.println("Files do not exist.");
			System.exit(-1);
		}
		FileReader image_fr = new FileReader(image_file);
		FileReader data_fr = new FileReader(label_file);

		/*
		 * Use buffered reader to read from the file input stream (file reader)
		 */
		BufferedReader image_br = new BufferedReader(image_fr);
		BufferedReader data_br = new BufferedReader(data_fr);

		String data = "";

		/*
		 * Image extraction begins here
		 */
		while((data = data_br.readLine()) != null) {
			int int_data = Integer.parseInt(data);
			//TestObject t = new Face(int_data);
			TestObject t = new Digit(int_data);
			boolean start = false;
			String line = "";
			while((line = image_br.readLine()) != null) {
				if(!start) {
					if(line.trim().length() != 0) {
						start = true;
						t.addToObject(line);
					}
				}else {
					if(line.trim().length() == 0){
						if(t.isObject()) {
							break;
						}else {
							t.reset();
							start = false;
						}
					}else {
						t.addToObject(line);
					}
				}
			}

		}


		/*
		 * Close all readers
		 */
		image_br.close();
		data_br.close();
		image_fr.close();
		data_fr.close();



	}

}
