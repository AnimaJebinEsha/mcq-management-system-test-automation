package tech.innospace.utils;

import io.github.cdimascio.dotenv.Dotenv;

public final class EnvLoader {

    private EnvLoader() {
        throw new AssertionError("Not instantiable!");
    }

    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    public static String get(String key) {
        return dotenv.get(key);
    }
}
