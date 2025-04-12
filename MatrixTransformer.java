import static java.lang.Math.sqrt;

import java.util.Arrays;
import java.util.Comparator;

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

    public double[][] process() {
        this.triangleMatrix();
        this.inverseMatrix();
        this.productMatrix();
        return this.a;
    }

    public double[][] triangleMatrix() {
        for (int k=0; k<a.length-1; k++) { // итерации метода отражений
            double v[] = new double[a.length-k];
            double y[] = new double[a.length-k]; 
            y[0] = sqrt(dotProduct(a[k], a[k], k, k)); 

            // sortByColumnLength(k);
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
        int n = a.length;    
        for (int j = n-2; j >= 0; j--) {
            double[] v = new double[n-j];
            for (int k = 0; k < v.length; k++) {
                v[k] = a[j][j+k];
                if (j == j+k) a[j][j+k] = diag[n-2-k];
                else a[j][j+k] = 0;
            }
            double sum = 0;
            for (int i = j; i < n; i++) {
                sum += a[i][j] * v[i-j];
            }
            for (int k = 0; k < n; k++) {
                for (int i = j; i < n; i++) {
                    a[i][k] = a[i][k] - 2 * sum / dotProduct(v, v, 0, 0);
                }
            }
        }
        return a;
    }

    private double columnLength(int col) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i][col] * a[i][col];
        }
        return sum;
    }

    public void sortByColumnLength(int k) {
        Integer[] indices = new Integer[a.length-k];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, Comparator.comparingDouble(this::columnLength).reversed());

        for (int i = 0; i < indices.length-k; i++) {
            while (indices[i] != i) {
                int target = indices[i];
                swapColumns(i+k, target+k);
                int temp = indices[i];
                indices[i] = indices[target];
                indices[target] = temp;
            }
        }
    }

    private void swapColumns(int col1, int col2) {
        for (int i = 0; i < a.length; i++) {
            double temp = a[col1][i];
            a[col1][i] = a[col2][i];
            a[col2][i] = temp;
        }
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

    public static double[] multiply(double[] a, double[] b) {
        double[] result = new double[a.length];

        // Умножение матриц
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] * b[k];
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

    


//     import java.util.Arrays;

// public class MatrixTransformation {
//     public static void main(String[] args) {
//         // Пример матрицы A
//         double[][] A = {
//             {4, 2, 1},
//             {2, 3, 5},
//             {1, 5, 6}
//         };

//         // Пример массива diag
//         double[] diag = {10, 20, 30};

//         // Выполнение вычисления A * Hn-1 * Hn-2 * ... * H1
//         computeTransformedMatrix(A, diag);

//         // Выводим результат
//         for (double[] row : A) {
//             System.out.println(Arrays.toString(row));
//         }
//     }

//     // Метод для вычисления A * Hn-1 * Hn-2 * ... * H1
//     public static void computeTransformedMatrix(double[][] A, double[] diag) {
//         int n = A.length;

//         for (int j = n - 2; j >= 0; j--) { // Идем с Hn-1 до H1
//             // Строим Householder матрицу H_j
//             updateHouseholderMatrix(A, j);
            
//             // Умножаем текущую матрицу A на H_j (вместо выделения новой памяти для промежуточной матрицы)
//             multiplyInPlace(A, A, j);

//             // Заменяем столбец в матрице A на элементы из diag[j]
//             replaceColumnWithDiag(A, j, diag[j]);
//         }
//     }

//     // Метод для построения и обновления матрицы Householder для V_j
//     private static void updateHouseholderMatrix(double[][] A, int j) {
//         int n = A.length;
        
//         // Размер V_j
//         int size = n - j;
//         double[] V = new double[size];

//         // Заполняем вектор V из столбца матрицы A
//         for (int i = 0; i < size; i++) {
//             V[i] = A[j + i][j];
//         }

//         // Вычисляем V * V^T и обновляем элементы в матрице A
//         for (int i = 0; i < size; i++) {
//             for (int k = 0; k < size; k++) {
//                 A[j + i][j + k] -= 2 * V[i] * V[k];
//             }
//         }
//     }

//     // Метод для умножения матриц A и H_j с обновлением A
//     private static void multiplyInPlace(double[][] A, double[][] B, int j) {
//         int n = A.length;
//         double temp;

//         // Умножаем A на H_j и сохраняем результат в A
//         for (int i = 0; i < n; i++) {
//             for (int k = j; k < n; k++) {
//                 temp = 0;
//                 for (int m = 0; m < n; m++) {
//                     temp += A[i][m] * B[m][k];
//                 }
//                 A[i][k] = temp;
//             }
//         }
//     }

//     // Метод для замены столбца в матрице A на элементы из diag
//     private static void replaceColumnWithDiag(double[][] A, int j, double diagValue) {
//         int n = A.length;
//         A[0][j] = diagValue;
//         for (int i = 1; i < n; i++) {
//             A[i][j] = 0;
//         }
//     }
// }

}
