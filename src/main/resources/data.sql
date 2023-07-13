DELETE FROM `usuarios`;
DELETE FROM `productos`;

INSERT INTO `usuarios` (`id`, `direccion`, `email`, `nombre`, `password`, `telefono`, `tipo`, `username`) VALUES (1, 'asdf', 'abraham@bedu.com', 'Abraham', '$2a$10$FrvY0Ps7ZYmr5jKPJs0P1OQ3c0ZInp0bJGqJEuNFdH28mRfPbqJTC', NULL, 'USER', NULL);
INSERT INTO `usuarios` (`id`, `direccion`, `email`, `nombre`, `password`, `telefono`, `tipo`, `username`) VALUES (2, 'ddd', 'anna@bedu.com', 'Anna', '$2a$10$DhWGLaQQ2lbP8GU.4HJJBeXuNgsR.raq1muI0UfdOMs1lGSvxJIAq', NULL, 'USER', NULL);
INSERT INTO `usuarios` (`id`, `direccion`, `email`, `nombre`, `password`, `telefono`, `tipo`, `username`) VALUES (3, 'fff', 'jafeth@bedu.com', 'Jafeth', '$2a$10$.iI/hSBBb5aC1wWSE6MsOOfd0cM4Pw3qxrr..b.9/VuzJ4y/BgW1a', NULL, 'USER', NULL);
INSERT INTO `productos` (`id`, `cantidad`, `descripcion`, `imagen`, `nombre`, `precio`, `usuario_id`) VALUES (1, 5, 'SMART LED TV SAMSUNG 50 PULGADAS 4K UHD 50AU7000 HD 21', 'TV.jpg', 'Televisor ', 12.558, 1);
INSERT INTO `productos` (`id`, `cantidad`, `descripcion`, `imagen`, `nombre`, `precio`, `usuario_id`) VALUES (2, 10, 'Impresora Multifuncional HP Smart Tank 530', 'impresora.jpg', 'Impresora', 15.312, 1);
INSERT INTO `productos` (`id`, `cantidad`, `descripcion`, `imagen`, `nombre`, `precio`, `usuario_id`) VALUES (3, 15, 'Celular Samsung Galaxy A54 5G 256GB Verde', 'celular.jpg', 'Celular', 20.555, 1);
INSERT INTO `productos` (`id`, `cantidad`, `descripcion`, `imagen`, `nombre`, `precio`, `usuario_id`) VALUES (4, 20, 'RODE NT1-KIT Large-Diaphragm Cardioid Condenser Microphone', 'micro.jpg', 'Microfono ', 14.771, 1);
INSERT INTO `productos` (`id`, `cantidad`, `descripcion`, `imagen`, `nombre`, `precio`, `usuario_id`) VALUES (5, 5, 'Notebook Gamer Lenovo 15,6\" Ryzen 5 16GB RAM 512GB SSD W11 15ACH6', 'notebook.jpg', 'Notebook', 214.771, 1);
INSERT INTO `productos` (`id`, `cantidad`, `descripcion`, `imagen`, `nombre`, `precio`, `usuario_id`) VALUES (6, 10, 'Lavarropas Drean Carga Frontal 8 Kg Next 8.14P', 'lavarropas.jpg', 'Lavarropas', 150.745, 1);
INSERT INTO `productos` (`id`, `cantidad`, `descripcion`, `imagen`, `nombre`, `precio`, `usuario_id`) VALUES (7, 5, 'Smart TV Hitachi 50\" 4K UHD CDH-LE504KSMART26-F', 'smarttv.jpg', 'Smart TV', 200.512, 1);
INSERT INTO `productos` (`id`, `cantidad`, `descripcion`, `imagen`, `nombre`, `precio`, `usuario_id`) VALUES (8, 10, 'Heladera Electrolux No Frost 265 Lts DFN3000B', 'heladera.jpg', 'Heladera', 480.121, 1);