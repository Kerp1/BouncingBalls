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

        Balls.add(new Ball(areaWidth, areaHeight, 1, 4, 0, 10, 0.5));
        Balls.add(new Ball(areaWidth, areaHeight, 2, 2, 2, 2, 0.5));

    }

    @Override
    public void tick(double deltaT) {
        
    	for(Ball b : Balls) {
            b.tick(deltaT);
            for(Ball c : Balls){
            	if(b != c){
            		if(Math.pow((b.r-c.r), 2) <= Math.pow(b.x-c.x, 2) + Math.pow(b.y-c.y, 2) && Math.pow(b.x-c.x, 2) + Math.pow(b.y-c.y, 2) <= Math.pow((b.r+c.r), 2)){
            			
            		}
            	}
            }
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