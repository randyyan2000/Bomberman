import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.imageio.*;

public class Sprite
{
  private double left;  //the x-coordinate of the left edge of the sprite
  private double top;   //the y-coordinate of the top edge of the sprite
  private int width;
  private int height;
  private boolean alive;
  private String image;
  
  public Sprite(double theLeft, double theTop, int theWidth, int theHeight, String theImage)
  {
    alive = true;
    left = theLeft;
    top = theTop;
    width = theWidth;
    height = theHeight;
    setImage(theImage);
  }
  
  public boolean overlap(Sprite other)
  {
    return (left<(other.getLeft()+other.getWidth()) && top<(other.getTop()+other.getHeight()) 
                    && (top+height)>other.getTop() && (left+width)>other.getLeft());
  }
  
  public void kill()
  {
    alive = false;
  }
  
  public boolean isAlive()
  {
    return alive;
  }
  
  public double getLeft()
  {
    return left;
  }
  
  public void setLeft(double l)
  {
    left = l;
  }
  
  public double getTop()
  {
    return top;
  }
  
  public void setTop(double t)
  {
    top = t;
  }
  
  public int getWidth()
  {
    return width;
  }
  
  public void setWidth(int w)
  {
    width = w;
  }
  
  public int getHeight()
  {
    return height;
  }
  
  public void setHeight(int h)
  {
    height = h;
  }
  
  public String getImage()
  {
    return image;
  }
  
  public void setImage(String i)
  {
    image = i;
  }
  
  public void step(World world)
  {
  }
}
