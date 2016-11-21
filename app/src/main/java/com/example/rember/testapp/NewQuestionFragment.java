package com.example.rember.testapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewQuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewQuestionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText editQuestion;
    EditText editAnswer1;
    EditText editAnswer2;
    EditText editAnswer3;
    EditText editAnswer4;
    EditText editAnswer5;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NewQuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewQuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewQuestionFragment newInstance(String param1, String param2) {
        NewQuestionFragment fragment = new NewQuestionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_new_question, container, false);
        View v = inflater.inflate(R.layout.fragment_new_question, container, false);
        editQuestion = (EditText) v.findViewById(R.id.editQuestion);
        editAnswer1 = (EditText) v.findViewById(R.id.editA1);
        editAnswer2 = (EditText) v.findViewById(R.id.editA2);
        editAnswer3 = (EditText) v.findViewById(R.id.editA3);
        editAnswer4 = (EditText) v.findViewById(R.id.editA4);
        editAnswer5 = (EditText) v.findViewById(R.id.editA5);
        Button btnCreate = (Button) v.findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnCreate:
                        String question = editQuestion.getText().toString();
                        String a1 = editAnswer1.getText().toString();
                        String a2 = editAnswer2.getText().toString();
                        String a3 = editAnswer3.getText().toString();
                        String a4 = editAnswer4.getText().toString();
                        String a5 = editAnswer5.getText().toString();
                        Question newQuestion = new Question(question, a1, a2, a3, a4, a5);

                        CharSequence text = "Question created!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(v.getContext(), text, duration);
                        toast.show();
                        break;
                }
            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
