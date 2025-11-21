package com.example.tickets.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tickets.Model.EstadoTicket;
import com.example.tickets.Model.Ticket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class TicketViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Ticket>> tickets = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Ticket> selectedTicket = new MutableLiveData<>();

    private final TicketManager ticketManager;


    public TicketViewModel(@NonNull Application application){
        super(application);
        ticketManager = new TicketManager(application);
        tickets.setValue(ticketManager.loadTickets());

    }

    public LiveData<List<Ticket>> getTickets() {
        return tickets;
    }


    public void addTicket(Ticket ticket) {
        List<Ticket> list = tickets.getValue();
            list.add(ticket);
            tickets.setValue(list);
            ticketManager.saveTickets(list);

        }
    }

    public void updateTicket(Ticket oldTicket, String newTitle, String newDescription, String newRecrearBug, EstadoTicket newStatus) {
        List<Ticket> list = tickets.getValue();
            for (Ticket t : list) {
                if (t == oldTicket) {
                    t.setTitulo(newTitle);
                    t.setDescripcion(newDescription);
                    t.setEstado(newStatus);
                    t.setRecrearBug(newRecrearBug);
                    break;
                }
            }
            tickets.setValue(list);
            ticketManager.saveTickets(list);
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