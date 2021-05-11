package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MyCompressorOutputStream extends OutputStream {

    private OutputStream out;

    public MyCompressorOutputStream() {
        OutputStream out;
    }

    public MyCompressorOutputStream(OutputStream toCompress) {
        super();
        out = toCompress;

    }
    public int to_decimal(String s)
    {
        String st=s;
        int sum=0;
        int two=1;
        int digit;
        for (int i=st.length()-1; i>-1;i--)
        {
            digit=(int)st.charAt(i)-48;
            sum=sum+two*digit;
            two=two*2;
        }
        return sum;
    }
    @Override
    public void write(int b) throws IOException {

    }
    /**
     * function write: get inputs: byte arrray- byteMaze that An array that represents
     * a non-shrinking maze and shrinks it into an array of bits that holds a mapped grouped
     * by a method of binary representation, each sequence of 8 bytes in the maze represented
     * a binary number and we will keep it as a number.
     * */
    public void write(byte[] byteMaze) throws IOException {
        int count_index = 0, i = 0;
        int rest = (byteMaze.length - 16 - 32) % 8;
        int division = (byteMaze.length - 16 - 32) / 8;
        /*a shfiut test*/
        if (byteMaze == null) {
            throw new NullPointerException("error");
        }
        if (byteMaze.length == 0) {
            return;
        }
        for (i = 0; i < 16; i++) {
            out.write(Byte.toUnsignedInt((byte)byteMaze[i]));
            out.flush();

        }
        // i = 16 we finished with the row and column.
        // start compressing the maze, we'll write to the stream -1 at the beginning of the maze content
        // and -1 at the end of the content, so we would know when to stop decompressing

        String st = "";    //he saved in st(string) 8 cells of maze in string that creat a binnary number/

        for (int d = 0; d < division; d++) {
            st = "";
            for (int j = 0; j < 8; j++) {

                st = st + byteMaze[i];
                i++;
            }

            int decimal = to_decimal(st);
            out.write(Byte.toUnsignedInt((byte) decimal));
            out.flush();
        }
        for (int j = 0; j < rest; j++) {
            out.write(Byte.toUnsignedInt((byte)byteMaze[i]));
            out.flush();
            st = st + byteMaze[i];
            i++;
        }
        while (i < byteMaze.length) {
            out.write(Byte.toUnsignedInt((byte)byteMaze[i]));
            out.flush();
            i++;
        }

    }
}