import java.util.Scanner; 
import java.io.File;

class Main {

  public static String name;
  
  public static void main(String[] args) {

    Scanner scan = new Scanner(System.in);
    System.out.println("ENTER NAME: ");
    name = scan.nextLine();

    Game game = new Game();
    game.main(args);
  }
}