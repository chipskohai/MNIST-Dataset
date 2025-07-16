import Training.*;

import javax.imageio.IIOException;
import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String testPath = "C:\\Users\\Admin\\IdeaProjects\\MNIST-Dataset\\src\\test.txt";
        float[] i = new float[]{1, 2, 3};
        Layer l = new Layer(3, i, "test");
        System.out.println(Arrays.toString(l.dotProduct()));

    }
}