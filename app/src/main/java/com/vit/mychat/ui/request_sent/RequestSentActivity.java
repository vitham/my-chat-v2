package com.vit.mychat.ui.request_sent;

import android.app.Activity;
import android.content.Intent;

import com.vit.mychat.R;
import com.vit.mychat.ui.base.BaseActivity;
import com.vit.mychat.ui.profile.ProfileActivity;
import com.vit.mychat.ui.request_sent.adapter.RequestSentAdapter;
import com.vit.mychat.ui.request_sent.listener.OnClickRequestSentItemListener;

import javax.inject.Inject;

public class RequestSentActivity extends BaseActivity implements OnClickRequestSentItemListener {

    public static void moveRequestSentActivity(Activity activity) {
        Intent intent = new Intent(activity, RequestSentActivity.class);
        activity.startActivity(intent);
    }

    @Inject
    RequestSentAdapter requestSentAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.request_sent_activity;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onClickRequestSentItem(String userId) {
        ProfileActivity.moveProfileActivity(this, userId);
    }

    @Override
    public void onClickCacelRequest(String userId) {

    }
}