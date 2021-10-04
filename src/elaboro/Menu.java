package elaboro;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class Menu extends JPanel
{
	public boolean status_drawing = false;
	public int data[];
	JLabel
		l_input_method	= new JLabel		("Способ ввода:",	JLabel.CENTER),
		l_manual_input	= new JLabel		("Ручной ввод:",	JLabel.CENTER),
		l_file_input	= new JLabel		("Файловый ввод:",	JLabel.CENTER),
		l_data			= new JLabel		("Данные:",			JLabel.CENTER),
		l_name_file		= new JLabel		("Имя файла:",		JLabel.CENTER)
	;
	JButton
		b_enter			= new JButton		("Ввод"				),
		b_end_enter		= new JButton		("Завершение"		),
		b_download		= new JButton		("Загрузить"		),
		b_draw			= new JButton		("Рисование"		)
	;
	JRadioButton
		rb_manual		= new JRadioButton	("ручной",	true	),
		rb_file			= new JRadioButton	("файловый"			)
	;
	JTextField
		tf_data			= new JTextField(),
		tf_name_file	= new JTextField()
	;
	Menu(int x, int y, int width, int height)
	{
		setLayout(null);
		setBounds(x, y, width, height);
		setBackground(Color.LIGHT_GRAY);
		rb_manual		.setBackground(Color.LIGHT_GRAY);
		rb_file			.setBackground(Color.LIGHT_GRAY);
		
		width	= width/4;
		height	= height/5;
		l_input_method	.setBounds(0,			0,			width,		height);
		l_manual_input	.setBounds(0,			height*2,	width,		height);
		l_file_input	.setBounds(0,			height*4,	width,		height);
		l_data			.setBounds(width,		height,		width,		height);
		l_name_file		.setBounds(width,		height*3,	width,		height);
		b_enter			.setBounds(width*2+2,	height*2,	width-2,	height);
		b_end_enter		.setBounds(width*3+2,	height*2,	width-3,	height);
		b_download		.setBounds(width*2+2,	height*4-2,	width-2,	height);
		b_draw			.setBounds(width*3+2,	height*4-2,	width-3,	height);
		rb_manual		.setBounds(width,		0,			width,		height);
		rb_file			.setBounds(width*2,		0,			width,		height);
		tf_data			.setBounds(width,		height*2,	width,		height);
		tf_name_file	.setBounds(width,		height*4-2,	width,		height);
		
		add(l_input_method);	add(rb_manual);		add(rb_file);
								add(l_data);
		add(l_manual_input);	add(tf_data);		add(b_enter);		add(b_end_enter);
								add(l_name_file);
		add(l_file_input);		add(tf_name_file);	add(b_download);	add(b_draw);
	}
}
