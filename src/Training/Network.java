package Training;

import java.util.Arrays;
import java.util.stream.DoubleStream;

public class Network {
    private float[] inputLayer;
    private Layer hiddenLayer;
    private Layer outputLayer;
    private DataReader data;

    public Network(){
        data = new DataReader();
        inputLayer = data.getImageArray(0);
        hiddenLayer = new Layer(10, inputLayer, "src/Training/Weights/w1.txt", "src/Training/Bias/b1.txt");
        hiddenLayer.randomMatrix();
        outputLayer = new Layer(10, hiddenLayer.getzLayer(), "src/Training/Weights/w2.txt", "src/Training/Bias/b2.txt");
        outputLayer.randomMatrix();
    }

    public void forwardPropagation(int i){
        hiddenLayer.forwardPropagation();
        outputLayer.forwardPropagation();

        int result = finalGuess(getPropability(outputLayer.getzLayer()));
        System.out.println(data.getLabelAt(i));
    }

    public float[] getPropability(float[] a){
        float[] result = new float[a.length];
        float sum = 0;
        for(float f:a){
            sum += (float) Math.exp(f);
        }
        for(int i = 0; i < a.length; i++){
            result[i] = ((float)Math.exp(a[i]))/ sum;
            System.out.println(i + ":   " + result[i]);
        }
        return result;
    }

    public int finalGuess(float[] prop){
        float max = 0;
        int index = 0;

        for(int i = 0; i < prop.length; i++) {
            if(prop[i] > max){
                max = prop[i];
                index = i;
            }
        }
        return index;
    }
}
