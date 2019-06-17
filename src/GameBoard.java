
public class GameBoard {
		private String board[];
		
		public void setup() {
			board = new String[3];
			for(int i=0;i<3;i++)
			{
				board[i]=new String("...");
			}
		}
		
		public void set(int x,int y,String player) {
			String[] temp = new String[3];
			for(int i=0;i<3;i++)temp[i] = new String(board[i]);
			char[] arr = temp[x].toCharArray();
			if(player.equals("Computer"))arr[y] = 'O';
			else arr[y] = 'X';
			temp[x] = String.valueOf(arr);
			for(int i=0;i<3;i++)board[i]=temp[i];
		}
		
		public boolean isFull() {
			for(int  i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
					if(board[i].charAt(j)=='.')return false;
			}
			return true;
		}
		
		public boolean placed(int x,int y) {
			if(board[x].charAt(y)=='.')return false;
			return true;
		}
		
		public String[] getboard() {
			
			return board;
		}
		
		public int check(String board[]) {
			
			int i,j;
			for(i=0;i<3;i++)if(board[i].equals("OOO"))return 1;
			for(i=0;i<3;i++)if(board[i].equals("XXX"))return -1;
			for(j=0;j<3;j++)
			{
				int o=0;int x=0;
				for(i=0;i<3;i++)
				{
					if(board[i].charAt(j)=='O')o++;
					else if(board[i].charAt(j)=='X')x++;
				}
				if(o==3)return 1;
				if(x==3)return -1;
			}
			if(board[0].charAt(0)==board[1].charAt(1) && board[2].charAt(2)==board[0].charAt(0) && board[0].charAt(0)=='O')
				return 1;
			if(board[0].charAt(0)==board[1].charAt(1) && board[2].charAt(2)==board[0].charAt(0) && board[0].charAt(0)=='X')
				return -1;
			if(board[0].charAt(2)==board[1].charAt(1) && board[2].charAt(0)==board[0].charAt(2) && board[0].charAt(2)=='O')
				return 1;
			if(board[0].charAt(2)==board[1].charAt(1) && board[2].charAt(0)==board[0].charAt(2) && board[0].charAt(2)=='X')
				return -1;
			return 0;
		}
		
}
