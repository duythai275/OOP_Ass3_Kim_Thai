package sait.frs.application;

import sait.frs.gui.*;

/**
 * This program demonstrates the Flight's Reservation System
 * 
 * @author Thai Nguyen, Kim Seulgi
 * @version March 05, 2020
 */
public class AppDriver {

	/**
	 * Entry point to Java application.
	 * @param args an array of strings which stores arguments passed by command line while starting a program
	 */
	public static void main(String[] args) {
		MainWindow mainWindow = new MainWindow();
		mainWindow.display();
	}

}
