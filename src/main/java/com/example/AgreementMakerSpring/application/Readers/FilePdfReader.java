package com.example.AgreementMakerSpring.application.Readers;

import com.example.AgreementMakerSpring.exceptions.FileNameNotFoundException;
import com.example.AgreementMakerSpring.exceptions.InvalidFileExtensionException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FilePdfReader {

    private static String fileExtension = "";

    public List<String> readPdf(String fileName) {

        File file = new File(fileName);

        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

        if (!fileExtension.equals("pdf"))
            throw new InvalidFileExtensionException("File must have PDF extension");

        if (fileName.isEmpty() || !file.exists())
            throw new FileNameNotFoundException("File not found");


        List<String> pageTextFromFile = new ArrayList<>();

        try {
            PdfReader reader = new PdfReader(fileName);
            int n = reader.getNumberOfPages();

            for (int i = 1; i <= n; i++) {

                String pageContent = PdfTextExtractor.getTextFromPage(reader, i);

                pageTextFromFile.add(pageContent);

            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Split content into single lines
        List<String> textLineByLine = new ArrayList<>();

        for (int i = 0; i < pageTextFromFile.size(); i++) {

            String currentLine = pageTextFromFile.get(i);

            String[] linesArray = currentLine.split("\n");

            for (int j = 0; j < linesArray.length; j++) {

                textLineByLine.add(linesArray[j]);

            }

        }

        return textLineByLine;
    }

}
