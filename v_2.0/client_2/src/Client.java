import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args)
    {
        try(Socket socket = new Socket("localhost", 3369))
        {

            DataInputStream inStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
            Scanner in = new Scanner(System.in);

            System.out.println("Соединение установлено");
            System.out.println("Для выхода напишите 'exit' ");

            while (!socket.isOutputShutdown())
            {
                    String message = in.nextLine();
                    outStream.writeUTF(message);

                    System.out.println("Сообщение отправлено ");
                    outStream.flush();
                    if(message.equalsIgnoreCase("exit"))
                     {
                          break;

                     }
                    String message2 = inStream.readUTF();
                    System.out.println("Сообщение от сервера: " + message2);




            }

            System.out.println("Соединение разорвано ");

            inStream.close();
            outStream.close();
            socket.close();


        }


        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
