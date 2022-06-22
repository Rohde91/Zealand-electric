
package com.example.zealand_electric;

import android.os.Environment;

import com.example.zealand_electric.Controllers.DBController;
import com.example.zealand_electric.Fragments.LoginFragment;
import com.example.zealand_electric.Fragments.NewCustomerFragment;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

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
    static Connection connection;
    static float titleSize, secondTitleSize;
    static LocalDate date = LocalDate.now();
    static int columnCount;
    static int rowCount;

    static String textAlignTable = "               ";

    //Creates the Pdf
    public static void CreatePDF(String orderNumber) {
        titleSize = 30f;
        secondTitleSize = 16f;

        try {

            //Passage to external storage.
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            //Document instance
            Document document = new Document(PageSize.A4, 5f, 5f, 5f, 5f);
            System.out.println("File created.\nSaved at " + path.getAbsolutePath());
            CreatePdfLayout(document, path, orderNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Creates the needed layout for the pdf.
    public static void CreatePdfLayout(Document document, File path, String orderNumber) {
        try {
            //OutputStream instance //TODO change path to sql database.
            OutputStream outputStream = new FileOutputStream(new File(path, "/" + orderNumber + ".pdf"));

            //PDFWriter instance
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            document.open();
            document.add(spacing);


            //First Table
            columnCount = 3;
            PdfPTable table1 = new PdfPTable(columnCount);
            cell = new PdfPCell(new Phrase("Virksomhedens navn eller logo."));
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            rowCount = 4;
            cell.setRowspan(rowCount);
            table1.addCell(cell);

            chunk = new Chunk("Tjekliste", FontFactory.getFont(FontFactory.TIMES_ROMAN, titleSize, Orange));
            cell = new PdfPCell(new Phrase(chunk));
            cell.setRowspan(rowCount);
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
            cell.setColspan(columnCount);
            cell.setBackgroundColor(Orange);
            table2.addCell(cell);

            cell = splitText("Kundenavn:", customer.getCustomerName());
            cell.setColspan(columnCount);
            table2.addCell(cell);

            cell = splitText("Adresse: ", DBController.sqlCallInfo("customer", "customerAdress", "customerName", customer.getCustomerName()));
            cell.setColspan(columnCount);
            table2.addCell(cell);

            try {
                cell = splitText("Post nr: ", DBController.sqlCallInfo("customer", "fk_zipCode", "customerName", customer.getCustomerName()));
            } catch (Exception e) {
                cell = splitText("Post nr: ", "Postnumme eksisterer ikke.");
                System.out.println(e);
            }
            table2.addCell(cell);

            cell = splitText("By: ", DBController.sqlCallInfo("zipcodetable", "city", "zipCode", customer.getFk_zipCode()));
            table2.addCell(cell);

            cell = splitText("Ordrenummer: ", NewCustomerFragment.checkList.getCaseNumber());
            table2.addCell(cell);

            cell = splitText("Identifikation af installation: ", NewCustomerFragment.checkList.getInstaller());
            cell.setColspan(columnCount);
            table2.addCell(cell);

            cell = splitText("Installation er udført af: ", NewCustomerFragment.checkList.getInstaller());
            cell.setColspan(columnCount);
            table2.addCell(cell);

            cell = splitText("Verifikation af installation er udført af: ", user.getUsername());
            columnCount = 2;
            cell.setColspan(columnCount);
            table2.addCell(cell);

            cell = splitText("Dato: ", date.toString());
            table2.addCell(cell);

            document.add(table2);
            document.add(new Chunk("\n"));

            try {
                int[] questionCount = new int[]{13, 6, 7, 2, 6, 3}; //Amount of questions for each question category
                ArrayList<String> questions = DBController.getColumnString("question", "questionText");
                ArrayList<Integer> answers = DBController.getColumnInt( "fk_valueId");
                String question;
                System.out.println(answers);
                int category = 0;
                int questionAnswer = 0;
                int questionNumber = 0;
                String markedAnswer = "";
                while (category < questionCount.length) {
                    int categoryQuestion = 0;
                    switch (category) {
                        case 0:
                            document.add(new Phrase(textAlignTable + "1. Generelt: \n", FontFactory.getFont(FontFactory.TIMES_BOLD)));
                            break;
                        case 1:
                            document.add(new Phrase(textAlignTable + "2. Tavlen: \n", FontFactory.getFont(FontFactory.TIMES_BOLD)));
                            break;
                        case 2:
                            document.add(new Phrase(textAlignTable + "3. Installation: \n", FontFactory.getFont(FontFactory.TIMES_BOLD)));
                            break;
                        case 3:
                            document.add(new Phrase(textAlignTable + "4. Indbygningsarmaturer: \n", FontFactory.getFont(FontFactory.TIMES_BOLD)));
                            break;
                        case 4:
                            document.add(new Phrase(textAlignTable + "5. Beskyttelsesledere og udligningsforbindelser: \n", FontFactory.getFont(FontFactory.TIMES_BOLD)));
                            break;
                        case 5:
                            document.add(new Phrase(textAlignTable + "6. Fejlbeskyttelse/supplerende beskyttelse: \n", FontFactory.getFont(FontFactory.TIMES_BOLD)));
                            break;
                    }
                    while (categoryQuestion < questionCount[category]) {
                        question = questions.get(questionNumber);

                        switch(0){ //Placeholder number
                            case 1:
                                markedAnswer = "Ja          ";
                                break;
                            case 2:
                                markedAnswer = "Nej          ";
                                break;
                            default:
                                markedAnswer = "Ikke relevant           ";
                        }
                        answer = new Phrase();
                        answer.add(new Chunk(textAlignTable + "  " + question, FontFactory.getFont(FontFactory.TIMES_ROMAN, 8f)));
                        answer.add(glue);
                        answer.add(new Chunk(markedAnswer + "\n", FontFactory.getFont(FontFactory.TIMES_ROMAN)));
                        document.add(answer);
                        categoryQuestion++;
                        questionNumber++;
                    }
                    category++;
                    document.add(new Chunk("\n"));
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            document.newPage();

            //Third Table
            columnCount = 3;
            PdfPTable table3 = new PdfPTable(columnCount);
            cell = new PdfPCell(new Phrase("Virksomhedens navn eller logo."));
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cell.setRowspan(4);
            table3.addCell(cell);

            chunk = new Chunk("Tjekliste", FontFactory.getFont(FontFactory.TIMES_ROMAN, titleSize, Orange));
            cell = new PdfPCell(new Phrase(chunk));
            cell.setRowspan(rowCount);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table3.addCell(cell);

            cell = new PdfPCell(new Phrase(" "));
            table3.addCell(cell);
            table3.addCell(cell);
            table3.addCell("Side 2 af 2");
            table3.addCell("Elinstallation");
            table3.addCell(cell);

            document.add(table3);
            Phrase phrase = new Phrase();
            phrase.add(new Chunk(textAlignTable, FontFactory.getFont(FontFactory.TIMES_ROMAN, secondTitleSize)));
            phrase.add(new Chunk(textAlignTable + "Måleresultater\n", FontFactory.getFont(FontFactory.TIMES_ROMAN, secondTitleSize)).setBackground(Orange));

            document.add(new Chunk("\n"));
            document.add(phrase);

            columnCount = 8;
            PdfPTable table4 = new PdfPTable(columnCount);
            cell = new PdfPCell(new Phrase("Kredsdetaljer"));
            cell.setColspan(columnCount);
            cell.setBackgroundColor(greyFilling);
            table4.addCell(cell);

            table4.addCell(new Phrase("Gruppe", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
            table4.addCell(new Phrase("OB (In)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
            table4.addCell(new Phrase("Karakteristik", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
            table4.addCell(new Phrase("Tværsnit", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
            table4.addCell(new Phrase("Maks. OB", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
            table4.addCell(new Phrase("Zs", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
            table4.addCell(new Phrase("Ra", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));
            table4.addCell(new Phrase("Isolation", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)));

            int index = 0;
            ArrayList<Integer> table4Data = DBController.exclude3ColumnsData("id", "isolation" , "fk_checklistId");
            System.out.println(table4Data);
            while(index < table4Data.size()){
                if(table4Data.get(index) == 0){
                    table4.addCell(" ");
                }
                else{
                    cell = new PdfPCell(new Phrase(table4Data.get(index).toString()));
                    table4.addCell(cell);
                }
                index++;
            }

            document.add(table4);
            document.close();
            System.out.println("Layout created.");
            System.out.println("Number of Pages: " + writer.getPageNumber());

        } catch (SQLException | DocumentException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    //Splits text in a cell.
    public static PdfPCell splitText(String text1, String text2) {
        answer = new Phrase();
        answer.add(text1);
        answer.add(glue);
        answer.add(text2);
        cell = new PdfPCell(answer);
        return cell;
    }
}


