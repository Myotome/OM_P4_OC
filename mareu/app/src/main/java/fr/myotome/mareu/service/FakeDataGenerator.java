package fr.myotome.mareu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.myotome.mareu.model.Meeting;
import fr.myotome.mareu.model.RoomName;

public abstract class FakeDataGenerator {

    /**
     * Fake list to test application
     */
    private static final List<Meeting> FAKE_MEETING = Arrays.asList(
            new Meeting(RoomName.SAMUS.toString(),"23 mars 2021", "8:00 AM", "9:00 AM", "Quête du Graal", Arrays.asList("arthur@lamzone.com", "leodagan@lamzone.com","perceval@lamzone.com", "karadoc@lamzone.com")),
            new Meeting(RoomName.ZELDA.toString(),"23 mars 2021", "9:00 AM", "10:00 AM", "La table ronde",  Arrays.asList("arthur@lamzone.com", "leodagan@lamzone.com","perceval@lamzone.com", "karadoc@lamzone.com")),
            new Meeting(RoomName.SAMUS.toString(),"22 mars 2021", "3:00 PM", "3:30 PM", "Escalibur",  Arrays.asList("arthur@lamzone.com", "leodagan@lamzone.com","perceval@lamzone.com", "karadoc@lamzone.com")),
            new Meeting(RoomName.MARIO.toString(),"23 mars 2021", "9:00 AM", "10:00 AM", "La dame du lac",  Arrays.asList("arthur@lamzone.com", "leodagan@lamzone.com","perceval@lamzone.com", "karadoc@lamzone.com")),
            new Meeting(RoomName.LUIGI.toString(),"22 mars 2021", "10:00 AM", "11:00 AM", "Les révoltes paysannes",  Arrays.asList("arthur@lamzone.com", "leodagan@lamzone.com","perceval@lamzone.com", "karadoc@lamzone.com")),
            new Meeting(RoomName.PEACH.toString(),"20 mars 2021", "11:00 AM","12h00 AM", "La fête de l'hiver",  Arrays.asList("arthur@lamzone.com", "leodagan@lamzone.com","perceval@lamzone.com", "karadoc@lamzone.com")),
            new Meeting(RoomName.YOSHI.toString(),"20 mars 2021", "12:00 AM","1:00 PM", "La colère divine",  Arrays.asList("arthur@lamzone.com", "leodagan@lamzone.com","perceval@lamzone.com", "karadoc@lamzone.com")),
            new Meeting(RoomName.SONIC.toString(),"24 mars 2021", "1:00 PM","2:00 PM", "Les skavens",  Arrays.asList("arthur@lamzone.com", "leodagan@lamzone.com","perceval@lamzone.com", "karadoc@lamzone.com")),
            new Meeting(RoomName.BOWSER.toString(),"24 mars 2021", "2:00 PM","3:00 PM", "Le fléau de Dieu",  Arrays.asList("arthur@lamzone.com", "leodagan@lamzone.com","perceval@lamzone.com", "karadoc@lamzone.com")),
            new Meeting(RoomName.ZELDA.toString(),"25 mars 2021", "3:00 PM","4:00 PM", "Le négociateur",  Arrays.asList("arthur@lamzone.com", "leodagan@lamzone.com","perceval@lamzone.com", "karadoc@lamzone.com")),
            new Meeting(RoomName.LINK.toString(),"26 mars 2021", "4:00 PM","5:00 PM", "Taxes", Arrays.asList("arthur@lamzone.com", "leodagan@lamzone.com","perceval@lamzone.com", "karadoc@lamzone.com")),
            new Meeting(RoomName.WARIO.toString(),"2 avril 2021", "3:00 PM", "3:30 PM", "Combat", Arrays.asList("arthur@lamzone.com", "leodagan@lamzone.com","perceval@lamzone.com", "karadoc@lamzone.com"))
    );

    public static List<Meeting> generateMeeting(){
        return new ArrayList<>(FAKE_MEETING);
    }

}

