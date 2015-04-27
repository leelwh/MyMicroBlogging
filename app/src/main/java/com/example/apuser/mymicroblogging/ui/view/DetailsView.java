package com.example.apuser.mymicroblogging.ui.view;

import com.example.apuser.mymicroblogging.ui.model.StatusModel;

/**
 * Created by apuser on 4/23/15.
 */
public interface DetailsView extends View {
    void renderStatusDetailView(StatusModel statusModel);
}
