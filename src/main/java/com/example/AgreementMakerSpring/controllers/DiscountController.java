package com.example.AgreementMakerSpring.controllers;

import com.example.AgreementMakerSpring.dto.DiscountDto;
import com.example.AgreementMakerSpring.exceptions.ResourceNotFoundException;
import com.example.AgreementMakerSpring.model.Discount;
import com.example.AgreementMakerSpring.services.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agreementmaker/")
public class DiscountController {

    private final DiscountService discountService;

    @GetMapping
    public List<DiscountDto> getAllDiscounts() {
        return discountService.findAll().stream().map(DiscountDto::new).collect(Collectors.toList());
    }

    @GetMapping("/discount/{id}")
    public DiscountDto findById(@PathVariable Long id) {
        Discount d = discountService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Discount not found, id: " + id));
        return new DiscountDto(d);
    }

    @GetMapping("/file/{file}")
    public StringBuilder getListOfDiscounts(@PathVariable String file) {
        return discountService.generateListOfDiscounts(file);
    }


}