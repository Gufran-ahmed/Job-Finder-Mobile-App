package com.example.groupproject;

import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class userProfileTest {
    @Rule

    public ActivityTestRule<Homepage> profileRule = new ActivityTestRule<>(Homepage.class);

    @Before
    public void init(){
        profileRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }
    @Test
    public void openUserProfile() {
        onView(withId(R.id.navigation_notifications)).perform(click());
        onView(withId(R.id.userProfil)).perform(click());
    }
    @Test
    public void logOut() {
        onView(withId(R.id.navigation_notifications)).perform(click());
        onView(withId(R.id.userProfil)).perform(click());
        onView(withId(R.id.logOutBtn)).perform(click());
    }
    @Test
    public void changeFirstName() {
        onView(withId(R.id.navigation_notifications)).perform(click());
        onView(withId(R.id.userProfil)).perform(click());
        onView(withId(R.id.newFirstName)).perform(typeText("admin"),closeSoftKeyboard());
        onView(withId(R.id.updateUserInfo)).perform(click());
    }

    @Test
    public void changeLastName() {
        onView(withId(R.id.navigation_notifications)).perform(click());
        onView(withId(R.id.userProfil)).perform(click());
        onView(withId(R.id.newLastName)).perform(typeText("admin"),closeSoftKeyboard());
        onView(withId(R.id.updateUserInfo)).perform(click());
    }

    @Test
    public void changePassword() {
        onView(withId(R.id.navigation_notifications)).perform(click());
        onView(withId(R.id.userProfil)).perform(click());
        onView(withId(R.id.newPassword)).perform(typeText("Admin1234"),closeSoftKeyboard());
        onView(withId(R.id.newPasswordConfirmation)).perform(typeText("Admin1234"),closeSoftKeyboard());
        onView(withId(R.id.updateUserInfo)).perform(click());
    }

}

