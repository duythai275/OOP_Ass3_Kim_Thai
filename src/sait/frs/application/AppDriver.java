package sait.frs.application;

//import java.io.EOFException;
//import java.io.IOException;

import sait.frs.gui.*;

/**
 * Application driver.
 * 
 */
public class AppDriver {

	/**
	 * Entry point to Java application.
	 * @param args
	 */
	public static void main(String[] args) {
		MainWindow mainWindow = new MainWindow();
		mainWindow.display();
	}

}
