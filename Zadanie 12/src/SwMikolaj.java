import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

class SwMikolaj implements Inwentaryzator{
    @Override
    public Map<String, Integer> inwentaryzacja(List<String> listaKlas) {
        Map<String,Integer> result = new HashMap<>();
        HashSet<String> nameAllowed = new HashSet<>(Set.of("bombki","lancuchy","cukierki","prezenty","szpice","lampki"));

        listaKlas.forEach((classToCount) ->{
            try {
                Class<?> MyClass = Class.forName(classToCount);
                Object obj = MyClass.newInstance();
                Field[] fields = MyClass.getFields();

                for(Field f:fields){
                    if(!Arrays.asList(f.toString().split(" ")).contains("int") || Modifier.isPrivate(f.getModifiers()) || Modifier.isStatic(f.getModifiers())){
                        continue;
                    }

                    try {
                        MyClass.getDeclaredField(f.getName());
                    } catch (NoSuchFieldException e) {
                        continue;
                    }

                    String name = f.getName();
                    if(!nameAllowed.contains(name)){
                        continue;
                    }
                    int value = (int) f.getInt(obj);
                    if(result.containsKey(name)){
                        result.put(name,result.get(name)+value);
                    }else{
                        result.put(name,value);
                    }
                }
            } catch (ClassNotFoundException | IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            }
        });
        return result;
    }
}