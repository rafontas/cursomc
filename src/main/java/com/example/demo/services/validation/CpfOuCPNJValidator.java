package com.example.demo.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demo.DTOS.ClienteNewDTO;
import com.example.demo.domain.enuns.TipoCliente;
import com.example.demo.services.excecoes.FieldMessage;
import com.example.demo.services.validation.uteis.BR;

public class CpfOuCPNJValidator implements ConstraintValidator<CpfOuCNPJInsert, ClienteNewDTO> {
	
	@Override
	public void initialize(CpfOuCNPJInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		// Realiza os testes
		if(objDto.getTipoCliente() == null) {
			list.add(new FieldMessage("tipo cliente", "O tipo do cliente não pode ser nulo."));
		}
		// Valida o CPF
		else if(objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
				list.add(new FieldMessage("CPF", "O CPF do cliente está inválido."));
		}
		else if(objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("O CNPJ do cliente está inválido.", "CNPJ inválido."));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFildName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}