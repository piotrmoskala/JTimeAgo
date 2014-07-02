package io.inspace.jtimeago;

import io.inspace.jtimeago.languages.LanguageFactory;
import io.inspace.jtimeago.languages.LanguageType;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * Class that extends standard java.util.Date class and returns
 * usable string dates like "3 days ago" , "3 minutes ago" etc.
 */
public class JTimeAgo extends Date {

  private Locale locale;
  private ResourceBundle resourceBundle;

  public JTimeAgo() {
    locale = LanguageFactory.getLanguage(LanguageType.EN);
    resourceBundle = ResourceBundle.getBundle("Language", locale);
  }

  public JTimeAgo(LanguageType languageType) {
    locale = LanguageFactory.getLanguage(languageType);
    resourceBundle = ResourceBundle.getBundle("Language", locale);
  }

  public JTimeAgo(Date date) {
    this();
    this.setTime(date.getTime());
  }

  public JTimeAgo(Date date, LanguageType languageType) {
    this(languageType);
    this.setTime(date.getTime());
  }

  /**
   * Returns simple string
   * "1m" , "2h" , "3d" etc
   *
   * @return - present time
   */
  public String getTimeAgoSimple() {
    Date now = Calendar.getInstance().getTime();
    long deltaMillis = intervalInMillis(this, now);
    long deltaSeconds = TimeUnit.MILLISECONDS.toSeconds(deltaMillis);
    long deltaMinutes = TimeUnit.MILLISECONDS.toMinutes(deltaMillis);
    if (deltaSeconds < 60) {
      return String.format("%ds", deltaSeconds);
    } else if (deltaMinutes < 60) {
      return String.format("%dm", deltaMinutes);
    } else if (deltaMinutes < (24 * 60)) {
      return String.format("%dh", TimeUnit.MINUTES.toHours(deltaMinutes));
    } else if (deltaMinutes < (24 * 60 * 7)) {
      return String.format("%dd", deltaMinutes / (60 * 24));
    } else if (deltaMinutes < (24 * 60 * 31)) {
      return String.format("%dw", deltaMinutes / (60 * 24 * 7));
    } else if (deltaMinutes < (24 * 60 * 365.25)) {
      return String.format("%dmo", deltaMinutes / (60 * 24 * 30));
    }
    return String.format("%dyr", deltaMinutes / (60 * 24 * 365));
  }


  /**
   * Returns more complex string like "a minute ago" , "a second ago"
   *
   * @return
   */
  public String getTimeAgo() {
    Date now = Calendar.getInstance().getTime();
    long deltaMillis = intervalInMillis(this, now);
    long deltaSeconds = TimeUnit.MILLISECONDS.toSeconds(deltaMillis);
    long deltaMinutes = TimeUnit.MILLISECONDS.toMinutes(deltaMillis);
    if (deltaSeconds < 5) {
      return getMessage("justNow");
    } else if (deltaSeconds < 60) {
      return String.format("%d %s %s", deltaSeconds, getMessage("seconds"), getMessage("ago"));
    } else if (deltaSeconds < 120) {
      return getMessage("minute") + " " + getMessage("ago");
    } else if (deltaMinutes < 60) {
      return String.format("%d %s %s", deltaMinutes, getMessage("minutes"), getMessage("ago"));
    } else if (deltaMinutes < 120) {
      return getMessage("hour") + " " + getMessage("ago");
    } else if (deltaMinutes < (24 * 60)) {
      return String.format("%d %s %s", deltaMinutes / 60, getMessage("hours"), getMessage("ago"));
    } else if (deltaMinutes < (24 * 60 * 2)) {
      return getMessage("yesterday");
    } else if (deltaMinutes < (24 * 60 * 7)) {
      return String.format("%d %s %s", deltaMinutes / (60 * 24), getMessage("days"), getMessage("ago"));
    } else if (deltaMinutes < (24 * 60 * 14)) {
      return getMessage("lastWeek");
    } else if (deltaMinutes < (24 * 60 * 31)) {
      return String
              .format("%d %s %s", deltaMinutes / (60 * 24 * 7), getMessage("weeks"), getMessage("ago"));
    } else if (deltaMinutes < (24 * 60 * 61)) {
      return getMessage("lastMonth");
    } else if (deltaMinutes < (24 * 60 * 365.25)) {
      return String.format("%d %s %s", deltaMinutes / (60 * 24 * 30), getMessage("months"),
              getMessage("ago"));
    } else if (deltaMinutes < (24 * 60 * 731)) {
      return getMessage("lastYear");
    }
    return String
            .format("%d %s %s", deltaMinutes / (60 * 24 * 365), getMessage("years"), getMessage("ago"));
  }


  private long intervalInMillis(Date first, Date second) {
    return Math.abs(first.getTime() - second.getTime());
  }

  public void setLanguage(LanguageType languageType) {
    locale = LanguageFactory.getLanguage(languageType);
  }

  private String getMessage(String key){
    return resourceBundle.getString(key);
  }

}
