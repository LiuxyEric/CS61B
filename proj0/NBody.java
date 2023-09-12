public class NBody {

    public static double readRadius(String fileName) {
        In in = new In(fileName);

        in.readInt();

        return in.readDouble();
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int N = in.readInt();
        in.readDouble();
        Planet[] ps = new Planet[N];
        for (int i = 0; i < N; i++) {
            double xx = in.readDouble();
            double yy = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String imgName = in.readString();
            ps[i] = new Planet(xx, yy, xV, yV, m, imgName);
        }
        return ps;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] ps = readPlanets(filename);

        /* set scale and draw background image*/
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "./images/starfield.jpg");

        /*Draw one planet */
        for (Planet p : ps) {
            p.draw();
        }

        StdDraw.enableDoubleBuffering();

        for (double t = 0; t < T; t += dt) {
            double[] xForces = new double[ps.length];
            double[] yForces = new double[ps.length];
            for (int i = 0; i < ps.length; i++) {
                xForces[i] = ps[i].calcNetForceExertedByX(ps);
                yForces[i] = ps[i].calcNetForceExertedByY(ps);
            }
            for (int i = 0; i < ps.length; i++) {
                ps[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "./images/starfield.jpg");

            for (Planet p : ps) {
                p.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

        }

        StdOut.printf("%d\n", ps.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < ps.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
                    ps[i].yyVel, ps[i].mass, ps[i].imgFileName);
        }

    }


}
