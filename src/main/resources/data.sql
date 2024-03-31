-- STYLE_POINT
insert into style_point (style_point_title, style_point_description, style_point_image) values
('Unique', '변화하는 트렌드를 반영하여 평범하지 않고 개성있는 디테일을 추구하는 스타일',
'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/stylepoint/unique_point.png'),

('Street', '격식을 갖추지 않고 길거리에서 편하게 입을 수 있는 힙한 스타일',
'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/stylepoint/street_point.png'),

('Modern', '장식적인 것 없이 깔끔하고 심플하며 직선적인 실루엣을 추구하는 스타일',
'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/stylepoint/modern_point.png'),

('Normal', '일상적이고 평범한 착장이 무난하지 않도록 센스있는 포인트가 들어간 스타일',
'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/stylepoint/normal_point.png'),

('Lovely', '사랑스러운 소녀같이 귀엽고 로맨틱하면서 여성스러운 무드를 강조한 스타일',
'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/stylepoint/lovely_point.jpg'),

('Retro', '1990-2000년대의 감성을 재해석하여 오래된 듯한 멋진 느낌이 드는 스타일',
'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/stylepoint/retro_point.jpg'),

('Glam', '섹시함이 강조되는 화려하고 여성스러운 스타일',
'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/stylepoint/glam_point.png'),

('Active', '스포츠웨어와 일상복의 경계를 허물고 활동적인 이미지를 표현하는 스타일',
'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/stylepoint/active_point.png');

-- CATEGORY
insert into category (category_title) values ('TOP');
insert into category (category_title) values ('OUTER');
insert into category (category_title) values ('DRESS');
insert into category (category_title) values ('BOTTOM');
insert into category (category_title) values ('BAG');
insert into category (category_title) values ('SHOES');
insert into category (category_title) values ('ACC');

-- TEST_QUESTION
insert into test_question (test_question_content, test_question_image_url)
values ('약속 시간에 늦어 급하게 옷을 입고 나간 당신, \n지금 입고 있는 옷의 색깔은?',
        'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/test/Image_Q1.jpg'),
       ('외출을 위해 옷장 앞에 선 당신, \n선호하는 옷의 핏은?', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/test/Image_Q2.jpg'),
       ('플리마켓에서 마음에 드는 가방을 발견했다! \n당신이 선택한 가방의 스타일은?',
        'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/test/Image_Q3.jpg'),
       ('우연히 지나가다가 보게 된 신발이 \n자꾸 눈에 아른거려… \n이건 너무 내 스타일이야!',
        'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/test/Image_Q4.jpg'),
       ('오랜만에 맞이한 휴일! \n시간에 쫓기지 않는 여유로운 하루, \n어떻게 보내면 좋을까?',
        'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/test/Image_Q5.jpg'),
       ('계절도 바뀌어가니 옷을 사야겠어! \n어디에서 쇼핑을 할까?', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/test/Image_Q6.jpg'),
       ('마음에 드는 옷이 너무 많아! \n이 중에 하나만 골라야 한다면… \n가장 중요하게 생각하는 요소는?',
        'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/test/Image_Q7.jpg'),
       ('내가 좋아하는 패션 유튜버가 \n오랜만에 영상을 올렸어! \n어떤 영상이 올라왔을까?',
        'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/test/Image_Q8.jpg');

-- TEST_ANSWER
insert into test_answer(test_answer_content, test_question_id)
values ('“무난한게 최고야!” 무채색 계열의 컬러', 1),
       ('“따뜻한 느낌이 좋아” 부드러운 파스텔 톤', 1),
       ('“조금은 화려하고 싶어” 독특한 느낌의 메탈릭 컬러', 1),
       ('“눈에 띄는게 좋아!” 채도 높은 컬러', 1),
       ('“편안한게 최고!” 무난한 레귤러핏', 2),
       ('“라인이 드러나는 게 좋아” 몸매를 강조하는 타이트 핏', 2),
       ('“활동적인 느낌이 좋아” 넉넉한 오버핏', 2),
       ('“독특하게 스타일링하고 싶어” 개성적인 믹스매치핏', 2),
       ('미니멀하고 심플한 디자인의 토트백', 3),
       ('레트로 감성의 가죽 숄더백', 3),
       ('넉넉한 수납 공간을 가진 실용적인 백팩', 3),
       ('독특하고 화려한 패턴이 있는 미니백', 3),
       ('독특하고 빈티지한 워커', 4),
       ('활동적이고 편한 스니커즈', 4),
       ('무난하게 신을 수 있는 크록스', 4),
       ('꾸민 날에 신기 좋은 구두', 4),
       ('“환상의 나라로!” 꿈과 희망이 가득한 놀이공원에 가자!', 5),
       ('“좋아하는 사람들과 함께!” 친구들과 함께 \n화려한 고급 레스토랑에 가서 맛있는 것을 먹어볼까?', 5),
       ('“아웃도어 활동!” 등산이나 자전거 타기 같은 \n야외 활동을 즐겨볼까?', 5),
       ('“조용한 휴식이 필요해” 숨겨진 분위기 맛집 \n카페에 가서 여유롭게 책을 읽어야지.', 5),
       ('“고급스러운 디자이너 브랜드와 \n명품이 있는 백화점이 좋아!”', 6),
       ('“유니크한 아이템이 있는 빈티지샵이나 플리마켓!”', 6),
       ('“다양한 아이템이 있는 인터넷 쇼핑몰에서 쇼핑해야지!”', 6),
       ('“활동적인 라이프스타일에 어울리는 \n스포츠 브랜드 매장이 좋아!”', 6),
       ('“나만의 스타일을 표현할 수 있는 디자인이 중요해!”', 7),
       ('“일상에서 편안함과 실용성이 중요해!”', 7),
       ('“고급스러운 소재와 품질이 중요해!”', 7),
       ('“사랑스럽고 부드러운 이미지가 중요해!”', 7),
       ('[직장인 vlog] 일주일 동안 입은 데일리 룩 모음. ZIP', 8),
       ('[GRWM] 같이 힙합 페스티벌 가요! #옷 추천 #코디', 8),
       ('[하이틴 감성] 90년대 영화 클루리스 룩 따라 입기!', 8),
       ('[운동 vlog] 브랜드별 바람막이 리뷰! TOP 3 추천', 8);

-- TEST_ANSWER_DETAIL
-- 1번문항
insert into test_answer_detail(test_answer_id, style_point_id, score)
values (1, 3, 1),
       (1, 2, 1),
       (2, 5, 1),
       (2, 4, 1),
       (3, 7, 1),
       (3, 6, 1),
       (4, 8, 1),
       (4, 1, 1);

-- 2번문항
insert into test_answer_detail(test_answer_id, style_point_id, score)
values (5, 3, 1),
       (5, 4, 1),
       (5, 5, 1),
       (6, 7, 1),
       (7, 2, 1),
       (7, 8, 1),
       (8, 1, 1),
       (8, 6, 1);

-- 3번문항
insert into test_answer_detail(test_answer_id, style_point_id, score)
values (9, 3, 1),
       (9, 4, 1),
       (10, 6, 1),
       (11, 2, 1),
       (11, 8, 1),
       (12, 1, 1),
       (12, 5, 1),
       (12, 7, 1);

-- 4번문항
insert into test_answer_detail(test_answer_id, style_point_id, score)
values (13, 1, 1),
       (13, 6, 1),
       (14, 2, 1),
       (14, 8, 1),
       (15, 4, 1),
       (16, 7, 1),
       (16, 3, 1),
       (16, 5, 1);

-- 5번문항
insert into test_answer_detail(test_answer_id, style_point_id, score)
values (17, 5, 1),
       (18, 3, 1),
       (18, 7, 1),
       (19, 2, 1),
       (19, 8, 1),
       (20, 1, 1),
       (20, 6, 1),
       (20, 4, 1);

-- 6번문항
insert into test_answer_detail(test_answer_id, style_point_id, score)
values (21, 3, 1),
       (21, 7, 1),
       (22, 1, 1),
       (22, 6, 1),
       (23, 2, 1),
       (23, 4, 1),
       (23, 5, 1),
       (24, 8, 1);

-- 7번문항
insert into test_answer_detail(test_answer_id, style_point_id, score)
values (25, 1, 1),
       (25, 6, 1),
       (26, 2, 1),
       (26, 8, 1),
       (26, 4, 1),
       (27, 3, 1),
       (27, 7, 1),
       (28, 5, 1);

insert into test_answer_detail(test_answer_id, style_point_id, score)
values (29, 4, 1),
       (30, 2, 1),
       (31, 6, 1),
       (32, 8, 1);

-- IMAGE
-- Unique Point Brand Image
insert into image (image_type, image_file_name, image_url, image_is_used, deleted_at) values
('BRAND', 'brand/saltypebble_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/saltypebble_logo.png', 1, null),
('BRAND', 'brand/openyy_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/openyy_logo.png', 1, null),
('BRAND', 'brand/odlyworkshop_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/odlyworkshop_logo.png', 1, null),
('BRAND', 'brand/anderssonbell_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/anderssonbell_logo.png', 1, null),

-- Street Point Brand Image
('BRAND', 'brand/stussy_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/stussy_logo.png', 1, null),
('BRAND', 'brand/mschf_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/mschf_logo.png', 1, null),
('BRAND', 'brand/leey_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/leey_logo.png', 1, null),
('BRAND', 'brand/seo_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/seo_logo.png', 1, null),

-- Modern Point Brand Image
('BRAND', 'brand/cos_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/cos_logo.png', 1, null),
('BRAND', 'brand/le17septembrehomme_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/le17septembrehomme_logo.png', 1, null),
('BRAND', 'brand/facadapattern_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/facadapattern_logo.png', 1, null),
('BRAND', 'brand/notia_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/notia_logo.png', 1, null),

-- Normal Point Brand Image
('BRAND', 'brand/insilencewomen_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/insilencewomen_logo.png', 1, null),
('BRAND', 'brand/enor_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/enor_logo.png', 1, null),
('BRAND', 'brand/studiotable_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/studiotable_logo.png', 1, null),
('BRAND', 'brand/matinkim_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/matinkim_logo.png', 1, null),

-- Lovely Point Brand Image
('BRAND', 'brand/grove_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/grove_logo.png', 1, null),
('BRAND', 'brand/glowny_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/glowny_logo.png', 1, null),
('BRAND', 'brand/pehrt_logo.jpg', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/pehrt_logo.jpg', 1, null),
('BRAND', 'brand/magarinfingers_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/magarinfingers_logo.png', 1, null),

-- Retro Point Brand Image
('BRAND', 'brand/sculptor_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/sculptor_logo.png', 1, null),
('BRAND', 'brand/teket_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/teket_logo.png', 1, null),
('BRAND', 'brand/badee_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/badee_logo.png', 1, null),
('BRAND', 'brand/whynotus_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/whynotus_logo.png', 1, null),

-- Glam Point Brand Image
('BRAND', 'brand/painorpleasure_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/painorpleasure_logo.png', 1, null),
('BRAND', 'brand/notyourrose_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/notyourrose_logo.png', 1, null),
('BRAND', 'brand/yuse_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/yuse_logo.png', 1, null),
('BRAND', 'brand/threetimes_logo.jpg', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/threetimes_logo.jpg', 1, null),

-- Active Point Brand Image
('BRAND', 'brand/ojos_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/ojos_logo.png', 1, null),
('BRAND', 'brand/coyseio_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/coyseio_logo.png', 1, null),
('BRAND', 'brand/hugyourskin_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/hugyourskin_logo.png', 1, null),
('BRAND', 'brand/sansangear_logo.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/brand/sansangear_logo.png', 1, null),

-- CoordinateLook Image
('COORDINATE_LOOK','coordinateLook/unique_cody_1.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/coordinateLook/unique_cody_1.png', 1, null),
('COORDINATE_LOOK','coordinateLook/street_cody_1.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/coordinateLook/street_cody_1.png', 1, null),
('COORDINATE_LOOK','coordinateLook/modern_cody_1.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/coordinateLook/modern_cody_1.png', 1, null),
('COORDINATE_LOOK','coordinateLook/normal_cody_1.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/coordinateLook/normal_cody_1.png', 1, null),
('COORDINATE_LOOK','coordinateLook/lovely_cody_1.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/coordinateLook/lovely_cody_1.png', 1, null),
('COORDINATE_LOOK','coordinateLook/retro_cody_1.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/coordinateLook/retro_cody_1.png', 1, null),
('COORDINATE_LOOK','coordinateLook/glam_cody_1.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/coordinateLook/glam_cody_1.png', 1, null),
('COORDINATE_LOOK','coordinateLook/active_cody_1.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/coordinateLook/active_cody_1.png', 1, null),

-- ITEM Image
-- Unique Point 대표 코디룩 아이템 (6개)
('ITEM','item/unique_cody_1_item_1.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/item/unique_cody_1_item_1.png', 1, null),
('ITEM','item/unique_cody_1_item_2.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/item/unique_cody_1_item_2.png', 1, null),
('ITEM','item/unique_cody_1_item_3.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/item/unique_cody_1_item_3.png', 1, null),
('ITEM','item/unique_cody_1_item_4.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/item/unique_cody_1_item_4.png', 1, null),
('ITEM','item/unique_cody_1_item_5.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/item/unique_cody_1_item_5.png', 1, null),
('ITEM','item/unique_cody_1_item_6.png', 'https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/item/unique_cody_1_item_6.png', 1, null);

-- BRAND
insert into brand (brand_title, brand_title_eng, brand_site_url, style_point_id, brand_image_id) values
-- Unique Point Brand
('솔티페블', 'SALTY PEBBLE', 'https://saltypebble.com/', 1, 1),
('오픈 와이와이', 'OPEN YY', 'https://en.theopenproduct.com/', 1, 2),
('오들리워크샵', 'ODLYWORKSHOP', 'https://odlyworkshop.com/', 1, 3),
('앤더슨벨', 'ANDERSSON BELL', 'https://anderssonbell.com/', 1, 4),

-- Street Point Brand
('스투시', 'STUSSY', 'https://kr.stussy.com/', 2, 5),
('미스치프', 'MSCHF', 'https://mischief.co.kr/', 2, 6),
('엘이이와이', 'LEEY', 'https://www.leey.kr/', 2, 7),
('에스이오', 'S/E/O', 'https://ko.s-e-o.co.kr/', 2, 8),

-- Modern Point Brand
('코스', 'COS', 'https://www.cos.com/ko-kr/index.html', 3, 9),
('르917', 'LE17SEPTEMBRE', 'https://le17septembre.com/', 3, 10),
('파사드패턴', 'FACADA PATTERN', 'https://facadepattern.co.kr/', 3, 11),
('노티아', 'NOTIA', 'https://notia.kr/', 3, 12),

-- Normal Point Brand
('인사일런스 우먼', 'INSILENCE WOMEN', 'https://insilence.co.kr/', 4, 13),
('엔오르', 'ENOR', 'https://enor-shop.com/index.html', 4, 14),
('스튜디오테이블', 'STUDIOTABLE PATTERN', 'https://studiotable.kr/', 4, 15),
('마뗑킴', 'MATIN KIM', 'https://matinkim.com/', 4, 16),

-- Lovely Point Brand
('그로브', 'GROVE', 'https://grovestore.com/index.html', 5, 17),
('글로니', 'GLOWNY', 'https://glowny.co.kr/', 5, 18),
('페르트', 'PEHRT', 'https://pehrt.co.kr/', 5, 19),
('마가린핑거스', 'MAGARIN FINGERS', 'https://margarinfingers.com/', 5, 20),

-- Retro Point Brand
('스컬프터', 'SCULPTOR', 'https://sculptorpage.com/', 6, 21),
('테켓', 'TEKET', 'https://te-ket.com/', 6, 22),
('배디', 'BADEE', 'https://badee.kr/', 6, 23),
('와이낫어스', 'WHYNOTUS', 'https://whynotus.kr/index.html', 6, 24),

-- Glam Point Brand
('페인오어플레져', 'PAIN OR PLEASURE', 'https://painorpleasure.co.kr/', 7, 25),
('낫유어로즈', 'NOT YOUR ROSE', 'https://notyourrose.com/', 7, 26),
('유즈', 'YUSE', 'https://yuse.co.kr/', 7, 27),
('쓰리타임즈', 'THREETIMES', 'https://threetimes.kr/', 7, 28),

-- Active Point Brand
('오호스', 'OJOS', 'https://ojos.kr/', 8, 29),
('코이세이오', 'COYSEIO', 'https://m.smarturbanuseful.com/', 8, 30),
('허그유어스킨', 'HUG YOUR SKIN', 'https://hugyourskin.kr/', 8, 31),
('산산기어', 'SAN SAN GEAR', 'https://sansangear.com/', 8, 32);

-- COORDINATE_LOOK
insert into coordinate_look(coordinate_look_title, style_point_id, coordinate_look_image_id, like_count) values
('유니크 코디룩 1', 1, 33, 0),
('스트릿 코디룩 1', 2, 34, 0),
('모던 코디룩 1', 3, 35, 0),
('노멀 코디룩 1', 4, 36, 0),
('러블리 코디룩 1', 5, 37, 0),
('레트로 코디룩 1', 6, 38, 0),
('글램 코디룩 1', 7, 39, 0),
('액티브 코디룩 1', 8, 40, 0);

-- ITEM
insert into item(item_title, item_sales_link, category_id, brand_id, coordinate_look_id, like_count, item_image_id) values
('아이템 1', '아이템 판매 링크', 1, 1, 1, 0, 41),
('아이템 2', '아이템 판매 링크', 1, 1, 1, 0, 42),
('아이템 3', '아이템 판매 링크', 1, 1, 1, 0, 43),
('아이템 4', '아이템 판매 링크', 1, 1, 1, 0, 44),
('아이템 5', '아이템 판매 링크', 1, 1, 1, 0, 45),
('아이템 6', '아이템 판매 링크', 1, 1, 1, 0, 46);
