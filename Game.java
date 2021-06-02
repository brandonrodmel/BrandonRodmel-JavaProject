import javax.swing.JFrame;
import java.awt.Component;
import java.util.Scanner;

public class Game extends JFrame
{

  private final int WIDTH = 800;
	private final int HEIGHT = 600;

  public Game()
  {
    super("Snake");
		setSize(WIDTH, HEIGHT);

    SnakeGame sG = new SnakeGame();

    ((Component)sG).setFocusable(true);

		getContentPane().add(sG);

    setVisible(true);
  }

  public static void main( String args[] )
	{
		Game start = new Game();
	}
}