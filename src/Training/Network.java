package Training;

public class Network {
    private float[] inputLayer;
    private final float learningRate = 0.5F;
    private Layer hiddenLayer;
    private Layer outputLayer;
    private DataReader data;

    public Network(){
        data = new DataReader(
                60000,
                "src/Dataset/train-images-idx3-ubyte.gz",
                "src/Dataset/train-labels-idx1-ubyte.gz"
        );
        inputLayer = data.getImageArray(0);
        hiddenLayer = new Layer(10, inputLayer.length, "src/Training/Weights/w1.txt", "src/Training/Bias/b1.txt");
        hiddenLayer.randomMatrix();
        outputLayer = new Layer(10, 10, "src/Training/Weights/w2.txt", "src/Training/Bias/b2.txt");
        outputLayer.randomMatrix();
    }

    public void networkLearning(int sizeOfTraining){
        int label;
        float[] output, Z1, Z2;
        float[] dZ2 = new float[10], dZ1 = new float[10];
        float[][] dW2 = new float[10][10], dW1 = new float[10][784];
        float dB2 = 0, dB1 = 0;
        
        for(int iteration = 0; iteration < sizeOfTraining; iteration++){
            System.out.println("Iteration: " + iteration + " / " + sizeOfTraining);

            //Forward Propagation
            Z1 = hiddenLayer.forwardPropagation(data.getImageArray(iteration));
            //ReLo Function
            Z2 = outputLayer.forwardPropagation(ReLu(Z1));
            //SoftMax Function
            output =  getProbability(Z2);


            //Backward Propagation
            label = data.getLabelAt(iteration);
            //2nd Layer
            for(int i = 0; i < 10; i++){
                dZ2[i] = ((i == label) ? 1 : 0) - output[i];
            }
            for(int i = 0; i < dZ2.length; i++){
                for(int j = 0; j < dW2[0].length; j++){
                    dW2[i][j] = 0.1F * dZ2[i] * Z1[j];
                }
            }
            for (float v : dZ2) {
                dB2 += v;
            }
            dB2 = dB2 / dZ2.length;

            //1st Layer
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    dZ1[i] += hiddenLayer.getWeights()[i][j] * dZ2[j] * ((Z1[i] > 0) ? 1 : 0);
                }
            }
            for(int i = 0; i < dZ1.length; i++){
                for(int j = 0; j < 784; j++){
                    dW1[i][j] = 0.1F * dZ2[i] * data.getImageArray(iteration)[j];
                }
            }
            for (float v : dZ1) {
                dB1 += v;
            }
            dB1 = dB1/dZ1.length;
        }
    }

    public float[] ReLu(float[] input){
        for(int i = 0; i < input.length; i++) if(input[i] < 0) input[i] = 0;
        return input;
    }


    public float[] getProbability(float[] a){
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

    private int finalGuess(float[] prop){
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
