package com.gestion.budget.communes.event;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import com.gestion.budget.communes.DTO.GradeCostRequestDTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @NoArgsConstructor
public class EventGradeCost implements Event {

	private UUID eventId=UUID.randomUUID();
	private Date eventDate=new Date() ;
	
	private GradeCostRequestDTO costRequestDTO;
	private StatusGradeCost statusGradeCost;
	
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

	public GradeCostRequestDTO getCostRequestDTO() {
		return costRequestDTO;
	}

	public void setCostRequestDTO(GradeCostRequestDTO costRequestDTO) {
		this.costRequestDTO = costRequestDTO;
	}

	public StatusGradeCost getStatusGradeCost() {
		return statusGradeCost;
	}

	public void setStatusGradeCost(StatusGradeCost statusGradeCost) {
		this.statusGradeCost = statusGradeCost;
	}

	public void setEventId(UUID eventId) {
		this.eventId = eventId;
	}

	public EventGradeCost(GradeCostRequestDTO costRequestDTO, StatusGradeCost statusGradeCost) {
		super();
		this.costRequestDTO = costRequestDTO;
		this.statusGradeCost = statusGradeCost;
	}

	
}
