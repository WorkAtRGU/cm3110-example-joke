package uk.ac.rgu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JokeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JokeFragment extends Fragment implements View.OnClickListener {

    private String mJokeSetup;
    private String mPunchline;
    private TextView mTvSetup;

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

        mTvSetup = view.findViewById(R.id.tv_joke);

        // download a joke and update the ui
        downloadJokeUpdateUI();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_reveal){
            // launch the action to navigate to the punchline fragment
            Bundle bundle = new Bundle();
            bundle.putString("arg_punchline", mPunchline);

            Navigation.findNavController(view).navigate(R.id.action_jokeFragment_to_punchlineFragment, bundle);
        }
    }

    /**
     * Downloads a joke, updates this object's member variables, and the UI
     */
    private void downloadJokeUpdateUI() {
        String url = String.format("https://cm3110-2021-jokes-default-rtdb.europe-west1.firebasedatabase.app/%o.json", generateRandomNumber());
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            mJokeSetup = obj.getString("setup");
                            mPunchline = obj.getString("punchline");
                            mTvSetup.setText(mJokeSetup);
                        } catch (JSONException e){
                            mTvSetup.setText("Error processing json");
                            Log.d("JOKE", e.getLocalizedMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTvSetup.setText("Error downloading json");
                Log.d("JOKE", error.getLocalizedMessage());
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }


    /**
     * Generates a random number between 0 and 115 inclusive.
     * @return a random number between 0 and 115 inclusiv2
     */
    private int generateRandomNumber(){
        // adated from https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java
        int max = 116;
        Random random = new Random();
        int value = random.nextInt(max);
        return value;
    }
    
}