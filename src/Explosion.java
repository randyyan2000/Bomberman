import java.util.*;
public class Explosion extends Sprite
{
  private int count;
  private boolean itemCheck;
//  private int bombCheck;
    
  public Explosion(double theLeft, double theTop, String image)
  {
    super(theLeft, theTop, 40, 40, image);
    count = 54;
    itemCheck = false;
//    bombCheck = 1;
  }
  
  public void step(World world)
  {
    if(itemCheck == false)
    {
      killItems(world);
    }
    boomBombs(world);
//    if(bombCheck == 0)
//    {
//      boomBombs(world);
//    }
//    else if(bombCheck == 1)
//      bombCheck--;
    itemCheck = true;
    String image = getImage();
    count--;
    if(count==0)
      kill();
    else if(count%6==0)
    { 
      int n = Integer.parseInt(getImage().substring(5,6))+1;
      setImage(image.substring(0,5)+n+image.substring(6));
    }
  }

  public void killItems(World world)
  {
    ArrayList<Sprite> items = world.getItems();
    for(int i=items.size()-1; i>=0; i--)
    {
      ItemSprite item = (ItemSprite)items.get(i);
      if(overlap(item))
      {
        items.remove(i);
        return;
      }
    }
  }
  
  public void boomBombs(World world)
  {
    ArrayList<Sprite> sprites = world.getSprites();
    for(int i=sprites.size()-1; i>=0; i--)
    {
      Sprite sprite = sprites.get(i);
      if(sprite instanceof Bomb)
      {
        if(overlap(sprite))
        {
          sprites.remove(i);
          world.boom((Bomb)sprite);
        }
      }
    }
  }
}