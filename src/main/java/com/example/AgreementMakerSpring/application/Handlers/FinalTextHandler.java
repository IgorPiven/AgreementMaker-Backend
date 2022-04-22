package com.example.AgreementMakerSpring.application.Handlers;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class FinalTextHandler {

    public StringBuilder generateFileText(Map<String, List<String>> discounts) {

        StringBuilder textToFile = new StringBuilder();
        StringBuilder textRu = new StringBuilder();
        StringBuilder textEn = new StringBuilder();

        textRu.append("инвойс №" + LineHandler.getInvoiceNo() + " от " + LineHandler.getInvoiceDate() +
                " с общей скидкой " + LineHandler.getWarehouseDiscountString() + " USD:" + "\n\n");

        textEn.append("invoice №" + LineHandler.getInvoiceNo() + " dd " + LineHandler.getInvoiceDate() +
                " with the total discount of " + LineHandler.getWarehouseDiscountString() + " USD:" + "\n\n");


        for (Map.Entry<String, List<String>> d : discounts.entrySet()) {

            if (!d.getValue().get(2).equals("no discount")) {

                StringBuilder str = new StringBuilder(d.getValue().get(2));
                StringBuilder finalStr = str.replace(str.length() - 2, str.length() - 1, ";");

                textRu.append(d.getValue().get(0) + " " + finalStr + "\n");
                textEn.append(d.getValue().get(1) + " " + finalStr + "\n");
            }

        }

        textToFile.append(textRu + "\n");
        textToFile.append(textEn);

        return textToFile;
    }
}
