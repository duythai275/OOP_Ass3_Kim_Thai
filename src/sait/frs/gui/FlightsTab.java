package sait.frs.gui;

import java.awt.*;
import java.awt.event.*; // Needed for ActionListener Interface

import javax.swing.*;
import javax.swing.event.*;

import java.util.*;

import sait.frs.exception.*;
import sait.frs.manager.Manager;
import sait.frs.problemdomain.Flight;

/**
 * Holds the components for the flights tab.
 * 
 */
public class FlightsTab extends TabBase 
{
	/**
	 * Instance of travel manager.
	 */
	private Manager manager;
	
	/**
	 * List of flights.
	 */
	private JList<Flight> flightsList;
	private DefaultListModel<Flight> flightsModel;
	private JButton findFlights;
	private JButton reserve;
	private JComboBox tFrom;
	private JComboBox tTo;
	private JComboBox tDate;
	private JTextField tFlight;
	private JTextField tAirline;
	private JTextField tDay;
	private JTextField tTime;
	private JTextField tCost;
	private JTextField tName;
	private JTextField tCitizenship;
	
	
	/**
	 * Creates the components for flights tab.
	 */
	public FlightsTab(Manager manager) 
	{
		this.manager = manager;
		panel.setLayout(new BorderLayout(10,10));
		
		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);
		
		JPanel eastPanel = createEastPanel();
		panel.add(eastPanel, BorderLayout.EAST);
		
		JPanel westPanel = createWestPanel();
		panel.add(westPanel, BorderLayout.WEST);
		
		JPanel southPanel = createSouthPanel();
		panel.add(southPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Creates the north panel.
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() 
	{
		JPanel panel = new JPanel();
		
		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		
		return panel;
	}
	
	/**
	 * Creates the center panel.
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() 
	{
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout(10,10));
		
		flightsModel = new DefaultListModel<>();
		flightsList = new JList<>(flightsModel);
		
		// User can only select one item at a time.
		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.flightsList);
		
		flightsList.addListSelectionListener(new MyListSelectionListener());
		
		panel.add(scrollPane, BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel createEastPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout(10,10));
		
		JLabel title = new JLabel("Reserve", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 25));
		panel.add(title, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(7,2));
		
		JLabel lFlight = new JLabel("Flight: ", SwingConstants.RIGHT);
		tFlight = new JTextField(10);
		tFlight.setEditable(false);
		centerPanel.add(lFlight);
		centerPanel.add(tFlight);
		
		JLabel lAirline = new JLabel("Airline: ",SwingConstants.RIGHT);
		tAirline = new JTextField(10);
		tAirline.setEditable(false);
		centerPanel.add(lAirline);
		centerPanel.add(tAirline);
		
		JLabel lDay = new JLabel("Day: ",SwingConstants.RIGHT);
		tDay = new JTextField(10);
		tDay.setEditable(false);
		centerPanel.add(lDay);
		centerPanel.add(tDay);
		
		JLabel lTime = new JLabel("Time: ",SwingConstants.RIGHT);
		tTime = new JTextField(10);
		tTime.setEditable(false);
		centerPanel.add(lTime);
		centerPanel.add(tTime);
		
		JLabel lCost = new JLabel("Cost: ",SwingConstants.RIGHT);
		tCost = new JTextField(10);
		tCost.setEditable(false);
		centerPanel.add(lCost);
		centerPanel.add(tCost);
		
		JLabel lName = new JLabel("Name: ",SwingConstants.RIGHT);
		tName = new JTextField(10);
		centerPanel.add(lName);
		centerPanel.add(tName);
		
		JLabel lCitizenship = new JLabel("Citizenship: ",SwingConstants.RIGHT);
		tCitizenship = new JTextField(10);
		centerPanel.add(lCitizenship);
		centerPanel.add(tCitizenship);
		
		panel.add(centerPanel, BorderLayout.CENTER);
		
		reserve = new JButton("Reserve");
		panel.add(reserve, BorderLayout.SOUTH);
		reserve.addActionListener(new MyActionListener());
		
		return panel;
	}
	
	private JPanel createWestPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		
		return panel;
	}
	
	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Flight Finder", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 25));
		panel.add(title, BorderLayout.NORTH);
		
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new GridLayout(3,1));
		JLabel lFrom = new JLabel("From: ", SwingConstants.RIGHT);
		westPanel.add(lFrom);
		JLabel lTo = new JLabel("To: ", SwingConstants.RIGHT);
		westPanel.add(lTo);
		JLabel lDay = new JLabel("Day: ", SwingConstants.RIGHT);
		westPanel.add(lDay);

		panel.add(westPanel, BorderLayout.WEST);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(3,1));
		tFrom = new JComboBox(this.manager.getAirports().toArray(new String[manager.getAirports().size()]));
		centerPanel.add(tFrom);
		tTo = new JComboBox(manager.getAirports().toArray(new String[manager.getAirports().size()]));
		centerPanel.add(tTo);
		String[] days = {this.manager.WEEKDAY_ANY, this.manager.WEEKDAY_MONDAY, this.manager.WEEKDAY_TUESDAY, this.manager.WEEKDAY_WEDNESDAY, this.manager.WEEKDAY_THURSDAY, this.manager.WEEKDAY_FRIDAY, this.manager.WEEKDAY_SATURDAY, this.manager.WEEKDAY_SUNDAY};
		tDate = new JComboBox(days);
		centerPanel.add(tDate);
		
		
		panel.add(centerPanel, BorderLayout.CENTER);
		
//		System.out.println(manager.getAirports());
		
		findFlights = new JButton("Find Flights");
		findFlights.addActionListener(new MyActionListener());
		panel.add(findFlights, BorderLayout.SOUTH);
		
		return panel;
	}
	
	private class MyListSelectionListener implements ListSelectionListener 
	{
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent event) {
			try {
				Flight f = flightsList.getSelectedValue();
				tFlight.setText(f.getCode());
				tAirline.setText(f.getAirline());
				tDay.setText(f.getWeekday());
				tTime.setText(f.getTime());
				tCost.setText(f.getCostPerSeat()+"");
			} catch ( Exception e ) {
				
			}
			
		}
		
	}
	
	private class MyActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event) {
			
			if ( event.getSource() == findFlights ) {
				flightsModel.clear();
				ArrayList<Flight> flights = manager.findFlights((String) tFrom.getSelectedItem(),(String) tTo.getSelectedItem(),(String) tDate.getSelectedItem());
				for ( Flight f : flights )
				{
					flightsModel.addElement(f);
				};
			}
			
			if ( event.getSource() == reserve ) {
				try {
					JOptionPane.showMessageDialog(null, "Reversation created. Your code is " +
					manager.makeReservation(manager.findFlightByCode(tFlight.getText()),tName.getText(),tCitizenship.getText()).getCode());
				} catch (NullFlightException | NoMoreSeatsException | InvalidNameException
						| InvalidCitizenshipException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
			
		}
	}
}