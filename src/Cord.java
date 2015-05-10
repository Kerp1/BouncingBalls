/**
 * Created by kerp on 01/05/15.
 */
public class Cord {

    private double x, y;

    public Cord() {
        x = 0;
        y = 0;
    }

    public Cord(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getV() {
        return Math.atan2(y, x);
    }

    public double getR() {
        return Math.sqrt(x * x + y * y);
    }

    public void setV(double v) {

        double newX = calculateX(v, getR());
        double newY = calculateY(v, getR());
        setX(newX);
        setY(newY);
    }

    public Cord rotate(double a) {
        double currentV = getV();
        currentV -= a;
        return new Cord(calculateX(currentV, getR()), calculateY(currentV, getR()));
       // setV(currentV);
    }

    public double calculateX(double v, double r) {
        return Math.cos(v) * r;
    }

    public double calculateY(double v, double r) {
        return Math.sin(v) * r;
    }

}
