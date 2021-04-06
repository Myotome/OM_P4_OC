package fr.myotome.mareu.utils;


import fr.myotome.mareu.R;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;

public class NewMeetingTest {


    public static void newMeetingTest() {
        // click on floating action button to open new activity
        onView(withId(R.id.fab_activity_list_meeting)).perform(click());

        //open spinner and select item at position 5
        onView(withId(R.id.sp_addmeeting_room)).perform(click());
        onData(anything()).atPosition(5).perform(click());

        //open date picker and accept default date
        onView(withId(R.id.tv_addmeeting_datepicker)).perform(click());
        onView(withId(android.R.id.button1)).perform(scrollTo(), click());

        //open start time picker and accept default time
        onView(withId(R.id.tv_addmeeting_startpicker)).perform(click());
        onView(withId(android.R.id.button1)).perform(scrollTo(), click());

        //open end time picker and accept default time
        onView(withId(R.id.tv_addmeeting_endpicker)).perform(click());
        onView(withId(android.R.id.button1)).perform(scrollTo(), click());

        //change subject's text
        onView(withId(R.id.et_addmeeting_subject)).perform(replaceText("sujet"), closeSoftKeyboard());

        //open windows with email list of participant and select 2 of them
        onView(withId(R.id.bt_addmeeting_email)).perform(click());
        onData(anything()).atPosition(0).perform(scrollTo(), click());
        onData(anything()).atPosition(3).perform(scrollTo(), click());
        onView(withId(android.R.id.button1)).perform(scrollTo(), click());

        // crate new meeting
        onView(withId(R.id.bt_addmeeting_add)).perform(click());
    }

}
