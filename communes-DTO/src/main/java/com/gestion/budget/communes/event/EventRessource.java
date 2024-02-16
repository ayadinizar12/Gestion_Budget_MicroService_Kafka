package com.gestion.budget.communes.event;

import java.util.Date;
import java.util.UUID;

import com.gestion.budget.communes.DTO.RessourceRequestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class EventRessource implements Event {

	private UUID eventId=UUID.randomUUID();
	private Date eventDate= new Date();
	private RessourceRequestDTO ressourceRequestDTO;
	private StatusRessource statusRessource;
	
	@Override
	public UUID getEventId() {
		// TODO Auto-generated method stub
		return eventId;
	}

	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return eventDate;
	}

	
	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public RessourceRequestDTO getRessourceRequestDTO() {
		return ressourceRequestDTO;
	}

	public void setRessourceRequestDTO(RessourceRequestDTO ressourceRequestDTO) {
		this.ressourceRequestDTO = ressourceRequestDTO;
	}

	public StatusRessource getStatusRessource() {
		return statusRessource;
	}

	public void setStatusRessource(StatusRessource statusRessource) {
		this.statusRessource = statusRessource;
	}

	public void setEventId(UUID eventId) {
		this.eventId = eventId;
	}

	public EventRessource(RessourceRequestDTO ressourceRequestDTO, StatusRessource statusRessource) {
		super();
		this.ressourceRequestDTO = ressourceRequestDTO;
		this.statusRessource = statusRessource;
	}

	
	
}
