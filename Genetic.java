import java.io.*;
import java.util.*;
class Genetic{
	public int fitness(StringBuilder str,Vector<Integer> val,Vector<Integer> wgt,int w){
		int v=0;
		int wv=0;
		for (int i=0;i<val.size() ;i++ ) {
			if(str.charAt(i)=='1'){
				v+=val.elementAt(i);
				wv+=(int)wgt.elementAt(i);
			}
		}
		if(wv>w){
			return 0;
		}
		return v;
	}
	public void insert(Stack<StringBuilder> st,Stack<Integer> wgt,StringBuilder str,int w){
		if(w==0){
			return;
		}
		if(st.empty()){
			st.push(str);
			wgt.push(w);
			return;
		}
		else{
			if(w>=wgt.peek()){
				st.push(str);
				wgt.push(w);
				return;
			}
			else{
				StringBuilder s=st.peek();
				int tw=wgt.peek();
				st.pop();
				wgt.pop();
				insert(st,wgt,str,w);
				wgt.push(tw);
				st.push(s);
				return;
			}
		}
	}
	public void produce(StringBuilder str,int i,int n,HashMap<StringBuilder,Integer> map,Stack<StringBuilder> st,Stack<Integer> wgt,Vector<Integer> val,Vector<Integer> wval,int w){
		StringBuilder s=new StringBuilder(str);
		Integer k=map.get(s);
		if(k==null){
			k=0;
		}
		if(k==0){
			int wk=fitness(str,val,wval,w);
			if(w>0){
			insert(st,wgt,s,wk);
			}
			map.put(s,1);
		}
		if(i==n){
			return;
		}
		produce(str,i+1,n,map,st,wgt,val,wval,w);
		str.setCharAt(i, '1');
		produce(str,i+1,n,map,st,wgt,val,wval,w);
		str.setCharAt(i, '0');
		return;
	}
	public void crossover(StringBuilder s1,StringBuilder s2,int n){
		Random rn=new Random();
		int low=0;
		int high=n-1;
		int index=rn.nextInt()%n;
		if(index>=0&&index<(n-1)){
			String str1=new String(s1.substring(0,index));
			String str2=new String(s2.substring(0,index));
			s1.replace(0,index,str1);
			s2.replace(0,index,str2);
		}
		return;
	}
	public void mutation(StringBuilder str,int n){
		Random rn=new Random();
		int low=0;
		int i=rn.nextInt()%n;
		if(i>=0&&i<=(n-1)){
		if(str.charAt(i)=='0'){
			str.setCharAt(i, '1');
		}
		else{
			str.setCharAt(i, '0');
			}
		}
	}
	public void printstack(Stack<StringBuilder> st){
		while (!st.empty()) {
			System.out.println(st.peek());
			st.pop();
		}

	}
	public void reproduction(int n,Vector<Integer> val,Vector<Integer> wval,int w){
		Stack<StringBuilder> cell=new Stack<StringBuilder>();
		HashMap<StringBuilder,Integer> map=new HashMap<StringBuilder,Integer>();
		StringBuilder chrom=new StringBuilder();
		for (int i=0;i<n;i++ ) {
			chrom.append('0');
		}
		System.out.println(chrom);
		Stack<StringBuilder> st=new Stack<StringBuilder>();
		Stack<Integer> wgt=new Stack<Integer>();
		produce(chrom,0,n,map,st,wgt,val,wval,w);
		int generation=100;
		int ct=1;
		System.out.println("initial fittest sollution is: "+st.peek()+"with value="+getValue(st.peek(),val));
		//printstack(st);
		while(ct<=generation){
			if(st.size()>=2){
			StringBuilder c1=new StringBuilder(st.peek());
			st.pop();
			StringBuilder c2=new StringBuilder(st.peek());
			st.pop();
			crossover(c1,c2,val.size());
			insert(st,wgt,c1,fitness(c1,val,wval,w));
			insert(st,wgt,c2,fitness(c2,val,wval,w));
		}
			if(!st.empty()){

			StringBuilder c1=new StringBuilder(st.peek());
			st.pop();
			mutation(c1,c1.length());
			insert(st,wgt,c1,fitness(c1,val,wval,w));
			}
			if(!st.empty())
		System.out.println(ct+" generation fittest is: "+st.peek()+"with value="+getValue(st.peek(),val));
			ct++;
		}
		if(!st.empty())
		System.out.println("the fittest is: "+st.peek()+"with value="+getValue(st.peek(),val));
	}
	public int getValue(StringBuilder str,Vector<Integer> val){
		int wv=0;
		for (int i=0;i<val.size() ;i++ ) {
			if(str.charAt(i)=='1'){
				wv+=(int)val.elementAt(i);
			}
		}
		return wv;
	}
	public static void main(String[] args) {
		Genetic obj=new Genetic();
		Scanner ob=null;
		Vector<Integer> val=new Vector<Integer>();
		Vector<Integer> wgt=new Vector<Integer>();
		int w=0;
		try{
			File f=new File("value.txt");
			ob=new Scanner(f);
			int n=ob.nextInt();
			w=ob.nextInt();
			for (int i=0;i<n ;i++ ) {
				val.add(ob.nextInt());
			}
			f=new File("weight.txt");
			ob=new Scanner(f);
			for (int i=0;i<n ;i++ ) {
				wgt.add(ob.nextInt());
			}
		}
		catch(Exception e){	
		}
		obj.reproduction(val.size(),val,wgt,w);
	}
}