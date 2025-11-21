package com.example.tickets.ViewModel;

import android.content.Context;

import com.example.tickets.Model.EstadoTicket;
import com.example.tickets.Model.Ticket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class TicketManager {
    private final File ticketsFile;

    public TicketManager(Context context){
        File directory = new File(context.getFilesDir(),"TicketDBB");

        if(!directory.exists()){
            directory.mkdir();
        }
        ticketsFile = new File(directory,"tickets.txt");

        try{
            if(!ticketsFile.exists()){
                ticketsFile.createNewFile();
            }

        }catch(IOException e) {
            e.printStackTrace();
        }

    }
    public void saveTickets(List<Ticket> ticketList){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ticketsFile))){
                 for(Ticket t : ticketList){

                    String line = t.getTitulo() + t.getDescripcion() + t.getRecrearBug() + t.getEstado().name();

                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Ticket> loadTickets(){
        List<Ticket> list = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader (new FileReader(ticketsFile))){
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
        return list;
    }

}
