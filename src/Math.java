public class Math {
    public static float dotProduct(float[] a, float[] b){
        float result = 0;
        for(int i = 0; i < a.length; i++) result += a[i] * b[i];
        return result;
    }

    public static float ReLu(float x){
        return (x < 0) ? 0 : x;
    }
}
