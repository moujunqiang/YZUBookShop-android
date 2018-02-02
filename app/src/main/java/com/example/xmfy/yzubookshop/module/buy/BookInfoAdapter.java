package com.example.xmfy.yzubookshop.module.buy;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xmfy.yzubookshop.R;
import com.example.xmfy.yzubookshop.model.BookInfo;
import com.example.xmfy.yzubookshop.utils.CategoryLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xmfy on 2018/2/2.
 */

public class BookInfoAdapter extends BaseAdapter {

    private Context context;
    private List<BookInfo> bookInfos;
    private List<CheckBox> checkBoxes;

    public BookInfoAdapter(Context context, List<BookInfo> bookInfos) {
        this.context = context;
        this.bookInfos = bookInfos;
        checkBoxes = new ArrayList<>();
    }

    public void update(List<BookInfo> bookInfos) {
        this.bookInfos = bookInfos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bookInfos == null ? 0 : bookInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {
        ViewHolder holder;
        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.item_book_info, viewGroup, false);
            holder = new ViewHolder(v);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        if (checkBoxes.size() < i + 1)
            checkBoxes.add(holder.cb_bookinfo);
        holder.cb_bookinfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("cart", "  "+ b);
            }
        });
        BookInfo info = bookInfos.get(i);
        Glide.with(context).load(info.getUrls().split(" ")[0]).into(holder.iv_bookinfo_cover);
        holder.tv_bookinfo_title.setText(info.getTitle());
        holder.tv_bookinfo_author.setText(info.getAuthor());
        HashMap<Integer, String> map = CategoryLoader.getCategoryNameById(info.getCategory1(), info.getCategory2());
        holder.tv_bookinfo_c1.setText(map.get(1));
        holder.tv_bookinfo_c2.setText(map.get(2));
        holder.tv_bookinfo_price.setText(String.format("%.2f", info.getPrice()));
        return v;
    }

    void updateCheckBox(boolean b){
        Log.e("cart", b + "   " + checkBoxes.size());
        Log.e("cart", bookInfos.toString());
        for (CheckBox checkBox : checkBoxes)
            checkBox.setChecked(b);
    }

    class ViewHolder {
        CheckBox cb_bookinfo;
        ImageView iv_bookinfo_cover;
        TextView tv_bookinfo_title;
        TextView tv_bookinfo_author;
        TextView tv_bookinfo_c1;
        TextView tv_bookinfo_c2;
        TextView tv_bookinfo_price;

        ViewHolder(View v) {
            cb_bookinfo = v.findViewById(R.id.cb_bookinfo);
            iv_bookinfo_cover = v.findViewById(R.id.iv_bookinfo_cover);
            tv_bookinfo_title = v.findViewById(R.id.tv_bookinfo_title);
            tv_bookinfo_author = v.findViewById(R.id.tv_bookinfo_author);
            tv_bookinfo_c1 = v.findViewById(R.id.tv_bookinfo_c1);
            tv_bookinfo_c2 = v.findViewById(R.id.tv_bookinfo_c2);
            tv_bookinfo_price = v.findViewById(R.id.tv_bookinfo_price);
        }
    }
}
