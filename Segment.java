import java.awt.Color;

public class Segment extends Object
{

  private int speed;
  private String direction;

  public Segment()
  {
    this(20, 20, 20, 20, Color.GREEN, "L");
  }

  public Segment(int x, int y, int w, int h, Color c, String d)
  {
    super(x, y, w, h, c);
    setSpeed(w);
    setDirection(d);
  }

  //METHODS
  public void moveUp()
  {
    setY(getY()-getSpeed());
    setDirection("U");
  }

  public void moveDown()
  {
    setY(getY()+getSpeed());
    setDirection("D");
  }

  public void moveRight()
  {
    setX(getX()+getSpeed());
    setDirection("R");
  }

  public void moveLeft()
  {
    setX(getX()-getSpeed());
    setDirection("L");
  }

  //SETTERS & GETTERS
  public void setSpeed(int s)
  {
    speed = s;
  }

  public void setDirection(String dir)
  {
    direction = dir;
  }

  public int getSpeed()
  {
    return speed;
  }

  public String getDirection()
  {
    return direction;
  }

}