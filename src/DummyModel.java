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

        //Balls.add(new Ball(areaWidth, areaHeight, 3, 2, 2, 2, 0.5));
        //Balls.add(new Ball(areaWidth, areaHeight, 8, 2, -2, 2, 0.5));

        Balls.add(new Ball(areaWidth, areaHeight, 1, 4, 2, 2, 0.3));
        Balls.add(new Ball(areaWidth, areaHeight, 2, 2, -2, 2, 0.3));

    }

    @Override
    public void tick(double deltaT) {
        
    	/*for(Ball b : Balls) {
            b.tick(deltaT);
            for(Ball c : Balls){
            	if(b != c){
            		if(Math.pow((b.r-c.r), 2) <= Math.pow(b.x-c.x, 2) + Math.pow(b.y-c.y, 2) && Math.pow(b.x-c.x, 2) + Math.pow(b.y-c.y, 2) <= Math.pow((b.r+c.r), 2)){
            			collide(b, c);
            		}
            	}
            }
    }*/

        Ball b = Balls.get(0);
        Ball c = Balls.get(1);
        if(Math.pow((b.r - c.r), 2) <= Math.pow(b.x - c.x, 2) + Math.pow(b.y - c.y, 2) && Math.pow(b.x - c.x, 2) + Math.pow(b.y - c.y, 2) <= Math.pow((b.r + c.r), 2)) {
            collide(b, c, deltaT);
        } else {
            for (Ball a : Balls) {
                a.tick(deltaT);
            }
        }
    }

    private void collide(Ball one, Ball two, double deltaT) {

        Cord vOne = new Cord(one.vx, one.vy).rectToPolar();
        Cord vTwo = new Cord(two.vx, two.vy).rectToPolar();

        double a;
        if(one.x - two.x == 0) {
            a = Math.PI / 2;
        } else {
             a = Math.atan((one.y - two.y) / (one.x - two.x));
        }

        if ((one.y - two.y) / (one.x - two.x) >= 0) {
            vOne.y -= a;
            vTwo.y -= a;
        } else {
            vOne.y -= (Math.PI - a);
            vTwo.y -= (Math.PI - a);
         }

        vOne = vOne.polarToRect();
        vTwo = vTwo.polarToRect();

        double u1 = vOne.x;
        double u2 = vTwo.x;

        double I = u1 + u2;
        double R = -(u2 - u1);

        double v1 = (I + 1*R / (1 + 1)) - R;
        double v2 = (I + 1*R) / (1 + 1);

        vOne.x = v1;
        vTwo.x = v2;

        vOne = vOne.rectToPolar();
        vTwo = vTwo.rectToPolar();


        if ((one.y - two.y) / (one.x - two.x) >= 0) {

            vOne.y += a;
            vTwo.y += a;
            System.out.println("nej");
        } else {
            System.out.println("här");
            vOne.y += (Math.PI - a);
            vTwo.y += (Math.PI - a);
        }

        vOne = vOne.polarToRect();
        vTwo = vTwo.polarToRect();

        one.vx = vOne.x;
        one.vy = vOne.y;

        two.vx = vTwo.x;
        two.vy = vTwo.y;

        one.x += one.vx * deltaT;
        one.y += one.vy * deltaT;

        two.x += two.vx * deltaT;
        two.y += two.vy * deltaT;
        /*Cord oneC = new Cord(one.x, one.y).rectToPolar();
        Cord twoC = new Cord(two.x, two.y).rectToPolar();

        Cord v3one = oneC.sub(twoC);
        Cord v3two = twoC.sub(oneC);

        Cord projone = v3one.project(one.vx, one.vy);
        Cord projtwo = v3one.project(two.vx, two.vy);

        Cord cu1 = new Cord(projone.x, projone.y).rectToPolar();
        Cord cu2 = new Cord(projtwo.x, projtwo.y).rectToPolar();

        double ad = Math.atan2(projone.y, projone.x);
        double da = Math.atan2(projtwo.y, projtwo.x);

        double u1 = cu1.x;
        double u2 = cu2.x;




        //double u1 = Math.sqrt(projone.x * projone.x + projone.y * projone.y);
        // double u2 = Math.sqrt(projtwo.x * projtwo.x + projtwo.y * projtwo.y);

        double I = u1 + u2;
        double R = -(u2 - u1);

        double v1 = (I + 1*R / (1 + 1)) - R;
        double v2 = (I + 1*R) / (1 + 1);

        // Ball 1
        Cord vOne = new Cord(v1 * Math.cos(Math.atan2(projone.y, projone.x)), v1 * Math.sin(Math.atan2(projone.y, projone.x)));
        one.vx = one.vx - projone.x + vOne.x;
        one.vy = one.vy - projone.y + vOne.y;

        //Ball 2
        Cord vTwo = new Cord(v2 * Math.cos(Math.atan2(projtwo.y, projtwo.x)), v2 * Math.sin(Math.atan2(projtwo.y, projtwo.x)));
        two.vx = two.vx - projtwo.x + vTwo.x;
        two.vy = two.vy - projtwo.y + vTwo.y;*/
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