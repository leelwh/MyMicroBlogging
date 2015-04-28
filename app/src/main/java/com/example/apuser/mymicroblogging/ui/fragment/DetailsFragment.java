package com.example.apuser.mymicroblogging.ui.fragment;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.TextView;

import com.example.apuser.mymicroblogging.R;
import com.example.apuser.mymicroblogging.app.BaseFragment;
import com.example.apuser.mymicroblogging.ui.model.StatusModel;
import com.example.apuser.mymicroblogging.ui.presenter.DetailsPresenterImpl;
import com.example.apuser.mymicroblogging.ui.provider.StatusContract;
import com.example.apuser.mymicroblogging.ui.view.DetailsView;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * Created by apuser on 4/23/15.
 */
public class DetailsFragment extends BaseFragment implements DetailsView {
    @InjectView(R.id.list_item_text_user) TextView textUser;
    @InjectView(R.id.list_item_text_message) TextView textMessage;
    @InjectView(R.id.list_item_text_created_at) TextView textCreatedAt;

    @Inject DetailsPresenterImpl detailsPresenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        detailsPresenter.setView(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.list_item;
    }

    @Override
    public void onResume() {
        super.onResume();
        long id = getActivity().getIntent().getLongExtra(
                StatusContract.Column.ID, -1);

        updateView(id);
    }

    public void updateView(long id) {
        detailsPresenter.initialize(id);
    }

    @Override
    public void renderStatusDetailView(StatusModel statusModel) {
        if (statusModel != null) {
            textUser.setText(statusModel.getUser());
            textMessage.setText(statusModel.getMessage());
            textCreatedAt.setText(DateUtils.
                    getRelativeTimeSpanString(statusModel.getCreatedAt()));
        } else {
            textUser.setText("");
            textMessage.setText("");
            textCreatedAt.setText("");
        }
    }
}
