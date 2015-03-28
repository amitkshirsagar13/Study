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
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.log4j.Logger;

import com.businessadvancesolutions.businessmodel.BusinessCustomer;
import com.businessadvancesolutions.businessmodel.BusinessDress;
import com.businessadvancesolutions.businessmodel.BusinessInvoice;
import com.businessadvancesolutions.businessmodel.BusinessSell;
import com.businessadvancesolutions.dbapi.dao.BusinessCustomerDAO;
import com.businessadvancesolutions.dbapi.dao.BusinessDressDAO;
import com.businessadvancesolutions.dbapi.dao.BusinessInvoiceSellDAO;
import com.businessadvancesolutions.gui.model.BusinessTableModel;
import com.businessadvancesolutions.gui.renderer.BusinessTableRenderer;
import com.businessadvancesolutions.gui.startup.JMasterFrame;
import com.businessadvancesolutions.helper.SystemLogger;
import com.toedter.calendar.JDateChooser;

public class GenerateInvoiceTab extends JFrame implements FocusListener,
		MouseListener, ItemListener {
	private static Logger _log = null;
	private JMasterFrame _parent = null;

	private JPanel _mainTab = null;

	public GenerateInvoiceTab(JMasterFrame parentFrame) {
		super("GenerateInvoiceTab");
		_parent = parentFrame;
		if (_log == null) {
			_log = Logger.getLogger(GenerateInvoiceTab.class);
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

	JTextField invoiceIDText = new JTextField(20);
	JTextField invoiceBarCodeText = new JTextField();

	JDateChooser invoiceDate = null;

	JTextField invoiceTotal = new JTextField();
	JTextField invoiceDiscount = new JTextField();

	JTextField customerIDText = new JTextField();
	JTextField customerFirstNameText = new JTextField(20);
	JTextField customerLastNameText = new JTextField(20);
	JCheckBox customerIDCheckBox = new JCheckBox();
	JTextField customerBarCodeText = new JTextField();

	JTextField dressBarCodeText = new JTextField();

	BusinessTableModel invoiceTableModel = null;
	List<BusinessSell> businessSellList = new ArrayList<BusinessSell>();
	JTable invoiceDetailJTable = null;

	JButton resetForm = null;
	JButton toggleFunction = null;
	JButton submitForm = null;
	JButton invoiceDetailAdd = null;
	JButton invoiceDetailDelete = null;

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

			JLabel invoiceIDLabel = new JLabel("invoiceID");
			assignJLabelDetails(invoiceIDLabel, "Invoice ID: ",
					firstColumnStart, 50, labelWidth, labelHight);

			JLabel invoiceBarCodeLabel = new JLabel("invoiceBarCode");
			assignJLabelDetails(invoiceBarCodeLabel, "Invoice BarCode: ",
					firstColumnStart, 100, labelWidth, labelHight);

			JLabel invoiceDateLabel = new JLabel("invoiceDate");
			assignJLabelDetails(invoiceDateLabel, "Invoice Date: ",
					firstColumnStart, 150, labelWidth, labelHight);

			JLabel customerIDLabel = new JLabel("customerID");
			assignJLabelDetails(customerIDLabel, "Customer ID: ",
					firstColumnStart, 200, labelWidth, labelHight);

			JLabel customerFirstNameLabel = new JLabel("customerFirstName");
			assignJLabelDetails(customerFirstNameLabel, "First Name: ",
					firstColumnStart, 250, labelWidth, labelHight);

			JLabel customerLastNameLabel = new JLabel("customerLastName");
			assignJLabelDetails(customerLastNameLabel, "Last Name: ",
					firstColumnStart, 300, labelWidth, labelHight);

			JLabel customerBarCodeLabel = new JLabel("customerBarCode");
			assignJLabelDetails(customerBarCodeLabel, "Customer BarCode: ",
					firstColumnStart, 350, labelWidth, labelHight);

			JLabel invoiceDiscountLabel = new JLabel("invoiceDiscount");
			assignJLabelDetails(invoiceDiscountLabel, "Invoice Discount: ",
					firstColumnStart, 500, labelWidth, labelHight);

			JLabel invoiceTotalLabel = new JLabel("invoiceTotal");
			assignJLabelDetails(invoiceTotalLabel, "Invoice Total: ",
					firstColumnStart, 550, labelWidth, labelHight);

			JLabel dressBarCodeLabel = new JLabel("dressBarCode");
			assignJLabelDetails(dressBarCodeLabel, "Dress BarCode: ",
					firstColumnStart, 400, labelWidth, labelHight);
			dressBarCodeLabel
					.setFont(new Font("Times New Roman", Font.BOLD, 12));

			centerPanel.add(invoiceIDLabel);
			centerPanel.add(invoiceBarCodeLabel);
			centerPanel.add(invoiceDateLabel);
			centerPanel.add(customerIDLabel);
			centerPanel.add(customerFirstNameLabel);
			centerPanel.add(customerLastNameLabel);
			centerPanel.add(customerBarCodeLabel);
			centerPanel.add(dressBarCodeLabel);
			centerPanel.add(invoiceDiscountLabel);
			centerPanel.add(invoiceTotalLabel);

			assignJTextFieldDetails(invoiceIDText, "invoiceID",
					firstTextFieldColumnStart, 50, labelWidth, labelHight, true);
			assignJTextFieldDetails(invoiceBarCodeText, "invoiceBarCode",
					firstTextFieldColumnStart, 100, labelWidth, labelHight);

			invoiceDate = new JDateChooser();
			invoiceDate.setBounds(firstTextFieldColumnStart, 150, labelWidth,
					labelHight);
			invoiceDate.setDateFormatString("dd-MMM-yyyy");
			centerPanel.add(invoiceDate);

			assignJTextFieldDetails(customerIDText, "customerID",
					firstTextFieldColumnStart, 200, labelWidth, labelHight);

			customerIDCheckBox.setName("customerIDLock");
			customerIDCheckBox.setSelected(false);
			customerIDCheckBox.setBounds(firstTextFieldColumnStart + labelWidth
					+ 10, 200, labelHight, labelHight);
			customerIDCheckBox.addItemListener(this);
			customerIDCheckBox
					.setToolTipText("Uncheck to enable customerID Search");

			assignJTextFieldDetails(customerFirstNameText, "customerFirstName",
					firstTextFieldColumnStart, 250, labelWidth, labelHight);
			assignJTextFieldDetails(customerLastNameText, "customerLastName",
					firstTextFieldColumnStart, 300, labelWidth, labelHight);
			assignJTextFieldDetails(customerBarCodeText, "customerBarCode",
					firstTextFieldColumnStart, 350, labelWidth, labelHight);

			customerFirstNameText.setEnabled(false);
			customerLastNameText.setEnabled(false);
			customerBarCodeText.setEnabled(false);

			assignJTextFieldDetails(dressBarCodeText, "dressBarCode",
					firstTextFieldColumnStart, 400, labelWidth, labelHight);
			dressBarCodeText.setBackground(new Color(0xEBF29B));
			dressBarCodeText.getDocument().addDocumentListener(
					new DocumentListener() {
						@Override
						public void changedUpdate(DocumentEvent e) {
							processDressBarCode();
						}

						@Override
						public void removeUpdate(DocumentEvent e) {
							processDressBarCode();
						}

						@Override
						public void insertUpdate(DocumentEvent e) {
							processDressBarCode();
						}

					});

			centerPanel.add(dressBarCodeText);

			assignJTextFieldDetails(invoiceDiscount, "invoiceDiscount",
					firstTextFieldColumnStart, 500, labelWidth, labelHight,
					true);
			assignJTextFieldDetails(invoiceTotal, "invoiceTotal",
					firstTextFieldColumnStart, 550, labelWidth, labelHight,
					true);

			centerPanel.add(invoiceIDText);
			centerPanel.add(invoiceBarCodeText);
			centerPanel.add(customerIDText);
			centerPanel.add(customerIDCheckBox);
			centerPanel.add(customerFirstNameText);
			centerPanel.add(customerLastNameText);
			centerPanel.add(customerBarCodeText);
			centerPanel.add(invoiceDiscount);
			centerPanel.add(invoiceTotal);

			/*
			 * Add Sells DetailsTable for invoice content...
			 */

			int startColumnScrollPane = 550;

			invoiceTableModel = new BusinessTableModel(null, this);
			invoiceTableModel.setColumnNames(m_colNames);
			invoiceTableModel.setColumnTypes(m_colTypes);
			// invoiceTableModel.addInvoiceDetail(new BusinessInvoice()
			// .getVector());

			invoiceDetailJTable = new JTable(invoiceTableModel);
			invoiceDetailJTable.setCellSelectionEnabled(true);

			// invoiceDetailJTable.getModel().addTableModelListener(
			// invoiceTableModel);

			for (int i = 0; i < invoiceTableModel.getColumnCount(); i++) {
				invoiceDetailJTable.getColumnModel().getColumn(i)
						.setCellRenderer(new BusinessTableRenderer());
			}

			invoiceDetailJTable.setCellSelectionEnabled(true);

			invoiceDetailJTable
					.addMouseMotionListener(new MouseMotionAdapter() {
						@Override
						public void mouseMoved(MouseEvent e) {
							Point p = e.getPoint();
							int row = invoiceDetailJTable.rowAtPoint(p);
							int column = invoiceDetailJTable.columnAtPoint(p);
							invoiceDetailJTable.setToolTipText(String
									.valueOf(invoiceDetailJTable.getValueAt(
											row, column)));
						}// end MouseMoved
					}); // end MouseMotionAdapter

			JScrollPane invoiceContentScrollPane = new JScrollPane(
					invoiceDetailJTable);
			invoiceContentScrollPane.setBounds(startColumnScrollPane, 50, 800,
					525);

			invoiceContentScrollPane.setBorder(BorderFactory
					.createLoweredBevelBorder());

			centerPanel.add(invoiceContentScrollPane);

			JPanel invoiceButtonPanel = new JPanel();
			invoiceButtonPanel.setToolTipText("InvoiceButtonPanel");
			invoiceButtonPanel.setBorder(BorderFactory
					.createLoweredBevelBorder());
			invoiceButtonPanel.setBounds(startColumnScrollPane, 600, 800, 30);

			invoiceDetailAdd = new JButton("Add Invoice Detail");
			invoiceDetailDelete = new JButton("Delete Invoice Detail");

			invoiceDetailAdd.setBounds(50, 5, 100, 50);
			invoiceDetailDelete.setBounds(50, 5, 100, 50);

			// invoiceButtonPanel.add(invoiceDetailAdd);
			// invoiceButtonPanel.add(invoiceDetailDelete);

			invoiceDetailAdd.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					addInvoiceDetail(null);
				}
			});

			invoiceDetailDelete.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					deleteInvoiceDetail();
				}
			});

			centerPanel.add(invoiceButtonPanel);

			_mainTab.add(centerPanel, BorderLayout.CENTER);

			/*
			 * Button Panel
			 */
			JPanel buttonPanel = new JPanel();
			buttonPanel.setBorder(BorderFactory.createLoweredBevelBorder());
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

	public void processDressBarCode() {
		if (dressBarCodeText.getText().length() == 9) {
			dressBarCodeText.setEditable(false);
			SystemLogger.logDebug("Entered Valid BarCode:"
					+ dressBarCodeText.getText());
			BusinessSell businessSell = new BusinessSell();

			businessSell.setDressBarCode(dressBarCodeText.getText());
			int itemSrNo = invoiceTableModel.getRowCount() + 1;
			businessSell.setItemSrNo(itemSrNo + "");
			BusinessDress businessDress = new BusinessDress();
			businessDress.setDressBarCode(dressBarCodeText.getText());
			businessDress = BusinessDressDAO.getBusinessDress(businessDress);
			businessSell.setBusinessDress(businessDress);
			addInvoiceDetail(businessSell);

			Runnable cleanBarCodeField = new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(3500);
						dressBarCodeText.setText("");
						dressBarCodeText.setEditable(true);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
					}
				}
			};

			Thread cleanBarCode = new Thread(cleanBarCodeField);
			cleanBarCode.start();
		}
	}

	// Names of the columns
	public String[] m_colNames = { "ItemSrNo", "DressName", "Quantity",
			"ItemPrice", "InvoiceDetailPrice" };
	// Types of the columns.
	public Class[] m_colTypes = { String.class, String.class, Integer.class,
			Float.class, Float.class };

	private void addInvoiceDetail(BusinessSell sellFromBarCode) {
		if (sellFromBarCode == null) {
			// invoiceTableModel.addInvoiceDetail(new BusinessInvoice()
			// .getVector());
		} else {

			// invoiceTableModel.addInvoiceDetail(invoiceDetailsFromBarCode
			// .getVector());

			int rowNum = invoiceTableModel.getRowCount() - 1;
			businessSellList.add(sellFromBarCode);
			if ((rowNum < 0)
					|| (invoiceTableModel.getValueAt(rowNum, 2) != null && Integer
							.parseInt((String) invoiceTableModel.getValueAt(
									rowNum, 2)) > 0)) {
				invoiceTableModel.addInvoiceDetail(sellFromBarCode.getVector());
			} else {
				// invoiceTableModel.deleteInvoiceDetail(rowNum);
				invoiceTableModel.addInvoiceDetail(sellFromBarCode.getVector());
			}

		}
	}

	private void deleteInvoiceDetail() {
		int selectedRecord = invoiceDetailJTable.getSelectedRow();
		if (selectedRecord > 0) {
			invoiceTableModel.deleteInvoiceDetail(selectedRecord);
		}
	}

	private JLabel getEmptyLabel() {
		JLabel emptyJLabel = new JLabel("Empty");
		emptyJLabel.setText(" : ");
		return emptyJLabel;
	}

	@Override
	public void focusGained(FocusEvent evt) {

		// if (evt.getSource() == invoiceIDText) {
		// if (invoiceIDText.getText().equals("UserID")) {
		// invoiceIDText.setText("");
		// }
		// } else if (evt.getSource() == customerNameText) {
		// if (customerNameText.getText().equals("UserName")) {
		// customerNameText.setText("");
		// }
		// } else if (evt.getSource() == customerRoleText) {
		// if (customerRoleText.getText().equals("UserRole")) {
		// customerRoleText.setText("");
		// }
		// }
	}

	@Override
	public void focusLost(FocusEvent evt) {
		if (evt.getSource() == customerIDText) {
			_parent.statusBarMsg("Querying Customer ID: "
					+ customerIDText.getText());
			customerIDCheckBox.setSelected(true);
			BusinessCustomer businessCustomerForm = new BusinessCustomer();

			businessCustomerForm.setCustomerId(Integer.parseInt(customerIDText
					.getText()));
			BusinessCustomer customerDetail = BusinessCustomerDAO
					.getBusinessCustomer(businessCustomerForm);
			setCustomerDetails(customerDetail);
			_parent.statusBarMsg("Customer : "
					+ customerFirstNameText.getText() + " "
					+ customerLastNameText.getText() + " found...");
		}
	}

	// -------------------------------------------------------------------
	protected void submitForm() {

		if (invoiceIDText.isEnabled()) {
			try {
				BusinessInvoice invoiceForm = new BusinessInvoice();
				if (invoiceIDText.getText() != null
						&& !invoiceIDText.getText().equalsIgnoreCase("")) {
					invoiceForm.setInvoiceId(Integer.parseInt(invoiceIDText
							.getText()));
				}
				invoiceForm.setInvoiceBarCode(invoiceBarCodeText.getText());
				BusinessInvoice businessInvoice = BusinessInvoiceSellDAO
						.getBusinessInvoice(invoiceForm);

				setInvoiceForm(businessInvoice);

			} catch (Exception e) {
				_parent.statusBarMsg("Exception: " + e.getMessage());
			}
			_parent.statusBarMsg(" ID:" + invoiceIDText.getText() + "| "
					+ " Name:" + customerFirstNameText.getText() + "| "
					+ " Role:" + customerLastNameText.getText() + "| "
					+ " Password: | ");
		} else {
			if (invoiceIDText.getText().equals("")) {

				// BusinessInvoice businessInvoice = BusinessInvoiceSellDAO
				// .addBusinessInvoice(getInvoiceForm());
				BusinessInvoice businessInvoice = getInvoiceForm();
				int iSize = businessSellList.size();
				for (int i = 0; i < iSize; i++) {
					businessSellList.get(i);
					businessSellList.get(i).setQuantity(
							Integer.parseInt((String) invoiceTableModel
									.getValueAt(i, 2)));
					businessSellList.get(i).setSellPrice(
							(String) invoiceTableModel.getValueAt(i, 3));
					businessSellList.get(i).setTotalPrice(
							Integer.parseInt((String) invoiceTableModel
									.getValueAt(i, 4)));

					// businessSellForm.setInvoiceId(businessInvoice
					// .getInvoiceId());
					businessInvoice.setBusinessSells(businessSellList);
					// BusinessInvoiceSellDAO.updateBusinessSell(businessSellForm);
				}
				businessInvoice = BusinessInvoiceSellDAO
						.addBusinessInvoice(businessInvoice);
				setInvoiceForm(businessInvoice);
			} else {
				BusinessInvoice businessInvoice = BusinessInvoiceSellDAO
						.addBusinessInvoice(getInvoiceForm());
				int iSize = businessSellList.size();
				for (int i = 0; i < iSize; i++) {
					businessSellList.get(i);
					businessSellList.get(i).setQuantity(
							Integer.parseInt((String) invoiceTableModel
									.getValueAt(i, 2)));
					businessSellList.get(i).setSellPrice(
							(String) invoiceTableModel.getValueAt(i, 3));
					businessSellList.get(i).setTotalPrice(
							Integer.parseInt((String) invoiceTableModel
									.getValueAt(i, 4)));

					// businessSellForm.setInvoiceId(businessInvoice
					// .getInvoiceId());
					businessInvoice.setBusinessSells(businessSellList);
					// BusinessInvoiceSellDAO.updateBusinessSell(businessSellForm);
				}
				businessInvoice = BusinessInvoiceSellDAO
						.addBusinessInvoice(businessInvoice);
				setInvoiceForm(businessInvoice);
			}

		}

	}

	private BusinessInvoice getInvoiceForm() {
		BusinessInvoice invoiceForm = new BusinessInvoice();
		if (invoiceIDText.getText() != null
				&& !invoiceIDText.getText().equalsIgnoreCase("")) {
			invoiceForm.setInvoiceId(Integer.parseInt(invoiceIDText.getText()));
		}
		if (customerIDText.getText() != null
				&& !customerIDText.getText().equalsIgnoreCase("")) {
			invoiceForm
					.setCustomerId(Integer.parseInt(customerIDText.getText()));
		}
		if (invoiceTotal.getText() != null
				&& !invoiceTotal.getText().equalsIgnoreCase("")) {
			invoiceForm
					.setInvoiceTotal(Integer.parseInt(invoiceTotal.getText()));
		}
		if (invoiceDiscount.getText() != null
				&& !invoiceDiscount.getText().equalsIgnoreCase("")) {
			invoiceForm.setTotalDiscount(Integer.parseInt(invoiceDiscount
					.getText()));
		}
		invoiceForm.setInvoiceBarCode(invoiceBarCodeText.getText());
		invoiceForm.setInvoiceDate(invoiceDate.getDate());

		return invoiceForm;
	}

	private void setInvoiceForm(BusinessInvoice businessInvoice) {
		invoiceIDText.setText(businessInvoice.getInvoiceId() + "");
		invoiceBarCodeText.setText(businessInvoice.getInvoiceBarCode());
		invoiceDiscount.setText(businessInvoice.getTotalDiscount() + "");
		invoiceTotal.setText(businessInvoice.getInvoiceTotal() + "");
		customerIDText.setText(businessInvoice.getCustomerId() + "");
		invoiceDate.setDate(businessInvoice.getInvoiceDate());

		businessSellList = businessInvoice.getBusinessSells();

		Vector<Vector<String>> recordRecordVector = new Vector();
		for (Iterator<BusinessSell> iterator = businessSellList.iterator(); iterator
				.hasNext();) {
			BusinessSell businessSell = iterator.next();
			if (businessSell != null) {
				recordRecordVector.add(businessSell.getVector());
			}

		}

		invoiceTableModel.setRecordVector(recordRecordVector);

		customerFirstNameText.setText(businessInvoice.getBusinessCustomer()
				.getCustomerFirstName());
		customerLastNameText.setText(businessInvoice.getBusinessCustomer()
				.getCustomerLastName());
		customerIDCheckBox.setSelected(true);
		customerBarCodeText.setText(businessInvoice.getBusinessCustomer()
				.getCustomerBarCode());
		_parent.setCustomerDetail(businessInvoice.getBusinessCustomer());

	}

	private void toggleFunction() {

		if (!invoiceIDText.isEnabled()) {
			_parent.statusBarMsg("ID: Enabled For Query.");
			invoiceIDText.setEnabled(true);
			toggleFunction.setText("ToggleInsert/Update");
			submitForm.setText("Query");
		} else {
			_parent.statusBarMsg("ID: Disabled For Insert.");
			invoiceIDText.setEnabled(false);
			submitForm.setText("Insert/Update");
			toggleFunction.setText("ToggleQuery");
		}

	}

	// -------------------------------------------------------------------
	// -------------------------------------------------------------------
	protected void resetForm() {
		invoiceIDText.setText("");
		toggleFunction();

		invoiceBarCodeText.setText("");
		invoiceDiscount.setText("");
		invoiceTotal.setText("");
		customerIDText.setText("");
		invoiceDate.setDate(null);

		customerFirstNameText.setText("");
		customerLastNameText.setText("");
		customerIDCheckBox.setSelected(false);
		customerBarCodeText.setText("");

		int rowCount = invoiceTableModel.getRowCount();

		for (int index = rowCount - 1; index > -1; index--) {
			invoiceTableModel.deleteInvoiceDetail(index);
			// businessSellList.remove(index);
		}

		businessSellList = new ArrayList<BusinessSell>();
	}

	// -------------------------------------------------------------------
	private void populateList() {
		_log.info("Populated Role List...");
	}

	private void setCustomerDetails(BusinessCustomer customerDetail) {
		customerFirstNameText.setText(customerDetail.getCustomerFirstName());
		customerLastNameText.setText(customerDetail.getCustomerLastName());
		customerBarCodeText.setText(customerDetail.getCustomerBarCode());
		_parent.setCustomerDetail(customerDetail);
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
	public void mousePressed(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		if (event.getSource() == customerIDCheckBox) {
			if (((JCheckBox) event.getSource()).isSelected()) {
				customerIDText.setEnabled(false);
			} else {
				customerIDText.setEnabled(true);
				customerFirstNameText.setText("");
				customerLastNameText.setText("");
				customerBarCodeText.setText("");
			}
		}
	}

	public void calculateInvoiceTotal() {
		int rowCount = invoiceTableModel.getRowCount();
		int invoiceTotalCost = 0;
		for (int i = 0; i < rowCount; i++) {
			invoiceTotalCost = invoiceTotalCost
					+ Integer.parseInt((String) invoiceTableModel.getValueAt(i,
							4));
		}
		invoiceTotal.setText(invoiceTotalCost + "");
	}
}
