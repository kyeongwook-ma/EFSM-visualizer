package model.db;

import java.sql.ResultSet;
import java.util.List;

public interface IAllRowGetter {
	<T> List<T> getObject(ResultSet rs);
}
