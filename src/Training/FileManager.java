package Training;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    public static void saveMatrix(String path, float[][] matrix){
        try{
            FileWriter writer = new FileWriter(path);
            for(int i = 0; i < matrix.length; i++){
                writer.write(writeLine(matrix[i]));
            }
            writer.close();
        }catch(IOException e){
            System.out.println("failed");
            e.printStackTrace();
        }
    }

    public static float[][] readMatrix(String path){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            int rows = getRows(reader);
            reader = new BufferedReader(new FileReader(path));
            int column = getColumn(reader);
            reader = new BufferedReader(new FileReader(path));
            float[][] matrix = new float[column][rows];

            for(int i = 0; i < rows; i++){
                matrix[i] = StringToFloatArray(reader.readLine(), column);
            }
            reader.close();
            return matrix;
        }catch(IOException e){
            e.printStackTrace();
            return new float[1][1];
        }
    }

    public static void saveVector(String path, float[] vector){
        try{
            FileWriter writer = new FileWriter(path);
            writer.write(writeLine(vector));
        }catch(IOException e){
            System.out.println("failed");
            e.printStackTrace();
        }
    }

    public static float[] readVector(String path){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            int column = getColumn(reader);
            reader = new BufferedReader(new FileReader(path));
            float[] vector = StringToFloatArray(reader.readLine(), column);

            reader.close();
            return vector;
        }catch(IOException e){
            e.printStackTrace();
            return new float[1];
        }
    }

    private static float[] StringToFloatArray(String line, int size){
        float[] array = new float[size];
        int i = 0;
        StringBuilder sb = new StringBuilder();

        for(char c : line.toCharArray()){
            if(c == ',') {
                array[i] = Float.parseFloat(sb.toString());
                i++;
                sb.setLength(0);
            }else{
                sb.append(c);
            }
        }


        return array;
    }

    private static int getRows(@NotNull BufferedReader reader) throws IOException {
        int counter = 0;
        while(reader.readLine() != null) counter++;
        return counter;
    }

    private static int getColumn(@NotNull BufferedReader reader) throws IOException{
        int counter = 0;
        String line = reader.readLine();
        for(char c : line.toCharArray()){
            if(c == ',')counter++;
        }


        return counter;
    }

    private static String writeLine(float[] a){
        StringBuilder s = new StringBuilder();

        for(int i = 0; i < a.length - 1; i++){
            s.append(a[i]);
            s.append(",");
        }
        s.append(a[a.length - 1]);
        s.append(",\n");

        return s.toString();
    }
}
