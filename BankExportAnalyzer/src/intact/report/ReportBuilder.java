package intact.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReportBuilder {
	
	public void read(String fileName) {
		File file = new File(fileName);
		String readFileToString;
		CSVParser parser = null;
		
		try {
			readFileToString = FileUtils.readFileToString(file);
			parser = CSVParser.parse(readFileToString, CSVFormat.DEFAULT);
		} catch (IOException e) {
			System.out.println("Error getting CSV Parser.");
			e.printStackTrace();
		}
		
		int i = 0;
		ArrayList<String> headers = new ArrayList<String>();
		for (CSVRecord csvRecord : parser) {
			while (i < csvRecord.size()) {
				String header = csvRecord.get(i);
				if (header.equals("Incident ID")) {
					String incidentIdHeader = header;
					int incidentIdCol = i;
				} else if (header.equals("Title")) {
					String titleHeader = header;
					int titleCol = i;
				} else if (header.equals("Assignee")) {
					String assigneeHeader = header;
					int assigneeCol = i;
				} else if (header.equals("Assignment Group")) {
					String assignmentGroupHeader = header;
					int assignmentGroupId = i;
				} else if (header.equals("Open Time")) {
					String openTimeHeader = header;
					int openTimeCol = i;
				} else if (header.equals("Solution")) {
					String solutionHeader = header;
					int solutionCol = i;
				} else if (header.equals("Update Action")) {
					String updateActionHeader = header;
					int updateActionCol = i;
				}
				i++;
			}
			
			try {
				FileInputStream fileStream = new FileInputStream(new File("C:\\update.xls"));
				HSSFWorkbook workbook = new HSSFWorkbook(fileStream);
				HSSFSheet sheet = workbook.getSheetAt(0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}
		
	}
	

}
