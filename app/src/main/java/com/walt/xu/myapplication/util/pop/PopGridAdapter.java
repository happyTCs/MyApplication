package com.walt.xu.myapplication.util.pop;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.walt.xu.myapplication.R;
import com.walt.xu.myapplication.databinding.ItemPopGridBinding;

import java.util.List;

//import cn.chinapost.jdpt.pda.pcs.R;
//import cn.chinapost.jdpt.pda.pcs.databinding.ItemPopGridBinding;

/**
 * Created by gxl on 2017/3/7.
 * 底部弹出框 grid adapter
 */

public class PopGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;
    private int page;

    public PopGridAdapter(Context context, List<String> list, int page) {
        mContext = context;
        mList = list;
        this.page = page;
    }

    public void update(int page) {
        this.page = page;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ItemPopGridBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_pop_grid, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ItemPopGridBinding) convertView.getTag();
        }
        binding.tvType.setText(mList.get(position));

        if (page == position) {
            binding.tvType.setSelected(true);
            binding.llGridItem.setBackgroundResource(R.drawable.bg_item_grid_pop_select);
        } else {
            binding.tvType.setSelected(false);
            binding.llGridItem.setBackgroundResource(R.drawable.bg_item_grid_pop_default);
        }

        return convertView;
    }

}
