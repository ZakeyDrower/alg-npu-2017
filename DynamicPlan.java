/**
 * main class contains varies of cases using dynamicplan
 * @author Drower
 * @update 2016-04-12 08:11-09:30
 *		   minWeightTriangulation
 */ 
public abstract class DynamicPlan{
		
/**
 * Question 1: MatrixChain
 */
	/*
	 * solve MatrixChain with RECURSION
	 * @param i index of start point of matrix chain
	 * @param j index of end point of matrix chain
	 * @param COLUME_MARTIX an arraylist storing count num of columes of the i'th matrix
	 * 		  COLUME_MARTIX[0] = count num lines of first matrix
	 * @param RECORD a (N+1)-order matrix recording devide point between index i & j in matrixChain on which we can get best answer
	 */
	public static int recursionMatrixChain(int i, int j, int[] COLUME_MARTIX, int[][] RECORD){
		if(i == j) return 0; 
		int minValue = recursionMatrixChain(i, i, COLUME_MARTIX, RECORD) + recursionMatrixChain(i+1, j, COLUME_MARTIX, RECORD) + COLUME_MARTIX[i-1]*COLUME_MARTIX[i]*COLUME_MARTIX[j];
		RECORD[i][j] = i;
		for(int k = i+1; k < j; k++){
			int tempValue = recursionMatrixChain(i, k, COLUME_MARTIX, RECORD) + recursionMatrixChain(k+1, j, COLUME_MARTIX, RECORD) + COLUME_MARTIX[i-1]*COLUME_MARTIX[k]*COLUME_MARTIX[j];
			if(tempValue < minValue){
				minValue = tempValue;
				RECORD[i][j] = k;
			}
		}
		return minValue;
	}

	/*
	 * solve MatrixChain problem with DYNAMICPLAN using ITERATION
	 * @param COLUME_MARTIX an arraylist storing count num of columes of the i'th matrix
	 * 		  COLUME_MARTIX[0] = count num lines of first matrix
	 * @param N number of matrix in matrixChain
	 * @param RES a (N+1)-order matrix recording best answer in the part of matrixChain (between index i & j)
	 * @param RECORD a (N+1)-order matrix recording devide point between index i & j in matrixChain on which we can get best answer
	 */
	public static void iterationMatrixChain(int[] COLUME_MARTIX, int N, int[][] RES, int[][] RECORD){
		for(int initial = 1; initial <= N; initial++) RES[initial][initial] = 0;
		
		for(int r = 1; r <= N; r++){
			for(int i = 1; i <= N-r; i++){
				int j = i+r;
				int min = RES[i][i] + RES[i+1][j] + COLUME_MARTIX[i-1]*COLUME_MARTIX[i]*COLUME_MARTIX[j];
				RECORD[i][j] = i;
				for(int k = i+1; k <= j-1; k++){
					int temp = RES[i][k] + RES[k+1][j] + COLUME_MARTIX[i-1]*COLUME_MARTIX[k]*COLUME_MARTIX[j];
					if(temp < min){
						min = temp;
						RECORD[i][j] = k;
					}
				}
				RES[i][j] = min;
			}
		}
	}

	/*
	 * solve MatrixChain problem with DYNAMICPLAN using a MEMO_MATRIX
	 * @param N number of matrix in matrixChain
	 * @param COLUME_MARTIX an arraylist storing count num of columes of the i'th matrix
	 * 		  COLUME_MARTIX[0] = count num lines of first matrix
	 * @param MEMO a (N+1)-order matrix record best solution calculated before
	 * @param RECORD a (N+1)-order matrix recording devide point between index i & j in matrixChain on which we can get best answer
	 */
	public static int memoMatrixChain(int N, int[] COLUME_MARTIX, int[][] MEMO, int[][] RECORD){
		initialMemo(N, MEMO);
		return lookupChain(1, N, COLUME_MARTIX, MEMO, RECORD);
	}

		/*
		 * initialize MEMO matrix
		 * @param N number of matrix in matrixChain
		 * @param MEMO a (N+1)-order matrix record best solution calculated before
		 */
		public static void initialMemo(int N, int[][] MEMO){
			for(int i = 1; i <= N; i++)
				for(int j = i; j <= N; j++)
					MEMO[i][j] = 0;
		}

		/*
		 * a method use both RECURSION and MEMO to solve MatrixChain problem
		 * @param i index of the first matrix in this part of matrix chain
		 * @param j index of the last matrix in this part of matrix chain
		 * @param COLUME_MARTIX an arraylist storing count num of columes of the i'th matrix
		 * 		  COLUME_MARTIX[0] = count num lines of first matrix
		 * @param MEMO a (N+1)-order matrix record best solution calculated before
		 * @param RECORD a (N+1)-order matrix recording devide point between index i & j in matrixChain on which we can get best answer
		 */
		public static int lookupChain(int i, int j, int[] COLUME_MARTIX, int[][] MEMO, int[][] RECORD){
			if(i == j) return 0;
			if(MEMO[i][j] > 0) return MEMO[i][j];
			
			int min_u = lookupChain(i, i, COLUME_MARTIX, MEMO, RECORD) + lookupChain(i+1, j, COLUME_MARTIX, MEMO, RECORD) + COLUME_MARTIX[i-1]*COLUME_MARTIX[i]*COLUME_MARTIX[j];
			RECORD[i][j] = i;
			for(int k = i+1; k < j; k++){
				int temp = lookupChain(i, k, COLUME_MARTIX, MEMO, RECORD) + lookupChain(k+1, j, COLUME_MARTIX, MEMO, RECORD) + COLUME_MARTIX[i-1]*COLUME_MARTIX[k]*COLUME_MARTIX[j];
				if(temp < min_u){
					min_u = temp;
					RECORD[i][j] = k;
				}
			}
			MEMO[i][j] = min_u;
			return min_u;
		}

	/*
	 * trackback best devide plan with RECORD matrix using recursion
	 * @param RECORD the (N+1)-order matrix recording devide point between index i & j in matrixChain on which we can get best answer
	 * @param i index of the first matrix in this part of matrix chain
	 * @param j index of the last matrix in this part of matrix chain
	 */
	public static void traceBackMatrixChain(int[][] RECORD, int i, int j){
		if(i == j) return;
		
		traceBackMatrixChain(RECORD, i, RECORD[i][j]);
		traceBackMatrixChain(RECORD, RECORD[i][j]+1, j);
		System.out.println("(A" + i + " ... A" + RECORD[i][j] +
			") * (A " + RECORD[i][j]+1 + " ... A" + j + ")") ;
	}
	
	
/**
 * Question 2: MinWeightTriangulation
 * @editTime 2016-04-12 08:11-09:30
 */
	/*
	 * return weight of triangle which three points marked index i, k, and j
	 */
	public static int weight(int i, int k, int j){
		int weight = 0;	//need to be define
		return 0;
	}
	
	/*
	 * solve MinWeightTriangulation problem with RECURSION
	 * @param i first point's index in the polygons in this recursion step
	 * @param j last point's index in the polygons in this recursion step
	 * @param RESULT a (N+1)-order matrix recording minWeight of the best deviding plan
	 * @param RECORD a (N+1)-order matrix recording deviding plan
	 */
	public static int recursionMinWeightTriangulation(int i, int j, int[][] RECORD){
		if(i == j)	return 0;
		
		//weight(int, int, int) is unDefined
		int minWeight = recursionMinWeightTriangulation(i, i, RECORD) + recursionMinWeightTriangulation(i, j, RECORD) + weight(i, i, j);
		RECORD[i][j] = i;
		for(int k = i+1; k < j; k++){
			int tempWeight = recursionMinWeightTriangulation(i, k, RECORD) + recursionMinWeightTriangulation(k, j, RECORD) + weight(i, k, j);
			if(tempWeight < minWeight){
				minWeight = tempWeight;
				RECORD[i][j] = k;
			}
		}
		return minWeight;
	}
	
	/*
	 * use DYNAMICPLAN to solve minWeightTriangulation (by using iteration)
	 * @param n	number of points in the polygons
	 * @param RESULT a (N+1)-order matrix recording minWeight of the best deviding plan
	 * @param RECORD a (N+1)-order matrix recording deviding plan
	 */
	public static void iterationMinWeightTriangulation(int n, int[][] RESULT, int[][] RECORD){
		for(int initial = 0; initial < n; initial++)	RESULT[initial][initial] = 0;
		
		for(int step = 1; step < n; step++){
			for(int i = 0; i < n-step; i++){
				int j = i+step;
				//weight(int, int, int) is unDefined
				int minWeight = RESULT[i][i] + RESULT[i+1][j] + weight(i, i, j);
				RECORD[i][j] = i;
				for(int k = i+1; k < j; k++){
					int tempWeight = RESULT[i][k] + RESULT[j+1][j] + weight(i, k, j);
					if(tempWeight < minWeight){
						minWeight = tempWeight;
						RECORD[i][j] = k;
					}
				}
				RESULT[i][j] = minWeight;
			}
		}
	}
	
	/*
	 * use DYNAMICPLAN to solve minWeightTriangulation (by using a memo matrix)
	 * @param i	first point's index in the polygons
	 * @param j	last point's index in the polygons
	 * @param n	number of points in the polygons
	 * @param MEMO a (N+1)-order matrix recording minWeight of the best deviding plan between index i & j which has been calculated,
				   so we can get calculated value much more quickly than former two approach
	 * @param RECORD a (N+1)-order matrix recording deviding plan
	 * @return minweight of the triangle
	 */
	public static int memoMinWeightTriangulation(int i, int j, int n, int[][] MEMO, int[][] RECORD){
		initialMEMO(n, MEMO);
		return lookupInMEMO(i, j, n, MEMO, RECORD);
	}
	/*
	 * initial memo with 0
	 * @param n number of points in the polygons
	 * @param MEMO the memo matrix using in this approach
	 */
	public static void initialMEMO(int n, int[][] MEMO){
		for(int i = 0; i < n; i++)
			for(int j = i; j < n; j++)
				MEMO[i][j] = 0;
	}
	/*
	 * this is the main part of this approach, code struct is easy to understand, just make some small change in funtion recursionMinWeightTriangulation()
	 */
	public static int lookupInMEMO(int i, int j, int n, int[][] MEMO, int[][] RECORD){
		if(i == j) return 0;
		if(MEMO[i][j] != 0) return MEMO[i][j];	//different from recursionMinWeightTriangulation
		
		int minWeight = lookupInMEMO(i, i, n, MEMO, RECORD) + lookupInMEMO(i+1, j, n, MEMO, RECORD) + weight(i, i, j);
		RECORD[i][j] = i;
		for(int k = i+1; k < j; k++){
			int tempWeight = lookupInMEMO(i, k, n, MEMO, RECORD) + lookupInMEMO(k+1, j, n, MEMO, RECORD) + weight(i, k, j);
			if(tempWeight < minWeight){
				minWeight = tempWeight;
				RECORD[i][j] = k;
			}
		}
		MEMO[i][j] = minWeight;
		return minWeight;
	}
	
	/*
	 * trackback best devide plan with RECORD matrix using recursion
	 * @param RECORD a (N+1)-order matrix recording deviding plan
	 * @param i first point's index in the polygons
	 * @param j last point's index in the polygons
	 */
	public static void traceBackTriangulation(int[][] RECORD, int i, int j){
		if(i == j) return;
		
		traceBackTriangulation(RECORD, i, RECORD[i][j]);
		traceBackTriangulation(RECORD, RECORD[i][j]+1, j);
		System.out.println("(P" + i + " ... P" + RECORD[i][j] +
			") (P " + RECORD[i][j]+1 + " ... P" + j + ")");
	}
	
/**
 * Question 3: LSCLength
 * @editTime 2016-04-12 
 */
	/*
	 * use recursion to solve LSCLength 
	 * @param i index of array A of which value is current comparing
	 * @param j index of array B of which value is current comparing
	 * @param A source array A
	 *		    elements in A should be strictly increasing
	 * @param B source array B
	 *		    elements in B should be strictly increasing
	 * @return max number of common elements between arr A & arr B
	 */
	public static int resursionLSCLength(int i, int j, int[] A, int[] B){
		if(i==0 || j==0)	return 0;
		if(A[i] == B[j])	return resursionLSCLength(i-1, j-1, A, B) + 1;
		if(A[i] > B[j])	return resursionLSCLength(i-1, j, A, B);
		else return resursionLSCLength(i, j-1, A, B);
	}
	
	/*
	 * use dynamicplan to solve LSCLength
	 * @param RES a 2-orders array saving max number of common elements between arr A & arr B
	 * @param REC a 2-orders array saving iteration path
	 * @param A source array A
	 *		    elements in A should be strictly increasing
	 * @param B source array B
	 *		    elements in B should be strictly increasing
	 */
	public static void iterationLSCLength(int[][] RES, int[][] REC, int[] A, int[] B){
		for(int i = 0; i <= A.length; i++) RES[i][0] = 0;
		for(int j = 0; j <= B.length; j++) RES[0][j] = 0;
		
		for(int i = 1; i <= A.length; i++){
			for(int j = 1; j <= B.length; j++){
				if(A[i-1] == B[j-1]) {RES[i][j] = RES[i-1][j-1]+1; REC[i][j] = 1;}	// REC[i][j] = 1 means
				if(A[i-1] > B[j-1])  {RES[i][j] = RES[i-1][j]; REC[i][j] = 2;}		// REC[i][j] = 2;
				if(A[i-1] < B[j-1])  {RES[i][j] = RES[i][j-1]; REC[i][j] = 3;}		// REC[i][j] = 3;
			}
		}
	}
	
	/*
	 * create LSC with 2-orders array RES & REC using recursion and return LSC
	 * @param RES a 2-orders array saving max number of common elements between arr A & arr B
	 * @param REC a 2-orders array saving iteration path
	 * @param A source array A
	 *		    elements in A should be strictly increasing
	 * @param B source array B
	 *		    elements in B should be strictly increasing
	 * @return arr LSC
	 */
	public static int[] LSC(int[][] RES, int[][] REC, int[] A, int[] B){
		int length = RES[A.length][B.length];
		int[] LSC = new int[length];
		valueLSC(REC, A.length, B.length, length, LSC, A);
		return LSC;
	}
	/*																								  
	 * iteration progress valuing LSC																  j j j j ... j j j j
	 * @param REC the 2-orders array saving iteration path when calculate elements' number		eg:	i[* * * * ... * * * *]
	 * @param i vertical index of REC in current iteration										REC	i[* * * * ... * * * *]	
	 * @param j horizon index of REC in current iteration											i[* * * * ... * * * *] 
	 * @param k index of LSC in current iteration													i[* * * * ... 1 * * *] 2->1
			    initialize k by LSC.length															i[* * * * ... 2 3 * *] 3->2
	 * @param LSC max-length array contains															i[* * * * ... * * 1 *] 1->3
	 * @param A source array A																		i[* * * * ... * * * 1] 1->1
	 */
	public static void valueLSC(int[][] REC, int i, int j, int k, int[] LSC, int[] A){
		if(i == 0 || j == 0) return;
		if(REC[i][j] == 1) {valueLSC(REC, i-1, j-1, k-1, LSC, A); LSC[k] = A[i];}
		if(REC[i][j] == 2) valueLSC(REC, i-1, j, k, LSC, A);
		if(REC[i][j] == 3) valueLSC(REC, i, j-1, k, LSC, A);
	}
	
/**
 * Question 4: ImageCompress
 * 图像压缩问题，即
 */
	public static void 
	
	
/**
 * Question 5: 电路布线
 */
 
/**
 * Question 6: MaxSumOfChildArray
 */
 
/**
 * Question 7: FlowShop
 */
 
 
/**
 * Question 8: Investment
 */
 
/**
 * Question 9: 0-1 KnapSackProblem
 * m(i, j) 表示背包容量为j的情况下，1到i的可选物品中得到最大的价值（也可以反向规划，i到n的物品中装得到的最大价值。区别是矩阵中最后的解的位置不同）
 * 递推方程：M(i, j) = M(i-1, j) 							j<Wi
 *		   			 = max{M(i-1, j) , M(i-1, j-Wi)+Vi}		j>=Wi
 * 初始化：	n+1阶矩阵的第一行第一列赋初始值为0
 * 
 */
	/*
	 * 背包问题
	 * 输入参数：容量c，重量数组weight[]，价值数组value[]，二维矩阵存i，j下的最大装载价值MaxValue[][]
	 * @param c 背包最大容量
	 * @param weight 物品的质量数组
	 * @param value 物品价值数组
	 * @param maxValue 求解结果矩阵， maxValue[i][j]表示i个物品，在容量为j下背包的最大装载价值
	 * @return maxValue
	 */
	public static int maxLoadValue(int c, int[] weight, int[] value, int[][] maxValue){
		//初始化数组
		int gNum = weight.length;
		for(int i = 0; i <= c; i++)	maxValue[0][i] = 0;
		for(int i = 0; i <= gNum; i++)	maxValue[i][0] = 0;
			
		//递推求解
		for(int i = 1; i <= gNum; i++){
			
			int jBound = min(c+1, w[i-1]); //这个函数放出去
			for(int j = 1; j<jBound; j++){
				maxValue[i][j] = maxValue[i-1][j];
			}
				
			for(int j = weight[i-1]; j<=c; j++){
				maxValue[i][j] = maxValue[i-1][j] > (maxValue[i-1, j-weight[i]]+value[i]) ? maxValue[i-1][j] : (maxValue[i-1, j-weight[i]]+value[i]);
			}
		}
		return maxValue[gNum][c];
	}
	
	public static void traceBackKnapSack(int[][] maxValue, int[] w, int[] x){
		
		int gNum = maxValue.length-1;
		int kc = maxValue[0].length-1;
		
		for(int i = gNum; i > 0; i--){
			if(maxValue[i][kc] == maxValue[i-1][kc]){
				x[i-1] = 0;
			} else {
				x[i-1] = 1;
				kc -= w[i-1];
			}
		}
	}

	
	
	
	public static void main(String[] args){
		
	}
}