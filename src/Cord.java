/**
 * Created by kerp on 01/05/15.
 */
public class Cord {

    public double x, y;

    public Cord() {
        x = 0; // == r
        y = 0; // == v
    }

    public Cord(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Cord polarToRect() {
        return new Cord(Math.cos(y) * x, Math.sin(y) * x);
    }

    public Cord rectToPolar() {
    	double v = 0;
    	if(y<0 && x < 0){
    		v = Math.PI + Math.atan(y/x);
    	}else{
    		v = Math.atan(y/x);
    	}
        return new Cord(Math.sqrt(x * x + y * y),v);
    }

    public Cord sub(Cord other) {
        return new Cord(this.x * Math.cos(this.y) - other.x * Math.cos(other.y), this.x * Math.sin(this.y) - other.x * Math.sin(other.y));
    }

    public Cord project(double vx, double vy) {
        double div = (vx * x + vy * y) / (x * x + y * y);
        return new Cord(div*x, div*y);
    }
}
