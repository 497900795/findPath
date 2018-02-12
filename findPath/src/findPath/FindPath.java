package findPath;

public class FindPath {
	static int startPoint,startBuliding;
	static int endPoint,endBuliding;
	
	//l->楼号减,r->楼号加,n->不能移动楼号
	private static char transWay[][] = {
		{'l','n','r'},
		{'l','n','r'},};
	//结束后在新楼的位置
	private static int transEnd[][] = {
		{1,0,5},
		{2,3,4},
	};

	public static void main(String[] args) {
		Dijkstra dij = new Dijkstra();
		dij.dijkstra();
		dij.showResult();
	}
	
	FindPath(int startP,int startB,int endP,int endB){
		startPoint = startP;
		startBuliding = startB;
		endPoint = endP;
		endBuliding = endB;
	}
	
	public char sureWay(){
		if(startBuliding > endBuliding){
			return 'l';
		}
		else if(startBuliding < endBuliding){
			return 'r';
		}
		else{
			return  'n';
		}
	}
	
	public void resetStartPoint(int transPoint,int transBuliding){
		startPoint = transEnd[transBuliding][transPoint];
	}
	
}
