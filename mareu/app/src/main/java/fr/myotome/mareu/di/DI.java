package fr.myotome.mareu.di;

import fr.myotome.mareu.service.FakeMeetingApiService;
import fr.myotome.mareu.service.MeetingApiService;

public class DI {

    /**
     * Dependency injector to get instance of service
     */
    private static final MeetingApiService service = new FakeMeetingApiService();

    public static MeetingApiService getMeetingApiService(){
        return service;
    }
}
