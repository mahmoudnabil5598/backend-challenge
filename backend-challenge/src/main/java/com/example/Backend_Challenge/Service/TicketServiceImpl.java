package com.example.backend_challenge.service;

import com.example.backend_challenge.dto.TicketDTO;
import com.example.backend_challenge.dto.response.TicketResponseDTO;
import com.example.backend_challenge.entity.EventEntity;
import com.example.backend_challenge.entity.TicketEntity;
import com.example.backend_challenge.exception.SoldOutException;
import com.example.backend_challenge.repository.TicketRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.example.backend_challenge.util.SystemUtil.*;

@Service
public class TicketServiceImpl implements TicketService {


    private final TicketRepository ticketRepository;
    private final EventService eventService;

    private String entityName = "TicketEntity";

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, EventService eventService) {
        this.ticketRepository = ticketRepository;
        this.eventService = eventService;
    }

    @Override
    public Page<TicketResponseDTO> getAllTickets(String searchString, Integer pageSize, Integer pageNumber, String sortField, String sortDirection) {
        Pageable paginate= pagination(sortField,sortDirection,pageNumber,pageSize);
        searchString = searchString == null ? "" : searchString.toLowerCase();
        return ticketRepository.findAll(searchString, paginate);
    }

    @Override
    public TicketEntity getTicketById(Long id) {
        return unwrapEntity(ticketRepository.findById(id), id, entityName);
    }

    @Override
    public TicketEntity createTicket(TicketDTO ticket) {

        //get ticket Event
        EventEntity event = eventService.getEventId(ticket.getEvent());

        //copy data
        TicketEntity ticketObject = new TicketEntity();
        BeanUtils.copyProperties(ticket, ticketObject);

        ticketObject.setEvent(event);
        ticketObject.setInitialLimit(ticketObject.getTicketLimit());

        return ticketRepository.save(ticketObject);

    }

    @Override
    public TicketEntity updateTicket(TicketDTO ticket, Long id) {

        TicketEntity oldTicket = unwrapEntity(ticketRepository.findById(id), id, entityName);

        //Check if the Event is Updated
        if(ticket.getEvent()!=null) {
            EventEntity event = eventService.getEventId(ticket.getEvent());
            oldTicket.setEvent(event);
        }



        copyNonNullProperties(ticket, oldTicket);
        oldTicket.setInitialLimit(oldTicket.getTicketLimit());

        return ticketRepository.save(oldTicket);


    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public void reduceProductQuantity(int quantity, Long ticketId) {
        TicketEntity ticket = unwrapEntity(ticketRepository.findById(ticketId),ticketId, entityName);

        if(ticket.getTicketLimit()<quantity ||ticket.getTicketLimit()-quantity<0)
            throw  new SoldOutException(quantity,ticketId);
        ticket.setTicketLimit(ticket.getTicketLimit()-quantity);

        ticketRepository.save(ticket);


    }

    @Override
    public void addProductQuantity(int quantity, Long ticketId) {
        TicketEntity ticket = unwrapEntity(ticketRepository.findById(ticketId),ticketId, entityName);

        if(ticket.getTicketLimit()<quantity ||ticket.getTicketLimit()+quantity<0||ticket.getTicketLimit()+quantity> ticket.getInitialLimit())
            throw  new SoldOutException(quantity,ticketId);
        ticket.setTicketLimit(ticket.getTicketLimit()+quantity);

        ticketRepository.save(ticket);
    }
}
