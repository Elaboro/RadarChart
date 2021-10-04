package elaboro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DrawPressed implements ActionListener
{
	private Menu menu;
	private Drawing drawing;
	DrawPressed(Menu menu, Drawing drawing)
	{
		this.menu = menu;
		this.drawing = drawing;
	}
	public void actionPerformed(ActionEvent ae)
	{
		menu.b_draw.setEnabled(false);
		drawing.graphic = drawing.graphic.remember(menu);
		drawing.graphic.Plot(drawing.getGraphics());
	}
}
