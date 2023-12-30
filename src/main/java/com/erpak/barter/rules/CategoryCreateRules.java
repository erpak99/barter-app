package com.erpak.barter.rules;

import com.erpak.barter.exceptions.CategoryNameAlreadyUsedException;
import com.erpak.barter.exceptions.ExceptionMessages;
import com.erpak.barter.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryCreateRules {

    private final CategoryRepository categoryRepository;

    public void checkIfCategoryNameExists(String name) {

        if(this.categoryRepository.existsByName(name)) {
            throw new CategoryNameAlreadyUsedException(ExceptionMessages.CATEGORY_NAME_ALREADY_EXISTS);
        }

    }

}
