package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {
    private InputStream input;

    public SimpleDecompressorInputStream(InputStream input) {
        this.input = input;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
    public int read(byte[] byteMaze) throws IOException {
        int i=0; int count_index=0;
        /*a shfiut test*/
        if (byteMaze==null)
        {
            throw new NullPointerException("error");
        }
        int col=0; int row=0;
        // reading the maze and columns from the stream.
        for (i=0;i<16; i++ )
        {
            byteMaze[i]= (byte) input.read();
            if (i<8)
                row+=byteMaze[i];
            else
                col+=byteMaze[i];
        }
        int test = (byte)input.read(); // should be -1;
        int maze_size=row*col;
        int decimal=0;
        while(decimal!=-1)
        {
            decimal=(byte)input.read();
            for (int j=0; j<decimal; j++) {
                byteMaze[i]=0;
                i++;
            }
            if (decimal == -1)
                break;
            decimal=(byte)input.read();
            for (int j=0; j<decimal; j++) {
                byteMaze[i]=1;
                i++;
            }
        }
        for (i = maze_size+16; i<byteMaze.length; i++)
            byteMaze[i] = (byte) input.read();
        input.close();
        return 1;
    }
}