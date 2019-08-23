public class NBody {
  public static double readRadius(String file) {
    In in = new In(file);

    int numPlanets = in.readInt();
		double radius = in.readDouble();
    return radius;
  }

  public static Planet[] readPlanets(String file) {
    In in = new In(file);

    int numPlanets = in.readInt();
    double radius = in.readDouble();

    Planet[] planets = new Planet[numPlanets];

    for (int i=0; i<numPlanets; i+=1) {
      double xxPos = in.readDouble();
      double yyPos = in.readDouble();
      double xxVel = in.readDouble();
      double yyVel = in.readDouble();
      double mass = in.readDouble();
      String name = in.readString();
      planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, name);
    }
    return planets;
  }

  public static void main(String[] args){
    // Collecting all needed input
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double radius = readRadius(filename);
    Planet[] planets = readPlanets(filename);
    // Drawing the background
    StdDraw.setScale(-radius, radius);
    StdDraw.clear();
    StdDraw.picture(0, 0, "C:/Users/MaiRZ/Desktop/cs61b-uc-berkeley/proj0/images/starfield.jpg");
    // Drawing all of the Planets
    for (int i = 0; i < planets.length; i+=1) {
      planets[i].draw();
    }
    // Creating an animation
    StdDraw.enableDoubleBuffering();
    double time = 0.0;
    while (time < T) {
      /** Initialize xForces and yForces arrays*/
      double[] xForces = new double[planets.length];
      double[] yForces = new double[planets.length];
      /** Calculate the net x and y forces for each planet, storing these in the xForces and yForces arrays, respectively*/
      for (int i=0; i<planets.length; i+=1) {
        xForces[i] = planets[i].calcNetForceExertedByX(planets);
        yForces[i] = planets[i].calcNetForceExertedByY(planets);
      }
      /** Call update on each of the planets*/
      for (int j=0; j<planets.length; j+=1){
        planets[j].update(dt, xForces[j], yForces[j]);
      }
      StdDraw.picture(0, 0, "C:/Users/MaiRZ/Desktop/cs61b-uc-berkeley/proj0/images/starfield.jpg");
      for (int k=0; k<planets.length; k+=1) {
        planets[k].draw();
      }
      StdDraw.show();
      StdDraw.pause(10);
      time += dt;
    }
    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++){
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName); 
    }
  }
}
