package com.example.apuser.mymicroblogging.domain.repository.api.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by mikelee on 4/24/15.
 */
@Root
public class PostStatus {
    @Element(name = "status")
    String status;

    @Element(name = "lat", required = false)
    String latitude;

    @Element(name = "long", required = false)
    String longitude;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {

        return status;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
