package com.util;

import java.sql.SQLException;
import java.util.List;

public interface Entity {
	void insert() throws SQLException;
    void update() throws SQLException;
    void delete() throws SQLException;
    Object selectById(int id) throws SQLException;
    List<?> selectAll() throws SQLException;
}
