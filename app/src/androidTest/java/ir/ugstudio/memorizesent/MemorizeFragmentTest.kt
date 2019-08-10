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
import org.mockito.Mock

@RunWith(AndroidJUnit4::class)
class MemorizeFragmentTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testStartStateOfFragment() {
        onView(withId(R.id.memorize_button)).perform(click())
        onView(withId(R.id.show_next)).check(matches(isDisplayed()))
        onView(withId(R.id.show_next)).check(matches(withText(R.string.show_next)))
        onView(withId(R.id.sentence)).check(matches(withText("Hey Listeners, it’s Reid.")))
    }

}