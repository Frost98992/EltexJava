import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] args)
    {

        try(ServerSocket server = new ServerSocket(3369))
        {
            Socket client = server.accept();
            System.out.println("Соединение установлено");

            DataInputStream inStream = new DataInputStream(client.getInputStream());
            DataOutputStream outStream  = new DataOutputStream(client.getOutputStream());
            while (!client.isClosed())
            {
                String message = inStream.readUTF();
                System.out.println("Сообщение от клиента: " + message);

                String message2 = message.toUpperCase();
                if(message.equalsIgnoreCase("exit"))
                {
                    outStream.flush();
                    break;
                }

                outStream.writeUTF(message2);

                System.out.println("Сообщение отправлено ");
                outStream.flush();


            }
            System.out.println("Клиент отключился");
            inStream.close();
            outStream.close();
            client.close();


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



    }

}
