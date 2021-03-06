public class HeroSprite extends MobileSprite
{
  int Health = 3;
  public HeroSprite(double x, double y, double theLeft, double theTop, int theWidth, int theHeight, String theImage)
  {
    super(x,y,theLeft,theTop,theWidth,theHeight,theImage);
  }
  public void step(World world)
  {
  }
  public void left()
  {
    if(getLeft()>0)
      setLeft(getLeft()-10);
  }
  
  public void right()
  {
    if(getLeft()+getWidth()<500)
    setLeft(getLeft()+10);
  }
  
  public void shoot(World world)
  {
      world.getSprites().add(new Projectile(0,-5,getLeft()+getWidth()/2,getTop(), 3,9,"laser.png"));
  }
}