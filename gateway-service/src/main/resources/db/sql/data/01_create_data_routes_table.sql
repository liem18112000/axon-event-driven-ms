INSERT INTO `routes` (`id`, `uri`, `service_name`, `path`, `is_auth`, `ia_active`) VALUES
    (1, 'lb://product-service/**', 'product-service', '/product/**', b'1', b'1'),
    (2, 'lb://security-service/**', 'security-service', '/security/**', b'0', b'1'),
    (3, 'lb://product-service/**', 'product-endpoint', '/endpoint/product/**', b'0', b'1');
