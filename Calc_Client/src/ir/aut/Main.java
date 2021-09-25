package ir.aut;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        InetAddress ip = InetAddress.getLocalHost();
        int port = 4444;
        Scanner sc = new Scanner(System.in);

        // Step 1: Open the socket connection.
        Socket s = new Socket(ip, port);

        // Step 2: Communication-get the input and output stream
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        while (true)
        {
            // Enter the equation in the form-
            // "operand1 operation operand2"
            System.out.print("Enter the equation in the form: ");
            System.out.println("'operand operator operand'");

            String inp = sc.nextLine();

            if (inp.equals("bye"))
                break;

            // send the equation to server
            dos.writeUTF(inp);

            // wait till request is processed and sent back to client
            String ans = dis.readUTF();
            System.out.println( ans);
        }
    }
}