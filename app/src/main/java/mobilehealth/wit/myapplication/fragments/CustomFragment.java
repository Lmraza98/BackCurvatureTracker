package mobilehealth.wit.myapplication.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import androidx.fragment.app.Fragment;

@SuppressLint("ValidFragment")
public class CustomFragment extends Fragment {


    protected Activity parentActivity;

    @SuppressLint("ValidFragment")
    public CustomFragment(final Activity parentActivity) {
        this.parentActivity = parentActivity;
    }

    public Activity getParentActivity() {
        return parentActivity;
    }

    public void setParentActivity(Activity parentActivity) {
        this.parentActivity = parentActivity;
    }
}
