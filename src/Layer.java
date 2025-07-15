public class Layer {
    float[][] weights;
    float[] bias;
    float[] input;

    public Layer(int n, float[] input){
        this.input = input;
        this.weights = new float[n][input.length];
        this.bias = new float[n];
    }

}
