package com.example.appleworksltd.twickr.MainFiles;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appleworksltd.twickr.MysqlDatabase.Download;
import com.example.appleworksltd.twickr.MysqlDatabase.SenderReciever;
import com.example.appleworksltd.twickr.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Events.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Events#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Events extends Fragment {
    //final static String urlAddress = "https://twickr.000webhostapp.com/twickr/search.php";
    final static String urlAddress = "http://192.168.43.168/Tickite/search.php";
    SearchView sv;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    ListView lv;
    Spinner spinner;
    String cat = "Musical";
    ImageView noDataImage,noNetworkImage;
    SwipeRefreshLayout swipeRefreshLayout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Events() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Events.
     */
    // TODO: Rename and change types and number of parameters
    public static Events newInstance(String param1, String param2) {
        Events fragment = new Events();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        // Use filter.xml from step 1

        //final MenuItem searchItem = menu.findItem(R.id.sv);
        MenuItem menuItem = menu.findItem(R.id.sv);

        MenuItem item = menu.findItem(R.id.spinner);
        spinner = (Spinner) MenuItemCompat.getActionView(item);



        final String category [] = {"Musical", "Comedy", "Parties", "Sports","Confrences"};

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,category);
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(stringArrayAdapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                noDataImage.setVisibility(View.INVISIBLE);
                noNetworkImage.setVisibility(View.INVISIBLE);
                String cat = spinner.getSelectedItem().toString();
                String query ="";
                Toast.makeText(getActivity(),cat,Toast.LENGTH_SHORT).show();
                SenderReciever sr = new SenderReciever(getContext(), urlAddress, query,cat, lv,mRecyclerView, noDataImage, noNetworkImage);
                sr.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);


            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    noDataImage.setVisibility(View.INVISIBLE);
                    noNetworkImage.setVisibility(View.INVISIBLE);
                    SenderReciever sr = new SenderReciever(getContext(), urlAddress, query,cat, lv,mRecyclerView, noDataImage, noNetworkImage);
                    sr.execute();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    noDataImage.setVisibility(View.INVISIBLE);
                    noNetworkImage.setVisibility(View.INVISIBLE);
                    SenderReciever sr = new SenderReciever(getContext(), urlAddress, query,cat, lv,mRecyclerView, noDataImage, noNetworkImage);
                    sr.execute();

                    return false;
                }
            });




        }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.sv){
            //Do whatever you want to do




            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_events, container,false);
//        setHasOptionsMenu(true);

        lv = (ListView)rootView.findViewById(R.id.lv);
        sv = (SearchView)rootView.findViewById(R.id.sv);
        noDataImage = (ImageView)rootView.findViewById(R.id.nodataImage);
        noNetworkImage = (ImageView)rootView.findViewById(R.id.noServerImage);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(false);

        mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //lv.setNestedScrollingEnabled(true);
        final String urlAddress1 = "http://192.168.43.168/Tickite/test.php";
       //final  String urlAddress1 = "https://twickr.000webhostapp.com/twickr/test.php";
        noDataImage.setVisibility(View.INVISIBLE);
        noNetworkImage.setVisibility(View.INVISIBLE);


        new Download(getContext(),urlAddress1,mRecyclerView,noDataImage,noNetworkImage).execute();


        String query = "";
        SenderReciever sr = new SenderReciever(getContext(),urlAddress,query,cat,lv,mRecyclerView,noDataImage,noNetworkImage);
        sr.execute();

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
