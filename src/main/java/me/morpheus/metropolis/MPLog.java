package me.morpheus.metropolis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MPLog {

    private static final Logger LOGGER = LoggerFactory.getLogger(Metropolis.ID);

    public static Logger getLogger() {
        return LOGGER;
    }

    private MPLog() {}
}
