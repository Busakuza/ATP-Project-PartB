package Server;

import java.io.InputStream;
import java.io.OutputStream;
/**
 * Stratedy Design Pattern Interface.
 * each class which implements it must implement the applyStrategy method.
 * */
public interface IServerStrategy {
    void applyStrategy(InputStream inFromClient, OutputStream outToClient);
}
