package btn.jmt.hermetization.configuration;

public record EmailProperty(
    String senderName, String senderAddress, String subject, String textContent) {

  public static EmailProperty withDefaults(String subject, String textContent) {
    return new EmailProperty("Britenet", "powiadomienia@britenet.eu", subject, textContent);
  }
}
