package com.example.AgreementMakerSpring.services;

import com.example.AgreementMakerSpring.application.Launch.AppLaunch;
import com.example.AgreementMakerSpring.model.Discount;
import com.example.AgreementMakerSpring.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountRepository discountRepository;
    private final AppLaunch appLaunch;


    public List<Discount> findAll() {
        return discountRepository.findAll();
    }

    public Optional<Discount> findById(Long id) {
        return discountRepository.findById(id);
    }

    public StringBuilder generateListOfDiscounts(String file) {
        return appLaunch.launch(file);
    }

}
