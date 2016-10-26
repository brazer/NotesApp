package com.example.notesapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

/**
 * Author: Aliaksei Trafimchyk
 */
public class ActivityUtils {

    private ActivityUtils() {}

    public static void startActivity(AppCompatActivity activity,
                                     Class<? extends AppCompatActivity> activityClass) {
        startActivity(activity, activityClass, Bundle.EMPTY);
    }

    public static void startActivity(AppCompatActivity activity,
                                     Class<? extends AppCompatActivity> activityClass,
                                     Bundle args) {
        Intent intent = new Intent(activity, activityClass);
        if (args != null) {
            intent.putExtras(args);
        }
        activity.startActivity(intent);
    }

    public static void startActivityAndClearStack(Context context,
                                                  Class<? extends AppCompatActivity> activityClass) {
        Intent intent = new Intent(context, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public static void startActivityAndFinish(AppCompatActivity activity,
                                              Class<? extends AppCompatActivity> activityClass) {
        startActivityAndFinish(activity, activityClass, Bundle.EMPTY);
    }

    public static void startActivityAndFinish(AppCompatActivity activity,
                                              Class<? extends AppCompatActivity> activityClass,
                                              Bundle args) {
        Intent intent = new Intent(activity, activityClass);
        if (args != null) {
            intent.putExtras(args);
        }
        activity.startActivity(intent);
        activity.finish();
    }

    public static void startActivity(FragmentActivity activity,
                                     Class<? extends FragmentActivity> activityClass) {
        startActivity(activity, activityClass, Bundle.EMPTY);
    }

    public static void startActivity(FragmentActivity activity,
                                     Class<? extends FragmentActivity> activityClass,
                                     Bundle args) {
        Intent intent = new Intent(activity, activityClass);
        if (args != null) {
            intent.putExtras(args);
        }
        activity.startActivity(intent);
    }

    public static void startActivityForResult(AppCompatActivity activity,
                                              Class<? extends AppCompatActivity> activityClass,
                                              Bundle args, int requestCode) {
        Intent intent = new Intent(activity, activityClass);
        if (args != null) {
            intent.putExtras(args);
        }
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startActivityForResult(AppCompatActivity activity,
                                              Class<? extends AppCompatActivity> activityClass,
                                              int requestCode) {
        startActivityForResult(activity, activityClass, Bundle.EMPTY, requestCode);
    }

    public static void startActivityForResult(Fragment fragment, AppCompatActivity activity,
                                              Class<? extends AppCompatActivity> activityClass,
                                              Bundle args, int requestCode) {
        Intent intent = new Intent(activity, activityClass);
        if (args != null) {
            intent.putExtras(args);
        }
        fragment.startActivityForResult(intent, requestCode);
    }

}
