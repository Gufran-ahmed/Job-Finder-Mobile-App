package com.example.groupproject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.AfterClass;
import android.util.Patterns;


import com.example.groupproject.ui.home.HomeFragment;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    static SignIn signin;
    static SignUp signup;



    @BeforeClass
    public static void setup() {
        signin = new SignIn();
        signup = new SignUp();


    }
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void checkIfUserNameBoxIsEmpty(){
        assertTrue(signin.isEmptyUserName(""));
        assertFalse(signin.isEmptyUserName("test$user"));

    }

    @Test
    public void checkIfPassWordBoxIsEmpty(){
        assertTrue(signin.isEmptyPassword(""));
        assertFalse(signin.isEmptyPassword("123456"));
    }

    @Test
    public void checkIfUserNameIsValid(){
        assertTrue(signin.isValidUserName("Ayl123"));

    }

    @Test
    public void checkIfUserNameIsInValid(){
        assertFalse(signin.isValidUserName(""));
        assertFalse(signin.isValidUserName("Ayl*123!^"));
    }

    @Test
    public void checkIfPasswordIsValid(){
        assertTrue(signin.isValidPassword("Abcd123456"));

    }

    @Test
    public void checkIfPasswordIsInValid(){
        assertFalse(signin.isValidPassword(""));
        assertFalse(signin.isValidPassword("Ab!cd123456*"));
    }
    @Test
    public void checkIfUserNameIsEmpty() {
        assertTrue(signup.isEmptyUserName(""));
        assertFalse(signup.isEmptyUserName("xyz$56"));
    }
    @Test
    public void checkIfFirstNameIsEmpty(){
        assertTrue(signup.isEmptyFirstName(""));
        assertFalse(signup.isEmptyFirstName("xyz$56"));

    }
    @Test
    public void checkIfLastNameIsEmpty(){
        assertTrue(signup.isEmptyLastName(""));
        assertFalse(signup.isEmptyLastName("xyz$56"));

    }
    @Test
    public void checkIfPasswordIsEmpty(){
        assertTrue(signup.isEmptyPassword(""));
        assertFalse(signup.isEmptyPassword("xyz$56"));

    }
    @Test
    public void checkIfConfirmPasswordIsEmpty(){
        assertTrue(signup.isEmptyConfirmPassword(""));
        assertFalse(signup.isEmptyConfirmPassword("xyz$56"));

    }




    @Test
    public void checkIfUserNameIsAlphaNumeric(){
        assertTrue(signup.isAlphanumericUserName("xyz123"));
    }


    @Test
    public void checkIfUserNameIsNotAlphaNumeric(){
        assertFalse(signup.isAlphanumericUserName(""));
        assertFalse(signup.isAlphanumericUserName("&45abc^!"));
    }


    @Test
    public void checkIfEmailIsValid(){
        assertTrue(signup.isValidEmailAddress("abc123@dal.ca"));
    }


    @Test
    public void checkIfEmailIsInvalid(){
        assertFalse(signup.isValidEmailAddress("abc.123dal.ca"));
    }

    @AfterClass
    public static void tearDown(){
        System.gc();
    }
}
