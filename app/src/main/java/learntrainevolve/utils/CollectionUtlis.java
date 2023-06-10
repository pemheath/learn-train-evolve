package learntrainevolve.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static learntrainevolve.utils.NullUtils.ifNotNull;

public class CollectionUtlis {

    private CollectionUtlis () {}

    /**
     * If the parameter is not null, create a new Set with the collections contents.
     * If the parameter is null, return null.
     * @param collectionToWrap The collection to copy into a Set.
     * @param <E> The type of element in the collection.
     * @return A new Set or null.
     */
    public static <E> Set<E> copyToSet(Collection<E> collectionToWrap) {
        return ifNotNull(collectionToWrap, () -> new HashSet<>(collectionToWrap));
    }
}
