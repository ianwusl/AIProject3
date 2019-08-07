package objects;

import java.util.ArrayList;

public class TestObject {
	protected ArrayList<String> object;
	public int data;
	public int assigned_data;
	private static int count = 0;
	
	public TestObject(int data) {
		object = new ArrayList<>();
		this.data = data;
		count++;
	}
	
	public void addToObject(String s) {
		object.add(s);
	}
	
	public void printObject() {
		for(String s: object) {
			System.out.println(s);
		}
		
		System.out.println("data: " + data);
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
	
	public void printCount() {
		System.out.println("Count: "+ count);
	}
}
