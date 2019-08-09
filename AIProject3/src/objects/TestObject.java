package objects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import ImageExtraction.LoopDetection;

public class TestObject {
	protected ArrayList<String> object;
	protected ArrayList<String> object_edges;
	public int data;
	public int assigned_data;
	int length = 0;
	
	public TestObject(int data) {
		object = new ArrayList<>();
		object_edges = new ArrayList<>();
		this.data = data;
	}
	
	public void addToObject(String s) {
		if(s.length() > length) {
			length = s.length();
		}
		char[] arr = s.toCharArray();
		for(int i = 1; i < arr.length -1 ; i++) {
			if(arr[i]== ' ') {
				if(arr[i-1] != ' ' && arr[i+1] != ' ')  {
					arr[i] = '1';
				}
			}
		}
		s = new String(arr);
		object.add(s);
	}
	
	public void printObject() {
		for(String s: object) {
			System.out.println(s);
		}
		System.out.println("data: " + data);
	}
	public void printEdges() {
		for(String s: object_edges) {
			System.out.println(s);
		}
		System.out.println("data: " + data);
	}
	
	public void printObjectToFile() throws IOException {
		this.edges();
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
		writer.append("loops: " + this.loopnum());
		writer.newLine();
		writer.close();
	}
	public char[][] object2D() {
		char[][] object_2D = new char[object.size()][length];
		for(int i = 0; i < object.size(); i ++) {
			String s = object.get(i).replace('+', '1').replace('#', '1');
			char[] s_arr = s.toCharArray();
			
			object_2D[i] = s_arr;
		}
		return object_2D;
	}
	
	public char[][] edges() {
		char[][] object_2D = new char[object.size()][length];
		for(int i = 0; i < object.size(); i ++) {
			char[] s_arr = object.get(i).toCharArray();
			object_2D[i] = s_arr;
		}
		char[][] object_ret = new char[object.size()][length];
		for(int i = 0; i < object.size(); i ++) {
			for(int j = 0 ; j < length ; j++) {
				if(object_2D[i][j] == '+') {
					if(this.inbounds(i, j-1) && this.inbounds(i, j+1)) {
						if(object_2D[i][j-1] == '+' && object_2D[i][j+1] == ' ') {
							object_ret[i][j] = '#';
						}else if(object_2D[i][j-1] == ' ' && object_2D[i][j+1] == '+') {
							object_ret[i][j] = '#';
						}
					}
				}
				if(object_2D[i][j] == ' ' || object_2D[i][j] == '+') {
					object_ret[i][j] = ' ';
				}else {
					object_ret[i][j] = '1';
				}
				//System.out.print(object_2D[i][j]);
			}
			//System.out.println();
		}
		
		for(int i= 0; i<object.size(); i++) {
			char[] arr = object_ret[i];
			String s = new String(arr);
			if(s != null) {
				object_edges.add(s);
			}
			
		}
		
		return object_ret;
	}
	
	public int loopnum() {
		
		char[][] obj = this.object2D();
		char[][] edges = this.edges();
		this.printObject();
		//LoopDetection ld = new LoopDetection(obj);
		LoopDetection ld2 = new LoopDetection(obj);
		int i = ld2.highest_count();
		//int j = ld2.highest_count();
		
		return i; //> j? i: j;
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
