package br.com.emanuelgabriel.config.web;

import org.springdoc.webmvc.ui.SwaggerConfig;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(description = "Documentação da REST API ZSSN (Zombie Survival Social Network) Rede Social de Sobrevivência de Zumbis. O mundo como o conhecemos caiu em um cenário apocalíptico. Um vírus feito em laboratório está transformando seres humanos e animais em zumbis, famintos por carne fresca.\n" + 
				"\n" + 
				"Você, como membro da resistência zumbi (e o último sobrevivente que sabe codificar), foi designado para desenvolver um sistema para compartilhar recursos entre humanos não infectados.",
		termsOfService = "Termos de serviço",
		title = "REST API ZSSN - Zumbi Rede Social de Sobrevivência",
		version = "1.0.0",
		contact = @Contact(name = "Emanuel A. Gabriel", email = "emanuel.gabriel.sousa@hotmail.com", url = "LinkedIn aqui")))
public class ConfigWebSwagger extends SwaggerConfig {

}
