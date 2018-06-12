package com.example.bishoppc.apppdfread;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.io.IOException;

public class ItextActivity extends AppCompatActivity {


    /** The original PDF file. */
    public String COVER;
    /** The original PDF file. */
    public String SRC;

    /** The resulting PDF file. */
    public String DEST;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        SRC = Environment.getExternalStorageDirectory().toString()+ "/documents/HelloWorld.pdf";
        COVER = Environment.getExternalStorageDirectory().toString()+ "/documents/HelloWorld.pdf";
        DEST = Environment.getExternalStorageDirectory().toString() + "/Documents/ByeWolrd1.pdf";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itext);
        try {
            manipulatePdf(SRC,DEST);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    public void manipulatePdf(String src, String dest) throws IOException, DocumentException
    {
        PdfReader cover = new PdfReader(COVER);
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.insertPage(1, cover.getPageSizeWithRotation(1));
        PdfContentByte page1 = stamper.getOverContent(1);
        PdfImportedPage page = stamper.getImportedPage(cover, 1);
        page1.addTemplate(page, 0, 0);
        stamper.close();
        cover.close();
        reader.close();
    }


}
