package com.example.jbleo.evalapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jbleo.evalapp.R;
import com.example.jbleo.evalapp.TestTemplateActivity;
import com.example.jbleo.evalapp.model.TestTemplate;
import com.example.jbleo.evalapp.model.TextTestTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TestTemplateFragment extends Fragment implements ValueEventListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private OnAddButtonInteractionListener mAddListener;
    private TestTemplateRecyclerViewAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TestTemplateFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TestTemplateFragment newInstance(int columnCount) {
        TestTemplateFragment fragment = new TestTemplateFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_testtemplate_list, container, false);

        // Set the adapter
        if (view.findViewById(R.id.list) instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
            Context context = recyclerView.getContext();
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            mAdapter = new TestTemplateRecyclerViewAdapter(new ArrayList<TestTemplate>(), mListener);
            recyclerView.setAdapter(mAdapter);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            database
                .getReference("users/"+ FirebaseAuth.getInstance().getCurrentUser().getUid() + "/tests")
                .addValueEventListener(this);
        }
        if (view.findViewById(R.id.fab) instanceof FloatingActionButton) {
            FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAddListener.onAddButtonInteractionListener();
                }
            });
        }

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
        if (context instanceof OnAddButtonInteractionListener) {
            mAddListener = (OnAddButtonInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAddButtonInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        mAddListener = null;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        // List
        if ((dataSnapshot != null) && (dataSnapshot.getKey().compareTo("tests")==0)){
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("tests");
            for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()){
                String test_id = messageSnapshot.getKey();
                myRef.child(test_id).addValueEventListener(this);
                Log.d("INFO", test_id);
            }
        } else {
            // Parse dataSnapshot
            TestTemplate testTemplate;
            if (((String) dataSnapshot.child("type").getValue()).compareTo("TextTestTemplate")==0){
                testTemplate = (TestTemplate) dataSnapshot.getValue(TextTestTemplate.class);
            } else {
                testTemplate = (TestTemplate) dataSnapshot.getValue(TestTemplate.class);
            }
            testTemplate.setUuid(dataSnapshot.getKey());
            mAdapter.addItem(testTemplate);
            Log.d("INFO", testTemplate.toString());
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(TestTemplate item);
    }

    public interface OnAddButtonInteractionListener {
        void onAddButtonInteractionListener();
    }
}
