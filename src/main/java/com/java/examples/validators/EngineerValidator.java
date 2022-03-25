package com.java.examples.validators;

import com.java.examples.domain.Associate;
import com.java.examples.domain.Role;
import com.java.examples.exceptions.InvalidRoleRuntimeException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EngineerValidator implements Validator {
    private final Role role = Role.Engineer;

    @Override
    public void validate(Associate associate) throws InvalidRoleRuntimeException {
        if (Objects.isNull(associate.getId()) || !associate.getId().startsWith(role.prefix))
            throw new InvalidRoleRuntimeException(role, associate.getId());
    }

    @Override
    public boolean appliesTo(Associate associate) {
        return role.equals(associate.getRole());
    }
}