package com.example.kmucs.echoet_app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EarthFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EarthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EarthFragment extends Fragment {
    float rotateEarth = 0.0f;
    int fatNum = 0;
    int fatSize = 5;
    int earthGreen = 3;
    ImageView imageEarth;
    ImageView imageFat;
    Handler earthHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0){   // Message id 가 0 이면
                rotateEarth = (rotateEarth + 1.0f) % 360.0f;
                imageEarth.setRotation(rotateEarth);
            }
        }
    };
    Handler fatHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0){   // Message id 가 0 이면
                fatNum = (fatNum + 1) % 6;
                switch (fatNum){
                    case 0:
                        imageFat.setImageResource(R.drawable.fat001);
                        break;
                    case 1:
                        imageFat.setImageResource(R.drawable.fat002);
                        break;
                    case 2:
                        imageFat.setImageResource(R.drawable.fat003);
                        break;
                    case 3:
                        imageFat.setImageResource(R.drawable.fat004);
                        break;
                    case 4:
                        imageFat.setImageResource(R.drawable.fat005);
                        break;
                    case 5:
                        imageFat.setImageResource(R.drawable.fat006);
                        break;
                }
            }
        }
    };
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EarthFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EarthFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EarthFragment newInstance(String param1, String param2) {
        EarthFragment fragment = new EarthFragment();
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rotateEarth();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_earth, container, false);
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
    public void rotateEarth() {
        imageEarth = (ImageView) getView().findViewById(R.id.imageEarth);
        imageFat = (ImageView) getView().findViewById(R.id.imageFat);
        imageFat.setScaleX(1.0f - 0.1f * (5 - fatSize));
        imageFat.setScaleY(1.0f - 0.1f * (5 - fatSize));

        EarthThread earthThreadhread = new EarthThread();

        earthThreadhread.setDaemon(true);
        earthThreadhread.start();

        FatThread fatThread = new FatThread();
        fatThread.setDaemon(true);
        fatThread.start();

        setEarth(earthGreen);
    }
    public void setEarth(int green){
        switch (green){
            case 1:
                imageEarth.setImageResource(R.drawable.earth001);
                break;
            case 2:
                imageEarth.setImageResource(R.drawable.earth002);
                break;
            case 3:
                imageEarth.setImageResource(R.drawable.earth003);
                break;
            case 4:
                imageEarth.setImageResource(R.drawable.earth004);
                break;
            case 5:
                imageEarth.setImageResource(R.drawable.earth005);
                break;
        }
    }
    class EarthThread extends Thread{
        @Override
        public void run() {
            while(true){
                // 메인에서 생성된 Handler 객체의 sendEmpryMessage 를 통해 Message 전달
                earthHandler.sendEmptyMessage(0);

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } // end while
        } // end run()
    }
    class FatThread extends Thread{
        @Override
        public void run() {
            while(true){
                // 메인에서 생성된 Handler 객체의 sendEmpryMessage 를 통해 Message 전달
                fatHandler.sendEmptyMessage(0);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } // end while
        } // end run()
    }
}
