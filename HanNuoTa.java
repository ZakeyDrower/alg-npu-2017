/**
 * HanNuoTa
 * 2016-03-22 13:20-13:56  WRONG!
 * 2016-03-22 18:55		   RIGHT!
 */
 import java.util.*;
 
 public abstract class HanNuoTa{
	 
	 private static void HanNuoTa(int n, char A, char B, char C){
		 
		 if(n == 0){
			 return;
		 }
		 
		 if(n == 1){
			 
			 System.out.println(A + " -> " + C);
		 } else {
			 
			 /*HanNuoTa(n-1, 'A', 'C', 'B');
			 HanNuoTa(n-1, 'A', 'B', 'C');
			 HanNuoTa(n-1, 'B', 'A', 'C');*/
			 
			 HanNuoTa(n-1, A, C, B);
			 HanNuoTa(1, A, B, C);
			 HanNuoTa(n-1, B, A, C);
		 }
	 }
	 
	 public static void main(String[] args){
		 
		 int n = 1;;
		 Scanner sc = new Scanner(System.in);
		 
		 while(n != 0){
			 
			 n = sc.nextInt();
			 HanNuoTa(n, 'A', 'B', 'C');
		 }

		 return;
	 }
 }