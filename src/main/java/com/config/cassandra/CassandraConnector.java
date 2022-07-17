package com.config.cassandra;

import java.net.InetSocketAddress;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

public class CassandraConnector {
    private static String contactPoint = "192.168.1.7";
    private static int port = 9042;
    private static String keySpace = "database";
    private static String dataCenter = "datacenter";

    public static void main(String args[]) {

        try (CqlSession session = CqlSession
                                    .builder()
                                    .addContactPoint(new InetSocketAddress(contactPoint, port))
                                    .withLocalDatacenter(dataCenter)
                                    .withKeyspace(keySpace)
                                    .build()) {

                    ResultSet rs = session.execute("select * from table");
                    for (Row row : rs.all()) {
                        System.out.println(row.getString("column_name"));
                    }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}