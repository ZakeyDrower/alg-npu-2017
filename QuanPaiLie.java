/**
 * QuanPaiLie
 * 2016-03-22 22:03 - 22:16
 * 2016-03-22 23:50 - 00:20
 */
 import java.util.*;
 
 public abstract class QuanPaiLie{
	 
	 private static void quanPaiLie(int index, int n, char[] s){
		 
		 if(index == n-1){
			 
			 for(int count = 0; count < n; count++)
				System.out.print(s[count]);
			 System.out.println();
		 } else {
			 
			 for(int i = index; i < n; i++){
				 
				 swap(s, i, index);
				 quanPaiLie(index + 1, n, s);
				 swap(s, i, index);
			 }
		 }
	 }
	 
	 private static void swap(char[] a, int i, int j){
		 
		 char temp = a[j];
		 a[j] = a[i];
		 a[i] = temp;
	 }
	 
	 public static void main(String[] args){
		 
		 char[] str = new char[30];
		 String arrayTable = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		 str = arrayTable.toCharArray();
		 
		 int n = 1;
		 Scanner sc = new Scanner(System.in);
		 
		 while(n != 0){
			 
			 n = sc.nextInt();
			 quanPaiLie(3, n, str);
		 }

		 return;
	 }
 }