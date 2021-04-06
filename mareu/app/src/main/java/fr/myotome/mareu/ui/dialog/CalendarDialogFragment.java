package fr.myotome.mareu.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import fr.myotome.mareu.R;

public class CalendarDialogFragment extends DialogFragment {
    public interface CalendarDialogListener{
        void getFilter(List<String> date);
    }

    private final List<String> mDateList = new ArrayList<>();
    private String mDate;
    private CalendarDialogListener mCalendarDialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_calendar_search, null);
        calendarManagement(view);

        builder.setView(view)
                .setTitle(getText(R.string.select_date))
                .setNegativeButton(getText(R.string.cancel), (dialogInterface, i) -> {})
                .setNeutralButton(getText(R.string.clear), (dialogInterface, i) -> mCalendarDialogListener.getFilter(null))
                .setPositiveButton(getText(R.string.ok), (dialogInterface, i) -> mCalendarDialogListener.getFilter(mDateList));
        return builder.create();

    }

    private void calendarManagement(View v){
        CalendarView calendarView = v.findViewById(R.id.cv_calendar_frag);
        calendarView.setOnDateChangeListener((calendarView1, i, i1, i2) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, i);
            calendar.set(Calendar.MONTH, i1);
            calendar.set(Calendar.DAY_OF_MONTH, i2);
            mDateList.clear();
            mDate =java.text.DateFormat.getDateInstance().format(calendar.getTime());
            mDateList.add(mDate);
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mCalendarDialogListener = (CalendarDialogListener) context;
    }
}
