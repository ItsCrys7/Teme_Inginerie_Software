package org.example;

public interface HtmlComponent {
    /**
     * Generează reprezentarea HTML a acestui component.
     * @param indentLevel Nivelul de indentare pentru formatare.
     * @return String-ul HTML generat.
     */
    String generateHtml(int indentLevel);
}