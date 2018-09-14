/**
 * QiongJuNWeiErJinZhiShu
 * QTBS : O(n^x) ?
 * 2016-03-22 09:28-09:53
 */
import java.util.*; 

public abstract class QiongJuNWeiErJinZhiShu{
	
	private static void QiongJu(int n, int i, int[] a){
		
		if(i == n){
			
			for(int j = 0; j < n; j++)
			System.out.print(a[j]);
			
			System.out.println();
		} else {
			
			a[i] = 0;
			//System.out.print(a[i]);
			QiongJu(n, i+1, a);
			a[i] = 1;
			//System.out.print(a[i]);
			QiongJu(n, i+1, a);
		}
	}
	
	public static void main(String[] args){
		
		int n;
		int[] a = new int[20];
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		QiongJu(n, 0, a);
	}
}