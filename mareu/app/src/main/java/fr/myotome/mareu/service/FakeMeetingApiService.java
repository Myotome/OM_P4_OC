package fr.myotome.mareu.service;

import java.util.ArrayList;
import java.util.List;

import fr.myotome.mareu.model.Meeting;

public class FakeMeetingApiService implements MeetingApiService{

    /**
     * use this one for test app with Fake List
     */
    private final List<Meeting> mFullMeetings = FakeDataGenerator.generateMeeting();

    /**
     * Use this one in final use, no list in beginning
     */
//    private final List<Meeting> mFullMeetings = new ArrayList<>();
    private List<Meeting> mMeetings = new ArrayList<>(mFullMeetings);


    @Override
    public List<Meeting> getFullMeeting() {
        return mFullMeetings;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
        mFullMeetings.remove(meeting);

    }

    @Override
    public void addNewMeeting(Meeting meeting) {
        mFullMeetings.add(meeting);

    }

    @Override
    public List<Meeting> getFilteredList(List<String> filter) {
        mMeetings.clear();
        if (filter == null) {
            mMeetings = new ArrayList<>(mFullMeetings);
        } else {
            for (String search : filter) {
                if (search == null) {
                    mMeetings = new ArrayList<>(mFullMeetings);
                } else {
                    for (Meeting meeting : getFullMeeting()) {
                        if (meeting.getDate().toLowerCase().contains(search.toLowerCase().trim()) ||
                                meeting.getName().toLowerCase().contains(search.toLowerCase().trim())) {
                            mMeetings.add(meeting);
                        }
                    }
                }
            }
        }
        return mMeetings;
    }

}
