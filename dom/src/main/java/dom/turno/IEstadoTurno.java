package dom.turno;

public interface IEstadoTurno {

	public void disponerTurno();

	public void solicitarTurno();

	public void aceptarTurno();

	public void atenderTurno();

	public void cancelarTurno();

	public String nombreEstado();

}