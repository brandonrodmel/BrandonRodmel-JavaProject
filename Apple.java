import java.awt.Color;
import java.awt.Graphics;

public class Apple extends Object
{

  public Apple()
  {
    this(20, 20, 20, 20, Color.RED);
  }

  public Apple(int x, int y, int w, int h, Color c)
  {
    super(x, y, w, h, c);
  }

  //METHODS
  public void power(Snake s)
  {
    s.addSegment();
  }
}