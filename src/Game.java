import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game {
	
		private String turn = new String();
		JButton[] button;
		JButton first,second;
		JFrame frame;
		JLabel label;
		JPanel inner,grid,outer;
		GameBoard board;
		DrawPanel[] buttonpanel;
		Computer computer;
		int firstplayer;
		
		public void setturn(String arg) {
			turn = arg;
		}
		
		public String getturn() {
			return turn;
		}
		
		public void setIntroWindow() {
			
			first = new JButton("Play first");
			first.addActionListener(new IntroButtonListener());
			second = new JButton("Play second");
			second.addActionListener(new IntroButtonListener());
			
			outer = new JPanel();
			outer.setLayout(null);
			first.setBounds(225, 200, 150, 30);
			second.setBounds(225, 231, 150, 30);
			outer.add(first);outer.add(second);
			
			grid = new JPanel();
			button = new JButton[9];
			buttonpanel = new DrawPanel[9];
			for(int i=0;i<9;i++){
				buttonpanel[i] = new DrawPanel();
				buttonpanel[i].setBackground(Color.WHITE);
				button[i] = new JButton();
				button[i].setBackground(Color.WHITE);
				button[i].add(buttonpanel[i]);
				button[i].addActionListener(new MainButtonListener());
			}
			for(int i=0;i<9;i++)grid.add(button[i]);
			grid.setLayout(new GridLayout(3,3));
			inner = new JPanel();
			label = new JLabel();
			inner.setLayout(new BorderLayout());
			inner.add(new BorderLayout().SOUTH,label);
			inner.add(grid);
			frame = new JFrame();
			frame.setLayout(new CardLayout(0,0));
			frame.setResizable(false);
			frame.setSize(600,500);
			frame.setVisible(true);
			
			frame.getContentPane().add("a",outer);
			frame.getContentPane().add("b",inner);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		public void setMainWindow() {
			
			board = new GameBoard();
			board.setup();
			computer = new Computer();
		}
		
		public void startPlaying() {
			
			while(!board.isFull() ) {
				if(getturn().equals("Computer")) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Pair ans = computer.getPosition(board.getboard(), 0);
					//System.out.printf("%d %d",ans.getx(),ans.gety());
					board.set(ans.getx(), ans.gety(), getturn());
					buttonpanel[ans.getx()*3+ans.gety()].setdraw(1);
					button[ans.getx()*3+ans.gety()].repaint();
					setturn("Human");
					label.setText("");
				}
				if(board.check(board.getboard())==1) {
					label.setText("Computer has won!");
					break;
				}
				if(board.isFull()) {
					label.setText("Draw!!!");
				}
			}
		}
		
		class IntroButtonListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				frame.getContentPane().remove(0);
				JButton pressedbutton = (JButton) e.getSource();
				if(pressedbutton.equals(first))setturn("Human");
				else {setturn("Computer");firstplayer=1;}
				first.setVisible(false);
				second.setVisible(false);
			}
			
		}
		
		class MainButtonListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(getturn().equals("Computer"))return;
				if(board.check(board.getboard())==1) {
					label.setText("Computer has won!!!");
					return;
				}
				if(board.isFull()) {
					label.setText("Draw!!!");
					return;
				}
				int i,id=0;
				JButton pressedbutton = (JButton)e.getSource();
				for(i=0;i<9;i++)
				{
					if(pressedbutton.equals(button[i]))
					{
						id = i;
						break;
					}
				}
				if(!board.placed(id/3,id%3)) {
					board.set(id/3, id%3, getturn());
					setturn("Computer");
					buttonpanel[id].setdraw(2);
					button[id].repaint();
					label.setText("");
				}
				else if(!board.isFull()){
					label.setText("This place is already occupied. Try again!");
				}
				else {
					label.setText("Draw!!!");
				}
			}	
		}
}
