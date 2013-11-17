public class Triangulo extends BaseProgram {

	public static void main(String[] args) {
		int a,b,c,t,sum;
		a = readInt();
		b = readInt();    
		c = readInt();    
		sum=a+b;
    	t=0;
    	if(sum>c){
    		t=0;
    	}
    	else if((a==b)&&(b==c)){
    		t=3;
    	}
    	else if((a==b)&&(b!=c)){
    		t=2;
    	} 
    	else if ((a!=b)&& (b!=c)){
    		t=1;
    	}
    	else if ((a!=b)&& (b==c)){
    		t=2;
    	}
    	writeInt("t: ", t);
	}
}
