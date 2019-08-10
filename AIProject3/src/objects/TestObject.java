package objects;

import java.util.ArrayList;

public class TestObject {
	protected ArrayList<String> object;
	public int data;
	public int assigned_data;
	int length = 0;
	private Cell[][] cell_arr;

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

	public int[][] getIntImage() {
		int[][] object_2D = new int[object.size()][length];
		for(int i = 0; i < object.size(); i ++) {
			String s = new String(object.get(i)).replace(" ", "0").replace("+", "1").replace("#", "1");
			for(int j = 0 ; j < s.length(); j++){
				String num = s.charAt(j) + "";
				object_2D[i][j] = Integer.parseInt(num);
			}
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

	private Cell[][] createCellArray(){
		int[][] obj = this.getIntImage();
		Cell[][] cells = new Cell[28][28];
		for(int i = 0; i < obj.length; i++){
			for(int j = 0 ; j < obj[0].length; j++){
				Cell c = new Cell(j,i,obj[i][j]);
				cells[i][j] = c;
			}
		}
		return cells;
	}

	public Cell[][] getCellArray(){
		if(cell_arr == null){
			cell_arr = this.createCellArray();
		}
		return cell_arr;
	}

	public void printCellArray(){
		for(int i = 0; i < cell_arr.length; i++){
			for(int j = 0 ; j < cell_arr[0].length; j++){
				System.out.print(cell_arr[i][j]);
			}
			System.out.println();
		}
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
