package com.silva.datagenerator.utils;

public class StringUtil {
  private StringUtil() {
  }

  public static String snakeCase(String text) {
    //limpiar todos los espacios
    text = text.replaceAll("\\s+", " ").trim();
    //split por espacio o mayúsculas
    text = text.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    return text.replace(" ", "_");
  }
}
