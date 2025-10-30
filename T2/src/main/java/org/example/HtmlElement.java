package org.example;

import java.util.ArrayList;
import java.util.List;

public class HtmlElement implements HtmlComponent {
    
    private String tagName; 
    private List<HtmlComponent> children = new ArrayList<>();

    public HtmlElement(String tagName) {
        this.tagName = tagName;
    }

    public void addChild(HtmlComponent component) {
        children.add(component);
    }

    public void removeChild(HtmlComponent component) {
        children.remove(component);
    }
    
    private String getIndent(int indentLevel) {
        return "\t".repeat(Math.max(0, indentLevel));
    }

    @Override
    public String generateHtml(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        
        // <body
        sb.append(getIndent(indentLevel));
        sb.append("<").append(tagName).append(">\n");

        for (HtmlComponent child : children) {
            sb.append(child.generateHtml(indentLevel + 1));
        }

        // </body>
        sb.append(getIndent(indentLevel));
        sb.append("</").append(tagName).append(">\n");
        
        return sb.toString();
    }
}