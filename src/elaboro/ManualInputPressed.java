package elaboro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

class ManualInputPressed implements ActionListener
{
	private Menu menu;
	private StringBuilder string = new StringBuilder();
	ManualInputPressed(Menu menu)
	{
		this.menu = menu;
	}
	public void actionPerformed(ActionEvent ae)
	{
		Object button = ae.getSource();
		
		if(button == menu.b_enter)
		{
			String temp_string = menu.tf_data.getText();
			if("".equals(temp_string))
			{
				JOptionPane.showMessageDialog(
						null, 
						"Вы ничего не ввели!\nВведите целое положительное число!",
						"Предупреждение!",
						JOptionPane.WARNING_MESSAGE
				);
				return;
			}
			if(!isNumber(temp_string))
			{
				JOptionPane.showMessageDialog(
						null, 
						"Введены не верные данные!\nЗначение может быть - одно, целое, положительное число без пробелов и прочих символов!\nВведите число заново.",
						"Ошибка ввода данных!",
						JOptionPane.ERROR_MESSAGE
				);
				return;
			}
			try
			{
				Integer.parseInt(temp_string);
			}
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(
						null, 
						"Слишком большое число! Запрещено превышать число 2147483647.",
						"Ошибка ввода данных!",
						JOptionPane.ERROR_MESSAGE
				);
				return;
			}
			string.append(temp_string).append(' ');
			menu.tf_data		.setText("");
			menu.b_end_enter	.setEnabled(true);
			menu.rb_file		.setEnabled(false);
			menu.rb_manual		.setEnabled(false);
		}
		if(button == menu.b_end_enter)
		{
			if(string.length() == 0) return;
			
			menu.tf_data.setEnabled(false);
			menu.tf_data.setText("");
			menu.b_enter.setEnabled(false);
			
			String data_array[] = string.toString().split(" ");
			
			menu.data = new int[data_array.length];
			for(int i = 0; i < data_array.length; i++)
				menu.data[i] = Integer.parseInt(data_array[i]);
			
			menu.b_end_enter	.setEnabled(false);
			menu.b_draw			.setEnabled(true);
			
			menu.status_drawing = true;
		}
	}
	private boolean isNumber(String string)
	{
		for(int i = 0; i < string.length(); i++)
		{
			int symbol = string.charAt(i);
			if(!(symbol >= 48 && symbol <= 57))
			{
				return false;
			}
		}
		return true;
	}
}
