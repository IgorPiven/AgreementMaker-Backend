package com.example.AgreementMakerSpring.application.Handlers;

import com.example.AgreementMakerSpring.exceptions.InvalidDiscountAmountException;
import com.example.AgreementMakerSpring.exceptions.NoAnyDiscountException;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class LineHandler {

    private static boolean isInvoiced = false;
    private static boolean isDiscounted;
    private static String invoiceNo;
    private static String invoiceDate;
    private static String currentPO;
    private static String warehouseDiscountString;
    private static double warehouseDiscountValue;
    private static double totalDiscount;


    public Map<String, List<String>> handleLine(Map<String, List<String>> discountsData, List<String> lines) {

        isDiscounted = false;
        isInvoiced = false;
        totalDiscount = 0L;

        // Step 1:
        // Finding invoice number, invoice date and checking if current invoice contains any discount
        for (int i = 0; i < lines.size(); i++) {

            // Splitting the line for further handling
            String currentLine = lines.get(i);
            String[] currentLineParts = currentLine.split("\\s");

            // Finding invoice number
            if (currentLine.toLowerCase().contains("invoice") && !isInvoiced) {

                for (int j = 0; j < currentLineParts.length; j++) {

                    if (currentLineParts[j].equalsIgnoreCase("invoice")) {
                        invoiceNo = currentLineParts[j + 2];
                        isInvoiced = true;
                    }
                }
            }

            // Finding invoice date
            if (currentLine.toLowerCase().contains("fca")) {

                for (int j = 0; j < currentLineParts.length; j++) {

                    if (currentLineParts[j].contains("-")) {
                        invoiceDate = currentLineParts[j].replace("-", ".");

                    }
                }
            }

            // Finding total discount value
            if (currentLine.toLowerCase().contains("central warehouse discount")) {

                for (int j = 0; j < currentLineParts.length; j++) {

                    if (currentLineParts[j].equalsIgnoreCase("discount")) {
                        warehouseDiscountString = convertFigure(currentLineParts[j + 2]);
                        warehouseDiscountString = warehouseDiscountString.replace("-", "");
                        warehouseDiscountValue = convertDouble(currentLineParts[j + 2]);
                        isDiscounted = true;
                    }
                }
            }
        }

        // ---End of Step 1:
        // 1. invoice number & its date have been determined
        // 2. Discount availability has been checked


        // Step 2:
        // Running through all available discounts
        for (Map.Entry<String, List<String>> currentDiscount : discountsData.entrySet()) {

            String str = "";

            // Finding given discount in current invoice
            for (int i = 0; i < lines.size(); i++) {

                // Splitting the line for further handling
                String currentLine = lines.get(i);
                String[] currentLineParts = currentLine.split("\\s");

                // Finding PO number
                if (currentLine.toLowerCase().contains("po")) {

                    for (int j = 0; j < currentLineParts.length; j++) {

                        if (currentLineParts[j].equalsIgnoreCase("po")) {
                            currentPO = currentLineParts[j + 2];
                        }
                    }
                }

                // Finding given discount value and making the text for discount description
                if (currentLine.contains(currentDiscount.getKey() + " :")) {

                    str = str + "№" + currentPO + " - " + convertFigure(currentLineParts[2]) + " USD, ";

                    discountsData.put(currentDiscount.getKey(),
                            Stream.of(currentDiscount.getValue().get(0),
                                    currentDiscount.getValue().get(1),
                                    str)
                                    .collect(Collectors.toList()));

                    BigDecimal a = new BigDecimal(String.valueOf(totalDiscount));
                    BigDecimal b = new BigDecimal(String.valueOf(convertDouble(currentLineParts[2])));

                    totalDiscount = a.add(b).doubleValue();

                }
            }
        }

        //--- End of Step 2 ---

        // --- Step 3: checking errors

        // Checking if invoice contains any discount
        if (!isDiscounted)
            throw new NoAnyDiscountException("Invoice contains no discounts");

        //Checking if total invoice discount equals to total discount calculated
        if (totalDiscount != warehouseDiscountValue)
            throw new InvalidDiscountAmountException("Total amount of discounts does not equal to" +
                    " Central Warehouse Discount value. Check if the invoice contains all discounts uploaded");

        return discountsData;
    }

    // ------------------------------------------------------------------------------------------------ //

    // Converting figure to required format
    private static String convertFigure(String figure) {

        figure = figure.replace(",", " ");
        figure = figure.replace(".", ",");

        if (figure.contains("-")) figure = figure.replace("-", "");

        return figure;
    }

    // Converting figure to double format value
    private static double convertDouble(String figure) {

        int index = figure.indexOf(",");
        double tempDiscount = 0d;
        String tempFigure = null;

        if (index == -1) {
            tempDiscount = Math.abs(tempDiscount + Double.parseDouble(figure));
        } else {

            try {
                tempFigure = figure.substring(0, index);
                tempFigure = tempFigure + figure.substring(index + 1);
            } catch (Exception e) {
            }

            tempDiscount = Math.abs(tempDiscount + Double.parseDouble(tempFigure));

        }

        return tempDiscount;

    }

    public static String getInvoiceNo() {
        return invoiceNo;
    }

    public static String getInvoiceDate() {
        return invoiceDate;
    }

    public static String getWarehouseDiscountString() {
        return warehouseDiscountString;
    }

}
