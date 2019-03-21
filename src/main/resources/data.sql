--插入一个角色数据，其id为1，name为'super_admin'
--如果已经有id=1的角色数据，则把它的name字段改为'super_admin'
INSERT INTO role VALUES(1, 'super_admin')
ON DUPLICATE KEY UPDATE name='super_admin';

INSERT INTO role VALUES(2, 'admin')
ON DUPLICATE KEY UPDATE name='admin';

INSERT INTO role VALUES(3, 'user')
ON DUPLICATE KEY UPDATE name='user';

--插入一个账户数据，其id为1，角色是超级管理员；如果已经有id=1的账户，则更新这条数据
INSERT INTO account VALUES(1, 'laidw', 'laidw', '233333333@qq.com', true, 1, 'verify_code')
ON DUPLICATE KEY UPDATE name='laidw', password='laidw', email='233333333@qq.com', is_active=true, role_id=1, verify_code='verify_code'