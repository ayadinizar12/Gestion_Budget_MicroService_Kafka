package com.gestion.budget.communes.event;


import java.util.Date;
import java.util.UUID;

import com.gestion.budget.communes.DTO.ManagerRequestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventBudget implements Event {

    private UUID eventId=UUID.randomUUID();
    private Date eventDate= new Date();
    private ManagerRequestDTO managerRequestDTO;
    private StatusBudget statusBudget ;

    
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

	public EventBudget(ManagerRequestDTO managerRequestDTO, StatusBudget statusBudget) {
	
		this.managerRequestDTO = managerRequestDTO;
		this.statusBudget = statusBudget;
	}

	public ManagerRequestDTO getManagerRequestDTO() {
		return managerRequestDTO;
	}

	public void setManagerRequestDTO(ManagerRequestDTO managerRequestDTO) {
		this.managerRequestDTO = managerRequestDTO;
	}

	public StatusBudget getStatusBudget() {
		return statusBudget;
	}

	public void setStatusBudget(StatusBudget statusBudget) {
		this.statusBudget = statusBudget;
	}
	
	
	

}
