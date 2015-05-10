import java.awt.geom.Ellipse2D;

/**
 * Created by kerp on 01/05/15.
 */
public class Ball {

    private final double areaWidth;
    private final double areaHeight;

    public double x, y, vx, vy, r, m;

    public Ball(double width, double height, double x, double y, double vx, double vy, double r, double m) {
        this.areaWidth = width;
        this.areaHeight = height;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.r = r;
        this.m = m;
    }


    public void tick(double deltaT) {
    	 boolean appliedGravity = false;
        double g = 9.81;
        if (x < r || x > areaWidth - r) {
            vx *= -1;
            vy += -g*deltaT;
            appliedGravity = true;
        }
        if (y < r || y > areaHeight - r) {
            if(appliedGravity) {
                vy -= -g*deltaT;
            }
            vy = vy * -1;
        }else{
            if(!appliedGravity) {
               vy += -g * deltaT;
            }
        }
        y += vy * deltaT;
        x += vx * deltaT;
    }

    public Ellipse2D getEllipse() {
        return new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
    }

}
