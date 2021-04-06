package fr.myotome.mareu.utils;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;


import org.hamcrest.Matcher;

import fr.myotome.mareu.R;

public class DeleteViewAction implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on specific button";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.iv_meetingfrag_delete);
        // Maybe check for null
        button.performClick();
    }
}
