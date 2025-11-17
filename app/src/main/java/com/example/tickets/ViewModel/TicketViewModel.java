package com.example.tickets.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.tickets.Model.Ticket;
import java.util.ArrayList;
import java.util.List;

public class TicketViewModel extends ViewModel {
    private final MutableLiveData<List<Ticket>> tickets = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Ticket> selectedTicket = new MutableLiveData<>();

    public LiveData<List<Ticket>> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        List<Ticket> currentTickets = tickets.getValue();
        if (currentTickets != null) {
            currentTickets.add(ticket);
            tickets.setValue(currentTickets);
        }
    }

    public void updateTicket(Ticket oldTicket, String newTitle, String newDescription) {
        List<Ticket> currentTickets = tickets.getValue();
        if (currentTickets != null) {
            for (int i = 0; i < currentTickets.size(); i++) {
                if (currentTickets.get(i) == oldTicket) {
                    currentTickets.get(i).setTitulo(newTitle);
                    currentTickets.get(i).setDescripcion(newDescription);
                    break;
                }
            }
            tickets.setValue(currentTickets);
        }
    }

    public void selectTicket(Ticket ticket) {
        selectedTicket.setValue(ticket);
    }

    public LiveData<Ticket> getSelectedTicket() {
        return selectedTicket;
    }

    public void clearSelectedTicket() {
        selectedTicket.setValue(null);
    }
}