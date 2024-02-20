-- TestQuestion
INSERT INTO test_question (test_question_content)
values ('여러분이 가장 좋아하는 계절은 언제인가요?');

INSERT INTO test_question (test_question_content)
values ('평소 여가시간을 어떻게 보내시나요?');

-- TestAnswer
-- 1번 선택지
INSERT INTO test_answer (test_answer_content, test_question_id)
VALUES ('봄',  1);

INSERT INTO test_answer (test_answer_content, test_question_id)
VALUES ('여름', 1);

INSERT INTO test_answer (test_answer_content, test_question_id)
VALUES ('가을', 1);

INSERT INTO test_answer (test_answer_content, test_question_id)
VALUES ('겨울', 1);

-- 2번 선택지
INSERT INTO test_answer (test_answer_content, test_question_id)
VALUES ('잠', 2);

INSERT INTO test_answer (test_answer_content, test_question_id)
VALUES ('게임', 2);

INSERT INTO test_answer (test_answer_content, test_question_id)
VALUES ('독서', 2);

INSERT INTO test_answer (test_answer_content, test_question_id)
VALUES ('영화', 2);