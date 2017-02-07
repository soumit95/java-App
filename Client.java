import java.io.*;
import java.net.*;
public class Client{
	public static void main(String[] args) throws Exception{
		Socket cs=new Socket("127.0.0.1",8081);
		PrintWriter toserver=new PrintWriter(cs.getOutputStream(),true);
		BufferedReader usr=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("enter a number");
		String n=usr.readLine();
		toserver.println(n);
		System.out.println("send to server");
	}
}
