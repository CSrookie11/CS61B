public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename);
        int num = in.readInt();
        return in.readDouble();
    }
    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int num = in.readInt();
        double r=in.readDouble();
        Planet[] planets = new Planet[num];
        for (int i = 0; i < num; i++){
            double xP=in.readDouble();
            double yP=in.readDouble();
            double xV=in.readDouble();
            double yV=in.readDouble();
            double m=in.readDouble();
            String img=in.readString();
            planets[i]=new Planet(xP,yP,xV,yV,m,img);
        }
        return planets;
    }

    public static void main(String[] args) {
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        double radius=NBody.readRadius(filename);
        Planet []planets=NBody.readPlanets(filename);
        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius, radius);
        String imageToDraw = "images/starfield.jpg";
        StdDraw.enableDoubleBuffering();
        double t=0;
        while(t < T){
            double []xForces=new double[planets.length];
            double []yForces=new double[planets.length];
            for (int i=0; i< planets.length; i++){
                xForces[i]=planets[i].calcNetForceExertedByX(planets);
                yForces[i]=planets[i].calcNetForceExertedByY(planets);
            }
            for (int i=0; i< planets.length;i++){
                planets[i].update(dt, xForces[i], yForces[i] );
            }
            StdDraw.clear();
            StdDraw.picture(0, 0, imageToDraw);
            for (int i=0; i<planets.length; i++){
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t+=dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }

}
