package fr.myotome.mareu.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import fr.myotome.mareu.R;
import fr.myotome.mareu.controler.RecyclerViewAdapter;
import fr.myotome.mareu.databinding.ActivityListMeetingBinding;
import fr.myotome.mareu.di.DI;
import fr.myotome.mareu.model.Meeting;
import fr.myotome.mareu.service.MeetingApiService;
import fr.myotome.mareu.ui.dialog.DateFilterDialogFragment;
import fr.myotome.mareu.ui.dialog.RoomFilterDialogFragment;

public class ListMeetingActivity extends AppCompatActivity
        implements RecyclerViewAdapter.OnDeleteClickListener,
        DateFilterDialogFragment.CalendarDialogListener,
        RoomFilterDialogFragment.RoomFilterDialogListener {


    private final MeetingApiService mApiService = DI.getMeetingApiService();
    private List<Meeting> mMeetings = mApiService.getFullMeeting();
    private List<String> mFilteredList = new ArrayList<>();
    private ActivityListMeetingBinding mBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityListMeetingBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        initList();
        fabConfigure();

    }

    private void initList() {

        if (mMeetings.size() == mApiService.getFullMeeting().size()) {
            mBinding.tvActivityListMeeting.setVisibility(View.GONE);
        } else {
            mBinding.tvActivityListMeeting.setVisibility(View.VISIBLE);
        }
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mMeetings, this);
        mBinding.rvActivityListMeeting.setAdapter(adapter);
        mBinding.rvActivityListMeeting.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_date:
                DialogFragment calendarSearch = new DateFilterDialogFragment();
                calendarSearch.show(getSupportFragmentManager(), "calendarSearch");
                return true;
            case R.id.item_room:
                DialogFragment roomSearch = new RoomFilterDialogFragment();
                roomSearch.show(getSupportFragmentManager(), "roomSearch");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fabConfigure() {
        mBinding.fabActivityListMeeting.setOnClickListener(view -> {
            Intent intent = new Intent(ListMeetingActivity.this, AddMeetingActivity.class);
            ListMeetingActivity.this.startActivity(intent);
        });
    }

    @Override
    public void onDeleteClick(int position) {
        Meeting meeting = mMeetings.get(position);
        mApiService.deleteMeeting(meeting);
        initList();
    }


    /**
     * use calendar and roomFilter implementation
     * to obtain
     *
     * @param filter list of string
     */
    @Override
    public void getFilter(List<String> filter) {
        if (mFilteredList != null) {
            mFilteredList.clear();
        }
        mFilteredList = filter;
        mMeetings = mApiService.getFilteredList(mFilteredList);
        initList();
    }
}