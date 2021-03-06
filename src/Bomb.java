public class Bomb extends Sprite
{
  private int count;
  private PlayerSprite owner;
  
  public Bomb(double theLeft, double theTop, PlayerSprite sprite)
  {
    super(theLeft, theTop, 40, 40, "bomb1.gif");
    count = 300;
    owner = sprite;
  }
  
  public void step(World world)
  {
    
    count--;
    if(count <=0)
    {
      kill();
    }
    else
    {
      if(count%20==0)
      {
        int n = Integer.parseInt(getImage().substring(4,5))+1;
        if(n==6)
          n=1;
        setImage("bomb"+n+".gif");
      }
    }
  }
  
  public PlayerSprite getOwner()
  {
    return owner;
  }
  
  public int getRange()
  {
    return owner.getBombRange();
  }
}