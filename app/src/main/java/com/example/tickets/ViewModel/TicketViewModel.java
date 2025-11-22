package com.example.tickets.ViewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tickets.Model.EstadoTicket;
import com.example.tickets.Model.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public class TicketViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Ticket>> tickets = new MutableLiveData<>();
    private final MutableLiveData<Ticket> selectedTicket = new MutableLiveData<>();
    private final TicketManager ticketManager;

    public TicketViewModel(@NonNull Application application) {
        super(application);

        ticketManager = new TicketManager(application);
        
        List<Ticket> initialTickets = ticketManager.loadTickets();
        if (initialTickets == null) {
            initialTickets = new ArrayList<>();
        }
        tickets.setValue(initialTickets);
    }

    public LiveData<List<Ticket>> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        List<Ticket> currentList = tickets.getValue();
        if (currentList != null) {
            currentList.add(ticket);
            tickets.setValue(currentList);
            ticketManager.saveTickets(currentList);
        }
    }

    public void updateTicket(Ticket oldTicket, String newTitle, String newDescription, String newRecrearBug, EstadoTicket newStatus) {
        List<Ticket> currentList = tickets.getValue();
        if (currentList != null && oldTicket != null) {
            for (Ticket t : currentList) {
                if (Objects.equals(t.getTitulo(), oldTicket.getTitulo()) &&
                    Objects.equals(t.getDescripcion(), oldTicket.getDescripcion()) &&
                    Objects.equals(t.getRecrearBug(), oldTicket.getRecrearBug()) &&
                    t.getEstado() == oldTicket.getEstado()) {
                    
                    t.setTitulo(newTitle);
                    t.setDescripcion(newDescription);
                    t.setRecrearBug(newRecrearBug);
                    t.setEstado(newStatus);
                    break;
                }
            }
            tickets.setValue(currentList);
            ticketManager.saveTickets(currentList);
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
