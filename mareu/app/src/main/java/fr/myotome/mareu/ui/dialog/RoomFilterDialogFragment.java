package fr.myotome.mareu.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.myotome.mareu.R;
import fr.myotome.mareu.databinding.FragmentRoomFilterBinding;
import fr.myotome.mareu.model.RoomName;

public class RoomFilterDialogFragment extends DialogFragment {
    public interface RoomFilterDialogListener{
        void getFilter(List<String> filter);
    }


    private final List<String> mFilter = new ArrayList<>();
    private List<CheckBox> mCheckBoxList = new ArrayList<>();
    private RoomFilterDialogListener mRoomFilterDialogFragment;
    private FragmentRoomFilterBinding mBinding;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        mBinding = FragmentRoomFilterBinding.inflate(LayoutInflater.from(getContext()));
        View view = mBinding.getRoot();

        initialise();

        builder.setView(view)
                .setTitle(getText(R.string.room))
                .setNegativeButton(getText(R.string.cancel), (dialog, which) -> {

                })
                .setPositiveButton(getText(R.string.ok), (dialog, which) -> {
                    ifIsChecked();
                    if(mFilter.isEmpty()){
                        mRoomFilterDialogFragment.getFilter(null);
                    }else{
                        mRoomFilterDialogFragment.getFilter(mFilter);
                    }
                });
        return builder.create();
    }

    private void initialise() {
        mBinding.cbRoomFilterRoom1.setText(RoomName.SAMUS.toString());
        mBinding.cbRoomFilterRoom2.setText(RoomName.MARIO.toString());
        mBinding.cbRoomFilterRoom3.setText(RoomName.LUIGI.toString());
        mBinding.cbRoomFilterRoom4.setText(RoomName.PEACH.toString());
        mBinding.cbRoomFilterRoom5.setText(RoomName.YOSHI.toString());
        mBinding.cbRoomFilterRoom6.setText(RoomName.SONIC.toString());
        mBinding.cbRoomFilterRoom7.setText(RoomName.BOWSER.toString());
        mBinding.cbRoomFilterRoom8.setText(RoomName.ZELDA.toString());
        mBinding.cbRoomFilterRoom9.setText(RoomName.LINK.toString());
        mBinding.cbRoomFilterRoom10.setText(RoomName.WARIO.toString());

        mCheckBoxList = Arrays.asList(mBinding.cbRoomFilterRoom1,
                mBinding.cbRoomFilterRoom2,
                mBinding.cbRoomFilterRoom3,
                mBinding.cbRoomFilterRoom4,
                mBinding.cbRoomFilterRoom5,
                mBinding.cbRoomFilterRoom6,
                mBinding.cbRoomFilterRoom7,
                mBinding.cbRoomFilterRoom8,
                mBinding.cbRoomFilterRoom9,
                mBinding.cbRoomFilterRoom10);
    }

    private void ifIsChecked() {
        for (CheckBox checkBox : mCheckBoxList){
            if(checkBox.isChecked()){
                mFilter.add(checkBox.getText().toString());
            }else{
                mFilter.remove(checkBox.getText().toString());
            }
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mRoomFilterDialogFragment = (RoomFilterDialogListener) context;
    }
}