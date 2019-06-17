import javax.swing.*;
import java.awt.*;

class DrawPanel extends JPanel{

	private int draw;
	
	public void setdraw(int arg) {
		draw=arg;
	}
	
	public int getdraw() {
		return draw;
	}
	
	public void paintComponent(Graphics g) {
		
		if(draw==1) {
			StretchIcon image = new StretchIcon(this.getClass().getResource("/circle.png"));
			image.paintIcon(this, g, -1, -1);
		}
		else if(draw==2) {
			StretchIcon image = new StretchIcon(this.getClass().getResource("/cross.png"));
			image.paintIcon(this, g, -1, -1);
		}

	}
}
