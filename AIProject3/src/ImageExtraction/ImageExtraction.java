package ImageExtraction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import objects.Digit;
import objects.Face;

public class ImageExtraction {
		private double[] digit_frequency;
		private double total;


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

		public enum Type{
			FACE_TEST, FACE_VALID, FACE_TRAIN, DIGIT_TRAIN, DIGIT_VALID, DIGIT_TEST
		}

		public ArrayList<Digit> digitExtract(Type type, double[] digit_arr, double total) throws IOException{
			ArrayList<Digit> digits = new ArrayList<>();
			String dir1, dir2;
			switch(type){
			case DIGIT_TEST:
				dir1 = img1_test_dir;
				dir2 = img2_test_dir;
				break;
			case DIGIT_TRAIN:
				dir1 = img1_train_dir;
				dir2 = img2_train_dir;
				break;
			case DIGIT_VALID:
				dir1 = img1_valid_dir;
				dir2 = img2_valid_dir;
				break;
			default:
				dir1 = img1_train_dir;
				dir2 = img2_train_dir;
			}

			/*
			 * Get data files
			 */

			File image_file = new File(dir1);
			File label_file = new File(dir2);

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
				Digit t = new Digit(int_data);
				total++;
				digit_arr[int_data]++;
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
				digits.add(t);
			}


			/*
			 * Close all readers
			 */
			image_br.close();
			data_br.close();
			image_fr.close();
			data_fr.close();
			this.setDigit_frequency(digit_arr);
			this.setTotal(total);
			return digits;
		}

		public ArrayList<Face> faceExtract(Type type) throws IOException{
			ArrayList<Face> face = new ArrayList<>();
			String dir1, dir2;
			switch(type){
			case FACE_TEST:
				dir1 = face1_test_dir;
				dir2 = face2_test_dir;
				break;
			case FACE_TRAIN:
				dir1 = face1_train_dir;
				dir2 = face2_train_dir;
				break;
			case FACE_VALID:
				dir1 = face1_valid_dir;
				dir2 = face2_valid_dir;
				break;
			default:
				dir1 = face1_train_dir;
				dir2 = face2_train_dir;
			}

			/*
			 * Get data files
			 */

			File image_file = new File(dir1);
			File label_file = new File(dir2);

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
				Face t = new Face(int_data);
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
				face.add(t);
			}


			/*
			 * Close all readers
			 */
			image_br.close();
			data_br.close();
			image_fr.close();
			data_fr.close();

			return face;
		}

		public double[] getDigit_frequency() {
			return digit_frequency;
		}

		public void setDigit_frequency(double[] digit_frequency) {
			this.digit_frequency = digit_frequency;
		}

		public double getTotal() {
			return total;
		}

		public void setTotal(double total) {
			this.total = total;
		}



}
