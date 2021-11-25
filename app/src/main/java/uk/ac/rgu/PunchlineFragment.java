package uk.ac.rgu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PunchlineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PunchlineFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PUNCHLINE = "arg_punchline";

    // TODO: Rename and change types of parameters
    private String mPunchline;

    public PunchlineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment PunchlineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PunchlineFragment newInstance(String param1) {
        PunchlineFragment fragment = new PunchlineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PUNCHLINE, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPunchline = getArguments().getString(ARG_PUNCHLINE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_punchline, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // setup the punchline TextView
        TextView tvPunchline = view.findViewById(R.id.tv_punchline);
        tvPunchline.setText(mPunchline);


        Button btnNext = view.findViewById(R.id.btn_next_joke);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // when the next joke button is clicked switch back to the joke fragment
        Navigation.findNavController(view).navigate(R.id.action_punchlineFragment_to_jokeFragment);
    }
}