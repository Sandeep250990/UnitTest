package com.example.myassesment.view;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

public class MainActivityTest {

    MainActivity activity=null;

    @Rule
    ActivityTestRule<MainActivity> activityTestRule=new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp()  {
        activity=activityTestRule.getActivity();
    }

    @After
    public void tearDown() {
        activity=null;
    }
}