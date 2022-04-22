package com.example.AgreementMakerSpring.application.Launch;

import com.example.AgreementMakerSpring.application.Handlers.FinalTextHandler;
import com.example.AgreementMakerSpring.application.Handlers.LineHandler;
import com.example.AgreementMakerSpring.application.Readers.DiscountsReader;
import com.example.AgreementMakerSpring.application.Readers.FilePdfReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AppLaunch {

    private final DiscountsReader discountsReader;
    private final FilePdfReader filePdfReader;
    private final LineHandler lineHandler;
    private final FinalTextHandler finalTextHandler;


    public StringBuilder launch(String fileName) {

        return new StringBuilder(finalTextHandler.generateFileText(lineHandler.handleLine(discountsReader.loadDiscounts(),
                filePdfReader.readPdf(fileName))));

    }
}
