package com.example.tickets.View;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.tickets.R;
import com.example.tickets.ViewModel.TicketViewModel;
import com.example.tickets.Model.Ticket;

public class NewFragment extends Fragment {

    private TicketViewModel ticketViewModel;
    private EditText titleEditText;
    private EditText descriptionEditText;
    private Button actionButton;

    public NewFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ticketViewModel = new ViewModelProvider(requireActivity()).get(TicketViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleEditText = view.findViewById(R.id.TextTitle);
        descriptionEditText = view.findViewById(R.id.TextDescription);
        actionButton = view.findViewById(R.id.EnviarTicket);

        ticketViewModel.getSelectedTicket().observe(getViewLifecycleOwner(), ticket -> {
            if (ticket != null) {
                titleEditText.setText(ticket.getTitulo());
                descriptionEditText.setText(ticket.getDescripcion());
                titleEditText.setEnabled(true);
                descriptionEditText.setEnabled(true);
                actionButton.setText("Guardar Cambios");
                actionButton.setOnClickListener(v -> {
                    String newTitle = titleEditText.getText().toString();
                    String newDescription = descriptionEditText.getText().toString();
                    ticketViewModel.updateTicket(ticket, newTitle, newDescription);
                    getParentFragmentManager().popBackStack();
                });
            } else {
                titleEditText.setText("");
                descriptionEditText.setText("");
                actionButton.setText("Añadir Ticket");
                actionButton.setOnClickListener(v -> {
                    String title = titleEditText.getText().toString();
                    String description = descriptionEditText.getText().toString();
                    ticketViewModel.addTicket(new Ticket(title, description));
                    getParentFragmentManager().popBackStack();
                });
            }
        });
    }
}