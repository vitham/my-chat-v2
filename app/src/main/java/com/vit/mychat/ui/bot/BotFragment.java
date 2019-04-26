package com.vit.mychat.ui.bot;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vit.mychat.R;
import com.vit.mychat.data.model.Tesst;
import com.vit.mychat.ui.base.BaseFragment;
import com.vit.mychat.remote.common.RxFirebase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class BotFragment extends BaseFragment {

    public static final String TAG = BotFragment.class.getSimpleName();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("friends").child("id-b");
    DatabaseReference myRefUser = database.getReference("users");
    public static BotFragment newInstance() {
        BotFragment fragment = new BotFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.bot_fragment;
    }
    List<Tesst> list = new ArrayList<>();
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        /*RxFirebase.dataChangeKeyList(myRef, "not")
                .flatMap(Observable::fromIterable)
                .flatMap(this::getTesst)
                .toList()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tessts -> {
                    Log.i(TAG, "onViewCreated: --------------");
                    for (Tesst tesst : tessts) Log.i(TAG, tesst.getName());
                });*/

        /*getFriendIdList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        .subscribe(strings -> {
            for (String s : strings) showToast(s);
        });*/





//        RxFirebaseDatabase.getList(myRef)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(dataSnapshot -> showToast(dataSnapshot.toString()),
//                        throwable -> showToast(throwable.getMessage()));


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> list = new ArrayList<>();
                List<Tesst> listTesst = new ArrayList<>();

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data.getValue(String.class).contains("not")) {
                        list.add(data.getKey());
                        Log.i("dataChangeKeyList", data.getKey());
                    }
                }

                for (String s : list) {
                    myRefUser.child(s).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Log.i("dataChange", dataSnapshot.getValue(Tesst.class).toString());
                            listTesst.add(dataSnapshot.getValue(Tesst.class));

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        }));

    }

    private Observable<Tesst> getTesst(String id) {
        return RxFirebase.dataChange(myRefUser.child(id), Tesst.class);
    }

}
