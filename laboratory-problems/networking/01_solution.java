import java.io.*;
import java.net.Socket;

public class ChatClient236024 {
    private static final String SERVER_ADDRESS = "194.149.135.49";
    private static final int SERVER_PORT = 9753;
    private static final String ID = "236024";

    public static void main(String[] args) {
        while (true) {
            try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                 BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in))) {

                // 1. prati login msg
                serverOut.println("login:" + ID);
                String response = serverIn.readLine();
                if (response == null || !response.toLowerCase().contains("success")) {
                    System.out.println("Login failed, retrying...");
                    continue; // probaj pak
                }
                System.out.println("Login successful: " + response);

                // 2. prati hello msg
                serverOut.println("hello:" + ID);
                response = serverIn.readLine();
                System.out.println("Server: " + response);

                // 3. zapochni nishka za dobivanje poraki
                Thread receiveThread = new Thread(() -> {
                    try {
                        String msg;
                        while ((msg = serverIn.readLine()) != null) {
                            System.out.println("Received: " + msg);
                        }
                    } catch (IOException e) {
                        System.out.println("Connection closed.");
                    }
                });
                receiveThread.start();

                // 4. chitanje user input i prakjanje poraki
                String userMsg;
                while ((userMsg = userIn.readLine()) != null) {
                    if (userMsg.trim().isEmpty()) continue;
                    serverOut.println(userMsg);
                }

                break;
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
