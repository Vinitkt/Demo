package view;

import java.awt.print.Book;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.Lecturer;

public class PdfView extends AbstractItextPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			List<Lecturer>lecturer=(List<Lecturer>) model.get("lecturer");
		
		PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {3.0f, 2.0f, 2.0f});
        table.setSpacingBefore(10);
         
        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);
         
        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(3);
         
        // write table header
        cell.setPhrase(new Phrase("Id", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("Designation", font));
        table.addCell(cell);
         
        
         
        // write table row data
        for (Lecturer lecturers : lecturer) {
            table.addCell(String.valueOf(lecturers.getId()));
            table.addCell(lecturers.getName());
            table.addCell(lecturers.getDesignation());
        }
         
        document.add(table);
		
	}

}
