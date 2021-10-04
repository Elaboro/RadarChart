package elaboro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

class DownloadPressed implements ActionListener
{
	private static final int MINIMUM_FILE_NAME_LENGTH = 5;
	private Menu menu;
	DownloadPressed(Menu menu){this.menu = menu;}
	public void actionPerformed(ActionEvent ae)
	{
		String file_name = menu.tf_name_file.getText();
		if("".equals(file_name))
		{
			JOptionPane.showMessageDialog(
					null, 
					"Вы ничего не ввели!\nВведите имя файла.",
					"Предупреждение!",
					JOptionPane.WARNING_MESSAGE
			);
			return;
		}
		if(file_name.length() < MINIMUM_FILE_NAME_LENGTH)
		{
			JOptionPane.showMessageDialog(
					null, 
					"Длинна имени файла слишком короткая!\nИмя файла должно состоять минимум из 1 символа и расширения *.txt.",
					"Предупреждение!",
					JOptionPane.WARNING_MESSAGE
			);
			return;
		}
		file_name = file_name.toLowerCase();
		if(!isVerifyFileExtension(file_name))
		{
			JOptionPane.showMessageDialog(
					null, 
					"Не верное расширение файла!\nРасширение должно быть: *.txt.",
					"Ошибка!",
					JOptionPane.ERROR_MESSAGE
			);
			return;
		}
		if(!isVerifyNameFile(file_name))
		{
			JOptionPane.showMessageDialog(
					null, 
					"Имя файла содержит запрещённые символы!\nОно может состоять только из букв латинского алфавита, цифр и символа нижнего подчёркивания.",
					"Ошибка!",
					JOptionPane.ERROR_MESSAGE
			);
			return;
		}
		if(LoadFile(file_name))
		{
			menu.rb_file		.setEnabled(false);
			menu.rb_manual		.setEnabled(false);
			menu.tf_name_file	.setEnabled(false);
			menu.b_download		.setEnabled(false);
			
			menu.status_drawing = true;
			menu.b_draw			.setEnabled(true);
			return;
		}
	}
	private boolean  isVerifyFileExtension(String file_name)
	{
		String extension = "";
		for(int i = file_name.length()-4; i < file_name.length(); i++)
		{
			extension += file_name.charAt(i);
		}
		if(".txt".equals(extension)) return true;
		return false;
	}
	private boolean isVerifyNameFile(String file_name)
	{
		char c;
		for(int i = 0; i < file_name.length()-4; i++)
		{
			c = file_name.charAt(i);
			if((c < 48 || c > 57) && (c < 97 || c > 122) && (c != '_'))
				return false;
		}
		return true;
	}
	private boolean LoadFile(String file_name)
	{
		FileReader fr = null;
		BufferedReader bf = null;
		StringBuilder string = new StringBuilder();
		try
		{
			Properties p = System.getProperties();
			fr = new FileReader(p.getProperty("user.dir") + "\\" + file_name);
			bf = new BufferedReader(fr);
			while(true)
			{
				String l = bf.readLine();
				if(l == null) break;
				string.append(l + " ");
			}
			if("".equals(string.toString().trim()))
			{
				JOptionPane.showMessageDialog(
						null, 
						"Файл пустой!",
						"Ошибка загрузки файла!",
						JOptionPane.ERROR_MESSAGE
				);
				return false;
			}
			String data_array[] = string.toString().trim().split(" ");
			menu.data = new int[data_array.length];
			for(int i = 0; i < data_array.length; i++)
			{
				try
				{
					Integer.parseInt(data_array[i]);
				}
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(
							null, 
							"Файл содержит слишком большое число! Запрещено превышать число 2147483647.",
							"Ошибка загрузки файла!",
							JOptionPane.ERROR_MESSAGE
					);
					return false;
				}
				try
				{
					menu.data[i] = Integer.parseInt(data_array[i]);
				}
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(
							null, 
							"Файл содержит запрещённые символы или неверные данные!\nВсе значения должны быть целыми положительными числами,\nзаписанными через один пробел!",
							"Ошибка загрузки файла!",
							JOptionPane.ERROR_MESSAGE
					);
					return false;
				}
				if(menu.data[i] < 0)
				{
					JOptionPane.showMessageDialog(
							null, 
							"Файл содержит отрицательные значения!\nВсе значения должны быть целыми положительными числами!",
							"Ошибка загрузки файла!",
							JOptionPane.ERROR_MESSAGE
					);
					return false;
				}
			}
			
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(
					null, 
					"Файл не найден!\nСоздайте файл в папке проекта с расширением *.txt.",
					"Ошибка!",
					JOptionPane.ERROR_MESSAGE
			);
		}
		finally
		{
			try
			{
				bf.close();
				fr.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			catch(NullPointerException e)
			{
				return false;
			}
		}
		return true;
	}
}
