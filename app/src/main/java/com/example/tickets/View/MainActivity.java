package com.example.tickets.View;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tickets.R;

public class MainActivity extends AppCompatActivity {

    // ListaFragment fragmentMain;
    NewFragment MiFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ImageView iconOpen = findViewById(R.id.iconOpen);

        iconOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transact = fragmentManager.beginTransaction();
                MiFragment = new NewFragment();

                transact.replace(R.id.fragmentContainer, MiFragment);
                transact.commit();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        //FragmentManager fragmentManager = getSupportFragmentManager();
        //FragmentTransaction transact = fragmentManager.beginTransaction();

        //fragmentMain = new ListaFragment();
        //transact.replace(R.id.fragmentContainer,fragmentMain);
        //transact.commit();
    }

    public static int sumar(int a, int b){
        return a+ b;
    }
    public static int dividir(int a, int b){
        if(b==0){
            //throw
        }
        return a/ b;
    }
}
// el bundle va en el oncreate y solo se ejecuta 1 vez y solo se ejecuta cuando hay un new