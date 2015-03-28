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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.businessadvancesolutions.businessmodel.BusinessDress;
import com.businessadvancesolutions.dbapi.dao.BusinessDressDAO;
import com.businessadvancesolutions.gui.startup.JMasterFrame;

public class AddDressTab extends JFrame implements FocusListener, MouseListener {
	private static Logger _log = null;
	private JMasterFrame _parent = null;

	private JPanel _mainTab = null;

	public AddDressTab(JMasterFrame parentFrame) {
		super("AddDressTab");
		_parent = parentFrame;
		if (_log == null) {
			_log = Logger.getLogger(AddDressTab.class);
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

	JTextField dressID = new JTextField(20);
	JTextField dressName = new JTextField(20);
	JTextField dressColor = new JTextField(20);
	JTextField sellPrice = new JTextField(20);
	JTextField supplierId = new JTextField(20);
	JTextField supplierPrice = new JTextField(20);
	JTextField discount = new JTextField(20);
	JTextField lotNumberDate = new JTextField(20);
	JTextField dressBarCode = new JTextField(20);
	JTextField damageId = new JTextField(20);

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

			JLabel dressIDLabel = new JLabel("dressIDLabel");
			assignJLabelDetails(dressIDLabel, "Dress ID: ", firstColumnStart,
					50, labelWidth, labelHight);

			JLabel dressNameLabel = new JLabel("dressNameLabel");
			assignJLabelDetails(dressNameLabel, "Dress Name: ",
					firstColumnStart, 100, labelWidth, labelHight);

			JLabel dressColorLabel = new JLabel("dressColorLabel");
			assignJLabelDetails(dressColorLabel, "Dress Color: ",
					firstColumnStart, 150, labelWidth, labelHight);

			JLabel sellPriceLabel = new JLabel("sellPriceLabel");
			assignJLabelDetails(sellPriceLabel, "Sell Price: ",
					firstColumnStart, 200, labelWidth, labelHight);

			JLabel supplierIdLabel = new JLabel("supplierIdLabel");
			assignJLabelDetails(supplierIdLabel, "Supplier Id: ",
					firstColumnStart, 250, labelWidth, labelHight);

			JLabel supplierPriceLabel = new JLabel("supplierPriceLabel");
			assignJLabelDetails(supplierPriceLabel, "Supplier Price: ",
					firstColumnStart, 300, labelWidth, labelHight);

			JLabel discountLabel = new JLabel("discountLabel");
			assignJLabelDetails(discountLabel, "Discount: ", firstColumnStart,
					350, labelWidth, labelHight);

			assignJTextFieldDetails(dressID, "dressID",
					firstTextFieldColumnStart, 50, labelWidth, labelHight, true);

			assignJTextFieldDetails(dressName, "dressName",
					firstTextFieldColumnStart, 100, labelWidth, labelHight);

			assignJTextFieldDetails(dressColor, "dressColor",
					firstTextFieldColumnStart, 150, labelWidth, labelHight);

			assignJTextFieldDetails(sellPrice, "sellPrice",
					firstTextFieldColumnStart, 200, labelWidth, labelHight);

			assignJTextFieldDetails(supplierId, "supplierId",
					firstTextFieldColumnStart, 250, labelWidth, labelHight);

			assignJTextFieldDetails(supplierPrice, "supplierPrice",
					firstTextFieldColumnStart, 300, labelWidth, labelHight);

			assignJTextFieldDetails(discount, "discount",
					firstTextFieldColumnStart, 350, labelWidth, labelHight,
					true);

			/*
			 * Second Column
			 */
			int secondColumnStart = 500;
			int secondTextFieldColumnStart = secondColumnStart + labelWidth
					+ 50;

			JLabel lotNumberDateLabel = new JLabel("lotNumberDateLabel");
			assignJLabelDetails(lotNumberDateLabel, "Lot Number: ",
					secondColumnStart, 50, labelWidth, labelHight);

			JLabel barCodeIdLabel = new JLabel("barCodeIdLabel");
			assignJLabelDetails(barCodeIdLabel, "Dress BarCode: ",
					secondColumnStart, 100, labelWidth, labelHight);

			JLabel damageIdLabel = new JLabel("damageIdLabel");
			assignJLabelDetails(damageIdLabel, "Damage ID: ",
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

			assignJTextFieldDetails(lotNumberDate, "lotNumberDate",
					secondTextFieldColumnStart, 50, labelWidth, labelHight);

			assignJTextFieldDetails(dressBarCode, "dressBarCode",
					secondTextFieldColumnStart, 100, labelWidth + 100,
					labelHight);

			assignJTextFieldDetails(damageId, "damageId",
					secondTextFieldColumnStart, 150, labelWidth + 100,
					labelHight);

			centerPanel.add(dressIDLabel);
			centerPanel.add(dressNameLabel);
			centerPanel.add(dressColorLabel);
			centerPanel.add(sellPriceLabel);
			centerPanel.add(supplierIdLabel);
			centerPanel.add(supplierPriceLabel);
			centerPanel.add(discountLabel);
			centerPanel.add(lotNumberDateLabel);
			centerPanel.add(barCodeIdLabel);
			centerPanel.add(damageIdLabel);

			centerPanel.add(dressID);
			centerPanel.add(dressName);
			centerPanel.add(dressColor);
			centerPanel.add(sellPrice);
			centerPanel.add(supplierId);
			centerPanel.add(supplierPrice);
			centerPanel.add(discount);
			centerPanel.add(lotNumberDate);
			centerPanel.add(dressBarCode);
			centerPanel.add(damageId);

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
		if (dressID.isEnabled()) {
			_parent.statusBarMsg("Querying Dress ID: " + dressID.getText());
			// BusinessCustomer customerDetail = BusinessCustomerDAO
			// .getCustomerDetail(customerID.getText(),
			// customerFirstName.getText(),
			// customerLastName.getText());
			// setCustomerForm(customerDetail);

			BusinessDress customerDetail = BusinessDressDAO
					.getBusinessDress(getDressForm(new BusinessDress()));
			setDressForm(customerDetail);

		} else {
			_parent.statusBarMsg("Inserting/Updating Dress");
			if (dressID.getText().equals("")) {
				_parent.statusBarMsg("Inserting Dress");
				BusinessDress dressForm = new BusinessDress();
				BusinessDressDAO.addBusinessDress(getDressForm(dressForm));
				setDressForm(dressForm);
			} else {
				BusinessDress dressForm = new BusinessDress();
				BusinessDressDAO.addBusinessDress(getDressForm(dressForm));
				setDressForm(dressForm);
				_parent.statusBarMsg("Updating Dress");
			}
		}
	}

	protected void toggleFunction() {
		if (!dressID.isEnabled()) {
			_parent.statusBarMsg("ID: Enabled For Query.");
			dressID.setEnabled(true);
			toggleFunction.setText("ToggleInsert/Update");
			submitForm.setText("Query");
		} else {
			_parent.statusBarMsg("ID: Disabled For Insert.");
			dressID.setEnabled(false);
			submitForm.setText("Insert/Update");
			toggleFunction.setText("ToggleQuery");
		}
	}

	// -------------------------------------------------------------------
	// -------------------------------------------------------------------
	protected void resetForm() {
		// customerID.setText("");
		// customerFirstName.setText("");
		// customerLastName.setText("");
		// birthDate.setDate(null);
		// contactNumber.setText("");
		// addressFirstLine.setText("");
		// addressSecondLine.setText("");
		// landMark.setText("");
		// city.setText("");
		// state.setText("");
		// zip.setText("");
		// occupation.setText("");
		// customerIndex.setText("");
		// customerBarCode.setText("");
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

	private void setDressForm(BusinessDress businessDress) {
		dressID.setText(businessDress.getDressId() + "");
		dressName.setText(businessDress.getDressName());
		dressColor.setText(businessDress.getDressColor());

		sellPrice.setText(businessDress.getSellPrice() + "");
		supplierId.setText(businessDress.getSupplierId() + "");
		supplierPrice.setText(businessDress.getSupplierPrice() + "");
		discount.setText(businessDress.getDiscount());
		lotNumberDate.setText(businessDress.getLotNumberDate());
		dressBarCode.setText(businessDress.getDressBarCode());
		damageId.setText(businessDress.getDamageId());

	}

	private BusinessDress getDressForm(BusinessDress businessDress) {
		if (dressID.getText() != null && !dressID.getText().equals("")) {
			businessDress.setDressId(Integer.parseInt(dressID.getText()));
		}
		businessDress.setDressName(dressName.getText());
		businessDress.setDressColor(dressColor.getText());

		if (!sellPrice.getText().equalsIgnoreCase("")) {
			businessDress.setSellPrice(Integer.parseInt(sellPrice.getText()));
		}
		if (!supplierId.getText().equalsIgnoreCase("")) {
			businessDress.setSupplierId(Integer.parseInt(supplierId.getText()));
		}
		if (!supplierPrice.getText().equalsIgnoreCase("")) {
			businessDress.setSupplierPrice(Integer.parseInt(supplierPrice
					.getText()));
		}
		businessDress.setDiscount(discount.getText());
		businessDress.setLotNumberDate(lotNumberDate.getText());

		businessDress.setDressBarCode(dressBarCode.getText());
		businessDress.setDamageId(damageId.getText());

		return businessDress;

	}
}
