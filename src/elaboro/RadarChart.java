package elaboro;

import java.awt.Toolkit;
import javax.swing.JFrame;

class RadarChart extends JFrame
{
	private static final int
		FRAME_WIDTH		= 500,
		FRAME_HEIGHT	= 662
	;
	final Menu		menu_panel		= new Menu(
			5,	FRAME_HEIGHT/4*3,	FRAME_WIDTH-25, FRAME_HEIGHT/4-42
	);
	final Drawing	drawing_panel	= new Drawing(
			5,	5,					FRAME_WIDTH-25, FRAME_HEIGHT/4*3-10, menu_panel
	);
	
	public RadarChart()
	{
		super("Лепестковая диаграмма по параметру");
		setResizable(false);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width/2 - FRAME_WIDTH/2, 
				Toolkit.getDefaultToolkit().getScreenSize().height/2 - FRAME_HEIGHT/2
		);
		setLayout(null);
		add(	drawing_panel	);
		add(	menu_panel		);
		
		InputMethodPressed id_button = new InputMethodPressed(menu_panel);
		menu_panel.rb_manual		.addActionListener(id_button);
		menu_panel.rb_file			.addActionListener(id_button);
		
		ManualInputPressed id_button1 = new ManualInputPressed(menu_panel);
		menu_panel.b_enter			.addActionListener(id_button1);
		menu_panel.b_end_enter		.addActionListener(id_button1);
		
		menu_panel.b_download		.addActionListener(new DownloadPressed(menu_panel));
		menu_panel.b_draw.addActionListener(new DrawPressed(menu_panel, drawing_panel));
	}
}
