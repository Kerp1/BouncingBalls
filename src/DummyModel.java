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

        Balls.add(new Ball(areaWidth, areaHeight, 8, 8, 2, 2, 0.5, 10));
        Balls.add(new Ball(areaWidth, areaHeight, 2, 8, 2, 2, 1, 40));

    }

    private double t = 0;
    private double t1 = 0;

    @Override
    public void tick(double deltaT) {

        Ball b = Balls.get(0);
        Ball c = Balls.get(1);

        double bvel = (Math.pow(Math.sqrt((b.vx * b.vx + b.vy * b.vy)), 2) * b.m) / 2;
        double cvel = (Math.pow(Math.sqrt((c.vx * c.vx + c.vy * c.vy)), 2) * c.m) / 2;

        //double bvel = (((b.vy*b.vy) * b.m) / 2);
        //double cvel = (c.vy*c.vy) * c.m / 2;


        double bpot = b.y * b.m * 9.81;
        double cpot = c.y * c.m * 9.81;

        //System.out.println("Total energy: " + (bvel  + cvel + bpot + cpot));

        //System.out.println("MGH: " + bpot + " MV2: " + bvel + " TOT: " + (bpot + bvel) + " DIF: " + (t - (bpot + bvel)));

        t = Math.max(t, (bvel  + cvel + bpot + cpot));
        System.out.println(t);

        b.tick(deltaT);
        c.tick(deltaT);
        //for (Ball a : Balls) {
        //    a.tick(deltaT);
        //}
        if(Math.pow((b.r - c.r), 2) <= Math.pow(b.x - c.x, 2) + Math.pow(b.y - c.y, 2) && Math.pow(b.x - c.x, 2) + Math.pow(b.y - c.y, 2) <= Math.pow((b.r + c.r), 2)) {
            collide(b, c);
        }
        
    }

    private void collide(Ball one, Ball two) {

        Cord vOne = new Cord(one.vx, one.vy);
        Cord vTwo = new Cord(two.vx, two.vy);

        double m1 = one.m;
        double m2 = two.m;

        //vOne = vOne.rectToPolar();
        //vTwo = vTwo.rectToPolar();

        //System.out.println("onex: " + vOne.getX() + " == " + vOne.calculateX(vOne.getV(), vOne.getR()));
        //double a = Math.atan((one.y-two.y)/(one.x-two.x));
        double a = Math.atan2(one.y - two.y, one.x - two.x);

        //System.out.println("vONE v: " + vOne.getX());
        //System.out.println("vONE r: " + vOne.getY());
        vOne = vOne.rotate(a);
        //System.out.println("vONE v: " + vOne.getX());
        //System.out.println("vONE r: " + vOne.getY());
        //System.out.println("vONE v: " + vOne.getX());
        //System.out.println("vONE r: " + vOne.getY());

        vTwo = vTwo.rotate(a);

        //vOne.y -= a;
        //vTwo.y -= a;

        //vOne = vOne.polarToRect();
        //vTwo = vTwo.polarToRect();

        double u1 = vOne.getX();
        double u2 = vTwo.getX();

        double I = (m1*u1 + m2*u2);
        double R = -(u2 - u1);

        double v1 = ((I - m2*R) / (m1 + m2));
        double v2 = R + v1;

        vOne.setX(v1);
        vTwo.setX(v2);
        System.out.println((m1* u1 + m2*u2) + " " + (m1*v1 + m2*v2));
        
        //vOne = vOne.rectToPolar();
       // vTwo = vTwo.rectToPolar();

        vOne = vOne.rotate(-a);
        //System.out.println("vONE v: " + vOne.getX());
        //System.out.println("vOne r: " + vOne.getY());
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