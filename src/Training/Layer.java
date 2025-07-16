package Training;

public class Layer {
    private float[][] weights;
    private float[] bias;
    private float[] input;
    private float[] zLayer;
    private String path;
    private int size;

    public Layer(int size, float[] input, String path){
        this.size = size;
        zLayer = new float[size];
        this.input = input;
        this.bias = new float[size];
        this.weights = randomMatrix(size, input.length);
    }

    public float[] dotProduct(){
        float[] output = new float[size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < input.length; j++) output[i] += input[j] * weights[i][j];
        }

        return output;
    }

    private float[][] randomMatrix(int n, int m){
        float[][] random = new float[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++) random[i][j] = (float) (Math.random() - 0.5);
        }
        return random;
    }

    public String getPath(){
        return path;
    }

    public float[] getBias() {
        return bias;
    }

    public float[][] getWeights() {
        return weights;
    }
}
