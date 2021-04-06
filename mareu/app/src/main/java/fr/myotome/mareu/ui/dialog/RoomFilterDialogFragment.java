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
import fr.myotome.mareu.model.RoomName;

public class RoomFilterDialogFragment extends DialogFragment {
    public interface RoomFilterDialogListener{
        void getFilter(List<String> filter);
    }


    private final List<String> mFilter = new ArrayList<>();
    private List<CheckBox> mCheckBoxList = new ArrayList<>();
    private RoomFilterDialogListener mRoomFilterDialogFragment;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.room_filter_dialog_fragment, null);

        initialise(view);

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

    private void initialise(View view) {
        CheckBox room1 = view.findViewById(R.id.cb_room_filter_room1);
        room1.setText(RoomName.SAMUS.toString());
        CheckBox room2 = view.findViewById(R.id.cb_room_filter_room2);
        room2.setText(RoomName.MARIO.toString());
        CheckBox room3 = view.findViewById(R.id.cb_room_filter_room3);
        room3.setText(RoomName.LUIGI.toString());
        CheckBox room4 = view.findViewById(R.id.cb_room_filter_room4);
        room4.setText(RoomName.PEACH.toString());
        CheckBox room5 = view.findViewById(R.id.cb_room_filter_room5);
        room5.setText(RoomName.YOSHI.toString());
        CheckBox room6 = view.findViewById(R.id.cb_room_filter_room6);
        room6.setText(RoomName.SONIC.toString());
        CheckBox room7 = view.findViewById(R.id.cb_room_filter_room7);
        room7.setText(RoomName.BOWSER.toString());
        CheckBox room8 = view.findViewById(R.id.cb_room_filter_room8);
        room8.setText(RoomName.ZELDA.toString());
        CheckBox room9 = view.findViewById(R.id.cb_room_filter_room9);
        room9.setText(RoomName.LINK.toString());
        CheckBox room10 = view.findViewById(R.id.cb_room_filter_room10);
        room10.setText(RoomName.WARIO.toString());

        mCheckBoxList = Arrays.asList(room1, room2, room3, room4, room5, room6, room7, room8, room9, room10);
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