abstract class DocumentExporter {
    abstract Exporter createExporter();

    void export(String data) {
        Exporter exporter = createExporter();
        exporter.export(data);
    }
}

class PDFExporterCreator extends DocumentExporter {
    Exporter createExporter() { return new PDFExporter(); }
}

class DOCXExporterCreator extends DocumentExporter {
    Exporter createExporter() { return new DOCXExporter(); }
}
