/**
 * Test using of 'static'
 * edit date : 2016-03-20 23:16
 */
public class Static{
	
	private static int a;
	private int b;
	
	// static blocks are initialized in fixed order
	static{
		
		Static.a = 6;
		System.out.println(a);
		
		Static static_instance = new Static();
		static_instance.Laugh();
		static_instance.b = 1000;
		System.out.println(static_instance.b);
	}
	
	static{
		
		// instance should be decleared again
		Static static_instance = new Static();
		
		static_instance.a = 66;
		System.out.println(static_instance.a);
	}
	
	public static void main(String[] args){
		
		// TODO Auto-generated method stub
	}
	
	static{
		
		Static static_instance = new Static();
		
		static_instance.a = 666;
		System.out.println(static_instance.a);
	}
	
	public void Laugh(){
		
		System.out.println("HiaHiaHia~");
	}
}