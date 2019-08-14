package ir.ugstudio.memorizesent

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScrambleFragmentTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testStartStateOfFragment() {
        onView(withId(R.id.scramble_button)).perform(click())
        onView(withId(R.id.scrambled_sentence)).check(matches(isDisplayed()))
        onView(withId(R.id.repeat_scrambled_finished)).check(matches(isDisplayed()))
        onView(withId(R.id.repeat_scrambled_finished)).check(matches(withText(R.string.repeat_finished)))
    }

    // todo, write a test to check whether the text on scrambled_sentence changes by clicking or not
}