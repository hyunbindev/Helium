INSERT INTO card_grade (grade_id, weight, color_code, enhanceRate)
VALUES
    ('NORMAL',5000,#C0C0C0,1.0),
    ('RARE',2500,#C0C0C0,1.0),
    ('EPIC',1500,#C0C0C0,1.0),
    ('LEGEND',1000,#C0C0C0,1.0),
ON CONFLICT (grade_id) DO NOTHING;