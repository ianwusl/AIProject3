package objects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TestObject {
	protected ArrayList<String> object;
	public int data;
	public int assigned_data;
	private static int count = 0;
	int length = 0;
	
	public TestObject(int data) {
		object = new ArrayList<>();
		this.data = data;
		count++;
	}
	
	public void addToObject(String s) {
		if(s.length() > length) {
			length = s.length();
		}
		object.add(s);
	}
	
	public void printObject() {
		for(String s: object) {
			System.out.println(s);
		}
		
		System.out.println("data: " + data);
	}
	
	public void printObjectToFile() throws IOException {
		File file = new File("src/data/digitdata/sampletest");
		if(!file.exists()) {
			System.out.println("Error");
			return;
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
		for(String s: object) {
			writer.append(s);
			writer.newLine();
			
		}
		writer.append("data: " + data);
		writer.newLine();
		writer.close();
	}
	
	public char[][] printEdges() {
		char[][] object_2D = new char[object.size()][length];
		for(int i = 0; i < object.size(); i ++) {
			char[] s_arr = object.get(i).toCharArray();
			object_2D[i] = s_arr;
		}
		
		for(int i = 0; i < object.size(); i ++) {
			boolean start = false;
			for(int j = 0 ; j < length ; j++) {
				if(i == 0 || i == object.size() - 1) {
					if(object_2D[i][j] == '#') {
						object_2D[i][j] = '+';
					}
				}else {
					if(!start) {
						if(object_2D[i][j] != ' ') {
							object_2D[i][j]= '+';
							start = true;
						}
					}else {
						if(object_2D[i][j] == ' ') {
							object_2D[i][j-1] = '+';
							start = false;
						}else if(object_2D[i][j] == '+') {
							start = false;
						}
					}
				}
			}
		}
		
		//fill empty gaps
		for(int i = 0; i < object.size(); i ++) {
			for(int j = 0 ; j < length ; j++) {
				if(object_2D[i][j] == '#') {
					//check top
					if(inbounds(i,j-1)) {
						if(object_2D[i][j-1] == ' ') {
							object_2D[i][j] = '+';
						}
					}
					
					//check bottom
					if(inbounds(i,j+1)) {
						if(object_2D[i][j+1] == ' ') {
							object_2D[i][j] = '+';
						}
					}
					
					//check left
					if(inbounds(i-1,j)) {
						if(object_2D[i-1][j] == ' ') {
							object_2D[i][j] = '+';
						}
					}
					
					//check right
					if(inbounds(i+1,j)) {
						if(object_2D[i+1][j] == ' ') {
							object_2D[i][j] = '+';
						}
					}
					
				}
			}
		}
		
		for(int i = 0; i < object.size(); i ++) {
			for(int j = 0 ; j < length ; j++) {
				if(object_2D[i][j] == '#') {
					object_2D[i][j] = ' ';
				}
				System.out.print(object_2D[i][j]);
			}
			System.out.println();
		}
		
		return object_2D;
	}
	
	public int loopnum() {
		int loop = 0;
		return loop;
	}
	
	public void assign_value(int i) {
		assigned_data = i;
	}
	
	public boolean checkData() {
		if(assigned_data == data) {
			return true;
		}
		return false;
	}
	
	
	public boolean isObject() {
		if(object.size()>=10) {
			return true;
		}
		return false;
	}
	
	public void reset() {
		object.clear();
	}
	
	public boolean inbounds(int i, int j) {
		if(!(i >= 0 && i <object.size())) {
			return false;
		}
		if(!(j >= 0 && j <length)) {
			return false;
		}
		return true;
	}
}
