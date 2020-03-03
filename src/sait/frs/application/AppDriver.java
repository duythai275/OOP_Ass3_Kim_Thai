package sait.frs.application;

import java.io.EOFException;

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
	 * @throws EOFException 
	 */
	public static void main(String[] args) throws EOFException {
		MainWindow mainWindow = new MainWindow();
		mainWindow.display();
	}

}
