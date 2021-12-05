/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validaciones;

import javax.swing.JOptionPane;

/**
 *
 * @author Adelma Lorenzo
 */
public class Texto {
    public static boolean esCajaVacia(javax.swing.JTextField cajaTexto, String msg) {
        if (cajaTexto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, msg);
            cajaTexto.requestFocus();//enfocamos al componente que esta vacio
            return true;
        } else {
            return false;
        }
    }
}
