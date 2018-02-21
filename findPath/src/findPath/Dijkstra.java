package findPath;
import java.util.Scanner;
import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
public class Dijkstra {
	//常量
    private static int N = Integer.MAX_VALUE/2;
    private static int[][] Graph = {
        { 0, 1, 1,},
        { 1, 0, 1,},
        { 1, 1, 0,},
    };
    private static int NUM = Graph[0].length;
    
    //变量
    private static int start;
    private static int end;
    private static boolean [] flag = new boolean [NUM];
    private static int [] dist = new int [NUM];
    private static int [][]  path = new int [NUM][NUM];
    //函数
    Dijkstra(){
    	Scanner input=new Scanner(System.in);
    	System.out.print("请输入开始结点:");		
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
    
    Dijkstra(int inStart,int inEnd){
    	start = inStart;
    	end = inEnd;
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
     
    Dijkstra(int inStart){
    	start = inStart;
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
    
    public static int getNUM(){
    	return NUM;
    }
    
    public static int getDist(int endPoint){
    	return dist[endPoint];
    }
    
    public static int [] getPath(int point){
    	return path[point];
    }
    
    public static void setEnd(int inEnd){
    	end = inEnd;
    }
    
    public void resetGraph(int buliding,int floor) throws FileNotFoundException{
    	//重置Graph等成员，防止数组越界和浪费内存
    	Graph = null;
    	dist = null;
    	path = null;
    	flag = null;
    	String filePath = "./maps/" + buliding + "/" + floor + ".txt";//位置：maps/楼号/楼层.txt
    	File file = new File(filePath);
    	Scanner scanner = new Scanner(file);
    	
    	//根据新读入的NUM，重新分配空间
    	NUM = scanner.nextInt();
    	flag = new boolean [NUM];
        dist = new int [NUM];
        path = new int [NUM][NUM];
    	Graph = new int [NUM][NUM];
    	for(int i = 0;i < NUM;i++){
    		for(int j = 0;j < NUM;j++){
    			Graph[i][j] = scanner.nextInt();
    		}
    	}
    	scanner.close();  	
    }
}

