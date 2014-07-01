package io.inspace.jtimeago.languages;

import io.inspace.jtimeago.languages.lang.AbstractLanguage;
import io.inspace.jtimeago.languages.lang.EN;
import io.inspace.jtimeago.languages.lang.PL;

/**
 * LanguageFactory
 */
public class LanguageFactory {

  public static AbstractLanguage getLanguage(LanguageType languageType){
    switch (languageType){
      case EN:
        return new EN();
      case PL:
        return new PL();
      default:
        return new AbstractLanguage();
    }
  }
}
