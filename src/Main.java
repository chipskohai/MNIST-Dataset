import Training.*;

import javax.imageio.IIOException;
import java.io.*;
import java.net.spi.InetAddressResolver;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Network network = new Network();

    }

    public static DataReader getTestData(){
        return new DataReader(
                10000,
                "src/Dataset/t10k-images-idx3-ubyte.gz",
                "src/Dataset/t10k-labels-idx1-ubyte.gz"
        );
    }
}