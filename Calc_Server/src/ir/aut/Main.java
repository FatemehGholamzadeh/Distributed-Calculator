package ir.aut;

// Java program to illustrate Server Side Programming
// for Simple Calculator using TCP

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {

        // Step 1: Establish the socket connection.
        ServerSocket ss = new ServerSocket(4444);
        Socket s = ss.accept();
        long startTime = 0;
        long endTime = 0;
        // Step 2: Processing the request.
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        while (true) {
            // wait for input
            String input = dis.readUTF();

            if (input.equals("bye"))
                break;

            System.out.println("Equation received:-" + input);
            double result = 0;

            // Use StringTokenizer to break the equation into operand and
            // operation
            StringTokenizer st = new StringTokenizer(input);

            double oprnd1 = Double.parseDouble(st.nextToken());
            String operation = st.nextToken();
            double oprnd2 = 0;
            if (operation.equals("Add") || operation.equals("Subtract") || operation.equals("Divide") || operation.equals("Multiply"))
                oprnd2 = Double.parseDouble(st.nextToken());


            // perform the required operation.

            if (operation.equals("Add")) {
                startTime = System.nanoTime();
                result = oprnd1 + oprnd2;
                endTime = System.nanoTime();
            } else if (operation.equals("Subtract")) {
                startTime = System.nanoTime();
                result = oprnd1 - oprnd2;
                endTime = System.nanoTime();
            } else if (operation.equals("Multiply")) {
                startTime = System.nanoTime();
                result = oprnd1 * oprnd2;
                endTime = System.nanoTime();
            } else if (operation.equals("Divide")) {
                startTime = System.nanoTime();
                result = oprnd1 / oprnd2;
                endTime = System.nanoTime();
            } else if (operation.equals("Sin")) {
                startTime = System.nanoTime();
                result = Math.sin(Math.toRadians(oprnd1));
                endTime = System.nanoTime();

            } else if (operation.equals("Cos")) {
                startTime = System.nanoTime();
                result = Math.cos(Math.toRadians(oprnd1));
                endTime = System.nanoTime();

            } else if (operation.equals("Tan")) {
                startTime = System.nanoTime();
                result = Math.tan(Math.toRadians(oprnd1));
                endTime = System.nanoTime();

            } else if (operation.equals("Cot")) {
                startTime = System.nanoTime();
                result = 1 / Math.tan(Math.toRadians(oprnd1));
                endTime = System.nanoTime();

            }

            System.out.println("Sending the result...");

            long totalTime = endTime - startTime;
            // send the result back to the client.
            dos.writeUTF("Calculation Response:\n" + "$" + totalTime + " nano sec" + "$" + Double.toString(result) + "$");
        }
    }
}