package mx.com.qtx.gstinv.messageBroker.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import mx.com.qtx.gstinv.core.IConsumidorMsgEvtosNuevos;
import mx.com.qtx.gstinv.core.IGestorEventosProgramados;
import mx.com.qtx.gstinv.entidades.EventoPropuesto;

public class ConsumidorEventoNuevo extends ConsumidorMensajes implements IConsumidorMsgEvtosNuevos{
	private static Logger bitacora = LoggerFactory.getLogger(ConsumidorEventoNuevo.class); 
	private IGestorEventosProgramados gestorEventos;

	public ConsumidorEventoNuevo(String host, String nomCola, String nomExchange, IGestorEventosProgramados gestorEventos) {
		super(host, nomCola, nomExchange);
		this.gestorEventos = gestorEventos;
		
		bitacora.info("ConsumidorEventoNuevo(" + host + ", " + nomCola + ", " + nomExchange + ") instanciado");
	}

	@Override
	public void procesarMensajeJson(String mensaje) {
		Jsonb jsonb = JsonbBuilder.create();
		EventoPropuesto evento = jsonb.fromJson(mensaje, EventoPropuesto.class);
		this.gestorEventos.agregarEvento(evento);
		bitacora.info("Evento agregado:" + evento);		
	}
	
	public void consumirMensajes() {
		super.consumirMensajes();
		bitacora.info("Recuperación de mensajes en segundo plano activa. Id(Consumer tag)=" + this.idConsumidor);		
	}

}
