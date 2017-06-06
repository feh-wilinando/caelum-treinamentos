INSERT INTO course(code, description, duration)
SELECT * FROM  (SELECT 'FJ-11', 'Object Orientation', 144000)         WHERE NOT EXISTS (SELECT code FROM course WHERE code = 'FJ-11') UNION ALL
SELECT * FROM  (SELECT 'FJ-21', 'Java Web', 144000)                   WHERE NOT EXISTS (SELECT code FROM course WHERE code = 'FJ-21') UNION ALL
SELECT * FROM  (SELECT 'FJ-22', 'Spring, Test, Git & Maven', 72000)   WHERE NOT EXISTS (SELECT code FROM course WHERE code = 'FJ-22') UNION ALL
SELECT * FROM  (SELECT 'FJ-25', 'Hibernate, JPA & EJB Lite', 144000)  WHERE NOT EXISTS (SELECT code FROM course WHERE code = 'FJ-25') UNION ALL
SELECT * FROM  (SELECT 'FJ-26', 'JSF & Primefaces', 144000)           WHERE NOT EXISTS (SELECT code FROM course WHERE code = 'FJ-26') UNION ALL
SELECT * FROM  (SELECT 'FJ-27', 'Spring Framework', 144000)           WHERE NOT EXISTS (SELECT code FROM course WHERE code = 'FJ-27') UNION ALL
SELECT * FROM  (SELECT 'FJ-36', 'Integrations in Java', 144000)       WHERE NOT EXISTS (SELECT code FROM course WHERE code = 'FJ-36') UNION ALL
SELECT * FROM  (SELECT 'FJ-37', 'Java EE', 216000)                    WHERE NOT EXISTS (SELECT code FROM course WHERE code = 'FJ-37') UNION ALL
SELECT * FROM  (SELECT 'FJ-87', 'Agile Practices', 72000)             WHERE NOT EXISTS (SELECT code FROM course WHERE code = 'FJ-87') UNION ALL
SELECT * FROM  (SELECT 'FJ-91', 'Java Architecture', 144000)          WHERE NOT EXISTS (SELECT code FROM course WHERE code = 'FJ-91')
