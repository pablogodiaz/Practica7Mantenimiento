package org.mps.authentication;

import org.mps.authentication.CredentialValidator.ValidationStatus;

public class UserRegistration {

  public boolean register(Date birthDate, PasswordString passwordString,
      CredentialStore credentialStore,CredentialValidator cv) {

    Boolean registrado = false;

    ValidationStatus status = cv.validate();

    if (status == ValidationStatus.VALIDATION_OK) {
      credentialStore.register(birthDate, passwordString);
      registrado = true;
    }
    return registrado;
  }
}
