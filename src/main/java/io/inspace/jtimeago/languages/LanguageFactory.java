package io.inspace.jtimeago.languages;

import java.util.Locale;

/**
 * LanguageFactory
 */
public class LanguageFactory {

  public static Locale getLanguage(LanguageType languageType){
    switch (languageType){
      case EN:
        return new Locale("en", "US");
      case PL:
        return new Locale("pl", "PL");
      default:
        return new Locale("en", "US");
    }
  }
}
