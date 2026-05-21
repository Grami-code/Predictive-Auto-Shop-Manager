package com.serviceauto.services;

import com.serviceauto.models.Client;
import com.serviceauto.database.BazaDate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    // Add client in DB and return generated id
    public static int adaugaClientInDB(Client client) {
        String sql = "INSERT INTO Client (nume, prenume, email) VALUES (?, ?, ?)";

        try (Connection con = BazaDate.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, client.getNume());
            stmt.setString(2, client.getPrenume());
            stmt.setString(3, client.getEmail());

            int randuri = stmt.executeUpdate();

            if (randuri > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGenerat = rs.getInt(1);
                        client.setId(idGenerat);
                        System.out.println("Client adaugat in baza de date cu ID-ul: " + idGenerat);
                        return idGenerat;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; 
    }

    // Get the clients from DB
    public static List<Client> getTotiClientii() {
        List<Client> clienti = new ArrayList<>();
        String sql = "SELECT * FROM Client";

        try (Connection con = BazaDate.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nume = rs.getString("nume");
                String prenume = rs.getString("prenume");
                String email = rs.getString("email");

                Client c = new Client(id, nume, prenume, email);
                clienti.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clienti;
    }

    /**
     *  Show every client
     */
    public static void afiseazaClienti() {
        String sql = "SELECT * FROM Client";

        try (Connection con = BazaDate.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Nume: " + rs.getString("nume") +
                        ", Prenume: " + rs.getString("prenume") +
                        ", Email: " + rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
