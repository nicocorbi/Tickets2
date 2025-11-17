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
import android.widget.ListView;
import com.example.tickets.Model.Ticket;
import com.example.tickets.R;
import com.example.tickets.ViewModel.TicketViewModel;
import java.util.ArrayList;
import java.util.List;

public class ListaFragment extends Fragment {

    private TicketViewModel ticketViewModel;
    private ArrayAdapter<Ticket> adapter;
    private List<Ticket> ticketList = new ArrayList<>();

    public ListaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ticketViewModel = new ViewModelProvider(requireActivity()).get(TicketViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listView = view.findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, ticketList);
        listView.setAdapter(adapter);

        ticketViewModel.getTickets().observe(getViewLifecycleOwner(), tickets -> {
            ticketList.clear();
            ticketList.addAll(tickets);
            adapter.notifyDataSetChanged();
        });

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Ticket selectedTicket = ticketList.get(position);
            ticketViewModel.selectTicket(selectedTicket);
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).navigateToNewFragment();
            }
        });
    }
}