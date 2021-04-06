package fr.myotome.mareu.model;


import androidx.annotation.NonNull;

import java.util.List;

/**
 * Model object representing a meeting
 */
public class Meeting {

    /** Name of room */
    private final String mName;

    /** Date of meeting */
    private final String mDate;

    /** Start time of meeting*/
    private final String mStartTime;

    /** End time of meeting*/
    private final String mEndTime;

    /** Subject of meeting*/
    private final String mSubject;

    /** List of participant*/
    private final List<String> mParticipant;


    /**
     * constructor
     * @param name of room
     * @param date of meeting
     * @param startTime of meeting
     * @param endTime of meeting
     * @param subject of meeting
     * @param participant list of participant to meeting
     */
    public Meeting(String name, String date, String startTime, String endTime, String subject, List<String> participant) {
        mName = name;
        mDate = date;
        mStartTime = startTime;
        mEndTime = endTime;
        mSubject = subject;
        mParticipant = participant;
    }

    public String getName() {
        return mName;
    }


    public String getDate() {
        return mDate;
    }


    public List<String> getParticipant() {
        return mParticipant;
    }



    @NonNull
    @Override
    public String toString() {
        return mName + " - " + mStartTime + " - " + mSubject;
    }
}
