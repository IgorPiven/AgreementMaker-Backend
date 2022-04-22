package com.example.AgreementMakerSpring.application.Readers;


import com.example.AgreementMakerSpring.dto.DiscountDto;
import com.example.AgreementMakerSpring.exceptions.LoadDiscountsErrorException;
import com.example.AgreementMakerSpring.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DiscountsReader {

    private final DiscountRepository discountRepository;

    public Map<String, List<String>> loadDiscounts() {

        Map<String, List<String>> discounts = new HashMap<>();

        for (DiscountDto d : discountRepository.findAll().stream().map(DiscountDto::new).collect(Collectors.toList())) {

            discounts.put(d.getDiscount(), new ArrayList<>(Arrays.asList(d.getDescrRu(), d.getDescrEn(), "no discount")));
        }

        if (discounts.isEmpty()) throw new LoadDiscountsErrorException("No discounts data downloaded");

        return discounts;
    }


}
