import static java.lang.Math.min;
import static java.lang.Math.sqrt;
import java.util.Objects;

public class MatrixTransformer {
    private double[][] a;
    private double[] diag;

    public MatrixTransformer(double[][] a) {
        this.a = swapInternal(a);
    }

    public double[][] getA() {
        return this.a;
    } 

    public double[][] triangleMatrix() {
        for (int k=0; k<a.length-1; k++) { // итерации метода отражений
            double v[] = new double[a.length-k];
            double y[] = new double[a.length-k]; 
            y[0] = sqrt(dotProduct(a[k], a[k], k, k)); // a[k] динамически менять надо!

            for (int i=a.length-1; i>=k; i--) {
                v[i-k] = a[k][i] - y[i-k]; // TODO sign
            }
            for (int j=k; j<a.length; j++) { // какие столбцы меняем
                double[] curColumn = a[j].clone();
                for (int i=a.length-1; i>=k; i--) { 
                    a[j][i] -= (2 * dotProduct(v, curColumn, 0, k) / dotProduct(v, v, 0, 0)) * v[i-k]; // a[i][j] i и j поменять 
                }
            }


            // for (int i=0; i<a.length-k; i++) { 
            //     x1[i] = a[k][i] - x2[i];
            // }
            // for (int j=0; j<a.length-k; j++) {
            //     x2 = a[j].clone(); // x1 = v, x2 = curColumn
            //     for (int i=0; i<a.length-k; i++) { 
            //         a[j][i] -= (2 * dotProduct(x1, x2) / dotProduct(x1,x1)) * x1[i]; // a[i][j] i и j поменять 
            //     }
            // }
        }
        return a;
    }

    public double[][] inverseMatrix() {
        return a;
    }

    public double[][] productMatrix() {
        return a;
    }

    private static double dotProduct(double[] a, double[] b, int k1, int k2) { // игнорировать k1 элементов сверху 1 массива и k2 элементов сверху 2 массива
        if (a.length-k1 != b.length-k2) {
            throw new IllegalArgumentException("Arrays must have the same length");
        }
        
        double result = 0.0;
        for (int i = 0; (i+k1 < a.length) && (i+k2 < b.length); i++) {
            result += a[i+k1] * b[i+k2];
        }
        
        return result;
    }

    private double[][] swapInternal(double[][] a) {
        int rows = a.length;
        int cols = a.length;
        
        double[][] swapedA = new double[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                swapedA[j][i] = a[i][j];
            }
        }
        return swapedA;
    }

}
