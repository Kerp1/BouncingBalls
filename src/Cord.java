/**
 * Created by kerp on 01/05/15.
 */
public class Cord {

    public double x, y;

    public Cord() {
        x = 0;
        y = 0;
    }

    public Cord(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setPolar(double v ,double r) {
        Cord c = PolarToRect(v, r);
        this.x = c.x;
        this.y = c.y;
    }


    public Cord PolarToRect(double v, double r) {
        return new Cord(Math.cos(v) * r, Math.sin(v) * r);
    }

    public Cord RectToPolar(double x, double y) {
        return new Cord(Math.sqrt(x * x + y * y), Math.atan2(y, x));
    }
}
