package com.sample.base.display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.masterswings.tools.ToolsMasterSwings;

import com.masterswings.base.BaseMasterFrame;
import com.masterswings.data.InfoAccessModel;
import com.masterswings.view.StatusPanel;
import com.sample.base.model.SwingsModel;
import com.sample.base.model.renderer.BaseTableEditor;
import com.sample.base.model.renderer.BaseTableRenderer;
import com.sample.base.model.store.PersonRecord;
import com.sample.base.model.store.RecordsBase;

public class ApplicationMasterFrame extends BaseMasterFrame {

	private static final long serialVersionUID = 1L;
	static {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					UIManager.put("nimbusOrange", new Color(40, 225, 40));
					UIManager.put("Table.background", Color.WHITE);
					UIManager.put("Table.alternateRowColor", Color.BLUE);
					UIManager.put("nimbusSelectionBackground", new Color(220,
							220, 220));
					UIManager.put("List[Selected].textBackground", new Color(
							220, 220, 220));
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}

	}

	private static ApplicationMasterFrame mainFrame;

	private ApplicationMasterFrame(String applicationName) {
	}

	public static ApplicationMasterFrame getFrame(String frameName) {
		if (mainFrame == null) {
			mainFrame = new ApplicationMasterFrame(frameName);
			mainFrame.prepareView();
			mainFrame.loadViewComponants();
		}
		return mainFrame;
	}

	private StatusPanel statusPanel = null;

	public void setStatusPanel(StatusPanel statusPanel) {
		this.statusPanel = statusPanel;

	}

	JTable sampleTable = null;

	private void loadViewComponants() {

		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		controlPanel.setToolTipText(controlPanel.getName() + "Control");
		Vector<String> columns = new Vector<String>();

		controlPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				mouseClicked(arg0);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				sampleTable.getSelectionModel().clearSelection();

			}
		});

		columns.add("Id");
		columns.add("Name");
		columns.add("Place");
		columns.add("Role");
		columns.add("CheckBox");
		columns.add("Interger");

		Vector<RecordsBase> recordsList = new Vector<RecordsBase>();
		recordsList.add(new PersonRecord("", "", "", ""));
		recordsList.add(new PersonRecord("1", "Amit", "Pune", "Main"));
		recordsList.add(new PersonRecord("2", "Asma", "Pune", "Friend"));
		recordsList.add(new PersonRecord("3", "Poonam", "Pune", "Wife"));
		recordsList.add(new PersonRecord("1", "Amit", "Pune", "Main"));
		recordsList.add(new PersonRecord("2", "Asma", "Pune", "Friend"));
		recordsList.add(new PersonRecord("3", "Poonam", "Pune", "Wife"));
		recordsList.add(new PersonRecord("1", "Amit", "Pune", "Main"));
		recordsList.add(new PersonRecord("2", "Asma", "Pune", "Friend"));
		recordsList.add(new PersonRecord("3", "Poonam", "Pune", "Wife"));
		recordsList.add(new PersonRecord("1", "Amit", "Pune", "Main"));
		recordsList.add(new PersonRecord("2", "Asma", "Pune", "Friend"));
		recordsList.add(new PersonRecord("3", "Poonam", "Pune", "Wife"));
		recordsList.add(new PersonRecord("1", "Amit", "Pune", "Main"));
		recordsList.add(new PersonRecord("2", "Asma", "Pune", "Friend"));
		recordsList.add(new PersonRecord("3", "Poonam", "Pune", "Wife"));
		recordsList.add(new PersonRecord("1", "Amit", "Pune", "Main"));
		recordsList.add(new PersonRecord("2", "Asma", "Pune", "Friend"));
		recordsList.add(new PersonRecord("3", "Poonam", "Pune", "Wife"));
		recordsList.add(new PersonRecord("1", "Amit", "Pune", "Main"));
		recordsList.add(new PersonRecord("2", "Asma", "Pune", "Friend"));
		recordsList.add(new PersonRecord("3", "Poonam", "Pune", "Wife"));
		// recordsList.add(columns);
		SwingsModel swingsTableModel = new SwingsModel(recordsList, columns);

		sampleTable = new JTable(swingsTableModel);
		sampleTable.setToolTipText("Hellow");
		sampleTable.setRowHeight(20);

		String[] role = { "Main", "Friend", "Wife" };

		for (int i = 0; i < sampleTable.getColumnCount(); i++) {

			if (i == 4) {
				((JComponent) sampleTable.getDefaultRenderer(Boolean.class))
						.setOpaque(true);
			} else {
				sampleTable.getColumnModel().getColumn(i)
						.setCellRenderer(new BaseTableRenderer());
				if (i == 3) {
					sampleTable.getColumnModel().getColumn(i)
							.setCellEditor(new BaseTableEditor(role));
				}
			}
		}
		// Create a JScrollPane to contain for the JTable
		JScrollPane sp = new JScrollPane(sampleTable);
		controlPanel.add(sp, BorderLayout.CENTER);

		_maintPanel.add(controlPanel, BorderLayout.CENTER);
		_maintPanel.setToolTipText(_maintPanel.getName() + "Maint");
		loadToolBarPanel();

	}

	/**
	 * 
	 */
	private void loadToolBarPanel() {
		ToolsMasterSwings toolsBar = new ToolsMasterSwings();
		_maintPanel.add(toolsBar, BorderLayout.NORTH);
	}

	private JPanel controlPanel;
	private JPanel _maintPanel = null;

	private void prepareView() {
		/*
		 * Set Frame Specific attributes here....
		 */
		mainFrame.setSize(new Dimension(1000, 750));

		_maintPanel = (JPanel) mainFrame.getContentPane();
		_maintPanel.setLayout(new BorderLayout());

		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

	}

	@Override
	public void executeOK() {
		statusPanel.setText("Button OK clicked.");
		InfoAccessModel infoAccessModel = new InfoAccessModel("kshirsac",
				"admin", "5", "RW");
		try {
			for (int i = 0; i < 11; i++) {
				Thread.sleep(1000);
				statusPanel.setProgressStatus(i * 10);
			}

			statusPanel.loadInfoAccessModel(infoAccessModel);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sample.base.display.BaseMasterFrame#executeSubmit()
	 */
	@Override
	public void executeSubmit() {
		statusPanel.setText("Button Submitted clicked.");
		statusPanel.setProgressStatus(75);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sample.base.display.BaseMasterFrame#executeCancel()
	 */
	@Override
	public void executeCancel() {
		statusPanel.setText("Button Cancelled clicked.");
		statusPanel.setProgressStatus(25);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sample.base.display.BaseMasterFrame#executeReset()
	 */
	@Override
	public void executeReset() {
		statusPanel.setText("Button Reset clicked.");
		statusPanel.setProgressStatus(1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusGained(FocusEvent evn) {
		JComponent button = (JComponent) evn.getSource();
		button.setBackground(Color.GREEN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusLost(FocusEvent evn) {
		// TODO Auto-generated method stub

	}
}
