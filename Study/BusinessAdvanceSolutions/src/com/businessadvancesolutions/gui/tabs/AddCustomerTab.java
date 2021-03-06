package com.businessadvancesolutions.gui.tabs;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: May 9, 2013
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.businessadvancesolutions.businessmodel.BusinessCustomer;
import com.businessadvancesolutions.dbapi.dao.BusinessCustomerDAO;
import com.businessadvancesolutions.gui.startup.JMasterFrame;
import com.toedter.calendar.JDateChooser;

public class AddCustomerTab extends JFrame implements FocusListener,
		MouseListener {
	private static Logger _log = null;
	private JMasterFrame _parent = null;

	private JPanel _mainTab = null;

	public AddCustomerTab(JMasterFrame parentFrame) {
		super("AddCustomerTab");
		_parent = parentFrame;
		if (_log == null) {
			_log = Logger.getLogger(AddCustomerTab.class);
		}
	}

	/**
	 * Gets the content pane
	 * 
	 * @returns JPanel the content pane
	 */
	public JPanel getTab() {
		if (_mainTab == null)
			_mainTab = (JPanel) this.getContentPane();
		return _mainTab;
	}

	JTextField customerID = new JTextField(20);
	JTextField customerFirstName = new JTextField(20);
	JTextField customerLastName = new JTextField(20);
	JDateChooser birthDate = null;
	JTextField addressFirstLine = new JTextField(20);
	JTextField addressSecondLine = new JTextField(20);
	JTextField landMark = new JTextField(20);
	JTextField city = new JTextField(20);
	JTextField state = new JTextField(20);
	JTextField zip = new JTextField(20);
	JTextField contactNumber = new JTextField(20);
	JTextField occupation = new JTextField(20);
	JTextField customerIndex = new JTextField(20);
	JTextField customerBarCode = new JTextField(20);

	JButton resetForm = null;
	JButton toggleFunction = null;
	JButton submitForm = null;

	// JButton datePicker = null;

	public void buildForm() {
		try {
			_mainTab = (JPanel) this.getContentPane();

			JPanel infoPanel = new JPanel();
			infoPanel.setBounds(10, 10, 600, 200);
			JLabel infoLabel = new JLabel();
			infoLabel
					.setText("Add the user details for Tution Minutes and Earnings in this tab.");
			infoPanel.add(infoLabel);

			_mainTab.add(infoPanel, BorderLayout.NORTH);

			JPanel centerPanel = new JPanel();
			// centerPanel.setBounds(0, 0, 700, 800);
			centerPanel.setBorder(BorderFactory.createLoweredBevelBorder());
			centerPanel.setLayout(null);

			int labelWidth = 150;
			int labelHight = 25;

			/*
			 * First Column
			 */
			int firstColumnStart = 50;

			int firstTextFieldColumnStart = labelWidth + firstColumnStart + 50;

			JLabel customerIDLabel = new JLabel("CustomerIDLabel");
			assignJLabelDetails(customerIDLabel, "Customer ID: ",
					firstColumnStart, 50, labelWidth, labelHight);

			JLabel customerFirstNameLabel = new JLabel("CustomerFirstNameLabel");
			assignJLabelDetails(customerFirstNameLabel, "First Name: ",
					firstColumnStart, 100, labelWidth, labelHight);

			JLabel customerLastNameLabel = new JLabel("CustomerLastNameLabel");
			assignJLabelDetails(customerLastNameLabel, "Last Name: ",
					firstColumnStart, 150, labelWidth, labelHight);

			JLabel customerBirthDateLabel = new JLabel("customerBirthDateLabel");
			assignJLabelDetails(customerBirthDateLabel, "BirthDate: ",
					firstColumnStart, 200, labelWidth, labelHight);

			JLabel customerOccupationLabel = new JLabel(
					"customerOccupationLabel");
			assignJLabelDetails(customerOccupationLabel, "Occupation: ",
					firstColumnStart, 250, labelWidth, labelHight);

			JLabel customerContactNumberLabel = new JLabel(
					"customerContactNumberLabel");
			assignJLabelDetails(customerContactNumberLabel, "Contact Number: ",
					firstColumnStart, 300, labelWidth, labelHight);

			JLabel customerIndexLabel = new JLabel("customerIndexLabel");
			assignJLabelDetails(customerIndexLabel, "Customer Index: ",
					firstColumnStart, 350, labelWidth, labelHight);

			assignJTextFieldDetails(customerID, "customerID",
					firstTextFieldColumnStart, 50, labelWidth, labelHight, true);

			assignJTextFieldDetails(customerFirstName, "customerFirstName",
					firstTextFieldColumnStart, 100, labelWidth, labelHight);

			assignJTextFieldDetails(customerLastName, "customerLastName",
					firstTextFieldColumnStart, 150, labelWidth, labelHight);

			// assignJTextFieldDetails(birthDate, "birthDate",
			// firstTextFieldColumnStart, 200, labelWidth, labelHight);

			birthDate = new JDateChooser();
			birthDate.setBounds(firstTextFieldColumnStart, 200, labelWidth,
					labelHight);
			birthDate.setDateFormatString("dd-MMM-yyyy");
			centerPanel.add(birthDate);

			assignJTextFieldDetails(occupation, "occupation",
					firstTextFieldColumnStart, 250, labelWidth, labelHight);

			assignJTextFieldDetails(contactNumber, "contactNumber",
					firstTextFieldColumnStart, 300, labelWidth, labelHight);

			assignJTextFieldDetails(customerIndex, "customerIndex",
					firstTextFieldColumnStart, 350, labelWidth, labelHight,
					true);

			/*
			 * Second Column
			 */
			int secondColumnStart = 500;
			int secondTextFieldColumnStart = secondColumnStart + labelWidth
					+ 50;

			JLabel customerBarCodeLabel = new JLabel("customerBarCodeLabel");
			assignJLabelDetails(customerBarCodeLabel, "Customer BarCode: ",
					secondColumnStart, 50, labelWidth, labelHight);

			JLabel customerAddressLine1Label = new JLabel(
					"customerAddressLine1Label");
			assignJLabelDetails(customerAddressLine1Label, "Address Line 1: ",
					secondColumnStart, 100, labelWidth, labelHight);

			JLabel customerAddressLine2Label = new JLabel(
					"customerAddressLine2Label");
			assignJLabelDetails(customerAddressLine2Label, "Address Line 2: ",
					secondColumnStart, 150, labelWidth, labelHight);

			JLabel customerLandMarkLabel = new JLabel("customerLandMarkLabel");
			assignJLabelDetails(customerLandMarkLabel, "Land Mark: ",
					secondColumnStart, 200, labelWidth, labelHight);

			JLabel customerCityLabel = new JLabel("customerCityLabel");
			assignJLabelDetails(customerCityLabel, "City: ", secondColumnStart,
					250, labelWidth, labelHight);

			JLabel customerStateLabel = new JLabel("CustomerStateLabel");
			assignJLabelDetails(customerStateLabel, "State: ",
					secondColumnStart, 300, labelWidth, labelHight);

			JLabel customerZipLabel = new JLabel("customerZipLabel");
			assignJLabelDetails(customerZipLabel, "Zip: ", secondColumnStart,
					350, labelWidth, labelHight);

			assignJTextFieldDetails(customerBarCode, "customerBarCode",
					secondTextFieldColumnStart, 50, labelWidth, labelHight);

			assignJTextFieldDetails(addressFirstLine, "addressFirstLine",
					secondTextFieldColumnStart, 100, labelWidth + 100,
					labelHight);

			assignJTextFieldDetails(addressSecondLine, "addressSecondLine",
					secondTextFieldColumnStart, 150, labelWidth + 100,
					labelHight);

			assignJTextFieldDetails(landMark, "landMark",
					secondTextFieldColumnStart, 200, labelWidth + 100,
					labelHight);

			assignJTextFieldDetails(city, "city", secondTextFieldColumnStart,
					250, labelWidth, labelHight);

			assignJTextFieldDetails(state, "state", secondTextFieldColumnStart,
					300, labelWidth, labelHight);

			assignJTextFieldDetails(zip, "zip", secondTextFieldColumnStart,
					350, labelWidth, labelHight);

			centerPanel.add(customerIDLabel);
			centerPanel.add(customerFirstNameLabel);
			centerPanel.add(customerLastNameLabel);
			centerPanel.add(customerBirthDateLabel);
			centerPanel.add(customerContactNumberLabel);
			centerPanel.add(customerAddressLine1Label);
			centerPanel.add(customerAddressLine2Label);
			centerPanel.add(customerLandMarkLabel);
			centerPanel.add(customerCityLabel);
			centerPanel.add(customerStateLabel);
			centerPanel.add(customerZipLabel);
			centerPanel.add(customerOccupationLabel);
			centerPanel.add(customerIndexLabel);
			centerPanel.add(customerBarCodeLabel);

			centerPanel.add(customerID);
			centerPanel.add(customerFirstName);
			centerPanel.add(customerLastName);
			centerPanel.add(birthDate);
			centerPanel.add(contactNumber);
			centerPanel.add(addressFirstLine);
			centerPanel.add(addressSecondLine);
			centerPanel.add(landMark);
			centerPanel.add(city);
			centerPanel.add(state);
			centerPanel.add(zip);
			centerPanel.add(occupation);
			centerPanel.add(customerIndex);
			centerPanel.add(customerBarCode);

			_mainTab.add(centerPanel, BorderLayout.CENTER);

			JPanel buttonPanel = new JPanel();
			buttonPanel.setBorder(BorderFactory.createLoweredBevelBorder());
			// buttonPanel.setMinimumSize(new Dimension(800, 100));
			buttonPanel.setBounds(0, 0, 800, 100);

			buttonPanel.setToolTipText("Panel");

			submitForm = new JButton("Insert/Update");
			submitForm.setBounds(50, 5, 100, 50);

			submitForm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					submitForm();
				}
			});

			buttonPanel.add(submitForm);

			resetForm = new JButton("Reset");
			resetForm.setBounds(50, 5, 100, 50);

			resetForm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					resetForm();
				}
			});

			buttonPanel.add(resetForm);

			toggleFunction = new JButton("ToggleQuery");
			toggleFunction.setBounds(50, 5, 100, 50);

			toggleFunction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					toggleFunction();
				}
			});

			buttonPanel.add(toggleFunction);

			_mainTab.add(buttonPanel, BorderLayout.SOUTH);

		} catch (Exception e) {
			_log.error("buildForm", e);

		}

	}

	private JLabel getEmptyLabel() {
		JLabel emptyJLabel = new JLabel("Empty");
		emptyJLabel.setText(" : ");
		return emptyJLabel;
	}

	@Override
	public void focusGained(FocusEvent evt) {
		// if (evt.getSource() == userIDText) {
		// if (userIDText.getText().equals("UserID")) {
		// userIDText.setText("");
		// }
		// } else if (evt.getSource() == userNameText) {
		// if (userNameText.getText().equals("UserName")) {
		// userNameText.setText("");
		// }
		// } else if (evt.getSource() == userRoleText) {
		// if (userRoleText.getText().equals("UserRole")) {
		// userRoleText.setText("");
		// }
		// }
	}

	@Override
	public void focusLost(FocusEvent evt) {
		// if (evt.getSource() == userIDText) {
		// if (userIDText.getText().equals("")) {
		// userIDText.setText("UserID");
		// }
		// } else if (evt.getSource() == userNameText) {
		// if (userNameText.getText().equals("")) {
		// userNameText.setText("UserName");
		// }
		// } else if (evt.getSource() == userRoleText) {
		// if (userRoleText.getText().equals("")) {
		// userRoleText.setText("UserRole");
		// }
		// }
	}

	// -------------------------------------------------------------------
	protected void submitForm() {
		if (customerID.isEnabled()) {
			_parent.statusBarMsg("Querying Customer ID: "
					+ customerID.getText());
			// BusinessCustomer customerDetail = BusinessCustomerDAO
			// .getCustomerDetail(customerID.getText(),
			// customerFirstName.getText(),
			// customerLastName.getText());
			// setCustomerForm(customerDetail);

			BusinessCustomer customerDetail = BusinessCustomerDAO
					.getBusinessCustomer(getCustomerForm(new BusinessCustomer()));
			setCustomerForm(customerDetail);

			_parent.setCustomerDetail(customerDetail);
		} else {
			_parent.statusBarMsg("Inserting/Updating Customer");
			if (customerID.getText().equals("")) {
				_parent.statusBarMsg("Inserting Customer");
				BusinessCustomer customerDetail = new BusinessCustomer();
				BusinessCustomerDAO
						.addBusinessCustomer(getCustomerForm(customerDetail));
				setCustomerForm(customerDetail);
			} else {
				BusinessCustomer customerDetail = new BusinessCustomer();
				BusinessCustomerDAO
						.updateCustomerDetail(getCustomerForm(customerDetail));
				setCustomerForm(customerDetail);
				_parent.statusBarMsg("Updating Customer");
			}
		}
	}

	protected void toggleFunction() {
		if (!customerID.isEnabled()) {
			_parent.statusBarMsg("ID: Enabled For Query.");
			customerID.setEnabled(true);
			toggleFunction.setText("ToggleInsert/Update");
			submitForm.setText("Query");
		} else {
			_parent.statusBarMsg("ID: Disabled For Insert.");
			customerID.setEnabled(false);
			submitForm.setText("Insert/Update");
			toggleFunction.setText("ToggleQuery");
		}
	}

	// -------------------------------------------------------------------
	// -------------------------------------------------------------------
	protected void resetForm() {
		customerID.setText("");
		customerFirstName.setText("");
		customerLastName.setText("");
		birthDate.setDate(null);
		contactNumber.setText("");
		addressFirstLine.setText("");
		addressSecondLine.setText("");
		landMark.setText("");
		city.setText("");
		state.setText("");
		zip.setText("");
		occupation.setText("");
		customerIndex.setText("");
		customerBarCode.setText("");
	}

	// -------------------------------------------------------------------
	private void populateList() {
	}

	private void assignJLabelDetails(JLabel newJLabel, String text,
			int startHorizontal, int startVertical, int width, int hight) {
		newJLabel.setText(" " + text);
		newJLabel.setToolTipText(text);
		newJLabel.setBounds(getBound(startHorizontal, startVertical, width,
				hight));
		newJLabel.setBorder(BorderFactory.createLoweredBevelBorder());
	}

	private void assignJTextFieldDetails(JTextField newJTextField, String text,
			int startHorizontal, int startVertical, int width, int hight) {
		assignJTextFieldDetails(newJTextField, text, startHorizontal,
				startVertical, width, hight, false);
	}

	private void assignJTextFieldDetails(JTextField newJTextField, String text,
			int startHorizontal, int startVertical, int width, int hight,
			boolean disabled) {
		newJTextField.setName(text);
		if (disabled) {
			newJTextField.setEnabled(false);
		}
		newJTextField.setToolTipText(text);
		newJTextField.setBounds(getBound(startHorizontal, startVertical, width,
				hight));
		newJTextField.setBorder(BorderFactory.createLoweredBevelBorder());

		newJTextField.addMouseListener(this);
		newJTextField.addFocusListener(this);

	}

	private Rectangle getBound(int startHorizontal, int startVertical,
			int width, int hight) {
		return new Rectangle(startHorizontal, startVertical, width, hight);
	}

	JTextField _dummyTextField = new JTextField();

	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getClickCount() == 3) {
			if (event.getSource().getClass().equals(_dummyTextField.getClass())) {
				((JTextField) event.getSource()).setText("");
			} else {
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent event) {

	}

	@Override
	public void mouseExited(MouseEvent event) {

	}

	@Override
	public void mousePressed(MouseEvent event) {

	}

	@Override
	public void mouseReleased(MouseEvent event) {

	}

	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

	private void setCustomerForm(BusinessCustomer customerDetail) {
		customerID.setText(customerDetail.getCustomerId() + "");
		customerFirstName.setText(customerDetail.getCustomerFirstName());
		customerLastName.setText(customerDetail.getCustomerLastName());

		birthDate.setDate(customerDetail.getBirthDate());
		contactNumber.setText(customerDetail.getContactNumber() + "");
		addressFirstLine.setText(customerDetail.getAddressFirstLine());
		addressSecondLine.setText(customerDetail.getAddressSecondLine());
		landMark.setText(customerDetail.getLandMark());
		city.setText(customerDetail.getCity());
		state.setText(customerDetail.getState());
		zip.setText(customerDetail.getZip() + "");
		occupation.setText(customerDetail.getOccupation());
		customerIndex.setText(customerDetail.getCustomerIndex());
		customerBarCode.setText(customerDetail.getCustomerBarCode());

	}

	private BusinessCustomer getCustomerForm(BusinessCustomer customerDetail) {
		if (customerID.getText() != null && !customerID.getText().equals("")) {
			customerDetail
					.setCustomerId(Integer.parseInt(customerID.getText()));
		}
		customerDetail.setCustomerFirstName(customerFirstName.getText());
		customerDetail.setCustomerLastName(customerLastName.getText());
		if (birthDate.getDate() != null) {
			customerDetail.setBirthDate(new java.sql.Date(birthDate.getDate()
					.getTime()));
		}
		if (!contactNumber.getText().equalsIgnoreCase("")) {
			customerDetail.setContactNumber(Long.parseLong(contactNumber
					.getText()));
		}
		customerDetail.setAddressFirstLine(addressFirstLine.getText());
		customerDetail.setAddressSecondLine(addressSecondLine.getText());
		customerDetail.setLandMark(landMark.getText());
		customerDetail.setCity(city.getText());
		customerDetail.setState(state.getText());
		if (!zip.getText().equalsIgnoreCase("")) {
			customerDetail.setZip(Integer.parseInt(zip.getText()));
		}
		customerDetail.setOccupation(occupation.getText());
		customerDetail.setCustomerIndex(customerIndex.getText());
		customerDetail.setCustomerBarCode(customerBarCode.getText());
		return customerDetail;

	}
}
