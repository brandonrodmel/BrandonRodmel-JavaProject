import java.awt.Color;
import java.awt.Graphics;

public class Object
{
  private int xPos;
  private int yPos;
  private int width;
  private int height;
  private Color color;

  public Object()
  {
    this(10, 10, 10, 10, Color.GREEN);
  }

  public Object(int x, int y, int w, int h, Color c) 
  {
    setPos(x, y);
    setWidth(w);
    setHeight(h);
    setColor(c);
  }

  //METHODS
  public void draw(Graphics window)
  {
    window.setColor(getColor());
    window.fillRect(getX(), getY(), getWidth(), getHeight());
  }

  //SETTERS & GETTERS
  public void setPos(int x, int y)
  {
    setX(x);
    setY(y);
  }

  public void setX(int x)
  {
    xPos = x;
  }

  public void setY(int y)
  {
    yPos = y;
  }

  public void setWidth(int w)
  {
    width = w;
  }

  public void setHeight(int h)
  {
    height = h;
  }

  public void setColor(Color c)
  {
    color = c;
  }

  public int getX()
  {
    return xPos;
  }

  public int getY()
  {
    return yPos;
  }

  public int getWidth()
  {
    return width;
  }

  public int getHeight()
  {
    return height;
  }

  public Color getColor()
  {
    return color;
  }

}