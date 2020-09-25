public class ItemSprite extends Sprite
{
  int type;
  
  public static final int RANGEU = 0;
  public static final int RANGED = 1;
  public static final int BOMBU = 2;
  public static final int BOMBD = 3;
  public static final int SPEEDU = 4;
  public static final int SPEEDD = 5;
  public ItemSprite(double theLeft, double theTop, int t)
  {
    super(theLeft, theTop, 40, 40, t+".gif");
    type = t;
  }
  
  public int getType()
  {
    return type;
  }
}