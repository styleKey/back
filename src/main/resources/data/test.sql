-- TEST_QUESTION
INSERT INTO TEST_QUESTION (TEST_QUESTION_CONTENT, TEST_QUESTION_IMAGE_URL)
VALUES ('약속 시간에 늦어 급하게 옷을 입고 나간 당신, 지금 입고 있는 옷의 색깔은?',
        'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/test/Image_Q1.jpg'),
       ('외출을 위해 옷장 앞에 선 당신, 선호하는 옷의 핏은?', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/test/Image_Q2.jpg'),
       ('플리마켓에서 마음에 드는 가방을 발견했다! 당신이 선택한 가방의 스타일은?',
        'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/test/Image_Q3.jpg'),
       ('우연히 지나가다가 보게 된 신발이 자꾸 눈에 아른거려… 이건 너무 내 스타일이야!',
        'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/test/Image_Q4.jpg'),
       ('오랜만에 맞이한 휴일! 시간에 쫓기지 않는 여유로운 하루, 어떻게 보내면 좋을까?',
        'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/test/Image_Q5.jpg'),
       ('계절도 바뀌어가니 옷을 사야겠어! 어디에서 쇼핑을 할까?', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/test/Image_Q6.jpg'),
       ('마음에 드는 옷이 너무 많아! 이 중에 하나만 골라야 한다면… 가장 중요하게 생각하는 요소는?',
        'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/test/Image_Q7.jpg'),
       ('내가 좋아하는 패션 유튜버가 오랜만에 영상을 올렸어! 어떤 영상이 올라왔을까?',
        'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/test/Image_Q8.jpg');

-- TEST_ANSWER
INSERT INTO test_answer(test_answer_content, test_question_id)
VALUES ('"무난한게 최고야!” 무채색 계열의 컬러', 1),
       ('"따뜻한 느낌이 좋아” 부드러운 파스텔 톤', 1),
       ('"조금은 화려하고 싶어” 독특한 느낌의 메탈릭 컬러', 1),
       ('“눈에 띄는게 좋아!” 채도 높은 컬러', 1),
       ('"편안한게 최고!” 무난한 레귤러핏', 2),
       ('“라인이 드러나는 게 좋아” 몸매를 강조하는 타이트 핏', 2),
       ('"활동적인 느낌이 좋아” 넉넉한 오버핏', 2),
       ('"독특하게 스타일링하고 싶어" 개성적인 믹스매치핏', 2),
       ('미니멀하고 심플한 디자인의 토트백', 3),
       ('레트로 감성의 가죽 숄더백', 3),
       ('넉넉한 수납 공간을 가진 실용적인 백팩', 3),
       ('독특하고 화려한 패턴이 있는 미니백', 3),
       ('독특하고 빈티지한 워커', 4),
       ('활동적이고 편한 스니커즈', 4),
       ('무난하게 신을 수 있는 크록스', 4),
       ('꾸민 날에 신기 좋은 구두', 4),
       ('“환상의 나라로!” 꿈과 희망이 가득한 놀이공원에 가자!', 5),
       ('“좋아하는 사람들과 함께!” 친구들과 함께 화려한 고급 레스토랑에 가서 맛있는 것을 먹어볼까?', 5),
       ('“아웃도어 활동!” 등산이나 자전거 타기 같은 야외 활동을 즐겨볼까?', 5),
       ('"조용한 휴식이 필요해” 숨겨진 분위기 맛집 카페에 가서 여유롭게 책을 읽어야지.', 5),
       ('"고급스러운 디자이너 브랜드와 명품이 있는 백화점이 좋아!"', 6),
       ('"유니크한 아이템이 있는 빈티지샵이나 플리마켓!"', 6),
       ('"다양한 아이템이 있는 인터넷 쇼핑몰에서 쇼핑해야지!"', 6),
       ('"활동적인 라이프스타일에 어울리는 스포츠 브랜드 매장이 좋아!”', 6),
       ('"나만의 스타일을 표현할 수 있는 디자인이 중요해!"', 7),
       ('"일상에서 편안함과 실용성이 중요해!"', 7),
       ('"고급스러운 소재와 품질이 중요해!"', 7),
       ('"사랑스럽고 부드러운 이미지가 중요해!"', 7),
       ('[직장인 vlog] 일주일 동안 입은 데일리 룩 모음. ZIP', 8),
       ('[GRWM] 같이 힙합 페스티벌 가요! #옷 추천 #코디', 8),
       ('[하이틴 감성] 90년대 영화 클루리스 룩 따라 입기!', 8),
       ('[운동 vlog] 브랜드별 바람막이 리뷰! TOP 3 추천', 8);

-- TEST_ANSWER_DETAIL
-- 1번문항
INSERT INTO test_answer_detail(test_answer_id, style_point_id, score)
VALUES (1, 3, 1),
       (1, 2, 1),
       (2, 5, 1),
       (2, 4, 1),
       (3, 7, 1),
       (3, 6, 1),
       (4, 8, 1),
       (4, 1, 1);

-- 2번문항
INSERT INTO test_answer_detail(test_answer_id, style_point_id, score)
VALUES (5, 3, 1),
       (5, 4, 1),
       (5, 5, 1),
       (6, 7, 1),
       (7, 2, 1),
       (7, 8, 1),
       (8, 1, 1),
       (8, 6, 1);

-- 3번문항
INSERT INTO test_answer_detail(test_answer_id, style_point_id, score)
VALUES (9, 3, 1),
       (9, 4, 1),
       (10, 6, 1),
       (11, 2, 1),
       (11, 8, 1),
       (12, 1, 1),
       (12, 5, 1),
       (12, 7, 1);

-- 4번문항
INSERT INTO test_answer_detail(test_answer_id, style_point_id, score)
VALUES (13, 1, 1),
       (13, 6, 1),
       (14, 2, 1),
       (14, 8, 1),
       (15, 4, 1),
       (16, 7, 1),
       (16, 3, 1),
       (16, 5, 1);

-- 5번문항
INSERT INTO test_answer_detail(test_answer_id, style_point_id, score)
VALUES (17, 5, 1),
       (18, 3, 1),
       (18, 7, 1),
       (19, 2, 1),
       (19, 8, 1),
       (20, 1, 1),
       (20, 6, 1),
       (20, 4, 1);

-- 6번문항
INSERT INTO test_answer_detail(test_answer_id, style_point_id, score)
VALUES (21, 3, 1),
       (21, 7, 1),
       (22, 1, 1),
       (22, 6, 1),
       (23, 2, 1),
       (23, 4, 1),
       (23, 5, 1),
       (24, 8, 1);

-- 7번문항
INSERT INTO test_answer_detail(test_answer_id, style_point_id, score)
VALUES (25, 1, 1),
       (25, 6, 1),
       (26, 2, 1),
       (26, 8, 1),
       (26, 4, 1),
       (27, 3, 1),
       (27, 7, 1),
       (28, 5, 1);

INSERT INTO test_answer_detail(test_answer_id, style_point_id, score)
VALUES (29, 4, 1),
       (30, 2, 1),
       (31, 6, 1),
       (32, 8, 1);
