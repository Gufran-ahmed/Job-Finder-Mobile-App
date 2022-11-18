package com.example.groupproject;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.service.autofill.Validator;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;


import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.Intents.intended;


import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)

public class ExampleInstrumentedTestSignUp {
    @Rule
    public ActivityScenarioRule<SignUp> myRule = new ActivityScenarioRule<>(SignUp.class);
    public IntentsTestRule<SignUp> myIntentRule = new IntentsTestRule<>(SignUp.class);
    public ActivityTestRule<SignUp> activityRule = new ActivityTestRule<>(SignUp.class, true, false);


    @BeforeClass
    public static void setup() {
        Intents.init();
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.groupproject", appContext.getPackageName());
    }

    @Test
    public void checkIfUserNameIsEmpty() {


        onView(withId(R.id.firstName)).perform(typeText("abc"), closeSoftKeyboard());
        onView(withId(R.id.lastName)).perform(typeText("abcd"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("abc123@dal.ca"), closeSoftKeyboard());
        onView(withId(R.id.userName)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("abc123"), closeSoftKeyboard());
        onView(withId(R.id.ConfirmPassword)).perform(typeText("abc123"), closeSoftKeyboard());


        onView(withId(R.id.signUp)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_USER_NAME)));
    }

    @Test
    public void checkIfUserLastIsEmpty() {

        onView(withId(R.id.firstName)).perform(typeText("abc"), closeSoftKeyboard());
        onView(withId(R.id.lastName)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("abc123@dal.ca"), closeSoftKeyboard());
        onView(withId(R.id.userName)).perform(typeText("abc"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("abc123"), closeSoftKeyboard());
        onView(withId(R.id.ConfirmPassword)).perform(typeText("abc123"), closeSoftKeyboard());


        onView(withId(R.id.signUp)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_Last_Name)));
    }

    @Test
    public void checkIfUserFirstIsEmpty() {

        onView(withId(R.id.firstName)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.lastName)).perform(typeText("abcd"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("abc123@dal.ca"), closeSoftKeyboard());
        onView(withId(R.id.userName)).perform(typeText("abc"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("abc123"), closeSoftKeyboard());
        onView(withId(R.id.ConfirmPassword)).perform(typeText("abc123"), closeSoftKeyboard());

        onView(withId(R.id.signUp)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_FIRST_Name)));
    }

    @Test
    public void checkIfPasswordIsEmpty() {


        onView(withId(R.id.firstName)).perform(typeText("abc"), closeSoftKeyboard());
        onView(withId(R.id.lastName)).perform(typeText("abcd"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("abc123@dal.ca"), closeSoftKeyboard());
        onView(withId(R.id.userName)).perform(typeText("abc"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.ConfirmPassword)).perform(typeText("abc123"), closeSoftKeyboard());


        onView(withId(R.id.signUp)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_Password)));

    }

    @Test
    public void checkIfConfirmPasswordIsEmpty() {

        onView(withId(R.id.firstName)).perform(typeText("abc"), closeSoftKeyboard());
        onView(withId(R.id.lastName)).perform(typeText("abcd"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("abc123@dal.ca"), closeSoftKeyboard());
        onView(withId(R.id.userName)).perform(typeText("abc"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("abc123"), closeSoftKeyboard());
        onView(withId(R.id.ConfirmPassword)).perform(typeText(""), closeSoftKeyboard());


        onView(withId(R.id.signUp)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_Confirm_Password)));

    }


    @Test
    public void checkIfEmailIsInValid() {


        onView(withId(R.id.firstName)).perform(typeText("abc"), closeSoftKeyboard());
        onView(withId(R.id.lastName)).perform(typeText("abcd"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("abc123.dal.ca"), closeSoftKeyboard());
        onView(withId(R.id.userName)).perform(typeText("abc"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("abc123"), closeSoftKeyboard());
        onView(withId(R.id.ConfirmPassword)).perform(typeText("abc123"), closeSoftKeyboard());


        onView(withId(R.id.signUp)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_EMAIL_ADDRESS)));
    }

    @Test
    public void checkIfPasswordDoesNotMatch() {


        onView(withId(R.id.firstName)).perform(typeText("abc"), closeSoftKeyboard());
        onView(withId(R.id.lastName)).perform(typeText("abcd"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("abc123@dal.ca"), closeSoftKeyboard());
        onView(withId(R.id.userName)).perform(typeText("abc"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("abc123"), closeSoftKeyboard());
        onView(withId(R.id.ConfirmPassword)).perform(typeText("abc1234"), closeSoftKeyboard());

        onView(withId(R.id.signUp)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.Password_MissMatch)));
    }
    @Test
    public void checkIfMovedToSignInPage() {
        onView(withId(R.id.firstName)).perform(typeText("abc"), closeSoftKeyboard());
        onView(withId(R.id.lastName)).perform(typeText("abcd"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("abc123@dal.ca"), closeSoftKeyboard());
        onView(withId(R.id.userName)).perform(typeText("abc123"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("abc123"), closeSoftKeyboard());
        onView(withId(R.id.ConfirmPassword)).perform(typeText("abc123"), closeSoftKeyboard());
        onView(withId(R.id.signUp)).perform(click());
        intended(hasComponent(SignIn.class.getName()));
    }

    @Test
    public void checkIfUserNameIsNotAlphaNumeric() {
        onView(withId(R.id.firstName)).perform(typeText("abc"), closeSoftKeyboard());
        onView(withId(R.id.lastName)).perform(typeText("abcd"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("abc123@dal.ca"), closeSoftKeyboard());
        onView(withId(R.id.userName)).perform(typeText("abc@*!"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("abc123"), closeSoftKeyboard());
        onView(withId(R.id.ConfirmPassword)).perform(typeText("abc1234"), closeSoftKeyboard());

        onView(withId(R.id.signUp)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.NON_ALPHA_NUMERIC_USER_NAME)));
    }
}
