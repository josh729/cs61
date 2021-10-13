class NBody{


	public static void main(String[] args){

		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double rad = readRadius("./data/planets.txt");

		Planet[] p = new Planet[5];

		p = readPlanets("./data/planets.txt");

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-rad , rad);
		StdDraw.clear(); 
		StdDraw.picture(0,0,"./images/starfield.jpg");
		StdDraw.show();


		double[] xxForce = new double[5];
		double[] yyForce = new double[5];


		for(int i = 0; i < 5;i++){
			p[i].draw();

		}
		StdDraw.show();

		double now_t = 0;

		while(now_t < T){
			for(int i = 0;i<5;i++){
				xxForce[i] = p[i].calcNetForceExertedByX(p);
				yyForce[i] = p[i].calcNetForceExertedByY(p);
				p[i].update(dt,xxForce[i],yyForce[i]);
			}

		StdDraw.setScale(-rad , rad);
		StdDraw.clear(); 
		StdDraw.picture(0,0,"./images/starfield.jpg");
		for(int i = 0; i < 5;i++){
			p[i].draw();

			}
			StdDraw.show();
			StdDraw.pause(10);

			now_t = now_t + dt;

		}


	}


	public  static double readRadius(String file){
		In in = new In(file);


		int firstvalue = in.readInt();
		double radius = in.readDouble();

		return radius;

	}

	public static Planet[] readPlanets(String file) {
		double xx,yy,xxvel,yyvel,mass;
		String images;

		In in = new In(file);
		
		int firstvalue = in.readInt();
		double radius = in.readDouble();

		Planet[] p = new Planet[5];

		for(int i = 0;i < 5;i++){
			xx = in.readDouble();
			yy = in.readDouble();
			xxvel = in.readDouble();
			yyvel = in.readDouble();
			mass = in.readDouble();
			images = in.readString();
			p[i] = new Planet (xx,yy,xxvel,yyvel,mass,images);

		}
		return p;


	}


}