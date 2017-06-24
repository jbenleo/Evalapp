package com.example.jbleo.evalapp.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jbleo.evalapp.R;
import com.example.jbleo.evalapp.fragment.TestTemplateFragment.OnListFragmentInteractionListener;
import com.example.jbleo.evalapp.model.TestTemplate;

import junit.framework.Test;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link TestTemplate} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class TestTemplateRecyclerViewAdapter extends RecyclerView.Adapter<TestTemplateRecyclerViewAdapter.ViewHolder> {

    private final List<TestTemplate> mValues;
    private final OnListFragmentInteractionListener mListener;

    public TestTemplateRecyclerViewAdapter(List<TestTemplate> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_testtemplate, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).getTitle());
        holder.mDescriptionView.setText(mValues.get(position).getDescription());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void addItem(TestTemplate test) {
        mValues.add(test);
        this.notifyItemInserted(mValues.size()-1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final TextView mDescriptionView;
        public TestTemplate mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.title);
            mDescriptionView = (TextView) view.findViewById(R.id.description);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDescriptionView.getText() + "'";
        }
    }
}
