package fr.myotome.mareu.service;


import java.util.List;

import fr.myotome.mareu.model.Meeting;

public interface MeetingApiService {

   /**
    * Get all meetings
    * @return fullList
    */
   List<Meeting> getFullMeeting();

   /**
    * Delete a meeting
    * @param meeting to delete
    */
   void deleteMeeting(Meeting meeting);

   /**
    * Create a meeting
    * @param meeting to create
    */
   void addNewMeeting(Meeting meeting);

   /**
    * Perform filtration by param
    * @param filter list of string to filter
    * @return filtered list of meeting
    */
   List<Meeting> getFilteredList(List<String> filter);

}
