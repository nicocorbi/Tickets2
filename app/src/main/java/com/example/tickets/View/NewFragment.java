package com.example.tickets.View;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.tickets.Model.EstadoTicket;
import com.example.tickets.R;
import com.example.tickets.ViewModel.TicketViewModel;
import com.example.tickets.Model.Ticket;

public class NewFragment extends Fragment {

    private TicketViewModel ticketViewModel;
    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText RecrearBugEditText;
    private Spinner statusSpinner;
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
    public void onViewCreated(@NonNull View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleEditText = view.findViewById(R.id.TextTitle);
        descriptionEditText = view.findViewById(R.id.TextDescription);
        RecrearBugEditText = view.findViewById(R.id.textRecrearBug);
        statusSpinner = view.findViewById(R.id.spinner);
        actionButton = view.findViewById(R.id.EnviarTicket);

        ArrayAdapter<EstadoTicket> adapter = new ArrayAdapter<>(requireContext(),android.R.layout.simple_spinner_item, EstadoTicket.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);

        ticketViewModel.getSelectedTicket().observe(getViewLifecycleOwner(), ticket -> {
            if (ticket != null) {
                titleEditText.setText(ticket.getTitulo());
                descriptionEditText.setText(ticket.getDescripcion());
                RecrearBugEditText.setText(ticket.getRecrearBug());
                titleEditText.setEnabled(true);
                descriptionEditText.setEnabled(true);
                RecrearBugEditText.setEnabled(true);
                actionButton.setText("Guardar Cambios");
                actionButton.setOnClickListener(v -> {
                    String newTitle = titleEditText.getText().toString();
                    String newDescription = descriptionEditText.getText().toString();
                    String newRecrearBug = RecrearBugEditText.getText().toString();
                    EstadoTicket newStatus = (EstadoTicket) statusSpinner.getSelectedItem();
                    ticketViewModel.updateTicket(ticket, newTitle, newDescription, newRecrearBug,newStatus);
                    getParentFragmentManager().popBackStack();
                });
            } else {
                titleEditText.setText("");
                descriptionEditText.setText("");
                RecrearBugEditText.setText("");
                actionButton.setText("Añadir Ticket");
                actionButton.setOnClickListener(v -> {
                    String title = titleEditText.getText().toString();
                    String description = descriptionEditText.getText().toString();
                    String recrearBug = RecrearBugEditText.getText().toString();
                    EstadoTicket status = (EstadoTicket) statusSpinner.getSelectedItem();

                    Ticket newTicket = new Ticket(title, description, recrearBug);
                    newTicket.setEstado(status);

                    ticketViewModel.addTicket(newTicket);
                    getParentFragmentManager().popBackStack();

                });
            }
        });
    }
}