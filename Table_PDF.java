import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.lang.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.sun.xml.internal.ws.util.StreamUtils;
import org.apache.commons.cli.ParseException;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.text.PDFTextStripper;
import technology.tabula.extractors.*;
import technology.tabula.extractors.BasicExtractionAlgorithm;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;
import technology.tabula.CommandLineApp;
import org.apache.commons.cli.CommandLine;
import org.apache.pdfbox.pdmodel.PDDocument;

public class Table_PDF {


    public static void main(String[] args) throws java.io.IOException, ParseException {

        //String PDF = "C:\\Users\\SASHASTR\\Desktop\\test.pdf";
        //String Dir_PDF = "C:\\Users\\SASHASTR\\Desktop\\Reports\\Sample";
        //String OutputFolder = "C:\\Users\\SASHASTR\\Desktop\\Reports\\Output";
        //Path workingDir = Paths.get("C:\\Program Files\\Tabula");
        //System.out.println("Path read");
        Path path = Paths.get("\\Users", "SASHASTR", "Desktop", "Reports", "Sample", "Test.pdf");
        //PrintWriter writer = new PrintWriter(new File("testPDFDoc.csv"));
        PDDocument mydoc = PDDocument.load(path.toFile());
        PDFTextStripper s = new PDFTextStripper();
        String content = s.getText(mydoc);
        //System.out.println(content);
        //writer.write(content);
        //writer.close();
        //System.out.println("----------------------------------------------------------------------------------------");
        PDFParser parser = new PDFParser(new RandomAccessBufferedFileInputStream(path.toFile()));
        parser.parse();
        PDDocument pdDoc = new PDDocument(parser.getDocument());
        PDPageTree pageTree = pdDoc.getPages();
        List<PDPage> collect = StreamSupport.stream(pageTree.spliterator(), false)
                .collect(Collectors.toList());
//        System.out.println("Printing");
//        Iterator<PDPage> pageIterator = collect.iterator();
//        while(pageIterator.hasNext() )
//            System.out.println(pageIterator.next());
        PDFTextStripper TextStrip = new PDFTextStripper();
        TextStrip.setWordSeparator(",");
        String output = TextStrip.getText(pdDoc);
        System.out.println(output);
        PrintWriter writer = new PrintWriter(new File("testPDFDoc3.csv"));
        writer.write(output);
        writer.close();
        //String[] command = {"java", "-jar","tabula-1.0.2-jar-with-dependencies.jar","-b",Dir_PDF,"-o","-n","-g","-p","all","-t"};
        //System.out.println("Executing command");
        //Process p = null;
        //ProcessBuilder pb = new ProcessBuilder(command);
        // pb.directory(workingDir.toFile());
        //p = pb.start();
        System.out.println("Done");
    }
}

