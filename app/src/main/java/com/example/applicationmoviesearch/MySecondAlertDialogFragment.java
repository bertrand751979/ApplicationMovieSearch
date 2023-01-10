package com.example.applicationmoviesearch;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MySecondAlertDialogFragment extends DialogFragment {
    private Button btnFinish;

    public MySecondAlertDialogFragment(){}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_popup_first_connexion,container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        btnFinish = view.findViewById(R.id.alertDialogBtnCancel);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                Toast.makeText(MySecondAlertDialogFragment.this.getContext(), "Cliqué", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static MySecondAlertDialogFragment newInstance(String title){
      MySecondAlertDialogFragment frag = new MySecondAlertDialogFragment();
      Bundle args = new Bundle();
      //args.putString("title",title);
      frag.setArguments(args);
        return frag;
    }




}
