import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;

public class Snake {

  private ArrayList<Segment> body;
  private Color color;
  private int length;

  public Snake()
  {
    this(100, 100, 4, Color.GREEN);
  }

  public Snake(int x, int y, int l, Color c)
  {
    setLength(l);
    setColor(c);

    body = new ArrayList<Segment>();

    int spacer = 0;
    for(int i=0; i<getLength(); i++) 
    {
      body.add(new Segment(x+spacer, y, 20, 20, getColor(), "L"));
      spacer = spacer + 20;
    }
  }

  //METHODS
  public void addSegment()
  {
    //TEMP VAR FOR EASE OF ACCESS - GETS THE LAST SEGMENT IN LIST
    Segment s = body.get(body.size()-1);

    if(s.getDirection() == "U")
    {
      body.add(new Segment(s.getX(), s.getY()+20, 20, 20, getColor(), s.getDirection()));
    }
    if(s.getDirection() == "D")
    {
      body.add(new Segment(s.getX(), s.getY()-20, 20, 20, getColor(), s.getDirection()));
    }
    if(s.getDirection() == "R")
    {
      body.add(new Segment(s.getX()+20, s.getY(), 20, 20, getColor(), s.getDirection()));
    }
    if(s.getDirection() == "L")
    {
      body.add(new Segment(s.getX()-20, s.getY(), 20, 20, getColor(), s.getDirection()));
    }
  }

  public void draw(Graphics window)
  {
    for(Segment s : body)
    {
      s.draw(window);
    }
  }

  public void moveUp()
  {
    for(int i=body.size()-1; i>=0; i--)
    {
      if(i == 0) {
        body.get(0).moveUp();
      } else {
        body.get(i).setPos(body.get(i-1).getX(), body.get(i-1).getY());
        body.get(i).setDirection(body.get(i-1).getDirection());
      }
    }
  }

  public void moveDown()
  {
    for(int i=body.size()-1; i>=0; i--)
    {
      if(i == 0) {
        body.get(0).moveDown();
      } else {
        body.get(i).setPos(body.get(i-1).getX(), body.get(i-1).getY());
        body.get(i).setDirection(body.get(i-1).getDirection());
      }
    }
  }

  public void moveRight()
  {
    for(int i=body.size()-1; i>=0; i--)
    {
      if(i == 0) {
        body.get(0).moveRight();
      } else {
        body.get(i).setPos(body.get(i-1).getX(), body.get(i-1).getY());
        body.get(i).setDirection(body.get(i-1).getDirection());
      }
    }
  }

  public void moveLeft()
  {
    for(int i=body.size()-1; i>=0; i--)
    {
      if(i == 0) {
        body.get(0).moveLeft();
      } else {
        body.get(i).setPos(body.get(i-1).getX(), body.get(i-1).getY());
        body.get(i).setDirection(body.get(i-1).getDirection());
      }
    }
  }

  public void deleteSnake()
  {
    for(int i=0; i<=length; i++) 
    {
      body.remove(0);
    }
  }


  //SETTERS & GETTERS
  public void setLength(int l)
  {
    length = l;
  }

  public void setColor(Color c)
  {
    color = c;
  }

  public int getLength()
  {
    return length;
  }

  public Color getColor()
  {
    return color;
  }

  public ArrayList<Segment> getList()
  {
    return body;
  }

  public Segment getSegment(int i)
  {
    return body.get(i);
  }
}