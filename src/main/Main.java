package main;

import java.awt.EventQueue;

import controllers.MainController;
import view.MenuView;

public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {
					MainController.load();

					MenuView frame = new MenuView();
					frame.setVisible(true);
					
					MainController.save();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
