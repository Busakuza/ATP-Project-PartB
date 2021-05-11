package Server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * The same Server class we learned at class.
 * */
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
    /**
     * Each time we start a server a new thread is opened so we could use multiple servers.
     * */
    public void start(){

        new Thread(()->{
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                serverSocket.setSoTimeout(listeningIntervalMS);

                while (!stop) {
                    try {
                        Socket clientSocket = serverSocket.accept();

                        // Insert the new task into the thread pool:
                        threadPool.submit(() -> {
                            handleClient(clientSocket);
                        });
                    } catch (Exception e){
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
            clientSocket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void stop(){
        stop = true;
    }


}
