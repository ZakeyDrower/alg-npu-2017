/**
 * HuaFen
 * 2016-03-23 14:50-15:04
 */
 import java.util.*;
 
 public abstract class HuaFen{
	 
	 /**
	  * count the number of huaFen of an integer
	  * @param integer integer to be depart
	  * @param max max value in all departed numbers
	  */
	 private static int huaFen(int integer, int max){
		 
		 if(integer < 1 || max < 1) return 0;
		 if(integer == 1 || max == 1) return 1;
		 if(integer < max) return huaFen(integer, integer);
		 if(integer == max) return huaFen(integer, max-1)+1;
		 return huaFen(integer, max-1)+huaFen(integer-max, max);
	 }
	 
	 public static void main(String[] args){
		 
		 Scanner sc = new Scanner(System.in);
		 int integer = sc.nextInt();
		 
		 while(integer > -1){
			 
			 System.out.println("Answer: " + huaFen(integer, integer));
			 integer = sc.nextInt();
		 }
	 }
 }