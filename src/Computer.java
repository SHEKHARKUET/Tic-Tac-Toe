import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Computer {
	
	HashMap<Long,Integer> map = new HashMap<Long,Integer>();
	HashMap<Long,ArrayList<Pair> > nextState = new HashMap<Long,ArrayList<Pair> >();
	GameBoard gameBoard = new GameBoard();
	
	int getmapsize() {
		return map.size();
	}
	
	public Long HashIt(String board[],int turn) {
		
		String toBeMapped = new String("1");
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(board[i].charAt(j) == '.')toBeMapped += "0";
				else if(board[i].charAt(j) == 'O')toBeMapped += "1";
				else toBeMapped += "2";
			}
		}
		toBeMapped += String.valueOf(turn);
		return Long.parseLong(toBeMapped);
	}
	
	public void findNextStates(String board[],int value,int turn) {
		
		ArrayList<Pair> list = new ArrayList<Pair>();
		
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				String temp[] = new String[3];
				for(int it=0;it<3;it++)temp[it]=new String(board[it]);
				if(turn==0 && board[i].charAt(j)=='.')
				{
					char[] arr = temp[i].toCharArray();
					arr[j] = 'O';
					temp[i] = String.valueOf(arr);
					if(value == map.get(HashIt(temp,turn^1))) {
						Pair pair = new Pair();
						pair.setx(i);pair.sety(j);
						list.add(pair);
					}
				}
				
				else if(turn==1 && board[i].charAt(j)=='.')
				{
					char[] arr = temp[i].toCharArray();
					arr[j] = 'X';
					temp[i] = String.valueOf(arr);
					if(value == map.get(HashIt(temp,turn^1))) {
						Pair pair = new Pair();
						pair.setx(i);pair.sety(j);
						list.add(pair);
					}	
				}
				
			}
		}
		nextState.put(HashIt(board,turn), list);
	}
	
	public int dfs(String board[],int turn) {
		
		int x=0,i,j;boolean leaf=true;
		Long toBeMapped = new Long(HashIt(board,turn));
		if(map.containsKey(toBeMapped))
			return map.get(toBeMapped);
		if(gameBoard.check(board)!=0) {
			map.put(toBeMapped, gameBoard.check(board));
			return gameBoard.check(board);
		}
		for(i=0;i<3;i++)
		{
			for(j=0;j<3;j++)
			{
				String temp[] = new String[3];
				for(int it=0;it<3;it++)temp[it]=new String(board[it]);
				if(turn==0 && board[i].charAt(j)=='.')
				{
					char[] arr = temp[i].toCharArray();
					arr[j] = 'O';
					temp[i] = String.valueOf(arr);
					x = Math.max(x,dfs(temp,1));
					leaf = false;
				}
				
				else if(turn==1 && board[i].charAt(j)=='.')
				{
					char[] arr = temp[i].toCharArray();
					arr[j] = 'X';
					temp[i] = String.valueOf(arr);
					x = Math.min(x,dfs(temp,0));	
					leaf = false;
				}
				
			}
		}
		if(leaf==true)x=gameBoard.check(board);
		map.put(toBeMapped,x);
		findNextStates(board,x,turn);
		return x;
	}
	
	public Pair getPosition(String board[],int turn) {
		
		ArrayList<Pair> arr = new ArrayList<Pair>(nextState.get(HashIt(board,turn)));
		int pos = (new Random()).nextInt(arr.size());
		return arr.get(pos);
	}
	
	public Computer() {
		String board[] = {"...","...","..."};
		dfs(board,0);
		dfs(board,1);
	}
	
	
}
