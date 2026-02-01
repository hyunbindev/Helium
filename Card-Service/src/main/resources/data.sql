INSERT INTO card_grade (grade_id, weight, color_code, enhance_rate)
VALUES
    ('NORMAL',5000,'#C0C0C0',1.0),
    ('RARE',2500,'#4DA6FF',1.0),
    ('EPIC',1500,'#B266FF',1.0),
    ('LEGEND',1000,'#FFD700',1.0)
ON DUPLICATE KEY UPDATE grade_id = grade_id;