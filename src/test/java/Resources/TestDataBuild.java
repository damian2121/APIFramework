package Resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace AddPlacePayload(String name, String language, String address) {
        AddPlace ap = new AddPlace();
        ap.setAccuracy(50);
        ap.setName(name);
        ap.setPhone_number("(+91) 983 893 3937");
        ap.setAddress(address);
        ap.setWebsite("http://google.com");
        ap.setLanguage(language);
        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");
        ap.setTypes(myList);

        Location loc = new Location();
        loc.setLat(-38.383494);
        loc.setLng(33.427362);
        ap.setLocation(loc);
        return ap;
    }
}
