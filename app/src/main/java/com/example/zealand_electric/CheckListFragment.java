package com.example.zealand_electric;


import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entities.Check_box_Object;

public class CheckListFragment extends Fragment {

    ExpandableListViewAdapter listViewAdapter;
    ExpandableListView expandableListView;
    List<String> chapterList;
    HashMap<String,List<String>> topicList;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_check_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        expandableListView = view.findViewById(R.id.eListView);


        showList();


        listViewAdapter = new ExpandableListViewAdapter(getContext(),chapterList,topicList);
        expandableListView.setAdapter(listViewAdapter);



        // Button Cancel
        Button button_cancel=(Button) view.findViewById(R.id.button_Annuller);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                //clear all check Checkbox
                listViewAdapter.setCheckedItems_clear();
                //ChangeScene
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NavHostFragment.findNavController(CheckListFragment.this)
                                .navigate(R.id.action_checkList_to_mainMenu);
                    }
                });
                /** need to send it to DataBase.java (and delete the customer there is made)*/
            }
        });
        //Button save

        Button button_save =(Button) view.findViewById(R.id.button_Gem);
     //     onClickListener
        button_save.setOnClickListener(v -> new Thread(() -> {


            ArrayList<Check_box_Object> checked_box_list = new ArrayList<Check_box_Object>();

            //forLoop get the Hashset's and send it to array  in objekt
            // enhanced forLoop = Iterator<Pair<Long, Long>> it = listViewAdapter.getCheckedItems_yes().iterator(); it.hasNext();
            for (Pair<Long, Long> it : listViewAdapter.getCheckedItems_yes()) {
                for (int i = 0; i <listViewAdapter.getNote_List_Object().size() ; i++) {
                    // make a Ojekt and add it to arraylist with note
                    if (it.first.intValue() == listViewAdapter.getNote_List_Object().get(i).getCategory_position()&&
                        it.second.intValue() == listViewAdapter.getNote_List_Object().get(i).getQuestion_position() ){

                        checked_box_list.add(new Check_box_Object(
                                it.first.hashCode(),
                                it.second.hashCode(),
                                1,
                                listViewAdapter.getNote_List_Object().get(i).getNote())
                        );

                    }else if(i<listViewAdapter.getNote_List_Object().size()&&
                            it.first.intValue()!=listViewAdapter.getNote_List_Object().get(i).getCategory_position() &&
                            it.second.intValue()!=listViewAdapter.getNote_List_Object().get(i).getQuestion_position() ){

                        //make a Ojekt and add it to arraylist without note
                        checked_box_list.add(new Check_box_Object(it.first.hashCode(), it.second.hashCode(), 1,null));
                    }else{

                    }
                }


            }//forLoop get the Hashset's and send it to array  in objekt
            // enhanced forLoop = Iterator<Pair<Long, Long>> it = listViewAdapter.getCheckedItems_yes().iterator(); it.hasNext();
            for (Pair<Long, Long> it : listViewAdapter.getCheckedItems_No()) {
                for (int i = 0; i <listViewAdapter.getNote_List_Object().size() ; i++) {
                    // make a Ojekt and add it to arraylist with note
                    if (
                            it.first.intValue()==listViewAdapter.getNote_List_Object().get(i).getCategory_position()&&
                            it.second.intValue()==listViewAdapter.getNote_List_Object().get(i).getQuestion_position()){

                        checked_box_list.add(new Check_box_Object(
                                it.first.hashCode(),
                                it.second.hashCode(),
                                2,
                                listViewAdapter.getNote_List_Object().get(i).getNote()));

                    }else if(i<listViewAdapter.getNote_List_Object().size()&&
                            it.first.intValue()!=listViewAdapter.getNote_List_Object().get(i).getCategory_position()&&
                            it.second.intValue()!=listViewAdapter.getNote_List_Object().get(i).getQuestion_position()){
                        //make a Ojekt and add it to arraylist without note
                        checked_box_list.add(new Check_box_Object(it.first.hashCode(), it.second.hashCode(), 2,null));
                    }else{

                    }
                }

            }//forLoop get the Hashset's and send it to array  in objekt
            // enhanced forLoop = Iterator<Pair<Long, Long>> it = listViewAdapter.getCheckedItems_yes().iterator(); it.hasNext();
            for (Pair<Long, Long> it : listViewAdapter.getCheckedItems_Not_relevant()) {
                for (int i = 0; i <listViewAdapter.getNote_List_Object().size() ; i++) {
                    // make a Ojekt and add it to arraylist with note
                    if (    it.first.intValue()==listViewAdapter.getNote_List_Object().get(i).getCategory_position()&&
                            it.second.intValue()==listViewAdapter.getNote_List_Object().get(i).getQuestion_position()){

                        checked_box_list.add(new Check_box_Object(
                                it.first.hashCode(),
                                it.second.hashCode(),
                                3,
                                listViewAdapter.getNote_List_Object().get(i).getNote()));

                    }else if(i<listViewAdapter.getNote_List_Object().size()&&
                            it.first.intValue()!=listViewAdapter.getNote_List_Object().get(i).getCategory_position()&&
                            it.second.intValue()!=listViewAdapter.getNote_List_Object().get(i).getQuestion_position()){
                        //make a Ojekt and add it to arraylist without note
                        checked_box_list.add(new Check_box_Object(it.first.hashCode(), it.second.hashCode(), 3,null));
                    }else{

                    }
                }
            }
            DBController.connectToDatabase();
            System.out.println("liste "+ checked_box_list.toString());
            // Insert to DatabaseController
            for (int i = 0; i <checked_box_list.size() ; i++) {

                DBController.insertIntoCheckListRow(
                                                    NewCustomerFragment.checkList.getId(),
                                                    checked_box_list.get(i).getQuestion_position(),
                                                    checked_box_list.get(i).getAnswerOnCheckBox(),
                                    null,checked_box_list.get(i).getNote(),
                                                    LoginFragment.user.getId(),
                                                    checked_box_list.get(i).getCategory_position());

            }
            DBController.closeConnection();

            //ChangeScene
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    NavHostFragment.findNavController(CheckListFragment.this)
                            .navigate(R.id.action_checkList_to_mainMenu);
                }
            });

            /** need image picker */
        }).start());

//      Button buttonsave_finish
        Button buttonsave_finish = (Button) view.findViewById(R.id.button_Gem_Afslut);
        //     onClickListener
        buttonsave_finish.setOnClickListener(v -> new Thread(() ->{
                ArrayList<Check_box_Object> checked_box_list = new ArrayList<Check_box_Object>();

                //forLoop get the Hashset's and send it to array  in objekt
                // enhanced forLoop = Iterator<Pair<Long, Long>> it = listViewAdapter.getCheckedItems_yes().iterator(); it.hasNext();
                for (Pair<Long, Long> it : listViewAdapter.getCheckedItems_yes()) {
                    for (int i = 0; i <listViewAdapter.getNote_List_Object().size() ; i++) {
                        // make a Ojekt and add it to arraylist with note
                        if (it.first.intValue()==listViewAdapter.getNote_List_Object().get(i).getCategory_position()&&
                                it.second.intValue()==listViewAdapter.getNote_List_Object().get(i).getQuestion_position()){

                            checked_box_list.add(new Check_box_Object(
                                    it.first.hashCode(),
                                    it.second.hashCode(),
                                    1,
                                    listViewAdapter.getNote_List_Object().get(i).getNote()));

                        }else if(i<listViewAdapter.getNote_List_Object().size()&&
                                it.first.intValue()!=listViewAdapter.getNote_List_Object().get(i).getCategory_position()&&
                                it.second.intValue()!=listViewAdapter.getNote_List_Object().get(i).getQuestion_position()){

                            //make a Ojekt and add it to arraylist without note
                            checked_box_list.add(new Check_box_Object(it.first.hashCode(), it.second.hashCode(), 1,null));
                        }else{

                        }
                    }


                }//forLoop get the Hashset's and send it to array  in objekt
                // enhanced forLoop = Iterator<Pair<Long, Long>> it = listViewAdapter.getCheckedItems_yes().iterator(); it.hasNext();
                for (Pair<Long, Long> it : listViewAdapter.getCheckedItems_No()) {
                    for (int i = 0; i <listViewAdapter.getNote_List_Object().size() ; i++) {
                        // make a Ojekt and add it to arraylist with note
                        if (it.first.intValue()==listViewAdapter.getNote_List_Object().get(i).getCategory_position()&&
                                it.second.intValue()==listViewAdapter.getNote_List_Object().get(i).getQuestion_position()){

                            checked_box_list.add(new Check_box_Object(it.first.hashCode(),
                                    it.second.hashCode(),
                                    2,
                                    listViewAdapter.getNote_List_Object().get(i).getNote()));

                        }else if(i<listViewAdapter.getNote_List_Object().size()&&
                                it.first.intValue() != listViewAdapter.getNote_List_Object().get(i).getCategory_position()&&
                                it.second.intValue() != listViewAdapter.getNote_List_Object().get(i).getQuestion_position()){

                            //make a Ojekt and add it to arraylist without note
                            checked_box_list.add(new Check_box_Object(it.first.hashCode(), it.second.hashCode(), 2,null));
                        }else{

                        }
                    }

                }//forLoop get the Hashset's and send it to array  in objekt
                // enhanced forLoop = Iterator<Pair<Long, Long>> it = listViewAdapter.getCheckedItems_yes().iterator(); it.hasNext();
                for (Pair<Long, Long> it : listViewAdapter.getCheckedItems_Not_relevant()) {
                    for (int i = 0; i <listViewAdapter.getNote_List_Object().size() ; i++) {
                        // make a Ojekt and add it to arraylist with note
                        if (it.first.intValue() == listViewAdapter.getNote_List_Object().get(i).getCategory_position()&&
                            it.second.intValue() == listViewAdapter.getNote_List_Object().get(i).getQuestion_position()){

                            checked_box_list.add(new Check_box_Object(
                                    it.first.hashCode(),
                                    it.second.hashCode(),
                                    3,
                                    listViewAdapter.getNote_List_Object().get(i).getNote()));

                        }else if(i<listViewAdapter.getNote_List_Object().size()&&
                                it.first.intValue() != listViewAdapter.getNote_List_Object().get(i).getCategory_position()&&
                                it.second.intValue()!=listViewAdapter.getNote_List_Object().get(i).getQuestion_position()){
                            //make a Ojekt and add it to arraylist without note
                            checked_box_list.add(new Check_box_Object(it.first.hashCode(), it.second.hashCode(), 3,null));
                        }else{

                        }
                    }
                }
                DBController.connectToDatabase();
                System.out.println("liste "+ checked_box_list.toString());
                // Insert to DatabaseController
                for (int i = 0; i <checked_box_list.size() ; i++) {

                    DBController.insertIntoCheckListRow(
                            NewCustomerFragment.checkList.getId(),
                            checked_box_list.get(i).getQuestion_position(),
                            checked_box_list.get(i).getAnswerOnCheckBox(),
                            null,checked_box_list.get(i).getNote(),
                            LoginFragment.user.getId(),
                            checked_box_list.get(i).getCategory_position());

                }
                DBController.closeConnection();

                //ChangeScene
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NavHostFragment.findNavController(CheckListFragment.this)
                                .navigate(R.id.action_checkList_to_restultTabs22);
                    }
                });
                /** ned image picker */

        }).start());



        //Set height in ExpandableListView
        setExpandableListViewHeight_whenNotExpandable(expandableListView,listViewAdapter.getGroupCount());
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                setExpandableListViewHeight(parent, groupPosition);
                System.out.println(groupPosition);
                return false;
            }
        });
    }

    private void showList() {
        chapterList = new ArrayList<>();
        topicList = new HashMap<String,List<String>>();

        chapterList.add("1. Generelt:");
        chapterList.add("2. Tavlen:");
        chapterList.add("3. Installation:");
        chapterList.add("4. Indbygningsarmaturer:");
        chapterList.add("5. Beskyttelsesledere og udligningsforbindelser:");
        chapterList.add("6. Fejlbeskyttelse/supplerende beskyttelse:");

        List<String> topic1 = new ArrayList<>(); //1. Generelt:
        topic1.add("Er der taget hensyn til ydre påvirkninger og anvendt korrekt kapslingsklasse?");
        topic1.add("Er der brandtætnet ved gennemføringer?");
        topic1.add("Er installationen isolationsprøvet?");
        topic1.add("Er der foretaget polaritetsprøve og kontrol af fasefølgen?");
        topic1.add("Er der foretaget funktionsprøver af installationen?");
        topic1.add("Er nul- og beskyttelsesledere korrekt identificeret?");
        topic1.add("Er ledere korrekt overstrømsbeskyttet og valgt efter strømværdi?");
        topic1.add("Er SPD’er (overspændingsbeskyttelsesudstyr) korrekt valgt og installeret?");
        topic1.add("Er permanent tilsluttede brugsgenstande egnet til den pågældende anvendelse?");
        topic1.add("Er nødvendig dokumentation til stede?");
        topic1.add("Er spændingsfald kontrolleret?");
        topic1.add("Er der foretaget foranstaltninger mod elektromagnetiske påvirkninger?");
        topic1.add("Er ejer/bruger informeret om funktion og betjening?");

        List<String> topic2 = new ArrayList<>(); //2. Tavlen:
        topic2.add("Er der tilstrækkelig plads til at arbejde på/adgang til tavlen?");
        topic2.add("Er overstrømsbeskyttelsesudstyr korrekt valgt og evt. indstillet?");
        topic2.add("Er der en entydig mærkning af beskyttelsesudstyr med tilhørsforhold?");
        topic2.add("Er der mærkning om max. mærke-/indstillingsstrøm?");
        topic2.add("Er mærkning med oplysninger om tekniske data for tavlen foretaget?");
        topic2.add("Er udgående beskyttelsesledere anbragt i separate klemmer i tavlen?");
        topic2.add("Er afdækning og dækplader monteret?");
        topic2.add("Er indføringer tilpasset/tætnet, så tavlens kapslingsklasse er som mærket?");

        List<String> topic3 = new ArrayList<>(); //3. Installation:
        topic3.add("Er udstyr til adskillelse og afbrydelse korrekt valgt, placeret og installeret?");
        topic3.add("Er stikkontakter og udtag m.m. installeret i henhold til gældende bestemmelser?");
        topic3.add("Er kabler/ledninger korrekt oplagt, afsluttet og forbundet?");
        topic3.add("Er kabler beskyttet mod mekanisk overlast ved opføringer fra gulv/jord?");
        topic3.add("Er tilledninger aflastet for træk og vridning ved tilslutning til installationen?");
        topic3.add("Er alle dæksler og afdækninger monteret, så der ikke er berøringsfare?");
        topic3.add("Er alle samlinger let tilgængelige?");

        List<String> topic4 = new ArrayList<>(); //4. Indbygningsarmaturer:
        topic4.add("Er indbygningsarmaturer korrekt valgt og monteret?");
        topic4.add("Er indbygningsarmaturer installeret således, at overophedning undgås?");

        List<String> topic5 = new ArrayList<>(); //5. Beskyttelsesledere og udligningsforbindelser:
        topic5.add("Er jordingslederen korrekt valgt (minimum 6 mm^2");
        topic5.add("Er der etableret beskyttende potentialudligning?");
        topic5.add("Er supplerende beskyttende potentialudligning etableret?");
        topic5.add("Er den gennemgående forbindelse i udligningsforbindelser kontrolleret?");
        topic5.add("Er den gennemgående forbindelse i beskyttelsesledere kontrolleret?");
        topic5.add("Er overgangsmodstand for jordelektroden kontrolleret?");

        List<String> topic6 = new ArrayList<>(); //6. Fejlbeskyttelse/supplerende beskyttelse:
        topic6.add("Er beskyttelsesmetode korrekt valgt i forhold til installationstype og systemjording?");
        topic6.add("Er RCD’er (fejlstrømsafbrydere) kontrolleret og afprøvet?");
        topic6.add("Er klasse I brugsgenstande tilsluttet til beskyttelseslederen?");

        topicList.put(chapterList.get(0),topic1);
        topicList.put(chapterList.get(1),topic2);
        topicList.put(chapterList.get(2),topic3);
        topicList.put(chapterList.get(3),topic4);
        topicList.put(chapterList.get(4),topic5);
        topicList.put(chapterList.get(5),topic6);
    }
    private void setExpandableListViewHeight_whenNotExpandable (ExpandableListView listView, int group){
        if (!listView.isGroupExpanded(-1)){
            int totalHeight=0;
            // the ca.
            int chapterHeight_ca = 100;
            totalHeight = chapterHeight_ca*(listViewAdapter.getGroupCount());

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight;
            listView.setLayoutParams(params);
        }

    }
    private void setExpandableListViewHeight(ExpandableListView listView, int group) {
        listViewAdapter = (ExpandableListViewAdapter) listView.getExpandableListAdapter();

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.EXACTLY);


        for (int i = 0; i < listViewAdapter.getGroupCount(); i++) {
            View groupItem = listViewAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listViewAdapter.getChildrenCount(i); j++) {
                    View listItem = listViewAdapter.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                    totalHeight += listItem.getMeasuredHeight();

                }
                //Add Divider Height
                totalHeight += listView.getDividerHeight() * (listViewAdapter.getChildrenCount(i) - 1);
            }
        }
        //Add Divider Height
        totalHeight += listView.getDividerHeight() * (listViewAdapter.getGroupCount() - 1);

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listViewAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}