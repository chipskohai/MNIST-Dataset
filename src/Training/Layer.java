package Training;

import java.util.Random;

public class Layer {
    private float[][] weights;
    private float bias;
    private String pathWeights;
    private String pathBias;
    private int layerSize;
    private int inputSize;

    public Layer(int layerSize, int inputSize, String pathWeights, String pathBias){
        this.pathWeights = pathWeights;
        this.pathBias = pathBias;
        this.layerSize = layerSize;
        this.inputSize = inputSize;
        this.bias = 0;
        this.weights = new float[layerSize][inputSize];
        randomMatrix();
    }

    public float[] forwardPropagation(float[] input){
        float[] zLayer = new float[layerSize];
        //Input * Weight Layer
        float[] dot = dotProduct(input);
        for(int i = 0; i < zLayer.length; i++){
            zLayer[i] = dot[i] + bias;
        }
        return zLayer;
    }

    public void backwardPropagation(float learningRate, float[][] dW, float dB){
        bias = bias - (learningRate*dB);

        for(int i = 0; i < weights.length; i++){
            for(int j = 0; j < weights[0].length; j++){
                weights[i][j] = weights[i][j] - (learningRate * dW[i][j]);
            }
        }
    }

    private float[] dotProduct(float[] input){
        float[] output = new float[layerSize];
        for(int i = 0; i < layerSize; i++){
            for(int j = 0; j < inputSize; j++) output[i] += input[j] * weights[i][j];
        }
        return output;
    }

    private void randomMatrix(){
        Random r = new Random(101);
        for(int i = 0; i < weights.length; i++){
            for(int j = 0; j < weights[0].length; j++) {
                weights[i][j] = r.nextFloat() - 0.5f;
            }
        }
    }

    public String getPathWeights(){
        return pathWeights;
    }

    public String getPathBias() {
        return pathBias;
    }

    public float getBias() {
        return bias;
    }

    public float[][] getWeights() {
        return weights;
    }
}
