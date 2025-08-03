package Training;

import java.util.Arrays;

public class Network {
    private final float learningRate = 0.5F;
    private final Layer hiddenLayer;
    private final Layer outputLayer;
    private final DataReader data;

    public Network(){
        data = new DataReader(
                600,
                "src/Dataset/train-images-idx3-ubyte.gz",
                "src/Dataset/train-labels-idx1-ubyte.gz"
        );

        hiddenLayer = new Layer(10, 784, "src/Training/Weights/w1.txt", "src/Training/Bias/b1.txt");
        outputLayer = new Layer(10, 10, "src/Training/Weights/w2.txt", "src/Training/Bias/b2.txt");
    }

    public void networkLearning(int sizeOfTraining){
        //initiate
        float[] output, Z1, Z2;
        float[] dZ2 = new float[10], dZ1 = new float[10];
        float[][] dW2 = new float[10][10], dW1 = new float[10][784];
        float dB2 = 0, dB1 = 0;

        for(int iteration = 0; iteration < sizeOfTraining; iteration++){
            System.out.println(iteration + 1 + " / " + sizeOfTraining);
            //Forward Propagation
            Z1 = hiddenLayer.forwardPropagation(data.getImageArray(iteration));
            //ReLo Function
            Z2 = outputLayer.forwardPropagation(ReLu(Z1));
            //SoftMax Function
            output = SoftMax(Z2);


            //Backward Propagation
            //2nd Layer
            for(int i = 0; i < 10; i++){
                dZ2[i] = ((i == data.getLabelAt(iteration)) ? 1 : 0) - output[i];
            }
            for(int i = 0; i < dZ2.length; i++){
                for(int j = 0; j < dW2[0].length; j++){
                    dW2[i][j] = 0.1F * dZ2[i] * Z1[j];
                }
            }
            dB2 = 0;
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
            dB1 = 0;
            for (float v : dZ1) {
                dB1 += v;
            }
            dB1 = dB1/dZ1.length;

            System.out.println(outputLayer.getBias() + " - " + dB2);

            //Update weights and bias
            outputLayer.backwardPropagation(learningRate, dW2, dB2);
            hiddenLayer.backwardPropagation(learningRate, dW1, dB1);
        }

        //save results
        /*FileManager.saveMatrix("src/Training/Weights/w1.txt", hiddenLayer.getWeights());
        FileManager.saveMatrix("src/Training/Weights/w2.txt", outputLayer.getWeights());
        FileManager.saveBias("src/Training/Bias/b2.txt", hiddenLayer.getBias());
        FileManager.saveBias("src/Training/Bias/b1.txt", outputLayer.getBias());

        System.out.println(correct);*/
    }

    public float[] ReLu(float[] input){
        for(int i = 0; i < input.length; i++) if(input[i] < 0) input[i] = 0;
        return input;
    }

    public float[] SoftMax(float[] a){
        float[] result = new float[a.length];
        float sum = 0;
        for(float f:a){
            sum += (float) Math.exp(f);
        }
        for(int i = 0; i < a.length; i++){
            result[i] = ((float)Math.exp(a[i]))/ sum;
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
