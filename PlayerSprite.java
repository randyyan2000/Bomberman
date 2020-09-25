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
  private int count;
  private boolean bombOverlap;
  //player num 1 or 2;
  public PlayerSprite(int n)
  {
    super(0,0,38,56,"P"+n+"down0.gif");
    playerNum = "P"+n;
    numBombs = 1;
    bombRange = 2;
    speed = 1;
    kick = false;
    direction = "down";
    setLeft(startLoc(n)[0]);
    setTop(startLoc(n)[1]);
    count = 40;
    bombOverlap = false;
  }
  
  public int[] startLoc(int i)
  {
    if(i == 1)
      return new int[] {40,20};
    else if(i == 2)
      return new int[] {520,420};
    else if(i == 3)
      return new int[] {520,20};
    else // i == 4
      return new int[] {40,420};
    }
  
  public void up(World world)
  {
    double newCor = getTop()-0.25-0.2*speed;
    if(!checkWalls(getLeft(), newCor, world) || !checkOverlapBombs(getLeft(), newCor, world))
    {
      newCor = overlap.getTop()+10;
    }
    setTop(newCor);
    String image = getImage();
    count--;
    if(!direction.equals("up"))
    {
      direction = "up";
      count = 0;
    }
    if(count == 0)
    {
      String num = image.substring(image.length()-5,image.length()-4);
      if(num.equals("0"))
        setImage(playerNum+"up1.gif");
      else if(num.equals("1"))
        setImage(playerNum+"up2.gif");
      else if(num.equals("2"))
        setImage(playerNum+"up0.gif");
      count = 40;
    }
  }
  
  public void down(World world)
  {
    double newCor = getTop()+0.25+0.2*speed;
    if(!checkWalls(getLeft(), newCor, world) || !checkOverlapBombs(getLeft(), newCor, world))
    {
//      System.out.println(overlap.getLeft() + " " + overlap.getTop());
//      System.out.println(getLeft() + " " + getTop());
      newCor = overlap.getTop()-56;
    }
    setTop(newCor);
    String image = getImage();
    count--;
    if(!direction.equals("down"))
    {
      direction = "down";
      count = 0;
    }
    if(count == 0)
    {
      String num = image.substring(image.length()-5,image.length()-4);
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
      count = 40;
    }
  }
  
  public void left(World world)
  {
    double newCor = getLeft()-0.25-0.2*speed;
    if(!checkWalls(newCor, getTop(), world) || !checkOverlapBombs(newCor, getTop(), world))
    {
      newCor = overlap.getLeft()+32;
    }
    setLeft(newCor);
    String image = getImage();
    count--;
    if(!direction.equals("left"))
    {
      direction = "left";
      count = 0;
    }
    if(count == 0)
    {
      String num = image.substring(image.length()-5,image.length()-4);
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
      count = 40;
    }
  }
  
  public void right(World world)
  {
    double newCor = getLeft()+0.25+0.2*speed; //6+4*speed;
    if(!checkWalls(newCor, getTop(), world) || !checkOverlapBombs(newCor, getTop(), world))
    {
      newCor = overlap.getLeft()-30;
    }
    setLeft(newCor);
    String image = getImage();
    String num = image.substring(image.length()-5,image.length()-4);
    count --;
    if(!direction.equals("right"))
    {
      direction = "right";
      count = 0;
    }
    if(count == 0)
    {
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
      count = 40;
    }
  }
  
  public void stand()
  {
    setImage(playerNum+direction+"0.gif");
  }
  
  public int[] getLocation()
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
  
  public int[] getLocation(double left, double top)
  {
    int[] loc = new int[2];
    for(int i = 0; i<600; i+=40)
    {
      if(Math.abs(i-left)<=20)
      {
        loc[0] = i;
      }
    }
    for(int i = 0; i<520; i+=40)
    {
      if(Math.abs(i-top-20)<=20)
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
    checkBombs(world);
  }
  
  public boolean overlap(Sprite other)
  {
    return (getLeft()+8<(other.getLeft()+other.getWidth()) && (getTop()+40)<(other.getTop()+other.getHeight()) 
                    && (getTop()+getHeight())>other.getTop() && (getLeft()+getWidth()-8)>other.getLeft());
  }
  
  public void checkBombs(World world)
  {
    ArrayList<Sprite> sprites = world.getSprites();
    for(int i=1; i<sprites.size(); i++)
    {
      Sprite sprite = sprites.get(i);
      if(sprite instanceof Bomb && overlap(sprite))
      {
        bombOverlap = true;
        return;
      }
    }
    bombOverlap = false;
    
    
    
    
    
    
    
    
    
//    
//    if(!bombOverlap)
//    {
//      ArrayList<Sprite> sprites = world.getSprites();
//      for(int i=0; i<sprites.size(); i++)
//      {
//        Sprite sprite = sprites.get(i);
//        if(sprite instanceof Bomb && overlap(sprite))
//        {
//          
////          int [] loc = getLocation();
////          if(loc[0] == sprite.getLeft() && loc[1] == sprite.getTop())
////          {
////            bombOverlap = true;
////            System.out.println("ayy");
////          }
////          if(overlap(sprite))
////          {
//            bombOverlap = true;
//            System.out.println("ayy");
////          }
//        }
//        bombOverlap = false;
//      }
//    }
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
//    System.out.println("checkingWalls");
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
  
  public boolean checkOverlapBombs(double left, double top, World world)
  {
//    System.out.println("checkingBombs");
    if(!bombOverlap)
    {
      ArrayList<Sprite> sprites = world.getSprites();
      for(int i=0; i<sprites.size(); i++)
      {
        Sprite sprite = sprites.get(i);
        if(sprite instanceof Bomb)
        {
          if(left+8<(sprite.getLeft()+sprite.getWidth()) && (top+40)<(sprite.getTop()+sprite.getHeight()) 
               && (top+getHeight())>sprite.getTop() && (left+getWidth())-8>sprite.getLeft())
          {
            overlap = sprite;
            return false;
          }
        }
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
  
  public void bombOverlap()
  {
    bombOverlap = true;
  }
}