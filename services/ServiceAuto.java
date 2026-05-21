package com.serviceauto.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.serviceauto.models.*;

// Main class

public class ServiceAuto {

    Scanner scanner = new Scanner(System.in);
    private List<Client> clienti;
    private List<Masina> masini;
    private List<Programare> programari;
    private List<Reparatie> reparatii;

    public ServiceAuto() {
        clienti = ClientService.getTotiClientii();
        masini = MasinaService.getToateMasinile();
        programari = new ArrayList<>();
        reparatii = new ArrayList<>();
    }

    public void adaugaClient(Client c) {
        clienti.add(c);
    }

    public void adaugaMasina(Masina m) {
        masini.add(m);
    }

    public void adaugaProgramare(Programare p) {
        programari.add(p);
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("Choose an option: ");
            System.out.println("1. Enter a new client");
            System.out.println("2. Enter client's car");
            System.out.println("3. Create a schedule");
            System.out.println("4. Show details");
            System.out.println("5. Show the repairs");
            System.out.println("0. Close the program");

            int optiune = Integer.parseInt(scanner.nextLine());
            System.out.println("\n");

            switch (optiune) {
                case 1:
                    System.out.println("Enter your Last Name");
                    String nume = scanner.nextLine();
                    System.out.println("Enter your First Name");
                    String prenume = scanner.nextLine();
                    System.out.println("Enter your E-mail");
                    String email = scanner.nextLine();

                    Client c = new Client(nume, prenume, email);
                    int idClientDB = ClientService.adaugaClientInDB(c);
                    if(idClientDB > 0) {
                        c.setId(idClientDB);
                        clienti.add(c); // actualizam lista locala
                        System.out.println("New client with ID: " + idClientDB);
                    } else {
                        System.out.println("Error at adding the client in the DB!");
                    }
                    break;

                case 2:

                    System.out.println("Enter the car's brand");
                    String marca = scanner.nextLine();
                    System.out.println("Enter the car's model");
                    String model = scanner.nextLine();
                    System.out.println("Enter the car owner's ID");
                    int proprietarId = Integer.parseInt(scanner.nextLine());

                    boolean clientExista = false;
                    for(Client cl : clienti) {
                        if(cl.getId() == proprietarId) {
                            clientExista = true;
                            break;
                        }
                    }

                    if(!clientExista) {
                        System.out.println("There is no client with this ID!");
                        break;
                    }

                    System.out.println("Enter the licence plate number: ");
                    String numar = scanner.nextLine();

                    Masina m = new Masina(marca, model, proprietarId, numar);
                    int idMasinaDB = MasinaService.adaugaMasinaInDB(m);
                    if(idMasinaDB > 0) {
                        m.setId(idMasinaDB);
                        masini.add(m);
                        System.out.println("New car's ID: " + idMasinaDB);
                    } else {
                        System.out.println("Error at adding the new car in the DB!");
                    }
                    break;

                case 3:
                    System.out.println("Enter the day");
                    int ziua = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter the month");
                    int luna = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter the year");
                    int anul = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter the hour");
                    int ora = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter the minutes");
                    int minute = Integer.parseInt(scanner.nextLine());

                    Programare p = new Programare(anul, luna, ziua, ora, minute);
                    programari.add(p);
                    break;

                case 4:
                    System.out.println("Enter client's ID");
                    int clientId = Integer.parseInt(scanner.nextLine().trim());
                    boolean gasit = false;

                    for(Client cl : clienti) {
                        if(cl.getId() == clientId) {
                            gasit = true;
                            System.out.println("Client details: " + cl);
                            System.out.println("Owned cars: ");


                            for(Masina mas : masini) {
                                if(mas.getProprietarId() == clientId) {
                                    System.out.println(mas);
                                }
                            }
                            break;
                        }
                    }

                    if(!gasit) {
                        System.out.println("This client doesnt exist!");
                    }
                    break;

                case (5):
                    System.out.println("Reparation title:");
                    String titlu = scanner.nextLine();

                    System.out.println("Parts used:");
                    String piese = scanner.nextLine();

                    System.out.println("Total cost:");
                    double cost = Double.parseDouble(scanner.nextLine());

                    System.out.println("Status:");
                    String status = scanner.nextLine();

                    Reparatie rep = new Reparatie(titlu, piese, cost, status);

                    // AI introduction
                    String prompt = "Generate a professional description for this reparation: " + titlu +
                            ". Parts used: " + piese +
                            ". Price: " + cost + " lei";

                    String raspuns = OpenAIService.cereAI(prompt);

                    rep.setDescriereAI(raspuns);

                    reparatii.add(rep);

                    System.out.println("Generated description");
                    System.out.println(raspuns);
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
