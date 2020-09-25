import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

public class World
{
  public static void main(String[] args)
  {
    int select = JOptionPane.CLOSED_OPTION;
    while(select == JOptionPane.CLOSED_OPTION)
    {
      select = JOptionPane.showOptionDialog(null,"How many players?", "Bomberman", 0, 3, null, new Object[] {1,2,3,4}, null);
    }
    
    int nP = 1 + select;
    Display display = new Display(600, 520, nP);
    display.run();
  }
  
  private ArrayList<Sprite> sprites;
  private ArrayList<Sprite> walls;
  private ArrayList<Sprite> items;
  private int width;
  private int height;
  private boolean pressUp1;
  private boolean pressUp2;
  private boolean pressUp3;
  private boolean pressUp4;
  private boolean pressDown1;
  private boolean pressDown2;
  private boolean pressDown3;
  private boolean pressDown4;
  private boolean pressLeft1;
  private boolean pressLeft2;
  private boolean pressLeft3;
  private boolean pressLeft4;
  private boolean pressRight1;
  private boolean pressRight2;
  private boolean pressRight3;
  private boolean pressRight4;
  private int numPlayers;
  private int numAlive;
//  private Display display;
  
  public World(int w, int h, int nP)
  {
    width = w;
    height = h;
    
    sprites = new ArrayList<Sprite>();
    walls = new ArrayList<Sprite>();
    items = new ArrayList<Sprite>();
    makeMap(walls,w,h);
    for(int i=0; i < nP ; i++)
    {
      sprites.add(i, new PlayerSprite(i+1));
    }
    
    numPlayers = nP;
    numAlive = nP;
//    display = d;
  }
  
  public ArrayList<Sprite> getSprites()
  {
    return sprites;
  }
  
  public void stepAll()
  {
    if(numAlive == 1 && numPlayers != 1)
    {
      for(int i=0; i<numPlayers; i++)
      {
        if(sprites.get(i) instanceof PlayerSprite)
        {
          end(i+1);
        }
      }
    }
    for(int i=0;i<sprites.size();i++)
    {
      
      Sprite sprite = sprites.get(i);
      sprite.step(this);
      if(!sprite.isAlive())
      {
        sprites.remove(i);
        i--;
        if(sprite instanceof Bomb)
        {
          boom((Bomb)sprite);
        }
        else if(sprite instanceof PlayerSprite)
        {
          sprites.add(((PlayerSprite)sprite).getPNum()-1, new DeadSprite((PlayerSprite)sprite));
          numAlive--;
        }
      }
      
      
      if(i>=sprites.size())
        break;
      if(sprites.get(0) instanceof PlayerSprite)
      {
        PlayerSprite player1 = (PlayerSprite)sprites.get(0);
        if(pressUp1)
          player1.up(this);
        if(pressDown1)
          player1.down(this);
        if(pressRight1)
          player1.right(this);
        if(pressLeft1)
          player1.left(this);
      }
      
      if(numPlayers>1)
      {
        if(sprites.get(1) instanceof PlayerSprite)
        {
          PlayerSprite player2 = (PlayerSprite) sprites.get(1);
          if(pressUp2)
            player2.up(this);
          if(pressDown2)
            player2.down(this);
          if(pressLeft2)
            player2.left(this);
          if(pressRight2)
            player2.right(this);
        }
      }
      
      if(numPlayers>2)
      {
        if(sprites.get(2) instanceof PlayerSprite)
        {
          PlayerSprite player3 = (PlayerSprite) sprites.get(2);
          if(pressUp3)
            player3.up(this);
          if(pressDown3)
            player3.down(this);
          if(pressLeft3)
            player3.left(this);
          if(pressRight3)
            player3.right(this);
        }
      }
      
      if(numPlayers>3)
      {
        if(sprites.get(3) instanceof PlayerSprite)
        {
          PlayerSprite player4 = (PlayerSprite) sprites.get(3);
          if(pressUp4)
            player4.up(this);
          if(pressDown4)
            player4.down(this);
          if(pressLeft4)
            player4.left(this);
          if(pressRight4)
            player4.right(this);
        }
      }
    }
    //if(Math.random()<.5)
      //sprites.add(new Sprite(Math.random()*500,Math.random()*500,10,10,"triangle.png"));
//    
//    for(int i=0;i<sprites.size();i++)
//    {
//      if(sprite.getImage().equals("triangle.png"))
//      {
//        for(int j=0;j<sprites.size();j++)
//        {
//          if(sprites.get(j).getImage().equals("circle.png"))
//          {
//            System.out.println("k");
//            if(sprite.overlap(sprites.get(j)))
//            {
//              sprites.remove(i);
//            }
//          }
//        }
//      }
//    }
  }
  
  public void boom(Bomb sprite)
  {
    double left = sprite.getLeft();
    double top = sprite.getTop();
    int range = sprite.getRange();
    sprites.add(sprites.size(),new Explosion(left,top,"boomC0.gif"));
    sweepTop(range,left,top);
    sweepDown(range,left,top);
    sweepLeft(range,left,top);
    sweepRight(range,left,top);
      
//    for(int i=1; i<=(range); i++)
//    {
//      System.out.println("ayyy");
//      Explosion n;
//      Explosion s;
//      Explosion e;
//      Explosion w;
//      if(i==range)
//      {
//        s = new Explosion(left,top+i*40,"boomS0.gif");
//        n = new Explosion(left,top-i*40,"boomN0.gif");
//        e = new Explosion(left+i*40,top,"boomE0.gif");
//        w = new Explosion(left-i*40,top,"boomW0.gif");
//      }
//      else
//      {
//        s = new Explosion(left,top+i*40,"boomV0.gif");
//        n = new Explosion(left,top-i*40,"boomV0.gif");
//        e = new Explosion(left+i*40,top,"boomH0.gif");
//        w = new Explosion(left-i*40,top,"boomH0.gif");
//      }
//      if(checkWalls(n))
//      {
//        System.out.println("collide");
//        sprites.add(2,n);
//      }
//      if(checkWalls(s))
//      {
//        System.out.println("collide");
//        sprites.add(2,s);
//      }
//      if(checkWalls(e))
//      {
//        System.out.println("collide");
//        sprites.add(2,e);
//      }
//      if(checkWalls(w))
//      {
//        System.out.println("collide");
//        sprites.add(2,w);
//      }
//    }
  }
  
  public void end(int n)
  {
    int select = JOptionPane.CLOSED_OPTION;
    while(select == JOptionPane.CLOSED_OPTION)
    {
      select = JOptionPane.showOptionDialog(null,"Player"+n+" wins!\nPlay Again?", "Game Over", 0, 3, null, new Object[] {"Maybe","Yes"}, null);
    }
    if(select == 1)
    {
       int select2 = JOptionPane.CLOSED_OPTION;
       while(select2 == JOptionPane.CLOSED_OPTION)
       {
         select2 = JOptionPane.showOptionDialog(null,"How many players?", "Bomberman", 0, 3, null, new Object[] {1,2,3,4}, null);
         System.out.println(select2);
         System.out.println(JOptionPane.CLOSED_OPTION);
       }
       int nP = 1 + select2;
       reset(nP);
    }
  }
  
  public void reset(int nP)
  {
    sprites = new ArrayList<Sprite>();
    walls = new ArrayList<Sprite>();
    items = new ArrayList<Sprite>();
    makeMap(walls,600,520);
    for(int i=0; i < nP ; i++)
    {
      sprites.add(i, new PlayerSprite(i+1));
    }
    
    numPlayers = nP;
    numAlive = nP;
    pressUp1 = false;
    pressUp2 = false;
    pressUp3 = false;
    pressUp4 = false;
    pressDown1 = false;
    pressDown2 = false;
    pressDown3 = false;
    pressDown4 = false;
    pressLeft1 = false;
    pressLeft2 = false;
    pressLeft3 = false;
    pressLeft4 = false;
    pressRight1 = false;
    pressRight2 = false;
    pressRight3 = false;
    pressRight4 = false;
  }
  
  public void sweepTop(int range, double left, double top)
  {
    for(int i=1; i<=(range); i++)
    {
      Explosion n;
      if(i==range)
      {
        n = new Explosion(left,top-i*40,"boomN0.gif");
      }
      else
      {
        n = new Explosion(left,top-i*40,"boomV0.gif");
      }
      if(boomBombs(n) && checkWalls(n))
      {
        sprites.add(sprites.size(),n);
      }
      else
      {
        return;
      }
    }
  }
  
  public boolean boomBombs(Sprite n)
  {
    for(int i=sprites.size()-1; i>=0; i--)
    {
      Sprite sprite = sprites.get(i);
      if(sprite instanceof Bomb)
      {
        if(n.overlap(sprite))
        {
          System.out.println("ayy");
          sprites.remove(i);
          boom((Bomb)sprite);
          return false;
        }
      }
    }
    return true;
  }
  
  public void sweepLeft(int range, double left, double top)
  {
    for(int i=1; i<=(range); i++)
    {
      Explosion w;
      if(i==range)
      {
        w = new Explosion(left-i*40,top,"boomW0.gif");
      }
      else
      {
        w = new Explosion(left-i*40,top,"boomH0.gif");
      }
      if(boomBombs(w) && checkWalls(w))
      {
        sprites.add(sprites.size(),w);
      }
      else
        return;
    }
  }
  
  public void sweepDown(int range, double left, double top)
  {
    for(int i=1; i<=(range); i++)
    {
      Explosion s;
      if(i==range)
      {
        s = new Explosion(left,top+i*40,"boomS0.gif");
      }
      else
      {
        s = new Explosion(left,top+i*40,"boomV0.gif");
      }
      if(boomBombs(s) && checkWalls(s))
      {
        sprites.add(sprites.size(),s);
      }
      else
        return;
    }
  }
  
  public void sweepRight(int range, double left, double top)
  {
    for(int i=1; i<=(range); i++)
    {
      Explosion e;
      if(i==range)
      {
        e = new Explosion(left+i*40,top,"boomE0.gif");
      }
      else
      {
        e = new Explosion(left+i*40,top,"boomH0.gif");
      }
      if(boomBombs(e) && checkWalls(e))
      {
        sprites.add(sprites.size(),e);
      }
      else
         return;
    }
  }
  
  
  public boolean checkWalls(Sprite s)
  {
    for(int i=0; i<walls.size()-1; i++)
    {
      Sprite wall = walls.get(i);
      if(s.overlap(wall))
      {
//        System.out.println(i+" fail");
//        System.out.println(s.getLeft()+" "+s.getTop());
//        System.out.println(wall.getLeft()+" "+wall.getTop());
        if(wall.getImage().equals("wallSoft.gif"))
        {
          walls.remove(i);
          if(Math.random()<0.30) /*powerup chance*/
          {
            int t = (int)(Math.random()*6);
            items.add(new ItemSprite(wall.getLeft(),wall.getTop(),t));
          }
        }
        return false;
      }
    }
    return true;
  }
  
  public int getWidth()
  {
    return width;
  }
  
  public int getHeight()
  {
    return height;
  }
  
  public int getNumSprites()
  {
    return sprites.size();
  }
  
  public Sprite getSprite(int index)
  {
    return sprites.get(index);
  }

  public void mouseClicked(int x, int y)
  {
    System.out.println("mouseClicked:  " + x + ", " + y);
  }
  
  public void keyPressed(int key)
  {
    if(sprites.size()>0 && sprites.get(0) instanceof PlayerSprite)
    {
      PlayerSprite player1 = (PlayerSprite)sprites.get(0);
      
      if(key == 65)
        pressLeft1 = true;
      else if(key == 68)
        pressRight1 = true;
      else if(key == 87)
        pressUp1 = true;
      else if(key == 83)
        pressDown1 = true;
      else if(key == 81)
      {
        if(checkBombs(player1))
        {
          int[] loc = player1.getLocation();
          sprites.add(numPlayers,new Bomb(loc[0],loc[1],(player1)));
          player1.bombOverlap();
        }
      }
    }
    
    if(numPlayers>1 && sprites.get(1) instanceof PlayerSprite)
    {
      PlayerSprite player2 = (PlayerSprite)sprites.get(1);
      if(key == 37)
        pressLeft2 = true;
      else if(key == 39)
        pressRight2 = true;
      else if(key == 38)
        pressUp2 = true;
      else if(key == 40)
        pressDown2 = true;
      else if(key == 16)
      {
        if(checkBombs((PlayerSprite)player2))
        {
          int[] loc = ((PlayerSprite)player2).getLocation();
          sprites.add(numPlayers,new Bomb(loc[0],loc[1],(player2)));
          player2.bombOverlap();
        }
      }
    }
    
    if(numPlayers>2 && sprites.get(2) instanceof PlayerSprite)
    {
      PlayerSprite player3 = (PlayerSprite)sprites.get(2);
      if(key == 80)
        pressUp3 = true;
      else if(key == 76)
        pressLeft3 = true;
      else if(key == 59)
        pressDown3 = true;
      else if(key == 222)
        pressRight3 = true;
      else if(key == 79)
      {
        if(checkBombs(player3))
        {
          int[] loc = (player3).getLocation();
          sprites.add(numPlayers,new Bomb(loc[0],loc[1],(player3)));
          player3.bombOverlap();
        }
      }
    }
    
    if(numPlayers>3 && sprites.get(3) instanceof PlayerSprite)
    {
      PlayerSprite player4 = (PlayerSprite)sprites.get(3);
      if(key == 89)
        pressUp4 = true;
      else if(key == 71)
        pressLeft4 = true;
      else if(key == 72)
        pressDown4 = true;
      else if(key == 74)
        pressRight4 = true;
      else if(key == 84)
      {
        if(checkBombs(player4))
        {
          int[] loc = (player4).getLocation();
          sprites.add(numPlayers,new Bomb(loc[0],loc[1],(player4)));
          player4.bombOverlap();
        }
      }
    }
   
    
//    System.out.println("keyPressed:  " + key);
    
  }
  
  public boolean checkBombs(PlayerSprite sprite)
  {
   int limit = sprite.getBombs();
   int count = 0;
   for(int i=1; i<sprites.size(); i++)
   {
     Sprite check = sprites.get(i);
     if(check instanceof Bomb)
     {
       if(((Bomb)check).getOwner().equals(sprite))
         count++;
       if(count>=limit)
         return false;
     }
   }
   return true;
  }
  
  public void keyReleased(int key)
  {
    //P1 controls
    if(key == 65)
      pressLeft1 = false;
    else if(key == 68)
      pressRight1 = false;
    else if(key == 87)
      pressUp1 = false;
    else if(key == 83)
      pressDown1 = false;
    
    //P2 controls
    else if(key == 37)
      pressLeft2 = false;
    else if(key == 39)
      pressRight2 = false;
    else if(key == 38)
      pressUp2 = false;
    else if(key == 40)
      pressDown2 = false;
    
    //P3 controls
    else if(key == 80)
      pressUp3 = false;
    else if(key == 76)
      pressLeft3 = false;
    else if(key == 59)
      pressDown3 = false;
    else if(key == 222)
      pressRight3 = false;
    
    //P4 controls
    else if(key == 89)
      pressUp4 = false;
    else if(key == 71)
      pressLeft4 = false;
    else if(key == 72)
      pressDown4 = false;
    else if(key == 74)
      pressRight4 = false;
//    System.out.println("keyReleased:  " + key);
  }
  
  public String getTitle()
  {
    return "World";
  }
  
  public ArrayList<Sprite> getItems()
  {
    return items;
  }
  
  public ArrayList<Sprite> getWalls()
  {
    return walls;
  }

  public void paintComponent(Graphics g)
  {
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, width, height);
    
    for (int i = walls.size()-1; i >=0 && i<walls.size() ; i--)
    {
      Sprite sprite = walls.get(i);
      g.drawImage(Display.getImage(sprite.getImage()),
                  (int)sprite.getLeft(), (int)sprite.getTop(),
                  sprite.getWidth(), sprite.getHeight(), null);
    }
    for (int i = items.size()-1; i >=0 ; i--)
    {
      Sprite sprite = items.get(i);
      g.drawImage(Display.getImage(sprite.getImage()),
                  (int)sprite.getLeft(), (int)sprite.getTop(),
                  sprite.getWidth(), sprite.getHeight(), null);
    }
    for (int i = sprites.size()-1; i >= 0 && i<sprites.size() ; i--)
    {
      Sprite sprite = sprites.get(i);
      g.drawImage(Display.getImage(sprite.getImage()),
                  (int)sprite.getLeft(), (int)sprite.getTop(),
                  sprite.getWidth(), sprite.getHeight(), null);
    }
  }
  
  public void makeMap(ArrayList<Sprite> walls, int w, int h)
  {
    
    for(int l=40; l<=w-80; l+=80)
    {
      for(int t=40; t<=h-80; t+=80)
      {
        boolean top = ((l<160 || l>=w-160) &&(t==40 || t==h-80));
        boolean side = ((t<160 || t>=h-160) &&(l==40 || l==w-80));
        if(!(top || side))
        { 
          if(Math.random()<0.75)
          {
            walls.add(new Sprite(l,t,40,40,"wallSoft.gif"));
          }
        }
      }
    }
    for(int l=80; l<=w-80; l+=80)
    {
      for(int t=40; t<=h-80; t+=80)
      {
        boolean top = ((l<160 || l>=w-160) &&(t==40 || t==h-80));
        boolean side = ((t<160 || t>=h-160) &&(l==40 || l==w-80));
        if(!(top || side))
        { 
          walls.add(new Sprite(l,t,40,40,"wallSoft.gif"));
        }
      }
    }
    for(int l=40; l<=w-80; l+=80)
    {
      for(int t=80; t<=h-80; t+=80)
      {
        boolean top = ((l<160 || l>=w-160) &&(t==40 || t==h-80));
        boolean side = ((t<160 || t>=h-160) &&(l==40 || l==w-80));
        if(!(top || side))
        { 
          walls.add(new Sprite(l,t,40,40,"wallSoft.gif"));
        }
      }
    }
    
    for(int l=80; l<w-40; l+=80)
    {
      for(int t=80; t<h-40; t+=80)
      {
        walls.add(new Sprite(l,t,40,40,"wall.gif"));
      }
    }
    for(int i=0; i<w; i+=40)
    {
      walls.add(new Sprite(i,0,40,40,"wall.gif"));
      walls.add(new Sprite(i,480,40,40,"wall.gif"));
    }
    for(int i=40; i<h-40; i+=40)
    {
      walls.add(new Sprite(0,i,40,40,"wall.gif"));
      walls.add(new Sprite(560,i,40,40,"wall.gif"));
    }
    walls.add(new Sprite(40,40,560,440,"grass.gif"));
//    for(int l=40; l<w-40; l+=40)
//    {
//      for(int t=40; t<h-40; t+=40)
//      {
//        walls.add(new Sprite(l,t,40,40,"grass.gif"));
//      }
//    }
    
  }
}