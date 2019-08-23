public class Planet {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  public static double g = 6.67e-11;

  public Planet(double xP, double yP, double xV, double yV, double m, String img){
    this.xxPos = xP;
    this.yyPos = yP;
    this.xxVel = xV;
    this.yyVel = yV;
    this.mass = m;
    this.imgFileName = img;
  }

  public Planet(Planet p){
    this.xxPos = p.xxPos;
    this.yyPos = p.yyPos;
    this.xxVel = p.xxVel;
    this.yyVel = p.yyVel;
    this.mass = p.mass;
    this.imgFileName = p.imgFileName;
  }

  public double calcDistance(Planet p) {
    double dx = this.xxPos - p.xxPos;
    double dy = this.yyPos - p.yyPos;
    double rSquare =  dx*dx + dy*dy;
    return Math.sqrt(rSquare);
  }

  public double calcForceExertedBy(Planet p) {
    double r = this.calcDistance(p);
    return this.mass * p.mass * g / (r*r);
  }

  public double calcForceExertedByX(Planet p) {
    double f = this.calcForceExertedBy(p);
    double r = this.calcDistance(p);
    double dx = p.xxPos - this.xxPos;
    return f * dx / r;
  }

  public double calcForceExertedByY(Planet p) {
    double f = this.calcForceExertedBy(p);
    double r = this.calcDistance(p);
    double dy = p.yyPos - this.yyPos;
    return f * dy / r;
  }

  public double calcNetForceExertedByX(Planet[] planets) {
    double netForceX = 0;
    for (Planet planet : planets) {
      if (!this.equals(planet)) {
        netForceX += this.calcForceExertedByX(planet);
      }
    }
    return netForceX;
  }

  public double calcNetForceExertedByY(Planet[] planets) {
    double netForceY = 0;
    for (Planet planet : planets) {
      if (!this.equals(planet)) {
        netForceY += this.calcForceExertedByY(planet);
      }
    }
    return netForceY;
  }

  public void update(double dt, double fX, double fY) {
    // Step 1
    double ax = fX/this.mass;
    double ay = fY/this.mass;
    // Step 2
    this.xxVel += ax * dt;
    this.yyVel += ay * dt;
    // Step 3
    this.xxPos += this.xxVel * dt;
    this.yyPos += this.yyVel * dt;
  }

  public void draw() {
    String imgpath = "./images/" + this.imgFileName;
    StdDraw.picture(this.xxPos, this.yyPos, imgpath);
  }
}
