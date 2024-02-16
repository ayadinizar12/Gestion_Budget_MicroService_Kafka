package com.gestion.budget.communes.event;

import java.util.Date;
import java.util.UUID;

import com.gestion.budget.communes.DTO.TemplateRequestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class EventTemplate implements Event {

	private UUID eventId=UUID.randomUUID();
	private Date eventDate=new Date();
	
	private TemplateRequestDTO templateRequestDTO;
	private StatusTemplate statusTemplate;
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
	public EventTemplate(TemplateRequestDTO templateRequestDTO, StatusTemplate statusTemplate) {
		super();
		this.templateRequestDTO = templateRequestDTO;
		this.statusTemplate = statusTemplate;
	}
	public TemplateRequestDTO getTemplateRequestDTO() {
		return templateRequestDTO;
	}
	public void setTemplateRequestDTO(TemplateRequestDTO templateRequestDTO) {
		this.templateRequestDTO = templateRequestDTO;
	}
	public StatusTemplate getStatusTemplate() {
		return statusTemplate;
	}
	public void setStatusTemplate(StatusTemplate statusTemplate) {
		this.statusTemplate = statusTemplate;
	}
	
	
	
}
