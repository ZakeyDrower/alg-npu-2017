/**
 * Binary search
 * 2016-03-23 18:37-18:56
 */
import java.util.*;

public abstract class BinarySearch{
	
	/**
	 * search key number in an integer array sorted in ascending order
	 * @param array is the array to look for in
	 * @param key is the number to find out
	 * @param left is the min index searched in the function
	 * @param right is the max index searched in the function
	 */
	private static void binarySearch(int[] array, int key, int left, int right){
		
		if(left<0 || right<0){
			 System.out.println("ERROR!");
			 return;	
		}
		if(left > right) {
			System.out.println("Not Found!");
			return;
		}
		
		int middle = (left + right) / 2;
		if(array[middle] == key) {
			System.out.println("Target index is " + index);
			return;
		}
		else if(array[middle] > key) binarySearch(array, key, left, middle);
			 else binarySearch(array, key, middle, right);
	}
	
	
	
	public static void main(String[] args){
		
		//place test code block here
		//do some initialization
		int[] array = {0, 1, 3, 5, 6, 7, 8, 9, 10, 15, 18, 19, 20};
		int key;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n--------start test-------\n\nPlease input a number(>=0) :\n");
		
		while(true){
			key = sc.nextInt();
			if(key >= 0)
				binarySearch(array, key, 0, array.length);
			else break;
		}
		
		System.out.println("\n---------end test--------\n");
	}
}