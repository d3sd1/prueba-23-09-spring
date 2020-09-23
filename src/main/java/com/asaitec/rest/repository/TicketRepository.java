package com.asaitec.rest.repository;

import com.asaitec.rest.exception.NoOperatorFoundException;
import com.asaitec.rest.exception.NoTicketFoundException;
import com.asaitec.rest.model.*;
import com.asaitec.rest.service.Randomizer;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketRepository {
    private final Randomizer randomizer;
    private final OperatorRepository operatorRepository;
    private List<Ticket> tickets;

    public TicketRepository(Randomizer randomizer, OperatorRepository operatorRepository) {
        this.randomizer = randomizer;
        this.operatorRepository = operatorRepository;
        this.generateInitialData();
    }

    private void generateInitialData() {
        this.tickets = new ArrayList<>();
    }

    public Ticket putTicket(Ticket ticket) throws NoOperatorFoundException {
        if (this.operatorRepository.findOneById(ticket.getAssignedOperatorId()).isPresent()) {
            this.tickets.add(ticket);
        } else {
            throw new NoOperatorFoundException();
        }
        return ticket;
    }

    public Optional<Ticket> findOneById(long ticketId) {
        return this.tickets.stream().
                filter(p -> p.getTicketId() == ticketId)
                .findFirst();
    }

    public List<Ticket> findByAssignedOperator(long operatorId) {
        return this.tickets.stream().
                filter(p -> p.getAssignedOperatorId() == operatorId)
                .collect(Collectors.toList());
    }

    public Ticket assignTicket(long ticketId, long operatorId) throws NoOperatorFoundException, NoTicketFoundException {
        if (this.operatorRepository.findOneById(operatorId).isEmpty()) {
            throw new NoOperatorFoundException();
        }
        Optional<Ticket> ticket = this.tickets.stream().
                filter(p -> p.getTicketId() == ticketId)
                .findFirst();
        if (ticket.isPresent()) {
            ticket.get().setAssignedOperatorId(operatorId);
        } else {
            throw new NoTicketFoundException();
        }
        return ticket.get();
    }

    public Ticket addComments(long ticketId, List<Comment> comments) throws NoTicketFoundException {
        Optional<Ticket> ticket = this.tickets.stream().
                filter(p -> p.getTicketId() == ticketId)
                .findFirst();
        if (ticket.isPresent()) {
            ticket.get().addComments(comments);
        } else {
            throw new NoTicketFoundException();
        }
        return ticket.get();
    }

    public Ticket modifyTicketStatus(long ticketId, TicketStatus ticketStatus) throws NoTicketFoundException {
        Optional<Ticket> ticket = this.tickets.stream().
                filter(p -> p.getTicketId() == ticketId)
                .findFirst();
        if (ticket.isPresent()) {
            ticket.get().setStatus(ticketStatus);
        } else {
            throw new NoTicketFoundException();
        }
        return ticket.get();
    }

    public Ticket modifyTicketOperator(long ticketId, long operatorId) throws NoTicketFoundException {
        Optional<Ticket> ticket = this.tickets.stream().
                filter(p -> p.getTicketId() == ticketId)
                .findFirst();
        if (ticket.isPresent()) {
            ticket.get().setAssignedOperatorId(operatorId);
        } else {
            throw new NoTicketFoundException();
        }
        return ticket.get();
    }

    public void deleteById(long ticketId) throws NoTicketFoundException {
        Optional<Ticket> ticket = this.tickets.stream().
                filter(p -> p.getTicketId() == ticketId)
                .findFirst();
        if (ticket.isPresent()) {
            this.tickets.remove(ticket.get());
        } else {
            throw new NoTicketFoundException();
        }
    }

    public List<Ticket> findAll() {
        return this.tickets;
    }
}
