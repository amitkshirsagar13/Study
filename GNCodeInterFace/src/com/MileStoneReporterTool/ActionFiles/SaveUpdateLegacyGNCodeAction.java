package com.MileStoneReporterTool.ActionFiles;

import java.sql.Connection;

import javax.servlet.http.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.MileStoneReporterTool.ConnectorForms.SaveUpdateGNCodeForm;
import com.MileStoneReporterTool.SQLConnector.sqldao.GetDBConnection;
import com.MileStoneReporterTool.SQLConnector.sqldao.SQLOraGNCodeDAO;
import com.MileStoneReporterTool.Util.CustomUtil;


public class SaveUpdateLegacyGNCodeAction extends Action {

	/**
	 * Method execute.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();

		
		
		SQLOraGNCodeDAO gncodeDAO = new SQLOraGNCodeDAO();
		SaveUpdateGNCodeForm addForm = (SaveUpdateGNCodeForm) form;
		int reset = addForm.getReset();
		if (reset > 0){
			session.setAttribute("message", "");
			addForm = new SaveUpdateGNCodeForm();
			session.setAttribute("SaveUpdateForm", addForm);
			return mapping.findForward("success");
		}
			
		String normlizedName = addForm.getNormalized_court();
		String stateName = addForm.getState_name();
		String countyName = addForm.getCounty();
		String county1Name = addForm.getCounty1();
		String courtName = addForm.getCourt();
		String parishName = addForm.getParish();
		String publicationName = addForm.getPublication();
		String gncodeId = addForm.getGncode_id();
		if (normlizedName == null || normlizedName.equals("") || 
				(stateName == null && stateName.equals("") || 
						courtName == null && courtName.equals("") || 
						countyName == null && countyName.equals("") || 
				county1Name == null && county1Name.equals("") ||
				parishName == null && parishName.equals("") || 
				publicationName == null && publicationName.equals(""))
				&& gncodeId.equals("0")){
			session.setAttribute("message", "No data saved for the action.");
			session.setAttribute("formSaved", "0");
			return mapping.findForward("success");
		}else if (normlizedName == null || normlizedName.equals("") && !gncodeId.equals("0")){
			gncodeDAO.updateGNCodeMappingInfo(CustomUtil.getGNCodeMappingInfoDataObject(addForm));
			session.setAttribute("formSaved", "1");
			session.setAttribute("message", "Saved the GNCode Mappings successfully.");
			session.setAttribute("SaveUpdateForm", addForm);
			return mapping.findForward("success");
		}else if (gncodeId == null|| gncodeId.equals("") || gncodeId.equals("0")){
			gncodeId = gncodeDAO.createNewGNCodeID(CustomUtil.getGNCodeMappingInfoDataObject(addForm));
		}else{
			gncodeDAO.updateGNCodeMappingInfo(CustomUtil.getGNCodeMappingInfoDataObject(addForm));
		}
		gncodeDAO.updateGNCodeMapping(CustomUtil.getGNCodeJVDataObject(addForm),Integer.parseInt(gncodeId));
		gncodeDAO.updateGNCodeJVData(CustomUtil.getGNCodeJVDataObject(addForm));
		addForm.setGncode_id(gncodeId);
		session.setAttribute("formSaved", "1");
		session.setAttribute("message", "Saved the GNCode Mappings successfully.");
		session.setAttribute("SaveUpdateForm", addForm);
		return mapping.findForward("success");
	}

}
