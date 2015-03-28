package com.businessadvancesolutions.gui.report;

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
import java.io.ByteArrayOutputStream;

import javax.swing.JEditorPane;
import javax.swing.JFrame;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;

import com.businessadvancesolutions.gui.startup.JMasterFrame;

public class FirstReport extends JFrame {

	JEditorPane myEditorPane = null;

	IReportEngine engine = null;

	EngineConfig config = null;
	JMasterFrame _parentFrame = null;

	public FirstReport(JMasterFrame parentFrame) {
		super("BIRT Report");
		myEditorPane = new JEditorPane();
		myEditorPane.setEditable(false);
	}

	public void runReport() {

		try {

			IReportRunnable design = null;

			// Open the report design

			design = engine.openReportDesign("./report/firstReport.rptdesign");

			// Create task to run and render the report,

			IRunAndRenderTask task = engine.createRunAndRenderTask(design);

			HTMLRenderOption options = new HTMLRenderOption();

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			options.setOutputStream(bos);

			options.setOutputFormat("html");

			options.setEmbeddable(true);

			task.setRenderOption(options);

			task.run();

			task.close();

			myEditorPane.setContentType("text/html");

			myEditorPane.setText(bos.toString());

			System.out.println("Finished Gen");

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

	public JEditorPane getReportPanel() {

		return myEditorPane;
	}

	public void startPlatform() {

		try {

			config = new EngineConfig();

			config.setBIRTHome("./");

			Platform.startup(config);

			IReportEngineFactory factory = (IReportEngineFactory) Platform
					.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);

			engine = factory.createReportEngine(config);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public void stopPlatform() {

		engine.destroy();

		Platform.shutdown();

	}

	public static void main(String[] args)

	{

		// FirstReport html = new FirstReport();
		//
		// html.startPlatform();
		//
		// System.out.println("Started");
		//
		// html.runReport();
		//
		// html.stopPlatform();
		//
		// System.out.println("Finished");

	}

}
