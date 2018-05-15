package com.cabibe.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekxia on 5/2/2018.
 */

public abstract class ArrayAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {

    private List<T> mList;

    protected OnItemClickListener<T> mOnItemClickListener;

    public ArrayAdapter() {
        this(new ArrayList<T>());
    }

    public ArrayAdapter(List<T> list) {
        mList = list;
    }

    public void add(T t) {
        mList.add(t);
    }

    public void addAll(List<T> t) {
        mList.addAll(t);
    }

    public void clear() {
        mList.clear();
    }

    public void remove(int index) {
        mList.remove(index);
    }

    public int size() {
        return mList.size();
    }

    public T get(int location) {
        return mList.get(location);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void onBindViewHolder(VH holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    T t = get(position);
                    mOnItemClickListener.onItemClick(t);
                }
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public View findViewById(int id) {
            return itemView.findViewById(id);
        }
    }

    @Override
    public long getItemId(int position) {
        return get(position).hashCode();
    }
}

