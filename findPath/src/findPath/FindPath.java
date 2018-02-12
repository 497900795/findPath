package findPath;

public class FindPath {
	static int startPoint,startFloor,startBuliding;
	static int endPoint,endFloor,endBuliding;
	
	//l->楼号减,r->楼号加,n->不能移动楼号
	private static char transBulidingWay[][][] = {
		//每一维分别为楼、楼层、所在点
		{
			{'l','n','r',},		
			{'l','n','r',},
		},
		
		{
			{'l','n','r',},
		},
	};
	//结束后在新楼的位置
	private static int transBulidingEnd[][][] = {
		//每一维分别为楼、楼层、所在点
		{
			{0,1,2,},			
			{0,1,2,},
		},
		
		{
			{0,1,2,},
		},
	};
	//c->能移动楼层,n->不能移动楼层
	private static char transFloorWay[][][] = {
		//每一维分别为楼、楼层、所在点
		{
			{'c','n','n',},		
			{'c','n','n',},	
		},
		
		{
			{'c','n','n',},	
		},
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
	
	public char sureBulidingWay(){
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
	
	public char sureFloorWay(){
		if(startFloor == endFloor){
			return 'n';
		}
		else{
			return 'c';
		}
	}
	
	public void resetStartPoint(int transPoint,int transFloor,int transBuliding){
		startPoint = transBulidingEnd[transBuliding][transFloor][transPoint];
	}
	
}
