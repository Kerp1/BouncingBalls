import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class DummyModel implements IBouncingBallsModel {

    private final double areaWidth;
    private final double areaHeight;

    private List<Ball> Balls = new ArrayList<Ball>();


    private double x, y, vx, vy, r;

    public DummyModel(double width, double height) {
        this.areaWidth = width;
        this.areaHeight = height;

        Balls.add(new Ball(areaWidth, areaHeight, 1, 1, 2.3, 1, 1));

    }

    @Override
    public void tick(double deltaT) {
        for(Ball b : Balls) {
            b.tick(deltaT);
        }
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