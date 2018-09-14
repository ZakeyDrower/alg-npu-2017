/**
 * MiGong
 * 2016-03-24 19:49-20:18 ERROR!WRONG ALGRORITHM!
 * 2016-03-24 20:29-??:??
 *
 * Some thoughts about this question:
 * There may not be a way to find all approaches from one point to destination, cause that without algorithm 
 * you'll find it impossible to find all approaches if there're not enough constraints.
 */
 import java.util.*;
 
 public abstract class MiGong{
	 
	//private static int[][] MARTIX = new int[100][]; 
	private static int[][] MARTIX = {
		{1,1,1,1,1,1},
		{1,0,0,1,0,1},
		{1,1,0,1,0,1},
		{0,1,1,1,1,1},
		{1,1,0,1,0,1},
		{1,1,0,1,1,1}};
	private static int ROW_MARTIX;
	private static int COLUME_MARTIX;
	private static int X_beg;
	private static int Y_beg;
	private static int X_des;
	private static int Y_des;
	private static int COUNTER;
	
/* 	private static void resetMartix(){
		
		& MARTIX = {
		{1,1,0,1,1,1},
		{0,1,0,1,0,1},
		{1,1,1,1,0,1},
		{1,0,1,0,1,1},
		{1,1,1,0,1,1},
		{0,0,0,0,1,1}};
	} */
	
	
	/* // to judge if the martix has been ergodiced
	private static boolean isErgodiced(){
		
		for(int i = 0; i < ROW_MARTIX; i++){
			for(int j = 0; j < COLUME_MARTIX; j++){
				if(MARTIX[i][j] == 1)	return false;
			}
		}
		return true;
	} */
	
	//private static boolean ARRIVED = false;
	
	private static void zouMiGong(int x_cur, int y_cur, String currentDir) {
		
		//if(ARRIVED){ return;}
		/* if(isErgodiced()){
			 if(x_cur == 0 && y_cur == 0)  return;
		} */
		
		//value 5 means that you've been here before
		if(MARTIX[x_cur][y_cur] == 2) {
			System.out.println("You've ever been here before! Turn back!\n");
			return;
		} else MARTIX[x_cur][y_cur] = 2;

		if(arrived(x_cur, y_cur)) {
			COUNTER = COUNTER + 1;
			MARTIX[x_cur][y_cur] = 1;
			System.out.println("WAY " + COUNTER + " : Arrived at [" + x_cur +", " + y_cur + "]!\n");	
			ARRIVED = true;
			return;
		}
		
		System.out.println("Now you are at point [" + x_cur +", " + y_cur + "]\n");
		
		/*
		 * this block is the main part of the whole doc
		 * here you choose a way and go to next step
		 */
		if(currentDir == "up"){
			
			if(canGo(x_cur, y_cur-1))	zouMiGong(x_cur, y_cur-1, "left");
			if(canGo(x_cur-1, y_cur))	zouMiGong(x_cur-1, y_cur, "up");
			if(canGo(x_cur, y_cur+1))	zouMiGong(x_cur, y_cur+1, "right");
			System.out.println("No more way at point [" + x_cur +", " + y_cur + "]! Turn down!\n");
		} else if(currentDir == "down"){
			
			if(canGo(x_cur, y_cur+1))	zouMiGong(x_cur, y_cur+1, "right");
			if(canGo(x_cur+1, y_cur))	zouMiGong(x_cur+1, y_cur, "down");
			if(canGo(x_cur, y_cur-1))	zouMiGong(x_cur, y_cur-1, "left");	
			System.out.println("No more way at point [" + x_cur +", " + y_cur + "]! Turn up!\n");
		} else if(currentDir == "left"){
			
			if(canGo(x_cur+1, y_cur))	zouMiGong(x_cur+1, y_cur, "down");
			if(canGo(x_cur, y_cur-1))	zouMiGong(x_cur, y_cur-1, "left");
			if(canGo(x_cur-1, y_cur))	zouMiGong(x_cur-1, y_cur, "up");
			System.out.println("No more way at point [" + x_cur +", " + y_cur + "]! Turn right!\n");
		} else if(currentDir == "right") {
			
			if(canGo(x_cur-1, y_cur))	zouMiGong(x_cur-1, y_cur, "up");
			if(canGo(x_cur, y_cur+1))	zouMiGong(x_cur, y_cur+1, "right");
			if(canGo(x_cur+1, y_cur))	zouMiGong(x_cur+1, y_cur, "down");
			System.out.println("No more way at point [" + x_cur +", " + y_cur + "]! Turn left!\n");
		}
	}
	
	
	
	private static boolean canGo(int x_direct, int y_direct){
	
		//value 1 means there is a way, value 0 means there is a wall
		if(!((x_direct >= 0) && (x_direct < ROW_MARTIX)
			 && (y_direct >= 0) && (y_direct < COLUME_MARTIX)))
			return false;	//index out of range
		else if(MARTIX[x_direct][y_direct] == 1)	
			return true;
			 else return false;
	}
	
	
	
	private static boolean arrived(int x_cur, int y_cur) {
		if((x_cur == X_des) && (y_cur == Y_des)) return true;
		return false;
	}
	
	
	
	private static void setRow(){
		System.out.println("Please set row_number of the martix : \n");
		Scanner sc_row = new Scanner(System.in);
		ROW_MARTIX = sc_row.nextInt();
		System.out.println("Set row_number succeed : " + ROW_MARTIX + "\n");
	}
	
	
	
	private static void setColume(){
		System.out.println("Please set colume_number of the martix : \n");
		Scanner sc_col = new Scanner(System.in);
		COLUME_MARTIX = sc_col.nextInt();
		System.out.println("Set colume_number succeed : " + COLUME_MARTIX + "\n");
	}
	
	
	
	private static void setMartix(){
		
		/*
		 * to be finished
		 * HERE'S A PROBLEM: How to initialize 
		 */
		System.out.println("Please set martix : \n");
		Scanner sc_mar = new Scanner(System.in).useDelimiter("/");
		for(int i = 0; i < ROW_MARTIX; i++){
			for(int j = 0; j < COLUME_MARTIX; j++){
				MARTIX[i][j] = sc_mar.nextInt();
			}
		}
		System.out.println("Set martix succeed...\n");
	}
	
	
	
	private static void setStartPoint(){
		
		System.out.println("Please set start point : \n");
		//int[] a = new int[2];
		Scanner s = new Scanner(System.in);
		
		//for(int i = 0; i < 2; i++) a[i] = s.nextInt();
		//X_beg = a[0];
		//Y_beg = a[1];
		X_beg = s.nextInt();
		Y_beg = s.nextInt();
		
		System.out.println("Set start point succeed...\n");
	}
	
	
	
	private static void setEndPoint(){
		
		System.out.println("Please set end point : \n");
		int[] a = new int[2];
		Scanner s = new Scanner(System.in);
		
		for(int i = 0; i < 2; i++)	a[i] = s.nextInt();
		
		X_des = a[0];
		Y_des = a[1];
		
		System.out.println("Set end point succeed...\n");
	}
	
	
	
	public static void main(String[] args){
		
		System.out.println("---------- Start test ----------\n");
		
		COUNTER = 0;
		/*
		setRow();
		setColume();
		setMartix();
		setStartPoint();
		setEndPoint();*/
		
		ROW_MARTIX = 6;
		COLUME_MARTIX = 6;
		X_beg = 0;
		Y_beg = 0;
		X_des = 3;
		Y_des = 3;
		
		System.out.println(">>>>>>>>>>>>>>>> Start run >>>>>>>>>>>>>>>>\n");
		if(canGo(X_beg, Y_beg-1)) zouMiGong(X_beg, Y_beg-1, "left");
		if(canGo(X_beg-1, Y_beg)) zouMiGong(X_beg-1, Y_beg, "up");
		if(canGo(X_beg, Y_beg+1)) zouMiGong(X_beg, Y_beg+1, "right");
		if(canGo(X_beg+1, Y_beg)) zouMiGong(X_beg+1, Y_beg, "down");
		System.out.println(">>>>>>>>>>>>>>> End running >>>>>>>>>>>>>>>\n");
		if(COUNTER == 0) System.out.println("There's no approach to destination!\n");
		else System.out.println("There're " + COUNTER + " approaches to destination.\n");
		
		System.out.println("----------- End test -----------\n");
	}
 }