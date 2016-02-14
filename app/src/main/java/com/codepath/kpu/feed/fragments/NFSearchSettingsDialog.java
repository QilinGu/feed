package com.codepath.kpu.feed.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.codepath.kpu.feed.R;

public class NFSearchSettingsDialog extends DialogFragment {

    public NFSearchSettingsDialog() {
        // Empty constructor is required for DialogFragment
    }

    public static NFSearchSettingsDialog newInstance() {
        NFSearchSettingsDialog frag = new NFSearchSettingsDialog();

        Bundle args = new Bundle();
        frag.setArguments(args);

        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_settings_dialog, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return view;
    }
}
