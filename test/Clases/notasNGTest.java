/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package Clases;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author ANDRES
 */
public class notasNGTest {
    
    public notasNGTest() {
    }

    /**
     * Test of TieneTipoValido method, of class notas.
     */
    @Test
    public void testTieneTipoValido() {
        System.out.println("TieneTipoValido");
        String tipo = "Parcial 1";
        notas instance = new notas();
        boolean expResult = true;
        boolean result = instance.TieneTipoValido(tipo);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of TieneCalificacion method, of class notas.
     */
    @Test
    public void testTieneCalificacion() {
        System.out.println("TieneCalificacion");
        String calif = "20";
        notas instance = new notas();
        boolean expResult = true;
        boolean result = instance.TieneCalificacion(calif);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of TieneCalificacionValida method, of class notas.
     */
    @Test
    public void testTieneCalificacionValida() {
        System.out.println("TieneCalificacionValida");
        int calif = 20;
        notas instance = new notas();
        boolean expResult = true;
        boolean result = instance.TieneCalificacionValida(calif);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of TieneNotasCompletas method, of class notas.
     */
    @Test
    public void testTieneNotasCompletas() {
        System.out.println("TieneNotasCompletas");
        int cantnotas = 3;
        notas instance = new notas();
        boolean expResult = true;
        boolean result = instance.TieneNotasCompletas(cantnotas);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
