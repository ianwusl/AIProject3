package objects;

public class Cell {
	private Point point;
	private double belief;
	public int data;

	public Cell(int x, int y, int data){
		point = Point.create(x, y);
		this.data = data;
		if(data == 0){
			belief = 0;
		}else if(data == 1){
			belief = 1;
		}
	}

	public Point getPoint(){
		return point;
	}

	public void updateBelief(double a){
		belief = a;
	}

	public double getBelief(){
		return belief;
	}

	public int getData(){
		return data;
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof Cell){
			Cell c = (Cell)o;
			return c.getPoint().equals(this.getPoint());
		}

		return false;
	}

	@Override
	public String toString(){
		return this.getBelief() ==  0 ? "    " : this.getBelief()+ " " ;
	}
}
