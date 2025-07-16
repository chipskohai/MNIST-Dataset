import Training.*;

import javax.imageio.IIOException;
import java.io.*;
import java.net.spi.InetAddressResolver;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Network nn = new Network();
        nn.forwardPropagation(0);
    }
}