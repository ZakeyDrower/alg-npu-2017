/**
 * MergeSort
 * 2016-03-25 19:30-20:06
 * 2016-03-26 23:08-23:54
 */
 import java.util.*;
 
 public abstract class MergeSort{
	 
	 // private static int[] ARRAY =  {};
	 // private static int[] TEMP = new int[ARRAY.length];
	 
	 //weidaima
	 middle_index <- (left_index+right_index)/2;
	 if(left_index < right_index)
		 merge()
	 
	 
	/*
	 * mergeSort(int *arr, int left, int right)
	 * use function merge to sort target array into increasing order
	 * @param arr	target array to be sorted
	 * @param left	index of arr where it begins
	 * @param right index of arr where it ends
	 */
	 private static void mergeSort(int[] ARRAY, int left, int right){
		 
		 int middle = (left + right) / 2;
		 if(left < right){
			 mergeSort(ARRAY, left, middle);
			 mergeSort(ARRAY, middle+1, right);
			 merge(ARRAY, left, middle+1, right);
		 }
	 }
	 
	/*
	 * merge(int *arr, int left, int mid, int right)
	 * merge two seprated part of array arr to make it ordered
	 * @param arr	target array
	 * @param left	index of left part of arr where it begins
	 * @param mid	index of left part of arr where it ends
	 * @param right	index of right part of arr where it ends
	 */
	 private static void merge(int[] ARRAY, int x, int y, int z){
		 
		 int[] temp = new int[z-x+1];
		 int k = 0, i = x, j = y;
		 
		 while(i<y && j<z+1){
			 
			 if(ARRAY[i] > ARRAY[j]){
					 temp[k++] = ARRAY[j++];
				 }
				 else if(ARRAY[i] == ARRAY[j]){
					 temp[k++] = ARRAY[j++];
					 temp[k++] = ARRAY[i++];
				 }
				 else temp[k++] = ARRAY[i++];
		 }
		 while(i < y) temp[k++] = ARRAY[i++];
		 while(j < z+1) temp[k++] = ARRAY[j++];
		 
		 copy(temp, ARRAY, x);
	 }
	 
	 /*
	  * copy(int *tag, int *src, int beg)
	  * copy values from src to tag begin with index beg of tag
	  * @param tag tagert array of copy
	  * @param src source array of copy
	  * @param beg index of src where copy begins
	  */
	 private static void copy(int[] temp, int[] array, int x){
		 
		 for(int i = 0; i < temp.length; i++){
			 array[x+i] = temp[i];
		 }
	 }
	 
	 private static void printArray(int[] array){
		 
		 for(int i = 0; i < array.length; i++)
			 System.out.print(array[i] + " ");
		 System.out.println();
	 }
	 
	 public static void main(String[] args){
		 
		 int[] array = {1,0,5,2,7,9,8,6,4,3};
		 mergeSort(array, 0, array.length-1);
		 printArray(array);
	 }
 }