package sait.frs.application;

import java.io.EOFException;

//import java.io.EOFException;
//import java.io.IOException;

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
	 * @param args
	 * @throws EOFException 
	 */
	public static void main(String[] args) throws EOFException {
		// Create MainWindow object and involke its display() method to start program
		MainWindow mainWindow = new MainWindow();
		mainWindow.display();
	}

}
