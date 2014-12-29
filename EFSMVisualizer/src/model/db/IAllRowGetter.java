package model.db;

import java.sql.ResultSet;
import java.util.List;

public interface IAllRowGetter {
	<T> T getObject(ResultSet rs);
	String getSql();
}
