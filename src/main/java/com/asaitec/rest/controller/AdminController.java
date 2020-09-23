package com.asaitec.rest.controller;

import com.asaitec.rest.exception.NoTicketFoundException;
import com.asaitec.rest.model.Operator;
import com.asaitec.rest.model.OperatorStatus;
import com.asaitec.rest.model.Ticket;
import com.asaitec.rest.model.TicketStatus;
import com.asaitec.rest.repository.OperatorRepository;
import com.asaitec.rest.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final OperatorRepository operatorRepository;
    private final TicketRepository ticketRepository;

    public AdminController(OperatorRepository operatorRepository, TicketRepository ticketRepository) {
        this.operatorRepository = operatorRepository;
        this.ticketRepository = ticketRepository;
    }

    /**
     * Get all tickets.
     *
     * @return All tickets.
     */
    @GetMapping("/tickets")
    public List<Ticket> getAllTickets() {
        return this.ticketRepository.findAll();
    }

    /**
     * Get ticket by id.
     *
     * @return Ticket if found.
     */
    @GetMapping("/ticket/{ticketId}")
    public Ticket getTicketById(@PathVariable("ticketId") long ticketId) throws NoTicketFoundException {
        Optional<Ticket> ticketOptional = this.ticketRepository.findOneById(ticketId);
        if (ticketOptional.isEmpty()) {
            throw new NoTicketFoundException();
        }
        return ticketOptional.get();
    }

    /**
     * Delete ticket by id.
     *
     * @return Ticket if found.
     */
    @DeleteMapping("/ticket/del/{ticketId}")
    public void deleteTicketById(@PathVariable("ticketId") long ticketId) throws NoTicketFoundException {
        this.ticketRepository.deleteById(ticketId);
    }


    /**
     * Modify ticket status.
     *
     * @return List<Operator> that matches current criteria. Can be empty.
     */
    @PostMapping("/status/{ticketId}/{ticketStatus}")
    public Ticket clientCloseTicket(@PathVariable("ticketId") long ticketId,
                                    @PathVariable("ticketStatus") TicketStatus ticketStatus)
            throws NoTicketFoundException {
        return this.ticketRepository.modifyTicketStatus(ticketId, ticketStatus);
    }


    /**
     * Modify ticket status.
     *
     * @return List<Operator> that matches current criteria. Can be empty.
     */
    @PostMapping("/operator/{ticketId}/{operatorId}")
    public Ticket clientOperatorChange(@PathVariable("ticketId") long ticketId,
                                       @PathVariable("operatorId") long operatorId)
            throws NoTicketFoundException {
        return this.ticketRepository.modifyTicketOperator(ticketId, operatorId);
    }


}