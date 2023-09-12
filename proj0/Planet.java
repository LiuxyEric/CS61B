public class Planet {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double r = this.calcDistance(p);
        return 6.67e-11 * p.mass * this.mass / (r * r);
    }

    public double calcForceExertedByX(Planet p) {
        double netF = this.calcForceExertedBy(p);
        double dx = p.xxPos - this.xxPos;
        double r = this.calcDistance(p);
        return netF * (dx / r);
    }

    public double calcForceExertedByY(Planet p) {
        double netF = this.calcForceExertedBy(p);
        double dy = p.yyPos - this.yyPos;
        double r = this.calcDistance(p);
        return netF * (dy / r);
    }

    public double calcNetForceExertedByX(Planet[] ps) {
        double netX = 0;
        for (Planet p : ps) {
            if (p.equals(this)) {
                continue;
            }
            netX += this.calcForceExertedByX(p);
        }
        return netX;
    }

    public double calcNetForceExertedByY(Planet[] ps) {
        double netY = 0;
        for (Planet p : ps) {
            if (p.equals(this)) {
                continue;
            }
            netY += this.calcForceExertedByY(p);
        }
        return netY;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        this.xxVel += dt * ax;
        this.yyVel += dt * ay;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }

}
