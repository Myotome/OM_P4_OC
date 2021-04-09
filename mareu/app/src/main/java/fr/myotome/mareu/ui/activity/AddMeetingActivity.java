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
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;

import java.util.Calendar;
import java.util.List;

import fr.myotome.mareu.databinding.ActivityAddMeetingBinding;
import fr.myotome.mareu.di.DI;
import fr.myotome.mareu.R;
import fr.myotome.mareu.controler.DatePickerFragment;
import fr.myotome.mareu.controler.TimePickerFragment;
import fr.myotome.mareu.model.Meeting;
import fr.myotome.mareu.model.RoomName;
import fr.myotome.mareu.ui.dialog.EmailListDialogFragment;

public class AddMeetingActivity extends AppCompatActivity implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener,
        AdapterView.OnItemSelectedListener,
        EmailListDialogFragment.onMultipleChoiceListener {

    private String mRoomName;
    private boolean mIsStart = true;
    private List<String> mParticipant;
    private ActivityAddMeetingBinding mBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        initialise();
        instanceSpinner();

    }

    private void initialise() {

        mBinding.tvAddmeetingDatepicker.setOnClickListener(this);
        mBinding.tvAddmeetingStartpicker.setOnClickListener(this);
        mBinding.tvAddmeetingEndpicker.setOnClickListener(this);
        mBinding.btAddmeetingCancel.setOnClickListener(this);
        mBinding.btAddmeetingAdd.setOnClickListener(this);
        mBinding.btAddmeetingEmail.setOnClickListener(this);

    }

    private void instanceSpinner() {

        ArrayAdapter<RoomName> roomNameAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, RoomName.values());
        roomNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBinding.spAddmeetingRoom.setAdapter(roomNameAdapter);
        mBinding.spAddmeetingRoom.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mRoomName = adapterView.getItemAtPosition(i).toString();
        Glide.with(view)
                .load(RoomName.valueOf(mRoomName).getDrawable())
                .into(mBinding.ivAddmeeting);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        mBinding.tvAddmeetingDatepicker.setText(java.text.DateFormat.getDateInstance().format(calendar.getTime()));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(0, 0, 0, hour, min);
        if (mIsStart) {
            mBinding.tvAddmeetingStartpicker.setText(DateFormat.format("hh:mm aa", calendar));
        } else {
            mBinding.tvAddmeetingEndpicker.setText(DateFormat.format("hh:mm aa", calendar));
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
        mBinding.tvAddmeetingEmail.setText(stringBuilder);
    }

    private void createNewMeeting() {
        String date = mBinding.tvAddmeetingDatepicker.getText().toString();
        String startTime = mBinding.tvAddmeetingStartpicker.getText().toString();
        String endTime = mBinding.tvAddmeetingEndpicker.getText().toString();
        String subject = mBinding.etAddmeetingSubject.getText().toString();

        if (date.length() != 0 && startTime.length() != 0 && endTime.length() != 0 && subject.length() != 0 && mParticipant != null) {
            Meeting meeting = new Meeting(mRoomName, date, startTime, endTime, subject, mParticipant);
            DI.getMeetingApiService().addNewMeeting(meeting);
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
                finish();
                break;
            case R.id.bt_addmeeting_add:
                createNewMeeting();

                break;

        }
    }
}