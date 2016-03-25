package com.doan.adapter;

import java.util.ArrayList;
import java.util.List;

import com.doan.lichhoctap.R;
import com.doan.model.DieuLeTag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

public class SearchAutoCompleteAdapter extends ArrayAdapter<DieuLeTag> {

    Context context;
    //int resource, textViewResourceId;
    int layoutId;
    List<DieuLeTag> items, tempItems, suggestions;

    public SearchAutoCompleteAdapter(Context context, int layoutid, List<DieuLeTag> items) {
        super(context, layoutid, items);
        this.context = context;
        /*this.resource = resource;
        this.textViewResourceId = textViewResourceId;*/
        this.layoutId = layoutid;
        this.items = items;
        tempItems = new ArrayList<DieuLeTag>(items); // this makes the difference.
        suggestions = new ArrayList<DieuLeTag>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutId, parent, false);
        }
        DieuLeTag tag = items.get(position);
        if (tag != null) {
            TextView tvTag = (TextView) view.findViewById(R.id.tvDieuLe);
            if (tvTag != null)
            	tvTag.setText(tag.getTag());
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    /**
     * Custom Filter implementation for custom suggestions we provide.
     */
    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((DieuLeTag) resultValue).getTag();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (DieuLeTag tag : tempItems) {
                    if (tag.getTag().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(tag);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<DieuLeTag> filterList = (ArrayList<DieuLeTag>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (DieuLeTag tag : filterList) {
                    add(tag);
                    notifyDataSetChanged();
                }
            }
        }
    };
}