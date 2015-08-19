package creati.movil.tuguia.models;

import android.content.Context;

import com.orm.SugarContext;
import com.orm.SugarRecord;

/**
 * Created by Dario Chamorro on 18/08/2015.
 */
public class Sitio extends SugarRecord{

    public static final int LUGAR =0;
    public static final int RESTAURANTE =1;

    String nombre, direccion, telefono, descripcion, url, categoria;
    int tipo;

    public Sitio(){}

    //region Getters & Setters
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    //endregion

    public static void init(Context context)
    {
        SugarContext.init(context);
        if(count(Sitio.class)<1){
            Sitio sitio  = new Sitio();
            sitio.setNombre("Parque Caldas");
            sitio.setDescripcion("Parque principal de la ciudad de Popayan, ubica en el centro historico.");
            sitio.setDireccion("Carrera 7 Calle 5");
            sitio.setUrl("https://goo.gl/ya2rJ5");
            sitio.setTipo(LUGAR);
            sitio.save();

            sitio  = new Sitio();
            sitio.setNombre("Pizzarra");
            sitio.setDireccion("Carrera 7 Calle 5");
            sitio.setTelefono("8311313");
            sitio.setUrl("http://goo.gl/Qc5TCK");
            sitio.setTipo(RESTAURANTE);
            sitio.setCategoria("Pizzeria");
            sitio.save();

            sitio  = new Sitio();
            sitio.setNombre("Morro");
            sitio.setDescripcion("Montaña Artificial donde se ubica el monumento de Belalcazar");
            sitio.setDireccion("Carrera 7 Calle 5");
            sitio.setUrl("http://goo.gl/J98aG6");
            sitio.setTipo(LUGAR);
            sitio.save();

            sitio  = new Sitio();
            sitio.setNombre("Carantanta");
            sitio.setTelefono("8234567");
            sitio.setDireccion("Carrera 7 Calle 5");
            sitio.setUrl("http://goo.gl/sZCnb4");
            sitio.setCategoria("Gourmet");
            sitio.setTipo(RESTAURANTE);
            sitio.save();
        }

    }


}
