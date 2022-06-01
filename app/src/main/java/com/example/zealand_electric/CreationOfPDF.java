
package com.example.zealand_electric;

import android.os.Environment;

import com.example.zealand_electric.Controllers.DBController;
import com.example.zealand_electric.Fragments.LoginFragment;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;

import entities.User;

public class CreationOfPDF {

    public static User user = LoginFragment.user;

    public static void CreatePDF(String orderNumber){

        PreparedStatement myStmt = null;
        FileInputStream input = null;

        try{

            //Passage to external storage.
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            //Document instance
            Document document = new Document();

            //OutputStream instance //TODO change path to sql database.
            OutputStream outputStream = new FileOutputStream(new File(path, "/"+ orderNumber + ".pdf"));

            //PDFWriter instance
            PdfWriter.getInstance(document, outputStream);

            document.open();

            document.add(new Paragraph(user.getFullName() + "\n" + "Hej."));

            document.close();
            outputStream.close();

            System.out.println("File created");

            DBController.connectToDatabase();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


