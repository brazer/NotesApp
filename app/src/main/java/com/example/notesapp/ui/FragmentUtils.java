package com.example.notesapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Author: Anatol Salanevich
 * Date: 23.12.2015
 */
public class FragmentUtils {

    public static Fragment instantiateFragment(final Context context,
                                               final Class<? extends Fragment> fragmentClass,
                                               final Bundle args) {
        return Fragment.instantiate(context, fragmentClass.getName(), args);
    }

    public static Fragment instantiateFragment(final Context context,
                                               final Class<? extends Fragment> fragmentClass) {
        return Fragment.instantiate(context, fragmentClass.getName());
    }

    public static void replaceContent(final FragmentActivity activity,
                                      int content, final Fragment fragment,
                                      final String tag) {
        addFragment(activity, content, fragment, tag, false);
    }

    public static void addFragment(final FragmentActivity activity,
                                   int content, final Fragment fragment,
                                   final String tag, boolean addToBackStack) {
        final FragmentManager manager = activity.getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(content, fragment, tag);
        if (addToBackStack) {
            transaction.addToBackStack(tag);
        }
        transaction.commit();
    }

    public static void popFragmentOrFinishActivity(final FragmentActivity activity) {
        final FragmentManager manager = activity.getSupportFragmentManager();
        final int backStackEntries = manager.getBackStackEntryCount();
        if (backStackEntries > 1) {
            manager.popBackStack();
        } else {
            activity.finish();
        }
    }

}
