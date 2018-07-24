package com.fwc.demohouse;

import android.app.Dialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.fwc.library.dialog.SweetAlertDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import xyz.bboylin.universialtoast.UniversalToast;

import static xyz.bboylin.universialtoast.UniversalToast.EMPHASIZE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_progress_dialog;
    private Button btn_success;
    private Button btn_error;
    private Button btn_basic_test;
    private Button btn_under_text_test;
    private Button btn_warning_confirm_test;
    private Button btn_warning_cancel_test;
    private Button btn_custom_img_test;
    private Button btn_select_photo;
    private Button btn_look_photo;
    private Button btn_select_time;
    private Button btn_select_address;

    private List<LocalMedia> selectList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ImgAdapter adapter;

    private TimePickerView pvTime, pvCustomTime, pvCustomLunar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {

        initTimePicker();

        btn_progress_dialog = findViewById(R.id.btn_progress_dialog);
        btn_success = findViewById(R.id.btn_success);
        btn_error = findViewById(R.id.btn_error);
        btn_basic_test = findViewById(R.id.btn_basic_test);
        btn_under_text_test = findViewById(R.id.btn_under_text_test);
        btn_warning_confirm_test = findViewById(R.id.btn_warning_confirm_test);
        btn_warning_cancel_test = findViewById(R.id.btn_warning_cancel_test);
        btn_custom_img_test = findViewById(R.id.btn_custom_img_test);
        btn_select_photo = findViewById(R.id.btn_select_photo);
        btn_look_photo = findViewById(R.id.btn_look_photo);
        btn_select_time = findViewById(R.id.btn_select_time);
        btn_select_address = findViewById(R.id.btn_select_address);


        btn_progress_dialog.setOnClickListener(this);
        btn_success.setOnClickListener(this);
        btn_error.setOnClickListener(this);
        btn_basic_test.setOnClickListener(this);
        btn_under_text_test.setOnClickListener(this);
        btn_warning_confirm_test.setOnClickListener(this);
        btn_warning_cancel_test.setOnClickListener(this);
        btn_custom_img_test.setOnClickListener(this);
        btn_select_photo.setOnClickListener(this);
        btn_look_photo.setOnClickListener(this);
        btn_select_time.setOnClickListener(this);
        btn_select_address.setOnClickListener(this);


        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ImgAdapter(this);
        adapter.setList(selectList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        recyclerView.addItemDecoration(new SpaceItemDecoration(this).setSpace(14).setSpaceColor(0xFFECECEC));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ImgAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                PictureSelector.create(MainActivity.this).themeStyle(R.style.picture_default_style).openExternalPreview(position, selectList);
            }
        });

    }

    private int i = -1;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_progress_dialog:
                final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("Loading");
                pDialog.show();
                pDialog.setCancelable(false);
                new CountDownTimer(800 * 7, 800) {
                    public void onTick(long millisUntilFinished) {
                        // you can change the progress bar color by ProgressHelper every 800 millis
                        i++;
                        switch (i) {
                            case 0:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.blue_btn_bg_color));
                                break;
                            case 1:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_50));
                                break;
                            case 2:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                                break;
                            case 3:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_20));
                                break;
                            case 4:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_blue_grey_80));
                                break;
                            case 5:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.warning_stroke_color));
                                break;
                            case 6:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                                break;
                        }
                    }

                    public void onFinish() {
                        i = -1;
                        pDialog.setTitleText("Success!")
                                .setConfirmText("OK")
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                }.start();

                break;
            case R.id.btn_success:
                new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Good job!")
                        .setContentText("You clicked the button!")
                        .show();

                break;
            case R.id.btn_error:

                new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong!")
                        .show();

                break;
            case R.id.btn_basic_test:
                // default title "Here's a message!"
                SweetAlertDialog sd = new SweetAlertDialog(this);
                sd.setCancelable(true);
                sd.setCanceledOnTouchOutside(true);
                sd.show();

                break;
            case R.id.btn_under_text_test:

                new SweetAlertDialog(this)
                        .setContentText("It's pretty, isn't it?")
                        .show();

                break;
            case R.id.btn_warning_confirm_test:

                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Won't be able to recover this file!")
                        .setConfirmText("Yes,delete it!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                // reuse previous dialog instance
                                sDialog.setTitleText("Deleted!")
                                        .setContentText("Your imaginary file has been deleted!")
                                        .setConfirmText("OK")
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            }
                        })
                        .show();
                break;
            case R.id.btn_warning_cancel_test:
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Won't be able to recover this file!")
                        .setCancelText("No,cancel plx!")
                        .setConfirmText("Yes,delete it!")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                // reuse previous dialog instance, keep widget user state, reset them if you need
                                sDialog.setTitleText("Cancelled!")
                                        .setContentText("Your imaginary file is safe :)")
                                        .setConfirmText("OK")
                                        .showCancelButton(false)
                                        .setCancelClickListener(null)
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.ERROR_TYPE);

                                // or you can new a SweetAlertDialog to show
                               /* sDialog.dismiss();
                                new SweetAlertDialog(SampleActivity.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Cancelled!")
                                        .setContentText("Your imaginary file is safe :)")
                                        .setConfirmText("OK")
                                        .show();*/
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.setTitleText("Deleted!")
                                        .setContentText("Your imaginary file has been deleted!")
                                        .setConfirmText("OK")
                                        .showCancelButton(false)
                                        .setCancelClickListener(null)
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            }
                        })
                        .show();
                break;
            case R.id.btn_custom_img_test:
                new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                        .setTitleText("Sweet!")
                        .setContentText("Here's a custom image.")
                        .setCustomImage(R.mipmap.custom_img)
                        .show();

                break;
            case R.id.btn_select_photo:
                PictureSelector.create(MainActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .isCamera(true)
                        .imageFormat(PictureMimeType.PNG)
                        .enableCrop(false)// 是否裁剪 true or false
                        .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .forResult(PictureConfig.CHOOSE_REQUEST);


                break;
            case R.id.btn_look_photo:


//                startActivity(new Intent(this,JsonDataActivity.class));
////                FancyToast.makeText(this,"Hello World !",FancyToast.LENGTH_LONG, FancyToast.DEFAULT,true);
////                FancyToast.makeText(this,"Hello World !",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true);
                UniversalToast.makeText(this, "我警告你！", UniversalToast.LENGTH_SHORT, UniversalToast.EMPHASIZE).showWarning();

                break;

            case R.id.btn_select_time:

                pvTime.show(v);//弹出时间选择器，传递参数过去，回调的时候则可以绑定此view
                break;

            case R.id.btn_select_address:
                Intent intent = new Intent(MainActivity.this, JsonDataActivity.class);
                startActivity(intent);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    this.selectList.clear();
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
//                    this.selectList.clear();
                    if (selectList.size() > 19) {
                        return;
                    }
//                    this.selectList.addAll(selectList);
////                    adapter.setList(selectList);
//                    adapter.notifyDataSetChanged();

                    this.selectList.addAll(selectList);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "selectList size : " + selectList.size(), Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
        return format.format(date);
    }

    /**
     * 时间选择器  dialog 模式
     */
    private void initTimePicker() {
        //Dialog 模式下，在底部弹出

        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
//                Toast.makeText(MainActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
                UniversalToast.makeText(MainActivity.this, getTime(date), UniversalToast.LENGTH_SHORT, UniversalToast.EMPHASIZE).showWarning();
                Log.i("pvTime", "onTimeSelect");

            }
        }).setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setType(new boolean[]{true, true, true, true, false, false})
                .isDialog(true)
//                .isCenterLabel(true)
//                .setOutSideCancelable(false)
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
    }

}
