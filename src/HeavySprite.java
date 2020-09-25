 public class HeavySprite extends MobileSprite
{
  public HeavySprite(int x, int y, double theLeft, double theTop, int theWidth, int theHeight, String theImage)
  {
    super(x,y,theLeft,theTop,theWidth,theHeight,theImage);
  }
  
  public void step(World world)
  {
    setVY(getVY()+0.1);
    super.step(world);
  }
  
}