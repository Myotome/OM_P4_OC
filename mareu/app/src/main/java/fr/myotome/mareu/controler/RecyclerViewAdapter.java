package fr.myotome.mareu.controler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import fr.myotome.mareu.databinding.FragmentMeetingListBinding;
import fr.myotome.mareu.model.Meeting;
import fr.myotome.mareu.model.RoomName;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    private final List<Meeting> mMeetings;
    private final OnDeleteClickListener mListener;
    private FragmentMeetingListBinding mBinding;

    /**
     * Recycler view Constructor
     * @param meetings list of meetings will display
     */
    public RecyclerViewAdapter(List<Meeting> meetings, OnDeleteClickListener deleteClickListener) {
        mMeetings = meetings;
        mListener = deleteClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mBinding = FragmentMeetingListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(mBinding, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        holder.mMeeting.setText(meeting.toString());
        holder.mMail.setText(meeting.getParticipant().toString().replace("[", "").replace("]", ""));
        Glide.with(holder.mMeeting.getContext())
                .asBitmap()
                .load(RoomName.valueOf(meeting.getName()).getDrawable())
                .into(holder.mLogo);
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView mMeeting = mBinding.tvMeetingfragHeading;
        private final TextView mMail = mBinding.tvMeetingfragEmail;
        private final ImageView mLogo = mBinding.ivMeetingfragLogo;
        private final OnDeleteClickListener mDeleteClickListener;

        public ViewHolder(FragmentMeetingListBinding binding, OnDeleteClickListener deleteClickListener) {
            super(binding.getRoot());
            mBinding = binding;
            mDeleteClickListener = deleteClickListener;

            mBinding.ivMeetingfragDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mDeleteClickListener.onDeleteClick(getAdapterPosition());
        }
    }

}