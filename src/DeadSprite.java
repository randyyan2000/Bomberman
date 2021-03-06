public class DeadSprite extends Sprite
{
  private int count;
  private int playerNum;
  
  public DeadSprite(PlayerSprite player)
  {
    super(player.getLeft(),player.getTop(),40,60,"P"+player.getPNum()+"death0.gif");
    count = 80;
    playerNum = player.getPNum();
  }
  
  public void step(World world)
  {
    count--;
    if(count>0)
    {
      if(count%20==0)
      {
        String image = getImage();
        int n = Integer.parseInt(image.substring(7,8));
        setImage("P"+playerNum+"death"+(n+1)+(".gif"));
      }
    }
  }
}