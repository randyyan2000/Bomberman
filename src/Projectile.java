import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Projectile extends MobileSprite
{
  public Projectile(double x, double y, double theLeft, double theTop, int theWidth, int theHeight, String theImage)
  {
    super(x,y,theLeft,theTop,theWidth,theHeight,theImage);
  }
  
  public void step(World world)
  {
    setLeft(getLeft() + getVX());
    setTop(getTop() + getVY());
    ArrayList<Sprite> sprites = world.getSprites();
    for(int i=0;i<sprites.size();i++)
    {
      String k = sprites.get(i).getImage();
      if(k.equals("square.png") || k.equals("alien.png"))
      {
        if(this.overlap(sprites.get(i)))
        {
          this.kill();
          sprites.get(i).kill();
        }
      }
    }
    if(getTop()+getHeight()>world.getHeight())
    {
      kill();
    }
    else if(getTop()<0)
    {
      kill();
    }
    else if (getLeft()+getWidth()>world.getWidth())
    {
      kill();
    }
    else if(getLeft()<0)
    {
      kill();
    }
  }
}