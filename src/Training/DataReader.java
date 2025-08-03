package Training;

import java.io.*;
import java.util.zip.GZIPInputStream;

public class DataReader {
    private String imagePath;
    private String labelPath;
    private String[] labels;
    private float[][][] images;

    public DataReader(int size, String imagePath, String labelPath){
        try{
            this.imagePath = imagePath;
            this.labelPath = labelPath;

            //Create InputStream for Labels and Image
            InputStream stream = new FileInputStream(new File(imagePath));
            InputStream imageIn = new GZIPInputStream(stream);

            stream = new FileInputStream(new File(labelPath));
            InputStream labelIn = new GZIPInputStream(stream);

            //Skip the first 16 Bytes of Header
            byte[] tempBuffer = new byte[16];
            imageIn.read(tempBuffer, 0, 16);
            labelIn.read(tempBuffer, 0, 16);

            //Initiate arrays
            byte[] dataBuffer = new byte[1];
            labels = new String[size];
            images = new float[size][28][28];

            //Assign InputStream to the Array
            for(int i = 0; i < size; i++){
                System.out.printf("Iter: %d/%d\n", i + 1, size);
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

    public int getLabelAt(int i){
        return Integer.parseInt(labels[i]);
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
