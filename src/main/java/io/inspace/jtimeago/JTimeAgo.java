package io.inspace.jtimeago;

import io.inspace.jtimeago.languages.LanguageFactory;
import io.inspace.jtimeago.languages.LanguageType;
import io.inspace.jtimeago.languages.lang.AbstractLanguage;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Class that extends standard java.util.Date class and returns
 * usable string dates like "3 days ago" , "3 minutes ago" etc.
 */
public class JTimeAgo extends Date {

  private AbstractLanguage abstractLanguage;

  public JTimeAgo() {
    abstractLanguage = LanguageFactory.getLanguage(LanguageType.EN);
  }

  public JTimeAgo(LanguageType languageType) {
    abstractLanguage = LanguageFactory.getLanguage(languageType);
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
      return abstractLanguage.getJustNow();
    } else if (deltaSeconds < 60) {
      return String.format("%d %s %s", deltaSeconds, abstractLanguage.getSeconds(), abstractLanguage.getAgo());
    } else if (deltaSeconds < 120) {
      return abstractLanguage.getMinute() + " " + abstractLanguage.getAgo();
    } else if (deltaMinutes < 60) {
      return String.format("%d %s %s", deltaMinutes, abstractLanguage.getMinutes(), abstractLanguage.getAgo());
    } else if (deltaMinutes < 120) {
      return abstractLanguage.getHour() + " " + abstractLanguage.getAgo();
    } else if (deltaMinutes < (24 * 60)) {
      return String.format("%d %s %s", deltaMinutes / 60, abstractLanguage.getHours(), abstractLanguage.getAgo());
    } else if (deltaMinutes < (24 * 60 * 2)) {
      return abstractLanguage.getYesterday();
    } else if (deltaMinutes < (24 * 60 * 7)) {
      return String.format("%d %s %s", deltaMinutes / (60 * 24), abstractLanguage.getDays(), abstractLanguage.getAgo());
    } else if (deltaMinutes < (24 * 60 * 14)) {
      return abstractLanguage.getLastWeek();
    } else if (deltaMinutes < (24 * 60 * 31)) {
      return String
              .format("%d %s %s", deltaMinutes / (60 * 24 * 7), abstractLanguage.getWeeks(), abstractLanguage.getAgo());
    } else if (deltaMinutes < (24 * 60 * 61)) {
      return abstractLanguage.getLastMonth();
    } else if (deltaMinutes < (24 * 60 * 365.25)) {
      return String.format("%d %s %s", deltaMinutes / (60 * 24 * 30), abstractLanguage.getMonths(),
              abstractLanguage.getAgo());
    } else if (deltaMinutes < (24 * 60 * 731)) {
      return abstractLanguage.getLastYear();
    }
    return String
            .format("%d %s %s", deltaMinutes / (60 * 24 * 365), abstractLanguage.getYears(), abstractLanguage.getAgo());
  }


  private long intervalInMillis(Date first, Date second) {
    return Math.abs(first.getTime() - second.getTime());
  }

  public void setLanguage(LanguageType languageType) {
    abstractLanguage = LanguageFactory.getLanguage(languageType);
  }

}
