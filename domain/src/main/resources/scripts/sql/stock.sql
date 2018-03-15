CREATE TABLE IF NOT EXISTS stock (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  stocks INT NOT NULL DEFAULT 0,
  notes JSON,
  create_at DATETIME NOT NULL DEFAULT NOW(),
  update_at DATETIME NOT NULL DEFAULT NOW(),
  del_flag TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
)
DEFAULT CHARSET = UTF8MB4;

INSERT INTO stock (name, stocks, notes) VALUES ('うまいぼう', 210, '{"color": "red", "status": "done"}');
INSERT INTO stock (name, stocks, notes) VALUES ('ぽてとふらい', 300, '{"color": "green", "shape": "rectangle"}');
INSERT INTO stock (name, stocks, notes) VALUES ('きなこぼう',  60, '{"color": "blue", "shape": "triangle", "status": "not started"}');
INSERT INTO stock (name, stocks, notes) VALUES ('なまいきびーる', 250, '{"color": "red", "status": "in progress"}');
INSERT INTO stock (name, stocks, notes) VALUES ('ふるーつよーぐる', 930, '{"status": "waiting"}');
INSERT INTO stock (name, stocks, notes) VALUES ('みにこーら',  10, '{"shape": "pentagon", "status": "waiting"}');
INSERT INTO stock (name, stocks, notes) VALUES ('こざくらもち', 330, '{"color": "green", "shape": "rectangle", "status": "not started"}');
INSERT INTO stock (name, stocks, notes) VALUES ('たまごあいす',  20, '{"color": "red", "shape": "hexagon", "status": "waiting"}');
INSERT INTO stock (name, stocks, notes) VALUES ('もちたろう', 100, '{"color": "blue"}');
