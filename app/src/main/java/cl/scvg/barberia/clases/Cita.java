package cl.scvg.barberia.clases;

public class Cita {

    private String ID;
    private String peluquero;
    private String servicio;
    private String lugar;
    private String fecha;
    private String hora;

    public Cita() {
        this.ID = ID;
        this.servicio = servicio;
        this.peluquero = peluquero;
        this.lugar = lugar;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Cita(String ID,String servicio ,String peluquero, String lugar,String fecha, String hora) {
        this.ID = ID;
        this.servicio = servicio;
        this.peluquero = peluquero;
        this.lugar = lugar;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPeluquero() {
        return peluquero;
    }

    public void setPeluquero(String peluquero) {
        this.peluquero = peluquero;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "ID='" + ID + '\'' +
                ", peluquero='" + peluquero + '\'' +
                ", servicio='" + servicio + '\'' +
                ", lugar='" + lugar + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}
