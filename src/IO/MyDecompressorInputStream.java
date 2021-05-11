package IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyDecompressorInputStream extends InputStream {
    private InputStream input;

    public MyDecompressorInputStream(InputStream input) {
        this.input = input;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
    /**function read: get input: Array of byte- byteMaze represents a compressing maze.
    The function will expand the maze by performing an operation opposite to the contraction
     operation, it will take any value in the array from the representation of the maze and convert
      it to a binary number represented by 8 bytes.*/
    public int read(byte[] byteMaze) throws IOException {
        int i=0; int count_index=0;
        /*a shfiut test*/
        if (byteMaze==null)
        {
            throw new NullPointerException("error");
        }
        if (byteMaze.length==0)
        {
            return 0;
        }
        int col=0; int row=0;
        for (int j=0;j<16; j++ ) {
            byteMaze[i] = (byte) input.read();
            if (i<8)
                row=row+(int)byteMaze[i];
            else
                col=col+(int)byteMaze[i];
            i++;
        }
        int maze_size=row*col;
        int decimal=0;
        String st_binar;
        int rest=(row*col)%8;
        int division=(row*col)/8;
        while(i<maze_size+16-rest) {
            decimal=input.read();
            st_binar=Integer.toBinaryString(decimal);
            for (int j=0; j<8-st_binar.length(); j++) {
                byteMaze[i]=(byte)0;
                i++;
            }
            for (int j=0; j<st_binar.length(); j++) {
                byteMaze[i]=(byte)(st_binar.charAt(j)-'0');
                i++;
            }

        }
        for (int j=i; j<byteMaze.length; j++)
        {
            byteMaze[j] = (byte)input.read();
        }
        return 1;
    }

}
