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

    public void setPolar(double v ,double r) {
        Cord c = polarToRect(v, r);
        this.x = c.x;
        this.y = c.y;
    }


    public Cord polarToRect(double v, double r) {
        return new Cord(Math.cos(v) * r, Math.sin(v) * r);
    }

    public Cord polarToRect() {
        return new Cord(Math.cos(y) * x, Math.sin(y) * x);
    }

    public Cord rectToPolar(double x, double y) {
        return new Cord(Math.sqrt(x * x + y * y), Math.atan2(y, x));
    }

    public Cord rectToPolar() {
        return new Cord(Math.sqrt(x * x + y * y), Math.atan2(y, x));
    }

    public Cord sub(Cord other) {
        return new Cord(this.x * Math.cos(this.y) - other.x * Math.cos(other.y), this.x * Math.sin(this.y) - other.x * Math.sin(other.y));
    }

    public Cord project(double vx, double vy) {
        double div = (vx * x + vy * y) / (x * x + y * y);
        return new Cord(div*x, div*y);
    }
}
