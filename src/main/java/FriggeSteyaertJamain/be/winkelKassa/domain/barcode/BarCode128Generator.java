package FriggeSteyaertJamain.be.winkelKassa.domain.barcode;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class BarCode128Generator {

    private Document document;
    private String code;
    private PdfWriter writer;
    private int amount;

    public BarCode128Generator(String pageSizeName, String code, int amount)throws FileNotFoundException, DocumentException {
        Rectangle pageSize = new RectangleReadOnly(125, 125);
        this.document = new Document(pageSize);
        this.code = code;
        this.writer = PdfWriter.getInstance(this.document, new FileOutputStream("temp/pdf/bar128.pdf"));
        this.amount = amount;
    }

    public BarCode128Generator(String pageSizeName, String code) throws FileNotFoundException, DocumentException {
        this(pageSizeName, code, 1);
    }

    public void generateBarCode128 ()throws DocumentException {

        this.document.open();

        Barcode128 code128 = new Barcode128();
        code128.setGenerateChecksum(true);
        code128.setCode(this.code);

        for(int i=0; i< this.amount; i++){
            this.document.add(code128.createImageWithBarcode(writer.getDirectContent(), null, null));
        }
        this.document.close();
        System.out.println("Document Generated...!!!!!!");
    }
}
