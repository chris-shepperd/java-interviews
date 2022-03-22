package com.java.examples.validators;

import com.java.examples.domain.Associate;
import com.java.examples.domain.Role;
import com.java.examples.exceptions.InvalidRoleRuntimeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ExecutiveValidatorTest {
    public static final Role ROLE = Role.Executive;
    public static final String INVALID_ID = "6";
    public static final String VALID_ID = "e1";
    @InjectMocks private ExecutiveValidator validator;

    @Test
    public void testValidate_NoId() {
        Associate associate = new Associate().setRole(ROLE);
        assertThrows(InvalidRoleRuntimeException.class, () -> validator.validate(associate));
    }

    @Test
    public void testValidate_IdNotValid() {
        Associate associate = new Associate().setId(INVALID_ID).setRole(ROLE);
        assertThrows(InvalidRoleRuntimeException.class, () -> validator.validate(associate));
    }

    @Test
    public void testValidate_IsValid() {
        Associate associate = new Associate().setId(VALID_ID).setRole(ROLE);
        assertDoesNotThrow(() -> validator.validate(associate));
    }

    @Test
    public void testValidate_ConstantIsValid() {
        Associate associate = new Associate().setId("5").setRole(ROLE);
        assertDoesNotThrow(() -> validator.validate(associate));
    }

    @Test
    public void testAppliesTo() {
        Associate associate = new Associate().setRole(ROLE);
        assertTrue(validator.appliesTo(associate));
    }

    @Test
    public void testAppliesTo_DoesNotApply() {
        Associate associate = new Associate().setRole(Role.Other);
        assertFalse(validator.appliesTo(associate));
    }
}