package org.mps.authentication;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTestIT {

    @Test
    void ShouldDateInvalidReturnCredentialValidator_ValidationStatus_BIRTHDAY_INVALID(){
        UserRegistration user = new UserRegistration();
        CredentialValidator cv = new CredentialValidator(null,null,null);
        assertEquals(CredentialValidator.ValidationStatus.BIRTHDAY_INVALID,cv.validate());
    }

    @Test
    void ShouldPasswordInvalidReturnCredentialValidator_ValidationStatus_PASSWORD_INVALID(){
        UserRegistration user = new UserRegistration();
        Date date = new Date(1,1,2022);
        assertTrue(date.validate());
        CredentialValidator cv = new CredentialValidator(date,null,null);
        assertEquals(CredentialValidator.ValidationStatus.PASSWORD_INVALID,cv.validate());
    }

    @Test
    void ShouldCredentialStoreInvalidReturnCredentialValidator_ValidationStatus_CredentialInvalid(){
        UserRegistration user = new UserRegistration();
        Date date = new Date(1,1,2022);
        PasswordString ps = new PasswordString(".12345678");
        assertTrue(date.validate());
        assertTrue(ps.validate());
        CredentialValidator cv = new CredentialValidator(date,ps,null);
        assertEquals(CredentialValidator.ValidationStatus.CREDENTIAL_INVALID,cv.validate());
    }

    @Test
    void ShouldCredentialStoreInvalidReturnCredentialValidator_ValidationStatus_ExistingCredential(){
        UserRegistration user = new UserRegistration();
        Date date = new Date(1,1,2022);
        PasswordString ps = new PasswordString(".12345678");
        CredentialStore cs = new CredentialStoreSet();
        assertTrue(date.validate());
        assertTrue(ps.validate());
        assertFalse(cs.credentialExists(date,ps));
        CredentialValidator cv = new CredentialValidator(date,ps,cs);
        assertEquals(CredentialValidator.ValidationStatus.VALIDATION_OK,cv.validate());
    }

    @Test
    void ShouldCredentialStoreInvalidReturnCredentialValidator_ValidationStatus_ValidationOK(){
        UserRegistration user = new UserRegistration();
        Date date = new Date(1,1,2022);
        PasswordString ps = new PasswordString(".12345678");
        CredentialStore cs = new CredentialStoreSet();
        assertTrue(date.validate());
        assertTrue(ps.validate());
        cs.register(date,ps);
        assertTrue(cs.credentialExists(date,ps));
        CredentialValidator cv = new CredentialValidator(date,ps,cs);
        assertEquals(CredentialValidator.ValidationStatus.EXISTING_CREDENTIAL,cv.validate());
    }


}
