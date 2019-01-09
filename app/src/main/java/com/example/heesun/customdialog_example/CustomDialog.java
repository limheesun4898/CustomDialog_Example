package com.example.heesun.customdialog_example;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class CustomDialog extends Dialog {
    EditText name;
    TextView checkText;
    Button findBtn,cancel;
    private MyDialogListener dialogListener;

    public CustomDialog(Context context) {
        super(context);
    }

    public void setDialogListener(MyDialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_customdialog);

        name = findViewById(R.id.name);
        checkText = findViewById(R.id.check);
        findBtn = findViewById(R.id.findbtn);
        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stname = name.getText().toString();
                if (stname.isEmpty()) {
                    checkText.setVisibility(View.VISIBLE);

                } else {
                    checkText.setVisibility(View.GONE);
                    dialogListener.onPositiveClicked(stname);
                    dismiss();
                }

            }
        });


    }


}
