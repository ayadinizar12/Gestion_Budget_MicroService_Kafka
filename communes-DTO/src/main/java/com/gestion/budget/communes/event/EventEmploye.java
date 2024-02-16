package com.gestion.budget.communes.event;

import java.util.Date;
import java.util.UUID;

import com.gestion.budget.communes.DTO.EmployeRequestDTO;
import com.gestion.budget.communes.DTO.ManagerRequestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class EventEmploye implements Event{


    private UUID eventId=UUID.randomUUID();
    private Date eventDate=new Date();

    private EmployeRequestDTO employeRequestDTO ;

    private StatusEmploye statusEmploye ;
	
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

	public EventEmploye(EmployeRequestDTO employeRequestDTO, StatusEmploye statusEmploye) {
		
		this.employeRequestDTO =employeRequestDTO;
		this.statusEmploye=statusEmploye;
	}

	
	
}
