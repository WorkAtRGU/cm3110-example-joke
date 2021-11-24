package uk.ac.rgu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JokeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JokeFragment extends Fragment implements View.OnClickListener {

    private String mJokeSetup;
    private String mPunchline;

    public JokeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment JokeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JokeFragment newInstance() {
        JokeFragment fragment = new JokeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // nothing to setup
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_joke, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // add on click listener to the reveal button
        Button revealButton = view.findViewById(R.id.btn_reveal);
        revealButton.setOnClickListener(this);

        // download a joke and update the ui
        downloadJokeUpdateUI();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_reveal){
            // launch the action to navigate to the punchline fragment
        }
    }

    /**
     * Downloads a joke, updates this object's member variables, and the UI
     */
    private void downloadJokeUpdateUI() {
    }
    
    private int generateRandomNumber(){
        // adated from https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java
        int max = 116;
        Random random = new Random();
        int value = random.nextInt(max);
        return value;
    }
    
}