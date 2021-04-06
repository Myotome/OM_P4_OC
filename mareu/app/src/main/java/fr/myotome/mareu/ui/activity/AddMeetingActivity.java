package fr.myotome.mareu.ui.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;

import java.util.Calendar;
import java.util.List;

import fr.myotome.mareu.di.DI;
import fr.myotome.mareu.R;
import fr.myotome.mareu.controler.DatePickerFragment;
import fr.myotome.mareu.controler.TimePickerFragment;
import fr.myotome.mareu.model.Meeting;
import fr.myotome.mareu.model.RoomName;
import fr.myotome.mareu.service.MeetingApiService;
import fr.myotome.mareu.ui.dialog.EmailListDialogFragment;

public class AddMeetingActivity extends AppCompatActivity implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener,
        AdapterView.OnItemSelectedListener,
        EmailListDialogFragment.onMultipleChoiceListener {

    private Spinner mRoomSpinner;
    private TextView mDisplayDate, mDisplayStartTime, mDisplayEndTime, mDisplayEmail;
    private EditText mSubject;
    private String mRoomName;
    private MeetingApiService mApiService;
    private boolean mIsStart = true;
    private List<String> mParticipant;
    private ImageView mLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        initialise();
        instanceSpinner();

    }

    private void initialise() {
        mApiService = DI.getMeetingApiService();

        mRoomSpinner = findViewById(R.id.sp_addmeeting_room);
        mDisplayDate = findViewById(R.id.tv_addmeeting_datepicker);
        mDisplayStartTime = findViewById(R.id.tv_addmeeting_startpicker);
        mDisplayEndTime = findViewById(R.id.tv_addmeeting_endpicker);
        mSubject = findViewById(R.id.et_addmeeting_subject);
        Button cancel = findViewById(R.id.bt_addmeeting_cancel);
        Button addMeeting = findViewById(R.id.bt_addmeeting_add);
        Button email = findViewById(R.id.bt_addmeeting_email);
        mDisplayEmail = findViewById(R.id.tv_addmeeting_email);
        mLogo = findViewById(R.id.iv_addmeeting);

        mDisplayDate.setOnClickListener(this);
        mDisplayStartTime.setOnClickListener(this);
        mDisplayEndTime.setOnClickListener(this);
        cancel.setOnClickListener(this);
        addMeeting.setOnClickListener(this);
        email.setOnClickListener(this);

    }

    private void instanceSpinner() {

        ArrayAdapter<RoomName> roomNameAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, RoomName.values());
        roomNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mRoomSpinner.setAdapter(roomNameAdapter);
        mRoomSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mRoomName = adapterView.getItemAtPosition(i).toString();
        Glide.with(view)
                .load(RoomName.valueOf(mRoomName).getDrawable())
                .into(mLogo);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        mDisplayDate.setText(java.text.DateFormat.getDateInstance().format(calendar.getTime()));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(0, 0, 0, hour, min);
        if (mIsStart) {
            mDisplayStartTime.setText(DateFormat.format("hh:mm aa", calendar));
        } else {
            mDisplayEndTime.setText(DateFormat.format("hh:mm aa", calendar));
        }

    }

    /**
     * Use EmailListDialog in implementation
     * @param selectedEmailList email selected
     */
    @Override
    public void onPositiveButtonClicked(List<String> selectedEmailList) {
        mParticipant = selectedEmailList;
        StringBuilder stringBuilder = new StringBuilder();
        for (String email : selectedEmailList) {
            stringBuilder.append(email).append(" ");
        }
        mDisplayEmail.setText(stringBuilder);
    }

    private void createNewMeeting() {
        String date = mDisplayDate.getText().toString();
        String startTime = mDisplayStartTime.getText().toString();
        String endTime = mDisplayEndTime.getText().toString();
        String subject = mSubject.getText().toString();

        if (date.length() != 0 && startTime.length() != 0 && endTime.length() != 0 && subject.length() != 0 && mParticipant != null) {
            Meeting meeting = new Meeting(mRoomName, date, startTime, endTime, subject, mParticipant);
            mApiService.addNewMeeting(meeting);
            Intent intent = new Intent(this, ListMeetingActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, getText(R.string.error), Toast.LENGTH_SHORT).show();
        }
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_addmeeting_datepicker:
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "dateFragment");
                break;
            case R.id.tv_addmeeting_startpicker:
                mIsStart = true;
                DialogFragment timeStartPicker = new TimePickerFragment();
                timeStartPicker.show(getSupportFragmentManager(), "timePicker");
                break;
            case R.id.tv_addmeeting_endpicker:
                mIsStart = false;
                DialogFragment timeEndPicker = new TimePickerFragment();
                timeEndPicker.show(getSupportFragmentManager(), "timePicker");
                break;
            case R.id.bt_addmeeting_email:
                DialogFragment emailList = new EmailListDialogFragment();
                emailList.show(getSupportFragmentManager(), "emailList");
                break;
            case R.id.bt_addmeeting_cancel:
                onBackPressed();
                break;
            case R.id.bt_addmeeting_add:
                createNewMeeting();

                break;

        }
    }
}