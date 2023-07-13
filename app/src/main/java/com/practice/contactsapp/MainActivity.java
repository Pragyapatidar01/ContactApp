package com.practice.contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private Router router;

    @Inject
    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        router = Conductor.attachRouter(this, findViewById(R.id.controller_container), savedInstanceState);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(new MainActivityController()));
        }
    }

    @Override
    public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
        }
    }

    /*public void replaceController(Controller newController, ControllerChangeHandler changeHandler) {
        router.replaceTopController(RouterTransaction.with(newController)
                .pushChangeHandler(changeHandler)
                .popChangeHandler(changeHandler));
    }*/

    public void pushNewController(Controller newController, ControllerChangeHandler changeHandler) {
        router.pushController(RouterTransaction.with(newController)
                .pushChangeHandler(changeHandler)
                .popChangeHandler(changeHandler));
    }

}