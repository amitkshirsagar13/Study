package com.sample.base;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * Servlet implementation class UploadFileServlet
 */
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadFileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String mailTo = request.getParameter("mailTo");
		System.out.println(mailTo);
		request.getAttribute("Test");
		String contentType = request.getContentType();
		if ((contentType.indexOf("multipart/form-data") >= 0)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// Configure a repository (to ensure a secure temp location is used)
			ServletContext servletContext = this.getServletConfig().getServletContext();
			File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
			System.out.println("Temp File Path:" + repository.getAbsolutePath());
			factory.setRepository(repository);
			Map paramterMap = request.getParameterMap();
			Set<String> entry = paramterMap.entrySet();
			for (Iterator iterator = entry.iterator(); iterator.hasNext();) {
				String type = (String) iterator.next();
				System.out.println(type);
			}
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			InputStream uploadedFileStream = null;
			String uploadedFileName = null;
			// Parse the request
			try {
				List<FileItem> items = upload.parseRequest(request);
				System.out.println(items.size());
				items = upload.parseRequest(request);
				System.out.println(items.size());
				for (Iterator<FileItem> iterator = items.iterator(); iterator.hasNext();) {
					FileItem fileItem = iterator.next();
					if (fileItem.isFormField()) {
						String key = fileItem.getFieldName();
						String val = fileItem.getString();
						System.out.println("Form parameter " + key + "=" + val);
					} else {
						if (fileItem.getSize() < 1) {
							System.out.println("No file was uplaoded");
						}

						uploadedFileName = fileItem.getName();
						uploadedFileStream = fileItem.getInputStream();
						System.out.println(uploadedFileName);
						POIFSFileSystem fs = new POIFSFileSystem(uploadedFileStream);
						HSSFWorkbook wb = new HSSFWorkbook(fs);
						HSSFSheet sheet = wb.getSheetAt(0);
						Iterator<Row> rowIterator = sheet.iterator();
						while (rowIterator.hasNext()) {
							Row row = rowIterator.next();

							// For each row, iterate through each columns
							Iterator<Cell> cellIterator = row.cellIterator();
							while (cellIterator.hasNext()) {

								Cell cell = cellIterator.next();

								switch (cell.getCellType()) {
								case Cell.CELL_TYPE_BOOLEAN:
									System.out.print(cell.getBooleanCellValue() + "\t\t");
									break;
								case Cell.CELL_TYPE_NUMERIC:
									System.out.print(cell.getNumericCellValue() + "\t\t");
									break;
								case Cell.CELL_TYPE_STRING:
									System.out.print(cell.getStringCellValue() + "\t\t");
									break;
								}
							}
							System.out.println("");
						}
						uploadedFileStream.close();
					}

				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
