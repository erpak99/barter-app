package com.erpak.barter.rules;

import com.erpak.barter.exceptions.BrandNameAlreadyUsedException;
import com.erpak.barter.exceptions.ExceptionMessages;
import com.erpak.barter.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandCreateRules {

    private final BrandRepository brandRepository;

    public void checkIfBrandNameExists(String name) {

        if(this.brandRepository.existsByName(name)) {
            throw new BrandNameAlreadyUsedException(ExceptionMessages.BRAND_NAME_ALREADY_EXISTS);
        }

    }

}
