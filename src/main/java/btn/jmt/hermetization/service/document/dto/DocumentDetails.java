package btn.jmt.hermetization.service.document.dto;

public record DocumentDetails(DocumentId documentId,
                              DocumentBarcode barcode,
                              String name,
                              DocumentType documentType) {}
