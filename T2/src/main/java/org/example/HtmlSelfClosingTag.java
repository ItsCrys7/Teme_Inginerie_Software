package org.example;

// "Leaf" -  tag-uri  <br> / <img>
public class HtmlSelfClosingTag implements HtmlComponent {
    
    private String tagName; // ex: "br", "img"
    private String attributes; // ex: "src=\"img.jpg\""

    public HtmlSelfClosingTag(String tagName, String attributes) {
        this.tagName = tagName;
        this.attributes = attributes;
    }

    private String getIndent(int indentLevel) {
        return "\t".repeat(Math.max(0, indentLevel));
    }

    @Override
    public String generateHtml(int indentLevel) {
        // ex: <img src="img.jpg" />
        return getIndent(indentLevel) + 
               "<" + tagName + " " + attributes + " />\n";
    }
}