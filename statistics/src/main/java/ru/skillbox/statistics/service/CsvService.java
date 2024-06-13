package ru.skillbox.statistics.service;

import java.io.IOException;

/**
 * CsvService
 *
 * @author alex90bar
 */

public interface CsvService {

    byte[] getCsvUserStats() throws IOException;
    byte[] getCsvBookingStats() throws IOException;

}
