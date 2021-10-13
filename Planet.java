class Planet{

	 double xxPos; //its current x position

	 double yyPos; //its current y position

	 double xxVel;

	 double yyVel;

	 double mass;

 	String imgFileName;

 	public static final double G = (6.67 * 1E-11);

	public Planet(double xP,double yP,double xV,double yV,double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;


}

	public Planet (Planet p){
		this(p.xxPos,p.yyPos,p.xxVel,p.yyVel,p.mass,p.imgFileName);

	}

	public double calcDistance(Planet p){
		double r;
		r  = Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
		return r;
	}

	public double calcForceExertedBy(Planet p){
		double r = calcDistance(p);

		double f = (G * this.mass * p.mass) / (r * r);

		return f;

	}

	public double calcForceExertedByX(Planet p){
		double dx = (p.xxPos- this.xxPos );

		double totalF = calcForceExertedBy(p);

		double r = calcDistance(p);

		double fx = (totalF * dx ) / (r);

		return fx;

	}

	public double calcForceExertedByY(Planet p){
		double dy = (p.yyPos - this.yyPos );

		double totalF = calcForceExertedBy(p);

		double r = calcDistance(p);

		double fy = (totalF * dy ) / (r);

		return fy;

	}

	public double calcNetForceExertedByX(Planet [] p){
		double fx = 0.0;
		for(int i = 0;i < p.length;i++){
			if(this.equals(p[i])){
				continue;
			}
			fx = fx + this.calcForceExertedByX(p[i]);	
		}
		return fx;



	}

	public double calcNetForceExertedByY(Planet [] p){
		double fy = 0.0;
		for(int i = 0;i < p.length;i++){
			if(this.equals(p[i])){
				continue;
			}
			fy = fy + this.calcForceExertedByX(p[i]);
		}
		return fy;
	}

	public void update(double dt, double fX, double fY){
		double xxAcc = fX / this.mass;
		double yyAcc = fY / this.mass;

		double xxNewVel = xxVel + dt * xxAcc;
		double yyNewVel = yyVel + dt * yyAcc;

		double xxNewPostion = xxPos + dt * xxNewVel;
		double yyNewPostion = yyPos + dt * yyNewVel;

		this.xxVel = xxNewVel;
		this.yyVel = yyNewVel;
		this.xxPos = xxNewPostion;
		this.yyPos = yyNewPostion;

	}

	void draw(){
		String image_root = "./images/" + this.imgFileName;
		StdDraw.picture(this.xxPos,this.yyPos,image_root);
	}



}