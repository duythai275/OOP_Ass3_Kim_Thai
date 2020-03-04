package sait.frs.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import java.util.*;

import sait.frs.exception.*;
import sait.frs.manager.Manager;
import sait.frs.problemdomain.Reservation;

/**
 * Holds the components for the reservations tab.
 * 
 */
public class ReservationsTab extends TabBase {
	/**
	 * Instance of travel manager.
	 */
	private Manager manager;
	
	private JList<Reservation> reversationsList;
	private DefaultListModel<Reservation> reservationsModel;
	private JButton findReservations;
	private JButton update;
	private JTextField tCode;
	private JTextField tAirline;
	private JTextField tName;
	private JTextField tCode1;
	private JTextField tFlight;
	private JTextField tAirline1;
	private JTextField tCost;
	private JTextField tName1;
	private JTextField tCitizenship;
	private JComboBox tStatus;
	
	/**
	 * Creates the components for reservations tab.
	 */
	public ReservationsTab(Manager manager) {
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
		
		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		
		return panel;
	}
	
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout(10,10));
		
		reservationsModel = new DefaultListModel<>();
		reversationsList = new JList<>(reservationsModel);
		
		reversationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(this.reversationsList);
		
		reversationsList.addListSelectionListener(new MyListSelectionListener());
		
		panel.add( scrollPane, BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel createEastPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout(10,10));
		
		JLabel title = new JLabel("Reserve", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 25));
		panel.add(title, BorderLayout.NORTH);
		
		JPanel GridLayout = new JPanel();
		GridLayout.setLayout(new GridLayout(7,2));
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new FlowLayout());
		centerPanel.add(GridLayout);
		
		
		JLabel lCode = new JLabel("Code: ", SwingConstants.RIGHT);
		tCode1 = new JTextField(10);
		tCode1.setEditable(false);
		GridLayout.add(lCode);
		GridLayout.add(tCode1);
		
		JLabel lFlight = new JLabel("Flight: ", SwingConstants.RIGHT);
		tFlight = new JTextField(10);
		tFlight.setEditable(false);
		GridLayout.add(lFlight);
		GridLayout.add(tFlight);
		
		JLabel lAirline = new JLabel("Airline: ",SwingConstants.RIGHT);
		tAirline1 = new JTextField(10);
		tAirline1.setEditable(false);
		GridLayout.add(lAirline);
		GridLayout.add(tAirline1);
		
		JLabel lCost = new JLabel("Cost: ",SwingConstants.RIGHT);
		tCost = new JTextField(10);
		tCost.setEditable(false);
		GridLayout.add(lCost);
		GridLayout.add(tCost);
		
		JLabel lName = new JLabel("Name: ",SwingConstants.RIGHT);
		tName1 = new JTextField(10);
		GridLayout.add(lName);
		GridLayout.add(tName1);
		
		JLabel lCitizenship = new JLabel("Citizenship: ",SwingConstants.RIGHT);
		tCitizenship = new JTextField(10);
		GridLayout.add(lCitizenship);
		GridLayout.add(tCitizenship);
		
		JLabel lStatus = new JLabel("Status: ",SwingConstants.RIGHT);
		String[] status = {"Active", "Inactive"};
		tStatus = new JComboBox(status);
		GridLayout.add(lStatus);
		GridLayout.add(tStatus);
		
		panel.add(centerPanel, BorderLayout.CENTER);
		
		update = new JButton("Update");
		update.addActionListener(new MyActionListener());
		panel.add(update, BorderLayout.SOUTH);
		
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
		
		JLabel title = new JLabel("Search", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 25));
		panel.add(title, BorderLayout.NORTH);
		
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new GridLayout(3,1));
		JLabel lCode = new JLabel("Code: ", SwingConstants.RIGHT);
		westPanel.add(lCode);
		JLabel lAirline = new JLabel("Airline: ", SwingConstants.RIGHT);
		westPanel.add(lAirline);
		JLabel lName = new JLabel("Name: ", SwingConstants.RIGHT);
		westPanel.add(lName);
//		
		panel.add(westPanel, BorderLayout.WEST);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(3,1));
		tCode = new JTextField();
		centerPanel.add(tCode);
		tAirline = new JTextField();
		centerPanel.add(tAirline);
		tName = new JTextField();
		centerPanel.add(tName);
		
		
		panel.add(centerPanel, BorderLayout.CENTER);
		
//		System.out.println(manager.getAirports());
		
		findReservations = new JButton("Find Reservations");
		findReservations.addActionListener(new MyActionListener());
		panel.add(findReservations, BorderLayout.SOUTH);
		
		return panel;
	}
	
	public void emptyFields () {
		tCode1.setText("");
		tFlight.setText("");
		tAirline1.setText("");
		tCost.setText("");
		tName1.setText("");
		tCitizenship.setText("");
		tStatus.setSelectedIndex(0);
		
//		reservationsModel.clear();
	}
	
	public void clearList() {
		reservationsModel.clear();
	}
	
	public void clearFilters() {
		tCode.setText("");
		tAirline.setText("");
		tName.setText("");
	}
	
	private class MyListSelectionListener implements ListSelectionListener 
	{
		@Override
		public void valueChanged(ListSelectionEvent event) {
			try {
				Reservation r = reversationsList.getSelectedValue();
				tCode1.setText(r.getCode());
				tFlight.setText(r.getFlight().getCode());
				tAirline1.setText(r.getFlight().getAirline());
				tCost.setText(r.getFlight().getCostPerSeat()+"");
				tName1.setText(r.getName());
				tCitizenship.setText(r.getCitizenship());
				tStatus.setSelectedIndex( (r.isActive()) ? 0 : 1 );
			} catch (Exception e) {}
		}
	}
	
	private class MyActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event) {
			if ( event.getSource() == findReservations ) {
				reservationsModel.clear();
				emptyFields();
				ArrayList<Reservation> reservations = manager.findReservations(tCode.getText(), tAirline.getText(), tName.getText());
				if ( reservations.size() == 0 ) {
					JOptionPane.showMessageDialog(null, "No Reservations found");
				} else {
					for ( Reservation r : reservations )
					{
						reservationsModel.addElement(r);
					};
				}
			}
			
			if ( event.getSource() == update ) {
				Reservation r = manager.findReservationByCode(tCode1.getText());
				try {
					r.setName(tName1.getText());
					r.setCitizenship(tCitizenship.getText());
					r.setActive( (tStatus.getSelectedIndex() == 0 ) ? true : false );
					JOptionPane.showMessageDialog(null, r.getCode() + "has been updated");
					emptyFields();
				} catch (InvalidNameException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e.getMessage());
				} catch (InvalidCitizenshipException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e.getMessage());
				}
				manager.persist();
			}
		}
	}
}
