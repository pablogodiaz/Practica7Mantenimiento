package org.mps.authentication;


import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTestIT {

    @Test
    void ShouldAllValidateComponentsMockedReturnValidRegister(){
        UserRegistration user = new UserRegistration();
        Date date = Mockito.mock(Date.class);
        PasswordString ps = Mockito.mock(PasswordString.class);
        CredentialStore cs = Mockito.mock(CredentialStoreSet.class);
        CredentialValidator cv = Mockito.mock(CredentialValidator.class);
        Mockito.when(date.validate()).thenReturn(true);
        Mockito.when(ps.validate()).thenReturn(true);
        Mockito.when(cs.credentialExists(date,ps)).thenReturn(false);
        Mockito.when(cv.validate()).thenReturn(CredentialValidator.ValidationStatus.VALIDATION_OK);
        assertTrue(user.register(date,ps,cs,cv));
    }

    @Test
    void ShouldValidCredentialValidatorWithoutMockReturnValidRegister(){
        UserRegistration user = new UserRegistration();
        Date date = Mockito.mock(Date.class);
        PasswordString ps = Mockito.mock(PasswordString.class);
        CredentialStore cs = Mockito.mock(CredentialStoreSet.class);
        CredentialValidator cv = new CredentialValidator(date,ps,cs);
        Mockito.when(date.validate()).thenReturn(true);
        Mockito.when(ps.validate()).thenReturn(true);
        Mockito.when(cs.credentialExists(date,ps)).thenReturn(false);
        assertTrue(user.register(date,ps,cs,cv));
    }

    @Test
    void ShouldValidDateWithoutMockReturnValidRegister(){
        UserRegistration user = new UserRegistration();
        Date date = new Date(1,1,2022);
        PasswordString ps = Mockito.mock(PasswordString.class);
        CredentialStore cs = Mockito.mock(CredentialStoreSet.class);
        CredentialValidator cv = new CredentialValidator(date,ps,cs);
        Mockito.when(ps.validate()).thenReturn(true);
        Mockito.when(cs.credentialExists(date,ps)).thenReturn(false);
        assertTrue(user.register(date,ps,cs,cv));
    }

    @Test
    void ShouldValidStringPasswordWithoutMockReturnValidRegister(){
        UserRegistration user = new UserRegistration();
        Date date = new Date(1,1,2022);
        PasswordString ps = new PasswordString(".12345678");
        CredentialStore cs = Mockito.mock(CredentialStoreSet.class);
        CredentialValidator cv = new CredentialValidator(date,ps,cs);
        Mockito.when(cs.credentialExists(date,ps)).thenReturn(false);
        assertTrue(user.register(date,ps,cs,cv));
    }

    @Test
    void ShouldCredentialStoreWithoutMockReturnValidRegister(){
        UserRegistration user = new UserRegistration();
        Date date = new Date(1,1,2022);
        PasswordString ps = new PasswordString(".12345678");
        CredentialStore cs = new CredentialStoreSet();
        CredentialValidator cv = new CredentialValidator(date,ps,cs);
        assertTrue(user.register(date,ps,cs,cv));
    }

    @Test
    void ShouldRegisterTwoTimesReturnNonValidRegister(){
        UserRegistration user = new UserRegistration();
        Date date = new Date(1,1,2022);
        PasswordString ps = new PasswordString(".12345678");
        CredentialStore cs = new CredentialStoreSet();
        CredentialValidator cv = new CredentialValidator(date,ps,cs);
        assertTrue(user.register(date,ps,cs,cv));
        assertFalse(user.register(date,ps,cs,cv));
    }

    @Test
    void ShouldNonValidDateReturnNonValidRegister(){
        UserRegistration user = new UserRegistration();
        Date date = null;
        PasswordString ps = new PasswordString(".12345678");
        CredentialStore cs = new CredentialStoreSet();
        CredentialValidator cv = new CredentialValidator(date,ps,cs);
        assertFalse(user.register(date,ps,cs,cv));
    }

    @Test
    void ShouldNonValidPasswordStringReturnNonValidRegister(){
        UserRegistration user = new UserRegistration();
        Date date = new Date(1,1,2022);
        PasswordString ps = null;
        CredentialStore cs = new CredentialStoreSet();
        CredentialValidator cv = new CredentialValidator(date,ps,cs);
        assertFalse(user.register(date,ps,cs,cv));
    }

    @Test
    void ShouldNonValidCredentialStoreReturnNonValidRegister(){
        UserRegistration user = new UserRegistration();
        Date date = new Date(1,1,2022);
        PasswordString ps = new PasswordString(".12345678");
        CredentialStore cs = null;
        CredentialValidator cv = new CredentialValidator(date,ps,cs);
        assertFalse(user.register(date,ps,cs,cv));
    }

    @Test
    void ShouldNonValidCredentialValidatorReturnNonValidRegister(){
        UserRegistration user = new UserRegistration();
        Date date = new Date(1,1,2022);
        PasswordString ps = new PasswordString(".12345678");
        CredentialStore cs = new CredentialStoreSet();
        CredentialValidator cv = null;
        assertFalse(user.register(date,ps,cs,cv));
    }

}
