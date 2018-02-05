package com.spec.controllers.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.spec.model.Result;
import com.spec.utils.WebappConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

/**
 * Created by Svetik on 26/11/2017.
 */
@Component
public class PDFBuilder extends AbstractITextPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
                                    PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        // get data model which is passed by the Spring container
        List<Result> result = (List<Result>) model.get("result");



        String declaration = (String) model.get("declaration");

        String name = result.get(0).getParent();

        String legalName = (String) model.get("legalName");

        String total = (String) model.get("total");

        Paragraph paragraph = new Paragraph(name, FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD, BaseColor.BLACK));

        paragraph.setAlignment(Element.ALIGN_LEFT);

        doc.add(paragraph);

        if (legalName != "" || legalName != null) {

            Paragraph lName = new Paragraph(legalName, FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.BLACK));

            lName.setAlignment(Element.ALIGN_LEFT);
            doc.add(lName);
        }


        PdfPTable table = new PdfPTable(numCols(declaration));
        table.setWidthPercentage(100.0f);

        table.setSpacingBefore(20);

        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setPadding(5);

        // write table header
        cell.setPhrase(new Phrase("Compound Ingredient", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Order", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Ingredient", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("% in product mixing bowl", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("% in finished product", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Total %", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Total % Salt", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Total % Water", font));
        table.addCell(cell);

        if (declaration != ("") && declaration != WebappConstants.error) {

            addQuidTableCols(table, cell, font);
        }
        // write table row data
        for (int i = 0; i < result.size(); i++) {


            table.addCell(result.get(i).getParent());
            table.addCell(result.get(i).getPosition());
            table.addCell(result.get(i).getName());
            table.addCell(result.get(i).getPercentage());
            table.addCell(result.get(i).getMixBowlPercentage());
            table.addCell(result.get(i).getTotal());
            table.addCell(result.get(i).getTotalSalt());
            table.addCell(result.get(i).getTotalWater());

            if (declaration != ("") && declaration != WebappConstants.error) {
                addQuidTableCells(table, i);
            }
        }

        doc.add(table);

        Paragraph totalP = new Paragraph("Total = " + total, FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.BLACK));

        totalP.setAlignment(Element.ALIGN_RIGHT);

        doc.add(totalP);

        if (declaration != ("") && declaration != WebappConstants.error) {

            Paragraph dec = new Paragraph("Declared Ingredient List: ", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.BLACK));

            doc.add(dec);

            doc.add(new Paragraph());

            XMLWorkerHelper.getInstance().parseXHtml(writer, doc,
                    new StringReader("<p>" + declaration + "</p>"));
        }

        //clearAll();

    }


    private String hasAllergen(String allergen) {


        String res = allergen;

        String[] restrictions = {"", "N/A", "COMPOUND"};

        for (String restriction : restrictions) {

            if (allergen.equals(restriction)) {

                res = "";

            }

        }
        return res;
    }

    private String hasFunction(String function) {


        String res = function;


        if ("N/A".equals(function)) {

            res = "";

        }


        return res;
    }


    private void clearAll() {

        WebappConstants.declarationResult = "";
        WebappConstants.total = "";
        WebappConstants.legalName = "";

    }

    private void addQuidTableCells(PdfPTable table, int i) {


        if (WebappConstants.allFunctions != null) {
            table.addCell(hasFunction(WebappConstants.allFunctions.get(i)));
        }

    }

    private void addQuidTableCols(PdfPTable table, PdfPCell cell, Font font) {

        if (WebappConstants.allFunctions != null) {
            cell.setPhrase(new Phrase("Function", font));
            table.addCell(cell);


        }
    }

    private int numCols(String declaration) {

        int res = 8;

        if (declaration != ("") && declaration != WebappConstants.error) {

            if (WebappConstants.allFunctions != null) {
                res += 1;
            }

        }
        return res;
    }
}