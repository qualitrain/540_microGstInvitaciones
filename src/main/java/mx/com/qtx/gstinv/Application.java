package mx.com.qtx.gstinv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import mx.com.qtx.gstinv.core.IConsumidorMsgEvtosNuevos;
import mx.com.qtx.gstinv.core.IGestorEventosProgramados;
import mx.com.qtx.gstinv.messageBroker.rabbitmq.ConsumidorEventoNuevo;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public IConsumidorMsgEvtosNuevos getConsumidorMensajes(Environment env, IGestorEventosProgramados gestorEventos) {
		String ipMessageBroker = env.getProperty("qtx.gstInvitaciones.messageBroker.host", "localhost");
		String nombreCola = env.getProperty("qtx.gstInvitaciones.messageBroker.nomColaEvtos", "colaEventosDefault");
		String nombreExchange = env.getProperty("qtx.gstInvitaciones.messageBroker.exchangeEvtos", "exchangeDefault");

		IConsumidorMsgEvtosNuevos consumMessageBroker =
				new ConsumidorEventoNuevo(ipMessageBroker, nombreCola, nombreExchange, gestorEventos);

		consumMessageBroker.suscribirseAexchangeConfig();
		consumMessageBroker.consumirMensajes();
		return consumMessageBroker;
		
	}

}
