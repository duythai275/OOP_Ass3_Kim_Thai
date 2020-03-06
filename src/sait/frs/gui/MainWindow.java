package sait.frs.gui;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;

import javax.swing.*;

import sait.frs.manager.Manager;



/**
 * The main window (JFrame) contains 
 * Flight tab and Reservation tab buttons
 * 
 * 
 * @author Thai Nguyen, Kim Seulgi
 * @version March 05, 2020
 */
public class MainWindow extends JFrame {
	private static final String TAB_FLIGHTS = "flights";
	private static final String TAB_RESERVATIONS = "reservations";
	
	/**
	 * Holds the flight and reservation manager.
	 */
    private Manager manager;
	
    /**
	 * Card layout to display tab content.
	 */
	private CardLayout cardLayout;
	
	/**
	 * North panel.
	 */
	private JPanel northPanel;
	
	/**
	 * Center panel.
	 */
	private JPanel centerPanel;
	
	/**
	 * Flights tab button.
	 */
	private JButton flightsButton;
	
	/**
	 * Reservations tab button.
	 */
	private JButton reservationsButton;
	
	/**
	 * Flights tab panel.
	 */
	private TabBase flightsTab;
	
	/**
	 * Reservations tab panel.
	 */
	private TabBase reservationsTab;
	
	/**
	 * Creates the Main Window and any components inside it.
	 */
	public MainWindow() {
		this.manager = new Manager();
		
		//Title of Window
		setTitle("Flight Reservation Management System");
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// For tabs
		northPanel = createNorthPanel();
		add(northPanel, BorderLayout.NORTH);
		
		// For contain
		centerPanel = createCenterPanel();
		add(centerPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Creates the north panel.
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() 
	{
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
			
		JPanel tabPanel = createTabPanel();
		panel.add(tabPanel, BorderLayout.SOUTH);
		
		return panel;
	}
	
	/**
	 * Creates the center panel.
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() 
	{
		JPanel panel = new JPanel();
		
		cardLayout = new CardLayout();
		
		flightsTab = new FlightsTab(manager);
		reservationsTab = new ReservationsTab(manager);
		
		panel.setLayout(cardLayout);
		
		panel.add(flightsTab.getPanel(), TAB_FLIGHTS);
		panel.add(reservationsTab.getPanel(), TAB_RESERVATIONS);
		
		cardLayout.first(panel);
		
		return panel;
	}
	
	/**
	 * Creates the tab buttons.
	 * @return JPanel containing tab buttons.
	 */
	private JPanel createTabPanel() {
		JPanel tabPanel = new JPanel();
		
		tabPanel.setLayout(new GridLayout(1, 2));
		
		flightsButton = new JButton("Flights");
		reservationsButton = new JButton("Reservations");
		
		flightsButton.addActionListener(new TabButtonActionListener());
		reservationsButton.addActionListener(new TabButtonActionListener());
		
		tabPanel.add(flightsButton);
		tabPanel.add(reservationsButton);
		
		return tabPanel;
	}
	
	/**
	 * Displays the JFrame window.
	 */
	public void display() 
	{
		pack();
		setVisible(true);
	}
	
	/**
	 * Inner action listener class that listens for a tab button to be clicked.
	 * 
	 * @author Nick Hamnett, Mohamed
	 * @version January 2, 2020
	 */
private class TabButtonActionListener implements ActionListener 
	{

		/**
		 * Action method for buttons in the Window
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// Action for clicking Flight tab button 
			if (e.getSource() == flightsButton) 
			{
				// Display Flight tab content
				cardLayout.show(centerPanel, TAB_FLIGHTS);
				// Empty fields, list and filters when switching between tabs
				flightsTab.emptyFields();
				flightsTab.clearList();
				flightsTab.clearFilters();
			} 
			// Action for clicking Reservation tab button 
			else if (e.getSource() == reservationsButton) 
			{
				// Display Reservation tab content
				cardLayout.show(centerPanel, TAB_RESERVATIONS);
				// Empty fields, list and filters when switching between tabs
				reservationsTab.emptyFields();
				reservationsTab.clearList();
				reservationsTab.clearFilters();
			}
		}
		
	}
}
