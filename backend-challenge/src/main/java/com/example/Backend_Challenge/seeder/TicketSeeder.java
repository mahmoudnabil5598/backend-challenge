package com.example.backend_challenge.seeder;


import com.example.backend_challenge.dto.TicketDTO;
import com.example.backend_challenge.repository.TicketRepository;
import com.example.backend_challenge.service.TicketService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn({"organizerSeeder", "locationSeeder","eventSeeder"})
public class TicketSeeder {

    private final TicketService ticketService;

    private final TicketRepository ticketRepository;


    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    public TicketSeeder(TicketService ticketService, TicketRepository ticketRepository, EntityManager entityManager) {
        this.ticketService = ticketService;
        this.ticketRepository = ticketRepository;
        this.entityManager = entityManager;

    }
    @SneakyThrows
    @PostConstruct
    @Transactional
    public void seedTickets() {

        if (ticketRepository.count() == 0) {

            //create  Tickets for  events


            TicketDTO ticket1 = new TicketDTO(1L,"1A",100, 2005, 3);
            TicketDTO ticket2 = new TicketDTO(1L,"1B",100, 200, 3);
            TicketDTO ticket3 = new TicketDTO(2L,"1C",100, 200, 3);
            TicketDTO ticket4 = new TicketDTO(2L,"1D",100, 200, 3);
            TicketDTO ticket5 = new TicketDTO(3L,"1A",1050, 200, 3);
            TicketDTO ticket6 = new TicketDTO(3L,"1B",1050, 200, 3);
            TicketDTO ticket7 = new TicketDTO(4L,"1C",1050, 200, 3);
            TicketDTO ticket8 = new TicketDTO(4L,"1D",1050, 200, 3);
            TicketDTO ticket9 = new TicketDTO(5L,"1A",1066, 200, 3);
            TicketDTO ticket10 = new TicketDTO(5L,"1B",100, 200, 3);
            TicketDTO ticket11 = new TicketDTO(6L,"1C",100, 300, 3);
            TicketDTO ticket12 = new TicketDTO(6L,"1D",100, 0, 3);
            TicketDTO ticket13 = new TicketDTO(7L,"1A",100, 0, 3);
            TicketDTO ticket14 = new TicketDTO(7L,"1B",1008, 400, 3);
            TicketDTO ticket15 = new TicketDTO(8L,"1C",1008, 200, 2);
            TicketDTO ticket16 = new TicketDTO(8L,"1D",1008, 200, 1);
            TicketDTO ticket17 = new TicketDTO(9L,"1A",1005, 200, 4);
            TicketDTO ticket18 = new TicketDTO(9L,"1B",1004, 200, 3);
            TicketDTO ticket19 = new TicketDTO(10L,"1C",1030, 200, 2);
            TicketDTO ticket20 = new TicketDTO(11L,"1D",1020, 200, 1);
            TicketDTO ticket21 = new TicketDTO(12L,"1A",1000, 200, 0);
            TicketDTO ticket22 = new TicketDTO(13L,"1B",700, 200, 3);
            TicketDTO ticket23 = new TicketDTO(14L,"1C",600, 200, 3);
            TicketDTO ticket24= new TicketDTO(14L,"1D",400, 200, 3);
            TicketDTO ticket25= new TicketDTO(14L,"1A",300, 200, 3);
            TicketDTO ticket26= new TicketDTO(15L,"1B",800, 200, 3);
            TicketDTO ticket27= new TicketDTO(15L,"1C",5, 950, 3);
            TicketDTO ticket28= new TicketDTO(15L,"1D",10, 2000, 3);

            ticketService.createTicket(ticket1);
            ticketService.createTicket(ticket2);
            ticketService.createTicket(ticket3);
            ticketService.createTicket(ticket4);
            ticketService.createTicket(ticket5);
            ticketService.createTicket(ticket6);
            ticketService.createTicket(ticket7);
            ticketService.createTicket(ticket8);
            ticketService.createTicket(ticket9);
            ticketService.createTicket(ticket10);
            ticketService.createTicket(ticket11);
            ticketService.createTicket(ticket12);
            ticketService.createTicket(ticket13);
            ticketService.createTicket(ticket14);
            ticketService.createTicket(ticket15);
            ticketService.createTicket(ticket16);
            ticketService.createTicket(ticket17);
            ticketService.createTicket(ticket18);
            ticketService.createTicket(ticket19);
            ticketService.createTicket(ticket20);
            ticketService.createTicket(ticket21);
            ticketService.createTicket(ticket22);
            ticketService.createTicket(ticket23);
            ticketService.createTicket(ticket24);
            ticketService.createTicket(ticket25);
            ticketService.createTicket(ticket26);
            ticketService.createTicket(ticket27);
            ticketService.createTicket(ticket28);






        }

    }
}
