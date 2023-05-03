import java.io.IOException;

public class UnitTest {
  public static void main(String... args) {
    System.out.print("Testing... ");

    try {
      if (Client.add(2, 4) == 6)
          System.out.print(".");
      else
          System.out.print("X");
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    try {
      if (Client.add(2, -4) == -2)
        System.out.print(".");
      else
        System.out.print("X");
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }

    try {
    if (Client.divide(20, 10) == 2)
        System.out.print(".");
    else
        System.out.print("X");
    } catch (ArithmeticException x) {
        System.out.print("X");
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }

    try {
        Client.divide(1, 0);
        System.out.print("X");
    } catch (ArithmeticException x) {
        System.out.print(".");
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }

      try {
          if (Client.echo("Hello").equals("You said Hello!"))
              System.out.print(".");
          else
              System.out.print("X");
      } catch (IOException | ClassNotFoundException e) {
          e.printStackTrace();
      }

      try {
          if (Client.echo("Flibbity-floog").equals("You said Flibbity-floog!"))
            System.out.print(".");
        else
            System.out.print("X");
      } catch (IOException | ClassNotFoundException e) {
          e.printStackTrace();
      }

      System.out.println(" Finished");
  }
}