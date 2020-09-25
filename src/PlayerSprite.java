import java.util.*;

public class PlayerSprite extends Sprite
{
  private int numBombs;
  private int bombRange;
  private int speed;
  private boolean kick;
  private Sprite overlap;
  private String playerNum;
  private String direction;
  //player num 1 or 2;
  public PlayerSprite(int n)
  {
    super(0,0,38,56,"P"+n+"down0.gif");
    playerNum = "P"+n;
    numBombs = 1;
    bombRange = 1;
    speed = 1;
    kick = false;
    direction = "down";
    setLeft(startLoc(n)[0]);
    setTop(startLoc(n)[1]);
  }
  
  public int[] startLoc(int i)
  {
    if(i == 1)
      return new int[] {40,20};
    else //(i == 2)
      return new int[] {520,420};
    }
  
  public void up(World world)
  {
    double newCor = getTop()-0.5*speed;
    if(!checkWalls(getLeft(), newCor, world))
    {
      newCor = overlap.getTop()+16;
    }
    setTop(newCor);
    String image = getImage();
    String num = image.substring(image.length()-5,image.length()-4);
    direction = "up";
    if(num.equals("0"))
    {
      setImage(playerNum+"up1.gif");
    }
    else if(num.equals("1"))
    {
      setImage(playerNum+"up2.gif");
    }
    else if(num.equals("2"))
    {
      setImage(playerNum+"up0.gif");
    }
  }
  
  public void down(World world)
  {
    double newCor = getTop()+0.5*speed;
    if(!checkWalls(getLeft(), newCor, world))
    {
      newCor = overlap.getTop()-56;
    }
    setTop(newCor);
    String image = getImage();
    String num = image.substring(image.length()-5,image.length()-4);
    direction = "down";
    if(num.equals("0"))
    {
      setImage(playerNum+"down1.gif");
    }
    else if(num.equals("1"))
    {
      setImage(playerNum+"down2.gif");
    }
    else if(num.equals("2"))
    {
      setImage(playerNum+"down0.gif");
    }
    
  }
  
  public void left(World world)
  {
    double newCor = getLeft()-0.5*speed;
    if(!checkWalls(newCor, getTop(), world))
    {
      newCor = overlap.getLeft()+40;
    }
    setLeft(newCor);
    String image = getImage();
    String num = image.substring(image.length()-5,image.length()-4);
    direction = "left";
    if(num.equals("0"))
    {
      setImage(playerNum+"left1.gif");
    }
    else if(num.equals("1"))
    {
      setImage(playerNum+"left2.gif");
    }
    else if(num.equals("2"))
    {
      setImage(playerNum+"left0.gif");
    }
    
  }
  
  public void right(World world)
  {
    double newCor = getLeft()+0.5*speed; //6+4*speed;
    if(!checkWalls(newCor, getTop(), world))
    {
      newCor = overlap.getLeft()-32;
    }
    setLeft(newCor);
    String image = getImage();
    String num = image.substring(image.length()-5,image.length()-4);
    direction = "right";
    if(num.equals("0"))
    {
      setImage(playerNum+"right1.gif");
    }
    else if(num.equals("1"))
    {
      setImage(playerNum+"right2.gif");
    }
    else if(num.equals("2"))
    {
      setImage(playerNum+"right0.gif");
    }
  }
  
  public void stand()
  {
    setImage(playerNum+direction+"0.gif");
  }
  
  
  
  public int[] getlocation()
  {
    int[] loc = new int[2];
    for(int i = 0; i<600; i+=40)
    {
      if(Math.abs(i-getLeft())<=20)
      {
        loc[0] = i;
      }
    }
    for(int i = 0; i<520; i+=40)
    {
      if(Math.abs(i-getTop()-20)<=20)
      {
        loc[1] = i;
      }
    }
    return loc;
  }
  
  public void step(World world)
  {
    checkItems(world);
    checkExplosions(world);
  }
  
  public boolean overlap(Sprite other)
  {
    return (getLeft()+8<(other.getLeft()+other.getWidth()) && (getTop()+40)<(other.getTop()+other.getHeight()) 
                    && (getTop()+getHeight())>other.getTop() && (getLeft()+getWidth()-8)>other.getLeft());
  }
  
  public void checkExplosions(World world)
  {
    ArrayList<Sprite> sprites = world.getSprites();
    for(int i=1; i<sprites.size(); i++)
    {
      Sprite sprite = sprites.get(i);
      if(sprite instanceof Explosion && overlap(sprite))
      {
        System.out.println(playerNum + " died");
        System.out.println("numBombs = " + numBombs);
        System.out.println("range = " + bombRange);
        System.out.println("speed = " + speed);
        kill();
      }
    }
  }
  
  public void checkItems(World world)
  {
    ArrayList<Sprite> items = world.getItems();
//    System.out.println(items);
    for(int i=items.size()-1; i>=0; i--)
    {
//      System.out.println(i);
      ItemSprite item = (ItemSprite)items.get(i);
      if(overlap(item))
      {
        int type = item.getType();
        power(type);
        items.remove(i);
      }
    }
  }
  
  public boolean checkWalls(double left, double top, World world)
  {
    ArrayList<Sprite> walls = world.getWalls();
    for(int i=0; i<walls.size()-1; i++)
    {
      Sprite other = walls.get(i);
      if(left+8<(other.getLeft()+other.getWidth()) && (top+30)<(other.getTop()+other.getHeight()) 
                    && (top+getHeight())>other.getTop() && (left+getWidth())-8>other.getLeft())
      {
        overlap = other;
        return false;
      }
    }
    return true;
  }
  
  public void power(int type)
  {
    if(type == ItemSprite.RANGEU)
      bombRangeUp();
    else if(type == ItemSprite.RANGED)
      bombRangeDown();
    else if(type == ItemSprite.BOMBU)
      bombUp();
    else if(type == ItemSprite.BOMBD)
      bombDown();
    else if(type == ItemSprite.SPEEDU)
      speedUp();
    else if(type == ItemSprite.SPEEDD)
      speedDown();
    
  }
  
  
  public void speedUp()
  {
    if(speed<=10)
    {
      speed++;
    }
  }
  
  public void speedDown()
  {
    if(speed!=1)
      speed--;
  }
  
  public void bombUp()
  {
    if(numBombs<=5)
    {
      numBombs++;
    }
  }
  
  public void bombDown()
  {
    if(numBombs!=1)
      numBombs--;
  }
  
  public void bombRangeUp()
  {
    if(bombRange<=7)
    {
      bombRange++;
    }
  }
  
  public void bombRangeDown()
  {
    if(bombRange!=1)
      bombRange--;
  }
  
  public void canKick()
  {
    if(!kick)
      kick = true;
  }
  
  public boolean kick()
  {
    return kick;
  }
  
  public int getBombRange()
  {
    return bombRange;
  }
  
  public int getBombs()
  {
    return numBombs;
  }
  
  public int getPNum()
  {
    return Integer.parseInt(playerNum.substring(1));
  }
}