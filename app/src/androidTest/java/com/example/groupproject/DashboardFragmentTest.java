package com.example.groupproject;

import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import com.example.groupproject.ui.dashboard.DashboardFragment;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class DashboardFragmentTest {
    @Rule

    public ActivityTestRule<Homepage> activityRule = new ActivityTestRule<>(Homepage.class);

    @BeforeClass
    public static void setup() {
        Intents.init();
    }

    @Test
    public void checkIfDashboardIsEmpty() {
        onView(withId(R.id.navigation_dashboard)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.statusLabel2)).check(matches(withText("TextView")));
    }

    @Test
    public void checkIfJobNameIsEmpty() {
        onView(withId(R.id.navigation_dashboard)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.SubmitJob)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.statusLabel2)).check(matches(withText(R.string.Empty_Job_Name)));
    }

    @Test
    public void checkIfJobDescriptionisEmpty() {
        onView(withId(R.id.navigation_dashboard)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.jobName)).perform(typeText("job Name"), closeSoftKeyboard());
        onView(withId(R.id.SubmitJob)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.statusLabel2)).check(matches(withText(R.string.Empty_Desc)));
    }

    @Test
    public void checkIfPositionIsEmpty() {
        onView(withId(R.id.navigation_dashboard)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.jobName)).perform(typeText("job Name"), closeSoftKeyboard());
        onView(withId(R.id.Desc)).perform(typeText("job Desc"), closeSoftKeyboard());
        onView(withId(R.id.SubmitJob)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.statusLabel2)).check(matches(withText(R.string.Empty_Positions)));

    }

    @Test
    public void checkIfLatitudeIsEmpty() {
        onView(withId(R.id.navigation_dashboard)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.jobName)).perform(typeText("job Name"), closeSoftKeyboard());
        onView(withId(R.id.Desc)).perform(typeText("job Desc"), closeSoftKeyboard());
        onView(withId(R.id.Positions)).perform(typeText("22"), closeSoftKeyboard());
        onView(withId(R.id.Longitude)).perform(typeText("30"), closeSoftKeyboard());
        onView(withId(R.id.SubmitJob)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.statusLabel2)).check(matches(withText(R.string.Latitude)));
    }

    @Test
    public void checkIfLongitudeIsEmpty() {
        onView(withId(R.id.navigation_dashboard)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.jobName)).perform(typeText("job Name"), closeSoftKeyboard());
        onView(withId(R.id.Desc)).perform(typeText("job Desc"), closeSoftKeyboard());
        onView(withId(R.id.Positions)).perform(typeText("position"), closeSoftKeyboard());
        onView(withId(R.id.SubmitJob)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.statusLabel2)).check(matches(withText(R.string.Longitude)));
    }

    @Test
    public void checkIfPointsIsEmpty() {
        onView(withId(R.id.navigation_dashboard)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.jobName)).perform(typeText("job Name"), closeSoftKeyboard());
        onView(withId(R.id.Desc)).perform(typeText("job Desc"), closeSoftKeyboard());
        onView(withId(R.id.Positions)).perform(typeText("position"), closeSoftKeyboard());
        onView(withId(R.id.Latitude)).perform(typeText("latitude"), closeSoftKeyboard());
        onView(withId(R.id.Longitude)).perform(typeText("logitude"), closeSoftKeyboard());
        onView(withId(R.id.SubmitJob)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.statusLabel2)).check(matches(withText(R.string.Empty_Ratings)));
    }

}
