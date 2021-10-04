package elaboro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class InputMethodPressed implements ActionListener
{
	private Menu menu;
	InputMethodPressed(Menu menu)
	{
		this.menu = menu;
		this.menu.tf_name_file	.setEnabled(false);
		this.menu.b_download	.setEnabled(false);
		this.menu.b_draw		.setEnabled(false);
		this.menu.b_end_enter	.setEnabled(false);
	}
	public void actionPerformed(ActionEvent ae)
	{
		Object id_clic = ae.getSource();
		if(id_clic == menu.rb_manual)
		{
			menu.rb_file		.setSelected(false);
			
			menu.tf_name_file	.setText("");
			menu.tf_name_file	.setEnabled(false);
			menu.b_download		.setEnabled(false);
			
			menu.tf_data		.setEnabled(true);
			menu.b_enter		.setEnabled(true);
			return;
		}
		if(id_clic == menu.rb_file)
		{
			menu.rb_manual		.setSelected(false);
			
			menu.tf_data		.setText("");
			menu.tf_data		.setEnabled(false);
			menu.b_enter		.setEnabled(false);
			menu.b_end_enter	.setEnabled(false);
			
			menu.tf_name_file	.setEnabled(true);
			menu.b_download		.setEnabled(true);
			return;
		}
	}
}
