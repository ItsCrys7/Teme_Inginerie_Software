package org.example;

// Un "Leaf" (Frunză) - echivalentul lui File
public class HtmlTextNode implements HtmlComponent {

    private String text;

    public HtmlTextNode(String text) {
        this.text = text;
    }

    private String getIndent(int indentLevel) {
        return "\t".repeat(Math.max(0, indentLevel));
    }

    @Override
    public String generateHtml(int indentLevel) {
        // Afișează textul cu indentarea corectă
        return getIndent(indentLevel) + text + "\n";
    }
}