package com.example.rember.testapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_list_item_1;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyQuestionsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyQuestionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyQuestionsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView txt;
    ListView listview;
    View v;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MyQuestionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyQuestionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyQuestionsFragment newInstance(String param1, String param2) {
        MyQuestionsFragment fragment = new MyQuestionsFragment();
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

        //return inflater.inflate(R.layout.fragment_my_questions, container, false);
        v = inflater.inflate(R.layout.fragment_my_questions, container, false);

        txt = (TextView) v.findViewById(R.id.txtTest);
        listview = (ListView) v.findViewById(R.id.listQuestions);
        //to access database from mainactivity
        MainActivity mainActivity = (MainActivity)getActivity();

        Cursor resultSet = mainActivity.mydatabase.rawQuery("Select * from Questions",null);
        ///Cursor resultSet = mainActivity.dbHandler.getReadableDatabase().rawQuery("Select * from Questions",null);

        List<String> questionsList = new ArrayList<String>();

        if (resultSet.moveToFirst()) {
            do {
                String question = resultSet.getString(0);
                questionsList.add(question);

            } while (resultSet.moveToNext());
        }

        ((ListView) v.findViewById(R.id.listQuestions)).setAdapter(
                new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_list_item_1, questionsList));

        /*String[] array = new String[10];
        for (int i = 0; i < 10; i++) { array[i] = "Question " + i; }
        ((ListView) v.findViewById(R.id.listQuestions)).setAdapter(
                new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_list_item_1, array));*/

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent i = new Intent(v.getContext(), DetailActivity.class);
                i.putExtra("selectedQuestion", position);
                //i.putExtra("selectedPlayer", players[position]);
                startActivity(i);

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
