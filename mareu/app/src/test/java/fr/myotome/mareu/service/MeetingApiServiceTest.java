package fr.myotome.mareu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import fr.myotome.mareu.di.DI;
import fr.myotome.mareu.model.Meeting;
import fr.myotome.mareu.model.RoomName;

import static org.junit.jupiter.api.Assertions.*;

class MeetingApiServiceTest {

    private MeetingApiService mService;
    private List<Meeting> mFullMeetingTest;
    private List<Meeting> meetings;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        mService=DI.getMeetingApiService();
        mFullMeetingTest = FakeDataGenerator.generateMeeting();
        meetings = new ArrayList<>(mFullMeetingTest);
    }

    @org.junit.jupiter.api.Test
    void getFullMeeting() {
        meetings = mService.getFullMeeting();
        assertEquals(meetings,mFullMeetingTest);
    }

    @org.junit.jupiter.api.Test
    void deleteMeeting() {
        Meeting meetingToDelete = mService.getFullMeeting().get(0);
        mService.deleteMeeting(meetingToDelete);
        assertFalse(mService.getFullMeeting().contains(meetingToDelete));
    }

    @org.junit.jupiter.api.Test
    void addNewMeeting() {
        Meeting addMeetingTest = new Meeting(RoomName.ZELDA.toString(),"31 mars 2021", "9:00 AM", "10:00 AM", "Test",  Arrays.asList("arthur@lamzone.com", "karadoc@lamzone.com"));
        mService.addNewMeeting(addMeetingTest);
        List<Meeting> newFullMeeting = mService.getFullMeeting();
        assertTrue(newFullMeeting.contains(addMeetingTest));

    }

    @org.junit.jupiter.api.Test
    void getFilteredList() {
        //Test by date
        List<String> dateTest = Collections.singletonList("23 mars 2021");
        List<Meeting> filteredDateTest = mService.getFilteredList(dateTest);
        for (Meeting meeting : filteredDateTest) {
            assertEquals(meeting.getDate(), "23 mars 2021");
            assertNotEquals(meeting.getDate(), "25 mars 2021");
        }

        //Test by room
        List<String> roomTest = Collections.singletonList(RoomName.SAMUS.toString());
        List<Meeting> filteredRoomTest = mService.getFilteredList(roomTest);
        for (Meeting meeting : filteredRoomTest) {
            assertEquals(meeting.getName(), RoomName.SAMUS.toString());
            assertNotEquals(meeting.getName(), RoomName.MARIO.toString());
        }
    }
}