/**
 * QuickSort
 * @author Drower
 * @editTime 2016-03-29 09:54 - 10:08 StackOverflowError
 * @editTime 2016-03-30 14:38 - 14:53 Error! WrongAnswer
 * @debug 	 2016-03-30 14:53 - 15:32 E:\Code>java QuickSort
 *									  1 4 5 6 7 8 8 8 9
 */
 
 public abstract class QuickSort{
	 
	/*
	 * quickSort(int[] arr, int left, int right)
	 * 
	 * @param arr
	 * @param left
	 * @param right
	 */ 
	private static void quickSort(int[] arr, int left, int right){
		
		if(left >= right) return;
		printArray(arr);
		int p = devide(arr, left, right);
		quickSort(arr, left, p-1);		//sort left part
		quickSort(arr, p+1, right);		//sort right part
	}
	
	/*
	 * devide(int[] arr, int l, int r)
	 * 
	 * @param arr
	 * @param l
	 * @param r
	 */
	private static int devide(int[] arr, int left, int right){
		
		// int x = arr[l];
		// int i = l, j = r+1;
		
		// while(true){
			
			// while(arr[++i]<x && i<r);
			// while(arr[--j]>x && j>l);
			// if(i >= j) break;
			// swap(arr, i, j);
		// }
		
		// arr[l] = arr[j];
		// arr[j] = x;
		// return j;
		int pivotpos = left;
		int pivot = arr[left];
		
		for(int i = left+1; i <= right; i++){
			if(arr[i] < pivot){
				pivotpos++;
				if(pivotpos != i)
					swap(arr, pivotpos, i);
			}
		}
		arr[left] = arr[pivotpos];
		arr[pivotpos] = pivot;
		
		return pivotpos;
	}
	 
	 
	/*
	 * swap(int[] a, int i, int j)
	 * a simple way to swap two numbers in an array with subscript i and j
	 * @param array a and subscript i & j
	 */
	private static void swap(int[] a, int i, int j){
		
		int temp = a[i]; 
		a[i] = a[j];
		a[j] = temp;
	}
	
	/*
	 * printArray(int[] array)
	 * print array on cmd
	 * @param array target array to be printed
	 */
	private static void printArray(int[] array){
		 
		 for(int i = 0; i < array.length; i++)
			 System.out.print(array[i] + " ");
		 System.out.println();
	 }
	
	/**
	 * main(String[] args)
	 * main method to test quickSort();
	 */
	public static void main(String[] args){
		
		int[] arr = {1,6,5,4,7,0,2,3,9,8};
		quickSort(arr, 0, arr.length-1);
		printArray(arr);
	}
 }