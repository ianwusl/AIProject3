package objects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import ImageExtraction.LoopDetection;

public class TestObject {
	protected ArrayList<String> object;
	public int data;
	public int assigned_data;
	int length = 0;

	public TestObject(int data) {
		object = new ArrayList<>();
		this.data = data;
	}

	public void addToObject(String s) {
		if(s.length() > length) {
			length = s.length();
		}
		char[] arr = s.toCharArray();
		s = new String(arr);
		object.add(s);
	}

	public void printObject() {
		for(String s: object) {
			System.out.println(s);
		}
		System.out.println("data: " + data);
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
