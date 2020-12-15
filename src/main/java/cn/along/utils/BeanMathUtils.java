package cn.along.utils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BeanMathUtils {


    private static  <W,P,K> List<W> listFilterByMap(List<W> wholeList, BiPredicate<W, Map> predicate,Map<K,P> partKeyMap){
        return wholeList.stream().filter( (o) -> predicate.test(o,partKeyMap)).collect(Collectors.toList());
    }

    public static <W,P, K> List<W> substract(List<W> wholeList, List<P> partList, Function<W,K> wholeKeyMapper, Function<P, K> partKeyMapper) {
        if(null == wholeList){
            return Collections.EMPTY_LIST;
        }
        if(null == partList){
            return wholeList;
        }
        Map<K,P> partKeyList = partList.stream().collect(Collectors.toMap(partKeyMapper,P->P,(oval,nval)-> oval, TreeMap::new));
        return listFilterByMap(wholeList,(w, map) -> !map.containsKey(wholeKeyMapper.apply(w)), partKeyList);
    }

    public static <W,P, K> List<W> retain(List<W> wholeList, List<P> partList, Function<W,K> wholeKeyMapper, Function<P, K> partKeyMapper) {
        if(null == wholeList){
            return Collections.EMPTY_LIST;
        }
        if(null == partList){
            return wholeList;
        }
        Map<K,P> partKeyList = partList.stream().collect(Collectors.toMap(partKeyMapper,P -> P));
        return listFilterByMap(wholeList,(w, map) -> map.containsKey( wholeKeyMapper.apply(w)), partKeyList);
    }

    public static <T,K> List<T> distinct(List<T> wholeList, Function<T,K> keyMapper) {
        Map<K, T> map = new TreeMap<>();
        return wholeList.stream().filter( o -> {
            T out = map.put(keyMapper.apply(o), o);
            return null == out;
        }).collect(Collectors.toList());

    }
}
