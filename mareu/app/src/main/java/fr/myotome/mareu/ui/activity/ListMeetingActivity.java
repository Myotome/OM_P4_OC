package fr.myotome.mareu.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import fr.myotome.mareu.di.DI;
import fr.myotome.mareu.R;
import fr.myotome.mareu.controler.RecyclerViewAdapter;
import fr.myotome.mareu.model.Meeting;
import fr.myotome.mareu.service.MeetingApiService;
import fr.myotome.mareu.ui.dialog.CalendarDialogFragment;
import fr.myotome.mareu.ui.dialog.RoomFilterDialogFragment;

public class ListMeetingActivity extends AppCompatActivity implements RecyclerViewAdapter.OnDeleteClickListener,
        CalendarDialogFragment.CalendarDialogListener,
        RoomFilterDialogFragment.RoomFilterDialogListener {


    private final MeetingApiService mApiService = DI.getMeetingApiService();
    private List<Meeting> mMeetings = mApiService.getFullMeeting();
//    private final List<Meeting> mFullMeetings = mApiService.getFullMeeting();
    private List<String> mFilteredList = new ArrayList<>();
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);
//        mMeetings = new ArrayList<>(mFullMeetings);
        initList();
        fabConfigure();

    }

    private void initList() {
        TextView mMessage = findViewById(R.id.tv_activity_list_meeting);
        if (mMeetings.size() == mApiService.getFullMeeting().size()) {
            mMessage.setVisibility(View.GONE);
        } else {
            mMessage.setVisibility(View.VISIBLE);
        }
        RecyclerView recyclerView = findViewById(R.id.rv_activity_list_meeting);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mMeetings, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
                DialogFragment calendarSearch = new CalendarDialogFragment();
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
        FloatingActionButton mAddMeeting = findViewById(R.id.fab_activity_list_meeting);
        mAddMeeting.setOnClickListener(view -> {
            Intent intent = new Intent(ListMeetingActivity.this, AddMeetingActivity.class);
            ListMeetingActivity.this.startActivity(intent);
        });
    }

    @Override
    public void onDeleteClick(int position) {
//        Meeting meeting = mFullMeetings.get(position);
        Meeting meeting = mMeetings.get(position);
        mApiService.deleteMeeting(meeting);
        initList();
    }


    /**
     * use calendar and roomFilter implementation
     * for obtain
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