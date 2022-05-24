package org.mps.authentication;


import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTestIT {

    @Test
    void prueba1(){
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
    void prueba2(){
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
    void prueba3(){
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
    void prueba4(){
        UserRegistration user = new UserRegistration();
        Date date = new Date(1,1,2022);
        PasswordString ps = new PasswordString(".12345678");
        CredentialStore cs = Mockito.mock(CredentialStoreSet.class);
        CredentialValidator cv = new CredentialValidator(date,ps,cs);
        Mockito.when(cs.credentialExists(date,ps)).thenReturn(false);
        assertTrue(user.register(date,ps,cs,cv));
    }

    @Test
    void prueba5(){
        UserRegistration user = new UserRegistration();
        Date date = new Date(1,1,2022);
        PasswordString ps = new PasswordString(".12345678");
        CredentialStore cs = new CredentialStoreSet();
        CredentialValidator cv = new CredentialValidator(date,ps,cs);
        assertTrue(user.register(date,ps,cs,cv));
    }

}
