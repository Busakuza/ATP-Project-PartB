package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream toCompress) {
        super();
        out = toCompress;

    }

    @Override
    public void write(int b) throws IOException {

    }
    // SERVER
    public void write(byte[] byteMaze) throws IOException {
        int count_index = 0,i=0;
        if (byteMaze==null)
        {
            throw new NullPointerException("error");
        }
        if (byteMaze.length==0)
        {
            return;
        }
        for (i=0; i<16;i++)
        {
            out.write(byteMaze[i]);
            out.flush();
        }
        // i = 16 we finished with the row and column.
        // start compressing the maze, we'll write to the stream -1 at the beginning of the maze content
        // and -1 at the end of the content, so we would know when to stop decompressing
        out.write(-1);
        out.flush();
        while(i<byteMaze.length-32)
        {
            while (byteMaze[i]==0 && i<byteMaze.length-32)
            {
                i++;
                count_index++;
            }
            out.write(count_index);
            out.flush();
            count_index=0;
            if (i==byteMaze.length-32 )
                break;
            while (byteMaze[i]==1 && i<byteMaze.length-32)
            {
                i++;
                count_index++;
            }
            out.write(count_index);
            out.flush();
            count_index=0;
//            i++;
        }
        // to signify the end of the maze's content we input -1;
        out.write(-1);
        out.flush();
        for (i=byteMaze.length-32; i<byteMaze.length;i++){
            out.write(byteMaze[i]);
            out.flush();
        }
        out.close();
    }
}