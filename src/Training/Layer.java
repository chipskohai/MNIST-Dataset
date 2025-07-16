package Training;

import java.util.Random;

public class Layer {
    private float[][] weights;
    private float[] bias;
    private float[] input;
    private float[] zLayer;
    private String pathWeights;
    private String pathBias;
    private int size;

    public Layer(int size, float[] input, String pathWeights, String pathBias){
        this.pathWeights = pathWeights;
        this.pathBias = pathBias;
        this.size = size;
        zLayer = new float[size];
        this.input = input;
        this.bias = new float[size];
        this.weights = new float[size][input.length];
        //this.weights = randomMatrix(size, input.length);
    }

    public void forwardPropagation(){
        float[] dot = dotProduct();
        for(int i = 0; i < zLayer.length; i++){
            zLayer[i] = dot[i] + bias[i];
            //Activation Function: ReLo
            zLayer[i] = (zLayer[i] < 0) ? 0 : zLayer[i];
        }
    }

    private float[] dotProduct(){
        float[] output = new float[size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < input.length; j++) output[i] += input[j] * weights[i][j];
        }

        return output;
    }

    public void randomMatrix(){
        Random r = new Random(101);
        for(int i = 0; i < weights.length; i++){
            for(int j = 0; j < weights[0].length; j++) weights[i][j] = r.nextFloat() - 0.5f;
        }
    }

    public String getPathWeights(){
        return pathWeights;
    }

    public String getPathBias() {
        return pathBias;
    }

    public float[] getBias() {
        return bias;
    }

    public float[][] getWeights() {
        return weights;
    }

    public float[] getzLayer() {
        return zLayer;
    }
}
