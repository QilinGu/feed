package com.codepath.kpu.feed.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.codepath.kpu.feed.R;
import com.codepath.kpu.feed.models.NFSearchSettingsModel;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NFSearchSettingsDialog extends DialogFragment {

    @Bind(R.id.cbWorld)
    CheckBox cbWorld;

    @Bind(R.id.cbUS)
    CheckBox cbUS;

    @Bind(R.id.cbBusiness)
    CheckBox cbBusiness;

    @Bind(R.id.cbTech)
    CheckBox cbTech;

    @Bind(R.id.cbScience)
    CheckBox cbScience;

    @Bind(R.id.cbSports)
    CheckBox cbSports;

    @Bind(R.id.cbArts)
    CheckBox cbArts;

    @Bind(R.id.dpBeginDate)
    DatePicker dpBeginDate;

    @Bind(R.id.spSortOrder)
    Spinner spSortOrder;

    @Bind(R.id.btnSave)
    Button btnSave;

    @Bind(R.id.btnReset)
    Button btnReset;

    private NFSearchSettingsModel searchSettingsModel;
    private static final String SEARCH_SETTINGS_ARG = "search_settings";

    public interface SearchSettingsDialogListener {
        void onSaveSearchSettings(NFSearchSettingsModel searchSettingsModel);
    }

    public NFSearchSettingsDialog() {
        // Empty constructor is required for DialogFragment
    }

    public static NFSearchSettingsDialog newInstance(NFSearchSettingsModel searchSettingsModel) {
        NFSearchSettingsDialog frag = new NFSearchSettingsDialog();

        Bundle args = new Bundle();
        args.putParcelable(SEARCH_SETTINGS_ARG, searchSettingsModel);
        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            searchSettingsModel = getArguments().getParcelable(SEARCH_SETTINGS_ARG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_settings_dialog, container, false);
        ButterKnife.bind(this, view);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.updateViewWithSearchSettings(searchSettingsModel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchSettingsModel.selectedWorld = cbWorld.isChecked();
                searchSettingsModel.selectedUS = cbUS.isChecked();
                searchSettingsModel.selectedBusiness = cbBusiness.isChecked();
                searchSettingsModel.selectedTech = cbTech.isChecked();
                searchSettingsModel.selectedScience = cbScience.isChecked();
                searchSettingsModel.selectedSports = cbSports.isChecked();
                searchSettingsModel.selectedArts = cbArts.isChecked();

                searchSettingsModel.beginDateDay = dpBeginDate.getDayOfMonth();
                searchSettingsModel.beginDateMonth = dpBeginDate.getMonth();
                searchSettingsModel.beginDateYear = dpBeginDate.getYear();

                searchSettingsModel.sortOrder = (NFSearchSettingsModel.SortOrder) spSortOrder.getSelectedItem();

                SearchSettingsDialogListener listener = (SearchSettingsDialogListener) getActivity();
                listener.onSaveSearchSettings(searchSettingsModel);

                getDialog().dismiss();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchSettingsModel = new NFSearchSettingsModel();
                updateViewWithSearchSettings(searchSettingsModel);
            }
        });

        return view;
    }

    private void updateViewWithSearchSettings(NFSearchSettingsModel searchSettingsModel) {
        cbWorld.setChecked(searchSettingsModel.selectedWorld);
        cbUS.setChecked(searchSettingsModel.selectedWorld);
        cbBusiness.setChecked(searchSettingsModel.selectedBusiness);
        cbTech.setChecked(searchSettingsModel.selectedTech);
        cbScience.setChecked(searchSettingsModel.selectedScience);
        cbSports.setChecked(searchSettingsModel.selectedSports);
        cbArts.setChecked(searchSettingsModel.selectedArts);

        int beginYear = searchSettingsModel.beginDateYear;
        int beginMonth = searchSettingsModel.beginDateMonth;
        int beginDay = searchSettingsModel.beginDateDay;
        dpBeginDate.updateDate(beginYear, beginMonth, beginDay);

        spSortOrder.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, NFSearchSettingsModel.SortOrder.values()));
        spSortOrder.setSelection(searchSettingsModel.sortOrder.ordinal());
    }
}
