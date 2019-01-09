package com.example.heesun.customdialog_example;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button alert_dialog, progrss_dialog, date_dialog, time_dialog, edit_dialog;
    Context context = this;
    TextView edit_text;
    public static int TIME_OUT = 1001;
    ProgressDialog progressDialog;

    final int DIALOG_DATE = 1;
    final int DIALOG_TIME = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alert_dialog = findViewById(R.id.alert_dialog);
        progrss_dialog = findViewById(R.id.progrss_dialog);
        date_dialog = findViewById(R.id.date_dialog);
        time_dialog = findViewById(R.id.time_dialog);
        edit_dialog = findViewById(R.id.edit_dialog);
        edit_text = findViewById(R.id.edit_text);

        alert_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("종료")
                        .setMessage("앱을 종료하시겠습니까?")
                        //긍정
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                            //setNeutralButton() 중립
                            //부정
                        }).setNegativeButton("취소", null).show();
            }
        });

        progrss_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(context, "Title", "Message");

                // 정해진 시간 후에는 타임아웃 발생을 위해 핸들러로 메시지 전달
                mHandler.sendEmptyMessageDelayed(TIME_OUT, 2000);
            }
        });


        date_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_DATE);
            }
        });

        time_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_TIME);
            }
        });

        edit_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog dialog = new CustomDialog(context);
                dialog.setDialogListener(new MyDialogListener() {
                    @Override
                    public void onPositiveClicked(String name) {
                        edit_text.setVisibility(View.VISIBLE);
                        edit_text.setText(name);
                    }

                    @Override
                    public void onNegativeClicked() {

                    }
                });
                dialog.show();
            }
        });


    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == TIME_OUT) {        // 타임아웃이 발생하면
                progressDialog.dismiss();    // ProgressDialog를 종료
            }
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        //Calendar를 이용하여 년, 월, 일, 시간, 분을 PICKER에 넣어준다.
        final Calendar cal = Calendar.getInstance();

        switch (id) {
            case DIALOG_DATE:
                DatePickerDialog dpd = new DatePickerDialog
                        (MainActivity.this, // 현재화면의 제어권자
                                new DatePickerDialog.OnDateSetListener() {
                                    public void onDateSet(DatePicker view,
                                                          int year, int month, int day) {

                                    }
                                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)); // 기본값 연월일
                return dpd;
            case DIALOG_TIME:
                TimePickerDialog tpd =
                        new TimePickerDialog(MainActivity.this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view,
                                                          int hour, int minute) {

                                    }
                                }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true); // 기본값 시분 등록
                // true : 24 시간(0~23) 표시
                // false : 오전/오후 항목이 생김
                return tpd;
        }
        return super.onCreateDialog(id);
    }

}
