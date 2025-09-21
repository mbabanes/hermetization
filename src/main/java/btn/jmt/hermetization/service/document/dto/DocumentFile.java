package btn.jmt.hermetization.service.document.dto;

public record DocumentFile(byte[] bytes, String fileName) {

  public static DocumentFile of(byte[] bytes) {
    return new DocumentFile(bytes, "");
  }
}
