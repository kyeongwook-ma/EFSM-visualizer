CREATE TABLE `SeqTable` (
	`u_id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`time`	INTEGER
);

CREATE TABLE `BMTable` (
	`u_id`	INTEGER,
	`seq_id` INTEGER,
	`touch_class`	TEXT,
	`touch_mode`	TEXT,
	`time_stamp`	INTEGER,
	`x`	INTEGER,
	`y`	INTEGER,
	PRIMARY KEY(u_id, seq_id)
	FOREIGN KEY(u_id) REFERENCES SeqTable(u_id)
);

