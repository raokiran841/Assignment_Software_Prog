import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.text.*;
import java.util.*;
class A{
	class B{
		int c;
	}
	B b;
	public A(){
		this.b = new B();
	}
}

class C{
	class B{
		int a;
	}
	B b;
	public C(){
		this.b = new B();
	}
}

class Demo{
	// method to convert integer month to String
	public static String month(String i){
		String mon = "";
		switch(i){
			case "01": mon = "JAN";break;
			case "02": mon = "FEB";break;
			case "03": mon = "MAR";break;
			case "04": mon = "APR";break;
			case "05": mon = "MAY";break;
			case "06": mon = "JUN";break;
			case "07": mon = "JUL";break;
			case "08": mon = "AUG";break;
			case "09": mon = "SEP";break;
			case "10": mon = "OCT";break;
			case "11": mon = "NOV";break;
			case "12": mon = "DEC";break;
		}
		return mon;
	}

	public static void main(String[] args) throws IOException {
		A a = new A();
		C c = new C();
		// solution to question 1.
		a.b.c = c.b.a;

		// Solution to question 2.
		/*** OUTPUT will look like this.
		NOV : 4
		JAN : 15
		DEC : 31
		FEB : 12
		MAR : 28
		APR : 29
		MAY : 6
		JUN : 4
		JUL : 18
		AUG : 9 ***/

		File file = new File("C:\\Users\\kikirao\\Downloads");
		File arr[] = file.listFiles();

		Map<String, Integer> hm = new HashMap<>();
		
		for(File f: arr){
			Path path = f.toPath();
			BasicFileAttributes fatr = Files.readAttributes(path, BasicFileAttributes.class);
			FileTime ft = fatr.creationTime();
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			
			String str = df.format(ft.toMillis());
			String time[] = str.split("/");
			
			if(hm.containsKey(time[0])){
				hm.put(time[0], hm.get(time[0]) + 1);
			} else {
				hm.put(time[0],1);
			}
		}

		// prints the month : no of files created on that month
		for (Map.Entry<String,Integer> entry : hm.entrySet()){
			System.out.println(month(entry.getKey()) + " : " + entry.getValue());
		}  
	}
}