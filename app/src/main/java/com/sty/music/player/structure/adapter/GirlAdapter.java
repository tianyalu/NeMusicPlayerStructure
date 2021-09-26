package com.sty.music.player.structure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sty.music.player.structure.R;
import com.sty.music.player.structure.bean.Girl;

import java.util.List;

/**
 * Author: ShiTianyi
 * Time: 2021/9/6 0006 20:31
 * Description:
 */
public class GirlAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Girl> girls;

    public GirlAdapter(Context context, List<Girl> girls) {
        this.inflater = LayoutInflater.from(context);
        this.girls = girls;
    }

    @Override
    public int getCount() {
        return girls.size();
    }

    @Override
    public Object getItem(int position) {
        return girls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item, null);
        Girl girl = girls.get(position);
        ImageView ivIcon = view.findViewById(R.id.iv_icon);
        ivIcon.setImageResource(girl.getIcon());

        TextView tvLike = view.findViewById(R.id.tv_like);
        tvLike.setText(girl.getStyle());
        return view;
    }
}
