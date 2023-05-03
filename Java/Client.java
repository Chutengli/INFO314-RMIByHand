import java.io.*;
import java.net.Socket;

public class Client {

    private Object handleRequest(RemoteMethod remoteMethod) throws IOException, ClassNotFoundException {
        Socket socket = new Socket(server, PORT);
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(remoteMethod);

        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return objectInputStream.readObject();
    }

    /**
     * This method name and parameters must remain as-is
     */
    public static int add(int lhs, int rhs) throws IOException, ClassNotFoundException {
        Client client = new Client();
        RemoteMethod remoteMethod = new RemoteMethod("add",
                                                        new ParamType[]{new ParamType(lhs, "int"),
                                                        new ParamType(rhs, "int")});
        return (int) client.handleRequest(remoteMethod);
    }
    /**
     * This method name and parameters must remain as-is
     */
    public static int divide(int num, int denom) throws IOException, ClassNotFoundException, ArithmeticException {
        Client client = new Client();
        RemoteMethod remoteMethod = new RemoteMethod("divide",
                                                    new ParamType[]{new ParamType(num, "int"),
                                                    new ParamType(denom, "int")});

        Object result = client.handleRequest(remoteMethod);

        if(result == null) {
            throw new ArithmeticException();
        }

        return (int) result;
    }
    /**
     * This method name and parameters must remain as-is
     */
    public static String echo(String message) throws IOException, ClassNotFoundException {
        Client client = new Client();
        RemoteMethod remoteMethod = new RemoteMethod("echo", new ParamType[]{new ParamType(message, "java.lang.String")});
        return (String) client.handleRequest(remoteMethod);
    }

    // Do not modify any code below this line
    // --------------------------------------
    String server = "localhost";
    public static final int PORT = 10314;

    public static void main(String... args) throws IOException, ClassNotFoundException {
        // All of the code below this line must be uncommented
        // to be successfully graded.
        System.out.print("Testing... ");

        if (add(2, 4) == 6)
            System.out.print(".");
        else
            System.out.print("X");

        try {
            divide(1, 0);
            System.out.print("X");
        }
        catch (ArithmeticException x) {
            System.out.print(".");
        }

        if (echo("Hello").equals("You said Hello!"))
            System.out.print(".");
        else
            System.out.print("X");
        
        System.out.println(" Finished");
    }
}