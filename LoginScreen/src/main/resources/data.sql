/*従業員テーブルのデータ*/

/*ユーザーマスターのデータ(アドミン権限)*/
INSERT INTO m_user(user_id,password,user_name,birthday,age,marriage,role)
VALUES('yamada@xxx.co.jp','$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.QjilhuIPa','password','山田太郎','1990-01-01',28,false,'ROLE_ADMIN');

/*ユーザーマスタのデータ(一般権限) */
INSERT INTO m_user(user_id,password,user_name,birthday,age,marriage,role)
VALUES('tamura@xxx.co.jp','password',$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.QjilhuIPa','田村達也','1996-11-05',31,false,'ROLE_GENERAL');