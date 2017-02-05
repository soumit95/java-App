import java.io.*;
import java.util.*;
class Tic{
	int[][] mat;
	boolean[][] visited;
	String name;
	public static int count=9;
	HashMap<Integer,Integer> rowsum;
	HashMap<Integer,HashMap<Integer,Integer>> row;
	HashMap<Integer,Integer> colsum;
	HashMap<Integer,HashMap<Integer,Integer>> col;
	public static Integer diag1sum=0;
	public static Integer diag2sum=0;
	HashMap<Integer,Integer> diag1;
	HashMap<Integer,Integer> diag2;
	public Tic(String name,int[][] mat,boolean[][] visited,HashMap<Integer,Integer> rs,HashMap<Integer,Integer> cs,HashMap<Integer,HashMap<Integer,Integer>> r,HashMap<Integer,HashMap<Integer,Integer>> c,HashMap<Integer,Integer> d1,HashMap<Integer,Integer> d2,Integer d1s,Integer d2s){
		this.mat=mat;
		this.name=name;
		this.visited=visited;
		rowsum=rs;
		colsum=cs;
		row=r;
		col=c;
		diag1=d1;
		diag2=d2;
	}
	public static boolean valid(int i,int j,Integer num,Tic player){
		HashMap<Integer,Integer> c=player.row.get(i);
			if(c!=null){
				Integer d=c.get(num);
			if(d!=null){
				if(d>(new Integer(0))){
				System.out.println("element exists try again");
				return false;
			}
			} 
			}
			HashMap<Integer,Integer> g=player.col.get(j);
			if(g!=null){
				Integer d=g.get(num);
			if(d!=null){
				if(d>(new Integer(0))){
				System.out.println("element exists try again");
				return false;
			}
			} 
			}
		if(i<0||i>2){
			System.out.println("out of bound try again");
			return false;
		}
		else if(j<0||j>2){
			System.out.println("out of bound try again");
			return false;
		}
		else if(num<1||num>9){
			System.out.println("try again invalid input");
			return false;
		}
		else if(player.visited[i][j]){
			System.out.println("try again cell is already marked");
			return false;
		}
		else if(i==j){
			HashMap<Integer,Integer> d=player.diag1;
			Integer val=d.get(num);
			if(val!=null){
				System.out.println("element "+val);
			if(val>0){
				System.out.println("element exists try again");
				return false;
			}
			}
			return true;
		}
		else if((i+j)==2){
			HashMap<Integer,Integer> d=player.diag2;
			Integer val=d.get(num);
			if(val!=null){
				System.out.println("element "+val);
			if(val>0){
				System.out.println("element exists try again");
				return false;
			}
		}
			return true;
		}
		else{
			return true;
		}
	}
	public void move(Integer i,Integer j,int in,int jn,Integer num){
		System.out.println("cell count is: "+count);
		Integer d=rowsum.get(i);
		count--;
		if(d==null){
			d=new Integer(0);
		}
		d+=num;
		rowsum.put(i,d);
		System.out.println("the sum at row :"+i+": "+rowsum.get(i));
		HashMap<Integer,Integer> c=row.get(i);
		if(c==null){
			c=new HashMap<Integer,Integer>();
			c.put(num,1);
			row.put(i,c);
		}
		else{
			Integer k=c.get(num);
			if (k==null) {
				k=new Integer(0);
			}
			k+=1;
			c.put(num,k);
			row.put(i,c);
		}
		d=colsum.get(j);
		if(d==null){
			d=new Integer(0);
		}
		d+=num;
		colsum.put(j,d);
		System.out.println("the sum at col :"+j+": "+colsum.get(j));
		HashMap<Integer,Integer> g=col.get(j);
		if(g==null){
			g=new HashMap<Integer,Integer>();
			g.put(num,1);
			col.put(j,g);
		}
		else{
			Integer k=g.get(num);
			if (k==null) {
				k=new Integer(0);
			}
			k+=1;
			g.put(num,k);
			col.put(j,g);
		}
		if(in==jn){
			diag1.put(num,new Integer(1));
			diag1sum+=num;
			System.out.println("sum at diagonal 1 is :"+diag1sum);
		}
		if((in+jn)==2){
			diag2.put(num,new Integer(1));
			diag2sum+=num;
			System.out.println("sum at diagonal 2 is :"+diag2sum);
		}
		mat[i][j]+=num;
		visited[i][j]=true;
		display(mat);
		if(rowsum.get(i)==15||colsum.get(j)==15||diag1sum==15||diag2sum==15){
			System.out.println(this.name+" wins");
			System.exit(1);
		}
	}
	public static void display(int[][] mat){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				System.out.print("["+mat[i][j]+"]");
			}
			System.out.println();
		}
		return;
	}
	public static void main(String[] args){
		HashMap<Integer,Integer> rowsum=new HashMap<Integer,Integer>();
		HashMap<Integer,HashMap<Integer,Integer>> row=new HashMap<Integer,HashMap<Integer,Integer>>();
		HashMap<Integer,Integer> colsum=new HashMap<Integer,Integer>();
		HashMap<Integer,HashMap<Integer,Integer>> col=new HashMap<Integer,HashMap<Integer,Integer>>();
		HashMap<Integer,Integer> diag1=new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> diag2=new HashMap<Integer,Integer>();
		int[][] mat=new int[3][3];
		boolean[][] visited=new boolean[3][3];
		Tic player1=new Tic("user1",mat,visited,rowsum,colsum,row,col,diag1,diag2,0,0);
		Tic player2=new Tic("user2",mat,visited,rowsum,colsum,row,col,diag1,diag2,0,0);
		display(mat);
		Tic player=player1;
		Scanner sc=new Scanner(System.in);
		while(count>0){
			System.out.println("enter row:");
			int i=sc.nextInt();
			Integer in=new Integer(i);
			System.out.println("enter col:");
			int j=sc.nextInt();
			Integer jn=new Integer(j);
			System.out.println("enter number:");
			int num=sc.nextInt();
			Integer n=new Integer(num);
			if(!valid(i,j,n,player)){
				continue;
			}
			player.move(in,jn,i,j,n);
			if(player==player1){
				player=player2;
			}
			else{
				player=player1;
			}
		}
		System.out.println("match drawn");
	}
}
