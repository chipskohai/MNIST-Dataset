package Training;

import java.io.*;
import java.util.zip.GZIPInputStream;

public class DataReader {
    private String[] labels;
    private float[][][] images;

    public DataReader(){
        try{
            //Create InputStream for Labels and Image
            InputStream stream = new FileInputStream(new File("C:\\Users\\Admin\\IdeaProjects\\MNIST-Dataset\\src\\Dataset\\train-images-idx3-ubyte.gz"));
            InputStream imageIn = new GZIPInputStream(stream);

            stream = new FileInputStream(new File("C:\\Users\\Admin\\IdeaProjects\\MNIST-Dataset\\src\\Dataset\\train-labels-idx1-ubyte.gz"));
            InputStream labelIn = new GZIPInputStream(stream);

            //Skip the first 16 Bytes of Header
            byte[] tempBuffer = new byte[16];
            imageIn.read(tempBuffer, 0, 16);
            labelIn.read(tempBuffer, 0, 16);

            //Initiate arrays
            byte[] dataBuffer = new byte[1];
            labels = new String[60000];
            images = new float[60000][28][28];

            //Assign InputStream to the Array
            //nerfed this !!!!

            for(int i = 0; i < 60; i++){
                System.out.printf("Iter: %d/60000\n", i + 1);
                labelIn.read(dataBuffer, 0, 1);
                labels[i] = Integer.toString(dataBuffer[0] & 0xFF);
                // Get the value of every pixel into the 2D array
                for(int j = 0; j < 784; j++){
                    imageIn.read(dataBuffer, 0, 1);
                    float pixel = (dataBuffer[0] & 0xFF) / 255.f;
                    images[i][j / 28][j % 28] = pixel;
                }
            }
        }catch(IOException e){
            System.out.println("FAIL");
        }
    }

    public String getLabelAt(int i){
        return labels[i];
    }

    public float[][] getImageAt(int i){
        return images[i];
    }

    public float[] getImageArray(int n){
        float[] a = new float[784];
        for(int i = 0; i < 784; i++){
            a[i] = images[n][i / 28][i % 28];
        }
        return a;
    }

    public String[] getLabels() {
        return labels;
    }

    public float[][][] getImages() {
        return images;
    }
}
