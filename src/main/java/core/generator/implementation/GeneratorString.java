package core.generator.implementation;

import com.silva.datagenerator.domain.dto.PropertyRequest;
import core.generator.GeneratorData;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;

public class GeneratorString implements GeneratorData {
  Random rdn = new Random();

  @Override
  public Object generate(PropertyRequest property) {
    return RandomStringUtils.random(rdn.nextInt(10), true, false);
  }
}
