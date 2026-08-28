package pojo.CreateAlertAPI;

import CreateEvent.CreateEvent;

import java.util.ArrayList;
import java.util.List;

public class CreateDataForCreateAlert {



    public static CreateEvent CreateEventJsonTestdata()
    {
        CreateEvent event = new CreateEvent(
                "Testonr testgdassa",
                "ssds",
                "Conference",
                "india",
                "Bengaluru",
                "2026-08-29T11:49:00.000Z",
                545,
                343
        );

        return event;

    }

}
