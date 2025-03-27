public class GenTest 
{

	public double[][] a_generated;
    public double[][] a_inv_generated;
	private int N = 3;
	private double ALPHA = 1.;
	private double BETA  = 1.e+3;

	public void GenTest()
	{
		int n = N;
		double alpha = ALPHA;
		double beta  = BETA;

		double[][] a = new double[n][];
		for (int i = 0; i < n; i++)	a[i] = new double[n];

		double[][] a_inv = new double[n][];
		for (int i = 0; i < n; i++)	a_inv[i] = new double[n];

		Gen g = new Gen();

		// g.mygen ( a, a_inv, n, alpha, beta, 1, 2, 0, 1 ); // симметричная
		g.mygen ( a, a_inv, n, alpha, beta, 1, 2, 1, 1 ); //проостой структуры
	//	g.mygen ( a, a_inv, n, alpha, beta, 0, 0, 2, 1 ); //жорданова клетка

		a_generated = g.a_generated;
		a_inv_generated = g.a_inv_generated;

		g.print_matr(a,n);
		g.print_matr(a_inv,n);
		// System.out.println();

		// for (int i=0; i<g.a_generated.length; i++) {
		// 	for (int j=0; j<g.a_generated.length; j++) {
		// 		System.out.print(g.a_generated[i][j]+ " ");
		// 	}
		// 	System.out.println();
		// }
		// System.out.println();
		// for (int i=0; i<g.a_inv_generated.length; i++) {
		// 	for (int j=0; j<g.a_inv_generated.length; j++) {
		// 		System.out.print(g.a_inv_generated[i][j]+ " ");
		// 	}
		// 	System.out.println();
		// }
		
		
	}

}
