package com.example.groupproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.service.autofill.Validator;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
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

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.Intents.intended;


import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.endsWith;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    //public  ActivityScenarioRule<SignIn> myRule = new ActivityScenarioRule<>(SignIn.class);
    //public IntentsTestRule<SignIn> myIntentRule = new IntentsTestRule<>(SignIn.class);
    public ActivityTestRule<SignIn> activityRule = new ActivityTestRule<>(SignIn.class);


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
    public void checkIfSignInPageIsShown() {
        onView(withId(R.id.userNameBox)).check(matches(withText("")));
        onView(withId(R.id.passwordBox)).check(matches(withText("")));
        onView(withId(R.id.signInButton)).check(matches(withText("SIGN IN")));
    }

    @Test
    public void checkIfUserNameBoxIsEmpty() {
        onView(withId(R.id.passwordBox)).perform(typeText("Admin1234"));
        onView(withId(R.id.userNameBox)).perform(typeText(""));
        onView(withId(R.id.signInButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("Empty user name!")));
    }

    @Test
    public void checkIfPassWordBoxIsEmpty() {
        onView(withId(R.id.userNameBox)).perform(typeText("admin"));
        onView(withId(R.id.passwordBox)).perform(typeText(""));
        onView(withId(R.id.signInButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("Empty password!")));
    }

    @Test
    public void checkIfUserNameIsInValid() {
        onView(withId(R.id.userNameBox)).perform(typeText("Admin*123!"));
        onView(withId(R.id.passwordBox)).perform(typeText("Admin1234"));
        onView(withId(R.id.signInButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("Non-Valid user name!")));
    }

    @Test
    public void checkIfUserNameIsInValidWithNonValidPassword() {
        onView(withId(R.id.userNameBox)).perform(typeText("Admin*123!"));
        onView(withId(R.id.passwordBox)).perform(typeText("Admin*1234"));
        onView(withId(R.id.signInButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("Non-Valid user name!")));
    }

    @Test
    public void checkIfUserNotExist(){
        onView(withId(R.id.userNameBox)).perform(typeText("amin"));
        onView((withId(R.id.passwordBox))).perform(typeText("123"));
        onView(withId(R.id.signInButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("No such user!")));
    }

    @Test
    public void checkIfUserNotExistWithEmptyPassword(){
        onView(withId(R.id.userNameBox)).perform(typeText("amin"));
        onView((withId(R.id.passwordBox))).perform(typeText(""));
        onView(withId(R.id.signInButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("Empty password!")));
    }

    @Test
    public void checkIfPasswordIsInValid() {
        onView(withId(R.id.userNameBox)).perform(typeText("admin"));
        onView(withId(R.id.passwordBox)).perform(typeText("Admin*1234!"));
        onView(withId(R.id.signInButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("Non-Valid password!")));
    }

    @Test
    public void checkIfPasswordNotMatch(){
        onView(withId(R.id.userNameBox)).perform(typeText("admin"));
        onView((withId(R.id.passwordBox))).perform(typeText("admin1234"));
        onView(withId(R.id.signInButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("Wrong password!")));
    }


    @Test
    public void checkIfMoved2EmployerHomepage() {
        onView(withId(R.id.userNameBox)).perform(typeText("admin"));
        onView(withId(R.id.passwordBox)).perform(typeText("Admin1234"));
        onView(withId(R.id.signInButton)).perform(click());
        intending(hasComponent(Homepage.class.getName()));
    }

    @Test
    public void CheckSearchJobIsValid() {
        onView(withId(R.id.userNameBox)).perform(typeText("admin"));
        onView(withId(R.id.passwordBox)).perform(typeText("Admin1234"));
        onView(withId(R.id.signInButton)).perform(click());
        intending(hasComponent(Homepage.class.getName()));
        onView(withId(R.id.searchJobBar)).perform(typeText("Sample"),closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click(),closeSoftKeyboard());
        onView(withId(R.id.listOfJobs)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listOfJobs)).atPosition(0).perform(click());
        intending(hasComponent(JobPage.class.getName()));
    }

    @Test
    public void CheckSearchJobIsValid1(){
        onView(withId(R.id.userNameBox)).perform(typeText("admin"));
        onView(withId(R.id.passwordBox)).perform(typeText("Admin1234"));
        onView(withId(R.id.signInButton)).perform(click());
        intending(hasComponent(Homepage.class.getName()));
        onView(withId(R.id.searchJobBar)).perform(typeText("3"),closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click(),closeSoftKeyboard());
        onView(withId(R.id.listOfJobs)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listOfJobs)).atPosition(0).perform(click());
        intending(hasComponent(JobPage.class.getName()));

    }

    @Test
    public void CheckSearchJobIsValid2(){
        onView(withId(R.id.userNameBox)).perform(typeText("admin"));
        onView(withId(R.id.passwordBox)).perform(typeText("Admin1234"));
        onView(withId(R.id.signInButton)).perform(click());
        intending(hasComponent(Homepage.class.getName()));
        onView(withId(R.id.searchJobBar)).perform(typeText("checkinf"),closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click(),closeSoftKeyboard());
        onView(withId(R.id.listOfJobs)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listOfJobs)).atPosition(0).perform(click());
        intending(hasComponent(JobPage.class.getName()));

    }

    @Test
    public void checkIfJobDetailMatch() {
        onView(withId(R.id.userNameBox)).perform(typeText("admin"));
        onView(withId(R.id.passwordBox)).perform(typeText("Admin1234"));
        onView(withId(R.id.signInButton)).perform(click());
        intending(hasComponent(Homepage.class.getName()));
        onView(withId(R.id.searchJobBar)).perform(typeText("Sample"), closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.listOfJobs)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listOfJobs)).atPosition(0).perform(click());
        intended((hasComponent(JobPage.class.getName())));
        onView(withId(R.id.descriptionView)).check(matches(withText("checkinf")));
        onView(withId(R.id.jobValue)).check(matches(withText("Points: 4")));
        onView(withId(R.id.employeeNumber)).check(matches(withText("Positions: 0")));
        onView(withId(R.id.PosterView)).check(matches(withText("Poster: bob")));
        onView(withId(R.id.startDateView)).check(matches(withText("StartDate: 2021-04-02")));
        onView(withId(R.id.statusView)).check(matches(withText("Status: Doing")));
        onView(withId(R.id.jobTypeView)).check(matches(withText("Type: Technical")));


    }

    @Test
    public void checkIfJobDetailMatch1() {
        onView(withId(R.id.userNameBox)).perform(typeText("admin"));
        onView(withId(R.id.passwordBox)).perform(typeText("Admin1234"));
        onView(withId(R.id.signInButton)).perform(click());
        intending(hasComponent(Homepage.class.getName()));
        onView(withId(R.id.searchJobBar)).perform(typeText("3"), closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.listOfJobs)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listOfJobs)).atPosition(0).perform(click());
        intending((hasComponent(JobPage.class.getName())));
        onView(withId(R.id.descriptionView)).check(matches(withText("3")));
        onView(withId(R.id.jobValue)).check(matches(withText("Points: 3")));
        onView(withId(R.id.employeeNumber)).check(matches(withText("Positions: 0")));
        onView(withId(R.id.PosterView)).check(matches(withText("Poster: admin")));
        onView(withId(R.id.startDateView)).check(matches(withText("StartDate: 2021-04-03")));
        onView(withId(R.id.statusView)).check(matches(withText("Status: Doing")));
        onView(withId(R.id.jobTypeView)).check(matches(withText("Type: Technical")));

    }

    @Test
    public void checkIfJobDetailMatch2() {
        onView(withId(R.id.userNameBox)).perform(typeText("admin"));
        onView(withId(R.id.passwordBox)).perform(typeText("Admin1234"));
        onView(withId(R.id.signInButton)).perform(click());
        intending(hasComponent(Homepage.class.getName()));
        onView(withId(R.id.searchJobBar)).perform(typeText("checkinf"),closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click(),closeSoftKeyboard());
        onView(withId(R.id.listOfJobs)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listOfJobs)).atPosition(0).perform(click());
        intending((hasComponent(JobPage.class.getName())));
        onView(withId(R.id.descriptionView)).check(matches(withText("checkinf")));
        onView(withId(R.id.jobValue)).check(matches(withText("Points: 4" )));
        onView(withId(R.id.employeeNumber)).check(matches(withText("Positions: 0")));
        onView(withId(R.id.PosterView)).check(matches(withText("Poster: bob")));
        onView(withId(R.id.startDateView)).check(matches(withText("StartDate: 2021-04-02")));
        onView(withId(R.id.statusView)).check(matches(withText("Status: Doing")));
        onView(withId(R.id.jobTypeView)).check(matches(withText("Type: Technical")));

    }

    @Test
    public void checkIfBackToJobs() {
        onView(withId(R.id.userNameBox)).perform(typeText("admin"));
        onView(withId(R.id.passwordBox)).perform(typeText("Admin1234"));
        onView(withId(R.id.signInButton)).perform(click());
        intending(hasComponent(Homepage.class.getName()));
        onView(withId(R.id.searchJobBar)).perform(typeText("Sample"), closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.listOfJobs)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listOfJobs)).atPosition(0).perform(click());
        intending(hasComponent(JobPage.class.getName()));
        onView(withId(R.id.back2jobs)).perform(click());
        intending(hasComponent(Homepage.class.getName()));
    }

    @Test
    public void checkIfAbleTakeJob(){
        onView(withId(R.id.userNameBox)).perform(typeText("admin"));
        onView(withId(R.id.passwordBox)).perform(typeText("Admin1234"));
        onView(withId(R.id.signInButton)).perform(click());
        intending(hasComponent(Homepage.class.getName()));
        onView(withId(R.id.searchJobBar)).perform(typeText("Sample"),closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click(),closeSoftKeyboard());
        onView(withId(R.id.listOfJobs)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listOfJobs)).atPosition(0).perform(click());
        intending(hasComponent(JobPage.class.getName()));
        onView(withId(R.id.takeJob)).perform(click());
        onView(withId(R.id.statusLabelForJob)).check(matches(withText("There is no position")));

    }

    @Test
    public void checkIfAbleTakeJob2(){
        onView(withId(R.id.userNameBox)).perform(typeText("admin"));
        onView(withId(R.id.passwordBox)).perform(typeText("Admin1234"));
        onView(withId(R.id.signInButton)).perform(click());
        intending(hasComponent(Homepage.class.getName()));
        onView(withId(R.id.searchJobBar)).perform(typeText("checkinf"),closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click(),closeSoftKeyboard());
        onView(withId(R.id.listOfJobs)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listOfJobs)).atPosition(0).perform(click());
        intending(hasComponent(JobPage.class.getName()));
        onView(withId(R.id.takeJob)).perform(click(),closeSoftKeyboard());
    }
}
