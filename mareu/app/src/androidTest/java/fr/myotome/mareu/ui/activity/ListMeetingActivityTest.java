package fr.myotome.mareu.ui.activity;


import androidx.test.ext.junit.runners.AndroidJUnit4;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;


import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.myotome.mareu.R;
import fr.myotome.mareu.utils.DeleteViewAction;
import fr.myotome.mareu.utils.NewMeetingTest;
import fr.myotome.mareu.utils.RecyclerViewItemCountAssertion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListMeetingActivityTest {

    @Rule
    public ActivityScenarioRule<ListMeetingActivity> mActivityTestRule = new ActivityScenarioRule<>(ListMeetingActivity.class);

    @Test
    public void deleteMeeting(){
        //before : check list = 13
        onView(ViewMatchers.withId(R.id.rv_activity_list_meeting)).check(RecyclerViewItemCountAssertion.withItemCount(13));

        // perform delete
        onView(ViewMatchers.withId(R.id.rv_activity_list_meeting)).perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));

        // after : check list = 12
        onView(ViewMatchers.withId(R.id.rv_activity_list_meeting)).check(RecyclerViewItemCountAssertion.withItemCount(12));


    }

    @Test
    public void createMeeting_and_addToMainList(){
        //before : check list = 12
        onView(withId(R.id.rv_activity_list_meeting)).check(RecyclerViewItemCountAssertion.withItemCount(12));

        // switch to new activity
        NewMeetingTest.newMeetingTest();

        // after : check list = 13
        onView(ViewMatchers.withId(R.id.rv_activity_list_meeting)).check(RecyclerViewItemCountAssertion.withItemCount(13));
    }

    @Test
    public void dateFilter_displayMainList(){
        //before : check list = 13 and no message display
        onView(ViewMatchers.withId(R.id.rv_activity_list_meeting)).check(RecyclerViewItemCountAssertion.withItemCount(13));
        onView(withId(R.id.tv_activity_list_meeting)).check(matches(not(isDisplayed())));

        // filtering list with no date
        onView(withId(R.id.item_menu)).perform(click());
        onView(allOf(withId(R.id.title), withText("Filtre par date"))).perform(click());
        onView(withId(android.R.id.button1)).perform(scrollTo(), click());

        // check filtering list is empty and message "filtered list" is visible
        onView(ViewMatchers.withId(R.id.rv_activity_list_meeting)).check(RecyclerViewItemCountAssertion.withItemCount(0));
        onView(withId(R.id.tv_activity_list_meeting)).check(matches(isDisplayed()));
    }

    @Test
    public void roomFilter_displayMainList(){
        //before : check list = 12
        onView(ViewMatchers.withId(R.id.rv_activity_list_meeting)).check(RecyclerViewItemCountAssertion.withItemCount(12));

        // perform filtration with 2 rooms
        onView(withId(R.id.item_menu)).perform(click());
        onView(Matchers.allOf(withId(R.id.title), withText("Filtre par salle"))).perform(click());
        onView(withId(R.id.cb_room_filter_room1)).perform(click());
        onView(withId(R.id.cb_room_filter_room8)).perform(click());
        onView(withId(android.R.id.button1)).perform(scrollTo(), click());

        // after : check list = 4
        onView(ViewMatchers.withId(R.id.rv_activity_list_meeting)).check(RecyclerViewItemCountAssertion.withItemCount(4));
    }



}
