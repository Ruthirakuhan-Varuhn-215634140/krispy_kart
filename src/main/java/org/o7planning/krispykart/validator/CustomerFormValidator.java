package org.o7planning.krispykart.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.o7planning.krispykart.form.CustomerForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CustomerFormValidator implements Validator {

   private EmailValidator emailValidator = EmailValidator.getInstance();

   // This validator only checks for the CustomerForm.
   @Override
   public boolean supports(Class<?> clazz) {
      return clazz == CustomerForm.class; 
   }
   
   
   //makes sure there are not empty spaces in the customerInfo
   @Override
   public void validate(Object target, Errors errors) {
      CustomerForm custInfo = (CustomerForm) target;

      // Check the fields of CustomerForm.
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.customerForm.name");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.customerForm.phone");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.customerForm.email");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.customerForm.address");

      if (emailValidator.isValid(custInfo.getEmail())) {
         System.out.println("Valid information");
      }
      else {
    	  errors.rejectValue("email", "Pattern.customerForm.email");
      }
   }

}