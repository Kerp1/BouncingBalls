import java.awt.geom.Ellipse2D;

/**
 * Created by kerp on 01/05/15.
 */
public class Ball {

    private final double areaWidth;
    private final double areaHeight;

    private double x, y, vx, vy, r;

    public Ball(double width, double height, double x, double y, double vx, double vy, double r) {
        this.areaWidth = width;
        this.areaHeight = height;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.r = r;
    }


    public void tick(double deltaT) {
        if (x < r || x > areaWidth - r) {
            vx *= -1;
        }
        if (y < r || y > areaHeight - r) {
            vy *= -1;
        }
        x += vx * deltaT;
        y += vy * deltaT;
    }

    public Ellipse2D getEllipse() {
        return new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
    }

}
