/**
 * 
 */
package cn.nwpu.drower.modeling;

/**
 * @author Drower
 *
 */
public abstract class Modeling {

	//order
	static final int N = 5;
	
	//3-dimensional matrix
	static final int[][][] matrix = {{{0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0},},
		 
		 {{0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0},},
		
		 {{0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0},},
		
		 {{0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0},},
		
		 {{0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0},
		  {0, 0, 0, 0, 0},}
		};
	
	//judge horizontal direction
	public static boolean horizontalDirection(int[][][] matrix){
		
		for(int i = 0; i < N; i++){
			
			int counter_dip_1 = 0;
			int counter_dip_2 = 0;
			
			for(int j = 0; j < N; j++){
				
				int count = 0;

				for(int k = 0; k < N; k++){
					count += matrix[i][j][k];
					
					counter_dip_1 += matrix[i][j][j];
					counter_dip_2 += matrix[i][N-1-j][N-1-j];
				}
				
				if(count == 0)	return false;
			}
			
			if(counter_dip_1 == 0 || counter_dip_2 == 0)	return false;
		}
		
		return true;
	}
	
	//judge vertical direction
	public static boolean verticalDirection(int[][][] matrix){
		
		
		for(int i = 0; i < N; i++){
			
			int counter_dip_1 = 0;
			int counter_dip_2 = 0;
			
			for(int j = 0; j < N; j++){
				
				int count = 0;

				for(int k = 0; k < N; k++){
					count += matrix[j][k][i];
					
					counter_dip_1 += matrix[j][k][k];
					counter_dip_2 += matrix[j][N-1-k][N-1-k];
				}
				
				if(count == 0)	return false;
			}
			
			if(counter_dip_1 == 0 || counter_dip_2 == 0)	return false;
		}
		
		return true;
	}
	
	//judge inner direction
	public static boolean innerDirection(int[][][] matrix){
		
		for(int i = 0; i < N; i++){
			
			int counter_dip_1 = 0;
			int counter_dip_2 = 0;
			
			for(int j = 0; j < N; j++){
				
				int count = 0;

				for(int k = 0; k < N; k++){
					count += matrix[k][i][j];
					
					counter_dip_1 += matrix[k][i][i];
					counter_dip_2 += matrix[k][N-1-i][N-1-i];
				}
				
				if(count == 0)	return false;
			}
			
			if(counter_dip_1 == 0 || counter_dip_2 == 0)	return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 */
	public static boolean isOkay(int[][][] matrix){
		
		if(horizontalDirection(matrix))		
			if(verticalDirection(matrix))
				if(innerDirection(matrix))
					return true;
		
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if(isOkay(matrix))
			System.out.println("Good solution!");
		else
			System.out.println("Bad solution!");
	}

}
