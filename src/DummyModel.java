import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class DummyModel implements IBouncingBallsModel {

    private final double areaWidth;
    private final double areaHeight;

    private List<Ball> Balls = new ArrayList<Ball>();

    public DummyModel(double width, double height) {
        this.areaWidth = width;
        this.areaHeight = height;

        Balls.add(new Ball(areaWidth, areaHeight, 8, 8, 2, 2, 0.5, 10));
        Balls.add(new Ball(areaWidth, areaHeight, 2, 8, 2, 2, 1, 40));

    }

    @Override
    public void tick(double deltaT) {

        Ball b = Balls.get(0);
        Ball c = Balls.get(1);

        b.tick(deltaT);
        c.tick(deltaT);

        if(Math.pow((b.r - c.r), 2) <= Math.pow(b.x - c.x, 2) + Math.pow(b.y - c.y, 2) && Math.pow(b.x - c.x, 2) + Math.pow(b.y - c.y, 2) <= Math.pow((b.r + c.r), 2)) {
            collide(b, c);
        }
        
    }

    private void collide(Ball one, Ball two) {

        Cord vOne = new Cord(one.vx, one.vy);
        Cord vTwo = new Cord(two.vx, two.vy);

        double m1 = one.m;
        double m2 = two.m;

        double a = Math.atan2(one.y - two.y, one.x - two.x);

        vOne = vOne.rotate(a);
        vTwo = vTwo.rotate(a);

        double u1 = vOne.getX();
        double u2 = vTwo.getX();

        double I = (m1*u1 + m2*u2);
        double R = -(u2 - u1);

        double v1 = ((I - m2*R) / (m1 + m2));
        double v2 = R + v1;

        vOne.setX(v1);
        vTwo.setX(v2);

        vOne = vOne.rotate(-a);
        vTwo = vTwo.rotate(-a);

        one.vx = vOne.getX();
        one.vy = vOne.getY();

        two.vx = vTwo.getX();
        two.vy = vTwo.getY();
    }

    @Override
    public List<Ellipse2D> getBalls() {
        List<Ellipse2D> myBalls = new LinkedList<Ellipse2D>();
        for(Ball b : Balls) {
            myBalls.add(b.getEllipse());
        }

        return myBalls;
    }
}