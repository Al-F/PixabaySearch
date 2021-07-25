package com.example.pixabaysearch

import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pixabaysearch.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ExampleInstrumentedTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.pixabaysearch", appContext.packageName)
    }

    @Test
    fun happyPath() {
        ActivityScenario.launch(MainActivity::class.java)
//
//        // Check Buttons fragment screen is displayed
//        onView(withId(R.id.textView)).check(matches(isDisplayed()))
//
//        // Tap on Button 1
//        onView(withId(R.id.button1)).perform(click())
//
//        // Navigate to Logs screen
//        onView(withId(R.id.all_logs)).perform(click())
//
//        // Check Logs fragment screen is displayed
//        onView(withText(containsString("Interaction with 'Button 1'")))
//            .check(matches(isDisplayed()))
    }
}
