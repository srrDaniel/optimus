package com.oygingenieria.optimus.models.dto;

import javax.validation.constraints.NotBlank;

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
public class CambiarClaveDTO {
	
	@NotBlank
	private String password;
	@NotBlank
	private String confirmarPassword;
	@NotBlank
	private String tokenPassword;

}
