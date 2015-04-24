package com.example.apuser.mymicroblogging.domain.repository.api.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by apuser on 4/23/15.
 */
@Root(strict=false, name = "statuses")
public class ResponseStatuses {
    @ElementList(inline = true)
    private List<ResponseStatus> statuses;

    public List<ResponseStatus> getStatuses() {

        return statuses;
    }

    public void setStatuses(List<ResponseStatus> statuses) {
        this.statuses = statuses;
    }
}
