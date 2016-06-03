/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.team7.Validator;

import com.team7.Domain.Passord;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 *
 * @author Eirik
 */
public class PasswordValidator implements Validator, PasswordEncoder{
    
    private static final int MINIMUM_PASSWORD_LENGTH = 8;
    private static final boolean HAS_UPPERCASE = true, HAS_LOWERCASE = true, HAS_NUMBER = true;

    public boolean supports(Class<?> paramClass) {
        return Passord.class.isAssignableFrom(paramClass);
    }

    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "gammeltPassord", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "nyttPassord", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "nyttPassordGjenta", "field.required");
        
        Passord passordObjekt = (Passord) o;
        
        if (!passordObjekt.getNyttPassord().equals(passordObjekt.getNyttPassordGjenta())){
            errors.rejectValue("nyttPassordGjenta", "valid.nyttPassordGjentaUlikt");
        }
    }
    public static boolean validerPassord(String passord, String databasepassord){
         if(Integer.valueOf(passord.hashCode()) == Integer.valueOf(databasepassord)){
             return true;
         }else{
             return false;
         }
    }
    public static String hashPassord(String passord){
        return String.valueOf(passord.hashCode());
    }

    @Override
    public String encode(CharSequence cs) {
        System.out.println("Encoder: " + cs.toString());
        return String.valueOf(cs.toString().hashCode());
    }

    @Override
    public boolean matches(CharSequence cs, String string) {
        boolean ok = (String.valueOf(cs.toString().hashCode()).equals(string));
        System.out.println("Matcher følgende: " + String.valueOf(cs.toString().hashCode()) + " = " + string + ". Match " + ok);
        return ok;
    }
    
}
