package com.example.tickets.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
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

public class TicketViewModel extends ViewModel {
    private final MutableLiveData<List<Ticket>> tickets = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Ticket> selectedTicket = new MutableLiveData<>();

    private final String File = "tickets.txt";

    public TicketViewModel(@NonNull Application application){
        super((Closeable) application);
        loadTickets();
    }

    public LiveData<List<Ticket>> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        List<Ticket> currentTickets = tickets.getValue();
        if (currentTickets != null) {
            currentTickets.add(ticket);
            tickets.setValue(currentTickets);
            saveTickets();
        }
    }

    public void updateTicket(Ticket oldTicket, String newTitle, String newDescription, String newRecrearBug, EstadoTicket newStatus) {
        List<Ticket> currentTickets = tickets.getValue();
        if (currentTickets != null) {
            for (int i = 0; i < currentTickets.size(); i++) {
                if (currentTickets.get(i) == oldTicket) {
                    currentTickets.get(i).setTitulo(newTitle);
                    currentTickets.get(i).setDescripcion(newDescription);
                    currentTickets.get(i).setEstado(newStatus);
                    currentTickets.get(i).setRecrearBug(newRecrearBug);
                    break;
                }
            }
            tickets.setValue(currentTickets);
        }
    }
    public void saveTickets(){
        try{
            FileOutputStream fos = getApplication().openFileOutput(File,getApplication().MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            List<Ticket> list = tickets.getValue();
            if (list != null){
                for(Ticket t : list){
                    String line = t.getTitulo() + t.getDescripcion() + t.getRecrearBug() + t.getEstado().name();

                    writer.write(line);
                    writer.newLine();
                }
            }
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void loadTickets(){
        List<Ticket> list = new ArrayList<>();
        try{
            FileInputStream fis = getApplication().openFileInput(File);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("/");

                if (parts.length == 10){
                    Ticket t = new Ticket(parts[0],parts[1],parts[2]);
                    t.setEstado(EstadoTicket.valueOf(parts[3]));
                    list.add(t);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();

        }
        tickets.setValue(list);
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