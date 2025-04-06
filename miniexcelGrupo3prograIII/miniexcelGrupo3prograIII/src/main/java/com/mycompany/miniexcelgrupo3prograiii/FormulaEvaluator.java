package com.mycompany.miniexcelgrupo3prograiii;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTable;

/**
 *
 * @author Javier Palma
 */

public class FormulaEvaluator {

    public static String evaluarFormula(String formula, JTable tabla) {
        try {
            if (formula.startsWith("=")) {
                String contenido = formula.substring(1).toUpperCase(); // Quitar '=' y convertir a mayúsculas

                if (contenido.startsWith("SUMAR(")) {
                    return String.valueOf(evaluarRango(contenido, tabla, "SUMAR"));
                } else if (contenido.startsWith("MULTIPLICAR(")) {
                    return String.valueOf(evaluarRango(contenido, tabla, "MULTIPLICAR"));
                } else if (contenido.startsWith("RESTAR(")) {
                    return String.valueOf(evaluarRango(contenido, tabla, "RESTAR"));
                } else if (contenido.startsWith("DIVIDIR(")) {
                    return String.valueOf(evaluarRango(contenido, tabla, "DIVIDIR"));
                } else {
                    // Evaluar fórmula matemática tipo =A1+B2
                    Pattern patron = Pattern.compile("[A-J][1-9][0]?");
                    Matcher matcher = patron.matcher(contenido);
                    while (matcher.find()) {
                        String ref = matcher.group();
                        int[] coords = convertirReferencia(ref);
                        Object valor = tabla.getValueAt(coords[0], coords[1]);
                        contenido = contenido.replace(ref, (valor != null && !valor.toString().isEmpty()) ? valor.toString() : "0");
                    }
                    return String.valueOf(evaluarMath(contenido));
                }
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
        return "";
    }

    // Método para evaluar expresiones matemáticas simples
    private static double evaluarMath(String expr) throws Exception {
        // Usar Exp4j para evaluar la expresión matemática
        return new net.objecthunter.exp4j.ExpressionBuilder(expr).build().evaluate();
    }

    // Método para evaluar un rango (SUMAR, MULTIPLICAR, RESTAR, DIVIDIR)
    private static double evaluarRango(String formula, JTable tabla, String operacion) {
        String rango = formula.substring(formula.indexOf("(") + 1, formula.length() - 1);
        String[] referencias = rango.split(":");

        int[] inicio = convertirReferencia(referencias[0]);
        int[] fin = convertirReferencia(referencias[1]);

        switch (operacion) {
            case "SUMAR": return sumarRango(inicio, fin, tabla);
            case "MULTIPLICAR": return multiplicarRango(inicio, fin, tabla);
            case "RESTAR": return restarRango(inicio, fin, tabla);
            case "DIVIDIR": return dividirRango(inicio, fin, tabla);
            default: return 0;
        }
    }

    // Método para convertir A1, B2 a coordenadas de tabla (fila, columna)
    private static int[] convertirReferencia(String ref) {
        char col = ref.charAt(0);
        int columna = col - 'A' + 1;
        int fila = Integer.parseInt(ref.substring(1));
        return new int[]{fila - 1, columna}; // para JTable
    }

    // Métodos para operaciones matemáticas
    private static double sumarRango(int[] inicio, int[] fin, JTable tabla) {
        double suma = 0;
        for (int fila = inicio[0]; fila <= fin[0]; fila++) {
            for (int col = inicio[1]; col <= fin[1]; col++) {
                Object val = tabla.getValueAt(fila, col);
                if (val != null && !val.toString().isEmpty()) {
                    suma += Double.parseDouble(val.toString());
                }
            }
        }
        return suma;
    }

    private static double multiplicarRango(int[] inicio, int[] fin, JTable tabla) {
        double producto = 1;
        for (int fila = inicio[0]; fila <= fin[0]; fila++) {
            for (int col = inicio[1]; col <= fin[1]; col++) {
                Object val = tabla.getValueAt(fila, col);
                if (val != null && !val.toString().isEmpty()) {
                    producto *= Double.parseDouble(val.toString());
                }
            }
        }
        return producto;
    }

    private static double restarRango(int[] inicio, int[] fin, JTable tabla) {
        Object inicial = tabla.getValueAt(inicio[0], inicio[1]);
        double resultado = (inicial != null && !inicial.toString().isEmpty()) ? Double.parseDouble(inicial.toString()) : 0;

        for (int fila = inicio[0]; fila <= fin[0]; fila++) {
            for (int col = inicio[1]; col <= fin[1]; col++) {
                if (fila == inicio[0] && col == inicio[1]) continue; // Saltar la primera celda
                Object val = tabla.getValueAt(fila, col);
                if (val != null && !val.toString().isEmpty()) {
                    resultado -= Double.parseDouble(val.toString());
                }
            }
        }
        return resultado;
    }

    private static double dividirRango(int[] inicio, int[] fin, JTable tabla) {
        Object inicial = tabla.getValueAt(inicio[0], inicio[1]);
        double resultado = (inicial != null && !inicial.toString().isEmpty()) ? Double.parseDouble(inicial.toString()) : 0;

        for (int fila = inicio[0]; fila <= fin[0]; fila++) {
            for (int col = inicio[1]; col <= fin[1]; col++) {
                if (fila == inicio[0] && col == inicio[1]) continue; // Saltar la primera celda
                Object val = tabla.getValueAt(fila, col);
                if (val != null && !val.toString().isEmpty()) {
                    double divisor = Double.parseDouble(val.toString());
                    if (divisor == 0) return Double.NaN; // Evitar división por cero
                    resultado /= divisor;
                }
            }
        }
        return resultado;
    }
}

