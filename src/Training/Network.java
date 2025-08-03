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
        float[] output, layer1, layer2;
        float[] dZ2 = new float[10], dZ1 = new float[10], dW2 = new float[10], dW1 = new float[10];
        
        for(int iteration = 0; iteration < sizeOfTraining; iteration++){
            System.out.println("Iteration: " + iteration + " / " + sizeOfTraining);

            //forward propagation
            layer1 = hiddenLayer.forwardPropagation(data.getImageArray(iteration));
            //ReLo Function
            for(int i = 0; i < layer1.length; i++){
                if(layer1[i] < 0) layer1[i] = 0;
            }

            layer2 = outputLayer.forwardPropagation(layer1);
            //SoftMax Function
            output =  getProbability(layer2);

            //Backward Propagation
            label = data.getLabelAt(iteration);
            for(int i = 0; i < 10; i++){
                dZ2[i] = ((i == label) ? 1 : 0) - output[i];
            }
        }
    }

    public void backwardPropagation(int iteration, float[] output){
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
