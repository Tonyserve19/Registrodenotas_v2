/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author ANDRES
 */
public class notas {
    public int id_alumno_nota;
    public String id_curso_nota, tipo;
    public int calificacion;

    public int getId_alumno_nota() {
        return id_alumno_nota;
    }

    public void setId_alumno_nota(int id_alumno_nota) {
        this.id_alumno_nota = id_alumno_nota;
    }

    public String getId_curso_nota() {
        return id_curso_nota;
    }

    public void setId_curso_nota(String id_curso_nota) {
        this.id_curso_nota = id_curso_nota;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public boolean TieneTipoValido(String tipo){
        return tipo != "Seleccione Tipo";
    }
    public boolean TieneCalificacion(String calif){
        if (calif != ""){
            return true;
        }else
            return false;
    }
    
    
    public boolean TieneCalificacionValida(int calif){
        return calif>=0 && calif <=20;
    }
    
    public boolean TieneNotasCompletas (int cantnotas){
        return cantnotas>=3;
    }
}
