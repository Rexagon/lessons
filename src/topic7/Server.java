package topic7;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Server {
    private static List<Character> symbols = new LinkedList<>();
    private static FileOutputStream outputFile;

    public static void main(String[] args) {
        try {
            outputFile = new FileOutputStream("server.log");

            ServerSocket connectionListener = new ServerSocket(7743);

            while (true) {
                Socket clientSocket = connectionListener.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void log(String text) {
        synchronized (outputFile) {
            System.out.println(text);
            try {
                outputFile.write(text.getBytes());
                outputFile.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket socket;
        private ObjectInputStream inputStream;
        private PrintWriter outputStream;
        private String name;

        public ClientHandler(Socket socket) {
            try {
                this.socket = socket;
                outputStream = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                outputStream.flush();
                inputStream = new ObjectInputStream(socket.getInputStream());

                name = socket.getInetAddress().toString();

                log("Client " + name + " connected\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Packet packet = (Packet) inputStream.readObject();
                    System.out.println(packet.getType());
                    synchronized (symbols) {
                        switch (packet.getType()) {
                            case SEND:
                                processSendPacket(packet);
                                break;

                            case GET:
                                processGetPacket(packet);
                                break;

                            case COUNT:
                                processCountPacket(packet);
                                break;

                            case CLEAR:
                                processClearPacket(packet);
                                break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }

        private void processSendPacket(Packet packet) {
            symbols.add(packet.getSymbol());
            log(name + " SEND('" + packet.getSymbol() + "')\n");
        }

        private void processGetPacket(Packet packet) {
            String result = "[";

            StringBuilder builder = new StringBuilder();
            for(Character symbol : symbols) {
                builder.append(symbol).append(',');
            }

            if(builder.length() > 0) {
                builder.deleteCharAt(builder.length() - 1);
            }

            result += builder.toString() + "]";
            outputStream.println(result);
            outputStream.flush();
            log(name + " GET " + result + "\n");
        }

        private void processCountPacket(Packet packet) {
            char symbol = packet.getSymbol();
            int result = 0;
            for (char s : symbols) {
                if (s == symbol) {
                    ++result;
                }
            }
            outputStream.println(result);
            outputStream.flush();
            log(name + " COUNT('" + packet.getSymbol() + "') " + result + "\n");
        }

        private void processClearPacket(Packet packet) {
            symbols.clear();
            log(name + " CLEAR\n");
        }
    }
}
