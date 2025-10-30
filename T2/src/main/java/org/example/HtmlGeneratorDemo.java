package org.example;

public class HtmlGeneratorDemo {

    public static void main(String[] args) {
        
        // <html>
        HtmlElement html = new HtmlElement("html");

        // <body>
        HtmlElement body = new HtmlElement("body");
        html.addChild(body); // body este copilul lui html

        // <h1>Titlu</h1>
        HtmlElement h1 = new HtmlElement("h1");
        h1.addChild(new HtmlTextNode("Acesta este un titlu"));
        body.addChild(h1); // h1 copilul lui body

        HtmlElement p1 = new HtmlElement("p");
        p1.addChild(new HtmlTextNode("Paragraf cu "));

        HtmlElement bold = new HtmlElement("b"); // element (composite)
        bold.addChild(new HtmlTextNode("text bold")); // care are ca și copil text
        
        p1.addChild(bold); 
        p1.addChild(new HtmlTextNode(".")); 
        
        body.addChild(p1); 

        HtmlSelfClosingTag img = new HtmlSelfClosingTag("img", "src=\"logo.png\" alt=\"logo\"");
        body.addChild(img);

        String paginaHtmlCompleta = html.generateHtml(0);

        System.out.println("--- Început Fișier HTML ---");
        System.out.println(paginaHtmlCompleta);
        System.out.println("--- Sfârșit Fișier HTML ---");
    }
}