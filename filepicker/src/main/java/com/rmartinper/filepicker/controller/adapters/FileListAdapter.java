package com.rmartinper.filepicker.controller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.rmartinper.filepicker.R;
import com.rmartinper.filepicker.controller.NotifyItemChecked;
import com.rmartinper.filepicker.model.DialogConfigs;
import com.rmartinper.filepicker.model.DialogProperties;
import com.rmartinper.filepicker.model.FileListItem;
import com.rmartinper.filepicker.model.MarkedItemList;
import com.rmartinper.filepicker.widget.MaterialCheckbox;
import com.rmartinper.filepicker.widget.OnCheckedChangeListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/* <p>
 * Created by Raul Martin on 02-07-2020.
 * </p>
 */

/**
 * Adapter Class that extends {@link BaseAdapter} that is
 * used to populate {@link ListView} with file info.
 */
public class FileListAdapter extends BaseAdapter {
    private ArrayList<FileListItem> listItem;
    private Context context;
    private DialogProperties properties;
    private NotifyItemChecked notifyItemChecked;

    public FileListAdapter(ArrayList<FileListItem> listItem, Context context, DialogProperties properties) {
        this.listItem = listItem;
        this.context = context;
        this.properties = properties;
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public FileListItem getItem(int i) {
        return listItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.dialog_file_list_item, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
        final FileListItem item = listItem.get(i);
        if(MarkedItemList.hasItem(item.getLocation())) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.marked_item_animation);
            view.setAnimation(animation);
        } else {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.unmarked_item_animation);
            view.setAnimation(animation);
        }
        if(item.isDirectory()) {
            holder.typeicon.setImageResource(R.drawable.ic_folder);
            holder.typeicon.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary));
            if(properties.getSelectionType() == DialogConfigs.FILE_SELECT) {
                holder.fmark.setVisibility(View.INVISIBLE);
            } else {
                holder.fmark.setVisibility(View.VISIBLE);
            }
        } else {
            holder.typeicon.setImageResource(R.drawable.ic_file);
            holder.typeicon.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent));
            if(properties.getSelectionType() == DialogConfigs.DIR_SELECT) {
                holder.fmark.setVisibility(View.INVISIBLE);
            } else {
                holder.fmark.setVisibility(View.VISIBLE);
            }
        }
        holder.typeicon.setContentDescription(item.getFilename());
        holder.name.setText(item.getFilename());
        SimpleDateFormat sdate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        Date date = new Date(item.getTime());
        if(i == 0 && item.getFilename().startsWith(context.getString(R.string.label_parent_dir))) {
            holder.type.setText(R.string.label_parent_directory);
        } else {
            holder.type.setText(context.getString(R.string.last_edit, sdate.format(date)));
        }
        if(holder.fmark.getVisibility() == View.VISIBLE) {
            if(i == 0 && item.getFilename().startsWith(context.getString(R.string.label_parent_dir))) {
                holder.fmark.setVisibility(View.INVISIBLE);
            }
            boolean hasItem = MarkedItemList.hasItem(item.getLocation());
            holder.fmark.setChecked(hasItem);
        }
        
        holder.fmark.setOnCheckedChangedListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MaterialCheckbox checkbox, boolean isChecked) {
                item.setMarked(isChecked);
                if(item.isMarked()) {
                    if(properties.getSelectionMode() == DialogConfigs.MULTI_MODE) {
                        MarkedItemList.addSelectedItem(item);
                    } else {
                        MarkedItemList.addSingleFile(item);
                    }
                } else {
                    MarkedItemList.removeSelectedItem(item.getLocation());
                }
                notifyItemChecked.notifyCheckBoxIsClicked();
            }
        });
        return view;
    }

    private class ViewHolder {
        ImageView typeicon;
        TextView name;
        TextView type;
        MaterialCheckbox fmark;

        ViewHolder(View itemView) {
            name = itemView.findViewById(R.id.fname);
            type = itemView.findViewById(R.id.ftype);
            typeicon = itemView.findViewById(R.id.image_type);
            fmark = itemView.findViewById(R.id.file_mark);
        }
    }

    public void setNotifyItemCheckedListener(NotifyItemChecked notifyItemChecked) {
        this.notifyItemChecked = notifyItemChecked;
    }
}
