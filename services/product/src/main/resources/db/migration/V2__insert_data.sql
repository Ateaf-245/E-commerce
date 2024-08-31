-- Insert categories
INSERT INTO category (id, description, name) VALUES 
    (nextval('category_seq'), 'Electronics and gadgets', 'Electronics'),
    (nextval('category_seq'), 'Books of various genres', 'Books'),
    (nextval('category_seq'), 'Kitchen and home appliances', 'Home & Kitchen'),
    (nextval('category_seq'), 'Fitness and sports equipment', 'Sports');

-- Insert products for Electronics category
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES 
    (nextval('product_seq'), 'Latest smartphone with advanced features', 'Smartphone', 100, 699.99, (SELECT id FROM category WHERE name = 'Electronics')),
    (nextval('product_seq'), 'High-definition 4K TV', '4K TV', 50, 1299.99, (SELECT id FROM category WHERE name = 'Electronics')),
    (nextval('product_seq'), 'Noise-cancelling wireless headphones', 'Headphones', 200, 199.99, (SELECT id FROM category WHERE name = 'Electronics')),
    (nextval('product_seq'), 'Portable Bluetooth speaker', 'Bluetooth Speaker', 150, 99.99, (SELECT id FROM category WHERE name = 'Electronics'));

-- Insert products for Books category
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES 
    (nextval('product_seq'), 'Bestselling mystery novel', 'Mystery Novel', 300, 14.99, (SELECT id FROM category WHERE name = 'Books')),
    (nextval('product_seq'), 'Comprehensive guide to data science', 'Data Science Book', 150, 29.99, (SELECT id FROM category WHERE name = 'Books')),
    (nextval('product_seq'), 'Popular science fiction series', 'Sci-Fi Series', 100, 24.99, (SELECT id FROM category WHERE name = 'Books')),
    (nextval('product_seq'), 'Cookbook with healthy recipes', 'Cookbook', 200, 19.99, (SELECT id FROM category WHERE name = 'Books'));

-- Insert products for Home & Kitchen category
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES 
    (nextval('product_seq'), 'Energy-efficient microwave oven', 'Microwave Oven', 75, 249.99, (SELECT id FROM category WHERE name = 'Home & Kitchen')),
    (nextval('product_seq'), 'Stainless steel cookware set', 'Cookware Set', 120, 199.99, (SELECT id FROM category WHERE name = 'Home & Kitchen')),
    (nextval('product_seq'), 'High-performance blender', 'Blender', 90, 149.99, (SELECT id FROM category WHERE name = 'Home & Kitchen')),
    (nextval('product_seq'), 'Smart home thermostat', 'Thermostat', 60, 99.99, (SELECT id FROM category WHERE name = 'Home & Kitchen'));

-- Insert products for Sports category
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES 
    (nextval('product_seq'), 'Adjustable dumbbell set', 'Dumbbell Set', 80, 249.99, (SELECT id FROM category WHERE name = 'Sports')),
    (nextval('product_seq'), 'Yoga mat with non-slip surface', 'Yoga Mat', 200, 29.99, (SELECT id FROM category WHERE name = 'Sports')),
    (nextval('product_seq'), 'Treadmill with various workout modes', 'Treadmill', 50, 999.99, (SELECT id FROM category WHERE name = 'Sports')),
    (nextval('product_seq'), 'Resistance bands for workouts', 'Resistance Bands', 150, 19.99, (SELECT id FROM category WHERE name = 'Sports'));
