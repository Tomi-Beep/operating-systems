import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient236024 {
    private static final String SERVER_IP = "194.149.135.49";
    private static final int SERVER_PORT = 9753;
    private static final String ID = "236024";
    private static final String LOG_FILE = "chatlog" + ID + ".txt";

    public static void main(String[] args) {
        try {
            // konektiranje do serverot
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Connected to server at " + SERVER_IP + ":" + SERVER_PORT);

            // setup na strimovite
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // prakjanje na login msg
            out.println("hello:" + ID);
            System.out.println("Sent login message: hello:" + ID);

            // odgovor na serverot
            String response = in.readLine();
            System.out.println(response);

            // proverka za dali bilo uspeshno
            if (response != null && response.contains("Succesfully")) {
                // prati vtor hello msg
                out.println("hello:" + ID);
                System.out.println("Sent second login message: hello:" + ID);

                // chita povtorno shto vika serverot
                String secondResponse = in.readLine();
                System.out.println(secondResponse);

                // pak proveruva
                if (secondResponse != null && secondResponse.contains("welcome")) {
                    // se otvara log file
                    PrintWriter logWriter = new PrintWriter(new FileWriter(LOG_FILE, true));

                    // se pishuvaat porakite od prethodno
                    logWriter.println("Sent: hello:" + ID);
                    logWriter.println(response);
                    logWriter.println("Sent: hello:" + ID);
                    logWriter.println(secondResponse);

                    // pravime novi threads za pishuvanje i chitanje
                    SenderThread sender = new SenderThread(out, logWriter);
                    ReceiverThread receiver = new ReceiverThread(in, logWriter);
                    sender.start();
                    receiver.start();

                    //chekame sender da zavrshi
                    sender.join();

                    //zatvarame se'
                    socket.close();
                    logWriter.close();
                    System.out.println("Disconnected from server and closed log file.");
                } else {
                    System.out.println("Second hello failed.");
                    socket.close();
                }
            } else {
                System.out.println("Login failed.");
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Error: Could not connect or communicate with server.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Error: Sender thread was interrupted.");
            e.printStackTrace();
        }
    }
}

// thread za prakjanje
class SenderThread extends Thread {
    private PrintWriter out;
    private PrintWriter logWriter;

    public SenderThread(PrintWriter out, PrintWriter logWriter) {
        this.out = out;
        this.logWriter = logWriter;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);  // scanner za chitanje
        while (true) {
            String message = scanner.nextLine();
            if (message.equals("exit")) {
                break;
            }
            out.println(message);                 // se prakja na serverot
            synchronized (logWriter) {            // se pazi samo eden thread da vpishuva vo logWriter
                logWriter.println("Sent: " + message);
            }
        }
        scanner.close();
    }
}

// thread za chitanje
class ReceiverThread extends Thread {
    private BufferedReader in;
    private PrintWriter logWriter;

    public ReceiverThread(BufferedReader in, PrintWriter logWriter) {
        this.in = in;
        this.logWriter = logWriter;
    }

    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(message);
                synchronized (logWriter) {
                    logWriter.println(message);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Lost connection to server.");
            e.printStackTrace();
        }
    }
}