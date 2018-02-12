package findPath;
import java.util.Scanner;
public class Dijkstra {
	//常量
    private static int N = Integer.MAX_VALUE/2;
    private static int[][] Graph = {
        { 0, 1, 5, N, N, N, N, N, N },
        { 1, 0, 3, 7, 5, N, N, N, N },
        { 5, 3, 0, N, 1, 7, N, N, N },
        { N, 7, N, 0, 2, N, 3, N, N },
        { N, 5, 1, 2, 0, 3, 6, 9, N },
        { N, N, 7, N, 3, 0, N, 5, N },
        { N, N, N, 3, 6, N, 0, 2, 7 },
        { N, N, N, N, 9, 5, 2, 0, 4 },
        { N, N, N, N, N, N, 7, 4, 0 },
    };
    private static int NUM = Graph[0].length;
    
    //变量
    private static int start;
    private static int end;
    private static boolean flag[] = new boolean [NUM];
    private static int dist[]  = new int [NUM];
    private static int  path[][] = new int [NUM][NUM];
    //函数
    Dijkstra(){
    	System.out.print("请输入开始结点:");
    	Scanner input=new Scanner(System.in);	
    	start = input.nextInt();
    	System.out.print("请输入目标结点:");
    	end = input.nextInt();
    	input.close();
    	//path初始化
    	for(int i = 0;i < NUM;i++){
    		flag[i] = false;
    		dist[i] = Graph[start][i];
    		for(int j = 0;j < NUM;j++){
    			path[i][j] = -1;
    		}
    		if(dist[i] != 0 && dist[i] != N){
    			path[i][0] = start;
    		}
    	}
    	dist[start] = 0;
    	flag[start] = true;
    }
        
    public void dijkstra(){
    	int min,v=0;
    	int k;
    	for(int i = 1; i < NUM;i++){
    		min = Integer.MAX_VALUE;
    		for(int j = 0;j < NUM;j++){
    			if(!flag[j]){
    				if(dist[j] < min){
    					v = j;
    					min = dist[j];
    				}
    			}
    		}
    		flag[v] = true;
    		
    		for(int j = 0;j < NUM;j++){
    			if(!flag[j]&& (min + Graph[v][j]) < dist[j]){
    				dist[j] = min + Graph[v][j];
    				
    				//更新path
    				for(int m = 0;m < NUM;m++){
    					path[j][m] = path[v][m];
    				}
    				for(k = 0;k < NUM && path[j][k] != -1;){
    					k++;		
    				}
    				path[j][k] = j;
    				
   			}
    		}//innerFor
    	}//outFor
    }
    
    public void showResult(){
    	for(int i = 0;path[end][i] != -1;i++){
    			System.out.print(i + "->");
    	}
    	System.out.print(end + "\n");
    	System.out.println("距离:" + dist[end]);
    }
}
