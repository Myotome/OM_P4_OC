package fr.myotome.mareu.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

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
        DatePicker calendarView = v.findViewById(R.id.dp_calendar_frag);
        Calendar calendar = Calendar.getInstance();
        calendarView.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), (view, year, monthOfYear, dayOfMonth) -> {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Calendar.YEAR, year);
            calendar1.set(Calendar.MONTH, monthOfYear);
            calendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            mDateList.clear();
            mDate =java.text.DateFormat.getDateInstance().format(calendar1.getTime());
            mDateList.add(mDate);
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mCalendarDialogListener = (CalendarDialogListener) context;
    }
}
