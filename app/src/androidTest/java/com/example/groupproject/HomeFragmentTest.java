package com.example.groupproject;

import android.widget.ListView;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.groupproject.ui.home.HomeFragment;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.util.EnumSet.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class HomeFragmentTest {
    @Rule

    public ActivityTestRule<Homepage> activityRule = new ActivityTestRule<>(Homepage.class);



    @BeforeClass
    public static void setup() {
        Intents.init();
    }

    @Test
    public void CheckSearchKeyWordIsShown() {
        onView(withId(R.id.searchJobBar)).check(matches(withText("")));
        onView(withId(R.id.searchButton)).check(matches(withText("SEARCH")));
    }

    @Test
    public void CheckSearchJobIsInValid() {
        onView(withId(R.id.searchJobBar)).perform(typeText("teacher"),closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click(),closeSoftKeyboard());
        onView(withId(R.id.listOfJobs)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listOfJobs)).atPosition(0);
        intending(hasComponent(Homepage.class.getName()));



    }

    @Test
    public void CheckSearchJobIsEmpty() {
        onView(withId(R.id.searchJobBar)).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(closeSoftKeyboard());
        onView(withId(R.id.listOfJobs)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listOfJobs));

    }



}