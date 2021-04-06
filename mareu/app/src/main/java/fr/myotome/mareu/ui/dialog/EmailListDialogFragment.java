package fr.myotome.mareu.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

import fr.myotome.mareu.R;

public class EmailListDialogFragment extends DialogFragment {

    public interface onMultipleChoiceListener{
        void onPositiveButtonClicked(List<String> selectedEmailList);
    }

    private onMultipleChoiceListener mListener;
    private final List<String> mEmailParticipant = new ArrayList<>();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] emailList = getActivity().getResources().getStringArray(R.array.email_list);
        builder.setTitle(R.string.select)
                .setMultiChoiceItems(emailList, null, (dialogInterface, i, b) -> {
                    if(b){
                        mEmailParticipant.add(emailList[i]);
                    }else{
                        mEmailParticipant.remove(emailList[i]);
                    }
                })
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> mListener.onPositiveButtonClicked(mEmailParticipant))
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {

                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener= (onMultipleChoiceListener) context;
    }
}
