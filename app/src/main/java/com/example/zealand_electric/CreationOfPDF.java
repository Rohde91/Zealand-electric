
package com.example.zealand_electric;

import android.os.Environment;

import com.example.zealand_electric.Controllers.DBController;
import com.example.zealand_electric.Fragments.LoginFragment;
import com.example.zealand_electric.Fragments.NewCustomerFragment;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;

import entities.Customer;
import entities.User;

public class CreationOfPDF {

    public static User user = LoginFragment.user;
    public static Customer customer = NewCustomerFragment.customer;

    static BaseColor Orange = new BaseColor(255, 192, 0);
    static BaseColor greyFilling = new BaseColor(191, 191, 191);
    static Chunk glue = new Chunk(new VerticalPositionMark());
    static PdfPCell cell;
    static Chunk chunk;
    static Phrase answer;
    static Chunk spacing = new Chunk("\n" + "\n" + "\n");
    static Connection connection = DBController.connectToDatabase();

    public static void CreatePDF(String orderNumber) {


        try {

            //Passage to external storage.
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            //Document instance
            Document document = new Document(PageSize.A4, 5f, 5f, 5f, 5f);
            float titleSize, secondTitleSize;
            titleSize = 30f;
            secondTitleSize = 16f;
            String textAlignTable = "              ";

            //OutputStream instance //TODO change path to sql database.
            OutputStream outputStream = new FileOutputStream(new File(path, "/" + orderNumber + ".pdf"));

            //PDFWriter instance
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            document.open();
            document.add(spacing);


            //First Table
            PdfPTable table1 = new PdfPTable(3);
            cell = new PdfPCell(new Phrase("Virksomhedens navn eller logo."));
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cell.setRowspan(4);
            table1.addCell(cell);

            chunk = new Chunk("Tjekliste", FontFactory.getFont(FontFactory.TIMES_ROMAN, titleSize, Orange));
            cell = new PdfPCell(new Phrase(chunk));
            cell.setRowspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            cell = new PdfPCell(new Phrase(" "));
            table1.addCell(cell);
            table1.addCell(cell);
            table1.addCell("Side 1 af 2");
            table1.addCell("Elinstallation");
            table1.addCell(cell);

            //DBController.connectToDatabase();
            document.add(table1);
            Phrase headline = new Phrase(textAlignTable + "Elinstallation – Verifikation af mindre elinstallation", (FontFactory.getFont(FontFactory.TIMES_ROMAN, secondTitleSize)));
            document.add(headline);

            //Second Table
            PdfPTable table2 = new PdfPTable(3);
            cell = new PdfPCell(new Phrase("Installationsoplysninger"));
            cell.setColspan(3);
            cell.setBackgroundColor(Orange);
            table2.addCell(cell);

            cell = splitText("Kundenavn:", customer.getCustomerName());
            cell.setColspan(3);
            table2.addCell(cell);

            cell = splitText("Adresse: ", DBController.sqlCallInfo("customer","customerAdress", "customerName", customer.getCustomerName()));
            cell.setColspan(3);
            table2.addCell(cell);

            cell = splitText("Post nr: ", DBController.sqlCallInfo("customer", "fk_zipCode","customerName", customer.getCustomerName()));
            table2.addCell(cell);

            cell = splitText("By: ", DBController.sqlCallInfo("zipcodetable", "city", "zipCode", customer.getFk_zipCode()));
            table2.addCell(cell);

            cell = splitText("Ordrenummer: ", NewCustomerFragment.checkList.getCaseNumber());
            table2.addCell(cell);

            cell = splitText("Identifikation af installation: ", user.getUsername());
            cell.setColspan(3);
            table2.addCell(cell);

            document.add(table2);
            document.newPage();

            //Third Table
            PdfPTable table3 = new PdfPTable(3);
            cell = new PdfPCell(new Phrase("Virksomhedens navn eller logo."));
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cell.setRowspan(4);
            table3.addCell(cell);

            chunk = new Chunk("Tjekliste", FontFactory.getFont(FontFactory.TIMES_ROMAN, titleSize, Orange));
            cell = new PdfPCell(new Phrase(chunk));
            cell.setRowspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table3.addCell(cell);

            cell = new PdfPCell(new Phrase(" "));
            table3.addCell(cell);
            table3.addCell(cell);
            table3.addCell("Side 2 af 2");
            table3.addCell("Elinstallation");
            table3.addCell(cell);

            document.add(new Chunk( textAlignTable+ "Måleresultater" + secondTitleSize).setBackground(Orange));

            document.add(table3);
            document.close();

            System.out.println("File created");
            System.out.println("Number of Pages: " + writer.getPageNumber());
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static PdfPCell splitText(String text1, String text2) {
        answer = new Phrase();
        answer.add(text1);
        answer.add(glue);
        answer.add(text2);
        cell = new PdfPCell(answer);
        return cell;
    }
}


