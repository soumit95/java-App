import java.io.*;
import java.util.*;
class Puzzle{
	public static void swap(int[][] mat,int i,int j,int pi,int pj){
		int temp=mat[i][j];
		mat[i][j]=mat[pi][pj];
		mat[pi][pj]=temp;
		return;
	}
	public static boolean compare(int[][] mat,int[][] tar){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(mat[i][j]!=tar[i][j]){
					return false;
				}
			}
		}
		return true;
	}
	public static void get(int i,int j,int pi,int pj,int count,int[][] mat,int[][] tar,boolean[][] visited){
		if(i<0||i>2){
			return;
		}
		else if(j<0||j>2){
			return;
		}
		else if(visited[i][j]){
			swap(mat,i,j,pi,pj);
			if(compare(mat,tar)){
				System.out.println("goal leaf found at level: "+count);
				display(mat);
				System.exit(1);
				}
			swap(mat,i,j,pi,pj);
			return;
		}
		else{
				visited[i][j]=true;
				swap(mat,i,j,pi,pj);
				display(mat);
				if(compare(mat,tar)){
				System.out.println("goal leaf found");
				System.exit(1);
				}
				get(i+1,j,i,j,count+1,mat,tar,visited);
				get(i,j+1,i,j,count+1,mat,tar,visited);
				get(i,j-1,i,j,count+1,mat,tar,visited);
				get(i-1,j,i,j,count+1,mat,tar,visited);
				swap(mat,i,j,pi,pj);
				display(mat);
				visited[i][j]=false;
		}
	}
	public static void display(int[][] mat){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				System.out.print("["+mat[i][j]+"]");
			}
			System.out.println();
		}
		System.out.println();
		return;
	}
	public static void main(String[] args){
		int[][] mat=new int[3][3];
		int[][] tar=new int[3][3];
		boolean[][] visited=new boolean[3][3];
		mat[0][0]=2;
		mat[0][1]=8;
		mat[0][2]=3;
		mat[1][0]=1;
		mat[1][2]=4;
		mat[1][1]=6;
		mat[2][0]=7;
		mat[2][2]=5;
		tar[0][0]=1;
		tar[0][1]=2;
		tar[0][2]=3;
		tar[1][0]=8;
		tar[1][2]=4;
		tar[1][1]=0;
		tar[2][0]=7;
		tar[2][1]=6;
		tar[2][2]=5;
		display(mat);
		display(tar);
		get(2,1,2,1,0,mat,tar,visited);
	}
}
