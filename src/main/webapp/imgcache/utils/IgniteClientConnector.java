package imgcache.utils;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;

import java.util.function.Consumer;
import java.util.function.Function;

public final class IgniteClientConnector {
    private IgniteClientConnector() {
        throw new Error("IgniteClientConnector is just Utility Class");
    }

    public static void accept(Consumer<Ignite> consumer) {
        Ignite ignite = Ignition.ignite();
        consumer.accept(ignite);
    }

    public static <T> T apply(Function<Ignite, T> function) {
        Ignite ignite = Ignition.ignite();
        return function.apply(ignite);
    }
}
