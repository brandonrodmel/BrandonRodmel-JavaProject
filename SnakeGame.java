import java.awt.event.KeyListener;
import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Font;

import java.util.Scanner; 
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class SnakeGame extends Canvas implements KeyListener, Runnable
{
  
  private final int WIDTH = 800;
	private final int HEIGHT = 600;

  private BufferedImage back;
  private boolean[] controls;

  private Snake snake;
  private Apple apple;
  private int time;
  private int score;
  private String name;

  private boolean gameOn;


  public SnakeGame() 
  {
    setBackground(Color.black);

    controls = new boolean[7];

    snake = new Snake();
    apple = new Apple();
    score = 0;
    name = Main.name;

    this.addKeyListener(this);
		new Thread(this).start();
		setVisible(true);
  }

  public void update(Graphics window)
  {
    paint(window);
  }
  
  public void paint(Graphics window)
	{
    Graphics2D twoDGraph = (Graphics2D) window;

    if(back==null)
      back = (BufferedImage)(createImage(getWidth(), getHeight()));

    Graphics graphToBack = back.createGraphics();

    graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0, 0, WIDTH, HEIGHT);

    //START SCREEN
    if(gameOn == false && time == 0) {

      graphToBack.setColor(Color.WHITE);
      graphToBack.drawString("PRESS F TO START", WIDTH/4, HEIGHT*2/3);

      graphToBack.drawString("USE ARROWS TO MOVE", WIDTH/4, HEIGHT*4/5);
      
      graphToBack.setColor(Color.GREEN);
      graphToBack.setFont(new Font("MONOSPACED", Font.PLAIN, 100)); 
      graphToBack.drawString("SNAKE", WIDTH/4, HEIGHT/2);

      if(controls[6] == true) {
        gameOn = true;
      }

    } else {

      snake.draw(graphToBack);
      apple.draw(graphToBack);

      //SNAKE MOVEMENT
      if(controls[0] == true && time % 15 == 0) {
        snake.moveUp();
      }
      if(controls[1] == true && time % 15 == 0) {
        snake.moveDown();
      }
      if(controls[2] == true && time % 15 == 0) {
        snake.moveLeft();
      }
      if(controls[3] == true && time % 15 == 0) {
        snake.moveRight();
      }

      //INTERACTION BETWEEN SNAKE & FRUIT
      if(apple.getX() == snake.getSegment(0).getX() && apple.getY() == snake.getSegment(0).getY()) {
        //TODO - ADD A WAY TO PREVENT APPLE FROM LANDING ON SNAKE
        int currX = apple.getX();
        int currY = apple.getY();
        int randomX = (int) (Math.random()*(WIDTH/20 + 1)) * 20;
        int randomY = (int) (Math.random()*(HEIGHT/20 + 1)) * 20;
        apple.setPos(randomX, randomY);
        apple.power(snake);
        score++;
      }

      //SNAKE COLLISION - CAUSES GAME TO END
      for(int i=1; i<snake.getList().size(); i++)
      {
        Segment s = snake.getSegment(0);
        Segment currSegment = snake.getSegment(i);
        if(s.getX() == currSegment.getX() && s.getY() == currSegment.getY()) {
          //DISABLES MOVEMENT CONTROLS && ALLOWS PLAYER TO RESTART
          gameOn = false;
          controls[0] = false;
          controls[1] = false;
          controls[2] = false;
          controls[3] = false;
        
          //GRAPHICS
          graphToBack.setColor(Color.BLACK);
		      graphToBack.fillRect(0, 0, WIDTH, HEIGHT);

          graphToBack.setColor(Color.RED);
          graphToBack.drawString("GAME OVER", WIDTH/2, HEIGHT/2);
        
          graphToBack.setColor(Color.GREEN);
          graphToBack.drawString("FINAL SCORE - " + score, WIDTH/2, HEIGHT/2 + 10);
        
          graphToBack.setColor(Color.YELLOW);
          graphToBack.drawString("PRESS H TO VIEW HIGHSCORES", WIDTH/2, HEIGHT/2 + 20);

          graphToBack.setColor(Color.WHITE);
          graphToBack.drawString("PRESS R TO RESTART", WIDTH/2, HEIGHT/2 + 30);

          //SHOWS HIGHSCORES
          if(controls[5] == true) {
            try {
              File file = new File("highscores.txt");

              Scanner scan = new Scanner(file);

              ArrayList<String> names = new ArrayList<String>();
              ArrayList<Integer> scores = new ArrayList<Integer>();

              //FILLS LIST
              while(scan.hasNext())
              {
                names.add(scan.next());
                scores.add(scan.nextInt());
              }

              int index = 0;

              for(int j=0; j<scores.size(); j++) 
              {
                if(scores.get(j) < score)
                {
                  names.add(j, name);
                  scores.add(j, score);

                  names.remove(scores.size()-1);
                  scores.remove(scores.size()-1);
                  break;
                }
              }

              // System.out.println(names);
              // System.out.println(scores);

              BufferedWriter bw = new BufferedWriter(new FileWriter(file));

              for(int j=0; j<names.size(); j++) {
                bw.write(names.get(j) + " " + scores.get(j) + "\n");
              }

              bw.close();

              //PRINTS LIST ON SCREEN
              graphToBack.setColor(Color.BLACK);
		          graphToBack.fillRect(0, 0, WIDTH, HEIGHT);
              graphToBack.setColor(Color.WHITE);
              for(int j=0; j<names.size(); j++)
              {
                graphToBack.drawString(names.get(j) + " - " + scores.get(j), 10, 10+10*j);
              }

            } catch(Exception e) {

            }
 
          }
          //RESTARTS GAME
          if(controls[4] == true) {
            snake = new Snake();
            apple = new Apple();
            gameOn = true;
            score = 0;
          }
        }
      }
    time++;  
    }

    twoDGraph.drawImage(back, null, 0, 0);
  }

  public void keyPressed(KeyEvent e)
	{
    if(e.getKeyCode() == KeyEvent.VK_UP && controls[1] == false && gameOn == true)
		{
			controls[0] = true;

      controls[1] = false;
      controls[2] = false;
      controls[3] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN && controls[0] == false && gameOn == true)
		{
			controls[0] = false;

      controls[1] = true;

      controls[2] = false;
      controls[3] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT && controls[3] == false && gameOn == true)
		{
			controls[0] = false;
      controls[1] = false;

      controls[2] = true;

      controls[3] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && controls[2] == false && gameOn == true)
		{
			controls[0] = false;
      controls[1] = false;
      controls[2] = false;

      controls[3] = true;
		}
    if(e.getKeyCode() == KeyEvent.VK_R && gameOn == false)
    {
      controls[4] = true;
    }
    if(e.getKeyCode() == KeyEvent.VK_H && gameOn == false)
    {
      controls[5] = true;
    }
    if(e.getKeyCode() == KeyEvent.VK_F && gameOn == false)
    {
      controls[6] = true;
    }

		repaint();
	}

	public void keyReleased(KeyEvent e)
	{
	}

	public void keyTyped(KeyEvent e)
	{
	}

  public void run()
  {
   	try {
   	  while(true)
   	  {
   	 	  Thread.currentThread().sleep(5);
        repaint();
      }
    } catch(Exception e) {
    }
  }
}