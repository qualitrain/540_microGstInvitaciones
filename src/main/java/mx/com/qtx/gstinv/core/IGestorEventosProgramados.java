package mx.com.qtx.gstinv.core;

import java.util.List;

import mx.com.qtx.gstinv.entidades.EventoPropuesto;

public interface IGestorEventosProgramados {
	List<EventoPropuesto> getEventos();
	void agregarEvento(EventoPropuesto evt);
}
