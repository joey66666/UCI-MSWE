-- Question 1
-- INSERT INTO terms (terms_id, terms_description, terms_due_days) VALUES(6, 'Net due 120 days', 120);


-- Question 2
-- UPDATE terms SET terms_description = 'Net due 125 days',  terms_due_days = 125
-- WHERE terms_id = 6;


-- Question 3
-- DELETE FROM terms WHERE terms_id = 6;


-- Question 4
-- INSERT INTO invoices VALUES(NULL, 32, 'AX-014-027', '2018-8-1', 434.58, 0.00, 0.00, 2, '2018-8-31', null);


-- Question 5
-- INSERT INTO invoice_line_items VALUES
-- (115, 1, 160, 180.23, 'Hard drive'),
-- (115, 2, 527, 254.35, 'Exchange Server update');


-- Question 6
-- UPDATE invoices
-- SET credit_total = 0.1 * invoice_total, payment_total = invoice_total - credit_total
-- WHERE invoice_id = 115;


-- Question 7
-- UPDATE vendors
-- SET default_account_number = 403
-- WHERE vendor_id = 44;


-- Question 8
-- UPDATE invoices
-- SET terms_id = 2
-- WHERE vendor_id = ANY
-- 			(SELECT vendor_id 
-- 			FROM vendors 
-- 			WHERE default_terms_id = 2);
-- 			
-- SELECT * FROM invoices WHERE vendor_id = ANY
-- 			(SELECT vendor_id 
-- 			FROM vendors 
-- 			WHERE default_terms_id = 2)


-- Question 9
-- DELETE FROM invoices
-- WHERE invoice_id = 115;

-- DELETE FROM invoice_line_items 
-- WHERE invoice_id = 115;
-- DELETE FROM invoices
-- WHERE invoice_id = 115;



