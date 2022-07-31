package com.example.restful_demo.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {
	@NotBlank
  	private String email;
	@NotBlank
	private String password;


}