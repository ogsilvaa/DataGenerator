package core.generator.implementation;

import com.silva.datagenerator.domain.dto.PropertyRequest;
import core.generator.GeneratorData;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;

public class GeneratorString implements GeneratorData {
  Random rdn = new Random();

  @Override
  public Object generate(PropertyRequest property) {
    var min = property.getMinValue();
    var max = property.getMaxValue();
    var length = 0;

    if (min == null) {
      min = 0;
    }
    if (max == null) {
      max = min+10;
    }

    length = rdn.nextInt(max-min)+min;

    return RandomStringUtils.random(length, true, false);
  }
}
