public class ProjectileSprite extends MobileSprite
{
  private int counter;
  private int counter2;
  private int counter3;
  public ProjectileSprite(int c, double x, double y, double theLeft, double theTop, int theWidth, int theHeight, String theImage)
  {
    super(x,y,theLeft,theTop,theWidth,theHeight,theImage);
    counter = c;
    counter2 = c;
    counter3 = 200;
  }
  
  public void step(World world)
  {
    setLeft(getLeft() + getVX());
    setTop(getTop() + getVY());
    setVX(getVX()+0.00001);
    if(getTop()+getHeight()>world.getHeight())
    {
      setVY(-Math.abs(getVY()));
    }
    else if(getTop()<0)
    {
      setVY(Math.abs(getVY()));
    }
    
    if(counter3<=0)
    {
      setVX(-getVX());
      setTop(getTop()+15);
      counter3 = 400;
//    }
//    else if(getLeft()<0)
//    {
//      setVX(-getVX());
//      setTop(getTop()+15);
    }
    if(counter == 0)
    {
      counter = counter2;
      world.getSprites().add(new Projectile(0,5,getLeft()+getWidth()/2,getTop()+getHeight(), 3,9,"laser.png"));
    }
    else
      counter--;
    counter3--;
  }
}