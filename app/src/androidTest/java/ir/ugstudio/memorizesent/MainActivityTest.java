package ir.ugstudio.memorizesent;

import androidx.test.espresso.assertion.PositionAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkMemorizeButton() {
        onView(withId(R.id.memorize_button)).check(matches(isDisplayed()));
        onView(withId(R.id.memorize_button)).check(matches(isClickable()));
        onView(withId(R.id.memorize_button)).check(matches(withText(R.string.start_memorize)));
    }

    @Test
    public void checkScrambleButton() {
        onView(withId(R.id.scramble_button)).check(matches(isDisplayed()));
        onView(withId(R.id.scramble_button)).check(matches(isClickable()));
        onView(withId(R.id.scramble_button)).check(matches(withText(R.string.start_scramble)));
    }

    @Test
    public void checkRelationOfMemorizeAndScrambled() {
        onView(withId(R.id.scramble_button)).check(PositionAssertions.isCompletelyBelow(withId(R.id.memorize_button)));
    }

    @Test
    public void checkMemorizeFragmentOpens_when_memorizeButtonIsClicked() {
        onView(withId(R.id.memorize_button)).perform(click());
        onView(allOf(withId(R.id.show_next), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
                    .check(matches(isDisplayed()));
    }
}
