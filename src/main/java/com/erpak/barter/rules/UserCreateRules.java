package com.erpak.barter.rules;

import com.erpak.barter.exceptions.DuplicateIdentityNumberException;
import com.erpak.barter.exceptions.EmailAlreadyUsedException;
import com.erpak.barter.exceptions.ExceptionMessages;
import com.erpak.barter.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserCreateRules {

    private UserRepository userRepository;

    public void checkIfUserEmailExists(String email) {

        if(this.userRepository.existsByEmail(email)) {
            throw new EmailAlreadyUsedException(ExceptionMessages.EMAIL_ALREADY_USED);
        }

    }

    public void checkIfUserIdentityNumberExists(String identityNumber) {

        if(this.userRepository.existsByIdentityNumber(identityNumber)) {
            throw new DuplicateIdentityNumberException(ExceptionMessages.DUPLICATE_IDENTITY_NUMBER);
        }

    }


}
