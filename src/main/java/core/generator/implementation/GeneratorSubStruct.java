package core.generator.implementation;

import com.silva.datagenerator.domain.dto.ListValueRequest;
import com.silva.datagenerator.domain.dto.PropertyRequest;
import com.silva.datagenerator.domain.dto.StructRequest;
import com.silva.datagenerator.domain.dto.TypeValue;
import core.generator.GeneratorData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

public class GeneratorSubStruct implements GeneratorData {
  static Random rdn = new Random();

  private static final Map<TypeValue, GeneratorData> generateByType = Map.of(
      TypeValue.INTEGER, new GeneratorInteger(),
      TypeValue.STRING, new GeneratorString(),
      TypeValue.SUB_STRUCT, new GeneratorSubStruct()
  );

  @Override
  public Object generate(PropertyRequest property) {
    return go(property.getSubStructure());
  }

  public static List<Object> go(StructRequest structRequest) {
    var result = new ArrayList<>();
    IntStream.range(0, structRequest.getQuantity())
        .forEach(i -> result.add(generateRepeat(structRequest)));
    return result;
  }

  private static Map<String, Object> generateRepeat(StructRequest struct) {
    var obj = new HashMap<String, Object>();
    if (!Objects.isNull(struct.getListValues())) {
      struct
          .getProperties()
          .stream()
          .filter(x -> !Objects.isNull(x.getNameList()))
          .forEach(prop -> obj.put(
              prop.getName(),
              generatePropertyFromList(prop, struct.getListValues()))
          );
    }
    struct
        .getProperties()
        .stream()
        .filter(x -> Objects.isNull(x.getNameList()))
        .forEach(x ->
            obj.put(x.getName(),
                generateByType.get(x.getTypeValue()).generate(x)
            )
        );
    return obj;
  }

  private static Object generatePropertyFromList(
      PropertyRequest prop,
      List<ListValueRequest<?>> listValues) {
    var list = listValues.stream()
        .filter(y -> y.getName().equals(prop.getNameList()))
        .findFirst()
        .orElseThrow()
        .getValues();
    return list.get(rdn.nextInt(list.size()));
  }
}
