package elaboro;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

class Drawing extends JPanel
{
	private int width, height;
	public Plotter graphic;
	class Plotter
	{
		private static final String AUTHOR = "Author: Elaboro | github.com/Elaboro | 2016 year";
		private static final int MINIMUM_DATA_LENGTH = 1;
		private static final int RECT_ARG = 5;
		private boolean status_drawing = false;
		private int data[];
		Plotter(Menu menu)
		{
			if(!menu.status_drawing) return;
			status_drawing = menu.status_drawing;
			data = new int[menu.data.length];
			for(int i = 0; i < data.length; i++) data[i] = menu.data[i];
		}
		public Plotter remember(Menu menu)
		{
			return new Plotter(menu);
		}
		public void Plot(Graphics g)
		{
			Graphics2D gr = (Graphics2D)g;
			gr.setPaint(Color.red);
			gr.drawString(AUTHOR, 2, height-5);
			if(!status_drawing) return;
			int data_length = data.length;
			double
				x[] = new double[data_length],
				y[] = new double[data_length],
				center_x = width/2,
				center_y = height/2,
				r = center_x-50
			;
			int max;
			if(center_y < center_x) r = center_y-10;;
			max = data[0];
			if(data_length > MINIMUM_DATA_LENGTH)
				for(int i = 0; i < data_length-1; i++)
					if(data[i+1] > max) max = data[i+1];
			for(int i = 0; i < data_length; i++)
			{
				x[i] = center_x + (Math.cos(2*Math.PI / data_length * i)) * r * data[i]/max;
				y[i] = center_y + (Math.sin(2*Math.PI / data_length * i)) * r * data[i]/max;				
			}
			GeneralPath rect = new GeneralPath();
	        rect.moveTo(-RECT_ARG, -RECT_ARG);
	        rect.lineTo(RECT_ARG, -RECT_ARG);
	        rect.lineTo(RECT_ARG, RECT_ARG);
	        rect.lineTo(-RECT_ARG, RECT_ARG);
	        rect.closePath();
			gr.setStroke(new BasicStroke(2.0f));
			gr.setPaint(Color.BLACK);
			gr.draw(new Line2D.Double(x[0], y[0], x[data_length-1], y[data_length-1]));
			for(int i = 0; i < data_length-1; i++)
			{
				gr.draw(new Line2D.Double(x[i],y[i],x[i+1],y[i+1]));
			}
			for(int i = 0, j = data_length; i < data_length; i++, j--)
			{
				gr.setColor(new Color(
						(int)(Math.random()*256),
						(int)(Math.random()*256),
						(int)(Math.random()*256)
				));
				gr.draw(new Line2D.Double(
						center_x,
						center_y,
						x[i],
						y[i]
				));
				gr.translate(x[i], y[i]);
				gr.rotate(2*Math.PI/data_length*i);
				gr.fill(rect);
				gr.rotate(2*Math.PI/data_length*j);
				gr.translate(x[i] - 2*x[i], y[i] - 2*y[i]);
				gr.setColor(Color.DARK_GRAY);
				gr.drawString("" + data[i],
						(int)(x[i] + ((Math.cos(2*Math.PI / data_length * i)) * r * 0.07) - 7),
						(int)(y[i] + ((Math.sin(2*Math.PI / data_length * i)) * r * 0.07) + 5)
				);
			}
			gr.setPaint(Color.BLACK);
			gr.fill(new Ellipse2D.Double(center_x-5, center_y-5, 10, 10));
		}
	}
	Drawing(int x, int y, int width, int height, Menu menu)
	{
		setBounds(x, y, width, height);
		setBackground(Color.WHITE);
		this.width = width;
		this.height = height;
		graphic = new Plotter(menu);
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		graphic.Plot(g);
	}
}
