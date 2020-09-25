public class MobileSprite extends Sprite
{
  private double vx;
  private double vy;
  public MobileSprite(double x, double y, double theLeft, double theTop, int theWidth, int theHeight, String theImage)
  {
    super(theLeft, theTop, theWidth, theHeight, theImage);
    vx=x;
    vy=y;
  }
  
  public double getVY()
  {
    return vy;
  }
  
  public double getVX()
  {
    return vx;
  }
  
  public void setVY(double ha)
  {
    vy = ha;
  }
  
  public void setVX(double ha)
  {
    vx = ha;
  }
  
  
  public void step(World world)
  {
    setLeft(getLeft() + vx);
    setTop(getTop() + vy);
    
    if(getTop()+getHeight()>world.getHeight())
    {
      vy = -Math.abs(vy);
    }
    else if(getTop()<0)
    {
      vy = Math.abs(vy);
    }
    
    if(getLeft()+getWidth()>world.getWidth())
    {
      vx = -Math.abs(vx);
    }
    else if(getLeft()<0)
    {
      vx = Math.abs(vx);
    }
  }
}