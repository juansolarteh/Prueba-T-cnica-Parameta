package parameta.prueba.tecnica.servicioRest.infrastructure.soapClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import parameta.prueba.tecnica.common.DTOs.SaveEmployeeRequestDTO;
import parameta.prueba.tecnica.common.DTOs.SaveEmployeeResponseDTO;

@Configuration
public class SoapClientConfig {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(SaveEmployeeRequestDTO.class, SaveEmployeeResponseDTO.class);
        return marshaller;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller) {
        WebServiceTemplate template = new WebServiceTemplate();
        template.setMarshaller(marshaller);
        template.setUnmarshaller(marshaller);
        template.setDefaultUri("http://localhost:8080/ws");
        return template;
    }
}
