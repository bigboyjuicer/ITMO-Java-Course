import java.util.Arrays;
import java.util.Random;
import static java.lang.Math.*;


public class Lab1 {
    public static void main(String[] args) {
        Random random = new Random();

        short[] a = new short[10];
        double[] x = new double[12];
        double[][] b = new double[10][12];
        String[] conditionList = new String[] {"5", "9", "17", "19", "21"};
        short aNums = 21;

        for(int i = 0; i < a.length; i++) {
            a[i] = aNums;
            aNums -= 2;
        }



        for(int i = 0; i < x.length; i++) {
            x[i] = random.nextDouble(11.0 + 8.0) - 8.0;
        }

        for(int i = 0; i < b.length; i++){
            for(int j = 0; j < b[i].length; j++) {
                if(a[i] == 3) {
                    b[i][j] = asin(pow(E, cbrt(-1 * acos((x[j] + 1.5) / 19))));
                } else if(Arrays.asList(conditionList).contains(String.valueOf(a[i]))) {
                    b[i][j] = pow((1.0F / 2.0F) / pow(cos(x[j]) / PI, 3), pow(2 * cos(x[j]), 3));
                } else {
                    b[i][j] = pow(log((abs(x[j]) + 1) / 3), (1 - pow(E, (1.0F / 2.0F) / (log(abs(x[j])) + 1)))/ (pow(cos(pow(E, x[j])), (cbrt(pow((0.25 - x[j]) / x[j], 2)) * atan(sin(x[j])) - 1))));
                }
            }
        }

        for(int i = 0; i < b.length; i++) {
            for(int j = 0; j < b[i].length; j++) {
                System.out.printf("%.5f\t\t", b[i][j]);
            }
            System.out.println("");
        }


    }
}
