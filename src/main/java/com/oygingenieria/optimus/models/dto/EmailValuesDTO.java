package com.oygingenieria.optimus.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailValuesDTO {
	
	private String mailFrom;
	private String mailTo;
	private String subject;
	private String userName;
	private String tokenPassword;
	
}
