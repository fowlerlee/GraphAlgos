package threads;

import java.util.Map;
import java.util.HashMap;

import com.google.gson.JsonElement;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class VectorClock {
    private final Map<Integer, Integer> vc = new HashMap<>();
    private final int n;

    public VectorClock(final int n, final int dimension) {
        this.vc.put((n), 0);
        this.n = n;
    }

    public JsonElement tick() {
        this.vc.merge(n, 1, Integer::sum);
        return toJson();
    }

    public JsonElement tickAndMerge(final JsonObject json) {
        tick();

        // deserialize
        final TypeToken<Map<Integer, Integer>> typeToken = new TypeToken<Map<Integer, Integer>>() {
        };
        final Map<Integer, Integer> m = new Gson().fromJson(json, typeToken);

        m.keySet().forEach(key -> this.vc.merge(key, m.get(key), Integer::max));
        return toJson();
    }

    public JsonElement toJson() {
        return new Gson().toJsonTree(this.vc);
    }
}
