public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
       xxPos=xP;
       yyPos=yP;
       xxVel=xV;
       yyVel=yV;
       mass=m;
       imgFileName=img;

    }
    public Planet (Planet p){
        xxPos=p.xxPos;
        yyPos=p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName=p.imgFileName;
    }
    public double calcDistance(Planet p){
        double distance=Math.sqrt((Math.pow((this.xxPos-p.xxPos),2))+(Math.pow((this.yyPos-p.yyPos),2)));
        return distance;
    }
    public double calcForceExertedBy(Planet p){
        double distance=this.calcDistance(p);
        return G*this.mass*p.mass/(distance*distance);
    }
    public double calcForceExertedByX(Planet p){
        double F=this.calcForceExertedBy(p);
        double r=this.calcDistance(p);
        double x=p.xxPos-this.xxPos;
        return F * x / r;
    }
    public double calcForceExertedByY(Planet p){
        double F=this.calcForceExertedBy(p);
        double r=this.calcDistance(p);
        double y=p.yyPos-this.yyPos;
        return F * y / r;
    }
    public double calcNetForceExertedByX(Planet [] allPlanet){
        double F=0;
        int j=allPlanet.length;
        for(int i =0; i<j; i++){
            if (this.equals(allPlanet[i])){
                continue;
            }
            F+=this.calcForceExertedByX(allPlanet[i]);
        }
        return F;
    }
    public double calcNetForceExertedByY(Planet [] allPlanet){
        double F=0;
        int j= allPlanet.length;
        for (int i =0; i<j; i++){
            if(this.equals(allPlanet[i])){
                continue;
            }
            F+=this.calcForceExertedByY(allPlanet[i]);
        }
        return F;
    }
    public void update(double dt, double fX, double fY){
        double ax=fX/this.mass;
        double ay=fY/this.mass;
        this.xxVel+=dt*ax;
        this.yyVel+=dt*ay;
        this.xxPos+=dt*this.xxVel;
        this.yyPos+=dt*this.yyVel;
    }
    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}



