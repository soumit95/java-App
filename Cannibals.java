import java.util.*;
import java.io.*;
import java.lang.*;
class Cannibals{
	public void enqueue(Vector<Integer> vec,Stack<Vector<Integer>> st,Stack<Integer> wgt,int w){
		if(st.empty()){
        st.push(vec);
        wgt.push(w);
        return;
    }
    else{
        if(w<=(Integer)wgt.peek()){
            st.push(vec);
            wgt.push(w);
            return;
        }
        else{
            Vector<Integer> t=st.peek();
            int w1=(Integer)wgt.peek();
            st.pop();
            wgt.pop();
            enqueue(vec,st,wgt,w);
            st.push(t);
            wgt.push(w1);
            return;
        }
    }
	}
	public int getWeight(int rm,int rc){
		return Math.abs(6-(rm+rc));
	}
	public void display(int lm,int lc,int rm,int rc,int bm,int bc,int dirc){
		System.out.println("east side Missionaries:: "+lm);
		System.out.println("east side Cannibals:: "+lc);
		System.out.println();
		System.out.println();
		System.out.println("west side Missionaries:: "+rm);
		System.out.println("west side Cannibals:: "+rc);
		System.out.println();
		System.out.println();
		System.out.println("boat side Missionaries:: "+bm);
		System.out.println("boat side Cannibals:: "+bc);
		System.out.println();
		System.out.println();
		if(dirc==1)
		System.out.println("and the direction is left");
	else{
		System.out.println("and the direction is right");
	}
	System.out.println();
		System.out.println();
	}
	public void f(Stack<Vector<Integer>> st,Stack<Integer> wgt,int lm,int lc,int rm,int rc,int bm,int bc,boolean[][] east,boolean[][] west,int dirc,int w){
		Vector<Integer> vec=new Vector<Integer>(7);
		vec.insertElementAt(lm,0);
		vec.insertElementAt(lc,1);
		vec.insertElementAt(rm,2);
		vec.insertElementAt(rc,3);
		vec.insertElementAt(bm,4);
		vec.insertElementAt(bc,5);
		vec.insertElementAt(dirc,6);
		enqueue(vec,st,wgt,w);
	}
	public void search(int lm,int lc,int rm,int rc,int bm,int bc,boolean[][] east,boolean[][] west,int dirc,int w){
		
		Stack<Vector<Integer>> st=new Stack<Vector<Integer>>();
		Stack<Integer> wgt=new Stack<Integer>();
		Vector<Integer> vec=new Vector<Integer>(7);
		vec.insertElementAt(lm,0);
		vec.insertElementAt(lc,1);
		vec.insertElementAt(rm,2);
		vec.insertElementAt(rc,3);
		vec.insertElementAt(bm,4);
		vec.insertElementAt(bc,5);
		vec.insertElementAt(dirc,6);
		enqueue(vec,st,wgt,w);
		while(!st.empty()){
			vec=new Vector<Integer>(st.peek());
			lm=vec.elementAt(0);
			lc=vec.elementAt(1);
			rm=vec.elementAt(2);
			rc=vec.elementAt(3);
			bm=vec.elementAt(4);
			bc=vec.elementAt(5);
			dirc=vec.elementAt(6);
			w=wgt.peek();
			st.pop();
			wgt.pop();
		display(lm,lc,rm,rc,bm,bc,dirc);
		if((lc>lm)||(rc>rm)){
			continue;
		}
		System.out.println("starting new Iteration");
		if(dirc==1){
			if(bc>0){
				lc+=bc;
				bc=0;
			}
			if(bm>0){
				lm+=bm;
				bm=0;
			}
			if(east[lm][lc]){
				continue;
			}
			east[lm][lc]=true;
			if(lm>=1){
				f(st,wgt,lm-1,lc,rm,rc,bm+1,bc,east,west,0,w+getWeight(rm,rc));
			}
			if(lc>=1){
				f(st,wgt,lm,lc-1,rm,rc,bm,bc+1,east,west,0,w+getWeight(rm,rc));
			}
			if(lm>=2){
				f(st,wgt,lm-2,lc,rm,rc,bm+2,bc,east,west,0,w+getWeight(rm,rc));
			}
			if(lc>=2){
				f(st,wgt,lm,lc-2,rm,rc,bm,bc+2,east,west,0,w+getWeight(rm,rc));
			}
			if(lm>=1&&lc>=1){
				f(st,wgt,lm-1,lc-1,rm,rc,bm+1,bc+1,east,west,0,w+getWeight(rm,rc));
			}
		}
		else{
				if(bc>0){
				rc+=bc;
				bc=0;
			}
			if(bm>0){
				rm+=bm;
				bm=0;
			}
			if((rm==3)&&(rc==3)){
				display(lm,lc,rm,rc,bm,bc,dirc);
				System.out.println("goal node found"+" with weight "+w);
				System.exit(1);
			}
			if(west[rm][rc]){
				continue;
			}
			west[rm][rc]=true;
			if(rm>=2){
				f(st,wgt,lm,lc,rm-2,rc,bm+2,bc,east,west,1,w+getWeight(rm-2,rc));
			}
			if(rc>=2){
				f(st,wgt,lm,lc,rm,rc-2,bm,bc+2,east,west,1,w+getWeight(rm,rc-2));
			}
			if (rm>=1) {
				f(st,wgt,lm,lc,rm-1,rc,bm+1,bc,east,west,1,w+getWeight(rm-1,rc));
			}
			if (rc>=1) {
				f(st,wgt,lm,lc,rm,rc-1,bm,bc+1,east,west,1,w+getWeight(rm,rc-1));
			}
			if ((rm>=1)&&(rc>=1)) {
				f(st,wgt,lm,lc,rm-1,rc-1,bm+1,bc+1,east,west,1,w+getWeight(rm-1,rc-1));
			}
		}
	}
}
	public void fun(){
		int lm=3;
		int rm=0;
		int lc=3;
		int rc=0;
		int bm=0;
		int bc=0;
		boolean[][] east=new boolean[4][4];
		boolean[][] west=new boolean[4][4];
		search(lm,lc,rm,rc,bm,bc,east,west,1,0);
	}
	public static void main(String[] args) {
		Cannibals obj=new Cannibals();
		obj.fun();
	}
}