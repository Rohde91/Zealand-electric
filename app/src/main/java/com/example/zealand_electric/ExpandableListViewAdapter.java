package com.example.zealand_electric;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> chapterList; //string type List
    private HashMap<String, List<String>> topicList;   // expandable list is create on hashmap
    //This  stores the  coordinates  of checkBox's yes: fx 0,1 (group, child)
    private final Set<Pair<Long, Long>> checkedItems_yes = new HashSet<Pair<Long, Long>>();
    private final Set<Pair<Long, Long>> checkedItems_No  = new HashSet<Pair<Long, Long>>();
    private final Set<Pair<Long, Long>> checkedItems_Not_relevant = new HashSet<Pair<Long, Long>>();

    public ExpandableListViewAdapter(Context context, List<String> chapterList, HashMap<String, List<String>> topicList) {
        this.context = context;
        this.chapterList = chapterList;
        this.topicList = topicList;
    }



    @Override
    public int getGroupCount() {
        return this.chapterList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.topicList.get(this.chapterList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.chapterList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.topicList.get(this.chapterList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String chapterTitle = (String) getGroup(groupPosition);

        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.chapter_list,null);
        }
        TextView chapterTv = convertView.findViewById(R.id.chapter_tv);
        chapterTv.setText(chapterTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String topicsTitle = (String) getChild(groupPosition,childPosition);

        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.topics_list,null);
        }
        TextView topicsTv = convertView.findViewById(R.id.topics_tv);
        topicsTv.setText(topicsTitle);
//_____________________________________________________________________
        //get answer from checkBox
        final CheckBox cb_Yes =         (CheckBox) convertView.findViewById(R.id.checkBox1_ja);
        final CheckBox cb_no =          (CheckBox) convertView.findViewById(R.id.checkBox2_Nej);
        final CheckBox cb_not_relevant =(CheckBox) convertView.findViewById(R.id.checkBox3_Ikke_relevant);
        // add tag to remember groupId/childId
        final Pair<Long, Long> tag = new Pair<>(
                getGroupId(groupPosition),
                getChildId(groupPosition, childPosition));
        // add groupId/childId to checkBox
        cb_Yes.setTag(tag);
        cb_no.setTag(tag);
        cb_not_relevant.setTag(tag);
        // set checked if groupId/childId in checked items
        cb_Yes.setChecked(checkedItems_yes.contains(tag));
        cb_no.setChecked(checkedItems_No.contains(tag));
        cb_not_relevant.setChecked(checkedItems_Not_relevant.contains(tag));
        // set OnClickListener to handle checked switches

        cb_Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CheckBox cb = (CheckBox) v;
                final Pair<Long, Long> tag = (Pair<Long, Long>) v.getTag();

                if (cb.isChecked()) {
                    //add answer to list
                    checkedItems_yes.add(tag);
                    // set other checkBox to unCheck
                    cb_no.setChecked(false);
                    cb_not_relevant.setChecked(false);
                    // delete other checkbox answer from list
                    checkedItems_No.remove(tag);
                    checkedItems_Not_relevant.remove(tag);

                } else {
                    // if you uncheck the box delete from list
                    checkedItems_yes.remove(tag);
                }
            }
        });
        cb_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CheckBox cb = (CheckBox) v;
                final Pair<Long, Long> tag = (Pair<Long, Long>) v.getTag();

                if (cb.isChecked()) {
                    //add answer to list
                    checkedItems_No.add(tag);
                    // set other checkBox to unCheck
                    cb_Yes.setChecked(false);
                    cb_not_relevant.setChecked(false);
                    // delete other checkbox answer from list
                    checkedItems_yes.remove(tag);
                    checkedItems_Not_relevant.remove(tag);

                } else {
                    // if you uncheck the box delete from list
                    checkedItems_No.remove(tag);
                }
            }
        });
        cb_not_relevant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CheckBox cb = (CheckBox) v;
                final Pair<Long, Long> tag = (Pair<Long, Long>) v.getTag();

                if (cb.isChecked()) {
                    //add answer to list
                    checkedItems_Not_relevant.add(tag);
                    // set other checkBox to unCheck
                    cb_Yes.setChecked(false);
                    cb_no.setChecked(false);
                    // delete other checkbox answer from list
                    checkedItems_yes.remove(tag);
                    checkedItems_No.remove(tag);

                } else {
                    // if you uncheck the box delete from list
                    checkedItems_Not_relevant.remove(tag);

                }
            }
        });
//____________________________________________________________

        return convertView;
    }

    public Set<Pair<Long, Long>> getCheckedItems_yes() {
        return checkedItems_yes;
    }

    public Set<Pair<Long, Long>> getCheckedItems_No() {
        return checkedItems_No;
    }

    public Set<Pair<Long, Long>> getCheckedItems_Not_relevant() {
        return checkedItems_Not_relevant;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
