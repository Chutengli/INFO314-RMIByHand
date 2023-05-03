import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    private static final int PORT = 10314;

    public static void main(String[] args) {
        Server server = new Server();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server is listening at port: " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(() -> server.serverService(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void serverService(Socket socket) {
        System.out.println("Connected!");
        try {
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            RemoteMethod remoteMethod = (RemoteMethod) objectInputStream.readObject();

            String methodName = remoteMethod.getName();
            ParamType[] methodParams = remoteMethod.getParams();
            Class<?>[] paramTypeList = new Class<?>[methodParams.length];
            Object[] paramValueList = new Object[methodParams.length];

            for (int i = 0; i < methodParams.length; i++) {
                switch (methodParams[i].getType()) { // add exceptions for primitive class
                    case "int":
                        paramTypeList[i] = int.class;
                        break;
                    case "byte":
                        paramTypeList[i] = byte.class;
                        break;
                    case "short":
                        paramTypeList[i] = short.class;
                        break;
                    case "long":
                        paramTypeList[i] = long.class;
                        break;
                    case "float":
                        paramTypeList[i] = float.class;
                        break;
                    case "double":
                        paramTypeList[i] = double.class;
                        break;
                    case "char":
                        paramTypeList[i] = char.class;
                        break;
                    case "boolean":
                        paramTypeList[i] = boolean.class;
                        break;
                    default:
                        paramTypeList[i] = Class.forName(methodParams[i].getType());
                }

                if(methodParams[i].getType().equals("int")) {  // add exceptions for primitive class
                    paramTypeList[i] = int.class;
                } else {
                    paramTypeList[i] = Class.forName(methodParams[i].getType());
                }
                paramValueList[i] = methodParams[i].getValue();
            }

            Method requestedMethod = Server.class.getMethod(methodName, paramTypeList);

            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            Object response;
            try {
                response = requestedMethod.invoke(null, paramValueList);
                objectOutputStream.writeObject(response);
            } catch (ArithmeticException | InvocationTargetException e) {
                objectOutputStream.writeObject(null);
            }

            inputStream.close();
            objectOutputStream.close();
            outputStream.close();
            objectOutputStream.close();
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException err) {
            err.printStackTrace();
        }

    }

    // Do not modify any code below tihs line
    // --------------------------------------
    public static String echo(String message) {
        return "You said " + message + "!";
    }
    public static int add(int lhs, int rhs) {
        return lhs + rhs;
    }
    public static int divide(int num, int denom) {
        if (denom == 0)
            throw new ArithmeticException();

        return num / denom;
    }
}