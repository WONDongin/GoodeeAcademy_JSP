package ex13.el;

import java.util.Arrays;
import java.util.List;

public class Compute {
	public static int add(String x, String y) {
		   try {
			   int a = Integer.parseInt(x);
			   int b = Integer.parseInt(y);
			   return a + b;
		   } catch (Exception e) {}
		   return 0;
	   }
	   public static boolean contains(String[] arr,String str) {
		   try {
			   List<String> list = Arrays.asList(arr);
			   return list.contains(str);
		   } catch (Exception e) {e.printStackTrace();}
		   return false;
	   }

}