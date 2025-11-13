package com.example.tickets.View;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tickets.R;


public class ListaFragment extends Fragment {



    public ListaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_test, container, false);

        View rootView = inflater.inflate(R.layout.fragment_test,container,false);

        Button button1 = (Button) rootView.findViewById(R.id.button1);

        button1.setOnClickListener(v -> {

        });

        return rootView;
    }
    @Override
    public void onStart(){
        super.onStart();
        if(getActivity() instanceof MainActivity){// instanceof pregunta si es una instancia de mainActivity y evita los nulos
           // ((MainActivity) getActivity()).test(1,2);//llamo a una funcion del mainactivity
            // con un else if(getActivity()) instanceof SecondActivity podemos hacer que ese fragmento haga cosas distintas dependendiendo de la activity
        }
    }
}