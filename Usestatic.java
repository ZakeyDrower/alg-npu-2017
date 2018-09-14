/**
 * Demostrate STATIC variables, methods, and bolcks
 * edit date : 2016-03-20 23:50
 */
 public class Usestatic{
	 
	 static int a = 1;
	 static int b;
	 
	 static void method(int x){
		 
		 System.out.println("x = " + x);
		 System.out.println("a = " + a);
		 System.out.println("b = " + b);
	 }
	 
	 // static block will be complied before main method when complieing
	 static{
		 
		 System.out.println("Static block initialized...");
		 b = a * 4;
	 }
	 
	 public static void main(String[] args){
		 
		 method(6);
	 }
 }