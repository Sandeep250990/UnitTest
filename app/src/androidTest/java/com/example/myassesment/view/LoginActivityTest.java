package com.example.myassesment.view;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.rule.ActivityTestRule;

import com.example.myassesment.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class LoginActivityTest {

    LoginActivity activity=null;
    Instrumentation.ActivityMonitor activityMonitor=getInstrumentation().addMonitor(MainActivity.class.getName(),null,false);

    @Rule
    public ActivityTestRule<LoginActivity>activityTestRule=new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setUp() {
        activity=activityTestRule.getActivity();

    }

    @Test
    public void checkValidEmail(){
        assertNotNull(activity.findViewById(R.id.editTextEmailAddress));
        assertTrue(EmailValidator.isValidEmail("adam.smith@test.com"));

    }

    @Test
    public void checkInValidEmail(){
        assertNotNull(activity.findViewById(R.id.editTextEmailAddress));
        assertFalse(EmailValidator.isValidEmail("adam@test"));

    }

    @Test
    public void checValidPwd(){
        assertNotNull(activity.findViewById(R.id.editTextPassword));
        assertTrue(PasswordValidator.isPasswordValidMethod("ADAM@129"));

    }

    @Test
    public void checkInvalidPwd(){
        assertNotNull(activity.findViewById(R.id.editTextPassword));
        assertFalse(PasswordValidator.isPasswordValidMethod("ADAM129"));

    }

    @Test
    public void checkButtonClickValidation(){
        assertNotNull(activity.findViewById(R.id.button_login));
        onView(withId(R.id.button_login)).perform(click());
        onView(withId(R.id.button_login)).check(ViewAssertions.matches(withText(R.string.login)));
        checkValidEmail();
        checValidPwd();
        Intent intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getInstrumentation().startActivitySync(intent);
        Activity mainActivity = getInstrumentation().waitForMonitor(activityMonitor);
        assertNotNull(mainActivity);

    }

    @After
    public void tearDown() {
        activity=null;
    }
}