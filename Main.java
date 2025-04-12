public class Main {
    public static void main(String[] args) {
        // GenTest test = new GenTest();
        // test.GenTest();

        double[][] a = new double[][]{{2,2,1},{1,6,5},{2,1,1}};
        MatrixTransformer transformer = new MatrixTransformer(a);
        double[][] triangleMatrix = transformer.triangleMatrix();
        
        System.out.println();
        System.out.println("Origin matrix");
		for (int i=0; i<a.length; i++) {
			for (int j=0; j<a.length; j++) {
				System.out.print(a[i][j]+ " ");
			}
			System.out.println();
		}
        System.out.println();

        System.out.println("Triangle matrix1");
		for (int i=0; i<triangleMatrix.length; i++) {
			for (int j=0; j<triangleMatrix.length; j++) {
				System.out.print(triangleMatrix[j][i]+ " ");
			}
			System.out.println();
		}
		System.out.println();

		for (int j=0; j<triangleMatrix.length; j++) {
			System.out.print(transformer.getDiag()[j]);
			System.out.print(" ");
		}
		System.out.println();

		System.out.println();
		double[][] reverseMatrix = transformer.inverseMatrix();
		System.out.println("Reverse matrix1");
		for (int i=0; i<reverseMatrix.length; i++) {
			for (int j=0; j<reverseMatrix.length; j++) {
				System.out.print(reverseMatrix[j][i]+ " ");
			}
			System.out.println();
		}
		System.out.println();

		for (int j=0; j<triangleMatrix.length; j++) {
			System.out.print(transformer.getDiag()[j]);
			System.out.print(" ");
		}
		System.out.println();
		

		System.out.println();
		double[][] productMatrix = transformer.productMatrix();
		System.out.println("Product matrix1");
		for (int i=0; i<productMatrix.length; i++) {
			for (int j=0; j<productMatrix.length; j++) {
				System.out.print(productMatrix[j][i]+ " ");
			}
			System.out.println();
		}
		System.out.println();

		for (int j=0; j<triangleMatrix.length; j++) {
			System.out.print(transformer.getDiag()[j]);
			System.out.print(" ");
		}
		System.out.println();

        // GenTest test = new GenTest();
        // test.GenTest();
        // MatrixTransformer transformer2 = new MatrixTransformer(test.a_generated);
        // double[][] triangleMatrix2 = transformer2.triangleMatrix();
        // System.out.println();
        // System.out.println("Triangle matrix2");
		// for (int i=0; i<triangleMatrix2.length; i++) {
		// 	for (int j=0; j<triangleMatrix2.length; j++) {
		// 		System.out.print(triangleMatrix2[j][i]+ " ");
		// 	}
		// 	System.out.println();
		// }
    }
}
