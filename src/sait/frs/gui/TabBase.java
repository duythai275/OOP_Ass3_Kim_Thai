package sait.frs.gui;

import java.awt.*;

import javax.swing.*;

/**
 * Abstract class for a tab in the JFrame.
 * 
 * @author Thai Nguyen, Kim Seulgi
 * @version March 05, 2020
 */
public abstract class TabBase 
{
	/**
	 * Tab panel attribute.
	 */
	protected JPanel panel;
	
	/**
	 * Default constructor.
	 */
	protected TabBase() {
		this.panel = new JPanel();
	}
	
	/**
	 * Gets the panel containing the tab components.
	 * @return JPanel with components.
	 */
	public JPanel getPanel() {
		return this.panel;
	}

	/**
	 * Clear value of fields in tabs
	 * Used for tabs 
	 * and main window when switching tabs
	 */
	protected abstract void emptyFields();

	/**
	 * Clear value in list in tabs
	 * Used for tabs 
	 * and main window when switching tabs
	 */
	protected abstract void clearList();

	/**
	 * Clear value of filters in tabs
	 * Used for tabs 
	 * and main window when switching tabs
	 */
	protected abstract void clearFilters();
}
