INSERT INTO public.users (id,email,first_name,last_name,"password","position","role",salary) VALUES
	 ('82a4b195-2ed3-47f5-8c44-34e55eb7cfea'::uuid,'gergana.ivanova@test.com','Gergana','Ivanova','$2a$10$1EjMQhcWrqwHp3uNul7XC.ofmp.WC1voAGX9q5/J/HuPQr0tzsiB2',NULL,'ROLE_USER',NULL),
	 ('39f425a0-0478-49d3-9666-cc21e074ecc6'::uuid,'ivelina.petrova@test.com','Ivelina','Petrova','$2a$10$FqcoXAsIChoPbFvhHVzIGeBJslmMq82GNr6fUFCAUAEk.EUjp3/fG',NULL,'ROLE_ADMIN',NULL),
	 ('2dc3d6e4-be44-44f6-81f5-d058de8bd1b5'::uuid,'kiril.georgiev@test.com','Kiril','Georgiev','$2a$10$7phz5a8SPDm0O9QlPOp8JeU.USnV/LYP1z.svt3sZl.C4JJ4PxkNK',NULL,'ROLE_USER',NULL)
	 ON CONFLICT (id) DO NOTHING;

INSERT INTO public.products (id,description,"name",price) VALUES
	 (1,'Test','Jacket',149.99),
	 (2,'Test','Dress',249.99),
	 (3,'Test','Jeans',49.99),
	 (4,'Test','White Jeans',49.99),
	 (5,'Test','T-Shirt',29.99),
	 (6,'Test','Blazer',129.99)
	 ON CONFLICT (id) DO NOTHING;

INSERT INTO inventory (product_id, quantity)
SELECT id, 0 FROM products
WHERE id NOT IN (SELECT product_id FROM inventory);