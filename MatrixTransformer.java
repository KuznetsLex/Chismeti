import static java.lang.Math.min;
import static java.lang.Math.sqrt;
import java.util.Objects;

public class MatrixTransformer {
    private double[][] a;
    private double[] diag;

    public MatrixTransformer(double[][] a) {
        this.a = swapInternal(a);
        diag = new double[a.length];
    }

    public double[][] getA() {
        return this.a;
    } 

    public double[] getDiag() {
        return this.diag;
    } 

    public double[][] triangleMatrix() {
        for (int k=0; k<a.length-1; k++) { // итерации метода отражений
            double v[] = new double[a.length-k];
            double y[] = new double[a.length-k]; 
            y[0] = sqrt(dotProduct(a[k], a[k], k, k)); 

            for (int i=a.length-1; i>=k; i--) {
                v[i-k] = a[k][i] - y[i-k];
                if (a[k][k] != 0) {
                    v[0] = v[0] * Math.signum(a[k][k]);
                }
            }
            for (int j=k; j<a.length; j++) { // какие столбцы меняем
                double[] curColumn = a[j].clone();
                for (int i=a.length-1; i>=k; i--) { 
                    if (j == k && i >= j) {
                        if (i == j) {
                            diag[k] = a[j][i] - (2 * dotProduct(v, curColumn, 0, k) / dotProduct(v, v, 0, 0)) * v[i-k];
                        } 
                        a[j][i] = v[i - k];
                    }
                    else {
                        a[j][i] -= (2 * dotProduct(v, curColumn, 0, k) / dotProduct(v, v, 0, 0)) * v[i-k]; // a[i][j] i и j поменять 
                    }
                    
                }
            }
        }
        diag[a.length-1] = a[a.length-1][a.length-1];
        return a;
    }

    public double[][] inverseMatrix() {
        int n = diag.length;        
        for (int i=0; i < diag.length; i++) {
            diag[i] = 1.0/diag[i];
        }

        // Обращаем матрицу согласно алгоритму
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n-1; j >= i+1; j--) {
                double sum = 0.0;
                for (int k = i + 1; k <= j; k++) {
                    if (j == k) {
                        sum += a[k][i] * diag[j];
                    } else {
                        sum += a[k][i] * a[j][k];
                    }
                }
                a[j][i] = (-1) * sum * diag[i];
            }
        }
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
