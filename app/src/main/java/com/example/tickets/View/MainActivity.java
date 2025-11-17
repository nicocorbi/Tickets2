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
import androidx.lifecycle.ViewModelProvider;
import com.example.tickets.R;
import com.example.tickets.ViewModel.TicketViewModel;

public class MainActivity extends AppCompatActivity {

    private TicketViewModel ticketViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ticketViewModel = new ViewModelProvider(this).get(TicketViewModel.class);

        ImageView iconOpen = findViewById(R.id.iconOpen);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new ListaFragment())
                    .commit();
        }

        iconOpen.setOnClickListener(v -> {
            ticketViewModel.clearSelectedTicket();
            navigateToNewFragment();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void navigateToNewFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new NewFragment())
                .addToBackStack(null)
                .commit();
    }
}