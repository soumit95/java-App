import java.io.*;
import java.util.*;
import java.lang.*;
class Waterjug{
	public static int c1=4;
	public static int c2=3;
	public static void fill1(Vector<Integer> child,Vector<Integer> vec){
		child.insertElementAt(c1,0);
		child.insertElementAt(vec.elementAt(1),1);
	}
	 public static void fill2(Vector<Integer> child,Vector<Integer> vec){
                child.insertElementAt(c2,1);
        }
	public static void wipe1(Vector<Integer> child,Vector<Integer> vec){
		child.insertElementAt(0,0);
		child.insertElementAt(vec.elementAt(1),1);
	}
	public static void wipe2(Vector<Integer> child,Vector<Integer> vec){
                child.insertElementAt(0,1);
        }
	public static void pfill1(Vector<Integer> child,Vector<Integer> vec){
		int d=vec.elementAt(0);
		int k=vec.elementAt(1);
		int i=Math.abs(c1-d);
		if(i>0){
			while((d!=c1)&&(k!=0)){
			d++;
			k--;
			}
			child.insertElementAt(d,0);
			child.insertElementAt(k,1);
		}
	}
	public static void pfill2(Vector<Integer> child,Vector<Integer> vec){
               int d=vec.elementAt(1);
		int k=vec.elementAt(0);
		int i=Math.abs(c2-d);
		if(i>0){
			while((d!=3)&&(k!=0)){
			d++;
			k--;
			System.out.println("water:"+d+" ,"+k);
			}
			child.insertElementAt(d,1);
			child.insertElementAt(k,0);
		}
    }

	public void sinsert(Vector<Integer> vec,Stack<Vector<Integer>> st,Stack<Integer> wgt,int w,String path,Stack<String> route){
    if(st.empty()){
        st.push(vec);
        wgt.push(w);
        route.push(path);
        return;
    }
    else{
        if(w<=(Integer)wgt.peek()){
            st.push(vec);
            wgt.push(w);
            route.push(path);
            return;
        }
        else{
            Vector<Integer> t=st.peek();
            int w1=(Integer)wgt.peek();
            String path1=route.peek();
            st.pop();
            wgt.pop();
            route.pop();
            sinsert(vec,st,wgt,w,path,route);
            st.push(t);
            wgt.push(w1);
            route.push(path1);
            return;
        }
    }
	}
	public boolean cal(Vector<Integer> vec){
		int i=vec.elementAt(0);
		if(i==2){
			return true;
		}
		return false;
	}
	public boolean bound(int i,int j){
		if(i<0||i>4){
			return false;
		}
		if(j<0||j>3){
			return false;
		}
		return true;
	}
	int  getWeight(int i1,int j1,int i2,int j2,int[][] cost){
			int d=cost[i1][j1];
			int k=cost[i2][j2];
			return Math.abs(d-k);
	}
	public void fun(int[][] cmat,Vector<Integer> jug,int[][] cost){
		Stack<Vector<Integer>> st=new Stack<Vector<Integer>>();
		Stack<Integer> wgt=new Stack<Integer>();
		Stack<String> route=new Stack<String>();
		st.push(jug);
		wgt.push(0);
		route.push("");
		while(!st.empty()){
		Vector<Integer> vec=new Vector<Integer>(st.peek());
		int w=wgt.peek();
		st.pop();
		wgt.pop();
		String path=route.peek();
		route.pop();
		int i=vec.elementAt(0);
		int j=vec.elementAt(1);
		int i1=i;
		int j1=j;
		path=path+" -> "+i+","+j;
		System.out.println("+---------------------------------+");
		System.out.println("new Iteration");
		System.out.println("jug1="+i+" jug2="+j);
		System.out.println("with weight "+w);
		cmat[i][j]=1;
		if(cal(vec)){
			System.out.println("+---------------------------------+");
			System.out.println("path found");
			System.out.println(path);
			System.exit(1);
		}
		Vector<Integer> child3=new Vector<Integer>(2);
		child3.insertElementAt(vec.elementAt(0),0);
		child3.insertElementAt(vec.elementAt(1),1);
        fill1(child3,vec);
		i=child3.elementAt(0);
		j=(int)child3.elementAt(1);
		System.out.println("jug1="+i+" jug2="+j);
		if(bound(i,j)&&cmat[i][j]!=1){
			sinsert(child3,st,wgt,w+Math.abs(i-2)+getWeight(i,j,i1,j1,cost),path,route);
		}
		Vector<Integer> child4=new Vector<Integer>(vec);
                fill2(child4,vec);
				i=child4.elementAt(0);
                j=child4.elementAt(1);
                System.out.println("jug1="+i+" jug2="+j);
                if(bound(i,j)&&cmat[i][j]!=1){
                        sinsert(child4,st,wgt,w+Math.abs(i-2)+getWeight(i,j,i1,j1,cost),path,route);
                }

		Vector<Integer> child5=new Vector<Integer>(vec);
                wipe1(child5,vec);
				i=child5.elementAt(0);
                j=child5.elementAt(1);
                System.out.println("jug1="+i+" jug2="+j);
                if(bound(i,j)&&cmat[i][j]!=1){
                        sinsert(child5,st,wgt,w+Math.abs(i-2)+getWeight(i,j,i1,j1,cost),path,route);
                }

		Vector<Integer> child6=new Vector<Integer>(vec);
                wipe2(child6,vec);
		i=child6.elementAt(0);
                j=child6.elementAt(1);
                System.out.println("jug1="+i+" jug2="+j);
                if(bound(i,j)&&cmat[i][j]!=1){
                        sinsert(child6,st,wgt,w+Math.abs(i-2)+getWeight(i,j,i1,j1,cost),path,route);
         	       }
	
		Vector<Integer> child7=new Vector<Integer>(vec);
                pfill1(child7,vec);
				i=child7.elementAt(0);
                j=child7.elementAt(1);
                System.out.println("jug1="+i+" jug2="+j);
                if(bound(i,j)&&cmat[i][j]!=1){
                        sinsert(child7,st,wgt,w+Math.abs(i-2)+getWeight(i,j,i1,j1,cost),path,route);
                }
		
		Vector<Integer> child8=new Vector<Integer>(vec);
                pfill2(child8,vec);
				i=child8.elementAt(0);
                j=child8.elementAt(1);
                System.out.println("jug1="+i+" jug2="+j);
                if(bound(i,j)&&cmat[i][j]!=1){
                        sinsert(child8,st,wgt,w+Math.abs(i-2)+getWeight(i,j,i1,j1,cost),path,route);
	                }
		}
	}
	public static void print(int mat[][])
                   {
 			 for(int i=0;i<5;i++)
                        {
                            for(int j=0;j<4;j++){
				    System.out.print("["+mat[i][j]+"]");
                    }
                   System.out.println("");
			}
			System.out.println();
    }
		public static void main(String args[])
		{
		int [][] mat =new int[5][4];
		int[][] cost=new int[5][4];
		Vector<Integer> vec=new Vector<Integer>(2); 
		vec.insertElementAt(0,0);
		vec.insertElementAt(0,1);
		Scanner ob=null;
		try{
		File file=new File("cost.txt");
			ob=new Scanner(file);
		}
		catch(Exception e){

		}
				System.out.println("Enter the cost value of matrix");
			for(int i=0;i<5;i++)						
                               
			{                   
			           for(int j=0;j<4;j++){
 						int val=ob.nextInt();
                        cost[i][j]=val;
			           }
                        
            }
            print(cost);
		Waterjug w=new Waterjug();
		w.fun(mat,vec,cost);
		}
}
