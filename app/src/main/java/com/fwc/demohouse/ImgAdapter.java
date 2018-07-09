package com.fwc.demohouse;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fwc.library.dialog.SweetAlertDialog;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

/**
 * Created by Administrator on 2018/7/6.
 */

public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.ViewHolder>{

    private Context context;


    List<LocalMedia> selectList;

    public ImgAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<LocalMedia> selectList) {
        this.selectList = selectList;
    }
        //  删除数据
    public void removeData(int position) {
        selectList.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_img,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        LocalMedia localMedia = selectList.get(position);
        Glide.with(context)
                .load(localMedia.getPath())
                .into(holder.img);

        if(onItemClickListener!=null){

            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(position);
                }
            });
        }
        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                removeData(position);
                final SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
                         dialog.setTitleText("确定删除?")
//                        .setContentText("Won't be able to recover this file!")
                        .setCancelText("取消")
                        .setConfirmText("确定")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                // reuse previous dialog instance, keep widget user state, reset them if you need
//                                sDialog.setTitleText("取消!")
//                                        .setContentText("Your imaginary file is safe :)")
//                                        .setConfirmText("OK")
//                                        .showCancelButton(false)
//                                        .setCancelClickListener(null)
//                                        .setConfirmClickListener(null)
//                                        .changeAlertType(SweetAlertDialog.ERROR_TYPE);

                                // or you can new a SweetAlertDialog to show
                               /* sDialog.dismiss();
                                new SweetAlertDialog(SampleActivity.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Cancelled!")
                                        .setContentText("Your imaginary file is safe :)")
                                        .setConfirmText("OK")
                                        .show();*/

                               dialog.dismiss();
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                removeData(position);
                                dialog.dismiss();
//                                sDialog.setTitleText("Deleted!")
//                                        .setContentText("Your imaginary file has been deleted!")
//                                        .setConfirmText("OK")
//                                        .showCancelButton(false)
//                                        .setCancelClickListener(null)
//                                        .setConfirmClickListener(null)
//                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                            }
                        })
                        .show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return selectList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        ImageView iv_delete;

        public ViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            iv_delete = itemView.findViewById(R.id.iv_delete);

        }
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onClick(int position);
    }
}
