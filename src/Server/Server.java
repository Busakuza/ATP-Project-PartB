package Server;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.search.ISearchingAlgorithm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stop;
    private ExecutorService threadPool;

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.threadPool = Executors.newFixedThreadPool(Configurations.getInstance().getNumOfThreads());
    }

    public void start(){
        new Thread(()->{
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                serverSocket.setSoTimeout(listeningIntervalMS);
                System.out.println("Starting server at port = " + port);

                while (!stop) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        System.out.println("Client accepted: " + clientSocket.toString());

                        // Insert the new task into the thread pool:
                        threadPool.submit(() -> {
                            handleClient(clientSocket);
                        });
                    } catch (SocketTimeoutException e){
                        e.printStackTrace();
                    }
                }
                serverSocket.close();
                threadPool.shutdownNow(); // do not allow any new tasks into the thread pool, and also interrupts all running threads (do not terminate the threads, so if they do not handle interrupts properly, they could never stop...)
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();


    }

    private void handleClient(Socket clientSocket) {
        try {
            strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            System.out.println("Done handling client: " + clientSocket.toString());
            clientSocket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void stop(){
        stop = true;
    }


}
