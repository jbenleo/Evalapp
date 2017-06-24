package com.example.jbleo.evalapp.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jbleo.evalapp.R;
import com.example.jbleo.evalapp.model.Client;

import java.util.Date;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Client} and makes a call to the
 * specified {@link ClientsFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ClientRecyclerViewAdapter extends RecyclerView.Adapter<ClientRecyclerViewAdapter.ViewHolder> {

    private final List<Client> mValues;
    private final ClientsFragment.OnListFragmentInteractionListener mListener;

    public ClientRecyclerViewAdapter(List<Client> items, ClientsFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_client, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mClient = mValues.get(position);
        holder.mName.setText(mValues.get(position).getName());
        holder.mBirthday.setText(new Date(mValues.get(position).getBirthday()).toString());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mClient);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void addItem(Client client){
        mValues.add(client);
        this.notifyItemInserted(mValues.size() - 1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mName;
        public final TextView mBirthday;
        public Client mClient;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mName = (TextView) view.findViewById(R.id.id);
            mBirthday = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mBirthday.getText() + "'";
        }
    }
}
