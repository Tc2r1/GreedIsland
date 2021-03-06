package com.tc2r.greedisland;


import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tc2r.greedisland.utils.PerformanceTracking;
import com.tc2r.greedisland.utils.RewardsHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreditsFragment extends Fragment {

    // Declare Layout Variables
    TextView creditTitle;

    public CreditsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_credits, container, false);
        creditTitle = (TextView) view.findViewById(R.id.credits_title);
        creditTitle = (TextView) view.findViewById(R.id.credits_text);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.secret);

        linearLayout.setOnClickListener(new SecretButton());
        return view;

    }

    // Secret button is a debugging button created to reset daily timers.
    private class SecretButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            PerformanceTracking.TrackEvent("SECRET BUTTON CLICKED");
            SharedPreferences userMap = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = userMap.edit();
            editor.putBoolean(getString(R.string.pref_can_travel_key), true);
            editor.putInt("Rewards", 0);
            editor.apply();
            RewardsHelper.bootAlarm(getContext());
            MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.tc_splash_intro);
            mp.setVolume(10, 10);
            mp.start();
        }
    }
}
