/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package Frames;

import javax.swing.JComboBox;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author ANDRES
 */
public class Registrar_calificacionesNGTest {
    
    public Registrar_calificacionesNGTest() {
    }

    /**
     * Test of TieneTipoValido method, of class Registrar_calificaciones.
     */
    @Test
    public void testTieneTipoValido() {
        System.out.println("TieneTipoValido");
        String tipo = "Parcial 1";
        Registrar_calificaciones instance = new Registrar_calificaciones();
        boolean expResult = true;
        boolean result = instance.TieneTipoValido(tipo);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of TieneCalificacion method, of class Registrar_calificaciones.
     */
    @Test
    public void testTieneCalificacion() {
        System.out.println("TieneCalificacion");
        Registrar_calificaciones instance = new Registrar_calificaciones();
        boolean expResult = false;
        boolean result = instance.TieneCalificacion();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of TieneCalificacionValida method, of class Registrar_calificaciones.
     */
    @Test
    public void testTieneCalificacionValida() {
        System.out.println("TieneCalificacionValida");
        Registrar_calificaciones instance = new Registrar_calificaciones();
        boolean expResult = false;
        boolean result = instance.TieneCalificacionValida();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
