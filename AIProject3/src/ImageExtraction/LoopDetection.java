package ImageExtraction;

import java.util.ArrayList;

public class LoopDetection {
	char[][] obj;
	int width;
	int length;
	
	public LoopDetection(char[][] arr) {
		obj = arr;
		length = arr.length;
		width = arr[0].length;
	}
	
	public int highest_count() {
		int loop = 0;
		int current = loop();
		loop = current;
		for(int i = 0; i < 3; i++) {
			invert();
			current = loop();
			if(current > loop) {
				loop = current;
			}
		}
		return loop;	
	}
	
	public int loop() {
		int loops = 0;
		for(int i = 1; i < length; i++) {
			//System.out.println("row: " + i);
			String s = new String(obj[i]);
			s = s.trim();
			//System.out.println(s);
			if(s.contains(" ")) {
				//System.out.println("row: " + i);
				//System.out.println(s);
				boolean top = true;
				String s_prev = "";
				if(i != 0) {
					String curr_str = new String(obj[i]);
					int num_left_space = curr_str.indexOf('1');
					curr_str = curr_str.trim();
					s_prev = new String(obj[i-1]).substring(num_left_space);
					int counter = 0;
					while(counter < curr_str.length()) {
						if(curr_str.charAt(counter) == ' ') {
							if(s_prev.charAt(counter)== ' ') {
								top = false;
								break;
							}
						}
						counter++;
					}
				}
				if(top) {
					if(i < obj.length -1) {
						while(s.contains(" ")) {
							i++;
							if(i > length -1) {
								break;
							}
							s_prev = s;
							s = new String(obj[i]);
							s = s.trim();
							
						}
						if(i< length -1) {
							s_prev = new String(obj[i-1]);
							s = new String(obj[i]);
							
							s = s.substring(s_prev.indexOf('1'));
							s_prev = s_prev.trim();
							boolean bottom = true;
							int counter = 0;
							boolean start = false;
							while(counter < s_prev.length()) {
								if(s_prev.charAt(counter) == ' ') {
									//System.out.println(counter);
									start = true;
									if(s.charAt(counter)== ' ') {
										bottom = false;
										break;
									}
								}else {
									if(start == true) {
										break;
									}
								}
								counter++;
							}
							if(bottom) {
								loops++;
							}
						}
					}
				}
			}
		}
		return loops;
	}
	
	
	public void invert() {
		char[][] newArray = new char[obj[0].length][obj.length];
		for(int i=0; i<obj[0].length; i++){
	        for(int j=obj.length-1; j>=0; j--){
	            newArray[i][j] = obj[j][i];
	        }
	    }
		obj = newArray;
		/*
		for(char[] c : obj) {
			String s = new String(c);
			System.out.println(s);
		}*/
	}
	
}
