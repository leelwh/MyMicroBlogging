package com.example.apuser.mymicroblogging.domain.repository;

import com.example.apuser.mymicroblogging.domain.model.Status;

import java.util.List;

/**
 * Created by apuser on 4/23/15.
 */
public interface StatusRepository {
    List<Status> getStatusCollection();
}
