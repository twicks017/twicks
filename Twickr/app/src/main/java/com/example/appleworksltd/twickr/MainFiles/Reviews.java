package com.example.appleworksltd.twickr.MainFiles;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.appleworksltd.twickr.MysqlDatabase.ReviewDownloader;
import com.example.appleworksltd.twickr.R;


/**
 * A simple {@link Fragment} subclass.e
 * Activities that contain this fragment must implement the
 * {@link Reviews.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Reviews#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Reviews extends Fragment {
   // final static String urlAddress = "https://twickr.000webhostapp.com/twickr/Recent.php";
   final static String urlAddress = "http://192.168.43.168/Tickite/Recent.php";
    ImageView noNetworkImage;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Reviews() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Reviews.
     */
    // TODO: Rename and change types and number of parameters
    public static Reviews newInstance(String param1, String param2) {
        Reviews fragment = new Reviews();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_reviews, container,false);
        final ListView lv = (ListView) rootView.findViewById(R.id.lv);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe);
        noNetworkImage = (ImageView)rootView.findViewById(R.id.noServerImage);

        final CoordinatorLayout coordinator_layout = (CoordinatorLayout)rootView.findViewById(R.id.coordinator_layout1);

        new ReviewDownloader(getActivity(),urlAddress,lv,swipeRefreshLayout,coordinator_layout,noNetworkImage).execute();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new ReviewDownloader(getContext(),urlAddress,lv,swipeRefreshLayout,coordinator_layout,noNetworkImage).execute();
            }
        });

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
