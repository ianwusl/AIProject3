package objects;

import java.util.ArrayList;

public class Point {
	private int x;
	private int y;
	
	private Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static Point create(int x, int y) {
		return new Point(x,y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public ArrayList<Point> getSiblings(TestObject obj, char[][] obj_2D){
		ArrayList<Point> siblings = new ArrayList<>();
		for(int i = -1 ; i < 2 ; i++) {
			for(int j = -1; j <2 ; j++) {
				int x = this.getX()+ j;
				int y = this.getY()+ i;
				if(obj.inbounds(y, x)) {
					Point p = Point.create(x, y);
					siblings.add(p);				
				}
			}
		}
		siblings.remove(this);
		return siblings;
		
	}
	
	public Point getBottomPoint(TestObject obj, char[][] obj_2D, int bias, Point previous){
		//biases -1 for left +1 for right
		ArrayList<Point> list = new ArrayList<>();
		Point selected = null;
			for(int i = 0; i < 2 ; i ++) {
				for(int j = -1; j <2 ; j++) {
					int x = this.getX()+ j;
					int y = this.getY() + i;
					if(obj.inbounds(y, x)) {
						if(obj_2D[y][x] != ' ') {
							list.add(Point.create(x, y));
						}
					}
				}
			}
			list.remove(previous);
		//left biased
		if(bias == -1) {
			//move left
			if(list.contains(Point.create(this.getX() - 1, this.getY()))) {
				selected = Point.create(this.getX() - 1, this.getY());
			}
			//move bottom left
			else if(list.contains(Point.create(this.getX() - 1, this.getY()+1))){
				selected = Point.create(this.getX() - 1, this.getY() +1);
			}
			//move down
			else if(list.contains(Point.create(this.getX() , this.getY() +1))) {
				selected = Point.create(this.getX(), this.getY() +1);
			
			}
			//move right
			else if(list.contains(Point.create(this.getX() +1, this.getY()))) {
				selected = Point.create(this.getX() +1, this.getY());
			}
			//right biased
		}else {
			//move right
			if(list.contains(Point.create(this.getX() +1, this.getY()))) {
				selected = Point.create(this.getX() +1, this.getY());
			}
			//move bottom right
			else if(list.contains(Point.create(this.getX() +1, this.getY()+1))){
				selected = Point.create(this.getX() +1, this.getY() +1);
			}
			//move down
			else if(list.contains(Point.create(this.getX() , this.getY() +1))) {
				selected = Point.create(this.getX(), this.getY() +1);
			}else if(list.contains(Point.create(this.getX() - 1, this.getY()))) {
				//move left
				selected = Point.create(this.getX() - 1, this.getY());
			}
		}
		
		return selected;
		
	}
	
	public int distance(Point p) {
		return (int)Math.sqrt(Math.pow(this.getX() - p.getX(), 2) + Math.pow(this.getY() - p.getY(), 2));
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Point) {
			Point p = (Point)o;
			if(this.getX() == p.getX() && this.getY() == p.getY()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "[" + x + ", " + y +"]";
	}
}
