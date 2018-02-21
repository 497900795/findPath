package findPath;
import java.util.Scanner;
import java.io.File;  
import java.io.FileNotFoundException;

public class Dijkstra {
	//常量
    private static int[][] Graph = {
    };
    private static int NUM;
    //变量
    private static int start;
    private static int end;
    private static boolean [] flag;
    private static int [] dist;
    private static int [][]  path;
    //函数
    
    Dijkstra(int startPoint,int startFloor,int startBuliding) throws FileNotFoundException{
    	start = startPoint;
    	//path初始化
    	setInfo(startBuliding,startFloor);
    	for(int i = 0;i < NUM;i++){
    		flag[i] = false;
    		dist[i] = Graph[start][i];
    		for(int j = 0;j < NUM;j++){
    			path[i][j] = -1;
    		}
    		if(dist[i] != 0 && dist[i] != 1000){
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
    
    public void setInfo(int buliding,int floor) throws FileNotFoundException{
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

